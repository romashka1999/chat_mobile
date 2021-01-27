package com.example.chat_mobile.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_mobile.R
import com.example.chat_mobile.ui.recycler_view.GroupListAdapter
import com.example.chat_mobile.util.getJwtTokenSharedPreferences
import com.example.chat_mobile.view_model.GroupViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    private val groupViewModel: GroupViewModel = GroupViewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var createGroupBtn: FloatingActionButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
    }

    private fun initViews(view: View) {
        createGroupBtn = view.findViewById(R.id.createie_group_floatingActionButton)
        recyclerView = view.findViewById(R.id.home_groups_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this@HomeFragment.context, LinearLayoutManager.VERTICAL, false)

        groupViewModel.getGroups(getToken())

        groupViewModel.groupsLiveData.observe(
            viewLifecycleOwner,
            {
                recyclerView.adapter = GroupListAdapter(it)
            }
        )
        groupViewModel.createGroupLiveData.observe(
            viewLifecycleOwner,
            {
                println(it)
            }
        )
        initListeners()
    }

    private fun initListeners() {
        createGroupBtn.setOnClickListener {
            findNavController().navigate(R.id.createGroupFragment)
        }
    }

    private fun getToken(): String {
        return "Bearer ${getJwtTokenSharedPreferences().getString(getString(R.string.token), "")}"
    }
}