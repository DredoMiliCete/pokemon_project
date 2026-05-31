package pokemon_project.database.pokemons;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import pokemon_project.database.moves.MoveDatabase;
import static pokemon_project.database.types.PokemonTypeDatabase.*;

// THIS IS A TEMPORARY SOLUTION, I WILL CHANGE THIS TO A JSON DATABASE
public class PokemonDatabase {
    private static MoveDatabase mdb = new MoveDatabase();

    public static Map<String, Pokemon> database = new HashMap<>(Map.of(
        
        // --- Bulbassaur ---
        "Bulbasaur", new Pokemon(
            "Bulbasaur", "This is a Bulbasaur", List.of(grassType, poisonType), 
            45, 49, 49, 65, 65, 45,
            new HashMap<>(Map.of()), new HashMap<>(Map.of())
        ),

        // --- Charmander ---
        "Charmander", new Pokemon(
            "Charmander", "This is a Charmander", List.of(fireType),
            39, 52, 43, 60, 50, 65,
            new HashMap<>(Map.of()), new HashMap<>(Map.of())
        ),

        // --- Squirtle ---
        "Squirtle", new Pokemon(
            "Squirtle", "This is a Squirtle", List.of(waterType),
            44, 48, 65, 50, 64, 43,
            new HashMap<>(Map.of()), new HashMap<>(Map.of())
        )

    ));
    

    
}
