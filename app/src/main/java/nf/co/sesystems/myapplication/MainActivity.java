package nf.co.sesystems.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ListItem> itemList = new ArrayList<>();
    private ListItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EnterValues.class);
                startActivityForResult(intent, 0);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.item_list);

        mAdapter = new ListItemAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemLongClickListener(new ListItemAdapter.onRecyclerViewItemLongClickListener() {
            @Override
            public void onItemLongClickListener(View view, int position) {
                removeItem(position);
            }
        });

        mAdapter.setOnItemClickListener(new ListItemAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if (view.getId() == R.id.ib_add) {
                    addQuantity(position);
                }
                if (view.getId() == R.id.ib_remove) {
                    removeQuantity(position);
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            ListItem listItem = (ListItem) data.getSerializableExtra("item");
            itemList.add(listItem);
            mAdapter.notifyDataSetChanged();
        }

    }

    public void addQuantity(int position) {
        itemList.get(position).incrementQuantity();
        mAdapter.notifyDataSetChanged();
    }

    public void removeQuantity(int position) {
        if (itemList.get(position).getQuantity() != 0) {
            itemList.get(position).decrementQuantity();
            mAdapter.notifyDataSetChanged();
        }
    }

    public void removeItem(int position) {
        itemList.remove(position);
        mAdapter.notifyDataSetChanged();
    }
}
