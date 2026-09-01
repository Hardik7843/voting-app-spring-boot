package com.voting.app.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T> {

    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

    private long statusCode;

    private String message;

    private String error;

    private Map<String, ?> errors;

    private T data;

    private boolean success;


    public ApiResponseDto(long statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.success = statusCode >= 200 && statusCode < 300;
    }

    public ApiResponseDto(long statusCode, String error, Map<String, ?> errors) {
        this.statusCode = statusCode;
        this.error = error;
        this.errors = errors;
        this.success = statusCode >= 200 && statusCode < 300;
    }

}
