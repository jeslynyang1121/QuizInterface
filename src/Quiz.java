import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Project 05 -- Quiz.java
 *
 * A program that creates and stores quiz objects into files. Is called in ProgramServer.java.
 *
 * <p>Purdue University -- CS18000 -- Spring 2022 -- Project 05</p>
 *
 * @author Collin Scott lab sec 04
 *
 * @version May 05, 2022
 *
 *
 */
public class Quiz {

    private String quizName;
    private final String courseName;
    private final Scanner scanner = new Scanner(System.in);
    private boolean randomize;
    private final List<Integer> randomPositions = new ArrayList<>();
    private List<String> questions = new ArrayList<>();
    private List<String> choices = new ArrayList<>();
    private List<Character> answers = new ArrayList<>();

    //Constructor used when getting input from file. Must ask for course name in main program before running.
    public Quiz(String courseName, boolean randomize, String filename2)  {
        this.courseName = courseName;
        this.randomize = randomize;
        //String fileName;
        boolean exists = false;
        int i = -1;
        //System.out.println("Please enter the name of the file for the quiz you would like to create. " +
        //        "Ensure it is the absolute file path on your computer. " +
        //      "Please check the ReadMe for further clarification");
        //loops through until valid filename is entered.
        do {
            //fileName = scanner.nextLine();
            //catches exception and asks for a valid filepath
            try (BufferedReader br = new BufferedReader(new FileReader(filename2))) {
                String line;
                while ((line = br.readLine()) != null) {
                    //first line = name
                    if (i == -1) {
                        quizName = line;
                        // every 5th line is the question and answer
                    } else if (i % 5 == 0) {
                        questions.add(generateQuizQuestion(line));
                        answers.add(generateQuizAnswer(line));
                        //everything else is an answer choice
                    } else {
                        choices.add(generateQuizChoiceLetter(i % 5) + ": " + line);
                    }
                    i++;
                }
                //if success, break loop
                exists = true;
            } catch (IOException w) {
                System.out.println("Please enter a valid filepath");
            }
        } while (!exists);
        createQuizFile();
        generateRandomPositions();
    }

    //constructor used when entering questions manually. Must ask for course name in main program before running.
// needs to write quiz file
    public Quiz(String courseName, String quizName, boolean randomize, ArrayList<String> questions, ArrayList<String> choices, ArrayList<Character> answers ) {
        this.courseName = courseName;
        this.quizName = quizName;
        this.randomize = randomize;
        this.questions = questions;
        this.choices = choices;
        this.answers = answers;

        createQuizFile();
        generateRandomPositions();

    }

