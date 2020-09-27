package Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybooking.R;

import Database.KullaniciDao;
import Database.Veritabani;


public class KayitActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText kayit_eposta_edit, kayit_adsoyad_edit , kayit_ceptel_edit;
    private EditText kayit_sifre_edit;
    private EditText kayit_sifretekrar_edit;
    private Button kayit_buton;
    Veritabani vt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        toolbar = findViewById(R.id.toolbar);
        kayit_adsoyad_edit = findViewById(R.id.kayit_adsoyad_edit);
        kayit_ceptel_edit = findViewById(R.id.kayit_ceptel_edit);
        kayit_eposta_edit = findViewById(R.id.kayit_eposta_edit);
        kayit_sifre_edit = findViewById(R.id.kayit_sifre_edit);
        kayit_sifretekrar_edit = findViewById(R.id.kayit_sifretekrar_edit);
        kayit_buton = findViewById(R.id.kayit_buton);

        vt = new Veritabani(this);

        toolbar.setTitle("Kayıt Ol");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KayitActivity.super.onBackPressed();
            }
        });

        //kayit işlemleri
        kayit_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = kayit_eposta_edit.getText().toString();
                String sifre = kayit_sifre_edit.getText().toString();
                String sifreTekrar = kayit_sifretekrar_edit.getText().toString();
                String adsoyad = kayit_adsoyad_edit.getText().toString();
                String ceptel = kayit_ceptel_edit.getText().toString();

                if(email.equals("") || sifre.equals("") || sifreTekrar.equals("") || adsoyad.equals("") || ceptel.equals("")){
                    Toast.makeText(KayitActivity.this,"Lütfen heryeri doldurunuz.",Toast.LENGTH_SHORT).show();
                }else{
                    if (sifre.equals(sifreTekrar)) {
                       boolean emailkontrol = new KullaniciDao().emailKontrol(email,vt);
                        if(emailkontrol == false){
                            boolean eklekullanici = new KullaniciDao().ekleKullanici(email,sifre,adsoyad,ceptel,vt);
                            if(eklekullanici == true){
                                Toast.makeText(KayitActivity.this,"Kayıt Başarılı.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(KayitActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(KayitActivity.this,"Bir Hata Oluştu",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(KayitActivity.this,"Bu Email Adresi Zaten Kullanılıyor.",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(KayitActivity.this,"Şifreler Uyuşmuyor.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
