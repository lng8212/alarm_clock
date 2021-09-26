package com.example.alarmclock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.databinding.FragmentMainBinding
import com.example.alarmclock.recyclerview.ItemAdapter
import java.time.Clock


class mainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var rvItem : RecyclerView
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.data.observe(viewLifecycleOwner,{
            rvItem.adapter = ItemAdapter(it,viewModel)

        })
        binding.btnAdd.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_fragment_add_clock)
        }
        binding.btnDemnguoc.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_countDown)
        }
        rvItem = binding.listBaothuc
        rvItem.setHasFixedSize(true)
        rvItem.layoutManager = LinearLayoutManager(context)
    }



}