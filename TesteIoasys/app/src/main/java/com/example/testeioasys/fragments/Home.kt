package com.example.testeioasys.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testeioasys.R
import com.example.testeioasys.adapters.CardEmpresaAdapter
import com.example.testeioasys.databinding.FragmentHomeBinding
import com.example.testeioasys.models.EnterpriseDto
import com.example.testeioasys.sharedpreferences.Preferences
import com.example.testeioasys.viewholders.CardEmpresaViewHolder
import com.example.testeioasys.viewmodels.EmpresasViewModel


class Home : Fragment(), CardEmpresaAdapter.CardListener {

    lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: EmpresasViewModel
    private lateinit var myPreferences: Preferences
    lateinit var mRecyclerView: RecyclerView
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    lateinit var empresaAdapter: RecyclerView.Adapter<CardEmpresaViewHolder>
    private val listaEmpresas: MutableList<EnterpriseDto> = mutableListOf()
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(EmpresasViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myPreferences = context?.let { Preferences(it) }!!
        configuraToolBar()
        configuraRecyclerView()
        configuraCorBarra()
        navController = Navigation.findNavController(view)
        configuraObservers(view)
    }

    private fun configuraObservers(view: View) {
        viewModel.empresas.observe(viewLifecycleOwner, Observer {
            when (it.size) {
                0 -> {
                    binding.fragmentHomeTextNone.visibility = View.VISIBLE
                }
                else -> {
                    listaEmpresas.clear()
                    listaEmpresas.addAll(it)
                    empresaAdapter.notifyDataSetChanged()
                    view.hideKeyboard()
                }
            }
        })
    }

    private fun configuraRecyclerView() {
        mRecyclerView = binding.fragmentHomeListaEmpresas
        mRecyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(requireContext())
        empresaAdapter =
            CardEmpresaAdapter(
                listaEmpresas, this
            )
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = empresaAdapter
    }

    private fun configuraToolBar() {
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.menu?.clear()
        toolbar?.navigationIcon = null
        toolbar?.title = ""
        toolbar?.findViewById<ImageView>(R.id.toolbarLogo)!!.visibility = View.VISIBLE
        toolbar?.inflateMenu(R.menu.toolbar_menu)
        configuraSearchView(toolbar)
        toolbar!!.visibility = View.VISIBLE
    }

    private fun configuraSearchView(toolbar: Toolbar?) {
        val procurar = toolbar?.menu?.findItem(R.id.toolbar_search)
        procurar?.setOnMenuItemClickListener {
            binding.fragmentHomeTextInicial.visibility = View.GONE
            binding.fragmentHomeTextNone.visibility = View.GONE
            true
        }
        val searchView = procurar?.actionView as SearchView
        searchView.queryHint = "Integers: tipo/ Strings: nome"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(textoDeBusca: String): Boolean {
                carregaEmpresas(textoDeBusca)
                binding.fragmentHomeTextNone.visibility = View.GONE
                return true
            }

            override fun onQueryTextChange(novoTexto: String): Boolean {
                return true
            }

        })
    }

    private fun carregaEmpresas(parametro: String = "") {
        val acessToken = myPreferences.getAcessToken()
        val client = myPreferences.getClient()
        val uid = myPreferences.getUid()
        viewModel.carregaEmpresas(acessToken, client, uid, parametro)
    }

    private fun configuraCorBarra() {
        val barra = activity?.findViewById<View>(R.id.barra)
        barra!!.setBackgroundResource(R.color.bright_pink)
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun passaEmpresa(empresa: EnterpriseDto) {
        navController?.navigate(HomeDirections.actionTelaHomeToTelaEmpresa(empresa))
    }
}