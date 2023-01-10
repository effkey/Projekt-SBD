package map;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "adres")
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdresu", unique = true, nullable = false)
    private int idAdresu;

    @Column(name = "miasto", nullable = false)
    private String miasto;

    @Column(name = "kodPocztowy", nullable = false)
    private int kodPocztowy;

    @Column(name = "ulica", nullable = false)
    private String ulica;

    @Column(name = "nrBudynku", nullable = false)
    private String nrBudynku;

    @Column(name = "nrLokalu", nullable = true)
    private Integer nrLokalu;

    @OneToMany(mappedBy = "adres")
    private List<Zamowienie> zamowienie = new ArrayList<Zamowienie>();

    public Adres() {
    }

    public Adres(String miasto, int kodPocztowy,
            String ulica, String nrBudynku, int nrLokalu) {
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        this.ulica = ulica;
        this.nrBudynku = nrBudynku;
        this.nrLokalu = nrLokalu;
    }

    public Adres(String miasto, int kodPocztowy,
            String ulica, String nrBudynku) {
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        this.ulica = ulica;
        this.nrBudynku = nrBudynku;
    }

    public int getIdAdresu() {
        return idAdresu;
    }

    public void setIdAdresu(int idAdresu) {
        this.idAdresu = idAdresu;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public int getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(int kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrBudynku() {
        return nrBudynku;
    }

    public void setNrBudynku(String nrBudynku) {
        this.nrBudynku = nrBudynku;
    }

    public Integer getNrLokalu() {
        return nrLokalu;
    }

    public void setNrLokalu(Integer nrLokalu) {
        this.nrLokalu = nrLokalu;
    }

    public List<Zamowienie> getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(List<Zamowienie> zamowienie) {
        this.zamowienie = zamowienie;
    }

    @Override
    public String toString() {
        return "miasto=" + miasto + ", kodPocztowy=" + kodPocztowy
                + ", ulica=" + ulica + ", nrBudynku=" + nrBudynku + ", nrLokalu=" + nrLokalu;
    }

}
