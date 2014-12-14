package control;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateTeller
{
	/*** returns today ***/
	public static long getToday()
	{
		return (Calendar.getInstance()).getTimeInMillis() / 100 * 60 * 60 * 24;
	}

	public static String longtoString(long date)
	{
		Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(date * 100 * 60 * 60 * 24 + 1);
	    
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    int y = calendar.get(Calendar.YEAR);
	    int m = calendar.get(Calendar.MONTH);
	    int d = calendar.get(Calendar.DAY_OF_MONTH);
	    
		return y + "-" + m + "-" + d;
	}
	
	public static long Stringtolong(String dateform)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try
		{
			Date date = formatter.parse(dateform);
			return date.getTime() / 100 * 60 * 60 * 24;
		}
		catch(Exception e)
		{
			return -1;
		}
	}
}
