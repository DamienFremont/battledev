/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package com.isograd.exercise;

import java.util.*;

public class IsoContest {
	public static void main(String[] argv) throws Exception {
		Env.read(argv);
		Scanner in = new Scanner(System.in);
		Model model = Game.init(in);
		model = Bot.solve(model);
		Game.play(model);
	}
}

class Model {
	List<Integer> p1Deck, p2Deck;
	int p1 = 0, p2 = 0;

	List<String>[] winPack;

	String winner;
	int round;
}

class Bot {

	static Model solve(Model m) {
		Log.debug("SOLVE =======================");
		m.round = 0;
		List<Integer> p1Deck = m.p1Deck;
		List<Integer> p2Deck = m.p2Deck;
		turn(m);
		while (!m.p1Deck.isEmpty() && !m.p2Deck.isEmpty()) {
			int win = fight(p1Deck, p2Deck);
			if (win == -1)
				m.p2++;
			if (win == 1)
				m.p1++;
			Log.debug("  Player A=%d B=%d", m.p1, m.p2);
		}
		String winner = (m.p1 > m.p2 ? "A" : "B");
		m.winner = winner;
		m.round--;
		Log.debug("Winner : Player %s", m.winner);
		return m;
	}

	private static void turn(Model m) {
		m.round++;
		Log.debug("Game turn %d", m.round);
		Log.debug("  Player 1 : %s", m.p1Deck);
		Log.debug("  Player 2 : %s", m.p2Deck);
	}

	private static int fight(List<Integer> p1Deck, List<Integer> p2Deck) {
		int p1Card = removeTopCardFrom(p1Deck);
		int p2Card = removeTopCardFrom(p2Deck);
		int fight = Integer.compare(p1Card, p2Card);
		Log.debug("    Turn : the fight %s,%s : %d", p1Card, p2Card, fight);
		return fight;
	}

	private static int removeTopCardFrom(List<Integer> deck) {
		return deck.remove(0);
	}
}

class Game {

	static Model init(Scanner in) {
		Log.debug("INIT =======================");
		Model o = new Model();
		int size = in.nextInt();
		Log.info("%d", size);
		o.p1Deck = new ArrayList<>();
		o.p2Deck = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			String card;
			card = in.next();
			o.p1Deck.add(Integer.valueOf(card));
			card = in.next();
			o.p2Deck.add(Integer.valueOf(card));
			Log.info("%s", card);
			Log.info("%s", card);
		}
		return o;
	}

	static void play(Model m) {
		Log.debug("PLAY =======================");
		Log.debug("SOLUTION %s", m.winner);
		System.out.println(m.winner);
	}
}

// UTILS

class Log {
	static boolean Level_DEBUG;

	static void info(String pattern, Object... values) {
		log(pattern, values);
	}

	static void debug(String pattern, Object... values) {
		if (!Level_DEBUG)
			return;
		log(pattern, values);
	}

	private static void log(String pattern, Object... values) {
		System.err.println(String.format(pattern, values));
	}
}

class Env {

	static void read(String args[]) {
		Log.Level_DEBUG = false;
		if (args == null)
			return;
		for (String arg : args) {
			if ("-debug".equals(arg))
				Log.Level_DEBUG = true;
		}
	}
}
