import java.io.*;
import java.net.*;
import java.util.*;

public class EchoThread extends Thread {
    private Socket connectionSocket;

    public EchoThread(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    public void run() {
        Scanner inFromClient = null;
        DataOutputStream outToClient = null;

        try {
            inFromClient = new Scanner(connectionSocket.getInputStream());
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String firstNumber = getNextLine(inFromClient);
            String secondNumber = getNextLine(inFromClient);
            if (InputUtility.isNullOrEmptyOrBlank(firstNumber) || InputUtility.isNullOrEmptyOrBlank(secondNumber)) {
                return;
            }

            String result = String.valueOf(Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber)) + '\n';

            outToClient.writeBytes(result);
        } catch (IOException e) {
            System.err.println("Closing Socket connection");
        } finally {
            try {
                if (inFromClient != null)
                    inFromClient.close();
                if (outToClient != null)
                    outToClient.close();
                if (connectionSocket != null)
                    connectionSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getNextLine(Scanner scanner) {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}