package com.oliveira.task.filter;

import java.time.LocalDate;
import java.util.Comparator;

import com.oliveira.task.dto.TaskDTO;
import com.oliveira.task.util.Utils;

/**
 * @author Jhonatas Oliveira
 *
 */
public class TaskFilter {

	private String user;
	private LocalDate dateAfter;
	private String sortBy;
	private Boolean includeCompleted;
	private Comparator<TaskDTO> comparator;

	/**
	 * @param taskFilterBuilder
	 */
	public TaskFilter(TaskFilterBuilder taskFilterBuilder) {
		this.user = taskFilterBuilder.user;
		this.dateAfter = taskFilterBuilder.dateAfter;
		this.sortBy = taskFilterBuilder.sortBy;
		this.includeCompleted = taskFilterBuilder.includeCompleted;
		this.comparator = taskFilterBuilder.comparator;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @return the dateAfter
	 */
	public LocalDate getDateAfter() {
		return dateAfter;
	}

	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @return the includeCompleted
	 */
	public Boolean getIncludeCompleted() {
		return includeCompleted;
	}

	/**
	 * @return the comparator
	 */
	public Comparator<TaskDTO> getComparator() {
		return comparator;
	}

	@Override
	public String toString() {
		return "TaskFilter [user=" + user + ", dateAfter=" + dateAfter + ", sortBy=" + sortBy + ", includeCompleted="
				+ includeCompleted + "]";
	}

	public static class TaskFilterBuilder {

		private String user;
		private LocalDate dateAfter;
		private String sortBy;
		private Boolean includeCompleted;
		private Comparator<TaskDTO> comparator;

		/**
		 * @param user
		 * @param dateAfter
		 * @param sortBy
		 * @param includeCompleted
		 */
		public TaskFilterBuilder(String user, LocalDate dateAfter, String sortBy, Boolean includeCompleted) {
			this.user = user;
			this.dateAfter = dateAfter;
			this.sortBy = sortBy;
			this.includeCompleted = includeCompleted;
		}

		/**
		 * 
		 */
		public TaskFilterBuilder() {
		}

		/**
		 * @param user the user to set
		 */
		public TaskFilterBuilder user(String user) {
			this.user = user;
			return this;
		}

		/**
		 * @param dateAfter the dateAfter to set
		 */
		public TaskFilterBuilder dateAfter(String dateAfter) {
			if (dateAfter != null) {
				this.dateAfter = Utils.StringToDate(dateAfter);
			}
			return this;
		}

		/**
		 * @param sortBy the sortBy to set
		 * @throws Exception
		 */
		public TaskFilterBuilder sortBy(String sortBy) {
			this.sortBy = sortBy;
			return this;
		}

		/**
		 * @param includeCompleted the includeCompleted to set
		 */
		public TaskFilterBuilder includeCompleted(Boolean includeCompleted) {
			this.includeCompleted = includeCompleted;
			return this;
		}

		/**
		 * @return TaskFilter
		 */
		public TaskFilter build() {
			return new TaskFilter(this);
		}
	}

}
