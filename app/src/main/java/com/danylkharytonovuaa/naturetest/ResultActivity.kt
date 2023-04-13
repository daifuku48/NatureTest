package com.danylkharytonovuaa.naturetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danylkharytonovuaa.naturetest.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    var binding : ActivityResultBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val intent = intent
        val total = intent.getIntExtra("total", 0)
        val percentage = ((total.toDouble() / 5.0)  * 100.0).toString()
        binding?.percentageTextView?.text = "Percentage: $percentage %"
        if (total < 3)
        {
            binding?.gradeImage?.setImageResource(R.drawable.bad_mark)
        } else
            binding?.gradeImage?.setImageResource(R.drawable.good_mark)

        val grade = when(total)
        {
            0 -> 0
            1 -> 2
            2 -> 4
            3 -> 6
            4 -> 8
            5 -> 10
            else -> 0
        }
        binding?.gradeTextView?.text = "Grade: $grade / 10"
        binding?.buttonToBack?.setOnClickListener {
            val intentBack = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intentBack)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}