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
		ArrayList<String> words = readWords("/Users/Annika/Documents/HPI/SS 16/PT2/PT2/assignment6_sources/words.txt");
        ArrayList<String> sortedwords = cloneList(words);
        Collections.sort(sortedwords);
        Collections.shuffle(words);

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

        inserttimes[3] = addWords(words,krypt1);
        inserttimes[4] = addWords(words,krypt2);
        inserttimes[5] = addWords(words,krypt3);

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

        krypt1 = new StringBloomfilter(10,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
        krypt2 = new StringBloomfilter(1000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
        krypt3 = new StringBloomfilter(100000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);

        ArrayList<int[]> fppos = new ArrayList<>();
        fppos.add(testfalsepositives(words,krypt1));
        fppos.add(testfalsepositives(words,krypt2));
        fppos.add(testfalsepositives(words,krypt3));

        krypt1 = new StringBloomfilter(10,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
        krypt2 = new StringBloomfilter(1000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
        krypt3 = new StringBloomfilter(100000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);

        fppos.add(testfalsepositives(sortedwords,krypt1));
        fppos.add(testfalsepositives(sortedwords,krypt2));
        fppos.add(testfalsepositives(sortedwords,krypt3));

        System.out.println("false-positives nach x Worten");
        System.out.println("\t\t\t\tSortiert:\t\t\tUnsortiert:");
        System.out.print("f-p in Reihe  :\t");
        System.out.format("%3d %3d %3d", 1, 5, 20);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", 1, 5, 20);
        System.out.println();
        System.out.print("10            :\t");
        System.out.format("%3d %3d %3d", fppos.get(3)[0], fppos.get(3)[1], fppos.get(3)[2]);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", fppos.get(0)[0], fppos.get(0)[1], fppos.get(0)[2]);
        System.out.println();
        System.out.print("1.000         :\t");
        System.out.format("%3d %3d %3d", fppos.get(4)[0], fppos.get(4)[1], fppos.get(4)[2]);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", fppos.get(1)[0], fppos.get(1)[1], fppos.get(1)[2]);
        System.out.println();
        System.out.print("100.000       :\t");
        System.out.format("%3d %3d %3d", fppos.get(5)[0], fppos.get(5)[1], fppos.get(5)[2]);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", fppos.get(2)[0], fppos.get(2)[1], fppos.get(2)[2]);
        System.out.println();
        System.out.println();

        krypt1 = new StringBloomfilter(10,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256, CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt2 = new StringBloomfilter(1000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256, CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt3 = new StringBloomfilter(100000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256, CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        inserttimes = new int[6];
        containtimes = new int[6];

        System.out.println("Test starting:\n");
        System.out.println("Vorgegebene kryptographische Hashes und eigene Hashes:");

        inserttimes[0] = addWords(words,krypt1);
        inserttimes[1] = addWords(words,krypt2);
        inserttimes[2] = addWords(words,krypt3);

        containtimes[0] = mightcontainWordstime(words,krypt1);
        containtimes[1] = mightcontainWordstime(words,krypt2);
        containtimes[2] = mightcontainWordstime(words,krypt3);

        krypt1 = new StringBloomfilter(10,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt2 = new StringBloomfilter(1000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt3 = new StringBloomfilter(100000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);

        inserttimes[3] = addWords(words,krypt1);
        inserttimes[4] = addWords(words,krypt2);
        inserttimes[5] = addWords(words,krypt3);

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

        krypt1 = new StringBloomfilter(10,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256, CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt2 = new StringBloomfilter(1000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256, CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt3 = new StringBloomfilter(100000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256, CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);

        fppos = new ArrayList<>();
        fppos.add(testfalsepositives(words,krypt1));
        fppos.add(testfalsepositives(words,krypt2));
        fppos.add(testfalsepositives(words,krypt3));

        krypt1 = new StringBloomfilter(10,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256, CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt2 = new StringBloomfilter(1000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256, CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt3 = new StringBloomfilter(100000,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256, CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);

        fppos.add(testfalsepositives(sortedwords,krypt1));
        fppos.add(testfalsepositives(sortedwords,krypt2));
        fppos.add(testfalsepositives(sortedwords,krypt3));

        System.out.println("false-positives nach x Worten");
        System.out.println("\t\t\t\tSortiert:\t\t\tUnsortiert:");
        System.out.print("f-p in Reihe  :\t");
        System.out.format("%3d %3d %3d", 1, 5, 20);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", 1, 5, 20);
        System.out.println();
        System.out.print("10            :\t");
        System.out.format("%3d %3d %3d", fppos.get(3)[0], fppos.get(3)[1], fppos.get(3)[2]);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", fppos.get(0)[0], fppos.get(0)[1], fppos.get(0)[2]);
        System.out.println();
        System.out.print("1.000         :\t");
        System.out.format("%3d %3d %3d", fppos.get(4)[0], fppos.get(4)[1], fppos.get(4)[2]);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", fppos.get(1)[0], fppos.get(1)[1], fppos.get(1)[2]);
        System.out.println();
        System.out.print("100.000       :\t");
        System.out.format("%3d %3d %3d", fppos.get(5)[0], fppos.get(5)[1], fppos.get(5)[2]);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", fppos.get(2)[0], fppos.get(2)[1], fppos.get(2)[2]);
        System.out.println();
        System.out.println();
        krypt1 = new StringBloomfilter(10,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt2 = new StringBloomfilter(1000,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt3 = new StringBloomfilter(100000,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        inserttimes = new int[6];
        containtimes = new int[6];

        System.out.println("Test starting:\n");
        System.out.println("eigene Hashes:");

        inserttimes[0] = addWords(words,krypt1);
        inserttimes[1] = addWords(words,krypt2);
        inserttimes[2] = addWords(words,krypt3);

        containtimes[0] = mightcontainWordstime(words,krypt1);
        containtimes[1] = mightcontainWordstime(words,krypt2);
        containtimes[2] = mightcontainWordstime(words,krypt3);

        krypt1 = new StringBloomfilter(10,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt2 = new StringBloomfilter(1000,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt3 = new StringBloomfilter(100000,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);

        inserttimes[3] = addWords(words,krypt1);
        inserttimes[4] = addWords(words,krypt2);
        inserttimes[5] = addWords(words,krypt3);

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

        krypt1 = new StringBloomfilter(10,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt2 = new StringBloomfilter(1000,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt3 = new StringBloomfilter(100000,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);

        fppos = new ArrayList<>();
        fppos.add(testfalsepositives(words,krypt1));
        fppos.add(testfalsepositives(words,krypt2));
        fppos.add(testfalsepositives(words,krypt3));

        krypt1 = new StringBloomfilter(10,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt2 = new StringBloomfilter(1000,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);
        krypt3 = new StringBloomfilter(100000,CustomHashFunction.Var1,CustomHashFunction.Var2,CustomHashFunction.Var3);

        fppos.add(testfalsepositives(sortedwords,krypt1));
        fppos.add(testfalsepositives(sortedwords,krypt2));
        fppos.add(testfalsepositives(sortedwords,krypt3));

        System.out.println("false-positives nach x Worten");
        System.out.println("\t\t\t\tSortiert:\t\t\tUnsortiert:");
        System.out.print("f-p in Reihe  :\t");
        System.out.format("%3d %3d %3d", 1, 5, 20);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", 1, 5, 20);
        System.out.println();
        System.out.print("10            :\t");
        System.out.format("%3d %3d %3d", fppos.get(3)[0], fppos.get(3)[1], fppos.get(3)[2]);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", fppos.get(0)[0], fppos.get(0)[1], fppos.get(0)[2]);
        System.out.println();
        System.out.print("1.000         :\t");
        System.out.format("%3d %3d %3d", fppos.get(4)[0], fppos.get(4)[1], fppos.get(4)[2]);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", fppos.get(1)[0], fppos.get(1)[1], fppos.get(1)[2]);
        System.out.println();
        System.out.print("100.000       :\t");
        System.out.format("%3d %3d %3d", fppos.get(5)[0], fppos.get(5)[1], fppos.get(5)[2]);
        System.out.print("\t\t\t");
        System.out.format("%3d %3d %3d", fppos.get(2)[0], fppos.get(2)[1], fppos.get(2)[2]);
        System.out.println();
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
            if(filter.mightContain(a_words[i-1])){
                inarow++;
            }
            else inarow = 0;
            if(inarow==1&&gotfpositives[0]==-1) gotfpositives[0]=i;
            else if(inarow==5&&gotfpositives[1]==-1) gotfpositives[1]=i;
            else if(inarow==20){
                gotfpositives[2]=i;
                break;
            }
            filter.add(a_words[i-1]);
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
