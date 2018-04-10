package nf.co.sesystems.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.MyViewHolder> {
    private List<ListItem> itemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, quantity, note;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            quantity = view.findViewById(R.id.quantity);
            note = view.findViewById(R.id.note);
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
}
