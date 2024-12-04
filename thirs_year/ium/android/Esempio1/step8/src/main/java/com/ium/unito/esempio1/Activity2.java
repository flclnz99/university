package com.ium.unito.esempio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

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
        EditText contenuto = new EditText(this);
        contenuto.setTextSize(40);
        contenuto.setText("MESSAGGIO");
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        contenuto.setLayoutParams(lparams);
        layout.addView(contenuto);

        Button b = new Button(this);
        String click = getString(R.string.mostra);
        b.setText(click);
        // Java 8
        b.setOnClickListener( view -> {
            contenuto.setText(MyGlobal.getVariabile());
        });
        layout.addView(b);
        return layout;
    }
}
