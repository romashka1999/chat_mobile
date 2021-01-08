package com.example.chat_mobile.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chat_mobile.R
import com.example.chat_mobile.application.RetrofitBuilder.getRetrofit
import com.example.chat_mobile.dto.SignInDto
import com.example.chat_mobile.view_model.AuthViewModel


class SignInFragment : Fragment() {

    private val authViewModel: AuthViewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        authViewModel.signIn(SignInDto("test", "test123123123"))

        authViewModel.signInLiveData.observe(
            this,
            {
                println(it)
            }
        )
    }
}