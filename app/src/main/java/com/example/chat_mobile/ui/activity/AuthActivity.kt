package com.example.chat_mobile.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_mobile.R
import com.example.chat_mobile.ui.fragment.SignInFragment
import com.example.chat_mobile.util.addFragment

class AuthActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        addFragment(R.id.auth_fragment_container, SignInFragment())
    }
}