    //used when quiz already exists as a quiz file, and needs no input from user. Simply creates quiz object.
    public Quiz(String courseName, String quizName) {
        this.courseName = courseName;
        this.quizName = quizName;

        boolean exists = false;
        int i = -1;
        //loops through until valid filename is entered.
        do {
            //catches exception and asks for a valid filepath
            try (BufferedReader br = new BufferedReader(new FileReader("resource/" + courseName + "_" +
                    quizName + ".txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    //first line = name
                    if (i == -1) {
                        randomize = line.charAt(line.length() - 1) == 'R';

                        // every 5th line is the question and answer
                    } else if (i % 5 == 0) {
                        questions.add(generateQuizQuestion(line));
                        answers.add(generateQuizAnswer(line));
                        //everything else is an answer choice
                    } else {
                        choices.add(line);
                    }
                    i++;
                }
                //if success, break loop
                exists = true;
            } catch (IOException w) {
                System.out.println("The quiz could not be accessed. Please make sure the file is not corrupted");
                break;
            }
        } while (!exists);
        generateRandomPositions();
    }


    //gets the quiz question from first line of file
    public String generateQuizQuestion(String line) {
        String[] brokenLine = line.split(":");
        return brokenLine[0];
    }

    //gets the quiz answer from the first line of file
    public char generateQuizAnswer(String line) {
        String[] brokenLine = line.split(":");
        return brokenLine[1].charAt(0);
    }

    //returns the character corresponding to 1/2/3/4. Used when getting indexes from lists etc.
    public char generateQuizChoiceLetter(int choice) {
        switch (choice) {
            case 1: return 'A';
            case 2: return 'B';
            case 3: return 'C';
            case 4: return 'D';
            default: throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    //checks if the answer is correct
    public boolean isCorrect(int question, int userChoice) {
        return (answers.get(question) == generateQuizChoiceLetter(userChoice));
    }
    //asks the user for their choice. Prints the question out and its choices too in the process.
    public int askChoice(int question) {
        System.out.println(questions.get(question));
        System.out.println(printQuestionChoices(question));
        System.out.println("Please enter your choice for the question using 1 for A, 2 for B, 3 for C, and 4 for D!");
        do {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 1 || choice > 4) {
                    System.out.println("Please enter a valid choice!");
                } else {
                    return choice;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer");
                scanner.next();
            }
        } while(true);
    }

    //prints question choices given the question number
    public ArrayList<String> printQuestionChoicesArray(int question) {
        ArrayList<String> array = new ArrayList<>();
        array.add(choices.get((question * 4)));
        array.add(choices.get((question * 4) + 1));
        array.add(choices.get((question * 4) + 2));
        array.add(choices.get((question * 4) + 3));

        return array;
    }

    public String printQuestionChoices(int question) {

        return choices.get((question * 4)) + "\n" +
                choices.get((question * 4) + 1) + "\n" +
                choices.get((question * 4) + 2) + "\n" +
                choices.get((question * 4) + 3);
    }

    //writes the students answers to their individual quiz file
    public void writeStudentQuizFile(String studentName, List<Character> studentAnswers) {
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter("resource/" + courseName + "_" + quizName + "_"
                    + studentName + ".txt"));
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            out.write(studentName + " submission of " + quizName + ": " + timeStamp + "\n");
            for (int i = 0; i < studentAnswers.size(); i++) {
                if (!randomize) {
                    if (studentAnswers.get(randomPositions.indexOf(i)) == answers.get(randomPositions.indexOf(i))) {
                        out.write(questions.get(randomPositions.indexOf(i)) + ": Correct!\n");
                    } else {
                        out.write(questions.get(randomPositions.indexOf(i)) + ": Wrong. Student put " +
                                studentAnswers.get(randomPositions.indexOf(i)) +
                                " but the answer is " + answers.get(randomPositions.indexOf(i)) + "\n");
                    }
                } else {
                    if (studentAnswers.get(i) == answers.get(randomPositions.indexOf(i))) {
                        out.write(questions.get(randomPositions.indexOf(i)) + ": Correct!\n");
                    } else {
                        out.write(questions.get(randomPositions.indexOf(i)) + ": Wrong. Student put " +
                                studentAnswers.get(i) +
                                " but the answer is " + answers.get(randomPositions.indexOf(i)) + "\n");
                    }
                }
            }
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing quiz solutions to student's file");
        }




    }

    // combines all others to actually take the quiz. The main thing that will be called in other classes.
    public void takeQuiz(String studentName) {
        int studentAnswer;
        List<Character> studentAnswers = new ArrayList<>();
        String useFile;
        for (int i = 0; i < answers.size(); i++ ) {
            System.out.println("Would you like to answer this next question with a file? [Y/N]") ;
            while (true) {
                useFile = scanner.nextLine();
                if (useFile.equalsIgnoreCase("Y")) {
                    do {
                        System.out.println(questions.get(randomPositions.get(i)));
                        System.out.println(printQuestionChoices(randomPositions.get(i)));
                        System.out.println("Please enter the name of the answer file you would like to use " +
                                "Ensure it is the absolute file path on your computer. " +
                                "Please check the ReadMe for further clarification");
                        String fileName = scanner.nextLine();
                        //catches exception and asks for a valid filepath
                        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                            String line;
                            String studentAnswerFromFile = null;
                            while ((line = br.readLine()) != null) {
                                studentAnswerFromFile = line;
                            }
                            //if success, break loop
                            if (studentAnswerFromFile != null && studentAnswerFromFile.length() == 1) {
                                if (studentAnswerFromFile.equalsIgnoreCase("A") ||
                                        studentAnswerFromFile.equalsIgnoreCase("B") ||
                                        studentAnswerFromFile.equalsIgnoreCase("C") ||
                                        studentAnswerFromFile.equalsIgnoreCase("D")) {


                                    studentAnswers.add(studentAnswerFromFile.charAt(0));

                                    break;
                                }
                            } else {
                                System.out.println("Please enter a file that has a valid answer of A B C or D");
                            }
                        } catch (IOException w) {
                            System.out.println("Please enter a valid filepath");
                        }
                    } while (true);
                } else if (useFile.equalsIgnoreCase("N")) {
                    studentAnswer = askChoice(randomPositions.get(i));
                    studentAnswers.add(generateQuizChoiceLetter(studentAnswer));
                    break;
                } else {
                    System.out.println("Please enter only Y or N");
                }
                break;
            }
        }
        writeStudentQuizFile(studentName, studentAnswers);
    }
    //checks if the student has taken the quiz. To be used in other classes
