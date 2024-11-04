package de.uni.swe_1.pokemonmanager.guidemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

import de.uni.swe_1.R;
import de.uni.swe_1.pokemonmanager.guidemo.data.Pokemon;
//import de.uni.swe_1.pokemonmanager.guidemo.data.Trainer;
//import de.uni.swe_1.pokemonmanager.guidemo.data.Type;

public class PokemonAdapter extends Adapter<PokemonHolder> {
    private LayoutInflater inflater;
    private List<Pokemon> originalData;

    public PokemonAdapter(Context context, List<Pokemon> originalData) {
        this.inflater = LayoutInflater.from(context);
        this.originalData = originalData;
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonHolder(inflater.inflate(R.layout.listitem_pokemon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
        Pokemon pokemon = originalData.get(position);
        holder.setPokemonView(pokemon);
    }

    @Override
    public int getItemCount() {
        return originalData.size();
    }
}

class PokemonHolder extends ViewHolder {

    private TextView pokemonName;
    private TextView pokemonType;
    private TextView pokemonID;
    private TextView pokemonSwaps;
    private TextView pokemonCompetitions;
    private TextView pokemonTrainer;

    public PokemonHolder(@NonNull View itemView) {
        super(itemView);
        pokemonName = itemView.findViewById(R.id.pokemonName);
        pokemonType = itemView.findViewById(R.id.pokemonType);
        pokemonID = itemView.findViewById(R.id.pokemonID);
        pokemonSwaps = itemView.findViewById(R.id.pokemonSwaps);
        pokemonCompetitions = itemView.findViewById(R.id.pokemonCompetitions);
        pokemonTrainer = itemView.findViewById(R.id.pokemonTrainerName);
    }

    public void setPokemonView(Pokemon pokemon) {
        this.pokemonName.setText(pokemon.getName());
        this.pokemonType.setText(pokemon.getType().toString());
        this.pokemonID.setText("#" + pokemon.getNumber());
        this.pokemonSwaps.setText("Swaps: " + pokemon.getSwaps().size());
        this.pokemonCompetitions.setText("Competitions: " + pokemon.getCompetitions().size());
        this.pokemonTrainer.setText((pokemon.getTrainer() != null ? pokemon.getTrainer().toString() : "Wild"));
    }
}