package pokemon_project.database.types;

import java.util.HashSet;
import java.util.Set;

public class PokemonTypeDatabase {
    public static HashSet<PokemonType> pokemonTypeSet;

    public static final PokemonType normalType =    new PokemonType("Normal");
    public static final PokemonType grassType =     new PokemonType("Grass");
    public static final PokemonType fireType =      new PokemonType("Fire");
    public static final PokemonType waterType =     new PokemonType("Water");
    public static final PokemonType electricType =   new PokemonType("Electric");
    public static final PokemonType flyingType =    new PokemonType("Flying");
    public static final PokemonType rockType =      new PokemonType("Rock");
    public static final PokemonType groundType =    new PokemonType("Ground");
    public static final PokemonType fightingType =  new PokemonType("Fighting");
    public static final PokemonType psychicType =   new PokemonType("Psychic");
    public static final PokemonType poisonType =    new PokemonType("Poison");
    public static final PokemonType ghostType =     new PokemonType("Ghost");
    public static final PokemonType bugType =       new PokemonType("Bug");
    public static final PokemonType iceType =       new PokemonType("Ice");
    public static final PokemonType dragonType =    new PokemonType("Dragon");
    public static final PokemonType darkType =      new PokemonType("Dark");
    public static final PokemonType steelType =     new PokemonType("Steel");
    public static final PokemonType fairyType =     new PokemonType("Fairy");

    static {
        // --------------------
        // Type Matchups
        // --------------------

        // grass
        grassType.setWeakness(new HashSet<>(Set.of(fireType, flyingType, poisonType, bugType, iceType)));
        grassType.setResistance(new HashSet<>(Set.of(grassType, waterType, electricType, groundType)));

        // fire
        fireType.setWeakness(new HashSet<>(Set.of(waterType, rockType, groundType)));
        fireType.setResistance(new HashSet<>(Set.of(grassType, fireType, iceType, bugType, steelType, fairyType)));

        // water
        waterType.setWeakness(new HashSet<>(Set.of(grassType, electricType)));
        waterType.setResistance(new HashSet<>(Set.of(fireType, waterType, iceType, steelType)));


        // --------------------
        // Initialize Type Set
        // --------------------
        pokemonTypeSet = new HashSet<>(Set.of(
            normalType,     grassType,      fireType,       waterType,      electricType,   flyingType,
            rockType,       groundType,     fightingType,   psychicType,    poisonType,     ghostType,
            bugType,        iceType,        dragonType,     darkType,       steelType,      fairyType
        ));

    }
}
