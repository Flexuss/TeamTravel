package ru.kpfu.itis.dmitryivanov.response;

/**
 * Created by Dmitry on 06.11.2017.
 */
public class ApiResponse<T> {

    private T responseData;

    public ApiResponse(T responseData) {
        this.responseData = responseData;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

}