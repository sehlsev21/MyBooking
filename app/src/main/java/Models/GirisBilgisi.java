package Models;

public class GirisBilgisi {

    Kullanicilar user;
    Boolean login_info;

    public GirisBilgisi() {

    }

    public GirisBilgisi(Kullanicilar user, Boolean login_info) {
        this.user = user;
        this.login_info = login_info;
    }

    public Kullanicilar getUser() {
        return user;
    }

    public void setUser(Kullanicilar user) {
        this.user = user;
    }

    public Boolean getLogin_info() {
        return login_info;
    }

    public void setLogin_info(Boolean login_info) {
        this.login_info = login_info;
    }
}
