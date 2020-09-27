package Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Models.Kullanicilar;

public class KullaniciDao {

    public Boolean ekleKullanici(String kullanici_email, String kullanici_sifre, String kullanici_ad, String kullanici_cep, Veritabani vt){

        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("kullanici_email", kullanici_email);
        contentValues.put("kullanici_sifre", kullanici_sifre);
        contentValues.put("kullanici_ad", kullanici_ad);
        contentValues.put("kullanici_cep", kullanici_cep);
        long result = db.insert("kullanici",null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean emailKontrol(String kullanici_email, Veritabani vt){ //kayıt activity de email kullanılmış mı kontrolü

        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from kullanici where kullanici_email = ? ", new String[] {kullanici_email});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean emailSifreKontrol (String kullanici_email, String kullanici_sifre, Veritabani vt){ //main activityde bu email-şifre database de var mı kontrolü
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from kullanici where kullanici_email = ? and kullanici_sifre = ? ", new String[] {kullanici_email, kullanici_sifre});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Kullanicilar KullaniciGetir(String email, Veritabani vt) {
        Kullanicilar k;
        Kullanicilar k1 = new Kullanicilar();
        SQLiteDatabase db = vt.getReadableDatabase();
        String a = "SELECT * FROM kullanici WHERE kullanici_email = '" + email + "'";
        Cursor c = db.rawQuery(a, null);
        if (c.getCount() == 1) {
            c.moveToFirst();
            k = new Kullanicilar(c.getInt(c.getColumnIndex("kullanici_id")),
                    c.getString(c.getColumnIndex("kullanici_ad")),
                    c.getString(c.getColumnIndex("kullanici_email")),
                    c.getString(c.getColumnIndex("kullanici_sifre")),
                    c.getString(c.getColumnIndex("kullanici_cep"))
            );
            return k;
        }

            return k1; //eğer buraya geliyorsa hata veriyor demektir.
    }

    public boolean kullaniciGuncelle (Kullanicilar k, String email, String ad, String cep,Veritabani vt){ //profil ac. de kullanici bilgilerini güncelleme

        int id = k.getKullanici_id();
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("kullanici_email",email);
        cv.put("kullanici_ad",ad);
        cv.put("kullanici_cep",cep);
        db.update("kullanici",cv,"kullanici_id = ?",new String[] {String.valueOf(id)});

        return true;
    }


    public Boolean eMailListGetir(Veritabani vt, String email){ //profil activity de email kullanılmış mı kontrolü
        ArrayList<String> myList = new ArrayList<>();
        String temp;
        SQLiteDatabase db = vt.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM kullanici where kullanici_email != ? ", new String[] {email});
        if(c.getCount()>0) {
            c.moveToFirst();
            do {
                temp = c.getString(c.getColumnIndex("kullanici_email"));
                myList.add(temp);
            }while(c.moveToNext());
        }
        else{
            return true;
        }
        for(int i=0;i<myList.size();i++) {
            if(email.equals(myList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean sifreGuncelle (Kullanicilar k,String sifre ,Veritabani vt){ //profil ac. de kullanici bilgilerini güncelleme

        int id = k.getKullanici_id();
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("kullanici_sifre",sifre);
        db.update("kullanici",cv,"kullanici_id = ?",new String[] {String.valueOf(id)});
        return true;
    }





}
