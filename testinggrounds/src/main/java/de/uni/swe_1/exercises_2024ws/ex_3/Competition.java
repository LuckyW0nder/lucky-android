package de.uni.swe_1.exercises_2024ws.ex_3;

import java.util.Date;
import java.lang.Math;

public class Competition extends Swap
{
    public void execute(Pokemon sourcePokemon, Pokemon targetPokemon)
    {
        if (sourcePokemon.isSwapAllowed() && targetPokemon.isSwapAllowed())
        {
            if (sourcePokemon.getTrainer() == null || targetPokemon.getTrainer() == null)
            {
                if (sourcePokemon.getTrainer() != targetPokemon.getTrainer())
                {
                    // swapping is allowed
                    // store Pokemons and Trainers in the competition
                    this.setSourcePokemon(sourcePokemon);
                    this.setTargetPokemon(targetPokemon);
                    this.setSourceTrainer(sourcePokemon.getTrainer());
                    this.setTargetTrainer(targetPokemon.getTrainer());
                    this.setDate(new Date());
                    this.setId("" + System.currentTimeMillis());

                    // determine the winner
                    double sourceEffectiveness = sourcePokemon.getType().getEffectivenessAgainst(targetPokemon.getType());
                    double damageRoll1 = Math.random();
                    double damageRoll2 = Math.random();

                    // remove the Pokemons from the Trainers
                    /*this.sourceTrainer.getPokemons().remove(sourcePokemon);
                    this.targetTrainer.getPokemons().remove(targetPokemon);*/
                    // reassign the Pokemons to the Trainers
                    /*this.sourceTrainer.addPokemon(targetPokemon);
                    this.targetTrainer.addPokemon(sourcePokemon);*/

                    // store the Swap/Competition in Pokemons Swap/Competition history
                    /*sourcePokemon.addSwap(this);
                    targetPokemon.addSwap(this);*/

                    sourcePokemon.addCompetition(this);
                    targetPokemon.addCompetition(this);

                }
                else
                {System.err.printf("No swap: Trainers '%s' == '%s' are identical!%n", sourcePokemon.getTrainer(), targetPokemon.getTrainer());}
            }
            else
            {System.err.printf("No swap: Pokemon '%s' or '%s' doesn't have a trainer!%n", sourcePokemon.getName(), targetPokemon.getName());}
        }
        else
        {System.err.printf("No swap: Pokemons '%s' and '%s' are NOT both allowed to be swapped!%n", sourcePokemon.getName(), targetPokemon.getName());}
    }
}
