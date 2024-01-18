package com.nialet.keepstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setup()
    }

    private fun setup() {
        val addStockbutton: Button = findViewById(R.id.addStockbutton);
        val removeStockbutton: Button = findViewById(R.id.removeStockbutton);
        val countStockButton: Button = findViewById(R.id.countStockButton);

        addStockbutton.setOnClickListener {
            val Intent = Intent(this, AddActivity::class.java)
            startActivity(Intent)
        }

        removeStockbutton.setOnClickListener {
            val Intent = Intent(this, removeActivity::class.java)
            startActivity(Intent)
        }
        countStockButton.setOnClickListener {
            val Intent = Intent(this, CountActivity::class.java)
            startActivity(Intent)
        }
    }
}