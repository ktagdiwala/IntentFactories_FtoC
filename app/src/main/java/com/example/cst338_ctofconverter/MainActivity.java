package com.example.cst338_ctofconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cst338_ctofconverter.Utils.Converters;
import com.example.cst338_ctofconverter.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String CONVERTED_VALUE_EXTRA_KEY = "MainActivity_Converted_value_double";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //gets a reference to the xml object
        //calling inflate converts xml into java references we can use
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //an object representation of the view-- tells Java where to start looking at the display elements
        View view = binding.getRoot();

        //the manipulated content is referenced by this
        setContentView(view);

        //sets a variable equal to the celsius output value from the CtoF converter (or 0.00 by default)
        double celsius = getIntent().getDoubleExtra(CONVERTED_VALUE_EXTRA_KEY, 0.0);
        //sets the EditText textbox to the celsius variable (thereby carrying-over the output from the FtoC converter)
        binding.MainActivityEditText.setText(String.format(Locale.ENGLISH,"%.2f",celsius));


        //creates an anonymous inner class that implements an onclick listener
        binding.CtoFTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //defines what action will happen when this label is clicked
                Toast.makeText(MainActivity.this, "this works :D", Toast.LENGTH_SHORT).show();
            }
        });

        binding.celsiusConvertButton.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){

                Intent intent = FtoCActivity.fToCIntentFactory(getApplicationContext(), convertValue());
                startActivity(intent);
                return false;
            }
        });

    }

//    public static Intent cToFIntentFactory(Context context, double celsiusVal){
//        //Used to switch from one activity to another
//        Intent intent = new Intent(context, MainActivity.class);
//
//        //celsiusVal is stored as an extra in the intent
//        intent.putExtra(CONVERTED_VALUE_EXTRA_KEY, celsiusVal);
//        return intent;
//    }

    private double convertValue(){
        String enteredValue = binding.MainActivityEditText.getText().toString();
        double valueToConvert = 0;
        if(!enteredValue.isEmpty()){
            valueToConvert = Double.parseDouble(enteredValue);
        }
        valueToConvert = Converters.celsiusToFahrenheit(valueToConvert);

        return valueToConvert;
    }

    public void displayConvertedValue(View v){
        binding.celsiusConvertedValueTextView.setText(
                //R is a built-in object that allows us to to access resources
                getString(R.string.output_degrees_Fahrenheit, convertValue())
        );

    }

    public static Intent MainActivityIntentFactory(Context context, double receivedValue){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(CONVERTED_VALUE_EXTRA_KEY, receivedValue);
        return intent;
    }
}