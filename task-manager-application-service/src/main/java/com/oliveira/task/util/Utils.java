package com.oliveira.task.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import com.oliveira.task.dto.TaskDTO;

/**
 * @author Jhonatas Oliveira
 *
 */
public final class Utils {

	final static String DUE_CANNOT_BE_IN_THE_PAST = "Due can't be in the past";
	final static String DUE_CANNOT_BE_EMPTY = "Due can't be empty";
	final static String USER_CANNOT_BE_EMPTY = "User can't be empty";
	final static String TASK_CANNOT_BE_EMPTY = "Task can't be empty";
	final static String FORMAT_DATE_REGEX = "^([0-9]{4})[-](0?[1-9]|1[012])[-](0?[1-9]|[12][0-9]|3[01])$";
	final static String PATTERN_DATE = "yyyy-MM-dd";

	/**
	 * @param date
	 * @return true if the date is after the current date
	 */
	public static boolean isDateAfter(LocalDate date) {
		return LocalDate.now().isAfter(date);
	}

	/**
	 * @param date
	 * @return LocalDate Object
	 */
	public static LocalDate StringToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
		return LocalDate.parse(date, formatter);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static String DateToString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
		return date.format(formatter);
	}

	/**
	 * 
	 * @param date
	 * @return true if the format date is like 'yyyy-MM-dd'
	 */
	public static boolean isDateFormatCorrect(String date) {
		return Pattern.matches(FORMAT_DATE_REGEX, date);
	}

	/**
	 * @param taskDTO
	 * @return error�s message
	 */
	public static String validateTaskDTO(TaskDTO taskDTO) {

		if (null == taskDTO.getDue()) {
			return DUE_CANNOT_BE_EMPTY;
		}
		if (isDateAfter(StringToDate(taskDTO.getDue()))) {
			return DUE_CANNOT_BE_IN_THE_PAST;
		}
		if (null == taskDTO.getUser()) {
			return USER_CANNOT_BE_EMPTY;
		}
		if (null == taskDTO.getTask()) {
			return TASK_CANNOT_BE_EMPTY;
		}

		return null;
	}

	/**
	 * @param taskDTO
	 * @return error�s message
	 */
	public static String validateIsDayAfter(String date) {

		if (isDateAfter(StringToDate(date))) {
			return DUE_CANNOT_BE_IN_THE_PAST;
		}

		return null;
	}

}
