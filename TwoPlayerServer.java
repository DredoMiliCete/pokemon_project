package pokemon_project;


// import from java libraries
import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.CountDownLatch;


// import from own project libraries
import static pokemon_project.util.Printing.*;




/**
 * This server only works for a player VS player battle.
 * May gain the functionality of allowing spectators on later builds.
 * 
 * * @author DredoMilicete
 */

public class TwoPlayerServer {
    public static ArrayList<ClientHandler> clients_list = new ArrayList<>();

    // --------------------
    // Main Function
    // --------------------
    public static void main(String[] args) throws IOException, InterruptedException {
        
        // --------------------
        // Start the Server (check for arguments, default if needed, etc)
        // --------------------
        int port = 12345;               // defines a default port for the server
        final int NUM_PLAYERS = 2;

        // Check for argument
        if (args.length > 0) {
            // Get custom port
            try {
                port = Integer.parseInt(args[0].trim());
            } catch (NumberFormatException e) {
                error(String.format("Invalid port, using default port %d", port));
            }
        } else System.out.printf("Using default port %d", port);
        enter();

        ServerSocket server = new ServerSocket(port);
        System.out.printf("- Created server at port=%d -", port);



        // --------------------
        // Wait for Player Connecting (create ClientHandlers, etc)
        // --------------------
        CyclicBarrier barrier =         new CyclicBarrier(NUM_PLAYERS);     // this barrier will make both ClientHandler threads wait for each other after finishing the setup
        CountDownLatch setup_done =     new CountDownLatch(NUM_PLAYERS);    // this latch pauses this main thread, and resumes once both handlers have setup the TeamData

        // wait for player connection
        while (clients_list.size() != NUM_PLAYERS) {
            Socket socket = server.accept();            // this is blocked until a client joins the server
            
            System.out.println("New client connected from: " + socket.getRemoteSocketAddress());

            // create a client handler for that client
            ClientHandler handler = new ClientHandler(socket, barrier, setup_done);
            new Thread(handler).start();            // start the thread
            clients_list.add(handler);              // add the handler to our clients list
        }

        // clients have all joined
        enter();
        System.out.println("- All players joined, waiting for setup... -");



        // --------------------
        // Wait for Player Setup (ClientHandler's handle this part)
        // --------------------
        setup_done.await();         // will be blocked until both handlers have finished setupping TeamData

        System.out.println("- All players ready, starting combat! -");



        // --------------------
        // Start Combat (create CombatManager, and delete this server [in later versions, server continues to receive spectators])
        // --------------------

        // NEED TO CONFIGURE COMBAT MANAGER FIRST

        server.close();     // kill the server, only the handlers will communicate with the client now
    }
}