package network;

import java.io.Serializable;

public class Packet implements Serializable
{
	private int 	flag;	// which message? (to tell the way of parsing packet, see below)
	private String	id;		// to validate
	private String	pw;		// to validate
	private Object	data;	// data contained in message
	
	public static final transient int
	/******************************* Flag List - Client Side *******************************/
	/* Simple Messages */
	LOGIN 				= 1,	// Request to validate ID-PW
	/* Account */
	MY_ACNT				= 10,	// View my account properties
	REGISTER 			= 11,	// Request to register
	EDIT_ACNT			= 12,	// Edit my account properties
	/* Room */
	MY_ROOMS			= 20,	// View my room list								(Staff)
	CREATE_ROOM			= 21,	// Create new room									(Staff)
	EDIT_ROOM			= 22,	// Edit room properties								(Staff)
	REMOVE_ROOM			= 23,	// Remove room										(Staff)
	/* Reservation - User */
	MY_RSRVS			= 30,	// View reservation list I reserved					(User)
	RESERVE				= 31,	// Reserve a room at a certain date					(User)
	REQ_CANCEL_RSRV		= 34,	// Request to cancel a certain reservation			(User)
	/* Reservation - Staff */
	OPEN_RSRV			= 41,	// Open a room in a certain date to be leased		(Staff)
	CLOSE_RSRV			= 43,	// Close a room not to be leased					(Staff)
	CANCEL_RSRV			= 44,	// Cancel a reservation requested to be canceled	(Staff)
	/* Managing registration */
	QUERY_REGS			= 51,	// Query for requests for registration				(Manager)
	ACCEPT_REG			= 52,	// Accept registration								(Manager)
	REJECT_REG			= 53,	// Reject registration								(Manager)
	/* Searching room & Querying reservations */
	SEARCH_ROOMS		= 60,	// Search rooms
	QUERY_RSRVS			= 65,	// Query reservations on a certain room
	/******************************* Flag List - Server Side *******************************/
	/* Simple Messages */
	_ACCEPTED 			= 0,	// Simple positive (= Yes)
	_REJECTED 			= 1,	// Rejected by server control (e.g : request on deleted room)
	_INVALID_ACNT 		= 2,	// Invalid ID-PW
	_INVALID_ACCESS		= 3,	// Invalid authorization
	_UNKNOWN			= 8,	// Unknown flag
	_OUTDATED_VERSION	= 9,	// Needed to be updated (For further maintenance)
	/* Searching room & Querying reservations */
	_SEARCH_PRI			= 10,	// These data are primary searched result
	_SEARCH_SEC			= 11;	// These data are secondary searched result
	/***************************************************************************************/
		
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
