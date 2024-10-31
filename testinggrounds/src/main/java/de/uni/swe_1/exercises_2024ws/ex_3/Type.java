package de.uni.swe_1.exercises_2024ws.ex_3;

public enum Type
{
    FIRE,
    WATER,
    POISON;

    private static final double[][] TYPE_EFFECTIVENESS = {
            // FIRE, WATER, POISON  // defender - >
            {1.0,    0.5,   2.0   }, // FIRE    // attacker
            {2.0,    1.0,   0.5   }, // WATER   // |
            {0.5,    2.0,   1.0   } // POISON   // v
    };

    public double getEffectivenessAgainst(Type other)
    {
        return TYPE_EFFECTIVENESS[this.ordinal()][other.ordinal()];
    }
}
