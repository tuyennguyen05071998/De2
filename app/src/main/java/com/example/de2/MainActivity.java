package com.example.de2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    PeopleAdapter adapter;
    Button btCapNhat;
    EditText etHoTen, etChucVu;
    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btCapNhat = findViewById(R.id.btCapNhat);
        etHoTen = findViewById(R.id.etHoTen);
        etChucVu = findViewById(R.id.etChucVu);

        btCapNhat.setOnClickListener(this);

        adapter = new PeopleAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                People people = adapter.getPeoples().get(index);
                etHoTen.setText(people.getHoTen());
                etChucVu.setText(people.getChucVu());

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Xoá")
                        .setMessage("Xác nhận xoá")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.deletePeople(position);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        String hoTen = etHoTen.getText().toString();
        String chucVu = etChucVu.getText().toString();
        if (hoTen.isEmpty() || chucVu.isEmpty()){
            Toast.makeText(this, "Du lieu trong", Toast.LENGTH_SHORT).show();
        }
        else{
            People newPeople = new People(hoTen, chucVu);
            adapter.updatePeople(index, newPeople);
            index = -1;
            etHoTen.setText("");
            etChucVu.setText("");
        }

    }
}
