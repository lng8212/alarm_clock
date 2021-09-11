package com.example.alarmclock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.databinding.FragmentMainBinding
import com.example.alarmclock.recyclerview.ItemAdapter
import java.time.Clock


class mainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var rvItem : RecyclerView
    private lateinit var recyclerViewAdapter: ItemAdapter
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var textClock: Clock
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
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_fragment_add_clock)
        }
        binding.btnDemnguoc.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_countDown)
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