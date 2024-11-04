package de.uni.swe_1.pokemonmanager.guidemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;
import java.util.ArrayList;

import de.uni.swe_1.R;
import de.uni.swe_1.pokemonmanager.guidemo.data.Pokemon;
import de.uni.swe_1.pokemonmanager.guidemo.data.Trainer;
import de.uni.swe_1.pokemonmanager.guidemo.data.Type;
import de.uni.swe_1.pokemonmanager.guidemo.ui.adapter.PokemonAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokemonList = findViewById(R.id.pokemonList);
        setupList();
    }

    private void setupList() {
        List<Pokemon> data = createSampleData();
        PokemonAdapter adapter = new PokemonAdapter(this, data);
        RecyclerView.LayoutManager manager = createLayoutManager();

        pokemonList.setLayoutManager(manager);
        pokemonList.setAdapter(adapter);
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }

    private static List<Pokemon> createSampleData() {
        List<Pokemon> sampleData = new ArrayList<>();
        Trainer t1 = new Trainer("Alisa", "Traurig");
        Trainer t2 = new Trainer("Petra", "Lustig");
        Pokemon p1 = new Pokemon("Shiggy", Type.WATER);
        Pokemon p2 = new Pokemon("Rettan", Type.POISON);
        Pokemon p3 = new Pokemon("Glurak", Type.FIRE);
        t1.addPokemon(p1);
        t1.addPokemon(p2);
        t2.addPokemon(p3);
        sampleData.add(p1);
        sampleData.add(p2);
        sampleData.add(p3);
        return sampleData;
    }
}
