package com.whysoezzy.exchangerates.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.whysoezzy.exchangerates.R
import com.whysoezzy.exchangerates.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    private lateinit var mainAdapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.dataState.onEach { datastate ->
            when (datastate) {
                is MainScreenState.Content -> {
                    val recyclerView = binding.recycleview
                    mainAdapter = MainAdapter(datastate.valute) {
                        findNavController().navigate(R.id.action_mainFragment_to_conversionFragment)
                    }
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = mainAdapter
                }
                is MainScreenState.Error -> {

                }
                is MainScreenState.Loading -> {

                }
            }

        }.launchIn(lifecycleScope)
        return binding.root
    }


}