package com.mutkuensert.fragmenttestingwithhilt.ui

import android.view.View
import com.bumptech.glide.Glide
import com.mutkuensert.fragmenttestingwithhilt.R

class FakeRecyclerAdapter: MyRecyclerAdapter() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            progressBar.visibility = View.GONE
            Glide
                .with(imageView.context)
                .load(R.drawable.test_image)
                .into(holder.binding.imageView)
        }
    }
}