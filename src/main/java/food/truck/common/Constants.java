package food.truck.common;

public class Constants {
    public static final String API_ENDPOINT = "https://data.sfgov.org/resource/jjew-r69b.json";
    public static final String API_KEY = "api_key";
    public static final int PER_PAGE = 10;
    public static final String[] TIMES = new String[]{"'12AM'", "'1AM'", "'2AM'", "'3AM'", "'4AM'", "'5AM'", "'6AM'", "'7AM'", "'8AM'",
            "'9AM'", "'10AM'", "'11AM'", "'12PM'", "'1PM'", "'2PM'", "'3PM'", "'4PM'",
            "'5PM'", "'6PM'", "'7PM'", "'8PM'", "'9PM'", "'10PM'", "'11PM'"};

    public static class QueryKeywords {
        public static final String WHERE = "$where";
        public static final String ORDER = "$order";
        public static final String LIMIT = "$limit";
        public static final String OFFSET = "$offset";
    }

    public static class FoodTruckAttributes {
        public static final String FIELD_DAYORDER = "dayorder";
        public static final String FIELD_STARTTIME = "starttime";
        public static final String FIELD_ENDTIME = "endtime";
        public static final String FIELD_APPLICANT = "applicant";
    }
}
