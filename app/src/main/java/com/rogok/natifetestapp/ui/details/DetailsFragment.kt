package com.rogok.natifetestapp.ui.details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rogok.natifetestapp.R
import com.rogok.natifetestapp.databinding.FragmentDetailsBinding
import kotlinx.android.synthetic.main.item_giphy_image.*

class DetailsFragment: Fragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val photo = args.photo

            Glide.with(this@DetailsFragment)
                .load(photo.images.original.url)
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBarDetails.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBarDetails.isVisible = false
                        textViewTitle.isVisible = true
                        return false
                    }
                })
                .into(imageViewDetails)

            textViewTitle.text = photo.title

            val uri = Uri.parse(photo.images.original.url)
            val intent = Intent(Intent.ACTION_VIEW, uri)

            textViewTitle.apply {
                text = photo.title
                setOnClickListener {
                    context.startActivity(intent)
                }
                paint.isUnderlineText = true
            }

        }
    }
}