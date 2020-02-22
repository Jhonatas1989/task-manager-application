package com.oliveira.task.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Jhonatas Oliveira
 *
 */
public class TaskDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonProperty("user")
	private String user;

	@JsonProperty("task")
	private String task;

	@JsonProperty("due")
	private String due;

	@JsonProperty("isCompleted")
	private boolean isCompleted;

	/**
	 * 
	 */
	public TaskDTO() {
	}

	/**
	 * @param id
	 * @param user
	 * @param task
	 * @param due
	 * @param isCompleted
	 */
	public TaskDTO(Long id, String user, String task, String due, boolean isCompleted) {
		super();
		this.id = id;
		this.user = user;
		this.task = task;
		this.due = due;
		this.isCompleted = isCompleted;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * @return the due
	 */
	public String getDue() {
		return due;
	}

	/**
	 * @param due the due to set
	 */
	public void setDue(String due) {
		this.due = due;
	}

	/**
	 * @return the isCompleted
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	/**
	 * @param isCompleted the isCompleted to set
	 */
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", user=" + user + ", task=" + task + ", due=" + due + ", isCompleted="
				+ isCompleted + "]";
	}

}
