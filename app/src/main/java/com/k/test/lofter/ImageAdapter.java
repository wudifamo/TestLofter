package com.k.test.lofter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * @Author: k
 * @Date: 2018/9/12 17:06
 */
public class ImageAdapter extends BaseQuickAdapter<ImageBean, BaseViewHolder> {
    private final int[] imgs = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3, R
            .drawable.img4, R.drawable.img5};

    ImageAdapter() {
        super(R.layout.item_recycler, new ArrayList<ImageBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageBean item) {
        SimpleDraweeView sdv = helper.getView(R.id.my_image_view);
        sdv.setActualImageResource(imgs[helper.getAdapterPosition() % imgs.length]);
    }


}
