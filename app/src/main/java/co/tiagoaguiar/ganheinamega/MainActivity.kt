package co.tiagoaguiar.ganheinamega

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.math.log
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //aqui o app ganha vida
        setContentView(R.layout.activity_main)

        // DADOS UNICOS DE PREFERENCIAS
        // É UTILICADO QUANDO QUEREMOS UTILIZAR INFORMAÇÕES PEQUENAS ( nesse caso não se torna nesseçario o uso do SQL)
        // EXEMPLO = QUANDO QUEREMOS ARMAZENAR O RESULTADO DO NUMERO GERADO,CONTA DE ACESSO DE LOGIN,TOKEN E ETC,PREFERENCIA DE IDIOMA)



        // buscar os objetos e ter a referencia deles
        val editText: EditText = findViewById(R.id.edit_number)

        val txtresult: TextView = findViewById(R.id.txt_result)

        val btnGenerate: Button = findViewById(R.id.btn_generate)


        prefs = getSharedPreferences("db", Context.MODE_PRIVATE)
        val result = prefs.getString("result", null )
        if (result != null) {
            txtresult.text = "Ultima aposta $result"
        }

        btnGenerate.setOnClickListener {

            val text = editText.text.toString()

            numberGenerator(text, txtresult)

        }

    }

    private fun numberGenerator(text: String, txtResult: TextView) {
        if (text.isEmpty()) {
            Toast.makeText(this, "Informe um numero entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        val qtd = text.toInt() // converte string para inteiro

        //FALHA NUMERO 1
        if (qtd < 6 || qtd > 15) {
            Toast.makeText(this, "Informe um numero entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }


        val numbers = mutableSetOf<Int>()
        val random = Random()

        while (true) {
            val number = random.nextInt(60)
            numbers.add(number + 1)

            if (numbers.size == qtd) {
                break

            }

        }
        txtResult.text = numbers.joinToString(" - ")

        val editor = prefs.edit()
        editor.putString("result", txtResult.text.toString())
        editor.apply()



    }
}






