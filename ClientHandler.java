/**
 * ClientHandler.java
 *
 * This class handles communication between the client
 * and the server.  It runs in a separate thread but has a
 * link to a common list of sockets to handle broadcast.
 *
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class ClientHandler implements Runnable {
  private Socket connectionSock = null;
  private ArrayList<Socket> socketList;
  private ChattyPatty patty;

  ClientHandler(Socket sock, ArrayList<Socket> socketList) {
    this.connectionSock = sock;
    this.socketList = socketList;  // Keep reference to master list
    patty = new ChattyPatty();
  }

  /**
   * received input from a client.
   * sends it to other clients.
   */
  public void run() {
    try {
      System.out.println("Connection made with socket " + connectionSock);
      BufferedReader clientInput = new BufferedReader(
          new InputStreamReader(connectionSock.getInputStream()));
      String userName = clientInput.readLine();
      for (Socket s : socketList) {
        DataOutputStream clientOutput;
        clientOutput = new DataOutputStream(s.getOutputStream());
        clientOutput.writeBytes("Patty: " + userName
            + " has joined the chatroom. Everyone say hello to them!\n");
      }
      while (true) {
        // Get data sent from a client
        String clientText = clientInput.readLine();
        if (clientText != null) {
          System.out.println("Received: " + clientText);
          // Turn around and output this data
          // to all other clients except the one
          // that sent us this information
          // Generate a random number between 0 and 1
          Random generator = new Random();
          double number = generator.nextDouble();
          // If random number is gretaer than 0.5 execute the Bot code
          for (Socket s : socketList) {
            DataOutputStream clientOutput;
            if (s != connectionSock) {
              clientOutput = new DataOutputStream(s.getOutputStream());
              clientOutput.writeBytes(clientText + "\n");
            }
            if (number >= 0.5) {
              if (clientText.substring(clientText.length() - 1).equals("!")
                  || clientText.substring(clientText.length() - 1).equals(".")
                  || clientText.substring(clientText.length() - 1).equals("?"))  {
                clientText = clientText.substring(0, clientText.length() - 1);
              }
              String[] messageToBot = clientText.split(":", 2);
              messageToBot[1] = messageToBot[1].substring(1, messageToBot[1].length());
              patty.setUserInput(messageToBot[1]);
              clientOutput = new DataOutputStream(s.getOutputStream());
              clientOutput.writeBytes("Chatty Patty: " + patty.getMessage() + "\n");
            }
            //write chattypatty's message here in this for loop
          }
        } else {
          // Connection was lost
          System.out.println("Closing connection for socket " + connectionSock);
          // Remove from arraylist
          socketList.remove(connectionSock);
          connectionSock.close();
          break;
        }
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.toString());
      // Remove from arraylist
      socketList.remove(connectionSock);
    }
  }
} // ClientHandler for MtServer.java
