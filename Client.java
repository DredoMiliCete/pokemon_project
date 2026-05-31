package pokemon_project;


// import from java libraries
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;

import java.lang.StringBuilder;

import java.net.Socket;

import java.util.Scanner;

// import from own project libraries

import static pokemon_project.util.Printing.*;





/**
 * This is the Client, and the class you run if you want to play.
 * 
 * * @author DredoMilicete
 */

public class Client {

    // --------------------
    // Main Function
    // --------------------
    public static void main(String[] args) throws IOException {
        
        // --------------------
        // Connect to Server (check for arguments, default if needed, etc)
        // --------------------
        String host = "127.0.0.1";
        int port = 12345;

        // Check for argument
        if (args.length > 1) {
            host = args[0];     // custom host

            // Get custom port
            try {
                port = Integer.parseInt(args[1].trim());
            } catch (NumberFormatException e) {
                error(String.format("Invalid port, using default port %d", port));
            }
        } else System.out.printf("Using default setting : host=%s; port=%d", host, port);
        enter();

        System.out.println("Connecting to " + host + ":" + port + "...");


        Socket socket = new Socket(host, port);     // create a new socket after connecting
        System.out.println("Connected!");



        // --------------------
        // Talk to Server (create the ways to do it, wait for handshake)
        // --------------------
        Scanner remote = new Scanner(socket.getInputStream());                             // receive messages from server => sc.nextLine();
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);      // send messages to server => out.println()

        Scanner sc = new Scanner(System.in); // this scanner is to get input from the user

        // wait for server handshake
        String handshake = remote.nextLine();       // blocks the code untils receives a message from the server
        
        if (!handshake.equals("CONNECTED")) {   // if not received CONNECTED, close the client
            error("Unexpected handshake: " + handshake);
            socket.close();
            sc.close();
            remote.close();
            return;
        }



        // --------------------
        // Collect player info (username and team file)
        // --------------------
        System.out.print("Enter username: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter team file name (must be in the files folder): ");
        String file = sc.nextLine().trim();
        String team_contents = readTeamFile("pokemon_project/files/" + file);       // transcribes the contents of the file into a single string

        if (team_contents == null) {    // if team file empty, close the client
            error("Problem reading team file");
            socket.close();
            sc.close();
            remote.close();
            return;
        }



        // --------------------
        // Send the player info
        // Protocol: line 1 = username, lines 2-7 = one pokemon per line
        // --------------------
        out.println(name);
        out.print(team_contents);
        out.flush();

        System.out.println("Team sent! Waiting for the other player...");
        enter();



        // --------------------
        // Game Loop (the server handles everything)
        // Protocol:
        //  line starts with "ASK": show prompt and ask for user input
        //  line starts with "SHW": show the line
        // --------------------
        while (remote.hasNextLine()) {
            String msg = remote.nextLine();     // line sent from the server

            // server asks for input
            if (msg.startsWith("ASK: ")) {
                System.out.print(msg.substring(5) + " ");
                String response = sc.nextLine().trim();
                out.println(response);


            // server broadcasts info
            } else if (msg.startsWith("SHW: ")) {
                System.out.println(msg.substring(5));
            }


        }



        // --------------------
        // Game Ended
        // --------------------

        System.out.println("Game ended, connection closed");
        remote.close();
        sc.close();
        socket.close();
    }



    // --------------------
    // Read Team Files
    // --------------------

    /**
     * Transcribes a team file into a full string to send to the server
     * 
     * @param fich The file to rad
     * @return A string of everything from the file except the header
     */
    public static String readTeamFile(String fich) {
        try (BufferedReader br = new BufferedReader(new FileReader(fich))) {
            
            StringBuilder sb = new StringBuilder();     // sb will be the string we will return
            String line;
            boolean header = false;

            while ( (line = br.readLine()) != null) {
                if (!header) { header = true; continue; }   // ignore first line
                
                line = line.trim();
                if (line.isEmpty()) continue;                 // ignore empty lines

                sb.append(line).append("\n");       // just join the line in a string
            }

            return sb.toString();
        
            
        } catch (IOException e) {       // catch errors during the file reading
            error("Problem reading file");
            return null;
        }
    }
}
