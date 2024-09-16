
#1. How to Run:
## Compiling:
Access files through IntelliJ IDEA. This will automatically compile the files for you
## Running:

In downloading the complete package,
the needed information storing files are already in the Resources folder, and the necessary
classes are in the SRC folder. Any other necessary files will be created as the program
works, so the user needn't worry about them. For more on this, see "File Locations & Format" below.

To run the program:

Run the "ThreadedProgramServer.java" file in the src folder using IntelliJ's run feature. 

Run any instances of the user interface by typing "java src/GUIPaneTests.java" into the terminal. Use
separate terminals for each instance! Alternatively, if your run state through IntelliJ
is set up to run in parallel, this can be performed using the built in run feature.

Alternatively, if not testing synchronization, just run "GUIPanTests.java" using IntelliJ's run feature
## File Locations & Format:
All files necessary for the program to run and save data are located in the "resources"
folder. The program comes with an empty "QuizSubmissions.txt", "TeachersCredentials.txt", "StudentCredentials.txt", "Quizzes.txt"and "Courses.txt" 
files in this resources file. They will be used in the program and should never be moved or deleted.
It is not advised to edit or change them in any way, but their formatting will be 
discussed below.There are also numerous files
used for testing purposes which are outlined in the testing instructions
Additionally, when asked to enter a file path, one must first put the file in the resources file then enter it in 
the form of the example below:

"exampleTestCreationFile.txt"

When entering a file to be read and create a new quiz, the user must follow the specific format
shown below and store it as a text file with (there is only a new
line after each line. Not an extra blank line like it may look):
######_____________________________________________________________________
quizName

Question1:Answer 

Choice1

Choice2

Choice3

Choice4

Question2:Answer

Choice1

Choice2

Choice3

Choice4
######_____________________________________________________________________
Not following this format will return a message asking that the user correctly
format their file and try again. It will not result in an error or crashing of the program.

To reset our program to its base, ensure that all files in the resource
folder are in the state described below. If they are manually edited, then we are not
responsible for any errors.
##Files in Base Package
Located in resource folder


Courses.txt -- Empty. No created courses.

Create a quiz.txt -- A file that a teacher user may use
to create a quiz. Has 3 questions in the correct format.

Create a quiz2.txt -- A file that a teacher user may use
to create a quiz. Has 3 questions in the correct format.

Create a quiz3.txt -- A file that a teacher user may use
to create a quiz. Has 3 questions in the correct format.


StudentCredentials -- Empty. No created student users.

TeacherCredentials -- Empty. No created teacher users.

QuizSubmissions.txt -- Empty. No submitted Quizzes.

Quizzes.txt -- Empty. No created quizzes.



## Creating an Account & Logging in:

##Once Logged in:
###Teacher:
######1. Create Quiz
Allows the user to create a quiz in an already existing class. The user
gets to choose from a list of created courses, and is then asked if they would
like to create the quiz in the terminal or the file. 

Both choices will ask the teacher if they would like to randomize the question output
for individual student quizzes. This option has no effect on viewing the student quizzes.
Student quiz files and quiz files, as they will be written in a standardized order for ease
of reading.

If the user chooses to create it from a file, then
please follow the format shown above in the "File Locations & Format" section.

If creating manually, the teacher will be prompted for the amount of questions on the quiz,
the quiz name (prompted again if the quiz already exists), each question, its correct choice, and the options.
Each question needs to be saved before moving on, and if not saved, an error pop up will occur
and the quiz will not be created but the program will not crash.
If the user saves a question multiple times, that question will simply be saved as the latest saved version
######2. View Quiz Submissions
Allows the user to choose which course they would like to access, and then which
quiz they would like to view submissions for. Then, the user is presented with a list
of every student that has taken the quiz and can view their answers to each question and if they 
were correct or wrong.
######3. Delete Quiz
Allows the user to delete a specific quiz when given a list of courses to choose from, and then
quizzes within the course. This deletes all user submissions too.
######4. Create Course
Allows the user to create a course. If the user inputs the name of
a course they would like to create. If it already exists, the user is prompted again
after being told the course already exists.
######5. View Courses
Allows the user to see courses avaialble.
######6. Delete Courses
Allows the user to delete courses from a list of courses. This also deletes all
associated quizzes and student submissions. 
######7. User Settings
Allows the user to view their username and password, as well as change either one.
If the user changes to an already existing username, the user will
be told it already exists, and prompted for another username. One can also delete their account
entirely.
######8. Log Out
Ends the current session and logs the user out.
###Student:
Each student has 4 choices once they have logged in. They are detailed below:
######1. Take Quiz
Allows the user to take a quiz. They will be given a list of courses, allowed to choose one,
and then given a list of corresponding quizzes and allowed to choose which to take.
While taking the quiz, the student will need to save their submission to each answer. If no choice
is chosen they will be prompted to choose one before moving on.
######2. View Courses
Allows the user to see courses avaialble.
######3. User Settings
Allows the user to view their username and password, as well as change either one.
If the user changes to an already existing username, the user will 
be told it already exists, and prompted for another username. One can also delete their account
and everything associated with it.
######4. Log out
Ends the current session and logs the user out.
#________________________________________
#2. Who Submitted What:
Collin Scott submitted everything through Vocareum

