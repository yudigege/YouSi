package com.example.qsys.yousi.common.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.activity.BaseActivity;
import com.example.qsys.yousi.common.widget.glide.GlideCircleTransform;
import com.example.qsys.yousi.fragment.BaseFragment;

/**
 * @author hanshaokai
 * @date 2017/11/29 17:48
 */


public class ImageLoadUtils {

    public static void loadAvatar(ImageView imageView, BaseActivity activity, BaseFragment baseFragment) {
        //配置glid
        RequestOptions myOptions = new RequestOptions()
                .transform(new GlideCircleTransform(activity))
                .placeholder(R.mipmap.ic_app)
                .error(R.drawable.ic_empty_picture);
        Glide.with(baseFragment)
                .load(CustomApplication.userEntity.getAvatar())
                .apply(myOptions)
                .thumbnail(0.5f)
                .into(imageView);

    }
}
