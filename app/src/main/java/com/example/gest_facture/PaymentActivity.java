package com.example.gest_facture;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {

    Intent intent;
    TextView label_payment;
    AutoCompleteTextView ref_facture;
    Button btn_confirm;
    String facture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        label_payment = findViewById(R.id.label_service);
        ref_facture = findViewById(R.id.ref_facture);
        btn_confirm = findViewById(R.id.payment_confirmation_button);

        intent = getIntent();

        facture = intent.getStringExtra("input_msg");

        label_payment.setText("Paiement " + facture);

        switch (facture){
            case "Orange":
                ref_facture.setHint("N° Entrer un n° de facture");
                break;
            case "Sde":
                ref_facture.setHint("N° Numéro facture");
                break;
            case "Woyofal":
                ref_facture.setHint("N° Entrer un n° de compteur");
                break;
            case "Sonatel":
                ref_facture.setHint("N° némero de la ligne fixe");
                break;
            default:
                break;
        }

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rf = ref_facture.getText().toString();

                if ((rf).isEmpty()){
                    ref_facture.setError(getString(R.string.error_field_required));
                }
                else{
                    Intent intent;
                    switch (facture) {
                        case "Orange":
                            intent = new Intent(PaymentActivity.this,
                                    ListeFactureActivity.class);
                            intent.putExtra("facture_type", "Woyofal");
                            startActivity(intent);
                            break;
                        case "Sde":
                            intent = new Intent(PaymentActivity.this,
                                    ListeFactureActivity.class);
                            intent.putExtra("facture_type", "Woyofal");
                            startActivity(intent);
                            break;
                        case "Woyofal":
                            intent = new Intent(PaymentActivity.this,
                                    ListeFactureActivity.class);
                            intent.putExtra("facture_type", "Woyofal");
                            startActivity(intent);
                            break;
                        case "Sonatel":
                            intent = new Intent(PaymentActivity.this,
                                    ListeFactureActivity.class);
                            intent.putExtra("facture_type", "Woyofal");
                            startActivity(intent);
                            break;
                        default:
                            break;
                    }
                }
            }
        });

    }

}
