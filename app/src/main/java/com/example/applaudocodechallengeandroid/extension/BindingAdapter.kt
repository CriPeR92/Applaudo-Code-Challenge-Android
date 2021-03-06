package com.example.applaudocodechallengeandroid.extension

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.applaudocodechallengeandroid.R


/**
 * Load image url to ImageView
 * If image url is null show iconAndroid
 */
@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view)
        .load(imageUrl)
        .fitCenter()
        .error(R.drawable.ic_launcher_foreground)
        .into(view)
}

/**
 * Show icon to add to favorites or hide if is already in favorites screen
 */
@BindingAdapter("app:setIcon")
fun setIcon(view: ImageView, isFavorite: Boolean?) {
    if (isFavorite == true) {
        view.visibility = View.GONE
    } else {
        view.setImageResource(R.drawable.ic_favorite)
    }
}

/**
 * Bind to know when to hide or show a view
 */
@BindingAdapter("app:hideIfSaved")
fun hideIfSaved(view: View, number: Boolean) {
    view.visibility = if (!number) View.GONE else View.VISIBLE
}

/**
 * Function to set adapter in recyclerView and set recyclerView features (Horizontal)
 */
@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    this.setHasFixedSize(true)
    this.isNestedScrollingEnabled = false
    this.adapter = adapter
}

/**
 * Function to set adapter in recyclerView and set recyclerView features (Vertical)
 */
@BindingAdapter("app:setRecycler")
fun setRecycler(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.run {
        this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.setHasFixedSize(true)
        this.isNestedScrollingEnabled = false
        this.adapter = adapter
    }
}