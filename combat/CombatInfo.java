package pokemon_project.combat;

import pokemon_project.database.moves.Move;
import pokemon_project.database.pokemons.Pokemon;

public record CombatInfo(Pokemon attacker, Move move, Pokemon target) {}