package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Action
        action()
    }

    private fun action() {
        binding.buttonCalculate.setOnClickListener{calculateTip()}
        binding.editCost.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
    }

    private fun calculateTip() {
        //get input cost
        val stringInTextField =  binding.editCost.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        //display result if cost is null or value = 0
        if(cost == null || cost == 0.0){
            displayTip(0.0)
            return
        }

        //get input tip percentage
        val tipPercentage = when(binding.radioGroupOptions.checkedRadioButtonId) {
            R.id.radio_amazing -> 0.20
            R.id.radio_good -> 0.18
            else -> 0.15
        }

        var tip = cost * tipPercentage
        //round up the tip
        if (binding.switchRoundUp.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        displayTip(tip)

    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}