package com.example.mvp_example

class Presenter: Interface.Presenter{
    override fun getValue(value: Int, option: Int): Int {
        var changeValue: Int = value;
        if(option == -1){
            changeValue -= 1;
            return Model().getValue(changeValue);
        };
        else if(option == 1){
            changeValue += 1;
            return Model().getValue(changeValue);
        };
        return 0;
    }
}