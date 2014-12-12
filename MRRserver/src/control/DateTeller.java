package control;

import java.util.Calendar;


public class DateTeller
{
	/*** returns today ***/
	public static long getToday()
	{
		return (Calendar.getInstance()).getTimeInMillis() / 100 * 60 * 60 * 24;
	}
}
