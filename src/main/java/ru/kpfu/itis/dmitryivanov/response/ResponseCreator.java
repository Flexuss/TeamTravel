package ru.kpfu.itis.dmitryivanov.response;

/**
 * Created by Dmitry on 06.11.2017.
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public abstract class ResponseCreator {

    protected <T> ResponseEntity<ApiResponse<T>> createGoodResponse(T body) {
        return createResponse(body, HttpStatus.OK);
    }

    protected <T> ResponseEntity<ApiResponse<T>> createGoodResponse() {
        return createGoodResponse(null);
    }

    protected <T> ResponseEntity<ApiResponse<T>> createBadResponse(String error) {
        return createResponse((T) error, HttpStatus.BAD_REQUEST);
    }

    private <T> ResponseEntity<ApiResponse<T>> createResponse(T body, HttpStatus status) {
        return new ResponseEntity<>(new ApiResponse<>(body), status);
    }

}
