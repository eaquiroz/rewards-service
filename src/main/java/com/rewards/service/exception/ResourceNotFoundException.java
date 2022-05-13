package com.rewards.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception will only handle the 404 realted http status code. It will
 * throw in case of Customer purchase history is not found.
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3402422054135928874L;

	public ResourceNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}