package Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mybooking.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import Database.MulklerDao;
import Database.Veritabani;
import Models.Kullanicilar;
import Models.Mulkler;

public class MulkEkleActivity extends AppCompatActivity {

    private Toolbar toolbarMulkEkle;
    private Bitmap selectedImage;
    private Uri imageUri;
    private ImageView imageView;
    private EditText editTextBaslik, editTextAdres , editTextUcret;
    private Button buttonEkle;
    private Veritabani vt = new Veritabani(MulkEkleActivity.this);
    Kullanicilar kullanicimiz;
    Mulkler m = new Mulkler();
    Bitmap imageToStore;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulk_ekle);

        Intent intent = getIntent();
        kullanicimiz =  (Kullanicilar) intent.getSerializableExtra("kullanici");

        imageView = findViewById(R.id.imageView);
        editTextUcret = findViewById(R.id.editTextUcret);
        editTextBaslik = findViewById(R.id.editTextBaslik);
        editTextAdres = findViewById(R.id.editTextAdres);
        toolbarMulkEkle = findViewById(R.id.toolbarMulkEkle);
        buttonEkle=findViewById(R.id.buttonEkle);

        toolbarMulkEkle.setTitle("Mülk Ekle");
        toolbarMulkEkle.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbarMulkEkle);

        toolbarMulkEkle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MulkEkleActivity.super.onBackPressed();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,1);
                }catch (Exception e){
                    Toast.makeText(MulkEkleActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adres= editTextAdres.getText().toString();
                String baslik = editTextBaslik.getText().toString();
                String ucret = editTextUcret.getText().toString();
                int id = kullanicimiz.getKullanici_id();

                Bitmap bitmap = imageToStore;
                byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                imageInBytes = byteArrayOutputStream.toByteArray();


                new MulklerDao().mulkEkle(imageInBytes,ucret,baslik,adres,id,vt);
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
                imageUri = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                imageView.setImageBitmap(imageToStore);
            }
        }catch (Exception e){
            Toast.makeText(MulkEkleActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }

    /*public Bitmap makeSmallerImage(Bitmap image, int maximumSize){
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width / (float) height;

        if(bitmapRatio > 1){
            width = maximumSize;
            height = (int) (width/bitmapRatio);
        }else{
            height = maximumSize;
            width = (int) (height*bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height , true);
    }*/

   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery,2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }*/


}

  /*imageview->if(ContextCompat.checkSelfPermission(MulkEkleActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
          ActivityCompat.requestPermissions(MulkEkleActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
          }else{
          Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          startActivityForResult(intentToGallery,2);
          } */

 /* onactivityresult -> if(requestCode == 2 && resultCode == RESULT_OK && data != null){
          Uri imageData = data.getData();

          try {
          if(Build.VERSION.SDK_INT >= 28){
          ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), imageData);
          selectedImage = ImageDecoder.decodeBitmap(source);
          imageView.setImageBitmap(selectedImage);
          }else{
          selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
          imageView.setImageBitmap(selectedImage);
          }
          } catch (IOException e) {
          e.printStackTrace();
          }
          }*/


