package com.mandroid.study1.modules.glide.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mandroid.study1.R;
import com.mandroid.study1.beans.GlideItem;
import com.mandroid.study1.util.glide.ImageGlide;

import java.util.ArrayList;
import java.util.List;

public class GlideListAdapter extends BaseAdapter {

    protected List<GlideItem> datas = new ArrayList();

    public void setDatas(List<GlideItem> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_glide_list, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.fillData(i);
        return view;
    }

    public class ViewHolder {
        public ImageView ivGlideItem;

        public ViewHolder(View view) {
            ivGlideItem = view.findViewById(R.id.iv_glide_item);
        }

        public void fillData(int i) {
            String url = ((GlideItem) getItem(i)).url;
            ImageGlide.load(url, ivGlideItem);
        }
    }
}
