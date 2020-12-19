package com.example.testeioasys.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.testeioasys.R
import com.example.testeioasys.databinding.FragmentEmpresaBinding
import com.example.testeioasys.models.EnterpriseDto
import com.example.testeioasys.repository.NetworkUtil
import com.example.testeioasys.sharedpreferences.Preferences
import com.example.testeioasys.viewmodels.EmpresasViewModel
import com.squareup.picasso.Picasso


class Empresa : Fragment() {

    lateinit var binding: FragmentEmpresaBinding
    private lateinit var viewModel: EmpresasViewModel
    private lateinit var myPreferences: Preferences
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmpresaBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(EmpresasViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myPreferences = context?.let { Preferences(it) }!!
        navController = Navigation.findNavController(view)
        val empresa = getEmpresaByArgs()
        setLogoImage(empresa)
        configuraToolBar(empresa.enterpriseName)
        binding.fragmentEmpresaDescricao.text = empresa.description
    }

    private fun setLogoImage(empresa: EnterpriseDto) {
        Picasso.get()
            .load(NetworkUtil.DEV_HOST + empresa.photo)
            .fit()
            .placeholder(R.drawable.img_e_1)
            .into(binding.fragmentEmpresaLogo)
    }

    private fun getEmpresaByArgs(): EnterpriseDto {
        val args: EmpresaArgs by navArgs()
        val empresa = args.empresa
        return empresa
    }

    fun configuraToolBar(title: String) {
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.menu?.clear()
        toolbar?.title = title
        toolbar?.findViewById<ImageView>(R.id.toolbarLogo)!!.visibility = View.GONE
        toolbar?.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        toolbar?.setNavigationOnClickListener {
            navController?.navigate(R.id.action_telaEmpresa_to_telaHome)
        }
    }
}