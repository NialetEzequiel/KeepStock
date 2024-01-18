package com.nialet.keepstock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.nialet.keepstock.R.*

class FamilyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_family)

        val db = Firebase.firestore
        val ProductoBase: TextView = findViewById(id.ProductoBase)
        val cargarButton: Button = findViewById(id.cargarButton)

        cargarButton.setOnClickListener {
            db.collection("Productos").document("Mesa Madera").get().addOnSuccessListener {
            ProductoBase.setText(it.get("Referencia")as String?)
            }
        }
    }
}