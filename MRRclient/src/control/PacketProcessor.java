package control;

import java.util.ArrayList;

import datatype.*;
import network.Packet;
import ui.*;

public class PacketProcessor 
{
	// singleton /////////////////////////////////////////////////
	private	static volatile PacketProcessor self;   			//
	public	static PacketProcessor getInstance()				//
	{															//
		if(self == null) synchronized(PacketProcessor.class)	//
			{ if(self == null) self = new PacketProcessor(); }	//
		return self;											//
	}/////////////////////////////////////////////////////////////
	
	// UI mainframe;
	
	
	private PacketProcessor ()
	{
		// ***to be implemented***
	}
	
	public void process(Packet packet)
	{
		switch(packet.getFlag())
		{
		case Packet._ACCEPTED:
			// mainframe.dialogue(0, STR.NOTI_TITLE_ACCEPTED, STR.NOTI_CONTENT_ACCEPTED);
			break;

		case Packet._INVALID_ACNT:
			// mainframe.dialogue(1, STR.NOTI_TITLE_INVALID_ACNT, STR.NOTI_CONTENT_INVALID_ACNT);
			break;

		case Packet._INVALID_ACCESS:
			// mainframe.dialogue(2, STR.NOTI_TITLE_INVALID_ACCESS, STR.NOTI_CONTENT_INVALID_ACCESS);
			break;

		case Packet._REJECTED:
			// mainframe.dialogue(1, STR.NOTI_TITLE_REJECTED, (String) packet.getData());
			break;

		case Packet._OUTDATED_VERSION:
			// mainframe.dialogue(1, STR.NOTI_TITLE_OUTDATED_VERSION, STR.NOTI_CONTENT_OUTDATED_VERSION);
			break;

			
		case Packet._MY_ACNT:
			// mainframe.updateAccountSection((Account) packet.getData());
			break;

		//case Packet._REGISTER:
			// Response with _ACCEPTED and _REJECTED
			//break;

		//case Packet.EDIT_ACNT:
			// Response with _ACCEPTED and _REJECTED
			//break;

		case Packet._MY_ROOMS:
			if(((ArrayList<Room>) packet.getData()).size() == 0); // remove semicolon when implementing
				// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESULT, STR.NOTI_CONTENT_NO_RESULT);
			// else mainframe.updateMyRooms((ArrayList<Room>) packet.getData());
			break;

		case Packet._CREATE_ROOM:
			// mainframe.dialogue(0, STR.NOTI_TITLE_CREATE_ROOM, STR.NOTI_CONTENT_CREATE_ROOM);
			// mainframe.updateMyRooms((ArrayList<Room>) packet.getData());
			break;

		case Packet._EDIT_ROOM:
			// mainframe.dialogue(0, STR.NOTI_TITLE_EDIT_ROOM, STR.NOTI_CONTENT_EDIT_ROOM);
			// mainframe.updateMyRooms((ArrayList<Room>) packet.getData());
			break;

		//case Packet._REMOVE_ROOM:
			// Response with _ACCEPTED and _REJECTED
			//break;

		case Packet._MY_RSRVS:
			if(((ArrayList<Room>) packet.getData()).size() == 0); // remove semicolon when implementing
				// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESULT, STR.NOTI_CONTENT_NO_RESULT);
			// else mainframe.updateMyReservations((ArrayList<Room>) packet.getData());
			break;

		case Packet._RESERVE:
			// mainframe.dialogue(0, STR.NOTI_TITLE_RESERVE, STR.NOTI_CONTENT_RESERVE);
			// mainframe.updateMyReservations((ArrayList<Room>) packet.getData()[0]);
			// mainframe.updateCalendar((ArrayList<Room>) packet.getData()[1]);
			break;

		case Packet._REQ_CANCEL_RSRV:
			// mainframe.dialogue(0, STR.NOTI_TITLE_REQ_CANCEL_RSRV, STR.NOTI_REQ_CANCEL_RSRV);
			// mainframe.updateMyReservations((ArrayList<Room>) packet.getData()[0]);
			// mainframe.updateCalendar((ArrayList<Room>) packet.getData()[1]);
			break;

		case Packet._OPEN_RSRV:
			// mainframe.updateCalendar((ArrayList<Room>) packet.getData());
			break;

		case Packet._CLOSE_RSRV:
			// mainframe.updateCalendar((ArrayList<Room>) packet.getData());
			break;

		case Packet._CANCEL_RSRV:
			// mainframe.updateCalendar((ArrayList<Room>) packet.getData());
			break;

		case Packet._QUERY_REGS:
			if(((ArrayList<Account>) packet.getData()).size() == 0); // remove semicolon when implementing
				// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESULT, STR.NOTI_CONTENT_NO_RESULT);
			// else mainframe.updateRegistrationTable((ArrayList<Room>) packet.getData());
			break;

		case Packet._ACCEPT_REG:
			// mainframe.dialogue(0, STR.NOTI_ACCEPT_REG, STR.NOTI_ACCEPT_REG);
			// mainframe.updateRegistrationTable((ArrayList<Room>) packet.getData());
			break;

		case Packet._REJECT_REG:
			// mainframe.dialogue(0, STR.NOTI_REJECT_REG, STR.NOTI_REJECT_REG);
			// mainframe.updateRegistrationTable((ArrayList<Room>) packet.getData());
			break;

		case Packet._SEARCH_PRI:
			// mainframe.updateRoomTable((ArrayList<Room>) packet.getData());
			break;

		case Packet._SEARCH_SEC:
			if(((ArrayList<Room>) packet.getData()).size() == 0); // remove semicolon when implementing
				// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESULT, STR.NOTI_CONTENT_NO_RESULT);
			// else mainframe.updateRoomTable((ArrayList<Room>) packet.getData());
			break;

		case Packet._QUERY_RSRVS:
			if(((ArrayList<Reservation>) packet.getData()).size() == 0); // remove semicolon when implementing
				// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESERVATION, STR.NOTI_CONTENT_NO_RESERVATION);
			// else mainframe.updateCalendar((ArrayList<Room>) packet.getData()[1]);
			break;
			
		}
		
		// mainframe.unblock(); // Unblock UI that is blocked not to get input.
	}
}