Collin Scott Submitted the report through BrightSpace

Jeslyn Yang Submitted the video
#________________________________________
#3. Class Descriptions:

## Quiz.java:
###Functionality:

It is called many times in our main class, "ProgramPortal.java"


This class handles quizzes in their entirety. It handles creating a quiz from user input or files
along with reading already existing quiz files so that students may take them or teachers may view them.
It also has functionality for editing quizzes, deleting them (along with all student submissions), actually taking them
as a student, writing the taken student quizzes to a file, randomizing answer choices, and checking if a student has taken
the quiz yet. 

When created, the quiz object is assigned a coursename, quizname, list of answers, questions and choices.
The Answers and Questions list are the length of the quiz, while the choices list is 4x the length of the quiz.
Every 4 choices corresponds to the next question, and if the question number is known, its choices
can be gotten by using multiplication and addition when entering indexes to call.

Randomization is done through a secondary list of integers from 0 to the length of the quiz. The order
of the integers are randomly assigned if the quiz is to be random, and assigned in ascending order if not.
When getting questions, answers, and choices, this random list is always used as an intermediate for indexing, and is
randomized again (if needed) for every new user taking the quiz. This intermediate random list allows the quiz
submissions to always be formatted in the same order, even if the students take it in different orders
###Testing:
[The info presented below is entirely the same as project four as only a single constructor in this class was minimally changed]

Testing was done in a separate intermediate class named QuizTest.java. The tests included creating quizzes, ensuring they were written correctly,
ensuring that they could be created correctly, ensuring students could take them and have their files
written correctly, ensuring that teachers could view, delete, and edit correctly. These basic functionality tests were 
carried out by creating and editing files manually as needed. 

This was all done manually and every single viable error case possible was accounted for 
in addition to basic functionality. These
error cases include, but are not limited to:
Teachers specifying a file that doesn't exist, Students entering wrong inputs when asked for their
answer choice, blank files or reading from specified files in the wrong format etc.

A basic functions checklist tested for in the now deleted QuizTest.java is shown below
in order to give insight to our testing and work flow. ALl testing was completed in project 04, and 
only small changes were made to this class for project 05. Any extended testing on new features was
wrapped into the testing of our main function.

    //create quiz from file ✓
    //create quiz from file ✓
    //create quiz from input ✓
    //load quiz object from already written file ✓
    //saves student file after taking ✓
    //take quiz from file ✓
    //take quiz from input ✓
    //Write quiz file in constructor ✓
    //view student quiz ✓
    //view quiz ✓
    //delete quiz ✓
    //delete student grades associated with quiz ✓
    //edit quiz from input ✓
    //edit quiz from file ✓

    //randomization retest for all above
    //create quiz from file
    //create quiz from input
    //saves student file after taking
    //take quiz from file
    //take quiz from input
    //Write quiz file in constructor
    //view student quiz
    //view quiz
    //delete quiz
    //delete student grades associated with quiz
    //edit quiz from input

