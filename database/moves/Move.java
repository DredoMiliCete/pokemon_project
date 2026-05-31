package pokemon_project.database.moves;

import pokemon_project.database.types.PokemonType;

public class Move {
    private String name;
    private PokemonType type;
    private MoveType move_type;
    private double power;
    private IEffect effect;

    public Move(String name, PokemonType type, MoveType move_type, double power, IEffect effect) {
        this.name = name;
        this.type = type;
        this.move_type = move_type;
        this.power = power;
        this.effect = effect;
    }

    // --------------------
    // Getters and Setters
    // --------------------
    public String getName() { return this.name; }
    public double getPower() { return this.power; }
    public PokemonType getType() { return this.type; }
    public MoveType getMoveType() { return this.move_type; }

    public void use() {
        // does any additional effect apply?
        if (effect != null) effect.use();



    }

}
