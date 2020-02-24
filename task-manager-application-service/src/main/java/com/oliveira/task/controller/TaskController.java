package com.oliveira.task.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oliveira.task.dto.TaskDTO;
import com.oliveira.task.filter.TaskFilter;
import com.oliveira.task.service.TaskService;
import com.oliveira.task.util.Utils;

/**
 * @author Jhonatas Oliveira
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TaskController {

	@Autowired
	private TaskService service;

	/**
	 * Used for testing - should clear down all the database entities
	 *
	 * @throws IOException
	 */
	@DeleteMapping("clear")
	public void clear() throws IOException {
		service.clear();
	}

	/**
	 *
	 * @param taskJson
	 * @return the body will be empty, but there will be a "Location" header that
	 *         will contain a URL to the new resource
	 * @throws IOException
	 */
	@PostMapping(value = "/tasks")
	public ResponseEntity<String> create(@RequestBody String request) throws IOException {
		TaskDTO requestTaskDTO = new ObjectMapper().readValue(request, TaskDTO.class);

		String validate = Utils.validateTaskDTO(requestTaskDTO);

		if (validate != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Error", validate).build();
		}

		String path = service.createTask(requestTaskDTO);
		return ResponseEntity.status(HttpStatus.OK).header("Location", path).build();
	}

	/**
	 *
	 * @param id
	 * @return task
	 * @throws JsonProcessingException
	 */
	@GetMapping("tasks/{id}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) throws JsonProcessingException {

		TaskDTO taskDTO = service.findTaskById(id);

		if (null != taskDTO) {
			return new ResponseEntity<TaskDTO>(service.findTaskById(id), HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 *
	 * @param id
	 * @return delete�s status
	 * @throws JsonProcessingException
	 */
	@DeleteMapping("tasks/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id) {

		if (service.findTaskById(id) == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.deleteTask(id);
			return ResponseEntity.ok().build();
		}

	}

	/**
	 * Completes a task - i.e. marks isCompleted = true
	 *
	 * @param id
	 * @return complete alteration�s status
	 * @throws JsonProcessingException
	 */
	@PutMapping("tasks/{id}/complete")
	public ResponseEntity<TaskDTO> complete(@PathVariable Long id) throws JsonProcessingException {

		TaskDTO taskDTO = service.updateTask(id);

		if (taskDTO != null) {
			return ResponseEntity.ok(taskDTO);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 *
	 * @param user      optional - if set then only tasks for this user are returned
	 *                  - if the user doesn't exist return nothing
	 * @param dateAfter optional - if set then only tasks _after_ this date are
	 *                  returned
	 * @param sortBy    sort by the field, in the form: fieldName:asc ,
	 *                  fieldName:desc, fieldName (defaults to ascending) -
	 *                  optional, defaults to id:asc
	 * @return list of tasks
	 * @throws Exception
	 */
	@GetMapping("tasks")
	public ResponseEntity<List<TaskDTO>> searchTask(@RequestParam(required = false) String user,
			@RequestParam(required = false) String dateAfter, @RequestParam(required = false) String sortBy,
			@RequestParam(required = false, defaultValue = "false") Boolean includeCompleted) throws Exception {

		if (dateAfter != null) {
			String validate = Utils.validateIsDayAfter(dateAfter);

			if (validate != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Error", validate).build();
			}
		}

		TaskFilter filter = new TaskFilter.TaskFilterBuilder().dateAfter(dateAfter).user(user).sortBy(sortBy)
				.includeCompleted(includeCompleted).build();

		List<TaskDTO> tasks = service.findTaskByFilter(filter);

		if (!tasks.isEmpty()) {
			return new ResponseEntity<List<TaskDTO>>(service.findTaskByFilter(filter), HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
