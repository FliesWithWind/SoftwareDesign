package ui;

// Define Strings here and use for easier maintenance.
// Then you can also implements multi-locale just with modifying these strings.

public class STR
{
	public static final String
	CURRENT_VERSION	= "0.5",

    CONFIRM_TITLE           = "Are you sure?",
    CONFIRM_CONTENT         = "Are you sure with the request to submit?",
    INPUT_RENTCOST          = "Please input rentcost",
            
	/***************************************************************************/
    
	NOTI_ACCEPTED           = "The request was submitted successfully.",
    NOTI_REJECTED           = "The request was rejected by the system.",
            
	NOTI_CONNECTION_ERROR	= "Connection Error",
    
    NOTI_INVALID_FORM       = "Written form is invalid. Please check again.",       
    NOTI_SHORT_IDPW         = "Lengths of ID and PW have to be longer than 4 at least.",
    
	NOTI_INVALID_ACNT		= "The account is not registered or submitted information is wrong.",
    
    NOTI_DUP_ACCLIST        = "There is the same ID already registered.",
    NOTI_DUP_REGLIST        = "There is the same ID requested for registration.",
    NOTI_DUP_ROOMNAME       = "There is the same Room Name already registered.",
    NOTI_DUP_RSRV           = "There is a Reservation already registered in the date.",
            
	NOTI_NO_RESULT			= "There is no result for the query.",
    NOTI_SEC_RESULT         = "There is no result for the query.\nReceived alternative suggestions.",
    
    NOTI_NOT_REGISTERED_ACC = "Cannot find the account.",
    NOTI_NOT_REGISTERED_ROOM= "Cannot find the room.",
    NOTI_NOT_REGISTERED_RSRV= "Cannot find the reservation.",
            
    NOTI_RESERVED_ROOM      = "The room had been reserved.",
    NOTI_RESERVED_RSRV      = "The room had been reserved by an other.",
            
    NOTI_PASSED_DATE_ROOM   = "Can not open room to be lent at passed date.",
    NOTI_PASSED_DATE_RSRV   = "Can not reserve room at passed date.",
    NOTI_PASSED_DATE_REQCANCEL = "Can not request for cancel reservation at passed date.",
    NOTI_PASSED_DATE_CLOSE = "Can not close reservation at passed date.",
    NOTI_PASSED_DATE_CANCEL = "Can not cancel reservation at passed date.",
    
    NOTI_NOT_SELECTED       = "Please select an item.",
    NOTI_INPUT_POSITIVE     = "Please input positive value.",
            
    LABEL_STATE_IDLE        = "Idle State",
    LABEL_STATE_BUSY        = "Processing...",
            
	NOTI_INVALID_ACCESS		= "You are not authorized for the request.",
            
	NOTI_OUTDATED_VERSION	= "Please update application.\n" + 
							  "The newest version is ",
            
	NOTI_UNKNOWN			= "Server received unknown message.",
    
    NOTI_FILL_THE_FORM      = "Please fill the all forms.";
}
