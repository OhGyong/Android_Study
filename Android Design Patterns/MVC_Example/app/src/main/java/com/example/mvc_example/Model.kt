package com.example.mvc_example

class Model(value: Int, option: Int) {
    private var modelValue = value;
    private var modelOption = option;

    fun getValue(): Int{

        if(modelOption == -1) return --modelValue;
        else if(modelOption == 1) return ++modelValue;
        return 0;
    }

}