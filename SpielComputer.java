import static Prog1Tools.IOTools.readChar;

import java.util.*;

/**
 * Variant for the game against computer
 * 
 * @author cacophonix
 * 
 */
public class SpielComputer extends Spiel2 {

	/*
	 * variablle to save to possible moves
	 */
	ArrayList<Map.Entry<Integer, String>> rankedMoves;
	int depth;

	SpielComputer(int n, int f) {
		super(n, f);

		depth = 6;
		// initializing the treeset where the moves will be saved
		rankedMoves = new ArrayList<Map.Entry<Integer, String>>();
	}

	// get which colors are available
	public ArrayList<Character> getAvailableColors(char current, char opponent) {

		ArrayList<Character> colors = new ArrayList<Character>();
		for (char ac : farben) {
			if (steps < sameStep) {

				if (ac != current)
					colors.add(ac);
			} else {

				if (ac != current && ac != opponent)
					colors.add(ac);

			}

		}
		return colors;
	}

	/**
	 * Finds the best option to choose a color, finds which move can get most of
	 * the boxes
	 * 
	 * @param c
	 */
	public char findMost() {

		char current = brett[n - 1][n - 1];
		char opponent = brett[0][0];
		ArrayList<Character> colors = new ArrayList<Character>();
		ArrayList<Integer> sizeArray = new ArrayList<Integer>();
		char[][] brettCopy = new char[n][n];
		HashMap owned2Copy;

		// get available colors
		colors = getAvailableColors(current, opponent);

		int curSize = owned2.size();

		// copying because the values will be changed
		brettCopy = brett.clone();
		owned2Copy = new HashMap(owned2);

		for (char acc : colors) {
			continueTillNew(acc, owned2, false);
			sizeArray.add(owned2.size() - curSize);

			// backs to its original values
			brett = brettCopy.clone();
			owned2 = new HashMap(owned2Copy);

		}

		int max = sizeArray.get(0);
		for (int i : sizeArray) {
			if (i > max)
				max = i;
		}

		int maxIndex = sizeArray.indexOf(max);

		return colors.get(maxIndex);

	}

	/**
	 * Calculates all the moves beforehand. Finds the best moves based on the
	 * depth of the engine
	 * 
	 * @param b
	 * @return
	 */
	public char findMost(boolean b) {
		if (steps >= depth) {
			char a = findMost();
			return a;
		}
		char current = brett[n - 1][n - 1];
		char opponent = brett[0][0];

		ArrayList<Character> colors = new ArrayList<Character>();

		char[][] brettCopy = new char[n][n];
		HashMap owned2Copy;
		int curSize = owned2.size();

		brettCopy = Test.arCopy(brettCopy, brett);
		owned2Copy = new HashMap(owned2);

		// get available colors

		colors = getAvailableColors(current, opponent);

		// colors to remove
		ArrayList<Character> chars = new ArrayList<Character>();
		for (char ac : farben) {
			if (steps < sameStep) {
				chars.add(current);
			} else {
				if (ac == current || ac == opponent)
					chars.add(ac);
			}
		}

		ArrayList<String> moves = new ArrayList<String>();

		// generate ranked moves if on the first step
		if (steps == 0) {

			// generate the possible move combinations
			moves = Test.norepeatCombination(0, depth, farben);

			// iterating over the moves
			int len;
			for (String mov : moves) {
				len = mov.length();
				for (int j = 0; j < len; j++) {
					char c = mov.charAt(j);
					continueTillNew(c, owned2, false);
					ownedChange(c, owned2);

					// backs to its original values

				}

				int size = owned2.size() - curSize;
				rankedMoves.add(new AbstractMap.SimpleEntry<Integer, String>(
						size, mov));

				brett = Test.arCopy(brett, brettCopy);
				owned2 = new HashMap(owned2Copy);
			}// endfor

			String a = "";

			// remove the chars dont needed
			rankedMoves = Test.removeChar(chars, rankedMoves);

			Collections.sort(rankedMoves, new CustomComparator());
			int s = rankedMoves.size();
			char to_return = rankedMoves.get(s - 1).getValue().charAt(0);
			rankedMoves = Test.stripFirstChar(rankedMoves);
			return to_return;

		} // endif steps = 0;
		else {

			rankedMoves = Test.removeChar(chars, rankedMoves);
			Collections.sort(rankedMoves, new CustomComparator());
			int s = rankedMoves.size();
			char to_return = rankedMoves.get(s - 1).getValue().charAt(0);
			rankedMoves = Test.stripFirstChar(rankedMoves);
			return to_return;

		}

	}

	/**
	 * Starts the input
	 */

	public void startInput() {

		showResult();

		while (true) {

			char c = readChar();

			startGame(c);

		}// endof isvalid

	}

	public void startGame(Character c) {

		// only runs if valid c else doesnt do anything
		if (isValid(c)) {
			eingabe = c;
			// if player one is playing

			continueTillNew(c, owned, false);

			// computer is playing

			char ac = findMost();

			continueTillNew(ac, owned2, true);

			steps += 1;

			showResult();
		}// endif

	}
}
