package com.umbrellatechnologies.firebaserecyclerview.Model;

import java.util.ArrayList;

public class ItemGroup {
    private  String headerTitle;
    private ArrayList<ItemData>ListView;

    public ItemGroup() {
    }

    public ItemGroup(String headerTitle, ArrayList<ItemData> listView) {
        this.headerTitle = headerTitle;
        ListView = listView;

}

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<ItemData> getListView() {
        return ListView;
    }

    public void setListView(ArrayList<ItemData> listView) {
        ListView = listView;
    }
}