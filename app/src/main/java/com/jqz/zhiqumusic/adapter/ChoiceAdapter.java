package com.jqz.zhiqumusic.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jqz.mvplibrary.adapter.BaseAdapter;
import com.jqz.mvplibrary.adapter.BaseViewHolder;
import com.jqz.zhiqumusic.R;
import com.jqz.zhiqumusic.bean.ChoicesThreeBean;
import com.jqz.zhiqumusic.bean.ChoicesTowBean;
import com.jqz.zhiqumusic.bean.PlayBean;
import com.jqz.zhiqumusic.constans.UrlConstans;
import com.jqz.zhiqumusic.ui.PlayVideoActivity;
import com.jqz.zhiqumusic.ui.VideoShowActivity;


import java.util.ArrayList;
import java.util.List;

/*
项目名：EgeddProject
包名：com.jqz.egeddproject.adapter
文件名：ChoiceAdapter
创建者：梦想
创建时间：2020/8/29  18:27  
描述：TODO
格言：你是怎样，你的世界就是怎样！
*/public class ChoiceAdapter extends RecyclerView.Adapter {
    private ArrayList<ChoicesTowBean> twoBeans;
    private ArrayList<ChoicesThreeBean> threeBeans;
    private Context context;

    public ChoiceAdapter(ArrayList<ChoicesTowBean> twoBeans, ArrayList<ChoicesThreeBean> threeBeans, Context context) {
        this.twoBeans = twoBeans;
        this.threeBeans = threeBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 1) {
            //加载图片布局
            View view = LayoutInflater.from(context).inflate(R.layout.choice_layout1, parent, false);
            return new ViewHolder1(view);


        } else if (viewType == 2) {
            //加载网络布局
            View view = LayoutInflater.from(context).inflate(R.layout.choice_layout2, parent, false);
            return new ViewHolder2(view);

        } else {
            //加载条目布局
            View view = LayoutInflater.from(context).inflate(R.layout.choice_layout3, parent, false);
            return new ViewHolder3(view);

        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //绑定数据

        if (getItemViewType(position)==1) {

            //加载图片数据
            ViewHolder1 viewHolder1= (ViewHolder1) holder;
            //小猪佩奇id=33
            Glide.with(context).load(UrlConstans.INSTANCE.getONE_IMAGE_URL()).into(viewHolder1.choice_layout1_iv_left);
            //萌鸡小队532
            Glide.with(context).load(UrlConstans.INSTANCE.getTOW_IMAGE_URL()).into(viewHolder1.choice_layout1_iv_right1);
            //汪汪队立大功175
            Glide.with(context).load(UrlConstans.INSTANCE.getTHREE_IMAGE_URL()).into(viewHolder1.choice_layout1_iv_right2);


            //各个图片的点击点听
            viewHolder1.choice_layout1_iv_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到详情页面
                    Intent intent = new Intent(context, VideoShowActivity.class);
                    intent.putExtra("id",33);
                    context.startActivity(intent);
                }
            });

            viewHolder1.choice_layout1_iv_right1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到详情页面
                    Intent intent = new Intent(context, VideoShowActivity.class);
                    intent.putExtra("id",532);
                    context.startActivity(intent);
                }
            });

            viewHolder1.choice_layout1_iv_right2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到详情页面
                    Intent intent = new Intent(context, VideoShowActivity.class);
                    intent.putExtra("id",175);
                    context.startActivity(intent);
                }
            });

        }else if(getItemViewType(position)==2){


            //加载网格形式的列表
            ViewHolder2 viewHolder2= (ViewHolder2) holder;
            viewHolder2.choice_layout2_rlv.setLayoutManager(new GridLayoutManager(context,4));
            //创建适配器
            Choice2Adapter choice2Adapter = new Choice2Adapter(twoBeans, context, R.layout.choice2_layout_item);
            viewHolder2.choice_layout2_rlv.setAdapter(choice2Adapter);

            //网格布局条目监听
            choice2Adapter.setItemClickListener(new BaseAdapter.ItemClickListener() {
                @Override
                public void onClickItem(int postion) {
                    int id = twoBeans.get(postion).getId();
                    Intent intent = new Intent(context, VideoShowActivity.class);
                    intent.putExtra("id",id);
                    context.startActivity(intent);
                }
            });

            choice2Adapter.notifyDataSetChanged();



        }else{
            //加载条目数据
            ViewHolder3 viewHolder3= (ViewHolder3) holder;
            viewHolder3.choice_layout3_rlv.setLayoutManager(new LinearLayoutManager(context));
            viewHolder3.choice_layout3_rlv.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

            //创建适配器
            Choice3Adapter choice3Adapter = new Choice3Adapter(threeBeans, context, R.layout.choice3_layout_item);
            viewHolder3.choice_layout3_rlv.setAdapter(choice3Adapter);


            //第三个布局条目监听，点击直接进入全屏播放视频
            choice3Adapter.setItemClickListener(new BaseAdapter.ItemClickListener() {
                @Override
                public void onClickItem(int postion) {
                    String name = threeBeans.get(postion).getVideo().getName();
                    String resource = threeBeans.get(postion).getVideo().getResource();
                    String image = threeBeans.get(postion).getVideo().getImage();
                    PlayBean playBean = new PlayBean();
                    playBean.setImage(image);
                    playBean.setName(name);
                    playBean.setResource(resource);
                    Intent intent = new Intent(context, PlayVideoActivity.class);
                    intent.putExtra("play",playBean);
                    context.startActivity(intent);

                }
            });

            choice3Adapter.notifyDataSetChanged();


        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return 1;
        } else if (position == 1) {
            return 2;
        }

        return 3;
    }



    static class Choice2Adapter extends BaseAdapter<ChoicesTowBean>{

        public Choice2Adapter(ArrayList<ChoicesTowBean> datas, Context context, int itemlayoutId) {
            super(datas, context, itemlayoutId);
        }

        @Override
        protected void bindData(BaseViewHolder holder, ChoicesTowBean choiceTwoBean) {
//            holder.setImageViewContent(R.id.imageview,choiceTwoBean.getImage_url());
            ImageView viewById = holder.itemView.findViewById(R.id.imageview);
            Glide.with(context).load(choiceTwoBean.getImage_url()).apply(new RequestOptions().circleCrop()).into(viewById);

            holder.setTextViewContent(R.id.choice2_layout_item_tv,choiceTwoBean.getName());

        }
    }


    static class Choice3Adapter extends BaseAdapter<ChoicesThreeBean>{

        public Choice3Adapter(ArrayList<ChoicesThreeBean> datas, Context context, int itemlayoutId) {
            super(datas, context, itemlayoutId);
        }

        @Override
        protected void bindData(BaseViewHolder holder, ChoicesThreeBean choiceThreeBean) {
            holder.setImageViewContent(R.id.choice3_layout_item_iv,choiceThreeBean.getImage());
            holder.setTextViewContent(R.id.choice3_layout_item_tv,choiceThreeBean.getName());
        }
    }




    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView choice_layout1_iv_left;
        public ImageView choice_layout1_iv_right1;
        public ImageView choice_layout1_iv_right2;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.choice_layout1_iv_left = (ImageView) rootView.findViewById(R.id.choice_layout1_iv_left);
            this.choice_layout1_iv_right1 = (ImageView) rootView.findViewById(R.id.choice_layout1_iv_right1);
            this.choice_layout1_iv_right2 = (ImageView) rootView.findViewById(R.id.choice_layout1_iv_right2);
        }

    }

    public static
    class ViewHolder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public RecyclerView choice_layout2_rlv;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.choice_layout2_rlv = (RecyclerView) rootView.findViewById(R.id.choice_layout2_rlv);
        }

    }

    public static
    class ViewHolder3 extends RecyclerView.ViewHolder {
        public View rootView;
        public RecyclerView choice_layout3_rlv;

        public ViewHolder3(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.choice_layout3_rlv = (RecyclerView) rootView.findViewById(R.id.choice_layout3_rlv);
        }

    }
}
