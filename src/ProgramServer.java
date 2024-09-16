import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Project 05 -- ProgramServer.java
 *
 * This is the thread that the server program should run for each client.
 *
 * <p>Purdue University -- CS18000 -- Spring 2022 -- Project 05</p>
 *
 * @author Akshay Godhani, Bodhi Scott, Jeslyn Yang lab sec 04
 *
 * @version May 01, 2022
 *
 *
 */

public class ProgramServer extends Thread {
    public Socket socket;

    public ProgramServer(Socket socket) {
        this.socket = socket;
    }
    public ProgramServer() {
    }


    public synchronized void fileUpdate(ArrayList<String> arrname, String filename) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < arrname.size(); i++) {
                bw.write(arrname.get(i) + "\n");
            }
            bw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        arrname = new ArrayList<>();
    }

    public synchronized ArrayList<String> fileRead(String filename) {
        ArrayList<String> newArray = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            do {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                newArray.add(line);
            } while (true);
            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return newArray;
    }

    public String sendArray(ArrayList<String> array) {
        String arrayList = "";
        for (int i = 0; i < array.size(); i++) {
            //send each quiz
            arrayList += array.get(i) + ",";
        }
        return arrayList;
    }

    //making thread to run
    @Override
    public void run() {
        ProgramServer programServer = new ProgramServer();
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            //creating new reader and writer
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Reader or Writer could not be created.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        String role = "";
        Teacher teacher = null;
        Student student = null;
        String clientInput;
        String username = "";
        String password = "";
        ArrayList<String> quizzes;
        ArrayList<String> courses;
        ArrayList<String> quizSubmissions;
        boolean isValid;
        do {
            try {
                clientInput = reader.readLine();
                //client signs in as teacher or student
                if (clientInput.equalsIgnoreCase("teacher")) {
                    role = "teacher";
                } else if (clientInput.equalsIgnoreCase("student")) {
                    role = "student";
                }

                //login or signup
                if (clientInput.equalsIgnoreCase("signup")) {
                    do {
                        username = reader.readLine();
                        password = reader.readLine();
                        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                            isValid = false;
                            writer.println("empty");
                        } else {
                            if (role.equalsIgnoreCase("teacher")) {
                                teacher = new Teacher(username, password);
                                isValid = teacher.createCredentials();
                            } else {
                                student = new Student(username, password);
                                isValid = student.createCredentials();
                            }
                            if (isValid) {
                                writer.println("valid");
                            } else {
                                writer.println("not");
                            }
                        }
                        writer.flush();
                    } while (!isValid);
                    writer.println(role);
                    writer.flush();


                } else if (clientInput.equalsIgnoreCase("login")) {
                    do {
                        username = reader.readLine();
                        password = reader.readLine();
                        if (role.equalsIgnoreCase("teacher")) {
                            teacher = new Teacher(username, password);
                            isValid = teacher.validCredentials(username, password);
                        } else {
                            student = new Student(username, password);
                            isValid = student.validCredentials(username, password);
                        }
                        if (isValid) {
                            writer.println("valid");
                        } else {
                            writer.println("not");
                        }
                        writer.flush();
                    } while (!isValid);
                    writer.println(role);
                    writer.flush();
                }


                //main menu choices
                //if teacher is a student
                if (role.equalsIgnoreCase("teacher")) {
                    //create quiz and create file
                    if (clientInput.equalsIgnoreCase("createQuiz")) {
                        //randomization
                        courses = programServer.fileRead("resource/Courses.txt");
                        quizzes = programServer.fileRead("resource/Quizzes.txt");
                        quizSubmissions = programServer.fileRead("resource/QuizSubmissions.txt");
                        if (courses.size() == 0) {
                            writer.println("noCourses");
                            writer.flush();
                        } else {
                            writer.println("Courses");
                            writer.flush();
                            boolean rando = false;
                            String random = reader.readLine();
                            if (random.equalsIgnoreCase("random")) {
                                rando = true;
                            }
                            String file = reader.readLine();
                            courses = fileRead("resource/Courses.txt");
                            writer.println(courses.toString());
                            writer.flush();
                            //made from a file
                            if (file.equalsIgnoreCase("file")) {
                                //course name and file creating from
                                String courseName = reader.readLine();
                                String filename = reader.readLine();
                                if (filename.equals("")) {
                                    writer.println("invalidFile");
                                    writer.flush();
                                } else {
                                    File f = new File("resource/" + filename);
                                    if (f.exists()) {
                                        ArrayList<String> quizText = fileRead("resource/" + filename);
                                        if (quizzes.contains(courseName + "_" + quizText.get(0))) {

                                            writer.println("Exists");
                                            writer.flush();
                                        } else {
                                            writer.println("doesnt exist");
                                            writer.flush();
                                            Quiz quiz = new Quiz(courseName, rando, "resource/" + filename);
                                            String quizName = courseName + "_" + quiz.getQuizName();
                                            //append the correct quiz lists
                                            quizzes = programServer.fileRead("resource/Quizzes.txt");
                                            quizzes.add(quizName);
                                            programServer.fileUpdate(quizzes, "resource/Quizzes.txt");
                                            writer.println("quizCreated");
                                            writer.flush();
                                        }
                                    } else {
                                        writer.println("invalidFile");
                                        writer.flush();
                                    }
                                }
                                //manually enter quiz
                            } else if (file.equalsIgnoreCase("manually")) {
                                String courseName = reader.readLine();
                                boolean isValidInt = reader.readLine().equalsIgnoreCase("Valid input");
                                if (isValidInt) {
                                    String quizName = reader.readLine();
                                    String numberQuestions = reader.readLine();
                                    if (!quizzes.contains(courseName + "_" + quizName)) {
                                        writer.println("Quiz does not exist");
                                        writer.flush();
                                        int numberQuestionsFinal = Integer.parseInt(numberQuestions);
                                        ArrayList<String> questions = new ArrayList<String>();
                                        ArrayList<Character> answers = new ArrayList<Character>();
                                        ArrayList<String> choices = new ArrayList<String>();
                                        boolean indexError = false;
                                        for (int i = 0; i < numberQuestionsFinal; i++) {
                                            questions.add(reader.readLine());
                                            if (questions.get(0).equalsIgnoreCase("Index Error")) {
                                                indexError = true;
                                                break;
                                            }
                                            choices.add(reader.readLine());
                                            choices.add(reader.readLine());
                                            choices.add(reader.readLine());
                                            choices.add(reader.readLine());
                                            answers.add(reader.readLine().charAt(0));
                                        }
                                        if (choices.contains("") || questions.contains("") || indexError) {
                                            writer.println("did not save");
                                            writer.flush();
                                        } else {
                                            writer.println("saved");
                                            writer.flush();
                                            Quiz newQuiz = new Quiz(courseName, quizName, rando, questions,
                                                    choices, answers);
                                            ArrayList<String> quizNames = programServer.fileRead("res" +
                                                    "ource/Quizzes.txt");
                                            quizNames.add(courseName + "_" + quizName);
                                            programServer.fileUpdate(quizNames, "resource/Quizzes.txt");
                                        }


                                    } else {
                                        writer.println("Quiz exists");
                                        writer.flush();
                                    }
                                    //prob return some questions like q and answers
                                }
                            }
                        }
                        //edit quiz files only if file exists
                    } else if (clientInput.equalsIgnoreCase("editQuiz")) {
//randomization
                        courses = programServer.fileRead("resource/Courses.txt");
                        quizzes = programServer.fileRead("resource/Quizzes.txt");
                        quizSubmissions = programServer.fileRead("resource/QuizSubmissions.txt");
                        if (courses.size() == 0) {
                            writer.println("noCourses");
                            writer.flush();
                        } else {
                            writer.println("Courses");
                            writer.flush();
                            boolean rando = false;
                            String random = reader.readLine();
                            if (random.equalsIgnoreCase("random")) {
                                rando = true;
                            }
                            String file = reader.readLine();
                            courses = fileRead("resource/Courses.txt");
                            writer.println(courses.toString());
                            writer.flush();
                            //made from a file
                            if (file.equalsIgnoreCase("file")) {
                                //course name and file creating from
                                String courseName = reader.readLine();
                                String filename = reader.readLine();
                                if (filename.equals("")) {
                                    writer.println("invalidFile");
                                    writer.flush();
                                } else {
                                    File f = new File("resource/" + filename);
                                    if (f.exists()) {
                                        ArrayList<String> quizText = fileRead("resource/" + filename);
                                        if (!quizzes.contains(courseName + "_" + quizText.get(0))) {

                                            writer.println("doesnt exist");
                                            writer.flush();
                                        } else {
                                            writer.println("Exists");
                                            writer.flush();
                                            Quiz quiz = new Quiz(courseName, rando, "resource/" + filename);
                                            String quizName = courseName + "_" + quiz.getQuizName();
                                            //append the correct quiz lists
                                            writer.println("quizCreated");
                                            writer.flush();
                                        }
                                    } else {
                                        writer.println("invalidFile");
                                        writer.flush();
                                    }
                                }
                                //manually enter quiz
                            } else if (file.equalsIgnoreCase("manually")) {

                                String courseName = reader.readLine();
                                boolean isValidInt = reader.readLine().equalsIgnoreCase("Valid input");
                                if (isValidInt) {
                                    String quizName = reader.readLine();
                                    String numberQuestions = reader.readLine();
                                    if (quizzes.contains(courseName + "_" + quizName)) {
                                        writer.println("Quiz exists");
                                        writer.flush();
                                        int numberQuestionsFinal = Integer.parseInt(numberQuestions);
                                        ArrayList<String> questions = new ArrayList<String>();
                                        ArrayList<Character> answers = new ArrayList<Character>();
                                        ArrayList<String> choices = new ArrayList<String>();
                                        boolean indexError = false;
                                        for (int i = 0; i < numberQuestionsFinal; i++) {
                                            questions.add(reader.readLine());
                                            if (questions.get(0).equalsIgnoreCase("Index Error")) {
                                                indexError = true;
                                                break;
                                            }
                                            choices.add(reader.readLine());
                                            choices.add(reader.readLine());
                                            choices.add(reader.readLine());
                                            choices.add(reader.readLine());
                                            answers.add(reader.readLine().charAt(0));
                                        }
                                        if (choices.contains("") || questions.contains("") || indexError) {
                                            writer.println("did not save");
                                            writer.flush();
                                        } else {
                                            writer.println("saved");
                                            writer.flush();
                                            Quiz newQuiz = new Quiz(courseName, quizName, rando,
                                                    questions, choices, answers);
                                        }


                                    } else {
                                        writer.println("Quiz does not exist");
                                        writer.flush();
                                    }
                                    //prob return some questions like q and answers
                                }
                            }
                        }
                    } else if (clientInput.equalsIgnoreCase("viewQuiz")) {

                        courses = programServer.fileRead("resource/Courses.txt");
                        quizzes = programServer.fileRead("resource/Quizzes.txt");
                        if (courses.size() == 0) {
                            //courses or not
                            writer.println("noCourses");
                            writer.flush();
                        } else {
                            writer.println("courses");
                            writer.flush();
                            //sending courseList
                            writer.println(courses);
                            writer.flush();
                            String courseName = reader.readLine();
                            //quizzes with courseName
                            ArrayList<String> courseQuizzes = new ArrayList<>();
                            for (int j = 0; j < quizzes.size(); j++) {
                                if (quizzes.get(j).startsWith(courseName)) {
                                    String[] strip = quizzes.get(j).split("_");
                                    courseQuizzes.add(strip[1]);
                                }
                            }
                            if (courseQuizzes.size() == 0) {
                                writer.println("noQuizzes");
                                writer.flush();
                            } else {
                                //sending quizList
                                writer.println(courseQuizzes);
                                writer.flush();
                                String quizName = reader.readLine();
                                //sending quiz details
                                Quiz quiz = new Quiz(courseName, quizName);
                                ArrayList<String> quizDetails = quiz.viewQuiz();
                                writer.println(programServer.sendArray(quizDetails));
                                writer.println("sent");
                                writer.flush();
                            }
                        }

                    } else if (clientInput.equalsIgnoreCase("viewQuizSubmissions")) {

                        courses = programServer.fileRead("resource/Courses.txt");
                        quizzes = programServer.fileRead("resource/Quizzes.txt");
                        quizSubmissions = programServer.fileRead("resource/QuizSubmissions.txt");
                        ArrayList<String> quizSubmissionsChecker = new ArrayList<String>();
                        ArrayList<String> studentNames = new ArrayList<String>();

                        for (String s: quizSubmissions) {
                            String[] split = s.split("_");
                            quizSubmissionsChecker.add(split[0] + "_" + split[1]);
                            studentNames.add(split[2]);
                        }
                        if (courses.size() == 0) {
                            //courses or not
                            writer.println("noCourses");
                            writer.flush();
                        } else {
                            writer.println("courses");
                            writer.flush();
                            //sending courseList
                            writer.println(courses);
                            writer.flush();
                            String courseName = reader.readLine();
                            //quizzes with courseName
                            ArrayList<String> courseQuizzes = new ArrayList<>();
                            for (int j = 0; j < quizzes.size(); j++) {
                                if (quizzes.get(j).startsWith(courseName)) {
                                    String[] strip = quizzes.get(j).split("_");
                                    courseQuizzes.add(strip[1]);
                                }
                            }
                            if (courseQuizzes.size() == 0) {
                                writer.println("noQuizzes");
                                writer.flush();
                            } else {
                                //sending quizList
                                writer.println(courseQuizzes);
                                writer.flush();
                                String quizName = reader.readLine();
                                if (quizSubmissionsChecker.contains(courseName + "_" + quizName)) {
                                    writer.println("has submissions");
                                    writer.flush();
                                    ArrayList<String> applicableSubmissions = new ArrayList<String>();
                                    int i = 0;
                                    for (String s: quizSubmissionsChecker) {
                                        if(s.equals(courseName + "_" + quizName)) {
                                            applicableSubmissions.add(studentNames.get(i));
                                        }
                                        i++;
                                    }
                                    writer.println(applicableSubmissions);
                                    writer.flush();
                                    String submissionToView = reader.readLine();
                                    ArrayList<String> finalSubmissions = fileRead("resource/" +
                                            courseName + "_" +
                                            quizName + "_" + submissionToView + ".txt");

                                    writer.println(finalSubmissions);
                                    writer.flush();

                                } else {
                                    writer.println("no submissions");
                                    writer.flush();
                                }
                            }
                        }
                        //view quizzes for teachers
                    } else if (clientInput.equalsIgnoreCase("deleteQuiz")) {
                        //delete quiz files
                        courses = programServer.fileRead("resource/Courses.txt");
                        quizzes = programServer.fileRead("resource/Quizzes.txt");
                        quizSubmissions = programServer.fileRead("resource/QuizSubmissions.txt");
                        if (courses.size() == 0) {
                            writer.println("noCourses");
                            writer.flush();
                        } else {
                            writer.println(courses);
                            writer.flush();
                            String courseName = reader.readLine();
                            ArrayList<String> courseQuizzes = new ArrayList<>();
                            for (int i = 0; i < quizzes.size(); i++) {
                                if (quizzes.get(i).startsWith(courseName)) {
                                    String[] strip = quizzes.get(i).split("_");
                                    courseQuizzes.add(strip[1]);
                                }
                            }
                            // if there are quizzes
                            if (courseQuizzes.size() == 0) {
                                writer.println("noQuizzes");
                                writer.flush();
                            } else {
                                writer.println(courseQuizzes);
                                writer.flush();
                                String quizName = reader.readLine();
                                Quiz quiz = new Quiz(courseName, quizName);
                                quiz.deleteQuiz();
                                quiz.deleteStudentQuizzes();
                                quizzes.remove(courseName + "_" + quizName);
                                programServer.fileUpdate(quizzes, "resource/Quizzes.txt");
                                for (int i = 0; i < quizSubmissions.size(); i++) {
                                    if (quizSubmissions.get(i).startsWith(courseName + "_" + quizName)) {
                                        quizSubmissions.remove(i);
                                        i--;
                                    }
                                }
                            }
                        }
                        programServer.fileUpdate(quizSubmissions, "resource/QuizSubmissions.txt");

                    } else if (clientInput.equalsIgnoreCase("createCourse")) {
                        //create new course and append list
                        courses = programServer.fileRead("resource/Courses.txt");
                        String courseName = reader.readLine();
                        if (courses.contains(courseName)) {
                            writer.println("notAdded");
                        } else {
                            courses.add(courseName);
                            writer.println("added");
                            programServer.fileUpdate(courses,"resource/Courses.txt" );
                        }
                        writer.flush();
                    } else if (clientInput.equalsIgnoreCase("viewCoursesTeacher")) {
                        courses = programServer.fileRead("resource/Courses.txt");
                        if (courses.size() == 0) {
                            //courses or not
                            writer.println("noCourses");
                            writer.flush();
                        } else {
                            writer.println("courses");
                            writer.flush();
                            //sending courseList
                            writer.println(courses);
                            writer.flush();
                        }
                        //return list of courses
                    } else if (clientInput.equalsIgnoreCase("deleteCourse")) {
                        //delete new course and append list
                        courses = programServer.fileRead("resource/Courses.txt");
                        quizzes = programServer.fileRead("resource/Quizzes.txt");
                        quizSubmissions = programServer.fileRead("resource/QuizSubmissions.txt");
                        if (courses.size() == 0) {
                            writer.println("noCourses");
                            writer.flush();
                        } else {
                            writer.println(courses);
                            writer.flush();
                            String courseName = reader.readLine();
                            courses.remove(courseName);
                            writer.println("deleted");
                            programServer.fileUpdate(courses, "resource/Courses.txt");
                            ArrayList<String> courseQuizzes = new ArrayList<>();
                            for (int i = 0; i < quizzes.size(); i++) {
                                if (quizzes.get(i).startsWith(courseName)) {
                                    String[] strip = quizzes.get(i).split("_");
                                    courseQuizzes.add(strip[1]);
                                }
                            }
                            // if there are quizzes
                            for (int j = 0; j < courseQuizzes.size(); j++) {
                                Quiz quiz = new Quiz(courseName, courseQuizzes.get(j));
                                quiz.deleteQuiz();
                                quiz.deleteStudentQuizzes();
                                quizzes.remove(courseName + "_" + courseQuizzes.get(j));
                                for (int i = 0; i < quizSubmissions.size(); i++) {
                                    if (quizSubmissions.get(i).startsWith(courseName + "_" + courseQuizzes.get(j))) {
                                        quizSubmissions.remove(i);
                                        i--;
                                    }
                                }
                            }
                            programServer.fileUpdate(quizzes, "resource/Quizzes.txt");
                        }
                        programServer.fileUpdate(quizSubmissions, "resource/QuizSubmissions.txt");
                        writer.flush();
                    } else if (clientInput.equalsIgnoreCase("userSettings")) {
                        String typeOfChange = reader.readLine();
                        if (typeOfChange.equalsIgnoreCase("changeUsername")) {
                            String newUsername = reader.readLine();
                            boolean validChange = teacher.validUsername(newUsername);
                            if (validChange) {
                                teacher.editCredentials(newUsername, teacher.getPassword());
                                teacher.setUsername(newUsername);
                                writer.println("done");
                                writer.flush();
                            } else {
                                writer.println("not");
                                writer.flush();
                            }
                        } else if (typeOfChange.equalsIgnoreCase("changePassword")) {
                            String newPassword = reader.readLine();
                            teacher.editCredentials(teacher.getUsername(), newPassword);
                            teacher.setPassword(newPassword);
                        } else {
                            teacher.deleteCredentials();
                            teacher = null;
                        }
                    } else if (clientInput.equalsIgnoreCase("logout")) {
                        teacher = null;
                    } else if (clientInput == null) {
                        reader.close();
                        writer.flush();
                        writer.close();
                        break;
                    }
                    //if student is a teacher
                } else if (role.equalsIgnoreCase("student")) {
                    if (clientInput.equalsIgnoreCase("viewCoursesStudent")) {
                        courses = programServer.fileRead("resource/Courses.txt");
                        if (courses.size() == 0) {
                            //courses or not
                            writer.println("noCourses");
                            writer.flush();
                        } else {
                            writer.println("courses");
                            writer.flush();
                            //sending courseList
                            writer.println(courses);
                            writer.flush();
                        }
                        //return list of courses
                    } else if (clientInput.equalsIgnoreCase("takequiz")) {
                        courses = programServer.fileRead("resource/Courses.txt");
                        quizzes = programServer.fileRead("resource/Quizzes.txt");
                        quizSubmissions = programServer.fileRead("resource/QuizSubmissions.txt");
                        if (courses.size() == 0) {
                            //courses or not
                            writer.println("noCourses");
                            writer.flush();
                        } else {
                            writer.println("courses");
                            writer.flush();
                            //sending courseList
                            writer.println(courses);
                            writer.flush();
                            String courseName = reader.readLine();
                            //quizzes with courseName
                            ArrayList<String> courseQuizzes = new ArrayList<>();
                            for (int j = 0; j < quizzes.size(); j++) {
                                if (quizzes.get(j).startsWith(courseName)) {
                                    String[] strip = quizzes.get(j).split("_");
                                    courseQuizzes.add(strip[1]);
                                }
                            }
                            if (courseQuizzes.size() == 0) {
                                writer.println("noQuizzes");
                                writer.flush();
                            } else {
                                //sending quizList
                                writer.println(courseQuizzes);
                                writer.flush();
                                String quizName = reader.readLine();
                                //sending quiz details
                                Quiz quiz = new Quiz(courseName, quizName);
                                ArrayList<String> quizDetails = quiz.viewQuizToTake();
                                writer.println(programServer.sendArray(quizDetails));
                                writer.println("sent");
                                writer.flush();
                                for (int j = 0; j < quizSubmissions.size(); j++) {
                                    if (quizzes.get(j).startsWith(courseName + "_" + quizName)) {
                                        String[] strip = quizzes.get(j).split("_");
                                        quizSubmissions.add(strip[1]);
                                    }
                                }
                                if (quizSubmissions.contains((courseName + "_" + quizName +
                                        "_" + student.getUsername()))) {
                                    writer.println("has taken");
                                    writer.flush();
                                } else {
                                    writer.println("not taken");
                                    writer.flush();
                                    //read answers
                                    String answers = "";
                                    answers = reader.readLine();
                                    String[] strip = answers.split("");
                                    ArrayList<Character> finalsAnswers = new ArrayList<Character>();
                                    for (String s : strip) {
                                        finalsAnswers.add(s.charAt(0));
                                    }
                                    if (finalsAnswers.size() < quiz.getQuestions().size()) {
                                        writer.println("did not save");
                                        writer.flush();
                                    } else {
                                        writer.println("saved");
                                        writer.flush();
                                        quiz.writeStudentQuizFile(student.getUsername(), finalsAnswers);
                                        ArrayList<String> toAdd = fileRead("resource/QuizSubmissions.txt");
                                        toAdd.add((courseName + "_" + quizName + "_" + student.getUsername()));
                                        fileUpdate(toAdd, "resource/QuizSubmissions.txt");

                                    }
                                }

                            }
                        }
                    } else if (clientInput.equalsIgnoreCase("userSettings")) {

                        String typeOfChange = reader.readLine();
                        if (typeOfChange.equalsIgnoreCase("changeUsername")) {
                            String newUsername = reader.readLine();
                            boolean validChange = student.validUsername(newUsername);
                            if (validChange) {
                                student.editCredentials(newUsername, student.getPassword());
                                student.setUsername(newUsername);
                                writer.println("done");
                                writer.flush();
                            } else {
                                writer.println("not");
                                writer.flush();
                            }
                        } else if (typeOfChange.equalsIgnoreCase("changePassword")) {
                            String newPassword = reader.readLine();
                            student.editCredentials(student.getUsername(), newPassword);
                            student.setPassword(newPassword);
                        } else {
                            student.deleteCredentials();
                            student = null;
                        }

                    } else if (clientInput.equalsIgnoreCase("logout")) {
                        student = null;
                    } else if (clientInput == null) {
                        reader.close();
                        writer.flush();
                        writer.close();
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException e) {
                //for when client logs out
            }
        } while (true);
    }
}
