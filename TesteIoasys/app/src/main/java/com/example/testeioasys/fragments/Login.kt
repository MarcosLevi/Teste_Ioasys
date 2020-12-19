package com.example.testeioasys.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testeioasys.R
import com.example.testeioasys.databinding.FragmentLoginBinding
import com.example.testeioasys.models.UserLoginDto
import com.example.testeioasys.sharedpreferences.Preferences
import com.example.testeioasys.viewmodels.UserViewModel

class Login : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var myPreferences: Preferences
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myPreferences = context?.let { Preferences(it) }!!
        escondeToolbar()
        navController = Navigation.findNavController(view)
        configuraListenerSubmit(view)
        configuraObservers(view)
    }

    private fun configuraObservers(view: View) {
        viewModel.response.observe(viewLifecycleOwner, Observer {
            when (it.success) {
                true -> {
                    view.isEnabled = true
                    binding.fragmentLoginLoading.visibility = View.GONE
                    myPreferences.setAcessToken(it.acessToken!!)
                    myPreferences.setClient(it.client!!)
                    myPreferences.setUid(it.uid!!)
                    navController!!.navigate(R.id.action_telaLogin_to_telaHome)
                }
                false -> {
                    view.isEnabled = true
                    binding.fragmentLoginLoading.visibility = View.GONE
                    binding.fragmentLoginError.text = it.errors[0]
                    binding.fragmentLoginEmailLayout.error = it.errors[0]
                    binding.fragmentLoginPasswordLayout.error = it.errors[0]
                    binding.fragmentLoginError.visibility = View.VISIBLE

                }
            }
        })
    }

    private fun configuraListenerSubmit(view: View) {
        binding.fragmentLoginSubmit.setOnClickListener {
            binding.fragmentLoginError.text = null
            binding.fragmentLoginEmailLayout.error = null
            binding.fragmentLoginPasswordLayout.error = null
            binding.fragmentLoginError.visibility = View.GONE
            val user = UserLoginDto(
                binding.fragmentLoginEmail.text.toString(),
                binding.fragmentLoginPassword.text.toString()
            )
            view.isEnabled = false
            binding.fragmentLoginLoading.visibility = View.VISIBLE
            viewModel.doLogin(user)
        }
    }

    private fun escondeToolbar() {
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar!!.visibility = View.GONE
    }
}