package pokemon_project.database.types;

import java.util.HashSet;
import java.util.Set;


// THIS ARE ALL REBALANCED
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
    public static final PokemonType soundType =     new PokemonType("Sound");
    public static final PokemonType ogreType =      new PokemonType("Ogre");
    public static final PokemonType cosmicType =    new PokemonType("Cosmic");


    static {
        // --------------------
        // Type Matchups
        // --------------------

        /*
        Type.setWeakness(new HashSet<>(Set.of( )));
        Type.setResistance(new HashSet<>(Set.of( )));
        Type.setImmunity(new HashSet<>(Set.of( )));
        */

        // normal
        normalType.setWeakness(new HashSet<>(Set.of(fightingType)));
        normalType.setResistance(new HashSet<>(Set.of(ogreType)));
        normalType.setImmunity(new HashSet<>(Set.of(ghostType)));


        // fighting
        fightingType.setWeakness(new HashSet<>(Set.of(flyingType, psychicType)));
        fightingType.setResistance(new HashSet<>(Set.of(rockType, bugType, darkType)));
        fightingType.setImmunity(new HashSet<>(Set.of( )));


        // flying
        flyingType.setWeakness(new HashSet<>(Set.of(rockType, electricType, iceType)));
        flyingType.setResistance(new HashSet<>(Set.of(fightingType, bugType)));   
        flyingType.setImmunity(new HashSet<>(Set.of(groundType)));


        // poison
        poisonType.setWeakness(new HashSet<>(Set.of(groundType, psychicType)));
        poisonType.setResistance(new HashSet<>(Set.of(fightingType, poisonType, grassType, fairyType, soundType)));
        poisonType.setImmunity(new HashSet<>(Set.of( )));


        // ground
        groundType.setWeakness(new HashSet<>(Set.of(waterType, grassType, iceType)));
        groundType.setResistance(new HashSet<>(Set.of(poisonType, rockType)));
        groundType.setImmunity(new HashSet<>(Set.of(electricType)));


        // rock
        rockType.setWeakness(new HashSet<>(Set.of(fightingType, steelType, waterType, grassType, soundType)));
        rockType.setResistance(new HashSet<>(Set.of(normalType, flyingType, poisonType, fireType)));
        rockType.setImmunity(new HashSet<>(Set.of( )));


        // bug
        bugType.setWeakness(new HashSet<>(Set.of(flyingType, rockType, fireType)));
        bugType.setResistance(new HashSet<>(Set.of(fightingType, groundType, grassType)));
        bugType.setImmunity(new HashSet<>(Set.of( )));


        // ghost
        ghostType.setWeakness(new HashSet<>(Set.of(ghostType, darkType)));
        ghostType.setResistance(new HashSet<>(Set.of(poisonType)));
        ghostType.setImmunity(new HashSet<>(Set.of(normalType, fightingType)));


        // steel
        steelType.setWeakness(new HashSet<>(Set.of(fightingType, groundType, fireType, ogreType)));
        steelType.setResistance(new HashSet<>(Set.of(normalType, flyingType, rockType, bugType, steelType, dragonType, fairyType)));
        steelType.setImmunity(new HashSet<>(Set.of(poisonType)));


        // fire
        fireType.setWeakness(new HashSet<>(Set.of(groundType, rockType, waterType)));
        fireType.setResistance(new HashSet<>(Set.of(bugType, steelType, fireType, grassType, iceType)));
        fireType.setImmunity(new HashSet<>(Set.of( )));


        // water
        waterType.setWeakness(new HashSet<>(Set.of(grassType, electricType)));
        waterType.setResistance(new HashSet<>(Set.of(fireType, waterType, iceType)));
        waterType.setImmunity(new HashSet<>(Set.of( )));


        // grass
        grassType.setWeakness(new HashSet<>(Set.of(poisonType, bugType, fireType, iceType)));
        grassType.setResistance(new HashSet<>(Set.of(groundType, waterType, grassType, electricType)));
        grassType.setImmunity(new HashSet<>(Set.of( )));

        
        // electric
        electricType.setWeakness(new HashSet<>(Set.of(groundType)));
        electricType.setResistance(new HashSet<>(Set.of(flyingType, steelType, electricType, soundType)));
        electricType.setImmunity(new HashSet<>(Set.of( )));


        // psychic
        psychicType.setWeakness(new HashSet<>(Set.of(bugType, ghostType, darkType, soundType)));
        psychicType.setResistance(new HashSet<>(Set.of(fightingType, psychicType, cosmicType)));
        psychicType.setImmunity(new HashSet<>(Set.of( )));


        // ice
        iceType.setWeakness(new HashSet<>(Set.of(fightingType, rockType, steelType, fireType)));
        iceType.setResistance(new HashSet<>(Set.of(waterType, iceType, ogreType)));
        iceType.setImmunity(new HashSet<>(Set.of( )));


        // dragon
        dragonType.setWeakness(new HashSet<>(Set.of(iceType, dragonType, fairyType)));
        dragonType.setResistance(new HashSet<>(Set.of(waterType, grassType, electricType)));
        dragonType.setImmunity(new HashSet<>(Set.of(fireType)));


        // dark
        darkType.setWeakness(new HashSet<>(Set.of(fightingType, bugType, fairyType)));
        darkType.setResistance(new HashSet<>(Set.of(ghostType, darkType, cosmicType)));
        darkType.setImmunity(new HashSet<>(Set.of(psychicType)));


        // fairy
        fairyType.setWeakness(new HashSet<>(Set.of(poisonType, steelType, ogreType)));
        fairyType.setResistance(new HashSet<>(Set.of(fightingType, darkType)));
        fairyType.setImmunity(new HashSet<>(Set.of(dragonType)));


        // sound
        soundType.setWeakness(new HashSet<>(Set.of(poisonType, bugType)));
        soundType.setResistance(new HashSet<>(Set.of(rockType, steelType, psychicType, soundType)));
        soundType.setImmunity(new HashSet<>(Set.of( )));


        // ogre
        ogreType.setWeakness(new HashSet<>(Set.of(psychicType, dragonType)));
        ogreType.setResistance(new HashSet<>(Set.of(groundType, steelType, fairyType)));
        ogreType.setImmunity(new HashSet<>(Set.of( )));


        // cosmic
        cosmicType.setWeakness(new HashSet<>(Set.of(psychicType, rockType)));
        cosmicType.setResistance(new HashSet<>(Set.of( )));
        cosmicType.setImmunity(new HashSet<>(Set.of(soundType, fireType)));

        


        // --------------------
        // Initialize Type Set
        // --------------------
        pokemonTypeSet = new HashSet<>(Set.of(
            normalType,     grassType,      fireType,       waterType,      electricType,   flyingType,
            rockType,       groundType,     fightingType,   psychicType,    poisonType,     ghostType,
            bugType,        iceType,        dragonType,     darkType,       steelType,      fairyType,
            soundType,      ogreType,       cosmicType
        ));

    }
}
