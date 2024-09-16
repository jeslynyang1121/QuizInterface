import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Project 05 -- GUIPaneTests.java
 *
 * This is the GUI portion of the project that interacts with the user
 *
 * <p>Purdue University -- CS18000 -- Spring 2022 -- Project 05</p>
 *
 * @author Akshay Godhani, Bodhi Scott, Jeslyn Yang lab sec 04
 *
 * @version May 01, 2022
 *
 *
 */


public class GUIPaneTests extends JComponent implements Runnable {
    //Initial Menu Buttons
    JButton logInButton;
    JButton signUpButton;

    JButton teacherButton;
    JButton studentButton;

    int counter = -1 ;
    int saveChecker = 0;
    String role = "";

    Container content;

    //Log in and Sign Up Menus
    JButton finalizeLogInButton;
    JButton finalizeSignUpButton;
    JTextField usernameLogInText;
    JTextField passwordLogInText;
    JTextField usernameSignUpText;
    JTextField passwordSignUpText;
    JLabel username1Label = new JLabel("Username:");
    JLabel username2Label = new JLabel("Username:");
    JLabel password1Label = new JLabel("Password:");
    JLabel password2Label = new JLabel("Password:");


    //Teacher Menu Buttons
    JButton createQuizButton;
    JButton editQuizButton;
    JButton viewQuizzesButton;
    JButton viewQuizSubmissionsButton;
    JButton deleteQuizzesButton;
    JButton createCourseButton;
    JButton viewCoursesTeacherButton;
    JButton deleteCoursesButton;
    JButton userSettingsTeacherButton;
    JButton logOutTeacherButton;

    //Student Menu Buttons
    JButton takeQuizButton;
    JButton userSettingsStudentButton;
    JButton viewCoursesStudentButton;
    JButton logOutStudentButton;

    //User Setting Buttons
    JButton changeUsernameButton;
    JButton changePasswordButton;
    JButton deleteAccountButton;
    JButton returnToMenuButton;

    //Create Quiz Buttons
    JButton createQuizFromFileButton;
    JButton createQuizManuallyButton;

