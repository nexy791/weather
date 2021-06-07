package com.ribsky.simpleweatherapp.ui.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.ribsky.simpleweatherapp.R
import com.ribsky.simpleweatherapp.adapter.RecyclerViewDayAdapter
import com.ribsky.simpleweatherapp.databinding.FragmentMainBinding
import com.ribsky.simpleweatherapp.util.Const

class FragmentDay : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(CreateMethod.INFLATE)
    private lateinit var viewModel: FragmentDayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(
            FragmentDayViewModel::class.java
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.fragmentDayViewModel = viewModel
        binding.lifecycleOwner = this

        initList()

        viewModel.status.observe(viewLifecycleOwner, {
            if (it == Const.LoadingStatus.ERROR) showError()
        })

        return binding.root
    }

    private fun showError() {
        Snackbar.make(binding.root, getString(R.string.checkConnection), Snackbar.LENGTH_INDEFINITE)
            .apply {
                setAction(getString(R.string.actionRetry)) { viewModel.getWeatherForToday() }
                show()
            }
    }

    private fun initList() {
        val adapter = RecyclerViewDayAdapter()
        val linearLayoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false)

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            this.adapter = adapter
        }

        viewModel.day.observe(viewLifecycleOwner, {
            it?.let {
                adapter.data = it
            }
        })
    }
}