package com.voting.app.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {

    private Timestamp timestamp;

    private long statusCode;

    private String message;

    private T data;


    public ApiResponseDto(long statusCode, String message, T data) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ApiResponseDto(Timestamp timestamp) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
