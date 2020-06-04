package ru.wolfa.mvc.err.handler.sfw5.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
	Logger log = LoggerFactory.getLogger(ErrorHandler.class);

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		log.error("ERROR catched in handleBindException! {}", request.getDescription(false), ex);
		/**
		 * See next method - it has java.lang.reflect.Method in exception.
		 * BindException does not.
		 * But! ModelAttributeMethodProcessor.resolveArgument has information about Method.
		 */
		return super.handleBindException(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("ERROR catched in handleMethodArgumentNotValid! {}", ex.getParameter().getMethod().toGenericString(),
				ex);
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}
}
