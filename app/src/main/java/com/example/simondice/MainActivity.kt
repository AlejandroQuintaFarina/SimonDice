package com.example.simondice


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var ronda = 1
    private var numbersArray= IntArray(1000)
    private var contador=0
    private var puntuacion=0
    private var booleanBotones: Boolean = true
    var numerointroducido:Int = 0

    lateinit var btnRojo:Button
    lateinit var btnAzul:Button
    lateinit var btnVerde:Button
    lateinit var btnAmarillo:Button
    lateinit var btnIniciar:Button
    lateinit var score:TextView
    lateinit var record: TextView
    var puntuacion2: Int=0
    }
