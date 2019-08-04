package com.example.gest_facture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gest_facture.Utilities.Facture;
import com.example.gest_facture.Utilities.FirebaseUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListeFactureActivity extends AppCompatActivity {


    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    EditText txtTitle;
    EditText txtPrice;
    EditText txtDescription;
    Facture facture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_facture);
        // FirebaseUtil.openFbReference("factures");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("factures");
        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        txtDescription = (EditText) findViewById(R.id.txtDescription);

        Intent intent = getIntent();
        Facture facture = (Facture) intent.getSerializableExtra("Deal");
        if(facture == null) {
            facture = new Facture();
        }

        this.facture = facture;
        txtTitle.setText(facture.getTitle());
        txtDescription.setText(facture.getDescription());
        txtPrice.setText(facture.getPrice());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }


    private void saveDeal() {
        facture.setTitle(txtTitle.getText().toString());
        facture.setDescription(txtDescription.getText().toString());
        facture.setPrice(txtPrice.getText().toString());

        mDatabaseReference.child("2").setValue(facture);

        //Artist artist = new Artist(id, name, genre);
        //mDatabaseReference.child("1").setValue(facture);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this, "Deal Saved", Toast.LENGTH_LONG).show();
                clean();
                //backToList();
                return true;
            case R.id.delete_menu:
                deleteDeal();
                Toast.makeText(this, "Deal Deleted", Toast.LENGTH_LONG).show();
                //backToList();
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void deleteDeal() {
        if (facture == null) {
            Toast.makeText(this, "Please Save the deal Before deleting", Toast.LENGTH_SHORT).show();
            return;
        }
        mDatabaseReference.child(facture.getId()).removeValue();
    }

    private void backToList() {
        Intent intent =  new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void clean() {
        txtTitle.setText("");
        txtPrice.setText("");
        txtDescription.setText("");
        txtTitle.requestFocus();
    }
}
