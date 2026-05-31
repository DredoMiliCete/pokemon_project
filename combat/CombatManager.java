package pokemon_project.combat;

import java.util.*;

import pokemon_project.*;
import pokemon_project.database.moves.*;
import pokemon_project.database.pokemons.*;

public class CombatManager {
    // Info
    private HashMap<TEAMS, TeamData> teams_map;

    // Constructor
    public CombatManager(HashMap<TEAMS, TeamData> teams_map) {
        this.teams_map = teams_map;
    }

    // --------------------
    // Getters and Setters
    // --------------------
    public TeamData getTeam(TEAMS team) { return teams_map.get(team); }

    // --------------------
    // Gameplay Flux
    // --------------------
    public void startBattle(TEAMS teamA, TEAMS teamB) {
        TeamData teamA_data = getTeam(teamA);
        TeamData teamB_data = getTeam(teamB);

        System.out.println("\n - Battle Start -");
        System.out.format(" %s VS %s\n", teamA_data.player().getName(), teamB_data.player().getName());



    }

    // --------------------
    // Show
    // --------------------
    public void printTeam(TEAMS team) {
        TeamData data = teams_map.get(team);
        System.out.printf("%s's Team:\n", data.player().getName());

        data.team().stream()
                   .forEach(
                        (Pokemon p) -> System.out.printf(" - %s;", p)
                    );
        
        System.out.println();
    }
}
