package com.example.tuan7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DatabaseContact dataUser;
    Button add_btn, remove_btn, cancel_btn;
    EditText name_edt;
    ListView lvName;
    ArrayList nameList;
    ArrayList idList;
    ArrayAdapter adapter;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataUser = new DatabaseContact(this,"userdb.sqlite", null, 1);


        idList = new ArrayList();
        nameList = new ArrayList();

        name_edt = findViewById(R.id.name_edt);
        add_btn = findViewById(R.id.them);
        remove_btn = findViewById(R.id.xoa);
        cancel_btn = findViewById(R.id.thoat);
        lvName = findViewById(R.id.idListview);

        getNameList();

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, nameList);
        lvName.setAdapter(adapter);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.addUser(new Contact(name_edt.getText().toString()));
                getNameList();
                adapter.notifyDataSetChanged();
            }
        });

        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.removeUser((int)idList.get(index));
                getNameList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,
                        "Succesful", Toast.LENGTH_SHORT).show();

            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>
                                            adapterView, View view, int i, long l) {
                index = i;
            }
        });

    }

    private ArrayList getNameList(){

        nameList.clear();
        idList.clear();
        for (Iterator iterator = dataUser.getAll().iterator(); iterator.hasNext(); ) {
            Contact user = (Contact)  iterator.next();
            nameList.add(user.getName());
            idList.add(user.getId());

        }
        return nameList;
    }

}