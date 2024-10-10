package networkfilecopy;

import java.io.*;
import java.net.*;


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

            sock.close();
        }
    }
}