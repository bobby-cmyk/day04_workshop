package networkfilecopy;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class ServerMain {
    
    public static void main(String[] args) throws IOException {

        int port = 3000;

        // Create a server port
        ServerSocket server = new ServerSocket(port);

        // Keep the server connection on a loop
        while (true) {
            System.out.printf("Waiting for a connection on port: %d\n", port);

            // Wait for an incoming connection --> The server will accept something when something is connected to the port
            Socket sock = server.accept();

            System.out.printf("Got a new connection.\n");


            InputStream is = sock.getInputStream();

            DataInputStream dis = new DataInputStream(is);

            String fileName = dis.readUTF();

            long fileSize = dis.readLong();

            byte[] fileBytes = new byte[(int) fileSize];

            for (int i = 0; i < fileSize; i++) {
                byte fileByte = dis.readByte();
                fileBytes[i] = fileByte;
            }

            System.out.printf("File Name: %s, File Size: %d\n", fileName, fileSize);

            String fileCopyName = "copy" + fileName;
            // Read the find
            BufferedOutputStream bis = new BufferedOutputStream(new FileOutputStream("servercopies/" + fileCopyName));

            bis.write(fileBytes);

            bis.flush();
            bis.close();

            sock.close();
        }
    }
}