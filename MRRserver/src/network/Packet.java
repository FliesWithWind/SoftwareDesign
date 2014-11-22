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
	LOGIN 				= 0,	// Request to validate ID-PW
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
	SEARCH_ROOMS		= 100,	// Search rooms
	QUERY_RSRVS			= 105,	// Query reservations on a certain room
	/******************************* Flag List - Server Side *******************************/
	/* Simple Messages */
	_ACCEPTED 			= 0,	// Simple positive (= Yes)
	_INVALID_ACNT 		= -1,	// Invalid ID-PW
	_INVALID_ACCESS		= -2,	// Invalid authorization
	_REJECTED 			= -3,	// Rejected by server control (e.g : request on deleted room)
	_OUTDATED_VERSION	= -9,	// Needed to be updated (For further maintenance)
	/* Account */
	_MY_ACNT			= 10,	// Your account properties
	//_REGISTER 		= 11,	// Response with _ACCEPTED and _REJECTED
	//_EDIT_ACNT		= 12,	// Response with _ACCEPTED and _REJECTED
	/* Room */
	_MY_ROOMS			= 20,	// Your room list
	_CREATE_ROOM		= 21,	// Your room list
	_EDIT_ROOM			= 22,	// Your room list
	//_REMOVE_ROOM		= 23,	// Response with _ACCEPTED and _REJECTED
	/* Reservation - User */
	_MY_RSRVS			= 30,	// [0] - Your Reservations, [1] - Reservation list of a room
	_RESERVE			= 31,	// [0] - Your Reservations, [1] - Reservation list of a room
	_REQ_CANCEL_RSRV	= 34,	// [0] - Your Reservations, [1] - Reservation list of a room
	/* Reservation - Staff */
	_OPEN_RSRV			= 41,	// List of reservations on a certain room
	_CLOSE_RSRV			= 43,	// List of reservations on a certain room
	_CANCEL_RSRV		= 44,	// List of reservations on a certain room
	/* Managing registration */
	_QUERY_REGS			= 51,	// List of Requests of registration
	_ACCEPT_REG			= 52,	// List of Requests of registration
	_REJECT_REG			= 53,	// List of Requests of registration
	/* Searching room & Querying reservations */
	_SEARCH_PRI			= 101,	// These data are primary searched result
	_SEARCH_SEC			= 102,	// These data are secondary searched result
	_QUERY_RSRVS		= 105;	// List of reservations on a certain room
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
