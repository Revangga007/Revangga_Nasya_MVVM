package com.revangga.revangga_nasya_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.revangga.revangga_nasya_mvvm.DogViewModelFactory
import com.revangga.revangga_nasya_mvvm.R
import com.revangga.revangga_nasya_mvvm.data.repository.MainRepository
import com.revangga.revangga_nasya_mvvm.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() , DogView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = DogViewModelFactory(MainRepository(this))
        viewModel = ViewModelProvider(this, factory).get(DogViewModel::class.java)
            .apply { view = this@MainActivity }

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)

        binding.btnDog.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.fetchAndLoadCharacters()
            }

        override fun showLoading() {
            runOnUiThread {
                binding.loading.visibility = View.VISIBLE
            }
        }

        override fun hideLoading() {
            runOnUiThread {
                binding.loading.visibility = View.GONE
            }
        }

        override fun showMessage(message: String) {
            runOnUiThread {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }
