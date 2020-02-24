package com.oliveira.task.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Jhonatas Oliveira
 *
 */
@Entity
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	private String task;

	private LocalDate due;

	private boolean isCompleted;

	/**
	 * 
	 */
	public Task() {
	}

	/**
	 * @param id
	 * @param user
	 * @param task
	 * @param due
	 * @param isCompleted
	 */
	public Task(Long id, User user, String task, LocalDate due, boolean isCompleted) {
		super();
		this.id = id;
		this.user = user;
		this.task = task;
		this.due = due;
		this.isCompleted = isCompleted;
	}

	/**
	 * @param due
	 * @param task
	 * @param user
	 */
	public Task(LocalDate due, String task, User user) {
		this.task = task;
		this.due = due;
		this.user = user;
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
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
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
	public LocalDate getDue() {
		return due;
	}

	/**
	 * @param due the due to set
	 */
	public void setDue(LocalDate due) {
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
		return "Task [id=" + id + ", user=" + user + ", task=" + task + ", due=" + due + ", isCompleted=" + isCompleted
				+ "]";
	}

}