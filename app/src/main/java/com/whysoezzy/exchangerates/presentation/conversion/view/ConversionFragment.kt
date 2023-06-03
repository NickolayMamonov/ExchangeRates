package com.whysoezzy.exchangerates.presentation.conversion.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.whysoezzy.exchangerates.databinding.FragmentConversionBinding
import com.whysoezzy.exchangerates.presentation.conversion.vm.ConversionViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ConversionFragment : Fragment() {
    private val args: ConversionFragmentArgs by navArgs()
    private val vm: ConversionViewModel by viewModel {
        parametersOf(
            args.name,
            args.value.toDouble()
        )
    }
    private lateinit var binding: FragmentConversionBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConversionBinding.inflate(inflater)
        vm.dataState.onEach { datastate ->
            binding.conversionText.text = "1 RUB = ${datastate.value} ${datastate.name}"
            binding.conversionValute.text = datastate.result
        }.launchIn(lifecycleScope)
        binding.conversionRub.addTextChangedListener {
            val text = binding.conversionRub.text.toString()
            text.toDoubleOrNull()?.let { it1 -> vm.convertRubToValute(it1) }
        }
        return binding.root
    }

}