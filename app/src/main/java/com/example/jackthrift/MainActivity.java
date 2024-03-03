package com.example.jackthrift;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvTotal;
    private RadioButton rbAda, rbTidak;
    private CheckBox cbHitam, cbBiru, cbPutih;
    private EditText etHitam, etBiru, etPutih;
    private Button btBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotal = findViewById(R.id.tvTotal);
        rbAda = findViewById(R.id.rbAda);
        rbTidak = findViewById(R.id.rbTidak);
        cbHitam = findViewById(R.id.cbHitam);
        cbBiru = findViewById(R.id.cbBiru);
        cbPutih = findViewById(R.id.cbPutih);
        etHitam = findViewById(R.id.etHitam);
        etBiru = findViewById(R.id.etBiru);
        etPutih = findViewById(R.id.etPutih);
        btBayar = findViewById(R.id.btBayar);

        StringBuilder struk = new StringBuilder();

        btBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Hpcs = 0;
                int Bpcs = 0;
                int Ppcs = 0;
                int Total = 0;
                int Hharga = 165000;
                int Bharga = 150000;
                int Pharga = 155000;
                int Ahitam = 2000;
                int Abiru = 2500;
                int Aputih = 3000;
                double discount = 0;
                double adminFee = 0;

                struk.setLength(0);

                try {
                    struk.append("***RINCIAN HARGA***\n");

                    if (cbHitam.isChecked()) {
                        Hpcs = Integer.parseInt(etHitam.getText().toString().trim());
                        int Htotal = Hpcs * Hharga;
                        int HadmFee = Hpcs * Ahitam;
                        struk.append("Hitam: ").append(Hpcs).append(" pcs x Rp.").append(Hharga).append(" = Rp").append(Htotal).append(" + Biaya Admin: Rp").append(HadmFee).append("\n");
                        Total += Htotal;
                        adminFee += HadmFee;
                    }

                    if (cbBiru.isChecked()) {
                        Bpcs = Integer.parseInt(etBiru.getText().toString().trim());
                        int Btotal = Bpcs * Bharga;
                        int BadmFee = Bpcs * Abiru;
                        struk.append("Biru: ").append(Bpcs).append(" pcs x Rp").append(Bharga).append(" = Rp").append(Btotal).append(" + Biaya Admin: Rp").append(BadmFee).append("\n");
                        Total += Btotal;
                        adminFee += BadmFee;
                    }

                    if (cbPutih.isChecked()) {
                        Ppcs = Integer.parseInt(etPutih.getText().toString().trim());
                        int Ptotal = Ppcs * Pharga;
                        int PadmFee = Ppcs * Aputih;
                        struk.append("Putih: ").append(Ppcs).append(" pcs x Rp").append(Pharga).append(" = Rp").append(Ptotal).append(" + Biaya Admin: Rp").append(PadmFee).append("\n");
                        Total += Ptotal;
                        adminFee += PadmFee;
                    }

                    struk.append("================================\n");

                    boolean isMember = rbAda.isChecked();

                    struk.append("Biaya Admin: Rp.").append(adminFee).append("\n");
                    Total += adminFee;

                    if (isMember) {
                        discount = 0.05;
                        struk.append("Diskon Membership (5%): Rp.").append(Total * discount).append("\n");
                        Total -= Total * discount;
                    }



                    struk.append("Total Setelah Diskon dan Biaya Admin: Rp.").append(Total).append("\n");

                    struk.append("================================\n");
                    struk.append("Total: Rp.").append(Total).append("\n");

                    tvTotal.setText(struk.toString());

                } catch (NumberFormatException e) {
                    tvTotal.setText("SILAHKAN MASUKKAN JUMLAH BAJU YANG INGIN ANDA BELI!!");
                }

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}