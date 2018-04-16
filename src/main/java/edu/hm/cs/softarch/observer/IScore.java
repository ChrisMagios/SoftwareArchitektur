package edu.hm.cs.softarch.observer;

public interface IScore {
	
	public void incrementHomeScore();
	
	public void incrementGuestScore();
	
	public void undo();
	
}
