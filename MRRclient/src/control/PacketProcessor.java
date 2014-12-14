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
	
	private int context;

	public void process(Packet packet)
	{
        String msg = "";
        
		if(packet.getFlag() == Packet._ACCEPTED)
			switch(context)
			{
			case Packet.LOGIN:
                LoginFrame.getInstance().setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
                LoginFrame.getInstance().dispose();
                MainFrame.setAccessType((int)packet.getData());
                MainFrame.getInstance().setVisible(true);
                break;

			case Packet.MY_ACNT:
				MainFrame.getInstance().updateAccountSection((Account) packet.getData());
                MainFrame.getInstance().unfreeze();
				break;

			case Packet.REGISTER:
                RegisterFrame.getInstance().dispose();
                LoginFrame.getInstance().setEnabled(true);
                LoginFrame.getInstance().showDialog(STR.NOTI_ACCEPTED);
                break;

			case Packet.EDIT_ACNT:
                MainFrame.getInstance().unfreeze();
                MainFrame.getInstance().showDialog(STR.NOTI_ACCEPTED);
				break;

			case Packet.MY_ROOMS:
                MainFrame.getInstance().updateMyRoomlist((ArrayList<Room>)packet.getData());
                MainFrame.getInstance().unfreeze();
				if(((ArrayList<Room>) packet.getData()).isEmpty())
                        MainFrame.getInstance().showDialog(STR.NOTI_NO_RESULT);
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
                MainFrame.getInstance().updateMyReservationlist((ArrayList<Reservation>)packet.getData());
                MainFrame.getInstance().unfreeze();
				if(((ArrayList<Reservation>) packet.getData()).isEmpty())
                        MainFrame.getInstance().showDialog(STR.NOTI_NO_RESULT);
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
				if(((ArrayList<Reservation>) packet.getData()).isEmpty()); // remove semicolon when implementing
					// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESERVATION, STR.NOTI_CONTENT_NO_RESERVATION);
				// mainframe.updateCalendar((ArrayList<Room>) packet.getData()[1]);
				break;

			case Packet.QUERY_REGS:
                MainFrame.getInstance().updateRegList((ArrayList<Account>) packet.getData());
                MainFrame.getInstance().updateRegSection(null);
                MainFrame.getInstance().unfreeze();
				if(((ArrayList<Account>) packet.getData()).isEmpty())
                    MainFrame.getInstance().showDialog(STR.NOTI_NO_RESULT);
				break;

			case Packet.ACCEPT_REG:
			case Packet.REJECT_REG:
                MainFrame.getInstance().updateRegList((ArrayList<Account>)packet.getData());
                MainFrame.getInstance().updateRegSection(null);
                MainFrame.getInstance().unfreeze();
				MainFrame.getInstance().showDialog(STR.NOTI_ACCEPTED);
				break;
			}
		
		if(packet.getFlag() == Packet._REJECTED)
			switch(context)
			{
			case Packet.REGISTER:
                RegisterFrame.getInstance().setEnabled(true);
                if((int)packet.getData() == 1) msg = STR.NOTI_DUP_ACCLIST;
                else if((int)packet.getData() == 2) msg = STR.NOTI_DUP_REGLIST;
                else if((int)packet.getData() == 3) msg = STR.NOTI_INVALID_FORM;
                LoginFrame.getInstance().showDialog(msg);
                break;

			case Packet.EDIT_ACNT:
                MainFrame.getInstance().unfreeze();
                if((int)packet.getData() == 1) msg = STR.NOTI_INVALID_FORM;
                else if((int)packet.getData() == 1) msg = STR.NOTI_NOT_REGISTERED_ACC;
                MainFrame.getInstance().showDialog(msg);
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
				if(((ArrayList<Reservation>) packet.getData()).isEmpty()); // remove semicolon when implementing
					// mainframe.dialogue(1, STR.NOTI_TITLE_NO_RESERVATION, STR.NOTI_CONTENT_NO_RESERVATION);
				// mainframe.updateCalendar((ArrayList<Room>) packet.getData()[1]);
				break;

			case Packet.ACCEPT_REG:
			case Packet.REJECT_REG:
                MainFrame.getInstance().updateRegList((ArrayList<Account>)packet.getData());
                MainFrame.getInstance().updateRegSection(null);
                MainFrame.getInstance().unfreeze();
				MainFrame.getInstance().showDialog(STR.NOTI_REJECTED);
				break;
			}
        
		switch(packet.getFlag())
		{
		case Packet._INVALID_ACNT:
            if(context == Packet.LOGIN)
            {
                LoginFrame.getInstance().setEnabled(true);
                LoginFrame.getInstance().showDialog(STR.NOTI_INVALID_ACNT);
            }
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
			MainFrame.getInstance().updateResultTable((ArrayList<Room>) packet.getData());
            MainFrame.getInstance().unfreeze();
			break;

		case Packet._SEARCH_SEC:
			MainFrame.getInstance().updateResultTable((ArrayList<Room>) packet.getData());
            MainFrame.getInstance().unfreeze();
			if(((ArrayList<Room>) packet.getData()).isEmpty())
                MainFrame.getInstance().showDialog(STR.NOTI_NO_RESULT);
            else MainFrame.getInstance().showDialog(STR.NOTI_SEC_RESULT);
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
