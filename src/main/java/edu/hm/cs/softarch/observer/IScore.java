package edu.hm.cs.softarch.observer;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IScore {
	
	public void incrementHomeScore();
	
	public void incrementGuestScore();
	
	public void undo();
	
	public int getHomeScore();
	
	public int getGuestScore();
	
	public Logger getLogger();	
	
	public void registerObserver(Observer display);
	
	public void deRegisterObserver(Observer display);
}
