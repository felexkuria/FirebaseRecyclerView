package com.umbrellatechnologies.firebaserecyclerview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.umbrellatechnologies.firebaserecyclerview.Interface.ItemClickListener;
import com.umbrellatechnologies.firebaserecyclerview.Model.ItemData;
import com.umbrellatechnologies.firebaserecyclerview.R;

import java.util.List;

public class MyItemAdpater extends RecyclerView.Adapter<MyItemAdpater.MyViewHolder> {
    private Context context;
    private List<ItemData> itemDataList;

    public MyItemAdpater(Context context, List<ItemData> itemData) {
        this.context = context;
        this.itemDataList = itemData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from( context ).inflate( R.layout.layout_item,parent,false );
        return new MyViewHolder( itemView );

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_item_title.setText( itemDataList.get( position ).getName() );
        Picasso.get().load( itemDataList.get( position ).getImage() ).into( holder.img_item );

        holder.setItemClickListener( new ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText( context,""+itemDataList.get( position ).getName(),Toast.LENGTH_SHORT ).show();
            }
        } );

    }

    @Override
    public int getItemCount() {
        return (itemDataList !=null ? itemDataList.size():0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_item_title;
        ImageView img_item;
        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            txt_item_title =(TextView)itemView.findViewById( R.id.itemTitle );
            img_item=(ImageView)itemView.findViewById( R.id.ItemImage);

            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClickListener( view,getAdapterPosition() );
        }
    }
}
