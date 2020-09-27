package Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybooking.R;


import Database.KullaniciDao;
import Database.Veritabani;
import Models.GirisBilgisi;
import Models.Kullanicilar;


public class ProfilActivity extends AppCompatActivity {

    private Toolbar toolbarProfil;
    private EditText editTextAd, editTextEmail, editTextCep;
    private Button buttonGuncelle, buttonSifreGuncelle;
    Kullanicilar k;
    Veritabani vt;
    String email, ad, cep, sifre;
    private KullaniciDao daoKullanici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Intent intent = getIntent();
        k = (Kullanicilar) intent.getSerializableExtra("kullanici");
        vt = new Veritabani(this);

        daoKullanici = new KullaniciDao();
        editTextAd = findViewById(R.id.editTextAd);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextCep = findViewById(R.id.editTextCep);
        buttonGuncelle = findViewById(R.id.buttonGuncelle);
        buttonSifreGuncelle = findViewById(R.id.buttonSifreGuncelle);
        toolbarProfil = findViewById(R.id.toolbarProfil);
        toolbarProfil.setTitle("Profil");
        toolbarProfil.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbarProfil);

        editTextAd.setText(k.getKullanici_ad());
        editTextEmail.setText(k.getKullanici_email());
        editTextCep.setText(k.getKullanici_cep());


        email= editTextEmail.getText().toString();
        ad= editTextAd.getText().toString();
        cep= editTextCep.getText().toString();


        toolbarProfil.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfilActivity.super.onBackPressed();
            }
        });

        buttonGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email= editTextEmail.getText().toString();
                ad= editTextAd.getText().toString();
                cep= editTextCep.getText().toString();


                boolean emailkontrol = daoKullanici.eMailListGetir(vt,k.getKullanici_email());
               if(emailkontrol) {
                   boolean isUpdate = daoKullanici.kullaniciGuncelle(k,email,ad,cep,vt);
                    if(isUpdate){
                        Kullanicilar k1 = daoKullanici.KullaniciGetir(email,vt);
                        Toast.makeText(getApplicationContext(),"Veriler Güncellendi.",Toast.LENGTH_LONG).show();
                        Intent intentToHome = new Intent(ProfilActivity.this,HomeActivity.class);
                        intentToHome.putExtra("kullanici",k1);
                        startActivity(intentToHome);
                    }
               }else{

                   Toast.makeText(getApplicationContext(),"Email Zaten Kullanılıyor",Toast.LENGTH_LONG).show();
               }
            }
        });

        buttonSifreGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View alertTasarim = getLayoutInflater().inflate(R.layout.alert_tasarim, null);
               final EditText editTextYeniSifre = alertTasarim.findViewById(R.id.editTextYeniSifre);
               final EditText editTextYeniSifreTekrar = alertTasarim.findViewById(R.id.editTextYeniSifreTekrar);

                AlertDialog.Builder ad = new AlertDialog.Builder(ProfilActivity.this);

                ad.setTitle("Şifre Güncelle");
                ad.setMessage("Yeni şifrenizi 2 kere giriniz");
                ad.setView(alertTasarim);

                ad.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sifre = editTextYeniSifre.getText().toString();
                        String sifreTekrar = editTextYeniSifreTekrar.getText().toString();
                        if(sifre.equals("") || sifreTekrar.equals("")){
                            Toast.makeText(ProfilActivity.this,"Lütfen Heryeri Doldurunuz.",Toast.LENGTH_SHORT).show();
                        }else{
                            if(sifre.equals(sifreTekrar)){
                                daoKullanici.sifreGuncelle(k,sifre,vt);
                                Toast.makeText(getApplicationContext(),"Şifre Güncellendi",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(),"Şifreler Uyuşmuyor",Toast.LENGTH_LONG).show();
                            }
                        }


                    }
                });

                ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                ad.create().show();


            }
        });

    }







}
