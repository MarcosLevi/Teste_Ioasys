package com.example.testeioasys.viewholders

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.testeioasys.databinding.CardEmpresaBinding

class CardEmpresaViewHolder(binding: CardEmpresaBinding) : RecyclerView.ViewHolder(binding.root) {

    var logo: ImageView = binding.cardEmpresaLogo
    var nomeEmpresa: TextView = binding.cardEmpresaNome
    var tipoEmpresa: TextView = binding.cardEmpresaTipo
    var pais: TextView = binding.cardEmpresaLocal
    var card: CardView = binding.cardEmpresa
}
