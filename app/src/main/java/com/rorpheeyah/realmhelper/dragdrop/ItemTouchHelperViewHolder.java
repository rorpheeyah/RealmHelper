package com.rorpheeyah.realmhelper.dragdrop;

import androidx.recyclerview.widget.ItemTouchHelper;

/**
 * Interface to notify an item ViewHolder of relevant callbacks from {@link ItemTouchHelper.Callback}.<br/>
 * ViewHolder must implement ItemTouchHelperViewHolder to prevent Class cast exception
 * @author rorpheeyah
 */
public interface ItemTouchHelperViewHolder {

    /**
     * Called when the {@link ItemTouchHelper} first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    void onItemSelected();


    /**
     * Called when the {@link ItemTouchHelper} has completed the move or swipe, and the active item
     * state should be cleared.
     */
    void onItemClear();
}