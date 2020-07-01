package com.example.de2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends BaseAdapter {
    List<People> peoples;

    public PeopleAdapter() {
        peoples = new ArrayList<>();
        peoples.add(new People("Hồ Chí Minh", "Chủ tịch nước"));
        peoples.add(new People("Obama", "Tổng thống"));
        peoples.add(new People("Bill Gates", "CEO"));
    }

    @Override
    public int getCount() {
        return peoples.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, parent, false);
        TextView tvHoTen, tvChucVu;
        tvHoTen = convertView.findViewById(R.id.tvHoTen);
        tvChucVu = convertView.findViewById(R.id.tvChucVu);

        People people = peoples.get(position);
        tvHoTen.setText(people.getHoTen());
        tvChucVu.setText(people.getChucVu());
        return convertView;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void updatePeople(int index, People people){
        peoples.set(index, people);
        notifyDataSetChanged();
    }

    public void deletePeople(int index){
        peoples.remove(index);
        notifyDataSetChanged();
    }
}
