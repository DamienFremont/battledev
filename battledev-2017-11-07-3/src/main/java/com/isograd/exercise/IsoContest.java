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
	int n;
	boolean[][] grid;
	boolean[][] cultivables;
	int cultivablesCount;
}

class ProductModel {
	String name;
	int price;
}

class Bot {

	static Model solve(Model m) {
		Log.debug("SOLVE =======================");

		for (int y = 0; y < m.n; y++) {
			for (int x = 0; x < m.n; x++) {
				if (m.grid[y][x]) {

					try {
						if (!m.grid[y + 1][x - 1])
							m.cultivables[y + 1][x - 1] = true;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!m.grid[y + 1][x])
							m.cultivables[y + 1][x] = true;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!m.grid[y + 1][x + 1])
							m.cultivables[y + 1][x + 1] = true;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!m.grid[y][x - 1])
							m.cultivables[y][x - 1] = true;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!m.grid[y][x + 1])
							m.cultivables[y][x + 1] = true;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!m.grid[y - 1][x - 1])
							m.cultivables[y - 1][x - 1] = true;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!m.grid[y - 1][x])
							m.cultivables[y - 1][x] = true;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!m.grid[y - 1][x - 1])
							m.cultivables[y - 1][x - 1] = true;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
		}

		Log.debug("%s", "grid");
		for (int y = 0; y < m.n; y++) {
			String line = "";
			for (int x = 0; x < m.n; x++) {
				line += (m.grid[y][x] ? "X" : ".") + " ";
			}
			Log.debug("%s", line);
		}

		Log.debug("%s", "culti");
		for (int y = 0; y < m.n; y++) {
			String line = "";
			for (int x = 0; x < m.n; x++) {
				line += (m.cultivables[y][x] ? "C" : ".") + " ";
			}
			Log.debug("%s", line);
		}

		for (int y = 0; y < m.n; y++) {
			for (int x = 0; x < m.n; x++) {
				if (m.cultivables[y][x] && !m.grid[y][x]) {
					m.cultivablesCount++;
				}
			}
		}
		
		return m;
	}

}

class Game {

	static Model init(Scanner in) {
		Log.debug("INIT =======================");
		Model m = new Model();

		m.n = in.nextInt();
		Log.debug("%d", m.n);
		m.grid = new boolean[m.n][m.n];
		m.cultivables = new boolean[m.n][m.n];
		m.cultivablesCount = 0;

		for (int y = 0; y < m.n; y++) {
			String line = in.next();
			for (int x = 0; x < m.n; x++) {
				String eva = line.substring(0 + x, 1 + x);
				boolean isVap = "X".equals(eva) ? true : false;
				m.grid[y][x] = isVap;
			}
		}
		return m;
	}

	static void play(Model m) {
		Log.debug("PLAY =======================");
		Log.debug("SOLUTION %d", m.cultivablesCount);
		System.out.println(m.cultivablesCount);
	}
}

// UTILS

class Log {
	static boolean Level_DEBUG;

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
