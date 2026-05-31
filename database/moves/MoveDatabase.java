package pokemon_project.database.moves;

import pokemon_project.database.types.PokemonTypeDatabase;

public class MoveDatabase {
    private PokemonTypeDatabase tdb = new PokemonTypeDatabase();

    public Move tackle =            new Move("Tackle", tdb.normalType, MoveType.PHYSICAL, 40.0, null);
    public Move grass_tackle =      new Move("Grass Tackle", tdb.grassType, MoveType.PHYSICAL, 40.0, null);
    public Move fire_tackle =       new Move("Fire Tackle", tdb.fireType, MoveType.PHYSICAL, 40.0, null);
    public Move water_tackle =      new Move("Water Tackle", tdb.waterType, MoveType.PHYSICAL, 40.0, null);
    public Move grass_ball =        new Move("Grass Ball", tdb.grassType, MoveType.SPECIAL, 80.0, null);
    public Move grass_whip =        new Move("Grass Whip", tdb.grassType, MoveType.PHYSICAL, 80.0, null);
    public Move blizzard =          new Move("Blizzard", tdb.iceType, MoveType.SPECIAL, 100.0, null);
}
