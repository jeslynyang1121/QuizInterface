import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
/**
 * Project 05 -- User.java
 *
 * A program that creates and stores User objects into files. Is called in ProgramServer.java and is extended by
 * Teacher and Student.
 *
 * <p>Purdue University -- CS18000 -- Spring 2022 -- Project 05</p>
 *
 * @author  Jeslyn Yang lab sec 04
 *
 * @version May 01, 2022
 *
 *
 */
public class User {
    private String username;
    private String password;
    private String userType;

    //constructor for user with a username and password
    public User(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    //getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //reads the usernames and passwords from the credentials file into an ArrayList
    public ArrayList<ArrayList<String>> readCredentials() {
        ArrayList<ArrayList<String>> credentials = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("resource/" + userType + "Credentials.txt"));
            ArrayList<String> usernamesFile = new ArrayList<>();
            ArrayList<String> passwordsFile = new ArrayList<>();
            String lineUsername = bfr.readLine();
            String linePassword = bfr.readLine();
            while ((lineUsername != null) && (linePassword != null)) {
                usernamesFile.add(lineUsername);
                passwordsFile.add(linePassword);
                lineUsername = bfr.readLine();
                linePassword = bfr.readLine();
            }
            bfr.close();
            credentials.add(usernamesFile);
            credentials.add(passwordsFile);
        } catch (Exception e) {
            System.out.println("Could not retrieve credentials.");
        }
        return credentials;
    }

    //checks if the inputted username is used by anyone else
    public boolean validUsername(String username) {
        try {
            ArrayList<ArrayList<String>> credentials = readCredentials();
            ArrayList<String> usernamesFile = credentials.get(0);
            if (usernamesFile.contains(username)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //checks if the inputted credentials matches an existing account
    public boolean validCredentials(String username1, String password1) {
        try {
            ArrayList<ArrayList<String>> credentials = readCredentials();
            ArrayList<String> usernamesFile = credentials.get(0);
            ArrayList<String> passwordsFile = credentials.get(1);
            int indexOfCredentials;
            if (usernamesFile.contains(username1)) {
                indexOfCredentials = usernamesFile.indexOf(username1);
                if (passwordsFile.get(indexOfCredentials).equals(password1)) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    //creates a new account for when the user signs up
    public boolean createCredentials() {
        boolean isCreated = false;
        try {
            ArrayList<ArrayList<String>> credentials = readCredentials();
            ArrayList<String> usernamesFile = credentials.get(0);
            ArrayList<String> passwordsFile = credentials.get(1);

            if (usernamesFile.contains(username)) {
                return false;
            } else {
                PrintWriter pw = new PrintWriter(new FileWriter("resource/" + userType + "Credentials.txt"));
                usernamesFile.add(username);
                passwordsFile.add(password);
                for (int i = 0; i < usernamesFile.size(); i++) {
                    pw.println(usernamesFile.get(i));
                    pw.println(passwordsFile.get(i));
                }
                pw.close();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    //edits an existing account's credentials
    public void editCredentials(String newUsername, String newPassword) {
        try {
            ArrayList<ArrayList<String>> credentials = readCredentials();
            ArrayList<String> usernamesFile = credentials.get(0);
            ArrayList<String> passwordsFile = credentials.get(1);
            int indexOfCredentials;
            if (usernamesFile.contains(username)) {
                indexOfCredentials = usernamesFile.indexOf(username);
                if (passwordsFile.get(indexOfCredentials).equals(password)) {
                    PrintWriter pw = new PrintWriter(new FileWriter("resource/" + userType + "Credentials.txt"));
                    usernamesFile.set(indexOfCredentials, newUsername);
                    passwordsFile.set(indexOfCredentials, newPassword);
                    for (int i = 0; i < usernamesFile.size(); i++) {
                        pw.println(usernamesFile.get(i));
                        pw.println(passwordsFile.get(i));
                    }
                    pw.close();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Could not find your account in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Could not find your account in the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Could not find your account in the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //deletes an existing account's credentials
    public void deleteCredentials() {
        try {
            ArrayList<ArrayList<String>> credentials = readCredentials();
            ArrayList<String> usernamesFile = credentials.get(0);
            ArrayList<String> passwordsFile = credentials.get(1);
            int indexOfCredentials;
            if (usernamesFile.contains(username)) {
                indexOfCredentials = usernamesFile.indexOf(username);
                if (passwordsFile.get(indexOfCredentials).equals(password)) {
                    PrintWriter pw = new PrintWriter(new FileWriter("resource/" + userType + "Credentials.txt"));
                    usernamesFile.remove(indexOfCredentials);
                    passwordsFile.remove(indexOfCredentials);
                    for (int i = 0; i < usernamesFile.size(); i++) {
                        pw.println(usernamesFile.get(i));
                        pw.println(passwordsFile.get(i));
                    }
                    pw.close();
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Could not find your account in the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Could not find your account in the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
