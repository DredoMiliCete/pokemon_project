package pokemon_project;

import java.io.Serializable;

public class Player implements Serializable {
    // info
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}
