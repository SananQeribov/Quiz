package com.example.quiz2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.quiz2.databinding.ActivityMainBinding
import java.util.Timer
import java.util.TimerTask


class MainActivity : AppCompatActivity() {
    private lateinit var quiztimer: Timer
    private var totaltimemins = 1
    private var seconds = 0
    private var Selectedtopic = " "
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chemlayout = binding.chlayout
        val mtlayout = binding.mathlayout
        val bglayout = binding.biologylayout
        val phylayout = binding.physicslayout
        val button = binding.bt
        chemlayout.setOnClickListener {
            Selectedtopic = "chemistry"
            chemlayout.setBackgroundResource(R.drawable.round_back_stroke_white10)
            mtlayout.setBackgroundResource(R.drawable.raunded)
            bglayout.setBackgroundResource(R.drawable.raunded)
            phylayout.setBackgroundResource(R.drawable.raunded)

        }
        mtlayout.setOnClickListener {
            Selectedtopic = "Math"
            mtlayout.setBackgroundResource(R.drawable.round_back_stroke_white10)
            chemlayout.setBackgroundResource(R.drawable.raunded)
            bglayout.setBackgroundResource(R.drawable.raunded)
            phylayout.setBackgroundResource(R.drawable.raunded)

        }

        bglayout.setOnClickListener {
            Selectedtopic = "biology"
            bglayout.setBackgroundResource(R.drawable.round_back_stroke_white10)
            mtlayout.setBackgroundResource(R.drawable.raunded)
            chemlayout.setBackgroundResource(R.drawable.raunded)
            phylayout.setBackgroundResource(R.drawable.raunded)

        }
        phylayout.setOnClickListener {
            Selectedtopic = "physics"
            phylayout.setBackgroundResource(R.drawable.round_back_stroke_white10)
            mtlayout.setBackgroundResource(R.drawable.raunded)
            bglayout.setBackgroundResource(R.drawable.raunded)
            chemlayout.setBackgroundResource(R.drawable.raunded)

        }
        button.setOnClickListener {
            if (Selectedtopic.isEmpty()) {
                Toast.makeText(this, "Please select the topic", Toast.LENGTH_SHORT).show()
            } else {
                var intent = Intent(this, Quizactivity::class.java)
                intent.putExtra("selectTopic", Selectedtopic)
                startActivity(intent)


            }
        }
    }


}
