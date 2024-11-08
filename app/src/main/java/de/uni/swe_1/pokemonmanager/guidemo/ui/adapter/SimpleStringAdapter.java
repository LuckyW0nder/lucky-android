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

public class SimpleStringAdapter extends Adapter<SimpleStringHolder> {
    private LayoutInflater inflater;
    private List<String> originalData;

    public SimpleStringAdapter(Context context, List<String> originalData) {
        this.inflater = LayoutInflater.from(context);
        this.originalData = originalData;
    }

    @NonNull
    @Override
    public SimpleStringHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimpleStringHolder(inflater.inflate(R.layout.listitem_string, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleStringHolder holder, int position) {
        holder.setText(originalData.get(position));
    }

    @Override
    public int getItemCount() {
        return originalData.size();
    }
}

class SimpleStringHolder extends ViewHolder {

    private TextView textView;

    public SimpleStringHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        itemView.setTag(this);
    }

    public void setText(String text) {
        this.textView.setText(text);
    }
}