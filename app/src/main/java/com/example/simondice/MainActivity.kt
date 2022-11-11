package com.example.simondice


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alejandro.simon_dice_alejandro.R
import kotlinx.coroutines.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var ronda = 1
    private var numbersArray= IntArray(1000)
    private var contador=0
    private var puntuacion=0
    private var booleanBotones: Boolean = true
    var numeroIntroducido:Int = 0

    lateinit var btnRojo:Button
    lateinit var btnAzul:Button
    lateinit var btnVerde:Button
    lateinit var btnAmarillo:Button
    lateinit var btnIniciar:Button
    lateinit var score:TextView
    lateinit var record: TextView
    var puntuacion2: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnIniciar= findViewById(R.id.btn_Inicio)
        btnRojo=findViewById(R.id.btn_rojo)
        btnAzul=findViewById(R.id.btn_azul)
        btnVerde=findViewById(R.id.btn_verde)
        btnAmarillo=findViewById(R.id.btn_amarillo)
        score = findViewById(R.id.score)
        record = findViewById(R.id.record)
        loadRecord()
        btnIniciar.setOnClickListener(object : View.OnClickListener{


            override fun onClick(p0: View?) {
                inicio()
                btnIniciar.setVisibility(View.GONE)
                Log.d("Lehasdadoclick","adsf")
            }
        })

    }

    private fun inicio(){
        colorRandom()
    }


}

