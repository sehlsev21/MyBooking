package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Veritabani extends SQLiteOpenHelper {


    public Veritabani(@Nullable Context context) {
        super(context, "mybooking.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS\"kullanici\" (\n" +
                "\t\"kullanici_id\"\tINTEGER,\n" +
                "\t\"kullanici_email\"\tTEXT,\n" +
                "\t\"kullanici_sifre\"\tTEXT,\n" +
                "\t\"kullanici_ad\"\tTEXT,\n" +
                "\t\"kullanici_cep\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"kullanici_id\" AUTOINCREMENT)\n" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS\"mulkler\" (\n" +
                "\t\"mulk_id\"\tINTEGER,\n" +
                "\t\"mulk_resim\"\tBLOB,\n" +
                "\t\"mulk_ucret\"\tTEXT,\n" +
                "\t\"mulk_baslik\"\tTEXT,\n" +
                "\t\"mulk_adres\"\tTEXT,\n" +
                "\t\"kullanici_id\"\tINTEGER,\n" +
                "\tFOREIGN KEY(\"kullanici_id\") REFERENCES \"kullanici\",\n" +
                "\tPRIMARY KEY(\"mulk_id\" AUTOINCREMENT)\n" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists kullanici");
        db.execSQL("drop table if exists mulkler");
        onCreate(db);
    }

}
