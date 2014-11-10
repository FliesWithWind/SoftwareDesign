import java.io.Serializable;


public class Room implements Serializable {
	
	private static final long serialVersionUID = -9203864981146323291L;
	String name_;
	String location_;
	int capacity_;
	
	public Room(String name,String location,int cap){
		this.name_=name;
		this.location_=location;
		this.capacity_=cap;
	}

}
