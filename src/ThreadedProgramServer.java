import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Project 05 -- ThreadedProgramServer.java
 *
 * Acts as the project server. Creates a space for multiple clients to join.
 *
 * <p>Purdue University -- CS18000 -- Spring 2022 -- Project 05</p>
 *
 * @author Akshay Godhani, lab sec 04
 *
 * @version April 13, 2022
 *
 *
 */
public class ThreadedProgramServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        ArrayList<Socket> socketArray = new ArrayList<>();
        try {
            //create new server socket
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            System.out.println("Server unable to launch");
        }

        while (true) {
            try {
                //new client to connect
                socket = serverSocket.accept();
                if (!socketArray.contains(socket)) {
                    ProgramServer newServer = new ProgramServer(socket);
                    newServer.start();
                    socketArray.add(socket);
                }
            } catch (IOException e) {
                System.out.println("No clients connected");
            }
            //implement thread for each connection

        }
    }
}

