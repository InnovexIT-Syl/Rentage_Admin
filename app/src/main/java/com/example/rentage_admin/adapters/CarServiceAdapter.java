package com.example.rentage_admin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentage_admin.R;
import com.example.rentage_admin.acitvities.AddServiceActivity;
import com.example.rentage_admin.acitvities.ServicesActivity;
import com.example.rentage_admin.models.CarServiceModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CarServiceAdapter extends RecyclerView.Adapter<CarServiceAdapter.MyViewHolder> {
    private Context context;
    private List<CarServiceModel> carServiceModelList;



    public CarServiceAdapter(Context context, List<CarServiceModel> carServiceModelList){
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
        final String title = carServiceModelList.get(position).getTitle();
        final String price = carServiceModelList.get(position).getPrice();
        final String description = carServiceModelList.get(position).getDescription();
        final String imageUrl = carServiceModelList.get(position).getImageUrl();
        final String itemId = carServiceModelList.get(position).getId();

        holder.title.setText(title);
        holder.price.setText("AED " + price);
        holder.description.setText(description);
        Picasso.get().load(imageUrl).into(holder.itemImage);
        holder.itemCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                chooseAction(itemId,title, price, description, imageUrl);
                return true;
            }
        });

    }

    private void chooseAction(final String itemId, final String title, final String price, final String description, final String imageUrl) {
        String[] options = {"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose an action");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    editService(new CarServiceModel( itemId, description,title, imageUrl, price));

                } else if (which == 1) {
                    deleteService();
                }
            }
        });

        builder.create().show();
    }

    private void deleteService() {

    }

    private void editService(CarServiceModel carServiceModel) {
        Intent inent = new Intent(context, AddServiceActivity.class);

        inent.putExtra("edit", carServiceModel);
        context.startActivity(inent);


    }


    @Override
    public int getItemCount() {
        return carServiceModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, description;
        ImageView itemImage;
        CardView itemCard;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemCard = itemView.findViewById(R.id.itemCard);
        }
    }
}
