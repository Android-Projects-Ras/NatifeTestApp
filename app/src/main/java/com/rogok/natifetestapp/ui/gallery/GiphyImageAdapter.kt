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
import com.rogok.natifetestapp.models.GiphyImage

class GiphyImageAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<GiphyImage, GiphyImageAdapter.GiphyViewHolder>(
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

    inner class GiphyViewHolder(private val binding: ItemGiphyImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //Click on recyclerView item
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)

                    }
                }
            }
        }

        fun bind(image: GiphyImage) {
            binding.apply {
                Glide.with(itemView)
                    .load(image.images.fixedHeightSmall.url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)

                textViewGiphyImage.text = image.userName
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(image: GiphyImage)
    }

    companion object {
        private val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<GiphyImage>() {
            override fun areItemsTheSame(oldItem: GiphyImage, newItem: GiphyImage) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GiphyImage, newItem: GiphyImage) =
                oldItem == newItem
        }
    }
}
