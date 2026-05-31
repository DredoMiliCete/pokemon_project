package pokemon_project.database.types;

import java.util.HashSet;
import java.util.Objects;
import java.io.Serializable;

public class PokemonType implements Serializable {
    private String name;
    private HashSet<PokemonType> weakness;
    private HashSet<PokemonType> resistance;
    private HashSet<PokemonType> immune;

    public PokemonType(String name) {
        this.name = name;
        this.weakness = new HashSet<>();
        this.resistance = new HashSet<>();
        this.immune = new HashSet<>();
    }

    public void setWeakness(HashSet<PokemonType> weakness) { this.weakness = weakness; }
    public void setResistance(HashSet<PokemonType> resistance) { this.resistance = resistance; }
    public void setImmunity(HashSet<PokemonType> immune) { this.immune = immune; }

    public String getName() { return this.name; }

    public boolean isWeak(PokemonType other_type) { return this.weakness.contains(other_type); }
    public boolean isResistant(PokemonType other_type) { return this.resistance.contains(other_type); }
    public boolean isImmune(PokemonType other_type) { return this.immune.contains(other_type); }

    @Override
    public String toString() { return this.name; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PokemonType)) return false;
        PokemonType obj_cast = (PokemonType) obj;
        return (this.name.equals(obj_cast.getName()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
