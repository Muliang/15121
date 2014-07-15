/*
 * Do not modify this class except to change which mode the game is played in.
 * You are encouraged to read the rest of the code in this class; you should
 * be able to understand what it does.
 * 
 */

import java.io.*;
import java.util.*;

public class Tester {
	private static BufferedReader reader =
        new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
        System.out.println("choose a mode");
        
        int mode = Integer.parseInt(input());
		
		
		
		
		play(mode);
	}

	public static void play(int mode) {
		HangmanState hangmanState = new HangmanState(load("/Users/violethaze/Desktop/15-121/15121/hw08-hangman/words.txt"));
		while (hangmanState.getFeedbackToUser().contains("-")) {
			System.out.println(hangmanState);
			String letter = input();
			if (letter.length() == 1)
				hangmanState.letterGuessedByUser(letter, mode);
		}
		System.out.println(hangmanState);
	}

	public static Set<String> load(String file) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			Set<String> set = new HashSet<String>();
			while (line != null) {
				set.add(line);
				line = in.readLine();
			}
			in.close();
			return set;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String input() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
