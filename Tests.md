#Test Cases

##Instructions: 
To run all code, you must start running “ThreadedProgramServer.java”. This 
opens the server for all clients to connect in order to run the 
teacher/student application. After you have started running the server program, 
clients should be able to log in and log out simultaneously. 

In the resources folder are 3 files titled "Create a Quiz.txt" , "Create a Quiz2.txt" and "Create a Quiz3.txt". 
These are to be used when creating a quiz from a file, so that the TA does not have to get a file
in the correct format!
#      LOG IN METHODS
##1. Signing Up
###Instructions:
1) User launches application.
2) User selects a role, teacher or student.
3) User select “Sign Up” to register an account.
4) User enters a username and password then clicks the “Sign Up” button to register the account.
   1) If the username is already taken by another user an error message will pop up and click “OK” to make the error disappear and try again typing in a new username and password to sign up.
5) Once the user successfully signs up they will see a pop-up saying welcome, and will be at the main menu. 
######____________________________________________________________________________________________________
###Expected result: 
Application stores the user's username and password for future login attempts and loads the main menu automatically.
######____________________________________________________________________________________________________
###Test Status: 
Passed!
#___________________________________________________

##2. Logging In
###Instructions:
1) User launches application.
2) User selects a role, teacher or student.
3) User select “Log In” to register an account.
4) User enters a username and password then clicks the “Log In” button to log in the account. 
   1) If the username or password is incorrect and doesn’t match existing credentials, an error will pop up telling the user that the username was invalid. Click “OK” and try logging in again with the correct credentials.
5) Once the user successfully logs in they will see a pop-up saying welcome, and will be at the main menu.
######____________________________________________________________________________________________________
###Expected result: 
Application accepts the user's username and password and loads the main menu automatically.
######____________________________________________________________________________________________________
###Test Status: 
Passed!
#___________________________________________________

#      TEACHER METHODS (Once you reach the main menu)
##1. Create Quiz
###Instructions:
1) User needs to create a course first, otherwise a pop-up will tell the user to create a course.
2) User selects whether they want the quiz to be randomized or not. 
3) User chooses to create a quiz from file or manually. 
   1) Choosing from a file 
   2) User selects the course to create a quiz in. 
      1) User enters file path for quiz (assuming in resource folder). 
         1) If an error occurs with file path, a pop up will tell the user to enter the correct file path. 
      2) Quiz is created!
   3) Enter Quiz manually 
      1) User selects the course to create a quiz in. 
      2) User enters the quiz name. 
      3) User enters the number of questions, click the next button. 
      4) User enters the questions and the answer choices, then selects the correct answer choice, then clicks the save question button, then the next button to enter the next question and repeat until you have completed the quiz. 
      5) Quiz is created!
4) User returns to the main menu.
######____________________________________________________________________________________________________
###Expected result: 
Application guides user through creating quiz based on what they choose, and successfully creates a quiz and saves it to the database, then returns to the main menu.
######____________________________________________________________________________________________________
###Test Status: 
Passed!
#___________________________________________________

##2. Edit Quiz
1) User needs to create a course and a quiz first, otherwise a pop-up will tell the user to create a course or quiz.
2) User selects whether they want the quiz to be randomized or not.
3) User chooses to edit a quiz from file or manually. 
   1) Choosing from a file 
      1) User selects the course to edit a quiz in. 
      2) User enters file path for quiz (assuming in resource folder). 
         1) If an error occurs with file path, a pop up will tell the user to enter the correct file path. 
      3) Quiz is edited!
   2) Enter Quiz manually 
      1) User selects the course to create a quiz in. 
      2) User enters the quiz name. 
      3) User enters the number of questions, clicks the next button. 
      4) User enters the questions and the answer choices, then selects the correct answer choice, then clicks the save question button, then the next button to enter the next question and repeat until you have completed the quiz. 
      5) Quiz is edited!
4) User returns to the main menu.
######____________________________________________________________________________________________________
###Expected result: 

Application guides user through editing an existing quiz based on what they choose, and successfully edits a quiz and saves it to the database, then returns to the main menu.
######____________________________________________________________________________________________________
###Test Status: 

Passed!


#___________________________________________________
##3. View Quiz

1) User needs to create a course and a quiz first, otherwise a pop-up will tell the user to create a course or quiz. 
2) User selects the course to view the quiz in. 
3) User selects the quiz they would like to view. 
4) User views quiz and returns to menu.
######____________________________________________________________________________________________________
###Expected result: 

Application guides user through viewing an existing quiz based on what they choose, and successfully shows the quiz then returns to the main menu.
######____________________________________________________________________________________________________
###Test Status: 

Passed!

#___________________________________________________
##4. View Quiz Submissions

