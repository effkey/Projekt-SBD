package map;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Uzytkownik")
public class Uzytkownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUzytkownika", unique = true, nullable = false)
    private int idUzytkownika;

    @Column(name = "imie", unique = false, nullable = false)
    private String imie;

    @Column(name = "nazwisko", unique = false, nullable = false)
    private String nazwisko;

    @Column(name = "nickname", unique = false, nullable = false)
    private String nickname;

    @Column(name = "password", unique = false, nullable = false)
    private String password;

    @Column(name = "email", unique = false, nullable = false)
    private String email;

    @Column(name = "dataZalozeniaKonta", unique = false, nullable = false)
    private Date dataZalozeniaKonta;

    @Column(name = "uprawnieniaAdministratora", unique = false, nullable = false)
    private boolean uprawnieniaAdministratora;

    @OneToMany(mappedBy = "uzytkownik")
    private List<Zamowienie> zamowienie = new ArrayList<Zamowienie>();

    public Uzytkownik() {
    }

    public Uzytkownik(String imie, String nazwisko, String nickname, String password, Date dataZalozeniaKonta,
            boolean uprawnieniaAdministratora) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nickname = nickname;
        this.password = password;
        this.dataZalozeniaKonta = dataZalozeniaKonta;
        this.uprawnieniaAdministratora = uprawnieniaAdministratora;

    }

    public int getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(int idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDataZalozeniaKonta() {
        return dataZalozeniaKonta;
    }

    public void setDataZalozeniaKonta(Date dataZalozeniaKonta) {
        this.dataZalozeniaKonta = dataZalozeniaKonta;
    }

    public boolean isUprawnieniaAdministratora() {
        return uprawnieniaAdministratora;
    }

    public void setUprawnieniaAdministratora(boolean uprawnieniaAdministratora) {
        this.uprawnieniaAdministratora = uprawnieniaAdministratora;
    }

    public List<Zamowienie> getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(List<Zamowienie> zamowienie) {
        this.zamowienie = zamowienie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
