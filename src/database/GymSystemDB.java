package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import main.java.room.Room;


public class GymSystemDB implements IGymSystem{
	
	protected Connection connection;
	protected Statement statement;
	protected ResultSet rs = null;
	
	private static GymSystemDB DB;
	
	public static GymSystemDB GetInstance() {
		
		if (DB == null) {  // First check
			 
            synchronized (GymSystemDB.class) {
            	
                if (DB == null) {  
                	DB = new GymSystemDB();
               }
          }
      }
		return DB;
	}
	

	public GymSystemDB() {
		
		String url = "jdbc:postgresql://localhost/gym_management";
		String user = "buidanhtung";
		String password = "Tung001202033042";
		
		try {
			
			this.connection = DriverManager.getConnection(url, user, password);
			this.statement = this.connection.createStatement();
			
		} catch (SQLException e) {
	        	
	         System.out.println(e.getMessage());
	    }
	}


	@Override
	public Room getRoomInfomation(String roomId) {
		
		 String query = "SELECT * FROM room WHERE room_id = '" + roomId + "';";

		    try {	    	
		    	ResultSet rs = this.statement.executeQuery(query);
		    	
		    	 while (rs.next()) {
		    		 
			    	 int roomID = rs.getInt("room_id");
			    	 String roomName = rs.getString("room_name");
			    	 int employeeInChargeId = rs.getInt("employee_in_charge_id");
			    	 
			    	 return new Room(roomID, roomName, employeeInChargeId);
			      }
		    	 
		    	 return null;
		    	
		    } catch (SQLException e) {
	        	
		         System.out.println(e.getMessage());
		         
		    }
		    
		    return null;
	}


	@Override
	public List<Room> getAllRoom() {
		
		String query = "SELECT * FROM room WHERE room_id = '" + roomId + "';";

	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
		    	 int roomID = rs.getInt("room_id");
		    	 String roomName = rs.getString("room_name");
		    	 int employeeInChargeId = rs.getInt("employee_in_charge_id");
		    	 
		    	 return new Room(roomID, roomName, employeeInChargeId);
		      }
	    	 
	    	 return null;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}
}
