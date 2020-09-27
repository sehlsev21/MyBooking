package Models;

import java.io.Serializable;

public class Kullanicilar implements Serializable {
    private int kullanici_id;
    private String kullanici_ad;
    private String kullanici_email;
    private String kullanici_sifre;
    private String kullanici_cep;

    public Kullanicilar() {
    }


    public Kullanicilar(int kullanici_id, String kullanici_ad, String kullanici_email, String kullanici_sifre, String kullanici_cep) {
        this.kullanici_id = kullanici_id;
        this.kullanici_ad = kullanici_ad;
        this.kullanici_email = kullanici_email;
        this.kullanici_sifre = kullanici_sifre;
        this.kullanici_cep = kullanici_cep;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getKullanici_ad() {
        return kullanici_ad;
    }

    public void setKullanici_ad(String kullanici_ad) {
        this.kullanici_ad = kullanici_ad;
    }

    public String getKullanici_email() {
        return kullanici_email;
    }

    public void setKullanici_email(String kullanici_email) {
        this.kullanici_email = kullanici_email;
    }

    public String getKullanici_sifre() {
        return kullanici_sifre;
    }

    public void setKullanici_sifre(String kullanici_sifre) {
        this.kullanici_sifre = kullanici_sifre;
    }

    public String getKullanici_cep() {
        return kullanici_cep;
    }

    public void setKullanici_cep(String kullanici_cep) {
        this.kullanici_cep = kullanici_cep;
    }
}
