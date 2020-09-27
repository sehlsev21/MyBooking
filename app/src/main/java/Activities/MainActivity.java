package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybooking.R;
import com.google.gson.Gson;

import Database.KullaniciDao;

import Database.Veritabani;
import Models.GirisBilgisi;
import Models.Kullanicilar;


public class MainActivity extends AppCompatActivity {

    private EditText giris_eposta_edit;
    private EditText giris_sifre_edit;
    private Button giris_buton;
    private TextView kayit_text;
    private Veritabani vt;
    private String email;
    private String sifre;
    private KullaniciDao daoKullanici;


    SharedPreferences sp;
    GirisBilgisi yaz,oku;
    SharedPreferences.Editor e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //tanımlamalar
         sp = getSharedPreferences("com.example.mybooking.R",MODE_PRIVATE);
         e = sp.edit();

        yaz = new GirisBilgisi(); // "x kullanıcısı giriş yaptı bilgisi" için bir kullanıcı ve bir bool değer içeren model class ı
        oku = new GirisBilgisi();

       if(isLogin()) {

                Intent intentStandartGiris = new Intent(MainActivity.this, HomeActivity.class);
                intentStandartGiris.putExtra("kullanici", oku.getUser());
                startActivity(intentStandartGiris);
                finish();

        }

        giris_eposta_edit = findViewById(R.id.giris_eposta_edit);
        giris_sifre_edit = findViewById(R.id.giris_sifre_edit);
        giris_buton = findViewById(R.id.giris_buton);
        kayit_text = findViewById(R.id.kayit_text);
        vt = new Veritabani(this);
        daoKullanici = new KullaniciDao();

        //kayıt olmaya yönlendirme
        kayit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,KayitActivity.class);
                startActivity(intent);
            }
        });

        //Giriş işlemleri
        giris_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = giris_eposta_edit.getText().toString();
                sifre = giris_sifre_edit.getText().toString();
               boolean emailsifrekontrol = new KullaniciDao().emailSifreKontrol(email,sifre,vt);

                if(email.equals("") || sifre.equals("")){
                    Toast.makeText(MainActivity.this,"Lütfen heryeri doldurunuz",Toast.LENGTH_SHORT).show();
                }else{

                    if(emailsifrekontrol == true){

                        Toast.makeText(MainActivity.this,"Giris Başarılı!",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this, HomeActivity.class);

                        Kullanicilar kullanicimiz = daoKullanici.KullaniciGetir(email,vt);
                        yaz.setLogin_info(true);
                        yaz.setUser(kullanicimiz);
                        // kullanicimiz kişisinin giriş bilgisi true olarak değiştirildi.
                        // Bu g objesi sharedPreeferences ta tutulmalı ve uygulama her açıldığında kontrol edilmeli.

                        Gson gson = new Gson();
                        String json = gson.toJson(yaz);
                        e.putString("giris_bilgisi", json);
                        e.commit();

                        intent1.putExtra("kullanici", kullanicimiz);
                        startActivity(intent1);
                        finish();

                    }else {
                        Toast.makeText(MainActivity.this, "Hatalı Giris", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

  public boolean isLogin() {

        Gson gson = new Gson();
        String json = sp.getString("giris_bilgisi", "");
        oku  = gson.fromJson(json, GirisBilgisi.class);

        if(oku != null) { //giriş bilgisine ait veri çekildiyse
            if(oku.getLogin_info()) { //daha önce giriş yapılmış ise true dönmeli...
                return true;
            }
        }
        return false;
    }
}
