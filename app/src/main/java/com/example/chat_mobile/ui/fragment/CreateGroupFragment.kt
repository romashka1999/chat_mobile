package com.example.chat_mobile.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat_mobile.R
import com.example.chat_mobile.payload.GroupCreateDto
import com.example.chat_mobile.ui.recycler_view.GroupListAdapter
import com.example.chat_mobile.util.getJwtTokenSharedPreferences
import com.example.chat_mobile.view_model.GroupViewModel
import com.google.android.material.textfield.TextInputEditText


class CreateGroupFragment : Fragment() {
    private lateinit var groupTitle: TextInputEditText
    private lateinit var createButton: Button
    private val groupViewModel: GroupViewModel = GroupViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
    }

    private fun initViews(view: View) {
        groupTitle = view.findViewById(R.id.create_group_title_input)
        createButton = view.findViewById(R.id.create_group_button)
        initListeners()
    }

    private fun validateGroupTitle(): Boolean {
        if(!setFormFieldValidations()) {
            Toast.makeText(context, "Fields must not be blank", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun setFormFieldValidations(): Boolean {
        return validateField(groupTitle)
    }

    private fun validateField(editText: EditText): Boolean {
        if(editText.text.toString().isEmpty()) {
            editText.error = "Title cant be empty"
            return false
        }
        return true
    }

    private fun initListeners() {
        createButton.setOnClickListener {
            createGroup()
        }
    }

    private fun createGroup() {
        if(!validateGroupTitle()) return

        val groupCreateDto = GroupCreateDto(groupTitle.text.toString(), true)
        groupViewModel.createGroup(groupCreateDto, getToken())
        findNavController().popBackStack()
    }

    private fun getToken(): String {
        return "Bearer ${getJwtTokenSharedPreferences().getString(getString(R.string.token), "")}"
    }
}