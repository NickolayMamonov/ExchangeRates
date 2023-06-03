package com.whysoezzy.exchangerates.presentation.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.whysoezzy.exchangerates.databinding.FragmentMainBinding
import com.whysoezzy.exchangerates.presentation.main.vm.MainScreenState
import com.whysoezzy.exchangerates.presentation.main.vm.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {
    private val vm: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding
    private lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.filter.addTextChangedListener {
            val text = binding.filter.text
            vm.filterList(text.toString())
        }
        vm.dataState.onEach { datastate ->
            when (datastate) {
                is MainScreenState.Content -> {
                    val error = binding.errorText
                    val progressbar = binding.progressBar
                    error.visibility = View.INVISIBLE
                    progressbar.visibility = View.INVISIBLE
                    val recyclerView = binding.recycleview
                    mainAdapter = MainAdapter(datastate.rates) { name, value ->
                        val action = MainFragmentDirections.actionMainFragmentToConversionFragment(
                            name,
                            value
                        )
                        findNavController().navigate(action)
                    }
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = mainAdapter
                }
                is MainScreenState.Error -> {
                    val error = binding.errorText
                    val progressbar = binding.progressBar
                    error.visibility = View.VISIBLE
                    progressbar.visibility = View.INVISIBLE
                }
                is MainScreenState.Loading -> {
                    val error = binding.errorText
                    val progressbar = binding.progressBar
                    error.visibility = View.INVISIBLE
                    progressbar.visibility = View.VISIBLE
                }
            }
        }.launchIn(lifecycleScope)
        return binding.root
    }

}