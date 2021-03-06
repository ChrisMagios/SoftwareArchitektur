package edu.hm.cs.softarch.observer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Klasse zum Zählen eines Ergebnisses im Hand- oder Fußball.
 * 
 * @author katz.bastian
 */
public class Score extends Observable implements IScore {

	private static final Logger LOG = LoggerFactory.getLogger(Score.class);

	// Torzähler der Heimmannschaft
	private int homeScore = 0;

	// Torzähler der Gastmannschaft
	private int guestScore = 0;

	// Speicher der vergangenen Spielstände für Undo-Funktion
	private Deque<Pair<Integer, Integer>> history = new ArrayDeque<>();

	/** Erhöht den Torzähler der Heimmannschaft */
	public void incrementHomeScore() {
		setChanged();
		saveState();
		homeScore++;
		notifyObservers(homeScore);		
		
		LOG.debug("Home score set to {}, now at {}:{}.", homeScore, homeScore, guestScore);
	}

	/** Erhöht den Torzähler der Gastmannschaft */
	public void incrementGuestScore() {
		setChanged();
		saveState();
		guestScore++;
		notifyObservers(guestScore);
		
		LOG.debug("Guest score set to {}, now at {}:{}.", guestScore, homeScore, guestScore);
	}

	/**
	 * @return ob eine Undo-Funktion zur Verfügung steht
	 */
	public boolean canUndo() {
		return !history.isEmpty();
	}

	/** Hilfsmethode zur Speicherung eines Spielstandes (Undo-Funktion) */
	private void saveState() {
		history.push(new ImmutablePair<Integer, Integer>(homeScore, guestScore));
	}

	/** Setzt den Spielstand auf den vorherigen Wert zurück. */
	public void undo() {
		if (canUndo()) {
			Pair<Integer, Integer> previousState = history.pop();
			homeScore = previousState.getLeft();
			guestScore = previousState.getRight();
		}
		setChanged();
		notifyObservers(homeScore);
		notifyObservers(guestScore);
		LOG.debug("Score reset to {}:{}.", homeScore, guestScore);
	}

	/** @return Torzähler der Heimmannschaft */
	public int getHomeScore() {
		return homeScore;
	}

	/** @return Torzähler der Gastmannschaft */
	public int getGuestScore() {
		return guestScore;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return this.LOG;
	}

	@Override
	public void registerObserver(Observer display) {
		this.addObserver(display);
	}

	@Override
	public void deRegisterObserver(Observer display) {
		this.deleteObserver(display);
	}

}
