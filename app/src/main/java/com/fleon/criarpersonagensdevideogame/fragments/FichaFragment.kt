package com.fleon.criarpersonagensdevideogame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fleon.criarpersonagensdevideogame.R

class FichaFragment : Fragment() {

    var textViewNome: TextView? = null
    var textViewRaca: TextView? = null
    var textViewClasse: TextView? = null
    var textViewPoder: TextView? = null
    var textViewVidaEterna: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ficha, container, false)//vinculación del Layout

        textViewNome = view.findViewById(R.id.textViewNome)
        textViewRaca = view.findViewById(R.id.textViewRaca)
        textViewClasse = view.findViewById(R.id.textViewClasse)
        textViewPoder = view.findViewById(R.id.textViewPoder)
        textViewVidaEterna = view.findViewById(R.id.textViewVidaEterna)

        //Recibir datos del Bundle, inseridos a traves da función newInstance
        val nome = arguments?.getString("nome")?: ""
        val raca = arguments?.getString("raca")?: ""
        val classe = arguments?.getString("classe")?: ""
        val poder = arguments?.getInt("poder")?: 0
        val vidaEterna = if (arguments?.getBoolean("vidaEterna") == true) "sim" else "Não"

        //Mostrar datos en la pantalla
        textViewNome?.text = "Nome: $nome"
        textViewRaca?.text = "Raça: $raca"
        textViewClasse?.text = "Classe: $classe"
        textViewPoder?.text = "Poder: $poder"
        textViewVidaEterna?.text = "Possui Vida Eterna?: $vidaEterna"

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(
            nome: String,
            raca: String,
            classe: String,
            poder: Int,
            vidaEterna: Boolean
        ): FichaFragment {
            val fragment = FichaFragment()
            val argumentos = Bundle()

            argumentos.putString("nome", nome)
            argumentos.putString("raca", raca)
            argumentos.putString("classe", classe)
            argumentos.putInt("poder", poder)
            argumentos.putBoolean("vidaEterna", vidaEterna)

            fragment.arguments = argumentos
            return fragment
        }
    }
}