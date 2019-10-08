package com.twitter.challenge.homepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.twitter.challenge.R
import com.twitter.challenge.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProviders.of(this).get(WeatherViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        setHasOptionsMenu(true)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.btnStandardDeviation.setOnClickListener(clickListener)

        return binding.root
    }

    val clickListener = View.OnClickListener {view ->

        when (view.getId()) {
            R.id.btn_standard_deviation -> viewModel.getFutureTWeather()
        }
    }


}