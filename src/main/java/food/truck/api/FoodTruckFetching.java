package food.truck.api;

import food.truck.entity.FoodTruck;

import java.util.List;
import java.util.Map;

public interface FoodTruckFetching {
    /**
     * Get list of trucks by given params
     *
     * @param params key value pair param
     * @return list of {@link FoodTruck}
     * @throws Exception
     */
    List<FoodTruck> getTrucks(Map<String, String> params) throws Exception;
}
