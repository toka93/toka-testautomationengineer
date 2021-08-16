package Models;

import java.util.List;

public class TrellaResponseModel {
	
	public class Address{
	    public int order;
	    public String key;
	    public double latitude;
	    public double longitude;
	    public String name;
	}

	public class Root{
	    public String key;
	    public int numberOfBids;
	    public String commodity;
	    public String vehicleType;
	    public int price;
	    public List<Address> addresses;
	}
	public class base{
		 public List<Root> roots;
	
	}
}
