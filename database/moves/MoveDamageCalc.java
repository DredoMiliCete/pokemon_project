package pokemon_project.database.moves;

import java.util.HashSet;

import pokemon_project.database.pokemons.Pokemon;
import pokemon_project.database.types.PokemonType;
import pokemon_project.util.Util;

public class MoveDamageCalc {
    public static int calculateDamage(Pokemon attacker, Pokemon defender, Move move) {
        // no modifier damage
        int atk_level = attacker.getLevel();
        double power = move.getPower();

        // physical or special
        MoveType movetype = move.getMoveType();
        double atk, def;

        if (movetype == MoveType.PHYSICAL) {
            atk = attacker.getAttack();
            def = defender.getDefense();
        } else if (movetype == MoveType.SPECIAL) {
            atk = attacker.getSpecialAttack();
            def = defender.getSpecialDefense();
        } else {
            atk = 0;
            def = 0;
        }

        double damage = ( ( ( (2 * atk_level / 5) + 2) * power * (atk / def) ) / 50 ) + 2;
        
        // stab
        PokemonType move_type = move.getType();
        if (attacker.getTypes().contains(move_type)) damage *= 1.5;

        // type matchups
        HashSet<PokemonType> def_types = defender.getTypes();

        for (PokemonType type_test : def_types) {
            if (type_test.isWeak(move_type))            { damage *= 2; continue; }      // weakness
            if (type_test.isResistant(move_type))       { damage *= 0.5; continue; }    // resistance
            if (type_test.isImmune(move_type))          { damage *= 0; continue; }      // immunity
        }

        // CRITICAL HITS NOT IMPLEMENTED

        // random modifier
        damage *= Util.randomInBetween(0.85, 1.00);

        return (int) damage;

    }
}
