package com.jqz.mvplibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：AndroidAptDemo
 * 包名：  com.liangxq.mymvpone.adapter
 * 文件名：BaseAdapter
 * 创建者：liangxq
 * 创建时间：2020/3/18  9:06
 * 描述：TODO
 */
abstract public class BaseAdapter<DATA> extends RecyclerView.Adapter<BaseViewHolder>{
    protected List<DATA>datas=new ArrayList<>();
    protected Context context;
    protected LayoutInflater layoutInflater;
    private int itemlayoutId;
    private ItemClickListener itemClickListener;
    public BaseAdapter(List<DATA> datas, Context context,int itemlayoutId) {
        this.datas = datas;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.itemlayoutId=itemlayoutId;
    }

    //创建条目布局并返回holder
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(itemlayoutId, parent, false);
        return new BaseViewHolder(inflate,context);
    }


    //此方法表示用新的数据集合代替原来的数据集合
    public void setNewData(@Nullable List<DATA> data) {
        this.datas=data;
        notifyDataSetChanged();
    }

    //绑定布局，设置监听
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null){
                    itemClickListener.onClickItem(position);
                }
            }
        });
        bindData(holder,datas.get(position));
    }
    protected abstract void bindData(BaseViewHolder holder ,DATA data);


    //返回条目数量
    @Override
    public int getItemCount() {
        return datas.size();
    }


    //给监听对象赋值
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    //接口，用于回调监听事件
   public interface ItemClickListener{
        void onClickItem(int postion);
    }
}
