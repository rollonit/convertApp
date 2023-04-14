package com.rollonit.convert;

import static com.rollonit.convert.helper.Converter.convert;
import static com.rollonit.convert.helper.Converter.convertTextToUnit;
import static com.rollonit.convert.helper.Converter.getShortName;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rollonit.convert.helper.Unit;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText e1, e2;
    Spinner s1, s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Setup for decimal format
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.###", otherSymbols);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup for first input value selector
        s1 = findViewById(R.id.spinner);

        final CharSequence[] unit_strings = getResources().getTextArray(R.array.length_units);

        ArrayAdapter<CharSequence> aa = new ArrayAdapter<CharSequence>(this, R.layout.spinner_color_item, unit_strings) {
            @androidx.annotation.NonNull
            @Override
            public View getView(int position, View convertView, @androidx.annotation.NonNull ViewGroup parent) {
                final View view;
                final TextView text;

                if (convertView == null) {
                    view = getLayoutInflater().inflate(R.layout.spinner_color_item, parent, false);
                } else {
                    view = convertView;
                }

                text = (TextView) view;

                final String item = (String) getItem(position);
                if (item != null) {
                    text.setText(getShortName(item));
                }
                return view;
            }
        };
        aa.setDropDownViewResource(R.layout.spinner_dropdown_item);
        s1.setAdapter(aa);
        s1.setOnItemSelectedListener(this);

        // Setup for second input value selector
        s2 = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> aa2 = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, unit_strings) {
            @androidx.annotation.NonNull
            @Override
            public View getView(int position, View convertView, @androidx.annotation.NonNull ViewGroup parent) {
                final View view;
                final TextView text;

                if (convertView == null) {
                    view = getLayoutInflater().inflate(R.layout.spinner_item, parent, false);
                } else {
                    view = convertView;
                }

                text = (TextView) view;

                final String item = (String) getItem(position);
                if (item != null) {
                    text.setText(getShortName(item));
                }
                return view;
            }
        };
        aa2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        s2.setAdapter(aa2);
        s2.setOnItemSelectedListener(this);

        //setup for unit conversion text fields
        e1 = findViewById(R.id.input);
        e2 = findViewById(R.id.output);

        // Handle events from the text fields
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Do nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!e2.hasFocus() && e1.hasFocus()) {
                    try {
                        if (e1.getText().toString().equals("")) {
                            e2.setText("");
                            return;
                        }
                        double val = Double.parseDouble(e1.getText().toString());
                        Unit from = convertTextToUnit(s1.getSelectedItem().toString()), to = convertTextToUnit(s2.getSelectedItem().toString());
                        e2.setText(df.format(convert(val, from, to)));
                    } catch (NumberFormatException e) {
                        //do nothing
                    }
                }
            }
        });

        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!e1.hasFocus() && e2.hasFocus()) {
                    try {
                        if (e2.getText().toString().equals("")) {
                            e1.setText("0");
                            return;
                        }
                        double val = Double.parseDouble(e2.getText().toString());
                        Unit from = convertTextToUnit(s2.getSelectedItem().toString()), to = convertTextToUnit(s1.getSelectedItem().toString());
                        e1.setText(df.format(convert(val, from, to)));
                    } catch (NumberFormatException e) {
                        //do nothing
                    }
                }
            }
        });
    }

    // Handle events from the spinners

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner) {
            // Handle event from the first spinner
            String selectedItem1 = parent.getItemAtPosition(position).toString();
            updateTextFields();

            // for debugging TODO remove later
            Toast.makeText(getApplicationContext(), "Selected item in spinner 1: " + selectedItem1 + " and the enum equivalent is " + convertTextToUnit(selectedItem1), Toast.LENGTH_SHORT).show();
        } else if (parent.getId() == R.id.spinner2) {
            // Handle event from the second spinner
            String selectedItem2 = parent.getItemAtPosition(position).toString();
            updateTextFields();

            // for debugging TODO remove later
            Toast.makeText(getApplicationContext(), "Selected item in spinner 2: " + selectedItem2, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method assigns each text field's value to itself to run the convert logic again.
     */
    private void updateTextFields() {
        e1.setText(e1.getText().toString());
        e2.setText(e2.getText().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }
}
