package com.rorpheeyah.realmhelper.dragdrop;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * SimpleViewHolder
 */
public abstract class SimpleViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{

    public abstract void onViewSelected();
    public abstract void onViewCleared();

    public SimpleViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void onItemSelected() {
        onViewSelected();
    }

    @Override
    public void onItemClear() {
        onViewCleared();
    }
}
