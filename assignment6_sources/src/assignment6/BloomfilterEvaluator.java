package assignment6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class BloomfilterEvaluator {

	public static void main(String[] args) {
		ArrayList<String> words = readWords("assignment6_sources\\words.txt");
        ArrayList<String> sortedwords = cloneList(words);
        Collections.sort(sortedwords);

		StringBloomfilter krypt1 = new StringBloomfilter(10,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
		StringBloomfilter krypt2 = new StringBloomfilter(1000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
		StringBloomfilter krypt3 = new StringBloomfilter(100000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
        int[] inserttimes = new int[6];
        int[] containtimes = new int[6];

        System.out.println("Test starting:\n");
        System.out.println("Vorgegebene kryptographische Hashes:");

        inserttimes[0] = addWords(words,krypt1);
        inserttimes[1] = addWords(words,krypt2);
        inserttimes[2] = addWords(words,krypt3);

        containtimes[0] = mightcontainWordstime(words,krypt1);
        containtimes[1] = mightcontainWordstime(words,krypt2);
        containtimes[2] = mightcontainWordstime(words,krypt3);

        krypt1 = new StringBloomfilter(10,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
        krypt2 = new StringBloomfilter(1000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
        krypt3 = new StringBloomfilter(100000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);

        containtimes[3] = mightcontainWordstime(sortedwords,krypt1);
        containtimes[4] = mightcontainWordstime(sortedwords,krypt2);
        containtimes[5] = mightcontainWordstime(sortedwords,krypt3);

        System.out.println("\t\t\t\tSortiert:\t\t\tUnsortiert:");
        System.out.println("Einfügen in ms");
        System.out.println("10            :\t"+inserttimes[3]+"\t\t\t\t\t"+inserttimes[0]);
        System.out.println("1.000         :\t"+inserttimes[4]+"\t\t\t\t\t"+inserttimes[1]);
        System.out.println("100.000       :\t"+inserttimes[5]+"\t\t\t\t\t"+inserttimes[2]);
        System.out.println();
        System.out.println("\t\t\t\tSortiert:\t\t\tUnsortiert:");
        System.out.println("Prüfen in ms");
        System.out.println("10            :\t"+containtimes[3]+"\t\t\t\t\t"+containtimes[0]);
        System.out.println("1.000         :\t"+containtimes[4]+"\t\t\t\t\t"+containtimes[1]);
        System.out.println("100.000       :\t"+containtimes[5]+"\t\t\t\t\t"+containtimes[2]);
        System.out.println();




		System.out.println("I know have " + words.size() + " words. What to do, what to do?");
	}

    public static int addWords(ArrayList<String> words, StringBloomfilter filter){
        long time = System.currentTimeMillis();
        for(String word:words) filter.add(word);
        time = System.currentTimeMillis() - time;
        return (int) time;
    }
	
    public static int mightcontainWordstime(ArrayList<String> words, StringBloomfilter filter){
        long time = System.currentTimeMillis();
        for(String word:words) if(!filter.mightContain(word)) return -1;
        time = System.currentTimeMillis() - time;
        return (int) time;
    }

    public static int[] testfalsepositives(ArrayList<String> words, StringBloomfilter filter){
        int[] gotfpositives = {-1,-1,-1};
        int inarow = 0;
        String[] a_words = words.toArray(new String[words.size()]);
        for(int i = 1; i<=words.size();i++){
            if(filter.mightContain(a_words[i])){
                inarow++;
            }
            if(inarow==1&&gotfpositives[0]==-1) gotfpositives[0]=i;
            else if(inarow==5&&gotfpositives[1]==-1) gotfpositives[1]=i;
            else if(inarow==20){
                gotfpositives[2]=i;
                break;
            }
            filter.add(a_words[i]);
        }
        return gotfpositives;
    }

    public static ArrayList<String> cloneList(ArrayList<String> List) {
        ArrayList<String> clonedList = new ArrayList<String>(List.size());
        for (String elem : List) {
            clonedList.add(elem);
        }
        return clonedList;
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
