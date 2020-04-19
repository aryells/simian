package com.mercadolivre.simian.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, code = HttpStatus.FORBIDDEN, reason = "Not a simian")
public class NotSimianException extends Exception {

}
