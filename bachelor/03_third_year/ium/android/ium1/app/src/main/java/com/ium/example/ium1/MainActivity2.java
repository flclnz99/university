package com.ium.example.ium1;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    static String PARAMETRO = "com.ium.example.ium1.parametro";
    public final static String EXTRA_MESSAGE = "com.ium.unito.esempio1.MESSAGE";
    public final static String RESULT_MESSAGE = "com.ium.unito.esempio1.RESULT";
    EditText contenuto;
    EditText operando1;
    EditText operando2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layout = createLayout();
        setContentView(layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public boolean opOptionsItemSelected(MenuItem item){
        Log.d("onOptionsItemSelected", item.getTitle().toString());
        switch (item.getItemId()){
            case android.R.id.home:
                //bottone del toolbar, quello del telefono chiama onBackPressed()
                //se restituisco true disattivo il bottone
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private View createLayout(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        contenuto = new EditText(this);
        contenuto.setTextSize(40);
        contenuto.setText("MESSAGGIO");
        operando1 = new EditText(this);
        operando1.setTextSize(40);
        operando1.setText("Operando 1");
        operando2 = new EditText(this);
        operando2.setTextSize(40);
        operando2.setText("Operando 2");
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        contenuto.setLayoutParams(lparams);
        layout.addView(contenuto);
        Button b = new Button(this);
        String click = getString(R.string.click);
        b.setText(click);
        // Java 8
        b.setOnClickListener( view -> {
            String s = contenuto.getText().toString();
            Intent intento = new Intent(this, Main3Activity.class);
            intento.putExtra(PARAMETRO,s);
            startActivityForResult(intento,123);
        }     );
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
            case 123: { //il codice della chiamata
                        //esempio; recupera risultato etc etc
                String ret = data.getStringExtra(RESULT_MESSAGE);
                contenuto.setText("ritorno:" + ret);
                break;
            }
            default:
                contenuto.setText("codice richiesta ignoto:" + requestCode);
        }
    }
}