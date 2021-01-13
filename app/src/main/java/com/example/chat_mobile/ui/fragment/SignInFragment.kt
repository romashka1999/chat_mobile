package com.example.chat_mobile.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.chat_mobile.R
import com.example.chat_mobile.dto.SignInDto
import com.example.chat_mobile.view_model.AuthViewModel


class SignInFragment : Fragment() {

    private val authViewModel: AuthViewModel = AuthViewModel()
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var signIn: Button
    private lateinit var signUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
    }

    private fun initViews(view: View) {
        username = view.findViewById(R.id.signin_username_edit_text)
        password = view.findViewById(R.id.signin_password_edit_text)
        signIn = view.findViewById(R.id.signin_login_button)
        signUp = view.findViewById(R.id.signin_register_button)

        initListeners()
    }


    private fun initListeners() {
        signIn.setOnClickListener {
            signInUser(

            )
//            signIn.setOnClickListener {
//                findNavController().navigate(R.id.signInFragment)
//            }
        }

        signUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }


    private fun signInUser() {
        val username = username.text.toString()
        val password = password.text.toString()

        if(!validateSignIn(username, password)) {
            println("araaa validuri")
            return
        }
        val signInDto = SignInDto(username, password)

        authViewModel.signIn(signInDto)

        authViewModel.signInLiveData.observe(
            viewLifecycleOwner,
            {
                saveAccessTokenToStorage(it.accessToken)
            }
        )
    }

    private fun validateSignIn(username: String, password: String): Boolean {
        if(!setFormFieldValidations()) {
            Toast.makeText(context, "Fileds must not be blank", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun setFormFieldValidations(): Boolean {
        return validateField(password) || validateField(username)
    }

    private fun validateField(editText: EditText): Boolean {
        if(editText.text.toString().isEmpty()) {
            editText.error = "Field can not be empty"
            return false
        }
        return true
    }

    private fun saveAccessTokenToStorage(accessToken: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(getString(R.string.token), accessToken)
            apply()
        }
    }

}