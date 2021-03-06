package com.rogok.natifetestapp.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rogok.natifetestapp.R
import com.rogok.natifetestapp.databinding.ItemGiphyImageBinding
import com.rogok.natifetestapp.models.GiphyImage1

class GiphyImageAdapter : PagingDataAdapter<GiphyImage1, GiphyImageAdapter.GiphyViewHolder>(
    IMAGE_COMPARATOR
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val binding =
            ItemGiphyImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiphyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class GiphyViewHolder(private val binding: ItemGiphyImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: GiphyImage1) {
            binding.apply {
                Glide.with(itemView)
                    .load(image.images.original.url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)

                textViewGiphyImage.text = image.userName
            }
        }
    }

    companion object {
        private val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<GiphyImage1>() {
            override fun areItemsTheSame(oldItem: GiphyImage1, newItem: GiphyImage1) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GiphyImage1, newItem: GiphyImage1) =
                oldItem == newItem
        }
    }
}
