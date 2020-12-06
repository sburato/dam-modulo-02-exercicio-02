package com.sburato.conversor

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.function.DoubleToLongFunction

class MainActivity : AppCompatActivity() {
    var unidadeEntrada: String = "";
    var unidadeSaida  : String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val medidas = resources.getStringArray(R.array.array_medidas)

        val adapter = ArrayAdapter(applicationContext,
            android.R.layout.simple_spinner_dropdown_item, medidas)

        sp_unidade_entrada.setAdapter(adapter)
        sp_unidade_saida.setAdapter(adapter)

        sp_unidade_entrada.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                unidadeEntrada = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) { }
        }

        sp_unidade_saida.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                unidadeSaida = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) { }
        }

        btnCalcular.setOnClickListener{
            if (!txtValor.text.toString().isNullOrEmpty()) {
                val valor = txtValor.text.toString().toDouble();
                val valorConvertido = converterMedidas(valor, unidadeEntrada, unidadeSaida)
                txtValorConvertido.text =
                    "${txtValor.text.toString().toDouble()} $unidadeEntrada = $valorConvertido $unidadeSaida"
            }
        }
    }

    fun converterMedidas(valor: Double, unEntrada: String, unSaida: String): Double {
        if (unEntrada == unSaida) {
            return valor
        } else if ((unEntrada == "KM") && (unSaida == "M")) {
            return valor * 1000f
        } else if ((unEntrada == "KM") && (unSaida == "CM")) {
            return valor * 100000f
        } else if ((unEntrada == "M") && (unSaida == "KM")) {
            return valor / 1000f
        } else if ((unEntrada == "M") && (unSaida == "CM")) {
            return valor * 100f
        } else if ((unEntrada == "CM") && (unSaida == "KM")) {
            return valor / 100000f
        } else if ((unEntrada == "CM") && (unSaida == "M")) {
            return valor / 100f
        } else {
            return 0.0
        }
    }
}
