package com.umbrellatechnologies.firebaserecyclerview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.umbrellatechnologies.firebaserecyclerview.Model.ItemData;
import com.umbrellatechnologies.firebaserecyclerview.Model.ItemGroup;
import com.umbrellatechnologies.firebaserecyclerview.R;

import java.util.List;

public class MyItemGroupAdpater  extends RecyclerView.Adapter<MyItemGroupAdpater.MyViewHolder>{
     private Context context;
     private List<ItemGroup>datalist;

    public MyItemGroupAdpater(Context context, List<ItemGroup> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from( context ).inflate( R.layout.layout_group,parent,false );
        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.itemTitle.setText( datalist.get(position ).getHeaderTitle() );
        List<ItemData> itemData= datalist.get( position ).getListView();
        MyItemAdpater itemListAdpater =new MyItemAdpater( context,itemData );
        holder.recycler_view_item_list.setHasFixedSize( true );
        holder.recycler_view_item_list.setLayoutManager( new LinearLayoutManager( context,LinearLayoutManager.HORIZONTAL,false ) );
        holder.recycler_view_item_list.setAdapter( itemListAdpater );
        holder.recycler_view_item_list.setNestedScrollingEnabled( false );

        holder.btnMore.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( context,"Button More"+holder.itemTitle.getText(),Toast.LENGTH_SHORT ).show();

            }
        } );
    }

    @Override
    public int getItemCount() {
        return (datalist !=null ? datalist.size():0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        Button btnMore;
        RecyclerView recycler_view_item_list;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            itemTitle=(TextView)itemView.findViewById( R.id.itemTitle );
            btnMore=(Button)itemView.findViewById( R.id.btnMore );
            recycler_view_item_list =(RecyclerView)itemView.findViewById( R.id.myrecycler_view_list);


        }
    }
}
