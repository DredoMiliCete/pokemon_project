package pokemon_project;

import java.io.FileReader;
import java.io.IOException;
import pokemon_project.TwoPlayerServer;

public class Test {
    public static void main(String[] args) {
        // Path relative to the project root
        String filePath = "paukemon_project/files/team.txt";

        try (FileReader reader = new FileReader(filePath)) {
            int data = reader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
