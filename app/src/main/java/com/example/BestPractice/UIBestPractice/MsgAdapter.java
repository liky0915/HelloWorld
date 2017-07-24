package com.example.BestPractice.UIBestPractice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.helloworld.R;

import java.util.List;

/**
 * Created by lester.ding on 7/7/2017.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> msgList;
    //内部静态类，附属于外部类（不需外部类实例化），可多次实例化
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView leftMsg, rightMsg;
        LinearLayout leftLayout, rightLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            leftMsg = (TextView) itemView.findViewById(R.id.msg_left);
            rightMsg = (TextView) itemView.findViewById(R.id.msg_right);
            leftLayout = (LinearLayout) itemView.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) itemView.findViewById(R.id.right_layout);
        }
    }
    //构造函数
    public MsgAdapter(List<Msg> list) {
        this.msgList = list;
    }

    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgAdapter.ViewHolder holder, int position) {
        Msg msg = msgList.get(position);
        if(msg.getType()==Msg.TYPE_RECEIVED){   //接受的消息
            holder.rightLayout.setVisibility(View.GONE);    //右边的消息布局隐藏
            holder.leftLayout.setVisibility(View.VISIBLE);  //左边的消息布局显示
            holder.leftMsg.setText(msg.getContent());       //左边显示接受的数据
        }else if(msg.getType()==Msg.TYPE_SENT){ //发送的消息
            holder.leftLayout.setVisibility(View.GONE);     //左边的消息布局隐藏
            holder.rightLayout.setVisibility(View.VISIBLE); //右边的消息布局显示
            holder.rightMsg.setText(msg.getContent());      //右边显示发送的数据
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
