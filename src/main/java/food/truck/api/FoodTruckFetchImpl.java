package food.truck.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import food.truck.common.ApiHelper;
import food.truck.entity.FoodTruck;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FoodTruckFetchImpl implements FoodTruckFetching {
    private static FoodTruckFetching instance;
    private ObjectMapper objectMapper;

    private FoodTruckFetchImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static FoodTruckFetching getInstance() {
        if (Objects.isNull(instance)) {
            instance = new FoodTruckFetchImpl();
        }

        return instance;
    }

    @Override
    public List<FoodTruck> getTrucks(Map<String, String> params) throws Exception {
        return objectMapper.readValue(ApiHelper.GET(params), new TypeReference<List<FoodTruck>>() {
        });
    }
}
