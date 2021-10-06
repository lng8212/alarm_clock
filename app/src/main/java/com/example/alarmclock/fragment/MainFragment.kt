package com.example.alarmclock.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.R
import com.example.alarmclock.databinding.FragmentMainBinding
import com.example.alarmclock.recyclerview.ItemAdapter
import com.example.alarmclock.viewmodel.MainActivityViewModel


class mainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var rvItem: RecyclerView
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java) //trả về viewModel đã tồn tại từ lớp MainActivityViewModel
        viewModel.data.observe(viewLifecycleOwner, {
            rvItem.adapter = ItemAdapter(it, viewModel)         //update livedata
        })
        rvItem = binding.listBaothuc
        rvItem.setHasFixedSize(true) // tối ưu hiệu năng, cuộn mượt hơn
        rvItem.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener() {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_mainFragment_to_fragment_add_clock)// chuyển qua fragment addclock
        }
        binding.btnDemnguoc.setOnClickListener() {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_mainFragment_to_countDown)// chuyển qua fragment countDown
        }

    }


}