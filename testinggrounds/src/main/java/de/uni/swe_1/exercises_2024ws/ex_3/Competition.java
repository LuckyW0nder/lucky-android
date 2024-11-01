package de.uni.swe_1.exercises_2024ws.ex_3;

import java.util.Date;

public class Competition extends Swap
{
    private Trainer winner = null;
    @Override
    public void execute(Pokemon sourcePokemon, Pokemon targetPokemon)
    {
        // Is Swapping allowed?
        if (!sourcePokemon.isSwapAllowed() || !targetPokemon.isSwapAllowed())
        {
            System.err.printf("No competition: Pokemons '%s' and '%s' are NOT both allowed to be swapped!%n", sourcePokemon.getName(), targetPokemon.getName());
            return;
        }
        // Do both Pokemon have a trainer?
        if (sourcePokemon.getTrainer() == null || targetPokemon.getTrainer() == null)
        {
            System.err.printf("No competition: Pokemon '%s' or '%s' doesn't have a trainer!%n", sourcePokemon.getName(), targetPokemon.getName());
            return;
        }
        // Are the Pokemon from the same trainer?
        if (sourcePokemon.getTrainer() == targetPokemon.getTrainer())
        {
            System.out.println("Competition not allowed for same trainers");
            System.err.printf("No competition: Trainers '%s' == '%s' are identical!%n", sourcePokemon.getTrainer(), targetPokemon.getTrainer());
            return;
        }

        // store Pokemons and Trainers in the competition
        this.setSourcePokemon(sourcePokemon);
        this.setTargetPokemon(targetPokemon);
        this.setSourceTrainer(sourcePokemon.getTrainer());
        Trainer sourceTrainer = this.getSourceTrainer();
        this.setTargetTrainer(targetPokemon.getTrainer());
        Trainer targetTrainer = this.getTargetTrainer();
        this.setDate(new Date());
        this.setId("" + System.currentTimeMillis());


        double sourceDamage = calclateDamage(sourcePokemon, targetPokemon);
        double targetDamage = calclateDamage(targetPokemon, sourcePokemon);

        //determine the winner
        if (sourceDamage > targetDamage)
        {
            this.setWinner(sourceTrainer);
            System.out.println(sourcePokemon + " hat gewonnen!" + "\n" +
                    "Transferiere " + targetPokemon + " zu " + sourceTrainer + "!");

            // remove the Pokemon from the Loser
            targetTrainer.getPokemons().remove(targetPokemon);
            // reassign the Pokemon to the Winner
            sourceTrainer.addPokemon(targetPokemon);
            targetPokemon.setTrainer(sourceTrainer);
        }
        else if (sourceDamage < targetDamage)
        {
            this.setWinner(targetTrainer);
            System.out.println(targetPokemon + " hat gewonnen!" + "\n" +
                    "Transferiere " + sourcePokemon + " zu " + targetTrainer + "!");

            // remove the Pokemon from the Loser
            sourceTrainer.getPokemons().remove(sourcePokemon);
            // reassign the Pokemon to the Winner
            targetTrainer.addPokemon(sourcePokemon);
            sourcePokemon.setTrainer(targetTrainer);
        }
        else
        {
            System.out.println("Es ist ein unentschieden!");
        }
        // store the Competition in Pokemons Competition history
        sourcePokemon.addCompetition(this);
        targetPokemon.addCompetition(this);
    }

    public Trainer getWinner()
    {
        return winner;
    }

    public void setWinner(Trainer winner)
    {
        this.winner = winner;
    }

    public double calclateDamage(Pokemon p1, Pokemon p2)
    {
        // Determine type effectiveness
        double effectiveness = p1.getType().getEffectivenessAgainst(p2.getType());
        // Random damage roll multiplier
        double damageRoll = 1 - (Math.random() / 10);
        // Random critical hit multiplier
        double critical = (1.0/24.0 < Math.random()) ? 1.5 : 1;

        return p1.getCombatPower() * damageRoll * critical * effectiveness;
    }
}
