package com.oliveira.task.service;

import java.util.List;

import com.oliveira.task.dto.UserDTO;

/**
 * @author Jhonatas Oliveira
 *
 */
public interface UserService {

	/**
	 * @return list with all users
	 */
	public List<UserDTO> findAllUsers();

}
