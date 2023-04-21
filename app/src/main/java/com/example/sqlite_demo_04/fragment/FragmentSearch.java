package com.example.sqlite_demo_04.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_demo_04.R;
import com.example.sqlite_demo_04.adapter.RecycleViewAdapter;
import com.example.sqlite_demo_04.dao.SQLiteHelper;
import com.example.sqlite_demo_04.model.Item;

import java.util.List;

public class FragmentSearch extends Fragment {
    private SQLiteHelper db;
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SearchView searchName,searchAuthor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter= new RecycleViewAdapter();
        db= new SQLiteHelper(getContext());
        List<Item> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Item> list= db.searchByName(s);
                adapter.setList(list);
                return true;
            }
        });
    }

    private void initView(View view) {
        recyclerView= view.findViewById(R.id.recycleView);
        searchName = view.findViewById(R.id.search);
        searchAuthor= view.findViewById(R.id.searchTg);
    }
}
