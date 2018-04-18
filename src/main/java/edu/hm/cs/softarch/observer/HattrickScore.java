package edu.hm.cs.softarch.observer;

import org.slf4j.Logger;

public class HattrickScore implements IScore {
	
	private IScore score;
	private int[] hattrickCounter = new int[2];
	
	public HattrickScore (IScore score) {
		this.score = score;
		//homescore
		hattrickCounter[0] = 0;
		
		//Guestscore
		hattrickCounter[1] = 0;
	}
	@Override
	public void incrementHomeScore() {
		score.incrementHomeScore();
		hattrickCounter[0]++;
		if(hattrickCounter[0] == 3) {
			hattrickCounter[0] = 0;
			score.getLogger().debug("HATTRICK");
			
		}
	}

	@Override
	public void incrementGuestScore() {
		score.incrementGuestScore();
		hattrickCounter[1]++;
		if(hattrickCounter[1] == 3) {
			hattrickCounter[1] = 0;
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
	
	

}
