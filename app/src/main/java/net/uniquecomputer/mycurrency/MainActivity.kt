package net.uniquecomputer.mycurrency

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.uniquecomputer.mycurrency.databinding.ActivityMainBinding
import kotlin.time.times

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var isCardOneVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.indiaCard.setOnClickListener {
            var input = binding.inputEt.text.toString()
            if (input.equals("") || input.toString().length <= 0) {
                binding.inputEt.error = "Invalid Number"
                Toast.makeText(this, "$input Invalid Number", Toast.LENGTH_SHORT).show()
            } else {
                convertInr()
            }
        }

        binding.usaCard.setOnClickListener {
            var input = binding.inputEt.text.toString()
            if (input.equals("") || input.toString().length <= 0) {
                Toast.makeText(this, "$input Invalid Number", Toast.LENGTH_SHORT).show()
            } else {
                convertUSd()
            }
        }
    }

    private fun convertInr() {

        var input = binding.inputEt.text.toString().toDouble()
        var getText = findViewById<TextView>(R.id.amount)
        var getCurrentValue = 83.32
        var finalValue = input*getCurrentValue
        binding.amount.visibility = View.VISIBLE
        animateTextView(getText,finalValue)
        getText.text = finalValue.toString()
    }


    private fun convertUSd(){
        var input = binding.inputEt.text.toString().toDouble()
        var getText = findViewById<TextView>(R.id.amount)
        var currentUSd = 0.012
        var finalUsd = currentUSd*input
        binding.amount.visibility = View.VISIBLE
        animateTextView(getText,finalUsd)
        getText.text = finalUsd.toString()
    }

    private fun animateTextView(textView: TextView, targetValue: Double) {
        val initialValue = 0.0

        // Create ValueAnimator
        val valueAnimator = ValueAnimator.ofFloat(initialValue.toFloat(), targetValue.toFloat())

        // Set duration
        valueAnimator.duration = 500 // Adjust duration as needed

        // Update TextView with animated values
        valueAnimator.addUpdateListener { animator ->
            val animatedValue = animator.animatedValue as Float
            textView.text = animatedValue.toString()
        }

        // Start animation
        valueAnimator.start()
    }

}

