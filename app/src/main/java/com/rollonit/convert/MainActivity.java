package com.rollonit.convert;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup for first input value selector
        Spinner s1 = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.unit_array, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s1.setAdapter(aa);

        // Setup for second input value selector
        Spinner s2 = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> aa2 = ArrayAdapter.createFromResource(this, R.array.unit_array, android.R.layout.simple_spinner_item);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s2.setAdapter(aa2);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        Toast.makeText(getApplicationContext(),"Hello Java point", Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // do nothing
    }
}
