package com.example.mvc_example

class Model() {
    fun getValue(value: Int, option: Int): Int{
        var modelValue = value;
        var modelOption = option;

        if(modelOption == -1) return --modelValue;
        else if(modelOption == 1) return ++modelValue;
        return 0;
    }
}