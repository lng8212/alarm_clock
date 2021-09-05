package com.example.alarmclock

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.alarmclock.databinding.FragmentMainBinding
import com.example.alarmclock.recyclerview.ItemAdapter


class mainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var rvItem : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }
    private fun setUpRecyclerView(){
        rvItem = binding.listBaothuc
        rvItem.setHasFixedSize(true)
        rvItem.adapter = ItemAdapter(MainActivity.data)


    }

}