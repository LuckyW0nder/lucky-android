package de.uni.swe_1.pokemonmanager.guidemo.data;

import java.util.Date;

public class Competition extends Swap
{
    private Trainer winnerTrainer = null;
    private Pokemon winnerPokemon = null;
    public static final String DAMAGE_INFLICTED = " (Damage inflicted: ";
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


        double sourceDamage = calculateDamage(sourcePokemon, targetPokemon);
        double targetDamage = calculateDamage(targetPokemon, sourcePokemon);

        // Determine the winner
        if (sourceDamage > targetDamage)
        {
            transferPokemon(sourcePokemon, targetPokemon, sourceTrainer, targetTrainer, sourceDamage, targetDamage);
        }
        else if (sourceDamage < targetDamage)
        {
            transferPokemon(targetPokemon, sourcePokemon, targetTrainer, sourceTrainer, targetDamage, sourceDamage);
        }
        else
        {
            System.out.println("Es ist ein unentschieden!");
        }
        // store the Competition in Pokemons Competition history
        sourcePokemon.addCompetition(this);
        targetPokemon.addCompetition(this);
    }

    public Trainer getWinnerTrainer()
    {
        return winnerTrainer;
    }

    public void setWinnerTrainer(Trainer winnerTrainer)
    {
        this.winnerTrainer = winnerTrainer;
    }

    public Pokemon getWinnerPokemon()
    {
        return winnerPokemon;
    }

    public void setWinnerPokemon(Pokemon winnerPokemon)
    {
        this.winnerPokemon = winnerPokemon;
    }

    public double calculateDamage(Pokemon p1, Pokemon p2)
    {
        // Determine type effectiveness
        // NOTE: type effectiveness is currently always applied twice, since one pokemon always attacks for super-effective damage and one attacks for not-very-effective damage
        double effectiveness = p1.getType().getEffectivenessAgainst(p2.getType());
        // Random damage roll multiplier
        double damageRoll = 1 - (Math.random() / 10);
        // Random critical hit multiplier
        double critical = (1.0/24.0 < Math.random()) ? 1.5 : 1;

        return p1.getCombatPower() * damageRoll * critical * effectiveness;
    }

    private void transferPokemon(Pokemon winnerPokemon, Pokemon loserPokemon, Trainer winnerTrainer, Trainer loserTrainer, double winnerDamage, double loserDamage) {
        this.setWinnerTrainer(winnerTrainer);
        this.setWinnerPokemon(winnerPokemon);
        System.out.println(winnerPokemon.getName() + DAMAGE_INFLICTED + (int) winnerDamage + ") hat gewonnen!" + "\n" +
                "Transferiere " + loserPokemon.getName() + DAMAGE_INFLICTED + (int) loserDamage + ") zu " + winnerTrainer + "!");

        // remove the Pokemon from the Loser
        loserTrainer.getPokemons().remove(loserPokemon);
        // reassign the Pokemon to the Winner
        winnerTrainer.addPokemon(loserPokemon);
        loserPokemon.setTrainer(winnerTrainer);
    }
}
