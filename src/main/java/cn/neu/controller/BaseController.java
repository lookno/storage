package cn.neu.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.neu.exception.ServerException;
import cn.neu.exception.ServiceException;

@ControllerAdvice
public class BaseController {
	private Logger log = Logger.getLogger(this.getClass());

	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity<Object> handleThrowable(Throwable e) {
		log.error("BaseController get an error", e);
		System.out.println("handleThrowable"+" "+e);
		return new ResponseEntity<Object>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = ServerException.class)
	public ResponseEntity<Object> handleThrowable(ServerException e) {
		log.error("BaseController get an ServerException", e);
		System.out.println("handleThrowable"+" "+e);
		return new ResponseEntity<Object>("系统异常,请稍后访问", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = ServiceException.class)
	public ResponseEntity<Object> handleThrowable(ServiceException e) {
		log.error("BaseController get an ServiceException", e);
		return new ResponseEntity<Object>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
}
