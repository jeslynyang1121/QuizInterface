/**
 * Project 05 -- Student.java
 *
 * A program that creates and stores Student credentials and reads them as needed. Is called in the ProgramServer.java.
 *
 * <p>Purdue University -- CS18000 -- Spring 2022 -- Project 05</p>
 *
 * @author Jeslyn Yang lab sec 05
 *
 * @version April 11, 2022
 *
 *
 */
public class Student extends User {

    public Student(String username, String password) {
        super(username, password, "Student");
    }
}
