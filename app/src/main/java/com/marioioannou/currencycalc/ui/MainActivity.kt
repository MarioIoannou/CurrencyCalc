package com.marioioannou.currencycalc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marioioannou.currencycalc.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastDigit: Boolean = false
    private var operation = false
    private var dot: Boolean = true
//    private val addOperation : Boolean = false

    val operationList: MutableList<String> = arrayListOf()
    val numberCache: MutableList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        number()
        operators()
        clearAll()
        delete()
        mathOperations()
    }

    private fun number() {
        binding.apply {
            btnDigit0.setOnClickListener { calculator("0", true) }
            btnDigit1.setOnClickListener { calculator("1", true) }
            btnDigit2.setOnClickListener { calculator("2", true) }
            btnDigit3.setOnClickListener { calculator("3", true) }
            btnDigit4.setOnClickListener { calculator("4", true) }
            btnDigit5.setOnClickListener { calculator("5", true) }
            btnDigit6.setOnClickListener { calculator("6", true) }
            btnDigit7.setOnClickListener { calculator("7", true) }
            btnDigit8.setOnClickListener { calculator("8", true) }
            btnDigit9.setOnClickListener { calculator("9", true) }
            btnDigitDot.setOnClickListener { calculator(".", true) }
        }
    }

    private fun delete() {
        binding.btnDigitAc.setOnClickListener {
            val expression = binding.tvInput.text.toString()
            if (expression.isNotBlank()) {
                binding.tvInput.text = expression.substring(0, expression.length - 1)
            }
            binding.tvResult.text = ""
        }
    }

    private fun clearAll() {
        binding.btnDigitAc.setOnClickListener {
            binding.tvInput.text = ""
            binding.tvResult.text = ""
        }
    }

    private fun operators() {
        binding.apply {
            btnDigitPlus.setOnClickListener { calculator("+", false) }
            btnDigitMinus.setOnClickListener { calculator("-", false) }
            btnDigitMultiply.setOnClickListener { calculator("x", false) }
            btnDigitDiv.setOnClickListener { calculator("/", false) }
        }
    }

    private fun mathOperations() {
        binding.apply {
            btnDigitResult.setOnClickListener {
                val input = ExpressionBuilder(tvInput.text.toString()).build()
                val result = input.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    tvResult.text = longResult.toString()
                } else {
                    tvResult.text = result.toString()
                }
            }
        }
    }

    private fun calculator(data: String, clearData: Boolean) {
        if (binding.tvResult.text.isNotEmpty()) {
            binding.tvInput.text = ""
        }
        if (clearData) {
            binding.tvResult.text = ""
            binding.tvInput.append(data)
        } else {
            binding.tvInput.append(binding.tvResult.text)
            binding.tvInput.append(data)
            binding.tvResult.text = ""
        }
    }
}
