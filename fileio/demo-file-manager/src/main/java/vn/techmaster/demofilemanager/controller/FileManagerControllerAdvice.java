package vn.techmaster.demofilemanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileManagerControllerAdvice {
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<?> handleFileSizeLimitExceeded(MaxUploadSizeExceededException exc) {
	    return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
