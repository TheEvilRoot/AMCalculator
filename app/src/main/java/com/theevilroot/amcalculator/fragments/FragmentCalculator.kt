package com.theevilroot.amcalculator.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.theevilroot.amcalculator.MainActivity
import com.theevilroot.amcalculator.R
import com.theevilroot.amcalculator.Result
import com.theevilroot.amcalculator.TheHolder
import com.udojava.evalex.Expression
import java.math.RoundingMode

class FragmentCalculator: Fragment(), View.OnClickListener {

    private lateinit var output: EditText
    private lateinit var subOutput: TextView

    private var showResult = false

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View) {
        val button = view as? Button ?: return
        when(button.text) {
            in "0123456789" -> {
                if(showResult || output.text.toString().replace("0", "").isBlank())
                    output.setText(button.text)
                else if(!output.text.toString().replace("0", "").isBlank() || button.text != "0")
                        output.append(button.text)
            }
            "." -> {
                if(output.text.last().toString() != ".")
                    output.append(button.text)
            }
            in "÷×-+^" -> {
                if(output.text.last().toString() in "÷×-+^")
                    output.setText(output.text.dropLast(1).toString() + button.text.toString())
                else
                    output.append(button.text)
            }
            "C" -> {
                with(output.text.dropLast(1)) {
                    if(this.isEmpty())
                        output.setText("0")
                    else
                        output.setText(this)
                }
            }
            "=" -> {
                subOutput.text = output.text.toString() + "="
                val expr = formatExpression(output.text.toString())
                val result = eval(expr)
                output.setText(result)
                TheHolder.history.add(Result(expr, result))
                (TheHolder.fragments[1] as FragmentHistory).updateUI()
            }
            "pi" -> {
                if(output.text.last() !in "÷×-+^")
                    output.append("×π")
                else
                    output.append("π")
            }
        }
        if(button.text != "=") {
            showResult = false
            subOutput.text = ""
        }
    }

    private fun eval(expr: String): String =
            try{ Expression(expr).setPrecision(128).setRoundingMode(RoundingMode.FLOOR).eval(false).toString() } catch (e: Exception) { "ERROR" }

    private fun formatExpression(str: String): String =
            str.replace("÷", "/").replace("×", "*").replace("π", "PI")

    private lateinit var activity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_calculator, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        with(view) {
            output = findViewById(R.id.calc_output_view)
            subOutput = findViewById(R.id.sub_output_view)
            findButton(R.id.zero).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.one).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.two).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.three).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.four).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.five).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.six).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.seven).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.eight).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.nine).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.c).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.s).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.prec).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.devide).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.multiply).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.subtract).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.plus).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.equals).setOnClickListener(this@FragmentCalculator)
            findButton(R.id.dot).setOnClickListener(this@FragmentCalculator)

            findButton(R.id.c).setOnLongClickListener {
                output.setText("0")
                true
            }
        }
    }

    private fun View.findButton(@IdRes id: Int): Button =
            findViewById(id)

    companion object {
        fun create(activity: MainActivity): FragmentCalculator{
            val fragment = FragmentCalculator()
            fragment.activity = activity
            return fragment
        }
    }
}

