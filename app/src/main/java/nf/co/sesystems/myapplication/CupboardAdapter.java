package nf.co.sesystems.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class CupboardAdapter extends RecyclerView.Adapter<CupboardAdapter.MyViewHolder> {

    private List<Cupboard> cupboardList;
    private ListItemAdapter.onRecyclerViewItemClickListener mItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public Button cupboardButton;

        public MyViewHolder(View view) {
            super(view);

            cupboardButton = view.findViewById(R.id.cupboard_button);
            cupboardButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClickListener(v, getAdapterPosition());
            }
        }
    }

    public CupboardAdapter(List<Cupboard> cupboardList) {
        this.cupboardList = cupboardList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cupboard_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Cupboard cupboard = cupboardList.get(position);
        holder.cupboardButton.setText(cupboard.getName());
        }

    @Override
    public int getItemCount() {
        return cupboardList.size();
    }

    public void setOnItemClickListener(ListItemAdapter.onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position);
    }
}
