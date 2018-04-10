package nf.co.sesystems.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class EnterValues extends AppCompatActivity {
    EditText edit_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_values);

        edit_txt = findViewById(R.id.enter_field);

        edit_txt.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int id, KeyEvent event) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    Intent data = new Intent();
                    data.putExtra("item", edit_txt.getText().toString());
                    setResult(RESULT_OK, data);
                    finish();
                    return true;
                }
                return false;
            }
        });

    }

}
