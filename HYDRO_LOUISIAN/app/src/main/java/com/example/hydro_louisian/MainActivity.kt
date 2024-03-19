package com.example.hydro_louisian

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hydro_louisian.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)

         binding.btnCalculate.setOnClickListener {
            calculatedBill()
        }
         binding.btnReset.setOnClickListener {
             clearCalculator()
             binding.tvErrorMessage.text=""
             binding.tvErrorMessage.setBackgroundColor(Color.parseColor("#FFFEF7FF"))

         }
         binding.btnCalculate.setBackgroundColor(Color.parseColor("#000000"))

    }
     private  fun calculatedBill() {

        //gets values from UI
        val morningUsageUI: String = binding.etMorningUsage.text.toString()
        val eveningUsageUI: String = binding.etEveningUsage.text.toString()

        //checks if the fields are empty
         if (morningUsageUI.isEmpty() || eveningUsageUI.isEmpty()) {
             binding.tvErrorMessage.setBackgroundColor(Color.parseColor("#FFEDA7A7"))
             binding.tvErrorMessage.text = "ERROR: All Fields must be filled"
             return
         }
         // calculates values per unit
        val morningUsageVal: Double = morningUsageUI.toDouble() * 0.132
        val eveningUsageVal: Double = eveningUsageUI.toDouble() * 0.094

         //calculates total bill charge
        val totalUsageCharge = morningUsageVal + eveningUsageVal

         // calculates environmental Rebate
         val environmentalRebate = if (binding.switch1.isChecked)
         {
             totalUsageCharge * 0.09
         } else {
             totalUsageCharge * 0.0
         }

         // calculates subTotal Value
         val subTotal: Double= totalUsageCharge - environmentalRebate

         // calculates sales Taxes
         val salesTaxes :Double= totalUsageCharge *0.13

        // final payment
         val finalPay :Double= subTotal + salesTaxes
         binding.tvTotal.setBackgroundColor(Color.parseColor("#000000"))
         binding.tvTotal.setTextColor(Color.WHITE)

         binding.tvBillRecipt.text ="""
                    CHARGE BREAKDOWN
           Morning Usage Cost: $${morningUsageVal}
           Evening Usage Cost: $${eveningUsageVal}
           Total Usage Charge : $${totalUsageCharge}
           Environmental rebate: -${environmentalRebate}
           SubTotal : $${subTotal}
           Sales Tax: $${salesTaxes}
       """.trimIndent()

         binding.tvTotal.text=" YOU MUST PAY: $${finalPay}"

     }

    private fun clearCalculator(){
        binding.etMorningUsage.text.clear()
        binding.etEveningUsage.text.clear()
        binding.tvBillRecipt.text=""
        binding.tvTotal.text=""
        binding.tvTotal.setBackgroundColor(Color.parseColor("#FFFEF7FF"))
    }
}