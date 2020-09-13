package food.truck.service;

import food.truck.api.FoodTruckFetchImpl;
import food.truck.api.FoodTruckFetching;
import food.truck.common.Constants;
import food.truck.dto.FoodTruckDTO;
import food.truck.entity.FoodTruck;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static food.truck.common.Constants.FoodTruckAttributes.*;
import static food.truck.common.Constants.QueryKeywords.*;

public class FoodTruckServiceImpl implements FoodTruckService {
    private static Logger logger = Logger.getLogger(FoodTruckFetchImpl.class);
    private static FoodTruckService instance;
    private FoodTruckFetching foodTruckFetching;

    private FoodTruckServiceImpl() {
        foodTruckFetching = FoodTruckFetchImpl.getInstance();
    }

    public static FoodTruckService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new FoodTruckServiceImpl();
        }

        return instance;
    }

    @Override
    public List<FoodTruckDTO> getOpenTrucks(int page, int perPage) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put(WHERE, getCurrentHourQuery());
        params.put(ORDER, orderByName());
        params.put(OFFSET, String.valueOf((page - 1) * perPage));
        params.put(LIMIT, String.valueOf(perPage));

        return convertToDTO(foodTruckFetching.getTrucks(params));
    }

    private List<FoodTruckDTO> convertToDTO(List<FoodTruck> foodTrucks) {
        return Optional.ofNullable(foodTrucks).orElse(Collections.emptyList()).
                stream().map(f -> new FoodTruckDTO(f.getApplicant(), f.getLocation())).collect(Collectors.toList());
    }

    private String getCurrentHourQuery() {
        StringBuilder sb = new StringBuilder();
        Calendar current = Calendar.getInstance();
        SimpleDateFormat hourFormatter = new SimpleDateFormat("ha");
        int day = current.get(Calendar.DAY_OF_WEEK);
        String hour = "'" + hourFormatter.format(current.getTime()) + "'";
        sb.append(FIELD_DAYORDER);
        sb.append("=");
        sb.append(day);
        sb.append(" AND ");

        //Build the list of hours before the current hour
        List<String> startHours = new ArrayList<>();
        for (String h : Constants.TIMES) {
            startHours.add(h);
            if (hour.equalsIgnoreCase(h)) {
                break;
            }
        }
        sb.append(FIELD_STARTTIME);
        sb.append(" in(");
        sb.append(startHours.stream().collect(Collectors.joining(",")));
        sb.append(")");

        //Build the list of hours after the current hour
        List<String> endHours = new ArrayList<>();
        for (int i = Constants.TIMES.length - 1; i >= 0; i--) {
            endHours.add(Constants.TIMES[i]);
            if (hour.equalsIgnoreCase(Constants.TIMES[i])) {
                break;
            }
        }
        sb.append(" AND ");
        sb.append(FIELD_ENDTIME);
        sb.append(" in(");
        sb.append(endHours.stream().collect(Collectors.joining(",")));
        sb.append(")");

        return sb.toString();
    }

    private String orderByName() {
        StringBuilder sb = new StringBuilder();
        sb.append(FIELD_APPLICANT);
        sb.append(" ASC");

        return sb.toString();
    }
}
