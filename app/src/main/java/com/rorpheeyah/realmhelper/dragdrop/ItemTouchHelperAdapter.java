package com.rorpheeyah.realmhelper.dragdrop;

import androidx.recyclerview.widget.ItemTouchHelper;

/**
 * Interface to listen for a move or dismissal event from a {@link ItemTouchHelper.Callback}.
 * @author rorpheeyah
 */
public interface ItemTouchHelperAdapter {

    /**
     * Called when an item has been dragged far enough to trigger a move. This is called every time
     * an item is shifted, and not at the end of a "drop" event.
     *
     * @param fromPosition The start position of the moved item.
     * @param toPosition   Then end position of the moved item.
     */
    void onItemMove(int fromPosition, int toPosition);


    /**
     * Called when an item has been dismissed by a swipe.
     */
    void onItemDismiss(int position);

    /**
     * Called when an item has been dismissed by a swipe.<br/>
     * {@link ItemTouchHelper} direction
     */
    void onItemDismissDirection(int direction);
}