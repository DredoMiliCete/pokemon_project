package pokemon_project.old_stuff_to_delete;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.lang.InterruptedException;

import pokemon_project.Player;
import pokemon_project.combat.*;
import pokemon_project.database.pokemons.*;


public class Server {
    public static ArrayList<ClientHandler> clients_list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // normal info
        int port = 12345;
        int num_players = 2;

        if (args.length >= 2) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("\n[ ERROR : Invalid port, using default conditions ]\n");
            }

            try {
                num_players = Integer.parseInt(args[1]);

                if (num_players < 2) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("\n[ ERROR : Invalid number of players, using default conditions ]\n");
            }
        }

        // create server
        ServerSocket server = new ServerSocket(port);
        System.out.printf("- Created server at port=%d -\n", port);

        CyclicBarrier barrier = new CyclicBarrier(num_players);

        while (clients_list.size() != num_players) {
            Socket socket = server.accept();
            System.out.println("New client connected from: " + socket.getRemoteSocketAddress());

            // start new thread
            ClientHandler handler = new ClientHandler(socket, barrier);
            new Thread(handler).start();
            clients_list.add(handler);
        }

        System.out.println("- All players joined -");

        CombatManager cm = new CombatManager(new HashMap<>(Map.of(
            TEAMS.A, clients_list.get(0).team_data,
            TEAMS.B, clients_list.get(1).team_data
        )));

        cm.printTeam(TEAMS.A);
        cm.printTeam(TEAMS.B);
    }

}


class ClientHandler implements Runnable {
    // Info
    private final Socket socket;
    private final CyclicBarrier barrier;
    private Player player;
    public TeamData team_data;

    // Constructor
    public ClientHandler(Socket socket, CyclicBarrier barrier) {
        this.socket = socket;
        this.barrier = barrier;
    }

    // --------------------
    // Interface Methods
    // --------------------
    @Override
    public void run() {

        try (
            Scanner sc = new Scanner(socket.getInputStream());                             // what receives the nessage | read message sent => sc.nextLine()
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);  // what sends the message | write message to client => out.println(message)
        ) {
            // --------------------
            // Player is connected
            // --------------------
            out.println();

            // receive the team data
            String name = sc.nextLine();        // receives the player data
            String[] pokemons = new String[6];

            for (int i = 0; i < 6; i ++) {
                if (sc.hasNextLine()) pokemons[i] = sc.nextLine(); else pokemons[i] = "none";
            }



            // --------------------
            // Create team data
            // --------------------
            this.player = new Player(name);
            List<PokemonCopy> team = new ArrayList<>();

            for (String line : pokemons) {
                if (line.equals("none")) continue;

                line = line.trim();
                
                String parts[] = line.split("\\|", 6);

                try {
                    String nickname = parts[0].trim();
                    Pokemon base = PokemonDatabase.database.get(parts[1].trim());
                    int level = Integer.parseInt(parts[2].trim());

                    // evs and ivs
                    String[] evs_str = parts[3].split(",");
                    String[] ivs_str = parts[4].split(",");
                    int[] evs = new int[6]; 
                    int[] ivs = new int[6];

                    for (int i = 0; i < 6; i ++){
                        evs[i] = Integer.parseInt(evs_str[i].trim());
                        ivs[i] = Integer.parseInt(ivs_str[i].trim());
                    }

                    PokemonCopy copy = new PokemonCopy(base, level, evs, ivs, null);
                    copy.setNickname(nickname);

                    team.add(copy);
                } catch (NumberFormatException e) {
                    System.err.println("[ Convertion error at line: " + line + " ]");
                }
            }

            team_data = new TeamData(team, this.player);

            barrier.await();





           



        // --------------------
        // Client Disconnected
        // --------------------
        } catch (IOException e) {
            System.out.println("Client disconnected or error: " + e.getMessage());
            
        } catch (InterruptedException e) {
            System.out.println("Await error: " + e.getMessage());

        } catch (BrokenBarrierException e) {
            System.out.println("Await error: " + e.getMessage());

        } finally {
            try {
                Server.clients_list.remove(this);
                socket.close();
                System.out.println("Connection closed safely.");

            } catch (IOException e) {
                System.err.println("Could not close socket: " + e.getMessage());
            }
        }
    }
}