###Constructors and Methods:
######public Quiz(String courseName, boolean randomize)
Constructor that creates a quiz through input from a file defined by
terminal inputs from the user within the constructor. Must be passed the course name in which
the quiz is to be created in and if the teacher would like the questions to be randomized. Doubles
as an editor if the quiz name already exists. This distinction must be made and 
checked from within the main function. Writes the quiz file at the end of the process.
######public Quiz(String courseName, String quizName, boolean randomize)
Constructor that creates a quiz through input from the terminal. Asks the user for questions, their four choices,
and the correct answer. Must be passed the course name in which
the quiz is to be created in, the name of quiz which the teacher is creating
and if the teacher would like the questions to be randomized. Doubles
as a terminal editor if the quiz name already exists. This distinction must be made and
checked from within the main function. Writes the quiz file at the end of the process.
######Last constructor needed
######public String generateQuizQuestion(String line)
Used when reading a file in the constructor. Gets the quiz question from the specified format (see file format
section for information on this) and stores it in the question list.
######public char generateQuizAnswer(String line)
Used when reading a file in the constructor. Gets the quiz answer from the specific format (see file format
section for information on this) and stores it in the answer list. 
######public char generateQuizChoiceLetter(int choice)
Used throughout the other methods. Converts the ints 1,2,3,4 to characters A,B,C,D respectively.
######public int askChoice(int question)
Prompts the user taking the quiz for an answer after outputting the question and choices.
Takes the question number as an input (integer of 0-# of questions)
######public String printQuestionChoices(int question)
Prints choices corresponding to the question given by an integer argument. Called in AskChoice()
######public void writeStudentQuizFile(String studentName, List<Character> studentAnswers)
Writes the student quiz file given the student name, and a list of their answers as characters.
######public void takeQuiz(String studentName) 
Takes the quiz given the name of the student taking it. Is what is called in the main method. Each question gives the user
the option to answer by file or by terminal input.
######public void deleteStudentQuizzes()
Deletes the quiz and every single student quiz file associated with it.
######public void viewQuiz()
Allows a user to view the quiz.
######public void viewStudentsQuiz(String studentName)
Allows the user to view a students quiz submission when given the students name.
######public void createQuizFile()
Writes necessary details to a quiz file. Called in constructors and when editing.
######public boolean hasStudentTakenQuiz(String studentName)
Checks if a student has taken the specific quiz.
####____________________________________________________________________________________________________
## GUIPaneTests.java:
###Functionality:
The class handling the GUI and therefore how the user interacts with the program.
Each button leads to another panel, with panels nested within panels for submenus that extend
from the same button. Every button and action taken with the GUI sends a keyword to the server
so that the server knows which menu the user is currently accessing, and what needs to be sent when.
Interacts only with the server, no other methods in Quiz or Teacher/Student are called.
###Testing:
To begin with, every possible wrong input for each button was brainstormed on a whiteboard. These include, but are not limited to:
entering strings into integer fields, trying to view courses when no courses exist, trying to create a quiz
that already exists or trying to edit a quiz that doesn't exist. This process is further 
explained in our video presentation.

After brainstorming and fixing everything we thought of, all encompassing testing was carried out by Akshay, Jeslyn, and Bodhi. We interacted with local versions of 
the program as normal users would. We did this from a clean slate with no existing users, quizzes, or courses.
We created many of each in the process, attempting to cause an error in every single way we had brainstormed above.
This was done individually of each other, with notes taken on what caused each error if we were 
able to cause one. After hitting every test case, we met again and compared. We had all gotten 
broadly the same errors and worked to fix them together. After fixing we repeated the process
until no errors could be caused intentionally. In this process, we also caught errors not caused
by poor inputs. These were bugs such as creating a new user with the same password as another causing
our user list to completely clear. One particularly bad one caused the program to crash and all
created user credentials to be cleared.

After the process was finished, we asked an unbiased third party to test the program.
We took into account their input for clarity and usability (in the code AND ReadMe) along with 
one or two more unexpected errors they found and completed what is now our final draft.


####____________________________________________________________________________________________________
## ThreadedProgramServer.java:
###Functionality:
The main server. Creates instances of the main server instructions everytime a new user 
attempts to run GUIPaneTests. Runs infinitely as is meant to be left on so a user can connect
at any time.

####____________________________________________________________________________________________________
## ProgramServer.java:
###Functionality:
Is created to interact with each user's local GUIPaneTests program every time a new user 
attempts to connect. Each specific use (creating quizzes, taking them etc...) is managed
by a keyword which is sent from the user's connection and then informs the project 
what menu the user is in and therefore what needs to be read and sent next.

All data writing and reading is handled by this server.

###Testing:
Testing was handled the same way as for GUIPaneTests, as they run in tandem.

####____________________________________________________________________________________________________



## User.java:
###Functionality:
This class keeps track of all the users, both students and teachers. It allows users to create, edit, and 
delete their accounts through their username and password, or credentials. In addition, all the credentials 
are held, managed, and updated within this class.

It is called many times in our main class, "ProgramPortal.java"

###Testing:
Testing was done through the main class, QuizTest.

###Constructors and Methods:
######public User(String username, String password, String userType)
Constructor that creates a user through an inputted username, password, and user type, which is either 
teacher or student. 
######public ArrayList<ArrayList<String>> readCredentials()
Used in every other method in User class. Reads all the usernames and passwords from the user type's 
credential files into an ArrayList.
######public boolean validCredentials(String username, String password)
Used when the user logs in. Checks if the inputted username and password match the credentials of 
an existing user account.
######public void createCredentials()
Used when the user signs up. Adds their inputted username and password to the user type's credential file,
as long as the username is not taken by another user.
######public void editCredentials(String newUsername, String newPassword)
Used when the user wants to change their username or password. Updates the user type's credential file to 
contain their new username and password, as long as the new username is not taken by another user.
######public void deleteCredentials() 
Deletes a user's credentials from the user type's credential file.
####____________________________________________________________________________________________________
## Student.java:
###Functionality:
public class Student extends User, creating what is effectively a user object with the usertype
of "student"
####____________________________________________________________________________________________________
## Teacher.java:
###Functionality:
public class Teacher extends User, creating what is effectively a user object with the usertype
of "teacher"