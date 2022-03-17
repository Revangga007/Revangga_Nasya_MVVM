package com.revangga.revangga_nasya_mvvm.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.revangga.revangga_nasya_mvvm.DogViewModelFactory
import com.revangga.revangga_nasya_mvvm.adapter.DogsAdapter
import com.revangga.revangga_nasya_mvvm.data.repository.MainRepository
import com.revangga.revangga_nasya_mvvm.databinding.ActivityMainBinding
import com.revangga.revangga_nasya_mvvm.dialog.CustomLoadingDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), DogView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DogViewModel
    private var loadingUi: CustomLoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = DogViewModelFactory(MainRepository(this))
        viewModel = ViewModelProvider(this, factory)[DogViewModel::class.java]
        loadingUi = CustomLoadingDialog(this)

        binding.btnDog.setOnClickListener {
            setupObserver()
        }
        setupObserver()
    }

    private fun setupObserver() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.fetchAndaLoadDogs()
        }
        viewModel.dogsDatabase.observe(this) {
            binding.recyclerDog.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = DogsAdapter(it)

            }
        }
    }
}
