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
import com.example.rentage_admin.models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;
    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(Context context, List<CategoryModel> categoryModelList){
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_category, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(categoryModelList.get(position).getTitle());
        holder.description.setText(categoryModelList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, description;
        ImageView itemImage;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cateTitle);
            description = itemView.findViewById(R.id.cateDescription);
        }
    }
}
