package ui;

// Define Strings here and use for easier maintenance.
// Then you can also implements multi-locale just with modifying these strings.

public class STR
{
	public static final String
	CURRENT_VERSION	= "0.5",

    CONFIRM_TITLE           = "Are you sure?",
    CONFIRM_CONTENT         = "Are you sure with the request to submit?",
    
	/***************************************************************************/
    
	NOTI_ACCEPTED           = "The request was submitted successfully.",
    NOTI_REJECTED           = "The request was rejected by the system.",
            
	NOTI_CONNECTION_ERROR	= "Connection Error",
    
    NOTI_INVALID_FORM       = "Written form is invalid. Please check again.",       
    NOTI_SHORT_IDPW         = "Lengths of ID and PW have to be longer than 4 at least.",
    
	NOTI_INVALID_ACNT		= "The account is not registered or submitted information is wrong.",
    
    NOTI_DUP_ACCLIST        = "There is the same ID already registered.",
    NOTI_DUP_REGLIST        = "There is the same ID requested for registration.",
    
	NOTI_NO_RESULT			= "There is no result for the query.",
    NOTI_SEC_RESULT         = "There is no result for the query.\nReceived alternative suggestions.",
    
    NOTI_NOT_REGISTERED_ACC = "The account is not registered.",
            
    NOTI_NOT_SELECTED       = "Please select an item.",
            
    LABEL_STATE_IDLE        = "Idle State",
    LABEL_STATE_BUSY        = "Processing...",
            
	/***************************************************************************/
	
	
			
	NOTI_TITLE_INVALID_ACCESS		= "Invalid Access",
	NOTI_CONTENT_INVALID_ACCESS		= "You are not authorized for the request.",
			
	NOTI_TITLE_REJECTED				= "Rejected",
	
	NOTI_TITLE_UNKNOWN				= "Unknown Massage",
	NOTI_CONTENT_UNKNOWN			= "Server received unknown message.",
			
	NOTI_TITLE_OUTDATED_VERSION		= "Outdated version",
	NOTI_CONTENT_OUTDATED_VERSION	= "Please update application." + 
									  "The newest version is ",
										
	NOTI_TITLE_CREATE_ROOM			= "Room Created",
	NOTI_CONTENT_CREATE_ROOM		= "The new room was created successfully.",
	
	NOTI_TITLE_RESERVE				= "Reserved",
	NOTI_CONTENT_RESERVE			= "The room was reserved successfully.",
	
	NOTI_TITLE_REQ_CANCEL_RSRV		= "Submitted",
	NOTI_CONTENT_REQ_CANCEL_RSRV	= "The cancel request was submitted successfully.",
			
	NOTI_TITLE_ACCEPT_REG			= "Accepted",
	NOTI_CONTENT_ACCEPT_REG			= "The accepting request was submitted successfully.",
					
	NOTI_TITLE_REJECT_REG			= "Accepted",
	NOTI_CONTENT_REJECT_REG			= "The rejecting request was submitted successfully.",
			
	NOTI_TITLE_NO_RESERVATION		= "No Record",
	NOTI_CONTENT_NO_RESERVATION		= "There is no record on the room.";
}
