package com.rorpheeyah.realmhelper.dragdrop;

/**
 * Called when a clicked on item view
 * @author rorpheeyah
 */
public interface OnDragItemClickListener {
    void onClicked(int position, boolean isClickable);
    void onRemoveClicked(int position);
}
