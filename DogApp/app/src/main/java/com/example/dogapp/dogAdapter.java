
package com.example.dogapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class dogAdapter extends RecyclerView.Adapter<dogAdapter.ViewHolder> implements Filterable {

    private List<DogBreed> dogs;
    private List<DogBreed> dogsOld;
    private Context context;

    public dogAdapter(List<DogBreed> dogs, Context context){
        this.dogs = dogs;
        this.dogsOld = dogs;
        this.context = context;
    }
    @NonNull
    @Override
    public dogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dogAdapter.ViewHolder holder, int position) {
        DogBreed contact = dogs.get(position);
        if (contact == null){
            return;
        }

        holder.tvName.setText(dogs.get(position).getName());
        holder.tvBredfor.setText(dogs.get(position).getBredfor());
        Picasso.with(context).load(dogs.get(position).getUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        if (dogs != null){
            return dogs.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvBredfor;
        public Boolean like;
        public ImageView img;
        public ImageButton imgbtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context =itemView.getContext();
            tvName = itemView.findViewById(R.id.tv_name);
            tvBredfor = itemView.findViewById(R.id.tv_bredfor);
            img =itemView.findViewById(R.id.iv_picture);
            imgbtn = itemView.findViewById(R.id.btn_like);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    dogs = dogsOld;
                } else {
                    List<DogBreed> list = new ArrayList<>();
                    for (DogBreed dog : dogsOld ){
                        if (dog.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(dog);
                        }
                    }
                    dogs = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dogs;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dogs = (List<DogBreed>) results.values;
                notifyDataSetChanged();

            }
        };
    }
}
