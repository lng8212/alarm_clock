package com.example.alarmclock

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alarmclock.database.Time
import com.example.alarmclock.databinding.FragmentMainBinding
import com.example.alarmclock.recyclerview.ItemAdapter


class mainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var rvItem : RecyclerView
    private lateinit var recyclerViewAdapter: ItemAdapter
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getAllTimesObservers().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()

        })
        binding.btnAdd.setOnClickListener() {
            val item = Time(0, "10:30", "Một lần", true)
            viewModel.insertTimes(item)
        }
        setUpRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
    private fun setUpRecyclerView(){
        rvItem = binding.listBaothuc
        rvItem.setHasFixedSize(true)
        recyclerViewAdapter = ItemAdapter()
        rvItem.adapter = recyclerViewAdapter
    }

}