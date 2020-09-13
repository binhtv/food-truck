package food.truck.controller;

import food.truck.common.ApiStatus;
import food.truck.dto.FoodTruckDTO;
import food.truck.exception.FailureToFetchDataException;
import food.truck.exception.ThrottlingLimitsException;
import food.truck.exception.UnauthorizedAccessException;
import food.truck.service.FoodTruckService;
import food.truck.service.FoodTruckServiceImpl;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Map;

public class FoodTruckController {
    private static final Logger logger = Logger.getLogger(FoodTruckController.class);

    private FoodTruckService foodTruckService;

    public FoodTruckController() {
        foodTruckService = FoodTruckServiceImpl.getInstance();
    }

    public Response<FoodTruckDTO> showOpenTrucks(Map<String, Object> params) {
        try {
            int page = (int) params.get("page");
            int perPage = (int) params.get("perPage");

            return new Response<>(foodTruckService.getOpenTrucks(page, perPage), ApiStatus.SUCCESS, "Success");
        } catch (ThrottlingLimitsException e) {
            logger.info("LOG: Failure to handle the request", e);
            return new Response<>(Collections.emptyList(), ApiStatus.FAILED, "You reached the limits, please try again in sometime");
        } catch (FailureToFetchDataException e) {
            logger.info("LOG: Failure to handle the request", e);
            return new Response<>(Collections.emptyList(), ApiStatus.FAILED, "Data source is unavailable, please try again");
        } catch (UnauthorizedAccessException e) {
            logger.info("LOG: Failure to handle the request", e);
            return new Response<>(Collections.emptyList(), ApiStatus.ERROR, "Unauthorized access");
        } catch (Exception exc) {
            logger.error("LOG: Error occurred", exc);
            return new Response<>(Collections.emptyList(), ApiStatus.ERROR, "Internal Error");
        }
    }
}
