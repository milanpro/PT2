package assignment4;

import java.io.*;
import java.util.ArrayList;


/**
 * @author Annika Baldi
 * @author Milan Proell
 */
public class DivideAndConquer {
    public static void main(String[] args) {
        filesort("C:/Users/Milan/Desktop/fruits.txt", "C:/Users/Milan/Desktop/fruitssorted.txt", 4);
    }

    public static void filesort(String inputFile, String outputFile, int maxLineCount) {
        File file = new File(inputFile);
        if (!file.canRead() || !file.isFile())
            System.exit(0); // abfangen von exceptions

        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(inputFile)); // zu lesende datei
            File tempfile; // zu schreibende temp datei(en)
            BufferedWriter tempwriter;
            String zeile = null; //ausgelesene zeilen der input file
            ArrayList<String> zusortieren = new ArrayList<String>(); // Array list für mergesort
            int filecounter = 0; //Anzahl temporärer dateien
            do {
                for (int i = 0; i < maxLineCount / 2 && (zeile = input.readLine()) != null; i++) { // ZEILEN WERDEN GELESEN
                    zusortieren.add(zeile); //auslesen der datei in maxlinecount/2 blöcken (da immer zwei listen im RAM)
                }
                mergesort(zusortieren); // sortieren der ausgelesenen zeilen
                tempfile = new File("./sorttemp" + filecounter + ',' + 0 + ".txt"); //benennung der temp datei
                if (!tempfile.exists()) {
                    tempfile.createNewFile(); //erstellen der datei
                }
                filecounter++;
                tempwriter = new BufferedWriter(new FileWriter(tempfile));
                for (int j = 0; j < zusortieren.size(); j++) { // Zeilen sortiert in temp datei schreiben
                    tempwriter.write(zusortieren.get(j));
                    tempwriter.newLine();
                }
                tempwriter.close();
                zusortieren.clear();
            } while (zeile != null); //solange bis input datei leer

            input.close();
            //Nun müssen nurnoch die tempdateien gemerged werden

            filemerge(filecounter, 0); //rekursives mergen der dateien -> ausgabe in sorttemp.txt

            File file1 = new File("sorttemp.txt"); //verschieben/umbenennen in output datei
            File file2 = new File(outputFile);
            if (file2.exists())
                file2.delete();
            file1.renameTo(file2);


            File path = new File("./"); //löschen aller tempdateien
            for (File files : path.listFiles()) {
                if (files.toString().contains("sorttemp")) {
                    files.delete();
                }
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void filemerge(int filecount, int deepness) { //deepness sowas wie die rekursionstiefe -> nötig für tempdateibenennung
        try {
            if (!(filecount == 1)) { //wenn nurnoch eine datei, dann fertig
                BufferedWriter tempwriter; //writer für gemergtes 1,2
                BufferedReader reader1; //reader 1 zum mergen
                BufferedReader reader2; //reader 2 zum mergen
                File tempfile;
                File readf1; //datei 1 zum mergen
                File readf2; //datei 2 zum mergen
                String line1 = "";
                String line2 = "";
                for (int i = 0; i < filecount; i = i + 2) { //iterieren in zweierschritten über alle dateien
                    readf1 = new File("./sorttemp" + i + ',' + deepness + ".txt");
                    readf2 = new File("./sorttemp" + (i + 1) + ',' + deepness + ".txt");
                    reader1 = new BufferedReader(new FileReader(readf1));
                    reader2 = new BufferedReader(new FileReader(readf2));
                    tempfile = new File("./sorttemp" + (i / 2) + ',' + (deepness + 1) + ".txt");
                    if (!tempfile.exists()) {
                        tempfile.createNewFile();
                    }
                    tempwriter = new BufferedWriter(new FileWriter(tempfile));


                    boolean written1 = true; //boolean um zu wissen, ob nächstes wort geladen werden soll
                    boolean written2 = true;

                    do { //mergen / auslesen der dateien 1,2 und schreiben in neue tempdatei
                        if (line1 != null && written1) {
                            line1 = reader1.readLine();
                            written1 = false;
                        }
                        if (line2 != null && written2) {
                            line2 = reader2.readLine();
                            written2 = false;
                        }
                        if (line1 != null && line2 != null) {
                            if (line1.compareTo(line2) <= 0) {
                                tempwriter.write(line1);
                                tempwriter.newLine();
                                written1 = true;
                            } else {
                                tempwriter.write(line2);
                                tempwriter.newLine();
                                written2 = true;
                            }
                        } else if (line2 != null) {
                            tempwriter.write(line2);
                            tempwriter.newLine();
                            written2 = true;
                        } else if (line1 != null) {
                            tempwriter.write(line1);
                            tempwriter.newLine();
                            written1 = true;
                        }
                    } while (line1 != null || line2 != null); //zuende wenn datei 1,2 leer
                    line1 = "";
                    line2 = "";
                    reader1.close();
                    reader2.close();
                    tempwriter.close();
                }

                int newfilecount = 0;
                if (filecount % 2 == 1) //falls filecount ungerade
                {
                    newfilecount = (filecount / 2) + 1;
                    File alt = new File("./sorttemp" + filecount + ',' + deepness + ".txt");
                    File neu = new File("./sorttemp" + newfilecount + ',' + (deepness + 1) + ".txt");
                    if (neu.exists())
                        neu.delete();
                    alt.renameTo(neu);
                } else newfilecount = filecount / 2;

                filemerge(newfilecount, (deepness + 1)); //rekursiver aufruf in neuer ebene
            } else { //wenn fertig dateiausgabe als sorttemp.txt
                File file = new File("./sorttemp" + 0 + ',' + deepness + ".txt");
                File file2 = new File("sorttemp.txt");
                if (file2.exists())
                    file2.delete();
                file.renameTo(file2);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }


    public static <X extends Comparable<X>> void mergesort(ArrayList<X> list) {
        if (list.size() <= 1) {
        } else {
            ArrayList<X> links = new ArrayList<X>();
            ArrayList<X> rechts = new ArrayList<X>();
            for (int k = 0; k < list.size(); k++) {
                if (k < (list.size() / 2)) {
                    links.add(list.get(k));
                } else rechts.add(list.get(k));
            }

            mergesort(links);
            mergesort(rechts);


            list.clear();
            list.addAll(merge(links, rechts));
        }
    }

    public static <X extends Comparable<X>> ArrayList<X> merge(ArrayList<X> links, ArrayList<X> rechts) {
        ArrayList<X> neu = new ArrayList<X>();
        while (!links.isEmpty() && !rechts.isEmpty()) {
            if (links.get(0).compareTo(rechts.get(0)) <= 0) {
                neu.add(links.get(0));
                links.remove(0);
            } else {
                neu.add(rechts.get(0));
                rechts.remove(0);
            }
        }
        while (!links.isEmpty()) {
            neu.add(links.get(0));
            links.remove(0);
        }
        while (!rechts.isEmpty()) {
            neu.add(rechts.get(0));
            rechts.remove(0);
        }
        return neu;
    }
}

