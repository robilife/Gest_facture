package com.example.gest_facture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.gest_facture.Utilities.Facture;
import com.example.gest_facture.Utilities.FirebaseUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListeFacturesSenelecActivity extends AppCompatActivity {

    RecyclerView rvDeals;
    ProgressBar prBar;
    FactureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_factures_senelec);
        rvDeals = findViewById(R.id.rvFactures);
        prBar = findViewById(R.id.progressBar);

        adapter = new FactureAdapter();
        rvDeals.setAdapter(adapter);

        @SuppressLint("WrongConstant") LinearLayoutManager dealsLayoutManager =
                new LinearLayoutManager(ListeFacturesSenelecActivity.this, LinearLayoutManager.VERTICAL , false);

        rvDeals.setLayoutManager(dealsLayoutManager);

    }
}
