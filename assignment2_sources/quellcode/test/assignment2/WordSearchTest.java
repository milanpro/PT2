package assignment2;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertArrayEquals;

public class WordSearchTest {

    @Test
    public void testSmallExample() {
        HashSet<String> words = new HashSet<String>();
        words.add("FoO");
        words.add("bar");
        words.add("BAZ");

        char[][] grid = new char[][]{
                {'B', 'A', 'Z', 'X'},
                {'X', 'X', 'F', 'R'},
                {'X', 'X', 'O', 'A'},
                {'X', 'X', 'O', 'B'}
        };

        char[][] gridSolved = new char[][]{
                {'B', 'A', 'Z', '-'},
                {'-', '-', 'F', 'R'},
                {'-', '-', 'O', 'A'},
                {'-', '-', 'O', 'B'}
        };

        assertArrayEquals(gridSolved, WordSearch.search(words, grid));
    }

    @Test
    public void testAnimalExample() {
        HashSet<String> words = new HashSet<String>();
        words.add("Hund");
        words.add("Katze");
        words.add("Maus");
        words.add("Pferd");
        words.add("Vogel");
        words.add("Fisch");

        char[][] grid = new char[][]{
                {'M', 'K', 'L', 'T', 'Q', 'L', 'P', 'Z', 'K', 'N', 'P', 'X', 'C', 'J', 'J'},
                {'Z', 'L', 'N', 'N', 'S', 'E', 'R', 'R', 'P', 'G', 'F', 'R', 'F', 'P', 'Q'},
                {'Z', 'F', 'W', 'T', 'Q', 'G', 'N', 'S', 'H', 'J', 'T', 'J', 'Z', 'R', 'M'},
                {'K', 'A', 'T', 'Z', 'E', 'O', 'M', 'L', 'C', 'F', 'B', 'V', 'L', 'X', 'Y'},
                {'V', 'F', 'W', 'J', 'J', 'V', 'R', 'Y', 'S', 'S', 'N', 'T', 'H', 'L', 'B'},
                {'B', 'M', 'G', 'P', 'Q', 'D', 'G', 'Q', 'I', 'P', 'N', 'R', 'M', 'N', 'P'},
                {'G', 'V', 'B', 'C', 'K', 'L', 'Z', 'K', 'F', 'G', 'J', 'G', 'J', 'N', 'F'},
                {'J', 'C', 'T', 'T', 'Z', 'L', 'Q', 'T', 'B', 'N', 'M', 'T', 'B', 'R', 'E'},
                {'L', 'J', 'W', 'Q', 'H', 'D', 'N', 'V', 'D', 'B', 'N', 'D', 'F', 'L', 'R'},
                {'X', 'W', 'H', 'N', 'T', 'J', 'M', 'Y', 'F', 'K', 'B', 'G', 'T', 'V', 'D'},
                {'D', 'J', 'K', 'V', 'B', 'S', 'A', 'D', 'J', 'X', 'N', 'V', 'T', 'X', 'T'},
                {'W', 'B', 'F', 'Q', 'G', 'D', 'U', 'R', 'W', 'V', 'D', 'N', 'U', 'H', 'S'},
                {'K', 'F', 'K', 'Y', 'K', 'X', 'S', 'H', 'X', 'Y', 'M', 'R', 'X', 'C', 'K'},
                {'K', 'K', 'M', 'G', 'V', 'J', 'X', 'K', 'D', 'P', 'W', 'T', 'F', 'J', 'R'},
                {'K', 'Y', 'W', 'V', 'R', 'Y', 'L', 'L', 'F', 'P', 'N', 'L', 'N', 'P', 'W'},
        };

        char[][] gridSolved = new char[][]{
                {'-', '-', '-', '-', '-', 'L', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', 'E', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', 'G', '-', '-', 'H', '-', '-', '-', '-', '-', '-'},
                {'K', 'A', 'T', 'Z', 'E', 'O', '-', '-', 'C', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', 'V', '-', '-', 'S', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', 'I', '-', '-', '-', '-', '-', 'P'},
                {'-', '-', '-', '-', '-', '-', '-', '-', 'F', '-', '-', '-', '-', '-', 'F'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', 'E'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', 'R'},
                {'-', '-', '-', '-', '-', '-', 'M', '-', '-', '-', '-', '-', '-', '-', 'D'},
                {'-', '-', '-', '-', '-', '-', 'A', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', 'U', '-', '-', '-', 'D', 'N', 'U', 'H', '-'},
                {'-', '-', '-', '-', '-', '-', 'S', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        };

        assertArrayEquals(gridSolved, WordSearch.search(words, grid));
    }
}
