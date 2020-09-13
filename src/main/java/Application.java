import food.truck.common.Constants;
import food.truck.controller.FoodTruckController;
import food.truck.controller.Response;
import food.truck.dto.FoodTruckDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Application {
    private static final Logger logger = Logger.getLogger(Application.class);
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";


    private static final String paramPage = "page";
    private static final String perPage = "perPage";
    private static final Map<String, Object> params = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String... envs) {
        configure(envs);
        displayInformation();
        fetchAndDisplayData();

        scanner.close();
    }

    private static void configure(String... env) {
        if (env.length != 0) {
            System.setProperty(Constants.API_KEY, env[0]);
        }
        BasicConfigurator.configure();
    }

    private static void displayInformation() {
        System.out.println(ANSI_BLUE_BACKGROUND + "****FOOD TRUCK FINDING****" + ANSI_RESET);
        askForCustomItemPerPage();
        out("Go to next page: Enter");
        info("Reload: r");
        error("Exit: q");
        out("************************");
    }

    private static void askForCustomItemPerPage() {
        params.put(perPage, Constants.PER_PAGE);
        out("How many truck you want to see each time (up to 50)? Default 10");
        try {
            int custom = Integer.valueOf(scanner.nextLine());
            if (custom >= 10 && custom <= 50) {
                params.put(perPage, custom);
            }
        } catch (Exception e) {
            logger.info("Invalid custom item per page");
        }
        out((String.format("Show %d trucks per page", params.get(perPage))));
    }

    private static void fetchAndDisplayData() {
        FoodTruckController controller = new FoodTruckController();
        Response<FoodTruckDTO> result;
        out("NAME    ADDRESS");
        while (true) {
            params.put(paramPage, (int) params.getOrDefault(paramPage, 0) + 1);
            info(String.format("Fetching page %d...", params.get(paramPage)));
            result = controller.showOpenTrucks(params);
            switch (result.getCode()) {
                case SUCCESS:
                    List<FoodTruckDTO> data = result.getData();
                    if (data.isEmpty()) {
                        warning("No more trucks!");
                        handleUserInput();
                    }
                    data.stream().forEach(System.out::println);
                    if (data.size() == (int) params.get(perPage)) {
                        handleUserInput();
                    }
                    break;
                case FAILED:
                    warning(result.getMessage());
                    handleUserInput();
                    break;
                case ERROR:
                    error(result.getMessage());
                    System.exit(1);
                    break;
            }
        }
    }

    private static void handleUserInput() {
        info("Press 'enter' to next page, 'r' to reload, 'q' to exit");
        switch (scanner.nextLine().toLowerCase()) {
            case "r":
                params.put(paramPage, (int) params.get(paramPage) - 1);
                break;
            case "q":
                System.exit(1);
                break;
            default:
                break;
        }
    }

    private static void out(String message) {
        System.out.println(message);
    }
    private static void info(String message) {
        System.out.println(ANSI_BLUE + message + ANSI_RESET);
    }

    private static void error(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    private static void warning(String message) {
        System.out.println(ANSI_YELLOW + message + ANSI_RESET);
    }
}
