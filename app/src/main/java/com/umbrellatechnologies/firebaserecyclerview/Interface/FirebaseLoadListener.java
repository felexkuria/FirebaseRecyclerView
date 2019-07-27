package com.umbrellatechnologies.firebaserecyclerview.Interface;

import com.umbrellatechnologies.firebaserecyclerview.Model.ItemGroup;

import java.util.List;

public interface FirebaseLoadListener {
    void  FirebaseLoadSucess(List<ItemGroup>itemGroups);
    void  FirebaseLoadFailed(String mesaage);
}
