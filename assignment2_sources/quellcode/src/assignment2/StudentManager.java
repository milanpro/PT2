package assignment2;

import java.util.ArrayList;
import java.util.Collections;

public class StudentManager {

    public static void main(String[] args) {
        System.out.println("Main-Methode.");
    }

    /***
     * Diese Methode pr�ft, ob der gegebene Name (Vor- und Nachname zusammen) in einer Liste von Studenten schon existiert
     *
     * @param lastName  der Nachname
     * @param firstName der Vorname
     * @param students  Liste aller Studenten, deren Namen �berpr�ft werden sollen
     * @return
     */
    //TODO: In dieser Methode hat sich ein Fehler eingeschlichen.
    public static boolean nameExists(String firstName, String lastName, ArrayList<Student> students) {
        for (Student s : students) {
            if (s.getFirstName() == firstName && s.getLastName() == lastName) return true;
        }
        return false;

		/* Falls jemand Interesse hat, Lamda-Funktionen in Java8 auszuprobieren:
         * Der folgende Code erzeugt das funktional gleiche Ergebnis.
		 * Er gibt �brigens noch Optimierungspotential her :)
		 * 
		return 
		students.stream()
		.filter(s -> s.getFirstName() == firstName)
		.filter(s -> s.getLastName() == lastName)
		.count() > 0 ? true : false;
		
		*/

    }

    //TODO implementieren
    public static ArrayList<Student> sortByMatrNumber(ArrayList<Student> students) {
        return students;
    }

    //TODO implementieren
    public static ArrayList<Student> sortByGradeImprovement(ArrayList<Student> students) {
        return students;
    }


    /***
     * Sortiert Studentenobjekte nach Name - zuerst Nachname, dann Vorname
     *
     * @param students Liste an zu sortierenden Studenten
     * @return sortierte Liste
     */
    public static ArrayList<Student> sortByName(ArrayList<Student> students) {
        Collections.sort(students);
        return students;

        /***
         * Hinweis: Collections.sort wird auf den �bergebenen Daten ausgef�hrt. Die �bergebene ArrayListe students
         * wird dadurch also ver�ndert. Dies kann zu Nebeneffekten in anderen Teilen im Code f�hren, der nicht erwartet, dass
         * die Ordnung ver�ndert wurde. Dies kann man mit dem folgenden Code umgehen:
         */
//		ArrayList<Student> copy = new ArrayList<>(students);
//		Collections.sort(copy);
//		return copy;
    }

}
