package com.revangga.revangga_nasya_mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.revangga.revangga_nasya_mvvm.data.model.Dog
import com.revangga.revangga_nasya_mvvm.databinding.ItemDogBinding


class DogsAdapter(
    private var dogs: List<Dog>
) : RecyclerView.Adapter<DogsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemDogBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsAdapter.ViewHolder {
        val view = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogsAdapter.ViewHolder, position: Int) {
        val dog = dogs[position]
        Glide.with(holder.binding.imgDog.context)
            .load(dog.url)
            .into(holder.binding.imgDog)
    }

    override fun getItemCount() = dogs.size
}