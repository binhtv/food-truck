package food.truck.common;

import food.truck.exception.FailureToFetchDataException;
import food.truck.exception.ThrottlingLimitsException;
import food.truck.exception.UnauthorizedAccessException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiHelper {
    private static Logger logger = Logger.getLogger(ApiHelper.class);

    public static String GET(Map<String, String> params) throws Exception {
        return readResponse(createHttpConnection(params));
    }

    private static String readResponse(HttpURLConnection connection) throws Exception {
        int statusCode = connection.getResponseCode();
        String message = connection.getResponseMessage();
        switch (statusCode) {
            case 200:
                //Success
                break;
            case 429:
                throw new ThrottlingLimitsException(String.format("Number of access reached the limit, code=%d, message=%s", statusCode, message));
            case 403:
                throw new UnauthorizedAccessException(String.format("Unauthorized access, code=%d, message=%s", statusCode, message));
            default:
                throw new FailureToFetchDataException(String.format("Could not fetch the data, code=%d, message=%s", statusCode, message));
        }

        InputStream ip = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ip));
        StringBuilder response = new StringBuilder();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }

        ip.close();
        connection.disconnect();

        return response.toString();
    }

    private static HttpURLConnection createHttpConnection(Map<String, String> params) throws Exception {
        String apiKey = System.getProperty(Constants.API_KEY);
        if (apiKey == null || apiKey.isEmpty()) {
            logger.warn("A limited number of requests can be made without an app token, but they are subject to much lower throttling limits than request that do include one");
        }

        URL url = new URL(buildStringQuery(params));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("X-App-Token", apiKey);

        return connection;
    }

    private static String buildStringQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.API_ENDPOINT);
        if (params != null && !params.isEmpty()) {
            sb.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue().replaceAll("\\s+", "+"));
                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
