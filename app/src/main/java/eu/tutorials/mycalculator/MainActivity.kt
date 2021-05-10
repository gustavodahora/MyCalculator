package eu.tutorials.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import eu.tutorials.mycalculator.databinding.ActivityMainBinding
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun clear(view: View) {
        binding.tvInput.text = " "
        lastDot = false
        lastNumeric = false
    }

    fun onDigit(view: View) {
        binding.tvInput.append((view as Button).text)
        lastNumeric = true

//        if(binding.tvInput.text.contains("1")) {
//            binding.tvInput.text = "haha"
//        }
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = binding.tvInput.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    // 99 - 1
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if (!prefix.isEmpty()) {
                        one += prefix
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                } else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    // 99 - 1
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if (!prefix.isEmpty()) {
                        one += prefix
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                } else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    // 99 - 1
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if (!prefix.isEmpty()) {
                        one += prefix
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                } else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    // 99 - 1
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if (!prefix.isEmpty()) {
                        one += prefix
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String {
        var value = result
        if(result.contains(".0")) {
            value = result.substring(0, result.length - 2)
            return value
        }
        return value
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(binding.tvInput.text.toString())) {
            binding.tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+")
                    || value.contains("-")
        }
    }
}