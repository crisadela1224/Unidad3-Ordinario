package mx.edu.utng.orders.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.edu.utng.orders.R;
import mx.edu.utng.orders.model.Topic;

/**
 * Created by user on 23/02/2017.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> implements View.OnClickListener{
    List<Topic> topics;
    View.OnClickListener listener;
    //Constructor
    public TopicAdapter(List<Topic> topics){
        this.topics=topics;
    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public TopicAdapter.TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_layout,parent,false);
        TopicViewHolder holder= new TopicViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(TopicAdapter.TopicViewHolder holder, int position) {
        holder.tvTopicSubject.setText(topics.get(position).getTopicSubject());
        holder.tvTopicDate.setText(topics.get(position).getTopicDate());
        holder.ivTopic.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);
    }



    @Override
    public int getItemCount() {
        return topics.size();
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class TopicViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cvTopic;
        TextView tvTopicSubject;
        TextView tvTopicDate;
        ImageView ivTopic;
        ImageButton btEditTopic;
        ImageButton btDeleteTopic;
        View.OnClickListener listener;




        public TopicViewHolder(View itemView) {
            super(itemView);
            cvTopic=(CardView)itemView.findViewById(R.id.cv_topic);
            ivTopic=(ImageView)itemView.findViewById(R.id.iv_topic);
            tvTopicSubject=(TextView)itemView.findViewById(R.id.tv_topic_subject);
            tvTopicDate=(TextView)itemView.findViewById(R.id.tv_topic_date);
            btEditTopic=(ImageButton) itemView.findViewById(R.id.bt_edit_topic);
            btDeleteTopic=(ImageButton) itemView.findViewById(R.id.bt_delete_topic);
            btEditTopic.setOnClickListener(this);
            btDeleteTopic.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onClick(v);
            }
        }

        public void setListener(View.OnClickListener listener){
            this.listener=listener;

        }
    }

}
