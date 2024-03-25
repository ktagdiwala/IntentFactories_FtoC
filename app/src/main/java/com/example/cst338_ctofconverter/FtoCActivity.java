package com.example.cst338_ctofconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cst338_ctofconverter.Utils.Converters;
import com.example.cst338_ctofconverter.databinding.ActivityFtoCactivityBinding;

import java.util.Locale;

public class FtoCActivity extends AppCompatActivity {

    //key used to retrieve a value (like in HashMaps)
    private static final String CONVERTED_VALUE_EXTRA_KEY = "FtoCActivity_Received_value";

    ActivityFtoCactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFtoCactivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //sets a variable equal to the fahrenheit output value from the CtoF converter
        double fahrenheit = getIntent().getDoubleExtra(CONVERTED_VALUE_EXTRA_KEY, 0.0);
        //sets the EditText textbox to the fahrenheit variable (thereby carrying-over the output from the CtoF converter)
        binding.FtoCEditText.setText(String.format(Locale.ENGLISH,"%.2f",fahrenheit));

        binding.fahrenheitConvertButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = MainActivity.MainActivityIntentFactory(getApplicationContext(), convertValue());
                startActivity(intent);
                return false;
            }
        });
    }

    public static Intent fToCIntentFactory(Context context, double fahrenheitVal){
        //Used to switch from one activity to another
        Intent intent = new Intent(context, FtoCActivity.class);

        //fahrenheitVal is stored as an extra in the intent
        intent.putExtra(CONVERTED_VALUE_EXTRA_KEY, fahrenheitVal);
        return intent;
    }

    private double convertValue(){
        String enteredValue = binding.FtoCEditText.getText().toString();
        double valueToConvert = 0;
        if(!enteredValue.isEmpty()){
            valueToConvert = Double.parseDouble(enteredValue);
        }
        valueToConvert = Converters.fahrenheitToCelsius(valueToConvert);

        return valueToConvert;
    }
    public void displayConvertedValue(View view) {
        binding.fahrenheitConvertedValueTextView.setText(
                //R is a built-in object that allows us to to access resources
                getString(R.string.output_degrees_Celsius, convertValue())
        );
    }

}