package food.truck.service;

import food.truck.dto.FoodTruckDTO;

import java.util.List;

public interface FoodTruckService {
    /**
     * Get open trucks at the current time
     *
     * @param page    current page
     * @param perPage number of item per page
     * @return list of available truck {@link FoodTruckDTO}
     * @throws Exception
     */
    List<FoodTruckDTO> getOpenTrucks(int page, int perPage) throws Exception;
}
