package de.uni.swe_1.exercises_2024ws.ex_3;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private String name;
    private Type type;
    private double combatPower = 100;
    private Trainer trainer = null;
    private final int number;
    private static int nextNumber = 1;
    private List<Swap> swaps = new ArrayList<>();
    private List<Swap> competitions = new ArrayList<>();
    private boolean isSwapAllowed = true;

    //implements SF: Create Pokemon in Terminal
    public Pokemon(String name, Type type)
    {
        this.name = name;
        this.type = type;
        this.number = nextNumber;
        nextNumber++;
    }

    public Pokemon(String name, Type type, double combatPower)
    {
        this.name = name;
        this.type = type;
        this.combatPower = combatPower;
        this.number = nextNumber;
        nextNumber++;
    }

    public String getName() {
        return name;
    }

    //implements SF: Edit Pokemon in Terminal
    public void setName(String name) {
        // this references the actual object instance
        this.name = name;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    //implements SF: Link Pokemon to Trainer in Terminal
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Type getType() {
        return type;
    }

    //implements SF: Edit Pokemon in Terminal
    public void setType(Type type) {
        this.type = type;
    }

    public double getCombatPower() {
        return combatPower;
    }

    public void setCombatPower(double combatPower) {
        this.combatPower = combatPower;
    }

    public int getNumber() {
        return number;
    }

    public List<Swap> getSwaps() {
        return swaps;
    }

    public void setSwaps(List<Swap> swaps) {
        this.swaps = swaps;
    }

    public void addSwap(Swap swap) {
        getSwaps().add(swap);
    }

    public List<Swap> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Swap> competitions) {
        this.competitions = competitions;
    }

    public void addCompetition(Competition competition) {
        getCompetitions().add(competition);
    }

    public boolean isSwapAllowed() {
        return isSwapAllowed;
    }

    //implements SF: Edit Pokemon in Terminal
    public void setSwapAllowed(boolean isSwapAllowed) {
        this.isSwapAllowed = isSwapAllowed;
    }

    //implements SF: Show Pokemon Details in Terminal
    @Override
    public String toString()
    {
        if(getTrainer() == null)
        {return "Pokemon(" + getNumber() + ") '" + getName() + "' of type '" + getType() + "'";}
        else
        {return "Pokemon(" + getNumber() + ") '" + getName() + "' of type '" + getType() +
                "' has trainer '" + getTrainer() + "'";}
    }

    public static void main(String[] args) {
        Pokemon p;
        p = new Pokemon("Glurak", Type.FIRE);
        System.out.println(p);
    }
}
