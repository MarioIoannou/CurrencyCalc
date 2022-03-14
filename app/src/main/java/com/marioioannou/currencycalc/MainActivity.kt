package com.marioioannou.currencycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.marioioannou.currencycalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnDigit0.setOnClickListener {
//            Toast.makeText(this,"0",Toast.LENGTH_SHORT).show()
//        }
    }

    fun onDigit(view : View){

    }
}