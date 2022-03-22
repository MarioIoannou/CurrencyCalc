package com.marioioannou.currencycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.marioioannou.currencycalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastDigit: Boolean = false
    private var dot: Boolean = false
//    private val addOperation : Boolean = false

    val operationList: MutableList<String> = arrayListOf()
    val numberCache: MutableList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateDisplay("")
//        binding.btndigit0.setOnClickListener {
//            Toast.makeText(this,"0",Toast.LENGTH_SHORT).show()
//        }
    }

    fun makeString(list: List<String>,joiner: String = "") : String {
        if (list.isEmpty()) return ""
        return list.reduce { r, s -> r + joiner + s }
    }

    fun clearCache() {
        numberCache.clear()
        operationList.clear()
    }

    fun clearClick(view: View) {
        clearCache()
        updateDisplay("");
    }

    fun updateDisplay(mainDisplayString: String){
        val fullCalculationString = makeString(operationList, " ")
        binding.tvInput.text = mainDisplayString
        binding.tvResult.text = fullCalculationString
    }

    fun equalsClick(view: View) {
        operationList.add(makeString(numberCache))
        numberCache.clear()

        val calculator = StringCalculator()
        val answer = calculator.calculate(operationList)

        updateDisplay("=" + answer.toString())
        clearCache()
    }

    fun negateNumber(view: View){
        if (numberCache.isNotEmpty()) {
            if (numberCache.first().equals("-")) {
                numberCache.removeAt(0)
            } else numberCache.add(0, "-")
        } else numberCache.add("-")

        val numberString = makeString(numberCache)
        updateDisplay(numberString)
    }

    fun buttonClick(view: View) {

        val button = view as Button

        if (numberCache.isEmpty()) return

        operationList.add(makeString(numberCache))
        numberCache.clear()
        operationList.add(button.text.toString())

        updateDisplay(button.text.toString())
    }

    fun numberClick(view: View) {
        val button = view as Button
        val numberString = button.text;

        numberCache.add(numberString.toString())
        val text = makeString(numberCache);
        updateDisplay(text)
    }

    fun backSpace(view: View){
        val length = binding.tvInput.length()
        if (length >0){
            binding.tvInput.text = binding.tvInput.text.subSequence(0,length -1)
        }
    }

    fun decimal(view: View){
        if(lastDigit && !dot){
            binding.tvInput.append(".")
            lastDigit = false
            dot = true
        }
    }
//    fun digit(view : View){
//        binding.tvInput.append((view as Button).text)
//        lastDigit = true
//        addOperation = true
//    }
//
//    private fun operator(value: String): Boolean{
//        return if(value.startsWith("-")){
//            false
//        }else{
//            value.contains("")
//        }
//    }
//
//    fun clear(view: View){
//        binding.tvInput.text = ""
//        lastDigit = false
//        dot = false
//    }
//
//    fun backSpace(view: View){
//        val length = binding.tvInput.length()
//        if (length >0){
//            binding.tvInput.text = binding.tvInput.text.subSequence(0,length -1)
//        }
//    }
//
//    fun decimal(view: View){
//        if(lastDigit && !dot){
//            binding.tvInput.append(".")
//            lastDigit = false
//            dot = true
//        }
//    }


}

class StringCalculator {

    fun calculate(calculationList: List<String>): Int {
        var currentOp: String = ""
        var currentNumber = 0

        calculationList.forEach { token ->
            when {
                token.equals("+")
                        || token.equals("/")
                        || token.equals("*")
                        || token.equals("-") -> currentOp = token

                currentOp.equals("-") -> currentNumber -= token.toInt()
                currentOp.equals("/") -> currentNumber /= token.toInt()
                currentOp.equals("*") -> currentNumber *= token.toInt()
                else -> currentNumber += token.toInt()

            }
        }

        return currentNumber
    }
}