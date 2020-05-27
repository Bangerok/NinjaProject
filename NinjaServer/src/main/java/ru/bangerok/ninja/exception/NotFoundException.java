package ru.bangerok.ninja.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс исключения для обработки 404 ошибки.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

}
