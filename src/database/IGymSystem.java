package database;

import java.util.List;

import main.java.room.Room;

public interface IGymSystem {

	Room getRoomInfomation(String roomId);
	List<Room> getAllRoom();
}
