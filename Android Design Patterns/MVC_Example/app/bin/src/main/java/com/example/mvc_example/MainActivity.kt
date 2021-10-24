package com.example.mvc_example

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var textValue: TextView;
    private  var count: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnPlus: ImageButton = findViewById(R.id.btn_plus);
        var btnMinus: ImageButton = findViewById(R.id.btn_minus);
        textValue = findViewById(R.id.value);
        textValue.setText("$count");

        btnPlus.setOnClickListener {
            Toast.makeText(this@MainActivity, "+1", Toast.LENGTH_SHORT).show()
            textValue.setText(Model().getValue(count, 1).toString())
            count = Model().getValue(count, 1);
        }

        btnMinus.setOnClickListener {
            Toast.makeText(this@MainActivity, "-1", Toast.LENGTH_SHORT).show()
            textValue.setText(Model().getValue(count, -1).toString())
            count = Model().getValue(count, -1);
        }
    }
}