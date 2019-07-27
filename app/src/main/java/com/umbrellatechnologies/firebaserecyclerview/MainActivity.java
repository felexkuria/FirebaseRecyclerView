package com.umbrellatechnologies.firebaserecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.umbrellatechnologies.firebaserecyclerview.Adapter.MyItemGroupAdpater;
import com.umbrellatechnologies.firebaserecyclerview.Interface.FirebaseLoadListener;
import com.umbrellatechnologies.firebaserecyclerview.Model.ItemData;
import com.umbrellatechnologies.firebaserecyclerview.Model.ItemGroup;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity implements FirebaseLoadListener {
    AlertDialog dialog;
    FirebaseLoadListener firebaseLoadListener;
    RecyclerView my_recycler_view;
    DatabaseReference myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        myData = FirebaseDatabase.getInstance().getReference("MyData");
        dialog= new SpotsDialog.Builder().setContext( this ).build();
        firebaseLoadListener= this;
        my_recycler_view=  (RecyclerView )findViewById( R.id.recycler_view );
        my_recycler_view.setHasFixedSize( true );
        my_recycler_view.setLayoutManager( new LinearLayoutManager( this ) );





        //load data
        getFirebaseData();
    }

    private void getFirebaseData() {
        dialog.show();
        myData.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ItemGroup> itemGroups=new ArrayList<>(  );
                for (DataSnapshot groupSnapshot:dataSnapshot.getChildren())
                {
                    ItemGroup itemGroup=new ItemGroup(  );
                    itemGroup.setHeaderTitle(groupSnapshot.child( "headerTitle" ).getValue( true ).toString());
                    GenericTypeIndicator<ArrayList<ItemData>> genericTypeIndicator =new GenericTypeIndicator<ArrayList<ItemData>>() {
                        @Override
                        public int hashCode() {
                            return super.hashCode();
                        }
                    };

                    itemGroup.setListView( groupSnapshot.child( "listItem" ).getValue(genericTypeIndicator) );
                    itemGroups.add( itemGroup );
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

    @Override
    public void FirebaseLoadSucess(List<ItemGroup> itemGroups) {
        MyItemGroupAdpater adpater =new MyItemGroupAdpater( this,itemGroups );
        my_recycler_view.setAdapter( adpater );
        dialog.dismiss();

    }

    @Override
    public void FirebaseLoadFailed(String mesaage) {
        Toast.makeText( this,mesaage,Toast.LENGTH_SHORT ).show();
        dialog.dismiss();

    }
}
