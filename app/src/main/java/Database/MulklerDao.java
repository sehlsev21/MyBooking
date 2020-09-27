package Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import Models.Kullanicilar;
import Models.Mulkler;

public class MulklerDao {



    public ArrayList<Mulkler> kullaniciMulkler(Veritabani vt, int kullanici_id) {

        ArrayList<Mulkler> kullaniciMulklerArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM mulkler, kullanici WHERE mulkler.kullanici_id = kullanici.kullanici_id and mulkler.kullanici_id =" +  kullanici_id, null);

        while (c.moveToNext()) {

            Kullanicilar k = new Kullanicilar(c.getInt(c.getColumnIndex("kullanici_id")),
                    c.getString(c.getColumnIndex("kullanici_email")),
                    c.getString(c.getColumnIndex("kullanici_sifre")),
                    c.getString(c.getColumnIndex("kullanici_ad")),
                    c.getString(c.getColumnIndex("kullanici_cep")));

            Mulkler m = new Mulkler(c.getInt(c.getColumnIndex("mulk_id")),
                    c.getBlob(c.getColumnIndex("mulk_resim")),
                    c.getString(c.getColumnIndex("mulk_ucret")),
                    c.getString(c.getColumnIndex("mulk_baslik")),
                    c.getString(c.getColumnIndex("mulk_adres")),k);

            kullaniciMulklerArrayList.add(m);
        }
        return kullaniciMulklerArrayList;
    }

    public void mulkEkle (byte[] resim,String ucret, String baslik, String adres, int kullanici_id, Veritabani vt) {



        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("mulk_resim", resim);
        cv.put("mulk_ucret", ucret);
        cv.put("mulk_baslik", baslik);
        cv.put("mulk_adres", adres);
        cv.put("kullanici_id", kullanici_id);
        db.insert("mulkler", null, cv);

    }

}
