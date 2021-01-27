package com.example.chat_mobile.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.chat_mobile.R
import com.example.chat_mobile.dto.SignInDto
import com.example.chat_mobile.util.getJwtTokenSharedPreferences
import com.example.chat_mobile.view_model.AuthViewModel


class SignUpFragment : Fragment() {
    private val authViewModel: AuthViewModel = AuthViewModel()
    private lateinit var signup_username_edit_text: EditText
    private lateinit var signup_email_edit_text: EditText
    private lateinit var signup_password_edit_text: EditText
    private lateinit var signup_confirm_password_edit_text: EditText
    private lateinit var signup_register_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
    }

    private fun initViews(view: View) {
        signup_username_edit_text = view.findViewById(R.id.signup_username_edit_text)
        signup_email_edit_text = view.findViewById(R.id.signup_email_edit_text)
        signup_password_edit_text = view.findViewById(R.id.signup_password_edit_text)
        signup_confirm_password_edit_text = view.findViewById(R.id.signup_confirm_password_edit_text)
        signup_register_button = view.findViewById(R.id.signup_register_button)
        ininListener()
    }

    private fun ininListener() {
        signup_register_button.setOnClickListener {
            println("daekliaaaaaaaaaaa")
            signUpUser()
        }
    }

    private fun signUpUser() {
        val username = signup_username_edit_text.text.toString()
        val email = signup_email_edit_text.text.toString()
        val password = signup_password_edit_text.text.toString()
        val confirmPassword = signup_confirm_password_edit_text.text.toString()

        if(!validateSignIUp(username, email, password, confirmPassword)) {
            println("araaa validuri")
            return
        }
        val signInDto = SignInDto(username, password)

        authViewModel.signIn(signInDto)

        authViewModel.signInLiveData.observe(
            viewLifecycleOwner,
            {
                goToSignInFragment()
            }
        )
    }

    private fun validateSignIUp(username: String, email: String, password: String, confirmPassword: String): Boolean {
        if(!setFormFieldValidations()) {
            Toast.makeText(context, "Fileds must not be blank", Toast.LENGTH_LONG).show()
            return false
        }
        if(password != confirmPassword) {
            Toast.makeText(context, "Confirm password does not match", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun setFormFieldValidations(): Boolean {
        var ans: Boolean = validateFiled(signup_username_edit_text)
        ans = validateFiled(signup_email_edit_text)
        ans = validateFiled(signup_password_edit_text)
        ans = validateFiled(signup_confirm_password_edit_text)
        return ans
    }

    private fun validateFiled(editText: EditText): Boolean {
        if(editText.text.toString().isEmpty()) {
            editText.setError("Field can not be empty")
            return false
        }
        return true
    }

    private fun goToSignInFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.auth_fragment_container, SignInFragment(), SignInFragment::class.java.simpleName)
            .commit()
    }



}