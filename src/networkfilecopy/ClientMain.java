package networkfilecopy;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientMain {
    
    public static void main(String[] args) throws UnknownHostException, IOException {

        int port = 3000;
        
        System.out.println("Connecting to the server\n");

        Socket sock = new Socket("localhost", port);

        System.out.println("Connected!\n");

        // Get the file path by getting the user to input through the command-line
        String filePath = args[0];

        // Read the find
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));

        // Intialise an arraylist to store the bytes of the file that wil be read
        ArrayList<Integer> fileData = new ArrayList<>(); 

        // Loop through the bytes of the file that will be read
        while (true) {
            int fileByte = bis.read();

            // When the buffered input stream returns -1, it means that there are no more characters
            if (fileByte == -1) {
                break;
            }
            // Add the byte into the arraylist to store
            fileData.add(fileByte);
        }
        // Close the buffered input stream after the reading of the file is done
        bis.close();
        




        sock.close();
    }
}
