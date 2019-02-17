package com.example.xsxerdiyiz.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xsxerdiyiz.R;
import com.example.xsxerdiyiz.entity.RequestParams;
import com.example.xsxerdiyiz.fragment.FragmentOne;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Url;

public class ShopAdapter extends XRecyclerView.Adapter<ShopAdapter.HolderView> {
    private List<RequestParams.ResultBean> list;
    private Context context;

    public ShopAdapter(Context context) {
        list=new ArrayList<>();
        this.context = context;
    }

    public void setList(List<RequestParams.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        HolderView holderView = new HolderView(view);
        return holderView;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holderView, int i) {
        Uri url = Uri.parse(list.get(i).getMasterPic());
        DraweeController controller=Fresco.newDraweeControllerBuilder()
                .setUri(url)
                .setAutoPlayAnimations(true)
                .build();
        holderView.item_image.setController(controller);
        holderView.itemtv_name.setText(list.get(i).getCommodityName());
        holderView.itemtv_price.setText(list.get(i).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size()>0?list.size():0;
    }

    public class HolderView extends RecyclerView.ViewHolder{
        private SimpleDraweeView item_image;
        private TextView itemtv_name;
        private TextView itemtv_price;

        public HolderView(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.item_image);
            itemtv_name = itemView.findViewById(R.id.itemtv_name);
            itemtv_price = itemView.findViewById(R.id.itemtv_price);
        }
    }
}
