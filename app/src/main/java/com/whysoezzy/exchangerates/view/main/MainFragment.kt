package com.whysoezzy.exchangerates.view.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.whysoezzy.exchangerates.data.api.ApiService
import com.whysoezzy.exchangerates.data.model.Rates
import com.whysoezzy.exchangerates.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Locale.filter


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
        binding.filter.addTextChangedListener {
                val text = binding.filter.text
                viewModel.filterList(text.toString())
            }
        viewModel.dataState.onEach { datastate ->
//
            when (datastate) {
                is MainScreenState.Content -> {

                    val recyclerView = binding.recycleview
                    mainAdapter = MainAdapter(datastate.valute) { charCode, value ->
                        val action = MainFragmentDirections.actionMainFragmentToConversionFragment(
                            charCode,
                            value
                        )
                        findNavController().navigate(action)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




}