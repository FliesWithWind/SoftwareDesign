package network;

import java.io.Serializable;

public class Packet implements Serializable
{
	/******************************* Flag List ******************************************/
	//
	// *** define packet flag *** //
	//
	/************************************************************************************/
	
	private int 	flag;	// which message? (to tell the way to parsing packet)
	private String	id;		// to validate
	private String	pw;		// to validate
	private Object	data;	// data contained in message
	
	
	public Packet(int flag, String id, String pw, Object data)
	{
		setFlag(flag);
		setId(id);
		setPw(pw);
		setData(data);
	}
	
	public int getFlag()
	{
		return flag;
	}
	
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getPw()
	{
		return pw;
	}
	
	public void setPw(String pw)
	{
		this.pw = pw;
	}
	
	public Object getData()
	{
		return data;
	}
	
	public void setData(Object data)
	{
		this.data = data;
	}
}
