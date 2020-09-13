package food.truck.controller;

import food.truck.common.ApiStatus;

import java.io.Serializable;
import java.util.List;

public class Response<T> implements Serializable {
    private ApiStatus code;
    private String message;
    private List<T> data;

    public Response(List<T> data, ApiStatus code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public ApiStatus getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getData() {
        return data;
    }
}
