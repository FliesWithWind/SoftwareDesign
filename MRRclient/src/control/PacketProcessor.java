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
	private int context;
	private PacketBuilder packetbuilder;
	
	private PacketProcessor ()
	{
		packetbuilder = PacketBuilder.getInstance();
		// ***to be implemented***
	}
	
	public void process(Packet packet)
	{
		System.out.println((String)packet.getData()); //  For test
		
		if(packet.getFlag() == Packet._ACCEPTED)
			switch(context)
			{
			case Packet.LOGIN:
				// switch to mainframe
				break;
				
			case Packet.MY_ACNT:
				// mainframe.updateAccountSection((Account) packet.getData());
				break;

			case Packet.REGISTER:
				// mainframe.dialogue(0, STR.NOTI_TITLE_ACCEPTED, STR.NOTI_CONTENT_ACCEPTED);
				break;

			case Packet.EDIT_ACNT:
				// mainframe.dialogue(0, STR.NOTI_TITLE_ACCEPTED, STR.NOTI_CONTENT_ACCEPTED);
				break;

			case Packet.MY_ROOMS:
				if(((ArrayList<Room>) packet.getData()).size() == 0); // remove semicolon when implementing
					// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESULT, STR.NOTI_CONTENT_NO_RESULT);
				// else mainframe.updateMyRooms((ArrayList<Room>) packet.getData());
				break;

			case Packet.CREATE_ROOM:
				// else mainframe.updateMyRooms((ArrayList<Room>) packet.getData());
				// mainframe.dialogue(0, STR.NOTI_TITLE_CREATE_ROOM, STR.NOTI_CONTENT_CREATE_ROOM);
				break;
				
			case Packet.EDIT_ROOM:
				// else mainframe.updateMyRooms((ArrayList<Room>) packet.getData());
				// mainframe.dialogue(0, STR.NOTI_TITLE_EDIT_ROOM, STR.NOTI_CONTENT_EDIT_ROOM);
				return;

			case Packet.REMOVE_ROOM:
				// else mainframe.updateMyRooms((ArrayList<Room>) packet.getData());
				// mainframe.dialogue(0, STR.NOTI_TITLE_ACCEPTED, STR.NOTI_CONTENT_ACCEPTED);
				break;

			case Packet.MY_RSRVS:
				if(((ArrayList<Room>) packet.getData()).size() == 0); // remove semicolon when implementing
					// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESULT, STR.NOTI_CONTENT_NO_RESULT);
				// else mainframe.updateMyReservations((ArrayList<Room>) packet.getData());
				break;

			case Packet.RESERVE:
				// mainframe.updateMyReservations((ArrayList<Reservation>) packet.getData()[0]);
				// mainframe.updateCalendar((ArrayList<Reservation>) packet.getData()[1]);
				// mainframe.dialogue(0, STR.NOTI_TITLE_RESERVE, STR.NOTI_CONTENT_RESERVE);
				return;

			case Packet.REQ_CANCEL_RSRV:
				// mainframe.updateMyReservations((ArrayList<Room>) packet.getData()[0]);
				// mainframe.updateCalendar((ArrayList<Room>) packet.getData()[1]);
				// mainframe.dialogue(0, STR.NOTI_TITLE_REQ_CANCEL_RSRV, STR.NOTI_REQ_CANCEL_RSRV);
				break;

			case Packet.OPEN_RSRV:
				// mainframe.updateCalendar((ArrayList<Room>) packet.getData());
				// mainframe.dialogue(0, STR.NOTI_TITLE_ACCEPTED, STR.NOTI_CONTENT_ACCEPTED);
				break;
				
			case Packet.CLOSE_RSRV:
				// mainframe.updateCalendar((ArrayList<Room>) packet.getData());
				// mainframe.dialogue(0, STR.NOTI_TITLE_ACCEPTED, STR.NOTI_CONTENT_ACCEPTED);
				break;

			case Packet.CANCEL_RSRV:
				// mainframe.updateCalendar((ArrayList<Room>) packet.getData());
				// mainframe.dialogue(0, STR.NOTI_TITLE_ACCEPTED, STR.NOTI_CONTENT_ACCEPTED);
				break;

			case Packet.QUERY_RSRVS:
				if(((ArrayList<Reservation>) packet.getData()).size() == 0); // remove semicolon when implementing
					// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESERVATION, STR.NOTI_CONTENT_NO_RESERVATION);
				// mainframe.updateCalendar((ArrayList<Room>) packet.getData()[1]);
				break;

			case Packet.QUERY_REGS:
				if(((ArrayList<Account>) packet.getData()).size() == 0); // remove semicolon when implementing
					// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESULT, STR.NOTI_CONTENT_NO_RESULT);
				// mainframe.updateRegistrationTable((ArrayList<Room>) packet.getData());
				break;

			case Packet.ACCEPT_REG:
				// mainframe.dialogue(0, STR.NOTI_ACCEPT_REG, STR.NOTI_ACCEPT_REG);
				// mainframe.updateRegistrationTable((ArrayList<Room>) packet.getData());
				break;

			case Packet.REJECT_REG:
				// mainframe.dialogue(0, STR.NOTI_REJECT_REG, STR.NOTI_REJECT_REG);
				// mainframe.updateRegistrationTable((ArrayList<Room>) packet.getData());
				break;
			}
		
		switch(packet.getFlag())
		{
		case Packet._INVALID_ACNT:
			// mainframe.dialogue(1, STR.NOTI_TITLE_INVALID_ACNT, STR.NOTI_CONTENT_INVALID_ACNT);
			break;

		case Packet._INVALID_ACCESS:
			// mainframe.dialogue(2, STR.NOTI_TITLE_INVALID_ACCESS, STR.NOTI_CONTENT_INVALID_ACCESS);
			break;

		case Packet._OUTDATED_VERSION:
			// mainframe.dialogue(1, STR.NOTI_TITLE_OUTDATED_VERSION, 
			//					STR.NOTI_CONTENT_OUTDATED_VERSION + (String)packet.getData());
			break;

		case Packet._SEARCH_PRI:
			// mainframe.updateRoomTable((ArrayList<Room>) packet.getData());
			break;

		case Packet._SEARCH_SEC:
			if(((ArrayList<Room>) packet.getData()).size() == 0); // remove semicolon when implementing
				// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESULT, STR.NOTI_CONTENT_NO_RESULT);
			// else mainframe.updateRoomTable((ArrayList<Room>) packet.getData());
			break;
			
		case Packet._ACCEPTED:
			// mainframe.dialogue(0, STR.NOTI_TITLE_ACCEPTED, STR.NOTI_CONTENT_ACCEPTED);
			break;

		case Packet._REJECTED:
			// mainframe.dialogue(1, STR.NOTI_TITLE_REJECTED, (String) packet.getData());
			break;
			
		case Packet._UNKNOWN:
			// mainframe.dialogue(1, STR.NOTI_TITLE_UNKNOWN, STR.NOTI_CONTENT_UNKNOWN);
			break;
		}
		
		// mainframe.unblock(); // Unblock UI that is blocked not to get input.
	}
	
	public void setContext(int context)
	{
		this.context = context;
	}
}
