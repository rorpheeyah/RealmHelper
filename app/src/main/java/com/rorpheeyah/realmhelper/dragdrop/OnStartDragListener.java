package com.rorpheeyah.realmhelper.dragdrop;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Listener for manual initiation of a drag.
 * @author rorpheeyah
 */
public interface OnStartDragListener {
    /**
     * Called when a view is requesting a start of a drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);

    /**
     * Called when a view is requesting a stop of a drag.
     */
    void onStopDrag();
}
