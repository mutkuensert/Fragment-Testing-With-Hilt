package com.mutkuensert.fragmenttestingwithhilt.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mutkuensert.fragmenttestingwithhilt.data.ImageHitsModel
import com.mutkuensert.fragmenttestingwithhilt.databinding.SingleImageItemBinding

open class MyRecyclerAdapter: ListAdapter<ImageHitsModel, MyRecyclerAdapter.ViewHolder>(ImageHitsModelListDiffCallback){
    class ViewHolder(val binding: SingleImageItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding){
            Glide
                .with(imageView.context)
                .load(getItem(position).largeImageURL)
                .listener(object: RequestListener<Drawable> { //https://stackoverflow.com/a/54130621
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                })
                .into(holder.binding.imageView)
        }

    }

    object ImageHitsModelListDiffCallback: DiffUtil.ItemCallback<ImageHitsModel>(){
        override fun areItemsTheSame(oldItem: ImageHitsModel, newItem: ImageHitsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageHitsModel, newItem: ImageHitsModel): Boolean {
            return oldItem.largeImageURL == newItem.largeImageURL
        }
    }

}