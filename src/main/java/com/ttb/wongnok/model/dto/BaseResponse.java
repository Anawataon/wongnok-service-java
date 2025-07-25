package com.ttb.wongnok.model.dto;

public record BaseResponse<T>(
        T result,
        String message) {
}