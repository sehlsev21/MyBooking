package Models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class Mulkler implements Serializable {

    private int mulk_id;
    private byte[] mulk_resim;
    private String mulk_ucret;
    private String mulk_baslik;
    private String mulk_adres;
    private Kullanicilar kullanici;

    public Mulkler() {
    }

    public Mulkler(int mulk_id, byte[] mulk_resim, String mulk_ucret, String mulk_baslik, String mulk_adres, Kullanicilar kullanici) {
        this.mulk_id = mulk_id;
        this.mulk_resim = mulk_resim;
        this.mulk_ucret = mulk_ucret;
        this.mulk_baslik = mulk_baslik;
        this.mulk_adres = mulk_adres;
        this.kullanici = kullanici;
    }

    public int getMulk_id() {
        return mulk_id;
    }

    public void setMulk_id(int mulk_id) {
        this.mulk_id = mulk_id;
    }

    public byte[] getMulk_resim() {
        return mulk_resim;
    }

    public void setMulk_resim(byte[] mulk_resim) {
        this.mulk_resim = mulk_resim;
    }

    public String getMulk_ucret() {
        return mulk_ucret;
    }

    public void setMulk_ucret(String mulk_ucret) {
        this.mulk_ucret = mulk_ucret;
    }

    public String getMulk_baslik() {
        return mulk_baslik;
    }

    public void setMulk_baslik(String mulk_baslik) {
        this.mulk_baslik = mulk_baslik;
    }

    public String getMulk_adres() {
        return mulk_adres;
    }

    public void setMulk_adres(String mulk_adres) {
        this.mulk_adres = mulk_adres;
    }

    public Kullanicilar getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanicilar kullanici) {
        this.kullanici = kullanici;
    }
}
