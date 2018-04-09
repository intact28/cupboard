package nf.co.sesystems.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItemsList =  findViewById(R.id.item_list);

        String[] dummyItems = {

        };

        for (String dummyWeatherDay : dummyItems) {
            mItemsList.append(dummyWeatherDay + "\n\n\n");
        }

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EnterValues.class);
                startActivity(intent);
            }
        });
    }

}
