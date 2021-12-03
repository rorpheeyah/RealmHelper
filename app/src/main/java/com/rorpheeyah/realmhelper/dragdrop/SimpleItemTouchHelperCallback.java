package com.rorpheeyah.realmhelper.dragdrop;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.rorpheeyah.realmhelper.swipe.RecyclerViewSwipeDecorator;

/**
 * An implementation of {@link ItemTouchHelper.Callback} that enables basic drag & drop and
 * swipe-to-dismiss. Drag events are automatically started by an item long-press.<br/>
 * </br/>
 * Expects the <code>RecyclerView.Adapter</code> to listen for {@link
 * ItemTouchHelperAdapter} callbacks and the <code>RecyclerView.ViewHolder</code> to implement
 * {@link ItemTouchHelperViewHolder}.
 *
 * @author rorpheeyah
 */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter mAdapter;

    private boolean isDraggable;

    private boolean isSwappable;

    private boolean isGridDraggable;

    private Direction direction;

    private String leftText;
    private @ColorRes int leftTextColor, leftBackgroundColor;

    /**
     * Swipe direction
     * @author rorpheeyah
     */
    public enum Direction {
        RIGHT, LEFT
    }

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    /**
     * Enable/Disable drag (top/bottom)
     */
    @SuppressWarnings("unused")
    public void setEnableDrag(boolean isDraggable){
        this.isDraggable = isDraggable;
        this.isSwappable = false;
        this.isGridDraggable = false;
    }

    /**
     * Enable/Disable swipe with direction
     */
    @SuppressWarnings("unused")
    public void setEnableSwipe(Direction direction, boolean isSwappable){
        this.isSwappable = isSwappable;
        this.isGridDraggable = false;
        this.isDraggable = false;
        this.direction = direction;
    }

    /**
     * Enable/Disable swipe
     */
    @SuppressWarnings("unused")
    public void setEnableSwipe(boolean isSwappable){
        this.isSwappable = isSwappable;
        this.isGridDraggable = false;
        this.isDraggable = false;
    }

    /**
     * Enable/Disable grid drag (GridLayout)
     */
    @SuppressWarnings("unused")
    public void setGridDraggable(boolean isGridDraggable){
        this.isGridDraggable = isGridDraggable;
        this.isDraggable = isGridDraggable;
        this.isSwappable = false;
    }

    public void setLeftDecorator(String leftText, @ColorRes int leftTextColor, @ColorRes int leftBackgroundColor){
        this.leftText = leftText;
        this.leftTextColor = leftTextColor;
        this.leftBackgroundColor = leftBackgroundColor;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return isDraggable;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return isSwappable;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if(isGridDraggable){
            return makeMovementFlags(ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START | ItemTouchHelper.END,
                    ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START | ItemTouchHelper.END);
        }
        else if(isDraggable){
            return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0);
        }
        else{
            int swipeType = ItemTouchHelper.START | ItemTouchHelper.END;
            if(direction != null){
                swipeType = direction == Direction.LEFT ? ItemTouchHelper.START : ItemTouchHelper.END;
            }
            return makeMovementFlags(0, swipeType);
        }
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
        if (source.getItemViewType() != target.getItemViewType()) {
            return false;
        }

        mAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
        mAdapter.onItemDismissDirection(i);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            try {
                ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
                itemViewHolder.onItemSelected();
            } catch (Exception e) {
                Log.e(SimpleItemTouchHelperCallback.this.getClass().getSimpleName(), "Your adapter view holder must implement ItemTouchHelperViewHolder");
                e.printStackTrace();
            }
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        try {
            ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
            itemViewHolder.onItemClear();
        } catch (Exception e) {
            Log.e(SimpleItemTouchHelperCallback.this.getClass().getSimpleName(), "Your adapter view holder must implement ItemTouchHelperViewHolder");
            e.printStackTrace();
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        if(isSwappable){
            Context context = recyclerView.getContext();

            // swipe to left
            if(dX < 0 && !TextUtils.isEmpty(leftText) && leftTextColor != 0 && leftBackgroundColor != 0){
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, leftBackgroundColor))
                        .addSwipeLeftLabel(leftText)
                        .setSwipeLeftLabelColor(ContextCompat.getColor(context, leftTextColor))
                        .create()
                        .decorate();
            }

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }
}
