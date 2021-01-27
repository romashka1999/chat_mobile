package com.example.chat_mobile.util

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import com.example.chat_mobile.R

fun Fragment.getJwtTokenSharedPreferences(): SharedPreferences {
    return requireActivity().getSharedPreferences(
        getString(R.string.token),
        Context.MODE_PRIVATE
    )
}