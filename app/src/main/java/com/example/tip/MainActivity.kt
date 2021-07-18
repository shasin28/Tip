package com.example.tip

//import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tip.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }
    private fun calculateTip()
    {
        val stringInTextField=binding.costOfServices.text.toString()
        val cost=stringInTextField.toDoubleOrNull()
        if(cost==null||cost==0.0)
        {
         displayTip(0.0)
            return
        }
        val tipPercentage=when(binding.tipOptions.checkedRadioButtonId)
        {
            R.id.option_twenty_percent->0.20
            R.id.option_fifteen_percent->.15
            else->.10

        }
        var tip=tipPercentage*cost
        if(binding.roundUpSwitch.isChecked)
        {
            tip=kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }
    private fun displayTip(tip:Double)
    {
        val formattedTip=NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text=getString(R.string.tip_amount,formattedTip)


    }

}