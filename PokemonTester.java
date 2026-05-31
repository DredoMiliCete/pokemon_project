package pokemon_project;

import java.util.*;

import pokemon_project.combat.*;
import pokemon_project.database.moves.*;
import pokemon_project.database.pokemons.*;
import pokemon_project.database.types.*;

public class PokemonTester {
    public static void main(String[] args) {
        // startup database
        PokemonTypeDatabase tdb = new PokemonTypeDatabase();
        MoveDatabase mdb = new MoveDatabase();
        PokemonDatabase db = new PokemonDatabase();

        int[] base_vs = {0, 0, 0, 0, 0, 0};
        PokemonCopy niggassaur = new PokemonCopy(db.bulbasaur, 50, base_vs, base_vs, null);
        PokemonCopy chinmander = new PokemonCopy(db.charmander, 50, base_vs, base_vs, null);
        PokemonCopy squirt = new PokemonCopy(db.squirtle, 50, base_vs, base_vs, null);
        

        /* OLD STUFF
        System.out.println(db.niggassaur);
        System.out.println(db.chinmander);
        System.out.println(db.squirt);
        System.out.println();

        db.niggassaur.setLevel(50);
        db.chinmander.setLevel(50);
        db.squirt.setLevel(50);
        db.penissaur.setLevel(50);
        db.bajablastoise.setLevel(50);

        // test out attack
        List<CombatInfo> combat_info = List.of(
            new CombatInfo(db.penissaur, mdb.grass_whip, db.bajablastoise),
            new CombatInfo(db.bajablastoise, mdb.blizzard, db.penissaur)
        );
        
        simulateTurn(combat_info);

        // test team
        Player playerA = new Player("Ass");
        Player playerB = new Player("Musty");

        CombatManager cm = new CombatManager(new HashMap<>(Map.of(
            TEAMS.A, new TeamData(List.of(db.niggassaur, db.bajablastoise), playerA),
            TEAMS.B, new TeamData(List.of(db.chinmander, db.penissaur, db.squirt), playerB)
        )));

        cm.startBattle(TEAMS.A, TEAMS.B);
        */


    }

    public static void testMove(Pokemon attacker, Pokemon defender, Move move) {
        System.out.printf("%s used %s on %s...\n", attacker.getName(), move.getName(), defender.getName());
        int damage = MoveDamageCalc.calculateDamage(attacker, defender, move);
        defender.takeDamage(damage);
        System.out.println(damage);
        System.out.println(defender.getHealthbar());
        System.out.println();
    }

    public static void simulateTurn(List<CombatInfo> combat_info) {
        combat_info.stream()        // stream
                   .sorted(Comparator.comparing( (CombatInfo ci) -> ci.attacker().getSpeed()).reversed())       // sort by speed
                   .forEach(ci -> testMove(ci.attacker(), ci.target(), ci.move())); // use each move
    }
}
