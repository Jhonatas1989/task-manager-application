package com.oliveira.task.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oliveira.task.model.Task;

/**
 * @author Jhonatas Oliveira
 *
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

	/**
	 * @param user
	 * @param dateAfter
	 * @param includeCompleted
	 * @return list of tasks
	 */
	@Query(value = "select * from Task t "
			+ " inner join User u on t.user_id = u.id "
			+ " WHERE "
			+ " ( :user is null or u.name = :user) "
			+ " and ( :dateAfter is null or t.due > :dateAfter) "
			+ " and ( t.is_completed = :includeCompleted or t.is_completed = false) ", nativeQuery = true)
	List<Task> findByFilter(@Param("user") String user, @Param("dateAfter") LocalDate dateAfter,
			@Param("includeCompleted") Boolean includeCompleted);

}
