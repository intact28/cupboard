package nf.co.sesystems.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private List<Cupboard> cupboards = new ArrayList();
    private RecyclerView recyclerView;
    private CupboardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addCupboardButton = findViewById(R.id.add_cupboard);

        recyclerView = (RecyclerView) findViewById(R.id.cupboard_list);

        mAdapter = new CupboardAdapter(cupboards);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        addCupboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = UUID.randomUUID().toString();
                showInputDialog(id);
            }
        });

        mAdapter.setOnItemClickListener(new ListItemAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                startActivityForExistingCupboard(position);
            }

        });
    }

    protected void showInputDialog(final String identifier) {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.name_enter, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.startActivityForNewCupboard(editText.getText().toString(), identifier);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void startActivityForNewCupboard(String name, String id) {
        Cupboard addedCupboard = new Cupboard(name, id);
        cupboards.add(addedCupboard);
        mAdapter.notifyDataSetChanged();

        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void startActivityForExistingCupboard(int position) {
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra("id", cupboards.get(position).getId());
        startActivity(intent);
    }
}
