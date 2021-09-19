package com.matecho.wms.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.matecho.wms.service.ApiClient.BASE_PATH
import com.maventech.cryptoapp.R

object ImageUtils {
    fun loadImage(context: Context, imageView: ImageView, imageUrl: String?, canEnlarge: Boolean) {
        if (imageUrl != null && imageUrl != "") {
            Glide.with(context)
                    .load(BASE_PATH + imageUrl).centerCrop() /*.apply(new RequestOptions().circleCrop())*/
                    .into(imageView)
            if (canEnlarge) {
                /*  imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new DialogUtils(context).showDialogImage(context, imageUrl);
                    }
                });*/
            }
            imageView.setPadding(
                    0
                    , 0
                    , 0
                    , 0)
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_img_placeholder) /*.apply(new RequestOptions().circleCrop())*/
                    .into(imageView)
            imageView.setPadding(
                    context.resources.getDimensionPixelSize(R.dimen._20sdp)
                    , context.resources.getDimensionPixelSize(R.dimen._20sdp
            ), context.resources.getDimensionPixelSize(R.dimen._20sdp)
                    , context.resources.getDimensionPixelSize(R.dimen._20sdp))
            imageView.setBackgroundResource(R.drawable.ic_bg_image)
        }
    }
}