package nf.co.sesystems.myapplication;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nf.co.sesystems.myapplication.room.AppDatabase;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ListItem> itemList = new ArrayList<>();
    private ListItemAdapter mAdapter;
    private List<ListItem> dbList = new ArrayList<>();
    private List<ListItem> removedItemsList = new ArrayList<>();
    public static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "item-database").build();

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

        Thread fetchFullListThread = new Thread(new Runnable() {
            @Override
            public void run() {
                dbList = db.ListItemDao().getAllItems();
                for (ListItem listItem : dbList) {
                    itemList.add(listItem);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        fetchFullListThread.start();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Thread addItemToDbThread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (ListItem listItem : removedItemsList) {
                    removeItemFromDb(listItem);
                }
                for (ListItem listItem : itemList) {
                    addItemToDb(listItem);
                }
            }
        });
        addItemToDbThread.start();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            final ListItem listItem = (ListItem) data.getSerializableExtra("item");
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

    public void removeItem(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete item?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removedItemsList.add(itemList.get(position));
                        itemList.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();
    }

    public void addItemToDb(ListItem listItem) {
        db.ListItemDao().insertAll(listItem);
    }

    public void removeItemFromDb(ListItem listItem) {
        db.ListItemDao().delete(listItem);
    }

}