//deletes quiz
    public void deleteQuiz() {
        try {
            Files.delete(Paths.get(("resource/" + courseName + "_" + quizName + ".txt")));
        } catch (IOException e) {
            System.out.println("Quiz could not be deleted. Please make sure it exists.");
        }
    }
    //deletes student quizzes associated with this quiz
    public void deleteStudentQuizzes() {
        try {
            File dir = new File("resource/");
            File[] files = dir.listFiles((dir1, name) -> name.startsWith(courseName + "_" + quizName) &&
                    name.endsWith(".txt"));
            if (files != null) {
                for (File f : files) {
                    Files.delete(Paths.get(String.valueOf(f)));
                }
            }
        } catch (IOException e) {
            System.out.println("Student quizzes could not be deleted");
            e.printStackTrace();
        }
    }
    //prints out contents of quiz
    public ArrayList<String> viewQuiz() {
        //catches exception and asks for a valid filepath
        ArrayList<String> array = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resource/" +
                courseName + "_" + quizName + ".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //TODO handle first line = name?
                //System.out.println(line);
                array.add(line);
            }
            //if success, break loop
        } catch (IOException e) {
            System.out.println("The quiz could not be read. Please make sure it exists");
        }

        return array;
    }

    public ArrayList<String> viewQuizToTake() {
        //catches exception and asks for a valid filepath
        ArrayList<String> array = new ArrayList<>();
        array.add(quizName);
        for (int i = 0; i < questions.size(); i++) {
            array.add(questions.get(randomPositions.get(i)) + ":" + answers.get(randomPositions.get(i)));
            ArrayList<String> arrayChoice = printQuestionChoicesArray(randomPositions.get(i));
            array.add(arrayChoice.get(0));
            array.add(arrayChoice.get(1));
            array.add(arrayChoice.get(2));
            array.add(arrayChoice.get(3));
        }

        return array;
    }

    public ArrayList<String> viewStudentsQuiz(String studentName) {
        ArrayList<String> studentQuiz = new ArrayList<>();

        //catches exception and asks for a valid filepath
        try (BufferedReader br = new BufferedReader(
                new FileReader("resource/" + courseName + "_" + quizName + "_" + studentName + ".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //TODO handle first name?
                //first line = name
                //System.out.println(line);
                studentQuiz.add(line);
            }
            //if success, break loop
        } catch (IOException w) {
            System.out.println(studentName + " has not taken the quiz yet");
        }
        return studentQuiz;
    }

    public void generateRandomPositions() {
        for (int i = 0; i < questions.size(); i++) {
            randomPositions.add(i);
        }
        if (randomize) {
            Collections.shuffle(randomPositions, new Random());
        }
    }

    public void createQuizFile() {
        BufferedWriter out;
        String random;
        if (randomize) {
            random = ":R";
        } else {
            random = "";
        }
        try {
            out = new BufferedWriter(new FileWriter("resource/" + courseName + "_" + quizName + ".txt"));
            out.write(quizName + random + "\n");
            for (int i = 0; i < answers.size(); i++) {
                out.write(questions.get(i) + ":" + answers.get(i) + "\n" );
                out.write(printQuestionChoices(i) + "\n");
            }
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing quiz solutions to student's file");
        }
    }

    public boolean hasStudentTakenQuiz(String studentName) {
        File f = new File("resource/" + courseName + "_" + quizName + "_" + studentName + ".txt");
        return f.isFile();
    }

    //setters
    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public void setAnswers(List<Character> answers) {
        this.answers = answers;
    }

    public void getAnswers() {
        for (char c: answers) {
            System.out.println(c);
        }
    }

    public ArrayList<String> getQuestions() {
        return (ArrayList<String>) questions;
    }

    public void getChoices() {
        for (String s : choices) {
            System.out.println(s);
        }
    }

    public String getQuizName() {
        return quizName;
    }
}