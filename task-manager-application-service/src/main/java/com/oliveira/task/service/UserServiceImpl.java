package com.oliveira.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.oliveira.task.dto.UserDTO;
import com.oliveira.task.model.User;
import com.oliveira.task.repository.UserRepository;

/**
 * @author Jhonatas Oliveira
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDTO> findAllUsers() {
		List<UserDTO> users = new ArrayList<UserDTO>();

		for (User user : userRepository.findAll(Sort.by("name").ascending())) {
			users.add(new UserDTO(user.getId(), user.getName()));
		}

		return users;
	}

}
