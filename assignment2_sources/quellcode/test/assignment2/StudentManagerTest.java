package assignment2;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StudentManagerTest {

    /***
     * Generiert einige Studenten
     *
     * @return
     */
    public static ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student(95621, "Jennifer", "K�hler", 25, 30));
        students.add(new Student(75821, "Martin", "Schultheiss", 27, 29));
        students.add(new Student(58122, "Christian", "Schulz", 17, 18));
        students.add(new Student(58125, "Peter", "Decker", 21, 25));


        return students;
    }

    @Test
    public void testNameExists() {
        assertTrue(StudentManager.nameExists("Peter", "Decker", getStudents()));
        assertFalse(StudentManager.nameExists("Frank", "Decker", getStudents()));
    }

    @Test
    public void testSortByName() {
        assertTrue(StudentManager.sortByName(getStudents()).get(0).getMatrNumber() == 58125);
    }

    @Test
    public void testSortByGradeImrovement() {
        assertTrue(StudentManager.sortByGradeImprovement(getStudents()).get(0).getMatrNumber() == 95621);
        assertTrue(StudentManager.sortByGradeImprovement(getStudents()).get(3).getMatrNumber() == 58122);
    }

    @Test
    public void testSortByMatrNumber() {
        assertTrue(StudentManager.sortByMatrNumber(getStudents()).get(0).getMatrNumber() == 58122);
        assertTrue(StudentManager.sortByMatrNumber(getStudents()).get(3).getMatrNumber() == 95621);
    }
}
