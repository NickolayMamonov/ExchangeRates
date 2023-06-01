package com.whysoezzy.exchangerates.view.conversion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.whysoezzy.exchangerates.R
import com.whysoezzy.exchangerates.databinding.FragmentConversionBinding
import com.whysoezzy.exchangerates.view.main.MainAdapter
import com.whysoezzy.exchangerates.view.main.MainFragmentDirections
import com.whysoezzy.exchangerates.view.main.MainScreenState
import com.whysoezzy.exchangerates.view.main.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class ConversionFragment : Fragment() {
    private val args: ConversionFragmentArgs by navArgs()

    private lateinit var binding: FragmentConversionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConversionBinding.inflate(inflater)

        binding.conversionValute
        lifecycleScope.launch {
            val name: String = args.charCode
            var valValute = args.value.toDouble()
            binding.conversionText.text = "1 RUB = ${(1*valValute).toInt()} ${name}"
            binding.conversionValute.text = "0 $name"

            binding.conversionRub.addTextChangedListener {
                val text = binding.conversionRub.text.toString()
                if(text.isNotEmpty()){
                    val value = text.toInt()
                    val result = value * valValute
                    binding.conversionValute.text = "${result.toInt()} $name"
                }else{
                    binding.conversionValute.text = "0 $name"
                }

            }
        }

        return binding.root
    }

}