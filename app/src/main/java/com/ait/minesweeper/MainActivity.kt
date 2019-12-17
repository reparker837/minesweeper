package com.ait.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showText(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    fun isToggleOn() : Boolean {
        return toggleBtn.isChecked
    }
}
