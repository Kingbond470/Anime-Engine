package com.example.recyclerview_whatsappdesign;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    private List<ModelClass> userList;

    public Adapter(List<ModelClass> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Adapter.viewHolder holder, int position) {
        int resource=userList.get(position).getIvImage();
        String name=userList.get(position).getTvName();
        String message=userList.get(position).getTvMessage();
        String time=userList.get(position).getTvTime();
        String divider=userList.get(position).getTvDivider();

        holder.setData(resource,name,message,time,divider);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private ImageView  ivImage;
        private TextView tvName, tvTime, tvMessage, tvDivider;

        public viewHolder(@NonNull  View itemView) {
            super(itemView);

            ivImage=itemView.findViewById(R.id.cvImage);
            tvName=itemView.findViewById(R.id.tvName);
            tvTime=itemView.findViewById(R.id.textTime);
            tvMessage=itemView.findViewById(R.id.textMessage);
            tvDivider=itemView.findViewById(R.id.textDash);

        }

        public void setData(int resource, String name, String message, String time, String divider) {
            ivImage.setImageResource(resource);
            tvName.setText(name);
            tvTime.setText(time);
            tvMessage.setText(message);
            tvDivider.setText(divider);

        }
    }
}
