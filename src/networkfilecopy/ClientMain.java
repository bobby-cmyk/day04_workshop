package networkfilecopy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

        // Instantiate file 
        File file = new File(filePath);
        
        // Read the find
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

        // Get file name
        String fileName = file.getName();

        // Get file size
        long fileSize = file.length();

        // Instantiate array with file size;
        byte[] fileBytes = new byte[(int) fileSize];

        // Loop through the bytes of the file that will be read
        for (int i = 0; i < fileSize; i++) {
            int fileByte = bis.read();

            // When the buffered input stream returns -1, it means that there are no more characters
            if (fileByte == -1) {
                break;
            }
            // Add the byte into the arraylist to store
            fileBytes[i] = (byte) fileByte;
        }
        // Close the buffered input stream after the reading of the file is done
        bis.close();

        OutputStream os = sock.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);
        
        // Clean up the filePath to only include the file name
        dos.writeUTF(fileName);

        dos.writeLong(fileSize);

        for (byte i : fileBytes) {
            dos.writeByte(i);
        }

        sock.close();
    }
}
