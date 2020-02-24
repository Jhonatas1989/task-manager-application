package com.oliveira.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.task.dto.UserDTO;
import com.oliveira.task.service.UserService;

/**
 * @author Jhonatas Oliveira
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

	@Autowired
	private UserService service;

	/**
	 *
	 * @return list of users
	 */
	@GetMapping("users")
	public ResponseEntity<List<UserDTO>> getUsers() {
		return new ResponseEntity<List<UserDTO>>(service.findAllUsers(), HttpStatus.OK);
	}

}
