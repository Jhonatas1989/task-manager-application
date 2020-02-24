package com.oliveira.task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.oliveira.task.dto.TaskDTO;
import com.oliveira.task.filter.TaskFilter;
import com.oliveira.task.model.Task;
import com.oliveira.task.model.User;
import com.oliveira.task.repository.TaskRepository;
import com.oliveira.task.repository.UserRepository;
import com.oliveira.task.util.Utils;



/**
 * @author Jhonatas Oliveira
 *
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Value("${application.host}")
	private String appRoot;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public String createTask(TaskDTO taskDTO) {

		User user = userRepository.findByName(taskDTO.getUser());

		if (user == null) {
			user = new User(taskDTO.getUser());
			user = userRepository.save(user);
		}

		Task task = taskRepository.save(new Task(Utils.StringToDate(taskDTO.getDue()), taskDTO.getTask(), user));

		return appRoot + "/tasks/" + task.getId();
	}

	@Override
	public List<TaskDTO> findAllTasks() {

		List<TaskDTO> tasks = new ArrayList<TaskDTO>();

		for (Task task : taskRepository.findAll()) {
			tasks.add(new TaskDTO(task.getId(), task.getUser().getName(), task.getTask(),
					Utils.DateToString(task.getDue()), task.isCompleted()));
		}

		return tasks;
	}

	@Override
	public TaskDTO findTaskById(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);

		if (optionalTask.isPresent()) {
			Task task = optionalTask.get();
			return new TaskDTO(task.getId(), task.getUser().getName(), task.getTask(),
					Utils.DateToString(task.getDue()), task.isCompleted());
		}

		return null;

	}

	@Override
	public TaskDTO updateTask(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);

		Task task = null;
		if (optionalTask.isPresent()) {
			task = optionalTask.get();
			task.setCompleted(true);
			taskRepository.saveAndFlush(task);

			return new TaskDTO(task.getId(), task.getUser().getName(), task.getTask(),
					Utils.DateToString(task.getDue()), task.isCompleted());
		}
		return null;
	}

	@Override
	public void deleteTask(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);

		if (optionalTask.isPresent()) {
			Task task = optionalTask.get();
			task.setCompleted(true);
			taskRepository.delete(task);
		}

	}

	@Override
	public void clear() {
		taskRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Override
	public List<TaskDTO> findTaskByFilter(TaskFilter taskFilter) {

		List<TaskDTO> tasks = new ArrayList<TaskDTO>();

		for (Task task : taskRepository.findByFilter(taskFilter.getUser(), taskFilter.getDateAfter(),
				taskFilter.getIncludeCompleted())) {
			tasks.add(new TaskDTO(task.getId(), task.getUser().getName(), task.getTask(),
					Utils.DateToString(task.getDue()), task.isCompleted()));
		}

		String sortBy = taskFilter.getSortBy();

		return tasks.stream().sorted((o1, o2) -> sort(sortBy, o1, o2)).collect(Collectors.toList());
	}

	/**
	 * @param sortBy
	 * @param o1
	 * @param o2
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int sort(@RequestParam(required = false) String sortBy, TaskDTO o1, TaskDTO o2) {
		if (null == sortBy) {
			return o1.getId().compareTo(o2.getId());
		} else {
			String sortField = sortBy.contains(":") ? sortBy.substring(0, sortBy.indexOf(":")) : sortBy;
			boolean isDescending = sortBy.contains(":") && sortBy.substring(sortBy.indexOf(":") + 1).equals("desc");

			Comparable f1 = selectField(o1, sortField);
			Comparable f2 = selectField(o2, sortField);

			// sort by ids ascending if the fields are the same
			if (f1.equals(f2)) {
				return o1.getId().compareTo(o2.getId());
			}
			if (isDescending) {
				return f2.compareTo(f1);
			} else {
				return f1.compareTo(f2);
			}

		}
	}

	/**
	 * @param task
	 * @param field
	 * @return
	 */
	private Comparable<?> selectField(TaskDTO task, String field) {
		switch (field) {
		case "user":
			return task.getUser();
		case "id":
			return task.getId();
		case "due":
			return task.getDue();
		case "task":
			return task.getTask();
		default:
			throw new IllegalArgumentException("Not a field: " + field);
		}
	}
}
