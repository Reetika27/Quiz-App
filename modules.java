package com.example.swarangigaurkar.learnersapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class modules extends Fragment {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    private Spinner spinnerCategory;

    List<Product> productList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View v=inflater.inflate(R.layout.fragment_modules,container,false);

        productList=new ArrayList<>();
        recyclerView=(RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productList.add(
                new Product(
                        1,
                        "C++",
                        4.3,
                        "Levels: " +
                                "1.Basic " +
                                "2.Intermediate " +
                                "3.Expert",
                         R.drawable.cc));

        productList.add(
                new Product(
                        1,
                        "Java",
                        3.5,
                        "Levels: " +
                                "1.Basic " +
                                "2.Intermediate " +
                                "3.Expert",
                        R.drawable.java));

        productList.add(
                new Product(
                        1,
                        "Html",
                        4.3,
                        "Levels: " +
                                "1.Basic " +
                                "2.Intermediate " +
                                "3.Expert",
                        R.drawable.html));


        productList.add(
                new Product(
                        1,
                        "Python",
                        4.3,
                        "Levels: " +
                                "1.Basic " +
                                "2.Intermediate " +
                                "3.Expert",
                        R.drawable.python));

        productList.add(
                new Product(
                        1,
                        "Android",
                        4.3,
                        "Levels: " +
                                "1.Basic " +
                                "2.Intermediate " +
                                "3.Expert",
                        R.drawable.android));


        adapter=new ProductAdapter(getContext(),productList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position) {

                if(position==1) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new java()
                    ).commit();
                    Fragment map = new Fragment ();
                    final Bundle bundle = new Bundle();
                    bundle.putString("one","Java");
                    map.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, map).commit();
                }
                else
                {

                    Toast.makeText(getActivity(),"clicked!!!!!!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}
