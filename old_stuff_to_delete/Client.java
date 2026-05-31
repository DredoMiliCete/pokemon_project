package pokemon_project.old_stuff_to_delete;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import pokemon_project.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.StringBuilder;



public class Client {
    public static void main(String[] args) throws IOException {
        // --------------------
        // Connect Client
        // --------------------
        readTeamFile("pokemon_project/files/team.txt");

        // Valores por omissão (default)
        String host = "127.0.0.1";
        int port = 12345;

        // Se o utilizador passar argumentos, usa-os
        if (args.length >= 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            System.out.printf("[ Using default parameters: %s:%d ]\n", host, port);
        }

        System.out.println("Connecting to " + host + ":" + port + "...");
        Socket socket = new Socket(host, port);
        System.out.println("Client connected");



        // --------------------
        // Client Connected to Server (now ask for username and team file)
        // --------------------
        Scanner remote = new Scanner(socket.getInputStream());                          // what receives the nessage | read message sent => remote.nextLine()
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);   // what sends the message | write message to server => out.println(message)
        Scanner sc = new Scanner(System.in);
        
        // Create Player
        System.out.print("Enter username: ");
        String name = sc.nextLine().trim();
        Player player = new Player(name);

        System.out.print("Enter team file (must be in files folder): ");
        String file = sc.nextLine().trim();
        String read_file = readTeamFile("pokemon_project/files/" + file);

        out.println(name + "\n" + read_file);







    
        sc.close();
        remote.close();
        socket.close();
    }

    public static String readTeamFile(String fich) {
        try (BufferedReader br = new BufferedReader(new FileReader(fich))) {
            String line;
            boolean header = false;

            StringBuilder sb = new StringBuilder();

            while ( (line = br.readLine()) != null) {
                if (!header) { header = true; continue; } // skip the header

                sb.append(line);
                sb.append("\n");
            }

            return sb.toString();

        } catch (IOException e) {
            System.err.println("[ ERROR READING FILE ]" + e);
            return null;
        }
    }

    /* 
    public static List<PokemonCopy> readTeamFile(String fich) {
        try (BufferedReader br = new BufferedReader(new FileReader(fich))) {
            String line;
            boolean header = false;

            List<PokemonCopy> team = new ArrayList<>();



            while ( (line = br.readLine()) != null ) {
                line = line.trim();

                if (!header) { header = true; continue; }   // ignore header
                if (line.isEmpty()) continue;               // ignore empty line

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

            return team;
        } catch (IOException e) {
            System.err.println("[ ERROR READING FILE ]" + e);
            return null;
        }
        */
}
