package com.example.testeioasys.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeioasys.R
import com.example.testeioasys.databinding.CardEmpresaBinding
import com.example.testeioasys.models.EnterpriseDto
import com.example.testeioasys.repository.NetworkUtil
import com.example.testeioasys.viewholders.CardEmpresaViewHolder
import com.squareup.picasso.Picasso


class CardEmpresaAdapter(
    var listaCards: MutableList<EnterpriseDto>,
    private val cardListener: CardListener
) :
    RecyclerView.Adapter<CardEmpresaViewHolder>() {

    lateinit var bindingCampanha: CardEmpresaBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardEmpresaViewHolder {
        bindingCampanha =
            CardEmpresaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardEmpresaViewHolder(
            bindingCampanha
        )
    }

    override fun getItemCount(): Int {
        return listaCards.size
    }

    override fun onBindViewHolder(holderFinal: CardEmpresaViewHolder, position: Int) {
        val currentItem: EnterpriseDto = listaCards.get(position)
        Picasso.get()
            .load(NetworkUtil.DEV_HOST + currentItem.photo)
            .placeholder(R.drawable.img_e_1)
            .into(holderFinal.logo)
        holderFinal.nomeEmpresa.text = currentItem.enterpriseName
        holderFinal.tipoEmpresa.text = currentItem.enterpriseType.enterpriseTypeName
        holderFinal.pais.text = currentItem.country
        holderFinal.card.setOnClickListener {
            cardListener.passaEmpresa(currentItem)
        }
    }

    interface CardListener {
        fun passaEmpresa(empresa: EnterpriseDto)
    }

}