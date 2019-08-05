package com.example.gest_facture;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gest_facture.Utilities.Facture;
import com.example.gest_facture.Utilities.FirebaseUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FactureAdapter extends  RecyclerView.Adapter<FactureAdapter.FactureViewHolder>{

    ArrayList<Facture> factures;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;

    public FactureAdapter(){

        FirebaseUtil.openFbReference("factures");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference =FirebaseUtil.mDatabaseReference;

        this.factures = FirebaseUtil.mFactures;
        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Facture fact = dataSnapshot.getValue(Facture.class);
                Log.d("Deal ", fact.getTitle());
                fact.setId(dataSnapshot.getKey());
                FactureAdapter.this.factures.add(fact);
                notifyItemInserted(FactureAdapter.this.factures.size() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListener);
    }

    @NonNull
    @Override
    public FactureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rv_row, parent, false);

        return new FactureViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FactureViewHolder holder, int position) {
        Facture facture = factures.get(position);
        holder.bind(facture);
    }

    @Override
    public int getItemCount() {
        return factures.size();
    }

    public class FactureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        TextView tvDescription;
        TextView tvPrice;
        TextView tvEtat;
        TextView tvDate;

        public FactureViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            //tvEtat = (TextView) itemView.findViewById(R.id.tvEtat);
            //tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            itemView.setOnClickListener(this);
        }


        public void bind(Facture facture) {
            tvTitle.setText(facture.getTitle());
            tvDescription.setText(facture.getDescription());
            tvPrice.setText(facture.getPrice());
            //tvEtat.setText(facture.getPrice());
            //tvDate.setText(facture.getPrice());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Facture selectedFacture = factures.get(position);
            Intent intent = new Intent(v.getContext(),ListeFactureActivity.class);
            intent.putExtra("Deal", selectedFacture);
            v.getContext().startActivity(intent);
        }
    }
}
