package com.fleon.criarpersonagensdevideogame.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import com.fleon.criarpersonagensdevideogame.R

class FormularioFragment : Fragment() {
    //referencias do layout. O lateinit é uma forma para não atribuir valor aqui e sim na onCreateView do fragment.
    lateinit var editTextNome: EditText
    lateinit var radioGroupRaca: RadioGroup
    lateinit var radioGrupoClasse: RadioGroup
    lateinit var seekBarPoder: SeekBar
    lateinit var textViewPoder: TextView
    lateinit var checkBoxVidaEterna: CheckBox
    lateinit var buttonConfirmar: Button

    var selectedRaca: String = ""   //Texto|Nome da raça selecionada
    var selectedClasse = ""         //Texto|Nome da clase selecionada
    var poder: Int = 0              //Poder selecionado na seekBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_formulario, container, false) //vinculação do arquivo xml
        //atribuição de valores dos componentes da view
        editTextNome = view.findViewById(R.id.editTextNome)
        radioGroupRaca = view.findViewById(R.id.radioGrupoRaca)
        radioGrupoClasse = view.findViewById(R.id.radioGroupClasse)
        seekBarPoder = view.findViewById(R.id.seekBarPoder)
        textViewPoder = view.findViewById(R.id.textViewPoder)
        checkBoxVidaEterna = view.findViewById(R.id.checkBoxVidaEterna)
        buttonConfirmar = view.findViewById(R.id.buttonConfirmar)

        //Configurar Listeners para os RadioGroups:
        radioGroupRaca.setOnCheckedChangeListener { _, checkedId ->
            //Obtener la string del RadioButton de raza seleccionado por el usuario.
            selectedRaca = radioGroupRaca.findViewById<RadioButton>(checkedId)?.text.toString()
        }

        radioGrupoClasse.setOnCheckedChangeListener { _, checkedId ->
            selectedClasse = radioGrupoClasse.findViewById<RadioButton>(checkedId).text.toString()
        }

        seekBarPoder.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                poder = progress
                textViewPoder.text = "Poder: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        buttonConfirmar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Confirmar Criação")
                .setMessage("Deseja confirmar a criação do personagem?")
                .setPositiveButton("Sim"){dialog, _ ->

                    //Enviar Bundle para el segundo Fragment: FichaFragment.kt
                    val fragment2 = FichaFragment.newInstance(
                        nome = editTextNome.text.toString(),
                        raca = selectedRaca,
                        classe = selectedClasse,
                        poder = seekBarPoder.progress,
                        vidaEterna = checkBoxVidaEterna.isChecked
                    )

                    //Sustituir el Fragmen actual por el segundo Fragment
                   parentFragmentManager.beginTransaction()
                       .replace(R.id.fragmentContainerView, fragment2)
                       .addToBackStack(null)
                       .commit()
                   dialog.dismiss()
                }
                .setNegativeButton("Não"){dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FormularioFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}