package com.vikas.task.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vikas.task.controller.Emptyvalueexception;
import com.vikas.task.controller.NoValuePresent;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handlenosuchelement(NoSuchElementException elementexception) {
		return new ResponseEntity<String>("No Value with given id is present", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoValuePresent.class)
	public ResponseEntity<String> handlenovaluepresentelement(NoValuePresent elementexception) {
		return new ResponseEntity<String>("No employee with given id is present", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Emptyvalueexception.class)
	public ResponseEntity<String> handleemptypresentelement(Emptyvalueexception elementexception) {
		return new ResponseEntity<String>("name cannot be empty", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handlenullvaluepresentelement(NullPointerException elementexception) {
		return new ResponseEntity<String>("Please Enter valid fields", HttpStatus.NOT_FOUND);
	}
	/*
	 * @ExceptionHandler(SQLIntegrityConstraintViolationException.class) public
	 * ResponseEntity<String>
	 * handlesqlelement(SQLIntegrityConstraintViolationException elementexception){
	 * return new ResponseEntity<String>("Sorry cannot delete the employee",
	 * HttpStatus.NOT_FOUND); }
	 */

}
