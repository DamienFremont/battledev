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
	List<ProductModel> products = new ArrayList<>();

	String searchedProductRequest;
	int lowestPriceResponse;
}

class ProductModel {
	String name;
	int price;
}

class Bot {

	static Model solve(Model m) {
		Log.debug("SOLVE =======================");

		m.lowestPriceResponse = -1;
		for (ProductModel p : m.products) {
			if (m.searchedProductRequest.equals(p.name)) {
				if (m.lowestPriceResponse == -1)
					m.lowestPriceResponse = p.price;
				if (m.lowestPriceResponse > p.price)
					m.lowestPriceResponse = p.price;
			}
		}
		return m;
	}

}

class Game {

	static Model init(Scanner in) {
		Log.debug("INIT =======================");
		Model m = new Model();

		int size = in.nextInt();
		Log.debug("%d", size);

		m.searchedProductRequest = in.next();
		Log.debug("%s", m.searchedProductRequest);

		for (int i = 0; i < size; i++) {
			String n = in.next();
			int p = in.nextInt();
			Log.debug("%s %s", n, p);
			ProductModel pp = new ProductModel();
			pp.name = n;
			pp.price = p;
			m.products.add(pp);
		}
		return m;
	}

	static void play(Model m) {
		Log.debug("PLAY =======================");
		Log.debug("SOLUTION %d", m.lowestPriceResponse);
		System.out.println(m.lowestPriceResponse);
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
