package assignment6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class BloomfilterEvaluator {

	public static void main(String[] args) {
		ArrayList<String> words = readWords("words.txt");
		System.out.println("I know have " + words.size() + " words. What to do, what to do?");
	}

	


	/***
	 * Reads words from a file in which each line is a word
	 * @param file
	 * @return ArrayList with all words in it
	 */
	public static ArrayList<String> readWords(String file) {
		Path path = Paths.get(file);
		ArrayList<String> words = new ArrayList<>();

		try (Stream<String> lines = Files.lines(path)) {
			lines.forEach(s -> words.add(s));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return words;
	}
}
