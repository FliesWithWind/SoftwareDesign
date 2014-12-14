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
			case Packet.EDIT_ROOM:
                MainFrame.getInstance().updateMyRoomlist((ArrayList<Room>)packet.getData());
                RoomFrame.getInstance().dispose();
				break;

			case Packet.REMOVE_ROOM:
                MainFrame.getInstance().updateRoomInf(null);
                MainFrame.getInstance().updateRsrvInf(-1, true, true);
                MainFrame.getInstance().getCalendar().setReservations(null);
                MainFrame.getInstance().updateCalendar(-1, -1);
                MainFrame.getInstance().updateMyRoomlist((ArrayList<Room>)packet.getData());
                MainFrame.getInstance().unfreeze();
				break;

			case Packet.MY_RSRVS:
                MainFrame.getInstance().updateMyReservationlist((ArrayList<Reservation>)packet.getData());
                MainFrame.getInstance().unfreeze();
				if(((ArrayList<Reservation>) packet.getData()).isEmpty())
                        MainFrame.getInstance().showDialog(STR.NOTI_NO_RESULT);
				break;

			case Packet.RESERVE:
			case Packet.REQ_CANCEL_RSRV:
			case Packet.OPEN_RSRV:
			case Packet.CLOSE_RSRV:
			case Packet.CANCEL_RSRV:
                ArrayList<Reservation> templist = ((Room)packet.getData()).getReservations();
                MainFrame.getInstance().updateRoomInf((Room)packet.getData());
                MainFrame.getInstance().getCalendar().setReservations(templist);
                MainFrame.getInstance().updateCalendar(-1, -1);
                MainFrame.getInstance().unfreeze();
				MainFrame.getInstance().showDialog(STR.NOTI_ACCEPTED);
				return;


			case Packet.QUERY_RSRVS:
                ArrayList<Reservation> temprsrv = ((Room)packet.getData()).getReservations();
                MainFrame.getInstance().updateRoomInf((Room)packet.getData());
                MainFrame.getInstance().getCalendar().setReservations(temprsrv);
                MainFrame.getInstance().updateCalendar(-1, -1);
                MainFrame.getInstance().unfreeze();
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
                else if((int)packet.getData() == 2) msg = STR.NOTI_NOT_REGISTERED_ACC;
                MainFrame.getInstance().showDialog(msg);
				break;

			case Packet.CREATE_ROOM:
                RoomFrame.getInstance().setEnabled(true);
                if((int)packet.getData() == 1) msg = STR.NOTI_DUP_ROOMNAME;
                else if((int)packet.getData() == 2) msg = STR.NOTI_INVALID_FORM;
                RoomFrame.getInstance().showDialog(msg);
				break;
				
			case Packet.EDIT_ROOM:
                RoomFrame.getInstance().setEnabled(true);
                if((int)packet.getData() == 1) msg = STR.NOTI_NOT_REGISTERED_ROOM;
                else if((int)packet.getData() == 2) msg = STR.NOTI_INVALID_FORM;
                RoomFrame.getInstance().showDialog(msg);
				return;

			case Packet.REMOVE_ROOM:
                MainFrame.getInstance().unfreeze();
                if((int)packet.getData() == 1) msg = STR.NOTI_NOT_REGISTERED_ROOM;
                else if((int)packet.getData() == 2) msg = STR.NOTI_RESERVED_ROOM;
                MainFrame.getInstance().showDialog(msg);
				break;

			case Packet.RESERVE:
                MainFrame.getInstance().unfreeze();
                if((int)packet.getData() == 1) msg = STR.NOTI_DUP_RSRV;
                else if((int)packet.getData() == 2) msg = STR.NOTI_PASSED_DATE_RSRV;
                else if((int)packet.getData() == 3) msg = STR.NOTI_RESERVED_RSRV;
                MainFrame.getInstance().showDialog(msg);
				return;

			case Packet.REQ_CANCEL_RSRV:
                MainFrame.getInstance().unfreeze();
                if((int)packet.getData() == 1) msg = STR.NOTI_NOT_REGISTERED_RSRV;
                else if((int)packet.getData() == 2) msg = STR.NOTI_PASSED_DATE_REQCANCEL;
                MainFrame.getInstance().showDialog(msg);
				break;

			case Packet.OPEN_RSRV:
                MainFrame.getInstance().unfreeze();
                if((int)packet.getData() == 1) msg = STR.NOTI_DUP_RSRV;
                else if((int)packet.getData() == 2) msg = STR.NOTI_PASSED_DATE_ROOM;
                else if((int)packet.getData() == 3) msg = STR.NOTI_INVALID_FORM;
                MainFrame.getInstance().showDialog(msg);
				break;
				
			case Packet.CLOSE_RSRV:
                MainFrame.getInstance().unfreeze();
                if((int)packet.getData() == 1) msg = STR.NOTI_NOT_REGISTERED_ROOM;
                else if((int)packet.getData() == 2) msg = STR.NOTI_PASSED_DATE_CLOSE;
                else if((int)packet.getData() == 3) msg = STR.NOTI_INVALID_FORM;
                MainFrame.getInstance().showDialog(msg);
				break;

			case Packet.CANCEL_RSRV:
                MainFrame.getInstance().unfreeze();
                if((int)packet.getData() == 1) msg = STR.NOTI_NOT_REGISTERED_ROOM;
                else if((int)packet.getData() == 2) msg = STR.NOTI_PASSED_DATE_CANCEL;
                else if((int)packet.getData() == 3) msg = STR.NOTI_INVALID_FORM;
                MainFrame.getInstance().showDialog(msg);
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
            else
            {
                MainFrame.getInstance().unfreeze();
                MainFrame.getInstance().showDialog(STR.NOTI_INVALID_ACNT);
            }
			break;

		case Packet._INVALID_ACCESS:
            MainFrame.getInstance().unfreeze();
            MainFrame.getInstance().showDialog(STR.NOTI_INVALID_ACCESS);
			break;

		case Packet._OUTDATED_VERSION:
            LoginFrame.getInstance().setEnabled(true);
			LoginFrame.getInstance().showDialog(STR.NOTI_OUTDATED_VERSION + 
                                                (String)packet.getData());
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
            
		case Packet._UNKNOWN:
            MainFrame.getInstance().unfreeze();
            MainFrame.getInstance().showDialog(STR.NOTI_UNKNOWN);
		}
		
	}
	
	public void setContext(int context)
	{
		this.context = context;
	}
}
