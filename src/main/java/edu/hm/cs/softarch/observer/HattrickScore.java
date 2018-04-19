package edu.hm.cs.softarch.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;

public class HattrickScore extends Observable implements IScore {
	
	private IScore score;
	private int HattrickHome;
	private int HattrickGuest;
	
	public HattrickScore (IScore score) {
		this.score = score;
		//homescore
		HattrickHome = 0;
		
		//Guestscore
		HattrickGuest = 0;
	}
	@Override
	public void incrementHomeScore() {
		score.incrementHomeScore();
		HattrickHome++;
		if(HattrickHome == 3) {
			HattrickHome = 0;
			score.getLogger().debug("HATTRICK");
			
		}
	}

	@Override
	public void incrementGuestScore() {
		score.incrementGuestScore();
		HattrickGuest++;
		if(HattrickGuest == 3) {
			HattrickGuest = 0;
			score.getLogger().debug("HATTRICK");
			
		}

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		score.undo();
	}
	
	@Override
	public int getHomeScore() {
		// TODO Auto-generated method stub
		return score.getHomeScore();
	}
	
	@Override
	public int getGuestScore() {
		// TODO Auto-generated method stub
		return score.getGuestScore();
	}
	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return score.getLogger();
	}
	@Override
	public void registerObserver(Observer display) {
		score.registerObserver(display);
	}
	@Override
	public void deRegisterObserver(Observer display) {
		score.deRegisterObserver(display);
	}
}
