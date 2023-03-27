package io.rjdev.booster.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class with methods to perform conversions.
 *
 * @author Antonio Carlos Venancio Junior
 * @author $Author: ec2-user $
 * @version $Revision: 1.1.1.1 $
 * @since 07/02/2006
 * @since $Date: 2018/10/05 17:10:38 $
 * @since 1.1
 */
public class Converter {

	/**
	 * Converts a text representing a date to another format.
	 *
	 * Ex.: convertDate("10/02/2005", "dd/MM/yyyy", "yyyy-MM-dd")
	 *
	 * @param sDate
	 *          date to be converted
	 * @param of
	 *          date input format
	 * @param to
	 *          date output format
	 * @return
	 *          formatted converted date
	 * @throwsException
	 *          if there is an error when parsing the date
	 *
	 * @see SimpleDateFormat#parse(String,
	 * 		java.text.ParsePosition)
	 * @see SimpleDateFormat#format(Date,
	* 		StringBuffer, java.text.FieldPosition)
	 * @since 1.1
	 */
	public static final String convertDate(String sDate, String of, String to)
			throws Exception {
		SimpleDateFormat fmtOf = new SimpleDateFormat(of);
		SimpleDateFormat fmtTo = new SimpleDateFormat(to);
		Date date;
		try {
			date = fmtOf.parse(sDate);
		} catch (ParseException e) {
			throw new Exception("Date parse error!");
		}

		return fmtTo.format(date);
	}

	/**
	 * Converts a Date to a String. Set "dd/MM/yyyy"
	 * by default if shape value is null
	 *
	 * Ex.: dataParaString(objData, "yyy.MM.mm")
	 *
	 * @param sDate
	 *            date to converted
	 * @param fmt
	 *            date output format string
	 *
	 * @return Date string
	 * @throws Exception
	 *             if there is an error when parsing the date
	 * @see SimpleDateFormat#format(Date,
	 *      StringBuffer, java.text.FieldPosition)
	 * @since 1.1
	 */
	public static final String dateToString(Date sDate, String fmt) throws Exception {

		fmt = (fmt == null) ? "dd/MM/yyyy" : fmt;
		DateFormat dFormat = new SimpleDateFormat(fmt);
		String sDtFmted = dFormat.format(sDate);

		return sDtFmted;
	}

	/**
	 * Overload of method dataParaString(Date data, String forma).
	 * Converts the Date object to a String in MM/dd/yyyy format
	 *
	 * Ex.: dateToString(objData)
	 *
	 * @param data
	 *            date to be converted
	 *
	 * @return String of the date
	 * @throws Exception
	 *             if there is an error when parsing the date
	 *
	 * @since 1.1
	 */
	public static final String dateToString(Date data) throws Exception {
		return dateToString(data, null);
	}

	/**
	 * Converts a String on Date. Set "dd/MM/yyyy"
	 * by default if shape value is null
	 *
	 * Eg: stringToDate(objData, "yyy.MM.mm")
	 *
	 * @param sDate
	 *            date to convert to String format
	 * @param fmt
	 *            date input/output format string
	 *
	 * @return String date
	 * @throws Exception
	 *             if there is an error when parsing the date
	 * @see SimpleDateFormat#format(Date,
	 *      StringBuffer, java.text.FieldPosition)
	 * @since 1.1
	 */
	public static final Date stringToDate(String sDate, String fmt) throws Exception {
		fmt = (fmt == null) ? "dd/MM/yyyy" : fmt;
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		Date date;
		try {
			date = format.parse(sDate);
		} catch (ParseException e) {
			throw new Exception("Date parse error!");
		}

		return date;
	}

	/**
     * Method overload.
     * Transforms the date passed in String format to Date
     * The date must be in mm/dd/yyyy format and
     * is returned in the same format
     *
     * Eg: stringToDate(objStr)
	 *
	 * @param data
	 *            date to convert to String format
     *            date input/output format string
	 *
	 * @return String date
	 * @throws Exception
	 *              if there is an error when parsing the date
	 * @see SimpleDateFormat#format(Date,
	 *      StringBuffer, java.text.FieldPosition)
	 * @since 1.1
	 */
	public static final Date stringToDate(String data) throws Exception {

		return stringToDate(data, null);
	}


}