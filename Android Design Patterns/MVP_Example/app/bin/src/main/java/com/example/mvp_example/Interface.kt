package com.example.mvp_example

interface Interface {
    interface View{
        fun showResult(count: Int);
    }

    interface Presenter{
        fun getValue(value: Int, option: Int): Int;
    }
}