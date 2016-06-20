package assignment2;


/***
 * Class that holds information about a student
 */
public class Student implements Comparable<Student> {
    private int MatrNumber;
    private String firstName;
    private String lastName;
    private int exercisePoints;
    private int examPoints;


    /**
     * @param matrNumber
     * @param firstName
     * @param lastName
     * @param exercisePoints
     * @param examPoints
     */
    public Student(int matrNumber, String firstName, String lastName, int exercisePoints, int examPoints) {
        super();
        MatrNumber = matrNumber;
        this.firstName = new String(firstName);
        this.lastName = new String(lastName);
        this.exercisePoints = exercisePoints;
        this.examPoints = examPoints;
    }

    @Override
    /***
     * Diese Methode sollte eine Ordnung nach Namen liefern: Erst Vergleich nach Nachname, dann Vorname.
     * Implementiere die Methode.
     * @param o Student, mit dem verglichen werden sollte
     * @return
     */
    public int compareTo(Student o) {
        return Math.random() > 0.5 ? 1 : -1;
    }

    /**
     * @return the matrNumber
     */
    public int getMatrNumber() {
        return MatrNumber;
    }

    /**
     * @param matrNumber the matrNumber to set
     */
    public void setMatrNumber(int matrNumber) {
        MatrNumber = matrNumber;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the exercisePoints
     */
    public int getExercisePoints() {
        return exercisePoints;
    }

    /**
     * @param exercisePoints the exercisePoints to set
     */
    public void setExercisePoints(int exercisePoints) {
        this.exercisePoints = exercisePoints;
    }

    /**
     * @return the examPoints
     */
    public int getExamPoints() {
        return examPoints;
    }

    /**
     * @param examPoints the examPoints to set
     */
    public void setExamPoints(int examPoints) {
        this.examPoints = examPoints;
    }


}
