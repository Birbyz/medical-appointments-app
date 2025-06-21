package com.example.medicalappointments.ui.register

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.medicalappointments.R
import com.example.medicalappointments.data.repositories.DoctorRepository
import com.example.medicalappointments.data.repositories.PatientRepository
import com.example.medicalappointments.data.repositories.SpecialtyRepository
import com.example.medicalappointments.data.repositories.UserRepository
import com.example.medicalappointments.extensions.toEntity
import com.example.medicalappointments.extensions.toModel
import com.example.medicalappointments.managers.SharedPrefsManager
import com.example.medicalappointments.models.Doctor
import com.example.medicalappointments.models.Patient
import com.example.medicalappointments.models.Specialty
import com.example.medicalappointments.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import java.time.LocalDate

class RegisterFragment : Fragment() {
    private val args: RegisterFragmentArgs by navArgs()
    private var specialtyList: List<Specialty> = emptyList()
    private var selectedAvatarUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_register, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailInput = view.findViewById<EditText>(R.id.edt_email)
        val passwordInput = view.findViewById<EditText>(R.id.edt_password)
        val firstNameInput = view.findViewById<EditText>(R.id.edt_first_name)
        val lastNameInput = view.findViewById<EditText>(R.id.edt_last_name)
        val birthdateInput = view.findViewById<EditText>(R.id.edt_birthdate)
        val yearsOfExperienceInput = view.findViewById<EditText>(R.id.et_experience_years)
        val isDoctorChecked = view.findViewById<CheckBox>(R.id.cb_is_doctor)
        val specialtyLayout = view.findViewById<LinearLayout>(R.id.layout_specialty_picker)
        val specialtySpinner = view.findViewById<Spinner>(R.id.sp_specialty)
        val avatarPreview = view.findViewById<ImageView>(R.id.iv_avatar_preview)
        val registerButton = view.findViewById<Button>(R.id.btn_register)

        // complete email in advance (from login)
        emailInput.setText(args.email)

        // deactivate the register button until data is loaded
        registerButton.isEnabled = false // dezactivat inițial

        // pick an avatar from gallery
        avatarPreview.setOnClickListener {
            pickAvatarLauncher.launch("image/*")
        }

        // show spinner if checkbox is selected
        isDoctorChecked.setOnCheckedChangeListener { _, isChecked ->
            specialtyLayout.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        loadSpecialtiesIntoSpinner(specialtySpinner)

        // add in database
        view.findViewById<Button>(R.id.btn_register).setOnClickListener {
            // check user existence
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val firstName = firstNameInput.text.toString()
            val lastName = lastNameInput.text.toString()
            val birthdateText = birthdateInput.text.toString()
            val yearsOfExperience = yearsOfExperienceInput.text.toString().toIntOrNull() ?: 0

            if (email.isBlank() || password.isBlank() || firstName.isBlank() || lastName.isBlank() || birthdateText.isBlank()) {
                Toast.makeText(requireContext(), "Complete all fields. Avatar is optional.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val birthdate = try {
                LocalDate.parse(birthdateText)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Invalid format. Use YYYY-MM-DD.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hashedPassword = hashPassword(password)
            val avatar = selectedAvatarUri?.toString() ?: ""

            lifecycleScope.launch {
                val existingUser = withContext(Dispatchers.IO) {
                    UserRepository.getByEmail(email)
                }

                if (existingUser != null) {
                    Toast.makeText(requireContext(), "Email is already used.", Toast.LENGTH_LONG).show()
                    return@launch
                }

                val newUser = User(
                    id = 0L,
                    email = email,
                    password = hashedPassword,
                    firstName = firstName,
                    lastName = lastName,
                    avatar = avatar
                )

                val newUserId = withContext(Dispatchers.IO) {
                    UserRepository.insertAndReturnId(newUser.toEntity())
                }

                val newId = newUser.copy(id = newUserId)

                withContext(Dispatchers.IO) {
                    if (isDoctorChecked.isChecked) {
                        val selectedSpecialty = specialtyList[specialtySpinner.selectedItemPosition]

                        val newDoctor = Doctor(
                            id = 0,
                            user = newId,
                            specialty = selectedSpecialty,
                            yearsOfExperience = yearsOfExperience
                        )
                        DoctorRepository.insert(newDoctor.toEntity())
                    } else {
                        val newPatient = Patient(
                            id = 0,
                            user = newId,
                            birthdate = birthdate
                        )
                        PatientRepository.insert(newPatient.toEntity())
                    }
                }
                Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()

                val token = "token_${newId.id}_${System.currentTimeMillis()}"
                SharedPrefsManager.saveAuthToken(token)

                goToHome()
            }
        }
    }

    private fun goToHome() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToNavigationHome()
        findNavController().navigate(action)
    }

    private val pickAvatarLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                selectedAvatarUri = it
                view?.findViewById<ImageView>(R.id.iv_avatar_preview)?.setImageURI(uri)
            }
        }

    fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())

        return bytes.joinToString("") { "%02x".format(it) }
    }

    private fun loadSpecialtiesIntoSpinner(spinner: Spinner) {
        lifecycleScope.launch {
            val list = withContext(Dispatchers.IO) {
                SpecialtyRepository.getAll().map { it.toModel() }
            }
            if (list.isNotEmpty()) {
                specialtyList = list
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    specialtyList.map { it.name }
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
                view?.findViewById<Button>(R.id.btn_register)?.isEnabled = true
            } else {
                Toast.makeText(requireContext(), "No specialties found in DB.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

