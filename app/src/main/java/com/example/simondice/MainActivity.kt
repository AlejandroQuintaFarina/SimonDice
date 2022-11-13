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

//Método para resetear el juego y tu marcador

    private fun reset(){
        for(i in 0..(ronda-1)){
            numbersArray[i]=0
        }
        score.text = "Score: "
    }

    //Método para los colores

    private fun colorRandom(){
        booleanBotones=false
        activarBotones(booleanBotones)

        for(i in 0..ronda){
            val randomValues = Random().nextInt(4) +1
            numbersArray.set((ronda-1),randomValues)
        }
        enterNumbers(numbersArray)
        Log.d("array:", Arrays.toString(numbersArray))


        jobColores()
    }

    private fun jobColores(){
        val jobColores = GlobalScope.launch(Dispatchers.Main) {

            for (i in 0..ronda){
                when (numbersArray[i]){
                    1-> {
                        btnRojo.setBackgroundColor(resources.getColor(R.color.red2))
                        delay(750)
                        btnRojo.setBackgroundColor(resources.getColor(R.color.red))
                        delay(100)
                    }
                    2->{
                        btnAzul.setBackgroundColor(resources.getColor(R.color.blue2))
                        delay(750)
                        btnAzul.setBackgroundColor(resources.getColor(R.color.blue))
                        delay(100)
                    }
                    3->{
                        btnVerde.setBackgroundColor(resources.getColor(R.color.green2))
                        delay(750)
                        btnVerde.setBackgroundColor(resources.getColor(R.color.green))
                        delay(100)
                    }
                    4->{
                        btnAmarillo.setBackgroundColor(resources.getColor(R.color.yellow2))
                        delay(750)
                        btnAmarillo.setBackgroundColor(resources.getColor(R.color.yellow))
                        delay(100)
                    }
                }
            }
            booleanBotones=true
            activarBotones(booleanBotones)
        }
        jobColores
    }



    private fun enterNumbers(numbersArray: IntArray){
        btnRojo.setOnClickListener(){

            if(booleanBotones){
                Log.d("botónpresionado:","1")
                numeroIntroducido=1
                comprobante()
            }
        }
        btnAzul.setOnClickListener(){

            if(booleanBotones){
                Log.d("botónpresionado:","2")
                numeroIntroducido=2
                comprobante()
            }

        }
        btnVerde.setOnClickListener(){

            if(booleanBotones){
                Log.d("botónpresionado:","3")
                numeroIntroducido=3
                comprobante()
            }

        }
        btnAmarillo.setOnClickListener(){

            if(booleanBotones){
                Log.d("botónpresionado:","4")
                numeroIntroducido=4
                comprobante()
            }
        }
    }

        //comprueba si la secuencia introducida por el jugador es la correcta, si falla le sale un mensaje de derrota y la puntuacion obtenida

    private fun comprobante(){
        if(numeroIntroducido == numbersArray[contador] && contador != ronda){
            contador+=1

            if(contador==ronda){
                comprobante()
            }
        }else if(contador == ronda || numeroIntroducido == numbersArray[contador]){
            ronda+=1
            puntuacion+=10
            score.text = "Score: " + puntuacion
            contador=0
            colorRandom()
        }else{
            Toast.makeText(this, "Has hecho " + puntuacion + " puntos!",Toast.LENGTH_LONG).show()
            reset()
            saveRecord()
            loadRecord()
            btnIniciar.setVisibility(View.VISIBLE)
            puntuacion=0
            ronda=1
            contador=0
        }
    }

    //Metodo para activar los botones

    fun activarBotones(boolean: Boolean){
        btnRojo.isClickable == boolean
        btnAzul.isClickable == boolean
        btnVerde.isClickable == boolean
        btnAmarillo.isClickable == boolean

    }


    private fun saveRecord(){

        if(puntuacion>puntuacion2){
            puntuacion2 = puntuacion
            val sharedPreferences = getSharedPreferences("record", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply(){
                putInt("INT_KEY",puntuacion2)
            }.apply()
        }
        Toast.makeText(this,"Se ha establecido un nuevo record!",Toast.LENGTH_LONG)
    }

    private fun loadRecord(){
        val sharedPreferences = getSharedPreferences("record", Context.MODE_PRIVATE)
        val savedRecord = sharedPreferences.getInt("INT_KEY",0)
        record.text = "Record: " + savedRecord
    }
}

