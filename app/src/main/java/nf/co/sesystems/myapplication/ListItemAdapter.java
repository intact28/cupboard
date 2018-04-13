package nf.co.sesystems.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.MyViewHolder> {
    private List<ListItem> itemList;
    private onRecyclerViewItemClickListener mItemClickListener;
    private onRecyclerViewItemLongClickListener mItemLongClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView name, quantity, note;
        public ImageButton addButton, removeButton;
        public LinearLayout row;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            quantity = view.findViewById(R.id.quantity);
            note = view.findViewById(R.id.note);
            addButton = view.findViewById(R.id.ib_add);
            removeButton = view.findViewById(R.id.ib_remove);
            row = view.findViewById(R.id.row);

            addButton.setOnClickListener(this);
            removeButton.setOnClickListener(this);
            row.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClickListener(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mItemLongClickListener != null) {
                mItemLongClickListener.onItemLongClickListener(v, getAdapterPosition());
            }
            return true;
        }
    }

    public ListItemAdapter(List<ListItem> itemList) {
        this.itemList = itemList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItem listItem = itemList.get(position);
        holder.name.setText(listItem.getName());
        holder.quantity.setText(String.valueOf(listItem.getQuantity()));
        holder.note.setText(listItem.getNote());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setOnItemClickListener(onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void setOnItemLongClickListener(onRecyclerViewItemLongClickListener mItemLongClickListener) {
        this.mItemLongClickListener = mItemLongClickListener;
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public interface onRecyclerViewItemLongClickListener {
        void onItemLongClickListener(View view, int position);
    }
}
