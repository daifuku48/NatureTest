package com.danylkharytonovuaa.naturetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import com.danylkharytonovuaa.naturetest.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    var binding : ActivityTestBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val list = ArrayList<Question>()

        var totalAnswer = 0
        list.add(Question("Soil degradation caused by water and wind is", "erosion", "soil formation",  1))
        list.add(Question("Biology is...", "the science of living and non-living nature", "the science of living nature", 2))
        list.add(Question("The structure of plants is studied by the science of", "ecology", "botany", 2))
        list.add(Question("What category does a hedgehog belong to?", "omnivores", "predators",  1))
        list.add(Question("Any objects around us are called", "material", "substance",  2))

        binding?.questionTextView?.text = list[0].question
        binding?.firstAnswerTextView?.text = list[0].answer1
        binding?.secondAnswerTextView?.text = list[0].answer2
        var count = 0

        binding?.firstRadioButton?.setOnCheckedChangeListener( object : CompoundButton.OnCheckedChangeListener
            {
                override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                    if (p1)
                        binding?.secondRadioButton?.isChecked = false
                }

            })

        binding?.secondRadioButton?.setOnCheckedChangeListener( object : CompoundButton.OnCheckedChangeListener
        {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                if (p1)
                     binding?.firstRadioButton?.isChecked = false
            }
        })

        binding?.buttonAnswer?.setOnClickListener {
            val first = binding?.firstRadioButton?.isChecked
            val second = binding?.secondRadioButton?.isChecked

            if (first != null)
            {
                if (first && list[count].rigth == 1)
                {
                    totalAnswer++
                }
            }
            if (second != null)
            {
                if (second && list[count].rigth == 2)
                {
                    totalAnswer++
                }
            }
            count++
            if (count == 5)
            {
                val intent = Intent(this@TestActivity, ResultActivity::class.java).apply {
                    putExtra("total", totalAnswer)
                }
                startActivity(intent)
            }
            if (count <= 4)
            {
                binding?.firstAnswerTextView?.text = list[count].answer1
                binding?.secondAnswerTextView?.text = list[count].answer2
                binding?.questionTextView?.text = list[count].question
                binding?.secondRadioButton?.isChecked = false
                binding?.firstRadioButton?.isChecked = false
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}