package com.example.medicalappointments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.medicalappointments.data.models.SpecialtyType


class MainActivity : AppCompatActivity() {
//    CREATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    findViewById<Button>(R.id.btn_welcome).setOnClickListener {
        goToLogInActivity()
        //changeDirection()

        Log.e("TAG", "SetOnClickListener")
    }

    Log.e("TAG", "onCreate")
    }

//    START
    override fun onStart() {
        super.onStart()

        Log.e("TAG", "onStart")
    }

//    RESUME
    override fun onResume() {
        super.onResume()

        Log.e("TAG", "onResume")
    }

//    PAUSE
    override fun onPause() {
        super.onPause()

        Log.e("TAG", "onPause")
    }

//    STOP
    override fun onStop() {
        super.onStop()

        Log.e("TAG", "onStop")
    }

//    DESTROY
    override fun onDestroy() {
        super.onDestroy()

        Log.e("TAG", "onDestroy")
    }

//    REDIRECT TO LOG IN SCREEN FUNCTION
    private fun goToLogInActivity() {
        val intent = Intent(this, ControllerActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun changeDirection(){
        findViewById<TextView>(R.id.textView2).text = getString(SpecialtyType.entries.random().specialty)
    }
}