package Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.mybooking.R;


import java.util.ArrayList;

import Adapters.MulklerAdapter;
import Database.MulklerDao;
import Database.Veritabani;
import Models.GirisBilgisi;
import Models.Kullanicilar;
import Models.Mulkler;


public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbarHome;
    Kullanicilar kullanicimiz;

    private RecyclerView rv;
    private ArrayList<Mulkler> mulklerArrayList;
    private MulklerAdapter mulklerAdapter;
    private Veritabani vt;

   SharedPreferences sp;
    SharedPreferences.Editor editor;
    GirisBilgisi yaz;


    @Override
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sp = getSharedPreferences("com.example.mybooking.R",MODE_PRIVATE);
        editor = sp.edit();

        yaz = new GirisBilgisi();

        Intent intent = getIntent();
        kullanicimiz =  (Kullanicilar) intent.getSerializableExtra("kullanici");

        toolbarHome = findViewById(R.id.toolbarHome);
        rv = findViewById(R.id.recycler);
        String ad = kullanicimiz.getKullanici_ad();

        toolbarHome.setTitle("MyBooking - " + ad);
        toolbarHome.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbarHome);

        vt = new Veritabani(this);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onResume(){
        super.onResume();
        mulklerArrayList  = new MulklerDao().kullaniciMulkler(vt,kullanicimiz.getKullanici_id());
        mulklerAdapter  = new MulklerAdapter(this,mulklerArrayList);
        mulklerAdapter.notifyDataSetChanged();
        rv.setAdapter(mulklerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.cikis){

            Intent intentCikis = new Intent(HomeActivity.this, MainActivity.class);

            //kullanicimiz kişisinin giriş bilgisi true olarak değiştirildi.
            // Bu g objesi sharedPreeferences ta tutulmalı ve uygulama her açıldığında kontrol edilmeli.

            editor.clear();
            editor.commit();

            startActivity(intentCikis);
            finish();
        }
        if (id == R.id.profil){

            Intent intentProfil = new Intent(HomeActivity.this, ProfilActivity.class);
            intentProfil.putExtra("kullanici",kullanicimiz);
            startActivity(intentProfil);
        }
        if (id == R.id.mulkEkle){

            Intent intentMulkEkle = new Intent(HomeActivity.this, MulkEkleActivity.class);
            intentMulkEkle.putExtra("kullanici",kullanicimiz);
            startActivity(intentMulkEkle);
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        mulklerArrayList.add(data.getClipData().getItemAt(i).getUri());
                    }
                    mulklerAdapter.notifyDataSetChanged();
                }
            } else if (data.getData() != null) {
                String imagePath = data.getData().getPath();
            }
        }
    }*/

}
