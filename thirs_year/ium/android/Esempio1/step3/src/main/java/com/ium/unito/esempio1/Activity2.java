package com.ium.unito.esempio1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Activity2 extends AppCompatActivity {

    // nomi dei parametri
    public final static String EXTRA_MESSAGE = "com.ium.unito.esempio1.MESSAGE";
    public final static String RESULT_MESSAGE = "com.ium.unito.esempio1.RESULT";

    private EditText contenuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layout = createLayout();
        setContentView(layout);
    }

    private View createLayout() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        contenuto = new EditText(this);
        contenuto.setTextSize(40);
        contenuto.setText("INSERISCI");//message);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        contenuto.setLayoutParams(lparams);
        layout.addView(contenuto);

        Button b = new Button(this);
        String click = getString(R.string.click);
        b.setText(click);
        // Java 8
        b.setOnClickListener(view -> {
            Intent intent = new Intent(this, Activity3.class);
            // passaggio parametro
            String message = contenuto.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivityForResult(intent, 1234);
        });
        layout.addView(b);
        return layout;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode != Activity.RESULT_OK) {
            String msg;
            if (resultCode == Activity.RESULT_CANCELED) {
                msg = "RESULT_CANCELED";
            } else {
                msg = "RESULT_CODE=" + resultCode;
            }
            contenuto.setText(msg);
            return;
        }
        switch (requestCode) {
            case 1234: { //il codice della chiamata
                // esempio; recupera rislutato etc etc
                String ret = data.getStringExtra(RESULT_MESSAGE);
                contenuto.setText("ritorno:" + ret);
                break;
            }
            default:
                contenuto.setText("codice richiesta ignoto:" + requestCode);
        }
    }
}