    JButton editQuizFromFileButton;
    JButton editQuizManuallyButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUIPaneTests());
    }

    public void run() {
        try {
            Socket socket = new Socket("localhost", 1234);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());


//Initial setup
            JFrame Menu = new JFrame("Role Selection Menu");
            Container content = Menu.getContentPane();
            content.setLayout(new BorderLayout());
            Menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Menu.setSize(500, 138);
            Menu.setLocationRelativeTo(null);
            Menu.setVisible(true);

//Initial Menu Buttons
            logInButton = new JButton("Log In");
            logInButton.setPreferredSize(new Dimension(100, 100));

            signUpButton = new JButton("Sign Up");
            signUpButton.setPreferredSize(new Dimension(100, 100));

//Role selection Buttons
            teacherButton = new JButton("Teacher");
            teacherButton.setPreferredSize(new Dimension(100, 100));

            studentButton = new JButton("Student");
            studentButton.setPreferredSize(new Dimension(100, 100));

//Sign Up Menu Buttons
            finalizeSignUpButton = new JButton("Sign Up");
            usernameSignUpText = new JTextField(10);
            passwordSignUpText = new JTextField(10);

//Log In Menu Buttons
            finalizeLogInButton = new JButton("Log In");
            usernameLogInText = new JTextField(10);
            passwordLogInText = new JTextField(10);


//Teacher Menu Buttons
            createQuizButton = new JButton("Create Quiz");
            createQuizButton.setPreferredSize(new Dimension(250, 100));

            editQuizButton = new JButton("Edit Quiz");
            editQuizButton.setPreferredSize(new Dimension(250, 100));

            viewQuizzesButton = new JButton("View Quizzes");
            viewQuizzesButton.setPreferredSize(new Dimension(250, 100));

            viewQuizSubmissionsButton = new JButton("View Quiz Submissions");
            viewQuizSubmissionsButton.setPreferredSize(new Dimension(250, 100));

            deleteQuizzesButton = new JButton("Delete Quiz");
            deleteQuizzesButton.setPreferredSize(new Dimension(250, 100));

            createCourseButton = new JButton("Create Course");
            createCourseButton.setPreferredSize(new Dimension(250, 100));

            viewCoursesTeacherButton = new JButton("View Courses");
            viewCoursesTeacherButton.setPreferredSize(new Dimension(250, 100));

            deleteCoursesButton = new JButton("Delete Course");
            deleteCoursesButton.setPreferredSize(new Dimension(250, 100));

            userSettingsTeacherButton = new JButton("User Settings");
            userSettingsTeacherButton.setPreferredSize(new Dimension(250, 100));

            logOutTeacherButton = new JButton("Log Out");
            logOutTeacherButton.setPreferredSize(new Dimension(250, 100));


//Student Menu Buttons
            takeQuizButton = new JButton("Take Quiz");
            takeQuizButton.setPreferredSize(new Dimension(250, 100));

            viewCoursesStudentButton = new JButton("View Courses");
            viewCoursesStudentButton.setPreferredSize(new Dimension(250, 100));

            userSettingsStudentButton = new JButton("User Settings");
            userSettingsStudentButton.setPreferredSize(new Dimension(250, 100));

            logOutStudentButton = new JButton("Log Out");
            logOutStudentButton.setPreferredSize(new Dimension(250, 100));

//User Setting Buttons
            changeUsernameButton = new JButton("Change Username");
            changeUsernameButton.setPreferredSize(new Dimension(250, 100));

            changePasswordButton = new JButton("Change Password");
            changePasswordButton.setPreferredSize(new Dimension(250, 100));

            deleteAccountButton = new JButton("Delete Account");
            deleteAccountButton.setPreferredSize(new Dimension(250, 100));

            returnToMenuButton = new JButton("Return to Menu");
            returnToMenuButton.setPreferredSize(new Dimension(250, 100));


//Create Quiz Buttons
            createQuizFromFileButton = new JButton("Create Quiz From File");
            createQuizFromFileButton.setPreferredSize(new Dimension(250, 100));

            createQuizManuallyButton = new JButton("Create Quiz Manually");
            createQuizManuallyButton.setPreferredSize(new Dimension(250, 100));

            editQuizFromFileButton = new JButton("Edit Quiz From File");
            editQuizFromFileButton.setPreferredSize(new Dimension(250, 100));

            editQuizManuallyButton = new JButton("Edit Quiz Manually");
            editQuizManuallyButton.setPreferredSize(new Dimension(250, 100));

//randomization Menu Set up
            JButton createQuizRandomYButton = new JButton("Random");
            createQuizRandomYButton.setPreferredSize(new Dimension(250, 100));
            JButton createQuizRandomNButton = new JButton("Not Random");
            createQuizRandomNButton.setPreferredSize(new Dimension(250, 100));
            JPanel randomizationMenuPanel = new JPanel();
            randomizationMenuPanel.setLayout(new GridLayout(1, 2));
            randomizationMenuPanel.add(createQuizRandomYButton);
            randomizationMenuPanel.add(createQuizRandomNButton);

            JButton editQuizRandomYButton = new JButton("Random");
            editQuizRandomYButton.setPreferredSize(new Dimension(250, 100));
            JButton editQuizRandomNButton = new JButton("Not Random");
            editQuizRandomNButton.setPreferredSize(new Dimension(250, 100));
            JPanel editRandomizationMenuPanel = new JPanel();
            editRandomizationMenuPanel.setLayout(new GridLayout(1, 2));
            editRandomizationMenuPanel.add(editQuizRandomYButton);
            editRandomizationMenuPanel.add(editQuizRandomNButton);

//Teacher Menu Setup
            JPanel teacherMenuPanel = new JPanel();
            teacherMenuPanel.setLayout(new GridLayout(5, 2));
            teacherMenuPanel.add(createQuizButton);
            teacherMenuPanel.add(editQuizButton);
            teacherMenuPanel.add(viewQuizzesButton);
            teacherMenuPanel.add(viewQuizSubmissionsButton);
            teacherMenuPanel.add(deleteQuizzesButton);
            teacherMenuPanel.add(createCourseButton);
            teacherMenuPanel.add(viewCoursesTeacherButton);
            teacherMenuPanel.add(deleteCoursesButton);
            teacherMenuPanel.add(userSettingsTeacherButton);
            teacherMenuPanel.add(logOutTeacherButton);

//Initial Menu Setup
            JPanel initialMenuPanel = new JPanel();
            initialMenuPanel.setLayout(new GridLayout(1, 2));
            initialMenuPanel.add(logInButton);
            initialMenuPanel.add(signUpButton);

//Role Selection Menu Setup
            JPanel roleSelectionMenuPanel = new JPanel();
            roleSelectionMenuPanel.setLayout(new GridLayout(1, 2));
            roleSelectionMenuPanel.add(teacherButton);
            roleSelectionMenuPanel.add(studentButton);

//Log In Menu Setup
            JPanel logInMenuPanel = new JPanel();
            logInMenuPanel.add(username1Label);
            logInMenuPanel.add(usernameLogInText);
            logInMenuPanel.add(password1Label);
            logInMenuPanel.add(passwordLogInText);
            logInMenuPanel.add(finalizeLogInButton);

//Sign Up Menu Buttons
            JPanel signUpMenuPanel = new JPanel();
            signUpMenuPanel.add(username2Label);
            signUpMenuPanel.add(usernameSignUpText);
            signUpMenuPanel.add(password2Label);
            signUpMenuPanel.add(passwordSignUpText);
            signUpMenuPanel.add(finalizeSignUpButton);

//Student Menu
            JPanel studentMenuPanel = new JPanel();
            studentMenuPanel.setLayout(new GridLayout(2, 2));
            studentMenuPanel.add(takeQuizButton);
            studentMenuPanel.add(viewCoursesStudentButton);
            studentMenuPanel.add(userSettingsStudentButton);
            studentMenuPanel.add(logOutStudentButton);

//User Settings Menu
            JPanel userSettingsMenuPanel = new JPanel();
            userSettingsMenuPanel.setLayout(new GridLayout(2, 2));
            userSettingsMenuPanel.add(changeUsernameButton);
            userSettingsMenuPanel.add(changePasswordButton);
            userSettingsMenuPanel.add(deleteAccountButton);
            userSettingsMenuPanel.add(returnToMenuButton);

//Create Quiz
            JPanel createQuizMenuPanel = new JPanel();
            createQuizMenuPanel.setLayout(new GridLayout(1, 2));
            createQuizMenuPanel.add(createQuizFromFileButton);
            createQuizMenuPanel.add(createQuizManuallyButton);

            JPanel editQuizMenuPanel = new JPanel();
            editQuizMenuPanel.setLayout(new GridLayout(1, 2));
            editQuizMenuPanel.add(editQuizFromFileButton);
            editQuizMenuPanel.add(editQuizManuallyButton);

            content.add(roleSelectionMenuPanel, BorderLayout.NORTH);
            //Works
            logOutTeacherButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(roleSelectionMenuPanel, BorderLayout.NORTH);
                    Menu.setSize(500, 138);
                    Menu.setLocationRelativeTo(null);
                    Menu.setVisible(true);
                    passwordLogInText.setText("");
                    passwordSignUpText.setText("");
                    usernameLogInText.setText("");
                    usernameSignUpText.setText("");
                }
            });
            //Works
            logOutStudentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(roleSelectionMenuPanel, BorderLayout.NORTH);
                    Menu.setSize(500, 138);
                    Menu.setLocationRelativeTo(null);
                    Menu.setVisible(true);
                    passwordLogInText.setText("");
                    passwordSignUpText.setText("");
                    usernameLogInText.setText("");
                    usernameSignUpText.setText("");

                }
            });
            //Works
            teacherButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("teacher");
                    writer.flush();

                    Menu.setVisible(false);
                    content.add(initialMenuPanel, BorderLayout.NORTH);
                    content.remove(roleSelectionMenuPanel);
                    Menu.setTitle("Initial Menu");
                    Menu.setVisible(true);

                }
            });
            //Works
            studentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("student");
                    writer.flush();

                    Menu.setVisible(false);
                    content.add(initialMenuPanel, BorderLayout.NORTH);
                    content.remove(roleSelectionMenuPanel);
                    Menu.setTitle("Initial Menu");
                    Menu.setVisible(true);

                }
            });
            //Works
            userSettingsTeacherButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("userSettings");
                    writer.flush();

                    Menu.setVisible(false);
                    content.add(userSettingsMenuPanel, BorderLayout.NORTH);
                    content.remove(teacherMenuPanel);
                    Menu.setSize(500, 238);
                    Menu.setTitle("User Settings Menu");
                    Menu.setVisible(true);
                }
            });

            userSettingsStudentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("userSettings");
                    writer.flush();

                    Menu.setVisible(false);
                    content.add(userSettingsMenuPanel, BorderLayout.NORTH);
                    content.remove(studentMenuPanel);
                    Menu.setSize(500, 238);
                    Menu.setTitle("User Settings Menu");
                    Menu.setVisible(true);
                }
            });
            //Works
            logInButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("login");
                    writer.flush();

                    Menu.setVisible(false);
                    content.add(logInMenuPanel, BorderLayout.NORTH);
                    content.remove(initialMenuPanel);
                    Menu.setSize(500, 75);
                    Menu.setTitle("Log In Menu");
                    Menu.setVisible(true);
                }
            });
            //Works
            signUpButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("signup");
                    writer.flush();

                    Menu.setVisible(false);
                    content.add(signUpMenuPanel, BorderLayout.NORTH);
                    content.remove(initialMenuPanel);
                    Menu.setSize(600, 75);
                    Menu.setTitle("Sign Up Menu");
                    Menu.setVisible(true);
                }
            });
            //Works
            finalizeLogInButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println(usernameLogInText.getText());
                    writer.println(passwordLogInText.getText());
                    writer.flush();
                    String isValid = "";
                    try {
                        isValid = reader.readLine();
                    } catch (IOException ie) {
                        JOptionPane.showMessageDialog(null,
                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (isValid.equalsIgnoreCase("not")) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                        Menu.setVisible(false);
                        content.add(logInMenuPanel);
                        usernameLogInText.setText("");
                        passwordLogInText.setText("");
                        Menu.setTitle("Log In Menu");
                        Menu.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Welcome " + usernameLogInText.getText() + "!",
                                "Success", JOptionPane.PLAIN_MESSAGE);
                        try {
                            role = reader.readLine();
                        } catch (IOException ie) {
                            JOptionPane.showMessageDialog(null,
                                    "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        if (role.equalsIgnoreCase("teacher")) {
                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 538);
                        } else {
                            content.add(studentMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 238);
                        }
                        Menu.setVisible(false);
                        content.remove(logInMenuPanel);
                        Menu.setTitle("Main Menu");
                        Menu.setVisible(true);
                    }
                }
            });
            //Works
            finalizeSignUpButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println(usernameSignUpText.getText());
                    writer.println(passwordSignUpText.getText());
                    writer.flush();

                    String isValid = "";
                    try {
                        isValid = reader.readLine();
                    } catch (IOException ie) {
                        JOptionPane.showMessageDialog(null,
                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (isValid.equalsIgnoreCase("not")) {
                        JOptionPane.showMessageDialog(null,
                                "This username is already being used.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        Menu.setVisible(false);
                        content.add(signUpMenuPanel);
                        usernameSignUpText.setText("");
                        passwordSignUpText.setText("");
                        Menu.setTitle("Sign Up Menu");
                        Menu.setVisible(true);
                    } else if (isValid.equalsIgnoreCase("empty")) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a username and password.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        Menu.setVisible(false);
                        content.add(signUpMenuPanel);
                        usernameSignUpText.setText("");
                        passwordSignUpText.setText("");
                        Menu.setTitle("Sign Up Menu");
                        Menu.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Welcome " + usernameSignUpText.getText() + "!",
                                "Success", JOptionPane.PLAIN_MESSAGE);
                        Menu.setVisible(false);
                        String role = "";
                        try {
                            role = reader.readLine();
                        } catch (IOException ie) {
                            JOptionPane.showMessageDialog(null,
                                    "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        if (role.equalsIgnoreCase("teacher")) {
                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 538);
                        } else {
                            content.add(studentMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 238);
                        }
                        content.remove(signUpMenuPanel);
                        Menu.setTitle("Main Menu");
                        Menu.setVisible(true);
                    }
                }
            });
            //Works
            createQuizRandomYButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("random");
                    writer.flush();

                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(createQuizMenuPanel, BorderLayout.NORTH);
                    Menu.setSize(500, 140);
                    Menu.setTitle("Create Quiz");
                    Menu.setVisible(true);

                }
            });
            //Works
            createQuizRandomNButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("not");
                    writer.flush();

                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(createQuizMenuPanel, BorderLayout.NORTH);
                    Menu.setSize(500, 140);
                    Menu.setTitle("Create Quiz");
                    Menu.setVisible(true);

                }
            });
            //Works
            createQuizButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("createQuiz");
                    writer.flush();

                    try {
                        String areCourses = reader.readLine();
                        if (areCourses.equalsIgnoreCase("Courses")) {
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(randomizationMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 140);
                            Menu.setTitle("Create Quiz");
                            Menu.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "There are no courses created.", "Error", JOptionPane.ERROR_MESSAGE);
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 538);
                            Menu.setTitle("Main Menu");
                            Menu.setVisible(true);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
            //Works
            createQuizFromFileButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("file");
                    writer.flush();
                    String courses = "";
                    try {
                        courses = reader.readLine();
                        courses = courses.substring(1, courses.length() - 1);
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null,
                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    String[] courseStrings = courses.split(", ");

                    JComboBox coursesDropDown = new JComboBox(courseStrings);
                    JButton courseToCreateQuizInButton = new JButton("Create Quiz");
                    JPanel courseToCreateQuizInPanel = new JPanel();
                    courseToCreateQuizInPanel.add(new JLabel("Choose a course to create a quiz in:"));
                    courseToCreateQuizInPanel.add(coursesDropDown);
                    courseToCreateQuizInPanel.add(courseToCreateQuizInButton);


                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(courseToCreateQuizInPanel, BorderLayout.NORTH);
                    Menu.setSize(700, 78);
                    Menu.setTitle("Create Quiz");
                    Menu.setVisible(true);

                    courseToCreateQuizInButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            writer.println(coursesDropDown.getSelectedItem());
                            writer.flush();

                            JPanel finalizeCreateQuizFromFilePanel = new JPanel();
                            finalizeCreateQuizFromFilePanel.add(new JLabel("Quiz file path:"));
                            JButton finalizeCreateQuizFromFileButton = new JButton("Create Quiz");
                            JTextField filePathText = new JTextField(10);
                            finalizeCreateQuizFromFilePanel.add(filePathText);
                            finalizeCreateQuizFromFilePanel.add(finalizeCreateQuizFromFileButton);
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(finalizeCreateQuizFromFilePanel, BorderLayout.NORTH);
                            Menu.setSize(500, 75);
                            Menu.setTitle("Create Quiz");
                            Menu.setVisible(true);

                            finalizeCreateQuizFromFileButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    writer.println(filePathText.getText());
                                    writer.flush();

                                    try {
                                        boolean exists = reader.readLine().equalsIgnoreCase("Exists");
                                        if (exists) {
                                            JOptionPane.showMessageDialog(null,
                                                    "This quiz already exists in this course",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            Menu.setVisible(false);
                                            content.removeAll();
                                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                                            Menu.setSize(500, 538);
                                            Menu.setTitle("Main Menu");
                                            Menu.setVisible(true);
                                        } else {
                                            String result = "";
                                            try {
                                                result = reader.readLine();
                                            } catch (IOException ioException) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                            if (result.equalsIgnoreCase("quizCreated")) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Quiz created.",
                                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                                                Menu.setVisible(false);
                                                content.removeAll();
                                                content.add(teacherMenuPanel, BorderLayout.NORTH);
                                                Menu.setSize(500, 538);
                                                Menu.setTitle("Main Menu");
                                                Menu.setVisible(true);
                                            } else if (result.equalsIgnoreCase("invalidFile")) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Invalid File Path",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                Menu.setVisible(false);
                                                content.removeAll();
                                                content.add(teacherMenuPanel, BorderLayout.NORTH);
                                                Menu.setSize(500, 538);
                                                Menu.setTitle("Main Menu");
                                                Menu.setVisible(true);
                                            } else if (result.equalsIgnoreCase("already exists")) {
                                                JOptionPane.showMessageDialog(null,
                                                        "This quiz already exists in this course",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                Menu.setVisible(false);
                                                content.removeAll();
                                                content.add(teacherMenuPanel, BorderLayout.NORTH);
                                                Menu.setSize(500, 538);
                                                Menu.setTitle("Main Menu");
                                                Menu.setVisible(true);
                                            }
                                        }
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null,
                                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            });
                        }
                    });
                }
            });
            //Works
            createQuizManuallyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("manually");
                    writer.flush();
                    String courses = "";
                    try {
                        courses = reader.readLine();
                        courses = courses.substring(1, courses.length() - 1);
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null,
                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    String[] courseStrings = courses.split(", ");

                    JComboBox coursesDropDown = new JComboBox(courseStrings);
                    JButton courseToCreateQuizInButton = new JButton("Create Quiz In");
                    JPanel courseToCreateQuizInPanel = new JPanel();
                    courseToCreateQuizInPanel.add(new JLabel("Choose a course to create quiz in:"));
                    courseToCreateQuizInPanel.add(coursesDropDown);
                    courseToCreateQuizInPanel.add(courseToCreateQuizInButton);


                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(courseToCreateQuizInPanel, BorderLayout.NORTH);
                    Menu.setSize(700, 78);
                    Menu.setTitle("Create Quiz");
                    Menu.setVisible(true);

                    courseToCreateQuizInButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            writer.println(coursesDropDown.getSelectedItem());
                            writer.flush();

                            counter = -1;
                            ArrayList<String> questions = new ArrayList<String>();
                            ArrayList<Character> answers = new ArrayList<Character>();
                            ArrayList<String> choices = new ArrayList<String>();

                            JLabel nameLabel = new JLabel("Name:");
                            JTextField nameText = new JTextField(10);
                            JLabel lengthLabel = new JLabel("# of Questions:");
                            JTextField lengthText = new JTextField(2);
                            JButton finalCreateQuizButton = new JButton("Create");
                            JPanel finalCreateQuizPanel = new JPanel();
                            finalCreateQuizPanel.add(nameLabel);
                            finalCreateQuizPanel.add(nameText);
                            finalCreateQuizPanel.add(lengthLabel);
                            finalCreateQuizPanel.add(lengthText);
                            finalCreateQuizPanel.add(finalCreateQuizButton);

                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(finalCreateQuizPanel, BorderLayout.NORTH);
                            Menu.setSize(700, 78);
                            Menu.setTitle("Create Quiz");
                            Menu.setVisible(true);

                            finalCreateQuizButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        int length = Integer.parseInt(lengthText.getText());
                                        boolean doesntExist = true;
                                        if (counter == - 1) {
                                            writer.println("Valid input");
                                            writer.println(nameText.getText());
                                            writer.println(lengthText.getText());
                                            writer.flush();
                                            doesntExist = reader.readLine().equalsIgnoreCase("Quiz " +
                                                    "does not exist");
                                        }
                                        if (doesntExist) {
                                            counter++;
                                            saveChecker = 0;
                                            String[] correctChoiceList = {"A", "B", "C", "D"};
                                            JComboBox chooseAnswerDropdown = new JComboBox(correctChoiceList);

                                            JLabel questionLabel = new JLabel("Question:");
                                            JLabel choiceALabel = new JLabel("A:");
                                            JLabel choiceBLabel = new JLabel("B:");
                                            JLabel choiceCLabel = new JLabel("C:");
                                            JLabel choiceDLabel = new JLabel("D:");
                                            JLabel correctAnswerLabel = new JLabel("Correct Answer");
                                            JButton save = new JButton("Save Question");

                                            JTextField questionText = new JTextField(10);
                                            JTextField choiceAText = new JTextField(10);
                                            JTextField choiceBText = new JTextField(10);
                                            JTextField choiceCText = new JTextField(10);
                                            JTextField choiceDText = new JTextField(10);

                                            JPanel quizQuestionPanel = new JPanel();
                                            quizQuestionPanel.setLayout(new BoxLayout(quizQuestionPanel,
                                                    BoxLayout.Y_AXIS));

                                            quizQuestionPanel.add(questionLabel);
                                            quizQuestionPanel.add(questionText);
                                            quizQuestionPanel.add(choiceALabel);
                                            quizQuestionPanel.add(choiceAText);
                                            quizQuestionPanel.add(choiceBLabel);
                                            quizQuestionPanel.add(choiceBText);
                                            quizQuestionPanel.add(choiceCLabel);
                                            quizQuestionPanel.add(choiceCText);
                                            quizQuestionPanel.add(choiceDLabel);
                                            quizQuestionPanel.add(choiceDText);
                                            quizQuestionPanel.add(correctAnswerLabel);
                                            quizQuestionPanel.add(chooseAnswerDropdown);
                                            quizQuestionPanel.add(save);
                                            quizQuestionPanel.add(finalCreateQuizButton);

                                            save.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    if (questionText.getText().equals("") ||
                                                            choiceAText.getText().equals("")
                                                            || choiceBText.getText().equals("")
                                                            || choiceCText.getText().equals("")
                                                            || choiceDText.getText().equals("")) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "Please fill all fields", "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                                        if (counter == 0) {
                                                            counter = 0;
                                                        } else {
                                                            counter--;
                                                        }
                                                        Menu.setVisible(false);
                                                        content.removeAll();
                                                        content.add(quizQuestionPanel, BorderLayout.NORTH);
                                                        Menu.setSize(500, 538);
                                                        Menu.setTitle("Create Quiz");
                                                        Menu.setVisible(true);
                                                    } else if (saveChecker == 0) {
                                                        questions.add(questionText.getText());
                                                        choices.add(choiceAText.getText());
                                                        choices.add(choiceBText.getText());
                                                        choices.add(choiceCText.getText());
                                                        choices.add(choiceDText.getText());
                                                        answers.add(chooseAnswerDropdown.getSelectedItem().
                                                                toString().charAt(0));
                                                        saveChecker = 1;
                                                    } else {
                                                        questions.set(counter, questionText.getText());
                                                        choices.set((counter * 4) , choiceAText.getText());
                                                        choices.set((counter * 4) + 1, choiceBText.getText());
                                                        choices.set((counter * 4) + 2, choiceCText.getText());
                                                        choices.set((counter * 4) + 3, choiceDText.getText());
                                                        answers.set(counter, chooseAnswerDropdown.getSelectedItem().
                                                                toString().charAt(0));
                                                    }
                                                }
                                            });

                                            if (counter < length) {

                                                choiceAText.setText("");
                                                choiceBText.setText("");
                                                choiceCText.setText("");
                                                choiceDText.setText("");
                                                finalCreateQuizButton.setText("Next");
                                                if (counter == length) {
                                                    finalCreateQuizButton.setText("Return to Menu");
                                                }

                                                Menu.setVisible(false);
                                                content.removeAll();
                                                content.add(quizQuestionPanel, BorderLayout.NORTH);
                                                Menu.setSize(500, 538);
                                                Menu.setTitle("Create Quiz");
                                                Menu.setVisible(true);

                                                if (counter != 0) {

                                                }
                                            } else {
                                                for (int i = 0; i < length; i++) {
                                                    try {
                                                        writer.println(questions.get(i));
                                                        writer.println(choices.get((i * 4)));
                                                        writer.println(choices.get((i * 4) + 1));
                                                        writer.println(choices.get((i * 4) + 2));
                                                        writer.println(choices.get((i * 4) + 3));
                                                        writer.println(answers.get(i).toString());
                                                        writer.flush();
                                                    } catch (IndexOutOfBoundsException k) {
                                                        writer.println("Index Error");
                                                        writer.flush();
                                                    }
                                                }

                                                boolean didTheySave;
                                                didTheySave = reader.readLine().equalsIgnoreCase("saved");
                                                if (didTheySave) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Quiz created.", "Success",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    Menu.setVisible(false);
                                                    content.removeAll();
                                                    content.add(teacherMenuPanel, BorderLayout.NORTH);
                                                    Menu.setSize(500, 538);
                                                    Menu.setTitle("Main Menu");
                                                    Menu.setVisible(true);
                                                } else {
                                                    JOptionPane.showMessageDialog(null,
                                                            "You did not save a question correctly",
                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                    Menu.setVisible(false);
                                                    content.removeAll();
                                                    content.add(teacherMenuPanel, BorderLayout.NORTH);
                                                    Menu.setSize(500, 538);
                                                    Menu.setTitle("Main Menu");
                                                    Menu.setVisible(true);
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null,
                                                    "This quiz already exists in this course",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            Menu.setVisible(false);
                                            content.removeAll();
                                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                                            Menu.setSize(500, 538);
                                            Menu.setTitle("Main Menu");
                                            Menu.setVisible(true);
                                        }

                                    } catch (NumberFormatException l) {
                                        writer.println("Invalid input");
                                        writer.flush();
                                        JOptionPane.showMessageDialog(null,
                                                "Please enter a valid number of questions.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        Menu.setVisible(false);
                                        content.removeAll();
                                        content.add(teacherMenuPanel, BorderLayout.NORTH);
                                        Menu.setSize(500, 538);
                                        Menu.setTitle("Main Menu");
                                        Menu.setVisible(true);
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null,
                                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            });
                        }
                    });
                }
            });

            editQuizRandomYButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("random");
                    writer.flush();

                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(editQuizMenuPanel, BorderLayout.NORTH);
                    Menu.setSize(500, 140);
                    Menu.setTitle("Edit Quiz");
                    Menu.setVisible(true);

                }
            });
            //Works
            editQuizRandomNButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("not");
                    writer.flush();

                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(editQuizMenuPanel, BorderLayout.NORTH);
                    Menu.setSize(500, 140);
                    Menu.setTitle("Edit Quiz");
                    Menu.setVisible(true);

                }
            });
            //Works
            editQuizButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("editQuiz");
                    writer.flush();

                    try {
                        String areCourses = reader.readLine();
                        if (areCourses.equalsIgnoreCase("Courses")) {
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(editRandomizationMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 140);
                            Menu.setTitle("Edit Quiz");
                            Menu.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "There are no courses created.", "Error", JOptionPane.ERROR_MESSAGE);
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 538);
                            Menu.setTitle("Main Menu");
                            Menu.setVisible(true);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
            //Works
            editQuizFromFileButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("file");
                    writer.flush();
                    String courses = "";
                    try {
                        courses = reader.readLine();
                        courses = courses.substring(1, courses.length() - 1);
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null,
                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    String[] courseStrings = courses.split(", ");

                    JComboBox coursesDropDown = new JComboBox(courseStrings);
                    JButton courseToCreateQuizInButton = new JButton("Edit Quiz");
                    JPanel courseToCreateQuizInPanel = new JPanel();
                    courseToCreateQuizInPanel.add(new JLabel("Choose a course to edit a quiz in:"));
                    courseToCreateQuizInPanel.add(coursesDropDown);
                    courseToCreateQuizInPanel.add(courseToCreateQuizInButton);


                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(courseToCreateQuizInPanel, BorderLayout.NORTH);
                    Menu.setSize(700, 78);
                    Menu.setTitle("Edit Quiz");
                    Menu.setVisible(true);

                    courseToCreateQuizInButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            writer.println(coursesDropDown.getSelectedItem());
                            writer.flush();

                            JPanel finalizeCreateQuizFromFilePanel = new JPanel();
                            finalizeCreateQuizFromFilePanel.add(new JLabel("Quiz file path:"));
                            JButton finalizeCreateQuizFromFileButton = new JButton("Edit Quiz");
                            JTextField filePathText = new JTextField(10);
                            finalizeCreateQuizFromFilePanel.add(filePathText);
                            finalizeCreateQuizFromFilePanel.add(finalizeCreateQuizFromFileButton);
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(finalizeCreateQuizFromFilePanel, BorderLayout.NORTH);
                            Menu.setSize(500, 75);
                            Menu.setTitle("Edit Quiz");
                            Menu.setVisible(true);

                            finalizeCreateQuizFromFileButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    writer.println(filePathText.getText());
                                    writer.flush();

                                    try {
                                        boolean exists = reader.readLine().equalsIgnoreCase("Exists");
                                        if (!exists) {
                                            JOptionPane.showMessageDialog(null,
                                                    "This quiz does not exist in this course",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            Menu.setVisible(false);
                                            content.removeAll();
                                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                                            Menu.setSize(500, 538);
                                            Menu.setTitle("Main Menu");
                                            Menu.setVisible(true);
                                        } else {
                                            String result = "";
                                            try {
                                                result = reader.readLine();
                                            } catch (IOException ioException) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                            if (result.equalsIgnoreCase("quizCreated")) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Quiz edit complete.", "Success",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                                Menu.setVisible(false);
                                                content.removeAll();
                                                content.add(teacherMenuPanel, BorderLayout.NORTH);
                                                Menu.setSize(500, 538);
                                                Menu.setTitle("Main Menu");
                                                Menu.setVisible(true);
                                            } else if (result.equalsIgnoreCase("invalidFile")) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Invalid File Path", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                                Menu.setVisible(false);
                                                content.removeAll();
                                                content.add(teacherMenuPanel, BorderLayout.NORTH);
                                                Menu.setSize(500, 538);
                                                Menu.setTitle("Main Menu");
                                                Menu.setVisible(true);
                                            } else if (result.equalsIgnoreCase("already exists")) {
                                                JOptionPane.showMessageDialog(null,
                                                        "This quiz already exists in this course", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                                Menu.setVisible(false);
                                                content.removeAll();
                                                content.add(teacherMenuPanel, BorderLayout.NORTH);
                                                Menu.setSize(500, 538);
                                                Menu.setTitle("Main Menu");
                                                Menu.setVisible(true);
                                            }
                                        }
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null,
                                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            });
                        }
                    });
                }
            });
            //Works
            editQuizManuallyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("manually");
                    writer.flush();
                    String courses = "";
                    try {
                        courses = reader.readLine();
                        courses = courses.substring(1, courses.length() - 1);
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null,
                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    String[] courseStrings = courses.split(", ");

                    JComboBox coursesDropDown = new JComboBox(courseStrings);
                    JButton courseToCreateQuizInButton = new JButton("Edit Quiz In");
                    JPanel courseToCreateQuizInPanel = new JPanel();
                    courseToCreateQuizInPanel.add(new JLabel("Choose a course to edit a quiz in:"));
                    courseToCreateQuizInPanel.add(coursesDropDown);
                    courseToCreateQuizInPanel.add(courseToCreateQuizInButton);

                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(courseToCreateQuizInPanel, BorderLayout.NORTH);
                    Menu.setSize(700, 78);
                    Menu.setTitle("Edit Quiz");
                    Menu.setVisible(true);

                    courseToCreateQuizInButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            writer.println(coursesDropDown.getSelectedItem());
                            writer.flush();

                            counter = -1;
                            ArrayList<String> questions = new ArrayList<String>();
                            ArrayList<Character> answers = new ArrayList<Character>();
                            ArrayList<String> choices = new ArrayList<String>();

                            JLabel nameLabel = new JLabel("Name:");
                            JTextField nameText = new JTextField(10);
                            JLabel lengthLabel = new JLabel("# of Questions:");
                            JTextField lengthText = new JTextField(2);
                            JButton finalCreateQuizButton = new JButton("Create");
                            JPanel finalCreateQuizPanel = new JPanel();
                            finalCreateQuizPanel.add(nameLabel);
                            finalCreateQuizPanel.add(nameText);
                            finalCreateQuizPanel.add(lengthLabel);
                            finalCreateQuizPanel.add(lengthText);
                            finalCreateQuizPanel.add(finalCreateQuizButton);

                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(finalCreateQuizPanel, BorderLayout.NORTH);
                            Menu.setSize(700, 78);
                            Menu.setTitle("Edit Quiz");
                            Menu.setVisible(true);

                            finalCreateQuizButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        int length = Integer.parseInt(lengthText.getText());
                                        boolean doesntExist = true;
                                        if (counter == - 1) {
                                            writer.println("Valid input");
                                            writer.println(nameText.getText());
                                            writer.println(lengthText.getText());
                                            writer.flush();
                                            doesntExist = reader.readLine().equalsIgnoreCase("Qu" +
                                                    "iz does not exist");
                                        }
                                        if (!doesntExist) {
                                            counter++;
                                            saveChecker = 0;
                                            String[] correctChoiceList = {"A", "B", "C", "D"};
                                            JComboBox chooseAnswerDropdown = new JComboBox(correctChoiceList);

                                            JLabel questionLabel = new JLabel("Question:");
                                            JLabel choiceALabel = new JLabel("A:");
                                            JLabel choiceBLabel = new JLabel("B:");
                                            JLabel choiceCLabel = new JLabel("C:");
                                            JLabel choiceDLabel = new JLabel("D:");
                                            JLabel correctAnswerLabel = new JLabel("Correct Answer");
                                            JButton save = new JButton("Save Question");

                                            JTextField questionText = new JTextField(10);
                                            JTextField choiceAText = new JTextField(10);
                                            JTextField choiceBText = new JTextField(10);
                                            JTextField choiceCText = new JTextField(10);
                                            JTextField choiceDText = new JTextField(10);

                                            JPanel quizQuestionPanel = new JPanel();
                                            quizQuestionPanel.setLayout(new BoxLayout(quizQuestionPanel,
                                                    BoxLayout.Y_AXIS));

                                            quizQuestionPanel.add(questionLabel);
                                            quizQuestionPanel.add(questionText);
                                            quizQuestionPanel.add(choiceALabel);
                                            quizQuestionPanel.add(choiceAText);
                                            quizQuestionPanel.add(choiceBLabel);
                                            quizQuestionPanel.add(choiceBText);
                                            quizQuestionPanel.add(choiceCLabel);
                                            quizQuestionPanel.add(choiceCText);
                                            quizQuestionPanel.add(choiceDLabel);
                                            quizQuestionPanel.add(choiceDText);
                                            quizQuestionPanel.add(correctAnswerLabel);
                                            quizQuestionPanel.add(chooseAnswerDropdown);
                                            quizQuestionPanel.add(save);
                                            quizQuestionPanel.add(finalCreateQuizButton);

                                            save.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    if (questionText.getText().equals("") ||
                                                            choiceAText.getText().equals("")
                                                            || choiceBText.getText().equals("")
                                                            || choiceCText.getText().equals("")
                                                            || choiceDText.getText().equals("")) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "Please fill all fields", "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                                        if (counter == 0) {
                                                            counter = 0;
                                                        } else {
                                                            counter--;
                                                        }
                                                        Menu.setVisible(false);
                                                        content.removeAll();
                                                        content.add(quizQuestionPanel, BorderLayout.NORTH);
                                                        Menu.setSize(500, 538);
                                                        Menu.setTitle("Edit Quiz");
                                                        Menu.setVisible(true);
                                                    } else if (saveChecker == 0) {
                                                        questions.add(questionText.getText());
                                                        choices.add(choiceAText.getText());
                                                        choices.add(choiceBText.getText());
                                                        choices.add(choiceCText.getText());
                                                        choices.add(choiceDText.getText());
                                                        answers.add(chooseAnswerDropdown.getSelectedItem().
                                                                toString().charAt(0));
                                                        saveChecker = 1;
                                                    } else {
                                                        questions.set(counter, questionText.getText());
                                                        choices.set((counter * 4) , choiceAText.getText());
                                                        choices.set((counter * 4) + 1, choiceBText.getText());
                                                        choices.set((counter * 4) + 2, choiceCText.getText());
                                                        choices.set((counter * 4) + 3, choiceDText.getText());
                                                        answers.set(counter, chooseAnswerDropdown.getSelectedItem().
                                                                toString().charAt(0));
                                                    }
                                                }
                                            });



                                            if (counter < length) {

                                                choiceAText.setText("");
                                                choiceBText.setText("");
                                                choiceCText.setText("");
                                                choiceDText.setText("");
                                                finalCreateQuizButton.setText("Next");
                                                if (counter == length) {
                                                    finalCreateQuizButton.setText("Return to Menu");
                                                }


                                                Menu.setVisible(false);
                                                content.removeAll();
                                                content.add(quizQuestionPanel, BorderLayout.NORTH);
                                                Menu.setSize(500, 538);
                                                Menu.setTitle("Edit Quiz");
                                                Menu.setVisible(true);


                                                if (counter != 0) {

                                                }
                                            } else {
                                                for (int i = 0; i < length; i++) {
                                                    try {
                                                        writer.println(questions.get(i));
                                                        writer.println(choices.get((i * 4)));
                                                        writer.println(choices.get((i * 4) + 1));
                                                        writer.println(choices.get((i * 4) + 2));
                                                        writer.println(choices.get((i * 4) + 3));
                                                        writer.println(answers.get(i).toString());
                                                        writer.flush();
                                                    } catch (IndexOutOfBoundsException k) {
                                                        writer.println("Index Error");
                                                        writer.flush();
                                                    }
                                                }

                                                boolean didTheySave;
                                                didTheySave = reader.readLine().equalsIgnoreCase("saved");
                                                if (didTheySave) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Quiz edit complete.", "Success",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    Menu.setVisible(false);
                                                    content.removeAll();
                                                    content.add(teacherMenuPanel, BorderLayout.NORTH);
                                                    Menu.setSize(500, 538);
                                                    Menu.setTitle("Main Menu");
                                                    Menu.setVisible(true);
                                                } else {
                                                    JOptionPane.showMessageDialog(null,
                                                            "You did not save a question correctly",
                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                    Menu.setVisible(false);
                                                    content.removeAll();
                                                    content.add(teacherMenuPanel, BorderLayout.NORTH);
                                                    Menu.setSize(500, 538);
                                                    Menu.setTitle("Main Menu");
                                                    Menu.setVisible(true);
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null,
                                                    "This quiz does not exist in this course",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            Menu.setVisible(false);
                                            content.removeAll();
                                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                                            Menu.setSize(500, 538);
                                            Menu.setTitle("Main Menu");
                                            Menu.setVisible(true);
                                        }
                                    } catch (NumberFormatException l) {
                                        writer.println("Invalid input");
                                        writer.flush();
                                        JOptionPane.showMessageDialog(null,
                                                "Please enter a valid number of questions.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        Menu.setVisible(false);
                                        content.removeAll();
                                        content.add(teacherMenuPanel, BorderLayout.NORTH);
                                        Menu.setSize(500, 538);
                                        Menu.setTitle("Main Menu");
                                        Menu.setVisible(true);
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null,
                                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            });
                        }
                    });
                }
            });

            takeQuizButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("takeQuiz");
                    writer.flush();
                    boolean noCourses = false;
                    String coursesOrNot = "";
                    String courses = "";
                    try {
                        coursesOrNot = reader.readLine();
                        if (coursesOrNot.equalsIgnoreCase("noCourses")) {
                            JOptionPane.showMessageDialog(null,
                                    "There are no courses available.", "Error", JOptionPane.ERROR_MESSAGE);
                            noCourses = true;
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(studentMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 238);
                            Menu.setTitle("Main Menu");
                            Menu.setVisible(true);
                        }
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null,
                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!noCourses) {
                        try {
                            courses = reader.readLine();
                            courses = courses.substring(1, courses.length() - 1);
                        } catch (IOException ioException) {
                            JOptionPane.showMessageDialog(null,
                                    "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                    String[] courseStrings = courses.split(", ");
                    JComboBox coursesDropDown = new JComboBox(courseStrings);
                    JButton courseToTakeQuizzesInButton = new JButton("View Quizzes Available");
                    JPanel courseToTakeQuizzesInPanel = new JPanel();
                    courseToTakeQuizzesInPanel.add(new JLabel("Choose a course to take quizzes from:"));
                    courseToTakeQuizzesInPanel.add(coursesDropDown);
                    courseToTakeQuizzesInPanel.add(courseToTakeQuizzesInButton);

                    if(!noCourses) {
                        Menu.setVisible(false);
                        content.removeAll();
                        content.add(courseToTakeQuizzesInPanel, BorderLayout.NORTH);
                        Menu.setSize(500, 78);
                        Menu.setTitle("Take Quiz");
                        Menu.setVisible(true);
                    }

                    courseToTakeQuizzesInButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            writer.println(coursesDropDown.getSelectedItem());
                            writer.flush();
                            boolean noQuizzes = false;
                            String quizzes = "";
                            try {
                                quizzes = reader.readLine();
                                if (quizzes.equalsIgnoreCase("noQuizzes")) {
                                    JOptionPane.showMessageDialog(null,
                                            "There are no quizzes to take in this course.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    noQuizzes = true;
                                    Menu.setVisible(false);
                                    content.removeAll();
                                    content.add(studentMenuPanel, BorderLayout.NORTH);
                                    Menu.setSize(500, 238);
                                    Menu.setTitle("Main Menu");
                                    Menu.setVisible(true);
                                }
                                quizzes = quizzes.substring(1, quizzes.length() - 1);
                            } catch (IOException ioException) {
                                JOptionPane.showMessageDialog(null,
                                        "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            String[] quizStrings = quizzes.split(", ");
                            counter = -1;

                            JComboBox quizDropDown = new JComboBox(quizStrings);
                            JButton finalTakeQuizButton = new JButton("Take");
                            JPanel finalTakeQuizPanel = new JPanel();
                            finalTakeQuizPanel.add(new JLabel("Choose a quiz to take:"));
                            finalTakeQuizPanel.add(quizDropDown);
                            finalTakeQuizPanel.add(finalTakeQuizButton);

                            ArrayList<String> questions = new ArrayList<>();
                            ArrayList<String> choices = new ArrayList<>();
                            ArrayList<Character> answers = new ArrayList<>();
                            ArrayList<Character> studentAnswers = new ArrayList<>();
                            if (!noQuizzes) {
                                Menu.setVisible(false);
                                content.removeAll();
                                content.add(finalTakeQuizPanel, BorderLayout.NORTH);
                                Menu.setSize(500, 78);
                                Menu.setTitle("Take Quiz");
                                Menu.setVisible(true);
                            }
                            finalTakeQuizButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {

                                    if (counter == - 1) {
                                        writer.println(quizDropDown.getSelectedItem());
                                        writer.flush();
                                    }
                                    boolean taken = false;
                                    String quizDetails = "";
                                    String isSent = "";
                                    String hasStudentTaken = "";
                                    if (counter == -1) {
                                        try {
                                            quizDetails = reader.readLine();
                                            isSent = reader.readLine();
                                            hasStudentTaken = reader.readLine();
                                        } catch (IOException ioException) {
                                            JOptionPane.showMessageDialog(null,
                                                    "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        String[] quizArray = quizDetails.substring(0,
                                                quizDetails.length() - 1).split(",");
                                        int questionCount = (quizArray.length) / 5;
                                        String choiceList = "";
                                        for (int i = 1; i < quizArray.length; i++) {
                                            if ((i - 1) % 5 == 0) {
                                                questions.add(quizArray[i].substring(0, quizArray[i].length() -2));
                                                answers.add(quizArray[i].substring(quizArray[i].length() - 1,
                                                        quizArray[i].length()).charAt(0));

                                            } else {
                                                choices.add(quizArray[i]);

                                            }
                                        }
                                        if (hasStudentTaken.equalsIgnoreCase("has taken")) {
                                            JOptionPane.showMessageDialog(null,
                                                    "You have already taken this quiz", "Error",
                                                    JOptionPane.ERROR_MESSAGE);
                                            taken = true;
                                            Menu.setVisible(false);
                                            content.removeAll();
                                            content.add(studentMenuPanel, BorderLayout.NORTH);
                                            Menu.setSize(500, 238);
                                            Menu.setTitle("Main Menu");
                                            Menu.setVisible(true);
                                        }
                                    }
                                    if (isSent.equalsIgnoreCase("sent")) {
                                        Menu.setVisible(false);
                                        content.removeAll();
                                        content.add(studentMenuPanel, BorderLayout.NORTH);
                                        Menu.setSize(500, 238);
                                        Menu.setTitle("Main Menu");
                                        Menu.setVisible(true);
                                    } else if (isSent.equalsIgnoreCase("sent") && counter == -1) {
                                        JOptionPane.showMessageDialog(null,
                                                "Unable to view this quiz", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                    }

                                    if (!taken) {
                                        JLabel[] questionListLabel = new JLabel[questions.size()];
                                        JLabel[] choiceListLabel = new JLabel[choices.size()];
                                        JButton save = new JButton("Save Question");
                                        JPanel quizQuestionPanel = new JPanel();
                                        quizQuestionPanel.setLayout(new BoxLayout(quizQuestionPanel, BoxLayout.Y_AXIS));
                                        if (counter < questions.size() - 1) {
                                            counter++;
                                            saveChecker = 0;
                                            ButtonGroup buttonGroup = new ButtonGroup();
                                            JRadioButton choiceArb = new JRadioButton(choices.get(counter * 4));
                                            JRadioButton choiceBrb = new JRadioButton(choices.get((counter * 4) + 1));
                                            JRadioButton choiceCrb = new JRadioButton(choices.get((counter * 4) + 2));
                                            JRadioButton choiceDrb = new JRadioButton(choices.get((counter * 4) + 3));
                                            buttonGroup.add(choiceArb);
                                            buttonGroup.add(choiceBrb);
                                            buttonGroup.add(choiceCrb);
                                            buttonGroup.add(choiceDrb);

                                            quizQuestionPanel.removeAll();

                                            questionListLabel[counter] = new JLabel(questions.get(counter));
                                            questionListLabel[counter].setAlignmentX(Component.CENTER_ALIGNMENT);
                                            questionListLabel[counter].setFont(new Font("Serif", Font.PLAIN,
                                                    30));
                                            quizQuestionPanel.add(questionListLabel[counter]);

                                            choiceArb.setAlignmentX(Component.CENTER_ALIGNMENT);
                                            quizQuestionPanel.add(choiceArb);

                                            choiceBrb.setAlignmentX(Component.CENTER_ALIGNMENT);
                                            quizQuestionPanel.add(choiceBrb);

                                            choiceCrb.setAlignmentX(Component.CENTER_ALIGNMENT);
                                            quizQuestionPanel.add(choiceCrb);

                                            choiceDrb.setAlignmentX(Component.CENTER_ALIGNMENT);
                                            quizQuestionPanel.add(choiceDrb);

                                            finalTakeQuizButton.setText("Next");
                                            if (counter == questions.size() - 1) {
                                                finalTakeQuizButton.setText("Return to Menu");
                                            }
                                            quizQuestionPanel.add(save);
                                            quizQuestionPanel.add(finalTakeQuizButton);

                                            save.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    if (!choiceArb.isSelected() && !choiceBrb.isSelected()
                                                            && !choiceCrb.isSelected()
                                                            && !choiceDrb.isSelected()) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "Please select an Answer",
                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                        if (counter == 0) {
                                                            counter = 0;
                                                        } else {
                                                            counter--;
                                                        }
                                                        Menu.setVisible(false);
                                                        content.removeAll();
                                                        content.add(quizQuestionPanel, BorderLayout.NORTH);
                                                        Menu.setSize(500, 538);
                                                        Menu.setTitle("Take Quiz");
                                                        Menu.setVisible(true);
                                                    } else if (saveChecker == 0) {
                                                        if (choiceArb.isSelected()) {
                                                            studentAnswers.add('A');
                                                        }
                                                        if (choiceBrb.isSelected()) {
                                                            studentAnswers.add('B');
                                                        }
                                                        if (choiceCrb.isSelected()) {
                                                            studentAnswers.add('C');
                                                        }
                                                        if (choiceDrb.isSelected()) {
                                                            studentAnswers.add('D');
                                                        }
                                                        saveChecker = 1;
                                                    } else {
                                                        if (choiceArb.isSelected()) {
                                                            studentAnswers.set(counter,'A');
                                                        }
                                                        if (choiceBrb.isSelected()) {
                                                            studentAnswers.set(counter,'B');
                                                        }
                                                        if (choiceCrb.isSelected()) {
                                                            studentAnswers.set(counter,'C');
                                                        }
                                                        if (choiceDrb.isSelected()) {
                                                            studentAnswers.set(counter,'D');
                                                        }
                                                    }
                                                }
                                            });
                                            Menu.setVisible(false);
                                            content.removeAll();
                                            content.add(quizQuestionPanel, BorderLayout.NORTH);
                                            Menu.setSize(500, 538);
                                            Menu.setTitle("Take Quiz");
                                            Menu.setVisible(true);


                                            //get answer and send it for each choice

                                        } else {
                                            String answerString = "";
                                            for (char c: studentAnswers) {
                                                answerString = answerString + String.valueOf(c);
                                            }
                                            writer.println(answerString);
                                            writer.flush();
                                            boolean didTheySave;
                                            try {
                                                didTheySave = reader.readLine().equalsIgnoreCase("saved");
                                                if (didTheySave) {

                                                    Menu.setVisible(false);
                                                    content.removeAll();
                                                    content.add(studentMenuPanel, BorderLayout.NORTH);
                                                    Menu.setSize(500, 238);
                                                    Menu.setTitle("Main Menu");
                                                    Menu.setVisible(true);
                                                } else {
                                                    JOptionPane.showMessageDialog(null,
                                                            "You did not save a question correctly",
                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                    Menu.setVisible(false);
                                                    content.removeAll();
                                                    content.add(studentMenuPanel, BorderLayout.NORTH);
                                                    Menu.setSize(500, 238);
                                                    Menu.setTitle("Main Menu");
                                                    Menu.setVisible(true);
                                                }
                                            } catch (IOException ex) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    });
                }
            });

            returnToMenuButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Menu.setVisible(false);
                    content.removeAll();
                    if (role.equalsIgnoreCase("teacher")) {
                        content.add(teacherMenuPanel, BorderLayout.NORTH);
                    } else {
                        content.add(studentMenuPanel, BorderLayout.NORTH);
                    }
                    Menu.setSize(500, 538);
                    Menu.setTitle("Main Menu");
                    Menu.setVisible(true);

                }
            });
            deleteAccountButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("deleteAccount");
                    writer.flush();

                    JButton confirmDeletionButton = new JButton("Confirm");
                    JLabel confirmDeletionLabel = new JLabel("Confirm Deletion:");
                    JButton cancelDeletionButton = new JButton("Cancel");
                    JPanel confirmDeletionPanel = new JPanel();
                    confirmDeletionPanel.add(confirmDeletionLabel);
                    confirmDeletionPanel.add(confirmDeletionButton);
                    confirmDeletionPanel.add(cancelDeletionButton);

                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(confirmDeletionPanel, BorderLayout.NORTH);
                    Menu.setSize(500, 78);
                    Menu.setTitle("Delete Account");
                    Menu.setVisible(true);

                    cancelDeletionButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(userSettingsMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 238);
                            Menu.setTitle("User Settings");
                            Menu.setVisible(true);

                        }
                    });
                    confirmDeletionButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null,
                                    "Account deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(roleSelectionMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 138);
                            Menu.setTitle("Role Selection Menu");
                            Menu.setLocationRelativeTo(null);
                            Menu.setVisible(true);
                            passwordLogInText.setText("");
                            passwordSignUpText.setText("");
                            usernameLogInText.setText("");
                            usernameSignUpText.setText("");
                        }
                    });
                }
            });

            deleteCoursesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("deleteCourse");
                    writer.flush();
                    String courses = "";

                    boolean areCourses = true;
                    try {
                        courses = reader.readLine();
                        if (courses.equalsIgnoreCase("noCourses")) {
                            JOptionPane.showMessageDialog(null,
                                    "There are no courses to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 538);
                            Menu.setTitle("Main Menu");
                            Menu.setVisible(true);
                            areCourses = false;
                        }
                        courses = courses.substring(1, courses.length() - 1);
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null,
                                "ERROR!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    String[] courseStrings = courses.split(", ");

                    JComboBox coursesDropDown = new JComboBox(courseStrings);
                    JButton deleteCourseFinalizeButton = new JButton("Delete");
                    JPanel deleteCoursePanel = new JPanel();
                    deleteCoursePanel.add(new JLabel("Choose a course to delete:"));
                    deleteCoursePanel.add(coursesDropDown);
                    deleteCoursePanel.add(deleteCourseFinalizeButton);

                    if (areCourses) {
                        Menu.setVisible(false);
                        content.removeAll();
                        content.add(deleteCoursePanel, BorderLayout.NORTH);
                        Menu.setSize(500, 78);
                        Menu.setTitle("Delete a Course");
                        Menu.setVisible(true);
                    }

                    deleteCourseFinalizeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            writer.println(coursesDropDown.getSelectedItem());
                            writer.flush();
                            String isDeleted = "";
                            try {
                                isDeleted = reader.readLine();
                            } catch (IOException ioException) {
                                JOptionPane.showMessageDialog(null,
                                        "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            Menu.setVisible(false);
                            content.removeAll();
                            if (isDeleted.equalsIgnoreCase("deleted")) {
                                JOptionPane.showMessageDialog(null,
                                        "Course deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                content.add(teacherMenuPanel, BorderLayout.NORTH);
                                Menu.setSize(500, 538);
                                Menu.setTitle("Main Menu");
                                Menu.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Unable to delete course.", "Error", JOptionPane.ERROR_MESSAGE);
                                content.add(deleteCoursePanel, BorderLayout.NORTH);
                                Menu.setSize(500, 78);
                                Menu.setTitle("Delete a Course");
                            }
                            Menu.setVisible(true);
                        }
                    });
                }
            });

            viewQuizzesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("viewQuiz");
                    writer.flush();
                    boolean noCourses = false;
                    String coursesOrNot = "";
                    String courses = "";
                    try {
                        coursesOrNot = reader.readLine();
                        if (coursesOrNot.equalsIgnoreCase("noCourses")) {
                            JOptionPane.showMessageDialog(null,
                                    "There are no courses to view quizzes from.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            noCourses= true;
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 538);
                            Menu.setTitle("Main Menu");
                            Menu.setVisible(true);
                        }

                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null,
                                "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    ArrayList<String> courseStringsList = new ArrayList<String>();

                    if (!noCourses) {
                        try {
                            courses = reader.readLine();
                        } catch (IOException ioException) {
                            JOptionPane.showMessageDialog(null,
                                    "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        courses = courses.substring(1, courses.length() - 1);
                        String[] courseStringsArray = courses.split(", ");
                        for (String s: courseStringsArray) {
                            courseStringsList.add(s);
                        }
                    }
                    String[] courseStrings = courseStringsList.toArray(new String[0]);
                    JComboBox coursesDropDown = new JComboBox(courseStrings);
                    JButton courseToViewQuizzesInButton = new JButton("View Quizzes");
                    JPanel courseToViewQuizzesInPanel = new JPanel();
                    courseToViewQuizzesInPanel.add(new JLabel("Choose a course to see quizzes from:"));
                    courseToViewQuizzesInPanel.add(coursesDropDown);
                    courseToViewQuizzesInPanel.add(courseToViewQuizzesInButton);

                    if (!noCourses) {
                        Menu.setVisible(false);
                        content.removeAll();
                        content.add(courseToViewQuizzesInPanel, BorderLayout.NORTH);
                        Menu.setSize(500, 78);
                        Menu.setTitle("View Quiz");
                        Menu.setVisible(true);
                    }

                    courseToViewQuizzesInButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            writer.println(coursesDropDown.getSelectedItem());
                            writer.flush();
                            boolean noQuizzes = false;
                            String quizzes = "";
                            try {
                                quizzes = reader.readLine();
                                if (quizzes.equalsIgnoreCase("noQuizzes")) {
                                    JOptionPane.showMessageDialog(null,
                                            "There are no quizzes to view.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    noQuizzes = true;
                                    Menu.setVisible(false);
                                    content.removeAll();
                                    content.add(teacherMenuPanel, BorderLayout.NORTH);
                                    Menu.setSize(500, 538);
                                    Menu.setTitle("Main Menu");
                                    Menu.setVisible(true);
                                }
                                quizzes = quizzes.substring(1, quizzes.length() - 1);
                            } catch (IOException ioException) {
                                JOptionPane.showMessageDialog(null,
                                        "ERROR!!!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            String[] quizStrings = quizzes.split(", ");
                            counter = -1;

                            JComboBox quizDropDown = new JComboBox(quizStrings);
                            JButton finalViewQuizzesButton = new JButton("View");
                            JPanel finalViewQuizzesPanel = new JPanel();
                            finalViewQuizzesPanel.add(new JLabel("Choose a quiz to view:"));
                            finalViewQuizzesPanel.add(quizDropDown);
                            finalViewQuizzesPanel.add(finalViewQuizzesButton);

                            ArrayList<String> questions = new ArrayList<>();
                            ArrayList<String> choices = new ArrayList<>();

                            if (!noQuizzes) {
                                Menu.setVisible(false);
                                content.removeAll();
                                content.add(finalViewQuizzesPanel, BorderLayout.NORTH);
                                Menu.setSize(500, 78);
                                Menu.setTitle("Quiz");
                                Menu.setVisible(true);
                            }

                            finalViewQuizzesButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    writer.println(quizDropDown.getSelectedItem());
                                    writer.flush();

                                    String quizDetails = "";
                                    String isSent = "";
                                    if (counter == -1) {
                                        try {
                                            quizDetails = reader.readLine();
                                            isSent = reader.readLine();
                                        } catch (IOException ioException) {
                                            JOptionPane.showMessageDialog(null,
                                                    "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        String[] quizArray = quizDetails.substring(0,
                                                quizDetails.length() - 1).split(",");
                                        int questionCount = (quizArray.length) / 5;
                                        String choiceList = "";
                                        for (int i = 1; i < quizArray.length; i++) {
                                            if ((i - 1) % 5 == 0) {
                                                questions.add(quizArray[i].substring(0, quizArray[i].length()));
                                            } else {
                                                choices.add(quizArray[i]);

                                            }
                                        }

                                    }
                                    if (isSent.equalsIgnoreCase("sent")) {
                                        Menu.setVisible(false);
                                        content.removeAll();
                                        content.add(teacherMenuPanel, BorderLayout.NORTH);
                                        Menu.setSize(750, 550);
                                        Menu.setTitle("Main Menu");
                                        Menu.setVisible(true);
                                    } else if (isSent.equalsIgnoreCase("sent") && counter == -1) {
                                        JOptionPane.showMessageDialog(null,
                                                "Unable to view this quiz", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                    JLabel[] questionListLabel = new JLabel[questions.size() + 1];
                                    JLabel[] choiceListLabel = new JLabel[4];
                                    JPanel quizQuestionPanel = new JPanel();
                                    quizQuestionPanel.setLayout(new BoxLayout(quizQuestionPanel, BoxLayout.Y_AXIS));

                                    if (counter < questions.size() - 1) {
                                        quizQuestionPanel.removeAll();
                                        counter++;
                                        questionListLabel[counter] = new JLabel(questions.get(counter));
                                        questionListLabel[counter].setAlignmentX(Component.LEFT_ALIGNMENT);
                                        questionListLabel[counter].setFont(new Font("Serif",
                                                Font.PLAIN, 22));
                                        quizQuestionPanel.add(questionListLabel[counter]);


                                        choiceListLabel[0] = new JLabel(choices.get(counter * 4));
                                        choiceListLabel[0].setAlignmentX(Component.LEFT_ALIGNMENT);
                                        quizQuestionPanel.add(choiceListLabel[0]);

                                        choiceListLabel[1] = new JLabel(choices.get((counter * 4) + 1));
                                        choiceListLabel[1].setAlignmentX(Component.LEFT_ALIGNMENT);
                                        quizQuestionPanel.add(choiceListLabel[1]);

                                        choiceListLabel[2] = new JLabel(choices.get((counter * 4) + 2));
                                        choiceListLabel[2].setAlignmentX(Component.LEFT_ALIGNMENT);
                                        quizQuestionPanel.add(choiceListLabel[2]);

                                        choiceListLabel[3] = new JLabel(choices.get((counter * 4) + 3));
                                        choiceListLabel[3].setAlignmentX(Component.LEFT_ALIGNMENT);
                                        quizQuestionPanel.add(choiceListLabel[3]);

                                        finalViewQuizzesButton.setText("Next");
                                        if (counter == questions.size() - 1) {
                                            finalViewQuizzesButton.setText("return to Menu");
                                        }
                                        quizQuestionPanel.add(finalViewQuizzesButton);

                                        Menu.setVisible(false);
                                        content.removeAll();
                                        content.add(quizQuestionPanel, BorderLayout.NORTH);
                                        Menu.setSize(500, 538);
                                        Menu.setTitle("View Quiz");
                                        Menu.setVisible(true);
                                    } else {
                                        Menu.setVisible(false);
                                        content.removeAll();
                                        content.add(teacherMenuPanel, BorderLayout.NORTH);
                                        Menu.setSize(500, 538);
                                        Menu.setTitle("Main Menu");
                                        Menu.setVisible(true);
                                    }
                                }
                            });
                        }
                    });
                }
            });

            deleteQuizzesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("deleteQuiz");
                    writer.flush();
                    String courses = "";
                    boolean areCourses = true;
                    try {
                        courses = reader.readLine();
                        if (courses.equalsIgnoreCase("noCourses")) {
                            JOptionPane.showMessageDialog(null,
                                    "There are no courses.", "Error", JOptionPane.ERROR_MESSAGE);
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 538);
                            Menu.setTitle("Main Menu");
                            Menu.setVisible(true);
                            areCourses = false;
                        }
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null,
                                "ERROR!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    courses = courses.substring(1, courses.length() - 1);
                    String[] courseStrings = courses.split(", ");


                    JComboBox coursesDropDown = new JComboBox(courseStrings);
                    JButton courseToDeleteQuizzesInButton = new JButton("choose course");
                    JPanel courseToDeleteQuizzesInPanel = new JPanel();
                    courseToDeleteQuizzesInPanel.add(new JLabel("Choose a course to delete quizzes from:"));
                    courseToDeleteQuizzesInPanel.add(coursesDropDown);
                    courseToDeleteQuizzesInPanel.add(courseToDeleteQuizzesInButton);

                    if (areCourses) {
                        Menu.setVisible(false);
                        content.removeAll();
                        content.add(courseToDeleteQuizzesInPanel, BorderLayout.NORTH);
                        Menu.setSize(500, 78);
                        Menu.setTitle("Delete Quiz");
                        Menu.setVisible(true);
                    }

                    courseToDeleteQuizzesInButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            writer.println(coursesDropDown.getSelectedItem());
                            writer.flush();
                            boolean areQuizzes = true;
                            String quizzes = "";
                            try {
                                quizzes = reader.readLine();
                                if (quizzes.equalsIgnoreCase("noQuizzes")) {
                                    JOptionPane.showMessageDialog(null,
                                            "There are no quizzes to delete in this course.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    Menu.setVisible(false);
                                    content.removeAll();
                                    content.add(teacherMenuPanel, BorderLayout.NORTH);
                                    Menu.setSize(500, 538);
                                    Menu.setTitle("Main Menu");
                                    Menu.setVisible(true);
                                    areQuizzes = false;
                                }
                                quizzes = quizzes.substring(1, quizzes.length() - 1);
                            } catch (IOException ioException) {
                                JOptionPane.showMessageDialog(null,
                                        "ERROR!!!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            String[] quizStrings = quizzes.split(", ");

                            JComboBox quizDropDown = new JComboBox(quizStrings);
                            JButton finalDeleteQuizzesButton = new JButton("Delete");
                            JPanel finalDeleteQuizzesPanel = new JPanel();
                            finalDeleteQuizzesPanel.add(new JLabel("Choose a quiz to Delete:"));
                            finalDeleteQuizzesPanel.add(quizDropDown);
                            finalDeleteQuizzesPanel.add(finalDeleteQuizzesButton);
                            if(areQuizzes) {
                                Menu.setVisible(false);
                                content.removeAll();
                                content.add(finalDeleteQuizzesPanel, BorderLayout.NORTH);
                                Menu.setSize(500, 78);
                                Menu.setTitle("Delete Quiz");
                                Menu.setVisible(true);
                            }

                            finalDeleteQuizzesButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    writer.println(quizDropDown.getSelectedItem());
                                    writer.flush();

                                    JOptionPane.showMessageDialog(null,
                                            "Quiz deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    Menu.setVisible(false);
                                    content.removeAll();
                                    content.add(teacherMenuPanel, BorderLayout.NORTH);
                                    Menu.setSize(500, 538);
                                    Menu.setTitle("Main Menu");
                                    Menu.setVisible(true);

                                }
                            });
                        }
                    });
                }
            });

            viewCoursesTeacherButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("viewCoursesTeacher");
                    writer.flush();
                    String coursesOrNot = "";
                    String courses = "";
                    boolean areCourses = true;
                    try {
                        coursesOrNot = reader.readLine();
                        if (coursesOrNot.equalsIgnoreCase("noCourses")) {
                            JOptionPane.showMessageDialog(null,
                                    "There are no courses to view.", "Error", JOptionPane.ERROR_MESSAGE);
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 538);
                            Menu.setTitle("Main Menu");
                            Menu.setVisible(true);
                            areCourses= false;
                        }
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null,
                                "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (areCourses) {
                        try {
                            courses = reader.readLine();
                            courses = courses.substring(1, courses.length() - 1);
                        } catch (IOException ioException) {
                            JOptionPane.showMessageDialog(null,
                                    "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        String[] courseStrings = courses.split(", ");

                        JLabel[] classListLabel = new JLabel[courseStrings.length];
                        JButton localReturnToMenuButton = new JButton("Return to Menu");
                        JPanel classListPanel = new JPanel();
                        classListPanel.setBackground(Color.WHITE);
                        classListPanel.setLayout(new BoxLayout(classListPanel, BoxLayout.Y_AXIS));
                        for (int i = 0; i < classListLabel.length; i++) {
                            classListLabel[i] = new JLabel((i + 1) + ": " + courseStrings[i]);
                            classListLabel[i].setAlignmentX(0.1f);
                            classListLabel[i].setFont(new Font("Serif", Font.PLAIN, 30));
                            classListPanel.add(classListLabel[i]);
                        }
                        classListPanel.add(localReturnToMenuButton);

                        Menu.setVisible(false);
                        content.add(classListPanel, BorderLayout.NORTH);
                        content.remove(teacherMenuPanel);
                        Menu.setSize(500, (30 * classListLabel.length) + 70);
                        Menu.setTitle("Courses");
                        Menu.setVisible(true);

                        localReturnToMenuButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Menu.setVisible(false);
                                content.removeAll();
                                content.add(teacherMenuPanel, BorderLayout.NORTH);
                                Menu.setSize(500, 538);
                                Menu.setTitle("Main Menu");
                                Menu.setVisible(true);

                            }
                        });
                    }
                }
            });

            viewCoursesStudentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("viewCoursesStudent");
                    writer.flush();
                    String coursesOrNot = "";
                    String courses = "";
                    boolean areCourses = true;
                    try {
                        coursesOrNot = reader.readLine();
                        if (coursesOrNot.equalsIgnoreCase("noCourses")) {
                            JOptionPane.showMessageDialog(null,
                                    "There are no courses to view.", "Error", JOptionPane.ERROR_MESSAGE);
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(studentMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 238);
                            Menu.setTitle("Main Menu");
                            Menu.setVisible(true);
                            areCourses= false;
                        }
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null,
                                "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (areCourses) {
                        try {
                            courses = reader.readLine();
                            courses = courses.substring(1, courses.length() - 1);
                        } catch (IOException ioException) {
                            JOptionPane.showMessageDialog(null,
                                    "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        String[] courseStrings = courses.split(", ");

                        JLabel[] classListLabel = new JLabel[courseStrings.length];
                        JButton localReturnToMenuButton = new JButton("Return to Menu");
                        JPanel classListPanel = new JPanel();
                        classListPanel.setBackground(Color.WHITE);
                        classListPanel.setLayout(new BoxLayout(classListPanel, BoxLayout.Y_AXIS));
                        for (int i = 0; i < classListLabel.length; i++) {
                            classListLabel[i] = new JLabel((i + 1) + ": " + courseStrings[i]);
                            classListLabel[i].setAlignmentX(0.1f);
                            classListLabel[i].setFont(new Font("Serif", Font.PLAIN, 30));
                            classListPanel.add(classListLabel[i]);
                        }
                        classListPanel.add(localReturnToMenuButton);

                        Menu.setVisible(false);
                        content.add(classListPanel, BorderLayout.NORTH);
                        content.remove(studentMenuPanel);
                        Menu.setSize(500, (30 * classListLabel.length) + 70);
                        Menu.setTitle("Courses");
                        Menu.setVisible(true);

                        localReturnToMenuButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Menu.setVisible(false);
                                content.removeAll();
                                content.add(studentMenuPanel, BorderLayout.NORTH);
                                Menu.setSize(500, 538);
                                Menu.setTitle("Main Menu");
                                Menu.setVisible(true);

                            }
                        });
                    }
                }
            });

            viewQuizSubmissionsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("viewQuizSubmissions");
                    writer.flush();
                    boolean noCourses = false;
                    String coursesOrNot = "";
                    String courses = "";
                    try {
                        coursesOrNot = reader.readLine();
                        if (coursesOrNot.equalsIgnoreCase("noCourses")) {
                            JOptionPane.showMessageDialog(null,
                                    "There are no courses to view quizzes from.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            noCourses= true;
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(teacherMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 538);
                            Menu.setTitle("Main Menu");
                            Menu.setVisible(true);
                        }

                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null,
                                "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    ArrayList<String> courseStringsList = new ArrayList<String>();

                    if (!noCourses) {
                        try {
                            courses = reader.readLine();
                        } catch (IOException ioException) {
                            JOptionPane.showMessageDialog(null,
                                    "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        courses = courses.substring(1, courses.length() - 1);
                        String[] courseStringsArray = courses.split(", ");
                        for (String s: courseStringsArray) {
                            courseStringsList.add(s);
                        }
                    }
                    String[] courseStrings = courseStringsList.toArray(new String[0]);
                    JComboBox coursesDropDown = new JComboBox(courseStrings);
                    JButton courseToViewQuizzesInButton = new JButton("View Quizzes");
                    JPanel courseToViewQuizzesInPanel = new JPanel();
                    courseToViewQuizzesInPanel.add(new JLabel("Choose a course to see quizzes from:"));
                    courseToViewQuizzesInPanel.add(coursesDropDown);
                    courseToViewQuizzesInPanel.add(courseToViewQuizzesInButton);

                    if (!noCourses) {
                        Menu.setVisible(false);
                        content.removeAll();
                        content.add(courseToViewQuizzesInPanel, BorderLayout.NORTH);
                        Menu.setSize(500, 78);
                        Menu.setTitle("View Quiz Submissions");
                        Menu.setVisible(true);
                    }

                    courseToViewQuizzesInButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            writer.println(coursesDropDown.getSelectedItem());
                            writer.flush();
                            boolean noQuizzes = false;
                            String quizzes = "";
                            try {
                                quizzes = reader.readLine();
                                if (quizzes.equalsIgnoreCase("noQuizzes")) {
                                    JOptionPane.showMessageDialog(null,
                                            "There are no quizzes to view.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    noQuizzes = true;
                                    Menu.setVisible(false);
                                    content.removeAll();
                                    content.add(teacherMenuPanel, BorderLayout.NORTH);
                                    Menu.setSize(500, 538);
                                    Menu.setTitle("Main Menu");
                                    Menu.setVisible(true);
                                }
                                quizzes = quizzes.substring(1, quizzes.length() - 1);
                            } catch (IOException ioException) {
                                JOptionPane.showMessageDialog(null,
                                        "ERROR!!!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            String[] quizStrings = quizzes.split(", ");
                            counter = -1;

                            JComboBox quizDropDown = new JComboBox(quizStrings);
                            JButton finalViewQuizzesButton = new JButton("View");
                            JPanel finalViewQuizzesPanel = new JPanel();
                            finalViewQuizzesPanel.add(new JLabel("Choose a quiz to view:"));
                            finalViewQuizzesPanel.add(quizDropDown);
                            finalViewQuizzesPanel.add(finalViewQuizzesButton);

                            ArrayList<String> questions = new ArrayList<>();
                            ArrayList<String> choices = new ArrayList<>();

                            if (!noQuizzes) {
                                Menu.setVisible(false);
                                content.removeAll();
                                content.add(finalViewQuizzesPanel, BorderLayout.NORTH);
                                Menu.setSize(500, 78);
                                Menu.setTitle("View Quiz Submissions");
                                Menu.setVisible(true);
                            }

                            finalViewQuizzesButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    writer.println(quizDropDown.getSelectedItem());
                                    writer.flush();
                                    boolean noSubmissions = false;
                                    String areSubmissions = "";
                                    try {
                                        areSubmissions = reader.readLine();
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null,
                                                "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    noSubmissions = !areSubmissions.equalsIgnoreCase("has Submissions");
                                    if (noSubmissions) {
                                        JOptionPane.showMessageDialog(null,
                                                "There are no submissions for this quiz to view.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        Menu.setVisible(false);
                                        content.removeAll();
                                        content.add(teacherMenuPanel, BorderLayout.NORTH);
                                        Menu.setSize(500, 538);
                                        Menu.setTitle("Main Menu");
                                        Menu.setVisible(true);
                                    } else {
                                        String studentNames = "";
                                        try {
                                            studentNames = reader.readLine();
                                        } catch (IOException ex) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        String[] submissions = studentNames.substring(1, studentNames.length()
                                                - 1).split(", ");
                                        JComboBox submissionsDropDown = new JComboBox(submissions);
                                        JButton submissionsToViewButton = new JButton("View Submissions");
                                        JPanel submissionsToViewPanel = new JPanel();
                                        submissionsToViewPanel.add(new JLabel("Choose a submissions to view"));
                                        submissionsToViewPanel.add(submissionsDropDown);
                                        submissionsToViewPanel.add(submissionsToViewButton);

                                        Menu.setVisible(false);
                                        content.removeAll();
                                        content.add(submissionsToViewPanel, BorderLayout.NORTH);
                                        Menu.setSize(500, 78);
                                        Menu.setTitle("View Quiz Submission");
                                        Menu.setVisible(true);

                                        submissionsToViewButton.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                writer.println(submissionsDropDown.getSelectedItem());
                                                writer.flush();
                                                String submissionInfo = "";
                                                try {
                                                    submissionInfo = reader.readLine();
                                                } catch (IOException ex) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                                                }
                                                String[] submissionInfoFinal = submissionInfo.substring(1,
                                                        submissionInfo.length() - 1).split(", ");

                                                JLabel[] submissionLabels = new JLabel[submissionInfoFinal.length];
                                                int i = 0;
                                                for (String s: submissionInfoFinal) {
                                                    submissionLabels[i] = new JLabel(s);
                                                    i++;
                                                }

                                                JPanel submissionPanel = new JPanel();
                                                submissionPanel.setLayout(new BoxLayout(submissionPanel,
                                                        BoxLayout.Y_AXIS));

                                                JButton returnToMenuLocalButton = new JButton("Return to menu");
                                                for (JLabel l: submissionLabels) {
                                                    submissionPanel.add(l);
                                                    l.setAlignmentX(Component.CENTER_ALIGNMENT);
                                                    l.setFont(new Font("Serif", Font.PLAIN, 22));
                                                }
                                                submissionPanel.add(returnToMenuLocalButton);

                                                Menu.setVisible(false);
                                                content.removeAll();
                                                content.add(submissionPanel, BorderLayout.NORTH);
                                                Menu.setSize(500, 538);
                                                Menu.setTitle("Quiz Submission");
                                                Menu.setVisible(true);

                                                returnToMenuLocalButton.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent e) {
                                                        Menu.setVisible(false);
                                                        content.removeAll();
                                                        content.add(teacherMenuPanel, BorderLayout.NORTH);
                                                        Menu.setSize(500, 538);
                                                        Menu.setTitle("Main Menu");
                                                        Menu.setVisible(true);
                                                    }
                                                });
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    });
                }
            });

            //works
            changePasswordButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("changePassword");
                    writer.flush();

                    JPanel changePasswordPanel = new JPanel();
                    changePasswordPanel.add(new JLabel("New Password"));
                    JButton finalizePasswordChangeButton = new JButton("Change Password");
                    JTextField changePasswordText = new JTextField(10);
                    changePasswordPanel.add(changePasswordText);
                    changePasswordPanel.add(finalizePasswordChangeButton);
                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(changePasswordPanel, BorderLayout.NORTH);
                    Menu.setSize(500, 75);
                    Menu.setTitle("Change Password");
                    Menu.setVisible(true);

                    finalizePasswordChangeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            writer.println(changePasswordText.getText());
                            writer.flush();

                            JOptionPane.showMessageDialog(null,
                                    "Password change complete.", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            Menu.setVisible(false);
                            content.removeAll();
                            content.add(userSettingsMenuPanel, BorderLayout.NORTH);
                            Menu.setSize(500, 238);
                            Menu.setTitle("User Settings Menu");
                            Menu.setVisible(true);
                        }
                    });
                }
            });
            //Works
            changeUsernameButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("changeUsername");
                    writer.flush();

                    JPanel changeUsernamePanel = new JPanel();
                    changeUsernamePanel.add(new JLabel("New Username"));
                    JButton finalizeUsernameChangeButton = new JButton("Change Username");
                    JTextField changeUsernameText = new JTextField(10);
                    changeUsernamePanel.add(changeUsernameText);
                    changeUsernamePanel.add(finalizeUsernameChangeButton);
                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(changeUsernamePanel, BorderLayout.NORTH);
                    Menu.setSize(500, 75);
                    Menu.setTitle("Change Username");
                    Menu.setVisible(true);

                    finalizeUsernameChangeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            writer.println(changeUsernameText.getText());
                            writer.flush();

                            String isValid = "";
                            try {
                                isValid = reader.readLine();
                            } catch (IOException ioException) {
                                JOptionPane.showMessageDialog(null,
                                        "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            if (isValid.equalsIgnoreCase("done")) {
                                JOptionPane.showMessageDialog(null,
                                        "Username change complete.", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                                Menu.setVisible(false);
                                content.removeAll();
                                content.add(userSettingsMenuPanel, BorderLayout.NORTH);
                                Menu.setSize(500, 238);
                                Menu.setTitle("User Settings Menu");
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid new username. Could not change it.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                Menu.setVisible(false);
                                content.removeAll();
                                content.add(changeUsernamePanel, BorderLayout.NORTH);
                                Menu.setSize(500, 75);
                                Menu.setTitle("Change Username");
                            }
                            Menu.setVisible(true);
                        }
                    });
                }
            });

            createCourseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.println("createCourse");
                    writer.flush();

                    JPanel finalizeCreateCoursePanel = new JPanel();
                    finalizeCreateCoursePanel.add(new JLabel("Course Name:"));
                    JButton finalizeCourseCreationButton = new JButton("Create Course");
                    JTextField changeUsernameText = new JTextField(10);
                    finalizeCreateCoursePanel.add(changeUsernameText);
                    finalizeCreateCoursePanel.add(finalizeCourseCreationButton);
                    Menu.setVisible(false);
                    content.removeAll();
                    content.add(finalizeCreateCoursePanel, BorderLayout.NORTH);
                    Menu.setSize(500, 75);
                    Menu.setTitle("Create Course");
                    Menu.setVisible(true);

                    finalizeCourseCreationButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            writer.println(changeUsernameText.getText());
                            writer.flush();
                            String isAdded = "";
                            try {
                                isAdded = reader.readLine();
                            } catch (IOException ioException) {
                                JOptionPane.showMessageDialog(null,
                                        "ERROR!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            if (isAdded.equalsIgnoreCase("added")) {
                                JOptionPane.showMessageDialog(null,
                                        "Course created.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                Menu.setVisible(false);
                                content.removeAll();
                                content.add(teacherMenuPanel, BorderLayout.NORTH);
                                Menu.setSize(500, 538);
                                Menu.setTitle("Main Menu");
                                Menu.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "This course already exists", "Error", JOptionPane.ERROR_MESSAGE);
                                Menu.setVisible(false);
                                content.removeAll();
                                content.add(teacherMenuPanel, BorderLayout.NORTH);
                                Menu.setSize(500, 538);
                                Menu.setTitle("Main Menu");
                                Menu.setVisible(true);
                            }
                        }
                    });
                }
            });

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to connect to server!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
