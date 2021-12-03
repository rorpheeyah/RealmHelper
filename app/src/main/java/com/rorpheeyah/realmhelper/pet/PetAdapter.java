package com.rorpheeyah.realmhelper.pet;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.rorpheeyah.realmhelper.R;
import com.rorpheeyah.realmhelper.databinding.LayoutItemBinding;
import com.rorpheeyah.realmhelper.dragdrop.ItemTouchHelperAdapter;
import com.rorpheeyah.realmhelper.dragdrop.SimpleViewHolder;

import java.util.ArrayList;
import java.util.Collections;

public abstract class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private ArrayList<Pet> pets;
    private Context context;

    abstract void onItemClicked(@NonNull View view, @NonNull Pet pet);
    abstract void onDismissed(@NonNull Pet pet);

    public void setPets(@NonNull ArrayList<Pet> listOfPet){
        pets = listOfPet;
        notifyItemRangeChanged(0, pets.size());
    }

    /**
     * Remove item
     */
    public void remove(@NonNull Pet pet){
        int position = 0;
        boolean isFound = false;
        for (int i = 0; i < pets.size(); i++) {
            if(pet.equals(pets.get(i))){
                position = i;
                isFound = true;
                break;
            }
        }

        if(isFound){
            pets.remove(position);
            notifyItemRemoved(position);
        }

    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Log.d(PetAdapter.class.getSimpleName(), "onItemMove from " + fromPosition + " to " + toPosition);
        Collections.swap(pets, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        Log.d(PetAdapter.class.getSimpleName(), "onItemDismiss position: " + position);
        onDismissed(pets.get(position));
        pets.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemDismissDirection(int direction) {
        String d = "";
        switch (direction){
            case ItemTouchHelper.UP:
                d = "UP";
                break;

            case ItemTouchHelper.DOWN:
                d = "DOWN";
                break;

            case ItemTouchHelper.LEFT:
            case ItemTouchHelper.START:
                d = "LEFT";
                break;

            case ItemTouchHelper.END:
            case ItemTouchHelper.RIGHT:
                d = "RIGHT";
                break;
        }
        Log.d(PetAdapter.class.getSimpleName(), "onItemDismissDirection direction: " + d);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pet pet = pets.get(holder.getAdapterPosition());
        holder.setBinding(pet);
        holder.binding.getRoot().setOnClickListener(v -> onItemClicked(holder.itemView, pet));

        if(position == pets.size() - 1) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(convertToDp(16), convertToDp(16), convertToDp(16),convertToDp(16));
            holder.binding.lnRoot.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return pets == null ? 0 : pets.size();
    }

    private int convertToDp(int size){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, context.getResources().getDisplayMetrics());
    }

    /**
     * ViewHolder
     */
    public static class ViewHolder extends SimpleViewHolder {
        private final LayoutItemBinding binding;

        public ViewHolder(@NonNull LayoutItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void setBinding(@NonNull Pet pet){
            binding.setPet(pet);
            binding.executePendingBindings();
        }

        @Override
        public void onViewSelected() {
            Log.d(PetAdapter.class.getSimpleName(), "onViewSelected");
        }

        @Override
        public void onViewCleared() {
            Log.d(PetAdapter.class.getSimpleName(), "onViewCleared");
        }
    }
}
