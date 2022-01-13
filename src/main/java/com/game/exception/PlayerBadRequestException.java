package com.game.exception;

public class PlayerBadRequestException extends Exception {
    // ошибка код 400
    public PlayerBadRequestException() {}
    public PlayerBadRequestException(String message) {
        super(message);
    }
}