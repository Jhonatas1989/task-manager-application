package com.oliveira.task.service;

import java.util.List;

import com.oliveira.task.dto.TaskDTO;
import com.oliveira.task.dto.UserDTO;
import com.oliveira.task.filter.TaskFilter;

/**
 * @author Jhonatas Oliveira
 *
 */
public interface TaskService {

	/**
	 * @return list with all users
	 */
	public List<UserDTO> findAllUsers();

	/**
	 * @param taskDTO
	 */
	public String createTask(TaskDTO taskDTO);

	/**
	 * @return list with all users
	 */
	public List<TaskDTO> findAllTasks();

	/**
	 * @param id
	 * @return task
	 */
	public TaskDTO findTaskById(Long id);

	/**
	 * @param taskFilter
	 * @return task
	 */
	public List<TaskDTO> findTaskByFilter(TaskFilter taskFilter);

	/**
	 * @param id
	 */
	public TaskDTO updateTask(Long id);

	/**
	 * @param id
	 */
	public void deleteTask(Long id);

	/**
	 * clear all tables
	 */
	public void clear();

}
