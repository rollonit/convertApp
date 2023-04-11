package com.rollonit.convert;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

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
        s1.setOnItemSelectedListener(this);

        // Setup for second input value selector
        Spinner s2 = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> aa2 = ArrayAdapter.createFromResource(this, R.array.unit_array, android.R.layout.simple_spinner_item);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s2.setAdapter(aa2);
        s2.setOnItemSelectedListener(this);

        //setup for unit conversion text fields
        EditText e1 = findViewById(R.id.input);
        EditText e2 = findViewById(R.id.output);

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!e2.hasFocus()) {
                    try {
                        if (e1.getText().toString().equals("")) {
                            e2.setText("0");
                            return;
                        }
                        double val = Double.parseDouble(e1.getText().toString());
                        Unit from = convertTextToUnit(s1.getSelectedItem().toString()), to = convertTextToUnit(s2.getSelectedItem().toString());
                        e2.setText(String.format(Locale.ENGLISH, "%.2f", convert(val, from, to)));
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
                if (!e1.hasFocus()) {
                    try {
                        if (e2.getText().toString().equals("")) {
                            e1.setText("0");
                            return;
                        }
                        double val = Double.parseDouble(e2.getText().toString());
                        Unit from = convertTextToUnit(s2.getSelectedItem().toString()), to = convertTextToUnit(s1.getSelectedItem().toString());
                        e1.setText(String.format(Locale.ENGLISH, "%.2f", convert(val, from, to)));
                    } catch (NumberFormatException e) {
                        //do nothing
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do something
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner) {
            // Handle event from the first spinner
            String selectedItem1 = parent.getItemAtPosition(position).toString();

            // for debugging TODO remove later
            Toast.makeText(getApplicationContext(), "Selected item in spinner 1: " + selectedItem1 + " and the enum equivalent is " + convertTextToUnit(selectedItem1), Toast.LENGTH_SHORT).show();
        } else if (parent.getId() == R.id.spinner2) {
            // Handle event from the second spinner
            String selectedItem2 = parent.getItemAtPosition(position).toString();

            // for debugging TODO remove later
            Toast.makeText(getApplicationContext(), "Selected item in spinner 2: " + selectedItem2, Toast.LENGTH_SHORT).show();
        }
        // Add more if statements if you have more spinners
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    /**
     * This method is used to convert the input value from one unit of measurement to another.
     *
     * @param value The value to be converted.
     * @param from  The unit of measurement that the value is currently in.
     * @param to    The unit of measurement that the value should be converted to.
     * @return The converted value.
     */
    public double convert(double value, Unit from, Unit to) {
        double meters = 0;

        // Convert the value to meters
        switch (from) {
            case METER:
                meters = value;
                break;
            case KILOMETER:
                meters = value * 1000;
                break;
            case CENTIMETER:
                meters = value / 100;
                break;
            case MILLIMETER:
                meters = value / 1000;
                break;
            case MILE:
                meters = value * 1609.344;
                break;
            case YARD:
                meters = value * 0.9144;
                break;
            case FOOT:
                meters = value * 0.3048;
                break;
            case INCH:
                meters = value * 0.0254;
                break;
            case NAUTICAL_MILE:
                meters = value * 1852;
                break;
        }

        // Convert and return the value from meters to the desired unit
        switch (to) {
            case METER:
                return meters;
            case KILOMETER:
                return meters / 1000;
            case CENTIMETER:
                return meters * 100;
            case MILLIMETER:
                return meters * 1000;
            case MILE:
                return meters / 1609.344;
            case YARD:
                return meters / 0.9144;
            case FOOT:
                return meters / 0.3048;
            case INCH:
                return meters / 0.0254;
            case NAUTICAL_MILE:
                return meters / 1852;
            default:
                return -1;
        }
    }

    /**
     * This method is used to convert the text in the spinners to the appropriate enum value.
     *
     * @param text The text to be converted.
     * @return The enum value that corresponds to the text.
     */
    public Unit convertTextToUnit(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }

        switch (text) {
            case "Meters":
                return Unit.METER;
            case "Kilometers":
                return Unit.KILOMETER;
            case "Centimeters":
                return Unit.CENTIMETER;
            case "Millimeters":
                return Unit.MILLIMETER;
            case "Miles":
                return Unit.MILE;
            case "Yards":
                return Unit.YARD;
            case "Feet":
                return Unit.FOOT;
            case "Inches":
                return Unit.INCH;
            case "Nautical Miles":
                return Unit.NAUTICAL_MILE;
            default:
                return null;
        }
    }

    /**
     * This enum is used to represent the different units of measurement
     * that are available for conversion.
     */
    enum Unit {
        METER, KILOMETER, CENTIMETER, MILLIMETER, MILE, YARD, FOOT, INCH, NAUTICAL_MILE
    }
}
