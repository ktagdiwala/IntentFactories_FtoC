package com.example.cst338_ctofconverter.Utils;

/**
 * Fahrenheit = (C * 9/5) + 32
 * Celsius = (F - 32) * (5/9)
 */

public class Converters {
    public static double celsiusToFahrenheit(double celsius){
        return (celsius * (9.0/5.0)) + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit){
        return (fahrenheit - 32) * (5.0/9.0);
    }

}
