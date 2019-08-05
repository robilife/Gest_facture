package com.example.gest_facture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListeFacturesSenelecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_factures_senelec);

        RecyclerView rvDeals = (RecyclerView) findViewById(R.id.rvFactures);
        final FactureAdapter adapter = new FactureAdapter();
        rvDeals.setAdapter(adapter);
        LinearLayoutManager dealsLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false);

        rvDeals.setLayoutManager(dealsLayoutManager);
    }
}