1) User needs to create a course and a quiz first, otherwise a pop-up will tell the user to create a course or quiz. (Student needs to take a quiz first to see submissions)
2) User selects the course to view the quiz in.
3) User selects the quiz they would like to view.
4) User selects students' submissions they would like to view.
5) User views the quiz submission and returns to the menu.
######____________________________________________________________________________________________________
###Expected result: 

Application guides user through viewing an existing students quiz submission based on what they choose, and successfully shows the quiz submission then returns to the main menu.
######____________________________________________________________________________________________________
###Test Status: 

Passed!

#___________________________________________________
##5. Delete Quiz

1) User needs to create a course and a quiz first, otherwise a pop-up will tell the user to create a course or quiz.
2) User selects the course to delete the quiz in.
3) User selects the quiz they would like to delete.
4) User deletes the quiz and returns to the menu.
######____________________________________________________________________________________________________
###Expected result: 

Application guides user through deleting an existing quiz based on what they choose, and successfully deletes the quiz and quiz submissions from the database then returns to the main menu.
######____________________________________________________________________________________________________
###Test Status: 

Passed.


#___________________________________________________
##6. Create Course

1) Users name a course to create.
2) Course is created and the user returns to the menu.
######____________________________________________________________________________________________________
###Expected result: 

Application guides users to create a course that is saved to the database, then returns them to the main menu.
######____________________________________________________________________________________________________
###Test Status: 

Passed!

#___________________________________________________
##7. View Course

1) User needs to create a course first, otherwise a pop-up will tell the user to create a course.
2) Users can see a list of all the courses.
3) User returns to the main menu.
######____________________________________________________________________________________________________
###Expected result: 
Application guides users to view a list of courses, then returns them to the main menu.
######____________________________________________________________________________________________________
###Test Status: 

Passed!
#___________________________________________________
##8. Delete Course

1) User needs to create a course first, otherwise a pop-up will tell the user to create a course.
2) User selects the course to delete.
3) User deletes the course and returns to the menu.
######____________________________________________________________________________________________________
###Expected result: 

Application guides users through deleting an existing quiz based on what they choose, and successfully deletes the course, then the quiz and quiz submissions affiliated with the course are also deleted from the database.
######____________________________________________________________________________________________________
###Test Status: 

Passed.

#___________________________________________________
##9. User Settings

1) Users have the option to change username, change password,  delete account, or return to menu.
   1) Changing Username
      1) Changing username prompts the user for the new username, and stores the new username for their account.
   2) Changing Password
      1) Changing password prompts the user for the new password, and stores the new password for their account.
   3) Delete Account
      1) Deleting the account lets user return to the main menu, then they will need to log out and cannot log back in.
   4) Return to Menu
      1) Returns the user to the menu.
######____________________________________________________________________________________________________
###Expected result: 

Application guides users through changing any of their user settings and updates the database accordingly.
######____________________________________________________________________________________________________      
###Test Status: 

Passed!

#___________________________________________________
#STUDENT METHODS

## 1. Take Quiz

1) Users can select the course.
2) Users select a quiz they want to take. (If existing otherwise prompts that they don’t exist).
3) Users can select answer choices, and click “Save Question” then next to move through all the questions.
4) Users returning to the menu submit quizzes.
######____________________________________________________________________________________________________
###Expected result: 

Application guides users through taking the quiz, and returns the user to the menu after they finish taking the quiz, and uploaded their answers in a file to the database.
######____________________________________________________________________________________________________
###Test Status: 

Passed!

#___________________________________________________
## 2. User Settings

1) Users have the option to change username, change password,  delete account, or return to menu.
   1) Changing Username
      1) Changing username prompts the user for the new username, and stores the new username for their account.
   2) Changing Password
      1) Changing password prompts the user for the new password, and stores the new password for their account.
   3) Delete Account
      1) Deleting the account lets user return to the main menu, then they will need to log out and cannot log back in.
   4) Return to Menu
      1) Returns the user to the menu.
######____________________________________________________________________________________________________
###Expected result:

Application guides users through changing any of their user settings and updates the database accordingly.
######____________________________________________________________________________________________________
###Test Status: 

Passed!

#___________________________________________________
## 3. View Course

1) User needs to create a course first, otherwise a pop-up will tell the user to create a course.
2) Users can see a list of all the courses.
3) User returns to the main menu.
######____________________________________________________________________________________________________
###Expected result: 

Application guides users to view a list of courses, then returns them to the main menu.
######____________________________________________________________________________________________________
###Test Status: 

Passed.

#___________________________________________________
## 4. Logout

1) Logs users out. Returns the user to the starting page to log back in.
######____________________________________________________________________________________________________
###Expected result: 

Application logs user out and returns to the beginning of launching the application to log back in.
######____________________________________________________________________________________________________
###Test Status: 

Passed!

