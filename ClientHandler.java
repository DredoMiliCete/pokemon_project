package pokemon_project;

// import from java libraries
import java.io.PrintWriter;
import java.io.IOException;

import java.net.Socket;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.BrokenBarrierException;


// import from own project libraries
import pokemon_project.combat.TeamData;
import pokemon_project.database.pokemons.*;

import static pokemon_project.util.Printing.*;




/**
 * Handles the clients in thread. Created by a server.
 * 
 * * @author DredoMiliCete
 */

public class ClientHandler implements Runnable {
    
    // --------------------
    // Info
    // --------------------
    private final Socket socket;                // socket of our client
    private final CyclicBarrier barrier;        // cyclic barrier for each thread
    private final CountDownLatch setup_done;    // count down latch of our server

    private Player player;
    public TeamData team_data;

    private PrintWriter out;    // send messages to client => out.println()
    private Scanner sc;         // receives message from the client => sc.nextLine()

    // Constructor
    /**
     * 
     * @param socket
     * @param barrier
     * @param setup_done
     */
    public ClientHandler(Socket socket, CyclicBarrier barrier, CountDownLatch setup_done) {
        this.socket =       socket;
        this.barrier =      barrier;
        this.setup_done =   setup_done;
    }



    // --------------------
    // Getters
    // --------------------
    public Socket getSocket()               { return this.socket; }
    public CyclicBarrier getBarrir()        { return this.barrier; }
    public CountDownLatch getSetupDone()    { return this.setup_done; }



    // --------------------
    // Methods to Communicate with the Clients (used by Combat Manager)
    // --------------------

    /**
     * Send a message to the client.
     * 
     * @param message Message to send the client.
     */
    public void send(String message) {
        out.println(message);
    }

    /**
     * Ask for a specific input from the client.
     * 
     * @param prompt Prompt to ask the client
     * @return The client's input.
     */
    public String ask(String prompt) {
        send(prompt);
        return sc.nextLine();
    }



    // --------------------
    // Runnable Interface
    // --------------------
    @Override
    public void run() {
        try {
            // Create the means to communicate with the server
            sc = new Scanner(socket.getInputStream());                             // receives message from the client => sc.nextLine()
            out = new PrintWriter(socket.getOutputStream(), true);      // send messages to client => out.println()



            // --------------------
            // Setup (get username and team to create team data)
            // --------------------
            out.println("CONNECTED");       // let the client know it has been succesfully connected

            // now it will wait for client response
            String name = sc.nextLine().trim();
            
            String[] pokemons_text = new String[6];
            for (int i = 0; i < 6; i ++) pokemons_text[i] = sc.hasNextLine() ? sc.nextLine() : "none";       // if the team the client sent has less than 6 pokemons, than the entries will be "none"

            // build the team by reading the file sent
            this.player = new Player(name);
            List<PokemonCopy> team = new ArrayList<>();

            for (String line : pokemons_text) {     // for each pokemon sent from the client
                if (line.equals("none") || line.isEmpty()) continue;    // ignore lines without pokemons

                line = line.trim();
                String[] parts = line.split("\\|", 6);

                try {
                    // base stuff
                    String nickname = parts[0].trim();
                    Pokemon base = PokemonDatabase.database.get(parts[1].trim());
                    int level = Integer.parseInt(parts[2].trim());
                    
                    // evs and ivs
                    String[] evs_str = parts[3].split(",");
                    String[] ivs_str = parts[4].split(",");
                    int[] evs = new int[6];
                    int[] ivs = new int[6];

                    for (int i = 0; i < 6; i ++) {
                        evs[i] = Integer.parseInt(evs_str[i].trim());
                        ivs[i] = Integer.parseInt(ivs_str[i].trim());
                    }

                    // create the copy [ ABILITY STILL NOT IMPLEMENTED]
                    PokemonCopy copy = new PokemonCopy(base, level, evs, ivs, null);
                    copy.setNickname(nickname);
                    team.add(copy);

                } catch (NumberFormatException e) {
                    error(String.format("Failed conversion at line => %s", line));
                }
            }

            

            // --------------------
            // Create everything and update server
            // --------------------
            team_data = new TeamData(team, this.player);

            System.out.printf("[ Setup done for player: %s ]\n", name);

            setup_done.countDown();     // update the server countdown
            barrier.await();            // wait for the rest of the clients to process their info



            // --------------------
            // Game Loop: now the CombatManager uses send() and ask() to talk to each client. this thread will stay alive
            // --------------------

        } catch (IOException e) {   // handles client disconnected errors
            System.out.println("Client disconnected or error: " + e.getMessage());
        
        
        } catch (InterruptedException | BrokenBarrierException e) { // handles errors in thread managment
            System.out.println("Barrier error: " + e.getMessage());
        
        
        } finally { // close the socket
            try {
                socket.close();
                System.out.println("Connection closed for: " + (player != null ? player.getName() : "unknown"));
            
            
            } catch (IOException e) {
                System.err.println("Could not close socket: " + e.getMessage());
            }

        }


    }
}

