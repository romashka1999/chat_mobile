package com.example.chat_mobile.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_mobile.R
import com.example.chat_mobile.ui.recycler_view.GroupListAdapter
import com.example.chat_mobile.util.getJwtTokenSharedPreferences
import com.example.chat_mobile.view_model.GroupViewModel

class HomeFragment : Fragment() {
    private val groupViewModel: GroupViewModel = GroupViewModel()
    private lateinit var recyclerView: RecyclerView

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
        recyclerView = view.findViewById(R.id.home_groups_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this@HomeFragment.context, LinearLayoutManager.VERTICAL, false)

        groupViewModel.getGroups(getToken()!!)

        groupViewModel.groupsLiveData.observe(
            viewLifecycleOwner,
            {
                recyclerView.adapter = GroupListAdapter(it)
            }
        )

    }

    private fun getToken(): String? {
        return getJwtTokenSharedPreferences().getString(getString(R.string.token), "")
    }
}