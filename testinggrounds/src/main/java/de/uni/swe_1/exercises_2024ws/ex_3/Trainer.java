package de.uni.swe_1.exercises_2024ws.ex_3;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String firstName;
    private String lastName;
    private List<Pokemon> pokemons = new ArrayList<>();

    //implements SF: Create Trainer in Terminal
    public Trainer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //implements SF: List Pokemons for Trainer in Terminal
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    // for bidirectional linking it is necessary to set this as trainer
    //implements SF: Link Pokemon to Trainer in Terminal
    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        for (Pokemon p : getPokemons()) {
            p.setTrainer(this); // set this as trainer for all
        }
    }

    //implements SF: Show i-th Pokemon for Trainer in Terminal
    public Pokemon getPokemon(int index) {
        return pokemons.get(index);
    }

    //implements SF: List Pokemons for Trainer by Type in Terminal
    public List<Pokemon> getPokemonsOfType(Type type) {
        List<Pokemon> pokemonsOfType = new ArrayList<>();
        for (Pokemon p : getPokemons()) {
            if (p.getType() == type) {
                pokemonsOfType.add(p);
            }
        }
        return pokemonsOfType;
    }

    //implements SF: Link Pokemon to Trainer in Terminal
    public void addPokemon(Pokemon pokemon) {
        getPokemons().add(pokemon); // add to list
        pokemon.setTrainer(this); // set as trainer
    }

    public void releasePokemon(Pokemon pokemon) {
        getPokemons().remove(pokemon); // remove from list
        pokemon.setTrainer(null); // set empty trainer
    }

    //implements SF: Show Trainer Details in Terminal
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}
