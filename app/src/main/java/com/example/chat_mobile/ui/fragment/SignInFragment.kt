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
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_mobile.R
import com.example.chat_mobile.application.RetrofitBuilder.getRetrofit
import com.example.chat_mobile.dto.SignInDto
import com.example.chat_mobile.payload.SignInResponse
import com.example.chat_mobile.service.AuthService
import com.example.chat_mobile.view_model.AuthViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignInFragment : Fragment() {

    private val authViewModel: AuthViewModel = AuthViewModel()
    private lateinit var signin_username_edit_text: EditText
    private lateinit var signin_password_edit_text: EditText
    private lateinit var signin_login_button: Button
    private lateinit var signin_register_button: Button

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
        signin_username_edit_text = view.findViewById(R.id.signin_username_edit_text)
        signin_password_edit_text = view.findViewById(R.id.signin_password_edit_text)
        signin_login_button = view.findViewById(R.id.signin_login_button)
        signin_register_button = view.findViewById(R.id.signin_register_button)

        initListeners()
    }


    private fun initListeners() {
        signin_login_button.setOnClickListener {
            println("daekliaaaaaaaaaaa")
            signInUser(

            )
        }

        signin_register_button.setOnClickListener {
            goToSignUpFragment()
        }
    }


    private fun signInUser() {
        val username = signin_username_edit_text.text.toString()
        val password = signin_password_edit_text.text.toString()

        if(!validateSignIn(username, password)) {
            println("araaa validuri")
            return
        }
        val signInDto = SignInDto(username, password)

        authViewModel.signIn(signInDto)

        authViewModel.signInLiveData.observe(
            this,
            {
                saveAcessTokenToStorage(it.accessToken)
            }
        )
    }

    private fun validateSignIn(username: String, password: String): Boolean {
        if(!setFormFieldValidations()) {
            Toast.makeText(context, "Fileds must not be blank", Toast.LENGTH_LONG)
            return false
        }
        return true
    }

    private fun setFormFieldValidations(): Boolean {
        var ans: Boolean = validateFiled(signin_username_edit_text)
        ans = validateFiled(signin_password_edit_text)
        return ans
    }

    private fun validateFiled(editText: EditText): Boolean {
        if(editText.text.toString().isEmpty()) {
            editText.setError("Field can not be empty")
            return false
        }
        return true
    }

    private fun saveAcessTokenToStorage(accessToken: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("TOKEN", accessToken)
            apply()
        }
    }

    private fun goToSignUpFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.auth_fragment_container, SignUpFragment(), SignUpFragment::class.java.simpleName)
            .commit()
    }

}