package com.example.medicalappointments.ui.login

import android.os.Bundle
import android.util.Log
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
import com.example.medicalappointments.networking.repository.UserRepository
import com.example.medicalappointments.utils.extensions.logErrorMessage
import com.example.medicalappointments.utils.extensions.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.security.MessageDigest

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = view.findViewById<EditText>(R.id.edt_email)?.text.toString()
        val password = view.findViewById<EditText>(R.id.edt_password)?.text.toString()

        // REGISTER REDIRECT
        view.findViewById<TextView>(R.id.tv_register).setOnClickListener{
            goToRegister(email)
        }

        // LOGIN
        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            doLogin()
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
        val email = view?.findViewById<EditText>(R.id.edt_email)?.text.toString()
        val password = view?.findViewById<EditText>(R.id.edt_password)?.text.toString()

        val hashedPassword = hashPassword(password)
        Log.d("Login", "Input email: '$email'")

        // LOGIN PROCESS
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val user = withContext(Dispatchers.IO) {
                    UserRepository.getByEmail(email)
                }

                if (user == null || user.password != hashedPassword) {
                    Log.d("Login", "Input email: '$email'")
                    "Email or password incorrect.".showToast(requireContext())
                } else {
                    val token = "token_${user.id}_${System.currentTimeMillis()}"
                    SharedPrefsManager.saveAuthToken(token)
                    "Login success".showToast(requireContext())

                    goToHome()
                }
            } catch (e: IOException) {
                ("Please check your internet connection").showToast(requireContext())
            } catch (e: HttpException) {
                ("Server error: ${e.code()}".showToast(requireContext()))
            } catch (e: Exception) {
                ("Unexpected error: ${e.localizedMessage}").showToast(requireContext())
            }
        }
    }

    private fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}