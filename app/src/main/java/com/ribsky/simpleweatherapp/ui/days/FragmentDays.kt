package com.ribsky.simpleweatherapp.ui.days

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.ribsky.simpleweatherapp.R
import com.ribsky.simpleweatherapp.adapter.RecyclerViewDaysAdapter
import com.ribsky.simpleweatherapp.databinding.FragmentInfoBinding
import com.ribsky.simpleweatherapp.util.Const

class FragmentDays : Fragment(R.layout.fragment_info) {

    private val binding: FragmentInfoBinding by viewBinding(CreateMethod.INFLATE)
    private lateinit var viewModel: FragmentDaysViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(
            FragmentDaysViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.fragmentInfoViewHolder = viewModel
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
                setAction(getString(R.string.actionRetry)) { viewModel.getWeatherForDays() }
                show()
            }
    }

    private fun initList() {
        val adapter = RecyclerViewDaysAdapter()
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            this.adapter = adapter
        }

        viewModel.days.observe(viewLifecycleOwner, {
            it?.let {
                adapter.data = it
            }
        })
    }
}