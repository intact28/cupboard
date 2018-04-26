package nf.co.sesystems.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class EnterValues extends AppCompatActivity {
    EditText name_txt;
    EditText quantity_nmbr;
    EditText note_txt;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_values);

        name_txt = findViewById(R.id.name_field);
        quantity_nmbr = findViewById(R.id.quantity_field);
        note_txt = findViewById(R.id.note_field);

        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ListItem newItem = new ListItem(name_txt.getText().toString(), Integer.parseInt(quantity_nmbr.getText().toString()),
                        note_txt.getText().toString(), getIntent().getStringExtra("id"));
                Intent data = getIntent();
                data.putExtra("item", newItem);
                setResult(RESULT_OK, data);
                finish();
            }
        });

    }

}
