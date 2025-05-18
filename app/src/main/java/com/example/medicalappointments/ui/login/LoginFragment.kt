package com.example.medicalappointments.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.medicalappointments.BuildConfig
import com.example.medicalappointments.R
import com.example.medicalappointments.managers.SharedPrefsManager
import com.example.medicalappointments.networking.repository.AuthenticationRepository
import com.example.medicalappointments.utils.extensions.logErrorMessage
import com.example.medicalappointments.utils.extensions.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

//    Primeste o referinta a view-ului ce trebuie sa il deseneze
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tv_register).setOnClickListener{
            val email = view.findViewById<TextView>(R.id.edt_email).text.toString()
            goToRegister(email)
        }

        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            doLogin()
        }

        if (BuildConfig.DEBUG) {
            // case: program runs from AndroidStudio - autocompletes the login fields with the following credentials
            view.findViewById<EditText>(R.id.edt_email).setText("eve.holt@reqres.in")
            view.findViewById<EditText>(R.id.etd_password).setText("cityslicka")
        }
    }

    private fun goToRegister(email: String) {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment(email)
        findNavController().navigate(action)
    }

    private fun goToHome() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun doLogin(){
        val email = view?.findViewById<EditText>(R.id.edt_email)?.text?.toString()
        val password = view?.findViewById<EditText>(R.id.etd_password)?.text?.toString()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            "Invalid credentials".showToast(requireContext())
            return
        }

        // LOGIN PROCESS
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    AuthenticationRepository.login(email, password)
                }
                "Login succes: ${result.token}".showToast(requireContext())

                // TOKEN
                withContext(Dispatchers.IO) {
                    SharedPrefsManager.saveAuthToken(result.token)
                }

                goToHome()
            } catch (e: IOException) {
                ("Please check your internet connection").showToast(requireContext())
            } catch (e: HttpException) {
                ("Server error: ${e.code()}".showToast(requireContext()))
            } catch (e: Exception) {
                ("Unexpected error: ${e.localizedMessage}").showToast(requireContext())
            }
        }
    }
}