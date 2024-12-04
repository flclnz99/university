package com.ium.unito.esempio1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Activity2 extends AppCompatActivity {

    EditText contenuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layout = createLayout();
        setContentView(layout);
    }

    private View createLayout(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        contenuto = new EditText(this);
        contenuto.setTextSize(40);
        contenuto.setText("MESSAGGIO");
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        contenuto.setLayoutParams(lparams);
        layout.addView(contenuto);
        Button b = new Button(this);
        String click = getString(R.string.click);
        b.setText(click);
        // Java 8
        b.setOnClickListener( view -> {
         // vuoto per ora
        }     );
        layout.addView(b);
        return layout;
    }
}
