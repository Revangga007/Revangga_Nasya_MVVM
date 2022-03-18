package com.revangga.revangga_nasya_mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.revangga.revangga_nasya_mvvm.DogViewModelFactory
import com.revangga.revangga_nasya_mvvm.adapter.DogsAdapter
import com.revangga.revangga_nasya_mvvm.data.repository.MainRepository
import com.revangga.revangga_nasya_mvvm.databinding.ActivityMainBinding
import com.revangga.revangga_nasya_mvvm.dialog.CustomLoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DogViewModel
    private var loadingUi: CustomLoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = DogViewModelFactory(MainRepository())
        viewModel = ViewModelProvider(this, factory)[DogViewModel::class.java]
        loadingUi = CustomLoadingDialog(this)

        binding.btnDog.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.fetchAndLoadDogs()
            }
            setupObserver()
        }
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.dogsDatabase.observe(this) {
            binding.recyclerDog.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = DogsAdapter(it)

            }
        }
    }
}