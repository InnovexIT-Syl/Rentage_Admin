package com.example.rentage_admin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentage_admin.R;
import com.example.rentage_admin.models.CarServiceModel;

import java.util.List;

public class CarServiceAdapter extends RecyclerView.Adapter<CarServiceAdapter.MyViewHolder> {
    private Context context;
    private List<CarServiceModel> carServiceModelList;

    CarServiceAdapter(Context context, List<CarServiceModel> carServiceModelList){
        this.context = context;
        this.carServiceModelList = carServiceModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_car, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(carServiceModelList.get(position).getTitle());
        holder.price.setText("AED" + carServiceModelList.get(position).getPrice());
        holder.description.setText(carServiceModelList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return carServiceModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, description;
        ImageView itemImage;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);
            itemImage = itemView.findViewById(R.id.itemImage);

        }
    }
}
