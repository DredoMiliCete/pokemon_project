package pokemon_project.combat;

import java.io.Serializable;
import java.util.List;

import pokemon_project.Player;
import pokemon_project.database.pokemons.PokemonCopy;

public record TeamData(List<PokemonCopy> team, Player player) implements Serializable {
    public void printTeam() { 
        for (PokemonCopy p : team) System.out.println(p.getNickname());
    }
}