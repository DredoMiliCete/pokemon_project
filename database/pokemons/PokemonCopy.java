package pokemon_project.database.pokemons;

import java.util.HashSet;
import java.util.List;
import java.io.Serializable;

import pokemon_project.database.moves.Ability;
import pokemon_project.database.moves.Move;
import pokemon_project.database.types.PokemonType;

public class PokemonCopy extends Pokemon implements Serializable {
    // ------------------------------------------------------------------------------------------------------------------------
    // Info
    // ------------------------------------------------------------------------------------------------------------------------
    
    // general info
    private String nickname;
    private List<PokemonType> types;
    // private Item held_item;
    // private Nature nature;

    // stats info
    private int hp, attack, defense, spe_attack, spe_defense, speed;
    private int level;
    private int[] evs, ivs = new int[6];

    // move/ability info
    private HashSet<Move> current_moves;
    private Ability current_ability;



    // ------------------------------------------------------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------------------------------------------------------
    public PokemonCopy(Pokemon base, int start_level, int[] evs, int[] ivs, Ability ability) {
        super(base); // makes sure this new instance is using the base as the base

        this.nickname = NAME; // since no nickname is given, the nickname is just the base name
        this.types = BASE_TYPES;

        this.level = start_level;
        setScaledStats(); // initializes the stats;

        // sets nature, ivs, evs, and held item
        this.evs = evs;
        this.ivs = ivs;
        // this.nature = nature;
        // this.held_item = item;

        // initializes the ability and current moveset
        current_moves = new HashSet<>();
        current_ability = ability;
    }



    // ------------------------------------------------------------------------------------------------------------------------
    // Getters and Setters
    // ------------------------------------------------------------------------------------------------------------------------
    
    // Getters
    public String getNickname()             { return this.nickname; }
    public List<PokemonType> getTypes()    { return this.types; }

    public int getHP()                      { return this.hp; }
    public int getAttack()                  { return this.attack; }
    public int getDefense()                 { return this.defense; }
    public int getSpecialAttack()           { return this.spe_attack; }
    public int getSpecialDefense()          { return this.spe_defense; }
    public int getSpeed()                   { return this.speed; }

    public int getLevel()                   { return this.level; }
    public int[] getEVs()                   { return this.evs; }
    public int[] getIVs()                   { return this.ivs; }

    public HashSet<Move> getCurrentMoves()  { return this.current_moves; }
    public Ability getCurrentAbility()      { return this.current_ability; }


    // Setters
    public void setNickname(String name)    { this.nickname = name; }
    public void setLevel(int level)         { if (100 >= level && level >= 1) this.level = level; }
    public void setEVs(int[] evs)           { this.evs = evs; }
    public void setIVs(int[] ivs)           { this.ivs = ivs; }

    public void setTypes(List<PokemonType> types) { this.types = types; }

    public void setCurrentAbility(Ability ability) {
        if (ABILITYSET.values().contains(ability)) this.current_ability = ability;
    }

    // Manage Moves
    public void addMove(Move move) {
        // only adds if there are less than 4 current moves and the move can be learned
        if (current_moves.size() < 4 && MOVESET.values().contains(move)) current_moves.add(move);
    }

    public void removeMove(Move move) {
        current_moves.remove(move);
    }


    // ------------------------------------------------------------------------------------------------------------------------
    // Stats work
    // ------------------------------------------------------------------------------------------------------------------------
    public int scaleToLevel(int stat) { return ((int) (2 * stat * level) / 100) + 5; }

    public void setScaledStats() {
        this.hp =               scaleToLevel(BASE_HP) + level;
        this.attack =           scaleToLevel(BASE_ATK);
        this.defense =          scaleToLevel(BASE_DEF);
        this.spe_attack =       scaleToLevel(BASE_SPEATK);
        this.spe_defense =      scaleToLevel(BASE_SPEDEF);
        this.speed =            scaleToLevel(BASE_SPEED); 
    }



    // ------------------------------------------------------------------------------------------------------------------------
    // Show
    // ------------------------------------------------------------------------------------------------------------------------
    
    // Print the Healthbar
    public String getHealthbar() {
        StringBuilder sb = new StringBuilder("[");
        final int HEALTHBAR_SIZE = 40;

        int max_hp = scaleToLevel(BASE_HP) + level;         // scale to get the maximum amount
        double percentage = (double) hp / max_hp;
        int full_squares = (int) Math.round(percentage * HEALTHBAR_SIZE); // get the amount of squares

        // draw the squares
        for (int i = 0; i < HEALTHBAR_SIZE; i ++) {
            if (i < full_squares) sb.append("█"); else sb.append("-");
        }

        sb.append("]");
        return sb.toString();
    }




}
