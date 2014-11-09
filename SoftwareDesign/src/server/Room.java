package server;

import client.*;
import common.*;
import java.io.*;

public class Room {
	public String name_;
	public String location_;
	public int capacity_;
	
	public Room(String name,String location,int cap){
		this.name_=name;
		this.location_=location;
		this.capacity_=cap;
	}
	
};
