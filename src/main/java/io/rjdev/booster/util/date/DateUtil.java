package io.rjdev.booster.util.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * Date util class.
 *
 * @author Renato de Paula Eduardo Jr
 * @since 17/08/2009
 * @see Date
 */
public class DateUtil {

	private static final String DEFAULT_DT_FORMAT = "dd/MM/yyyy";

	/**
	 * Method to add hours on the current date.
	 * @param addHours number of hours to be added.
	 * @return Date object with the current date added the number of hours.
	 */
	public static final Date dateNowAddHours(int addHours){
		Calendar now = Calendar.getInstance();

		now.add(Calendar.HOUR, addHours);

		return now.getTime();
	}

	/**
	 * Method to add hours on the date received.
	 * @param date to be calculated.
	 * @param addHours number of hours to be added.
	 * @return Date object with the date added the number of hours.
	 */
	public static final Date dateAddHours(Date date, int addHours){
		Calendar newDate = Calendar.getInstance();

		newDate.setTime(date);

		newDate.add(Calendar.HOUR, addHours);

		return newDate.getTime();
	}

	/**
	 * Method to return a date in the received format.
	 * @param date Date received, if null get date now
	 * @param Date date format, if null get default dd/MM/yyyy
	 * @return Formatted Date String.
	 */
	public static final String getStringDateFormat(Date date, String format){
		if(date == null)
			date = new Date();
		if(format==null)
			format = DEFAULT_DT_FORMAT;
		String sdf = null;
		DateFormat formatter = new SimpleDateFormat(format);
		sdf = formatter.format(date);
		return sdf;
	}

	/**
	 * Method to return the received date with the values
	 * of time chosen, (hour, minute, seconds).
	 *
	 * @param date Date
	 * @param hora Hour of the date
	 * @param min minutes of the date
	 * @param seg seconds of the date
	 * @return Date updated
	 */
	public static final Date getDate(Date date, int hour, int min, int sec){

		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, hour);
	    cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);

	    return cal.getTime();
	}

	/**
	 * Method to return the day from a date.
	 * @param date date entry
	 * @return the day referring to the date of entry
	 */
	public static final int getDateDay(Date date){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);

	    return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Method to return the month from a date.
	 * @param date date entry
	 * @return the month referring to the date of entry
	 */
	public static final int getDateMonth(Date date){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int mes = cal.get(Calendar.MONTH);
	    return mes+=1;
	}

	/**
	 * Method to return the year from a date.
	 * @param date date entry
	 * @return the year referring to the date of entry
	 */
	public static final int getDateYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.YEAR);
	}

	/**
	 * Checks whether the date reported and a date in the future
	 * from now on
	 *
	 * @param date
	 * @return true boolean if date and future, greater than the
     * Date now, or false otherwise.
	 */
	public static boolean isDateFutureFromNow(Date date){

		if(date==null)
			return false;

		return date.after(new Date());
    }

    /**
     * Returns the number of hours from a valid date.
     * If date not given, returns -1.
     *
     * @param date Date to check the number of hours
     * @return long number of hours passed from the date
     * Informed.
     *
     * @since 23/09/2018 17:40
     */
    public static long hoursPastDate(Date date){
        if(date==null)
            return -1;

        long secs = ( new Date().getTime() - date.getTime()) / 1000;
        long hours = secs / 3600;

        return hours;
    }

	/**
	 * Method to converter a date java Date to JODA LocalDate date
	 * @param date
	 * @return LocalDate Jodatime
	 * @since 25/01/2018
	 */
	public static LocalDate convertUtilDateToLocalDate(Date date) {
		if(date==null) return null;
		DateTime dt = new DateTime(date);
		return dt.toLocalDate();
	}

	/**
	 * Calculate the days between two dates.
	 *
	 * @param datei Day ini
	 * @param datef Day end
	 * @return number the days between two dates.
	 */
	public static int daysBetweenDates(Date datei, Date datef){
		LocalDate dateStart = convertUtilDateToLocalDate(datei);
		LocalDate dateEnd = convertUtilDateToLocalDate(datef);
		int days = Days.daysBetween(dateStart , dateEnd).getDays();
		return days;
	}
}