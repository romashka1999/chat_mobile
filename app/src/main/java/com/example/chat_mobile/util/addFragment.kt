package com.example.chat_mobile.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun AppCompatActivity.addFragment(mainFragmentId: Int, baseFragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .add(mainFragmentId, baseFragment, baseFragment::class.java.simpleName)
        .commit()
}