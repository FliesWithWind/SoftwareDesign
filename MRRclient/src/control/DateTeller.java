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
    
	public static Date getTodayDate()
	{
		return (Calendar.getInstance()).getTime();
	}
    
	public static String longtoString(long date)
	{
		Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(date * 100 * 60 * 60 * 24 + 1);
	    
	    int y = calendar.get(Calendar.YEAR) + 1900;
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
    
	public static Date StringtoDate(String dateform)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try
		{
			return formatter.parse(dateform);
		}
		catch(Exception e)
		{
			return null;
		}
	}
    
    public static long Datetolong(Date date)
    {
        if(date == null) return -1;
        return  date.getTime() / 100 * 60 * 60 * 24;
    }
    
	public static Date longtoDate(long date)
	{
		Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(date * 100 * 60 * 60 * 24 + 1);
        
		return calendar.getTime();
	}
}
