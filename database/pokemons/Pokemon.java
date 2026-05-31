package pokemon_project.database.pokemons;

import java.util.HashSet;
import java.util.List;

import pokemon_project.database.moves.Ability;
import pokemon_project.database.moves.Move;
import pokemon_project.database.types.PokemonType;

import java.util.HashMap;
import java.lang.StringBuilder;

public class Pokemon {
    // ------------------------------------------------------------------------------------------------------------------------
    // Info
    // ------------------------------------------------------------------------------------------------------------------------

    // general info
    protected final String NAME, DESCRIPTION;
    protected final List<PokemonType> BASE_TYPES;

    // stats info
    protected final int BASE_HP, BASE_ATK, BASE_DEF, BASE_SPEATK, BASE_SPEDEF, BASE_SPEED;

    // move/ability info
    protected final HashMap<Integer, Move> MOVESET;
    protected final HashMap<Integer, Ability> ABILITYSET;



    // ------------------------------------------------------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------------------------------------------------------
    
    // create base
    public Pokemon(
        String name, String description, List<PokemonType> types,
        int hp, int atk, int def, int speatk, int spedef, int speed,
        HashMap<Integer, Move> moveset, HashMap<Integer, Ability> abilityset
    ) {
        // general info
        this.NAME = name;
        this.DESCRIPTION = description;
        this.BASE_TYPES = types;

        // stats info
        this.BASE_HP = hp;
        this.BASE_ATK = atk;
        this.BASE_DEF = def;
        this.BASE_SPEATK = speatk;
        this.BASE_SPEDEF = spedef;
        this.BASE_SPEED = speed;

        // move/ability info
        this.MOVESET = moveset;
        this.ABILITYSET = abilityset;
    }

    // create copy
    protected Pokemon(Pokemon bp) {
        // general info
        this.NAME = bp.getName();
        this.DESCRIPTION = bp.getDescription();
        this.BASE_TYPES = bp.getBaseTypes();

        // stats info
        this.BASE_HP = bp.getBaseHP();
        this.BASE_ATK = bp.getBaseAttack();
        this.BASE_DEF = bp.getBaseDefense();
        this.BASE_SPEATK = bp.getBaseSpecialAttack();
        this.BASE_SPEDEF = bp.getBaseSpecialDefense();
        this.BASE_SPEED = bp.getBaseSpeed();

        // move/ability info
        this.MOVESET = bp.getMoveset();
        this.ABILITYSET = bp.getAbilityset();
    }



    // ------------------------------------------------------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------------------------------------------------------
    public String getName()                             { return this.NAME; }
    public String getDescription()                      { return this.DESCRIPTION; }
    public List<PokemonType> getBaseTypes()            { return this.BASE_TYPES; }

    public int getBaseHP()                              { return this.BASE_HP; }
    public int getBaseAttack()                          { return this.BASE_ATK; }
    public int getBaseDefense()                         { return this.BASE_DEF; }
    public int getBaseSpecialAttack()                   { return this.BASE_SPEATK; }
    public int getBaseSpecialDefense()                  { return this.BASE_SPEDEF; }
    public int getBaseSpeed()                           { return this.BASE_SPEED; }

    public HashMap<Integer, Move> getMoveset()          { return this.MOVESET; }
    public HashMap<Integer, Ability> getAbilityset()    { return this.ABILITYSET; }




    /* OLD STUFF
    // create base pokemon

    // --------------------
    // Stats Work
    // --------------------
    public int scaleToLevel(double stat) { return ((int) (2 * stat * level) / 100) + 5; }
    public int getScaledHP() { return ((int) (2 * base_hp * level) / 100) + level + 5;}

    public void startupStats() {
        this.hp = getScaledHP();
        this.attack = scaleToLevel(base_attack);
        this.defense = scaleToLevel(base_defense);
        this.special_attack = scaleToLevel(special_attack);
        this.special_defense = scaleToLevel(special_defense);
        this.speed = scaleToLevel(speed);
    }

    public void takeDamage(double damage) {
        this.hp -= damage;
    }


    // --------------------
    // Moves
    // --------------------
    public void addMove(Move move) {
        if (current_moves.size() < 4) current_moves.add(move);
    }

    public void removeMove(Move move) {
        current_moves.remove(move);
    }

    public void useMove(Move move) {
        // check if moves can be used
        if (!current_moves.contains(move)) { System.out.println("move not in pool"); return; }
    }

    // --------------------
    // Showing
    // --------------------
    @Override
    public String toString() {
        return String.format("%s = {types: %s}",
                             this.name, this.types);
    }

    public String getHealthbar() {
        StringBuilder sb = new StringBuilder("[");
        final int HEALTHBAR_SIZE = 40;

        double percentage = (double) hp / getScaledHP();
        int full_squares = (int) Math.round(percentage * HEALTHBAR_SIZE);

        for (int i = 0; i < HEALTHBAR_SIZE; i ++) {
            if (i < full_squares) sb.append("█"); else sb.append("-");
        }

        sb.append("]");
        return sb.toString();
    } */
}
