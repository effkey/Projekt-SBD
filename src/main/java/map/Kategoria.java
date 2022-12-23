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
@Table(name = "Kategoria")
public class Kategoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idKategoria", unique = true, nullable = false)
	private int idKategoria;

	@Column(name = "nazwaKategorii", unique = true, nullable = false)
	private String nazwaKategorii;

	@Column(name = "opisKategorii", unique = true, nullable = true)
	private String opisKategorii;

	@OneToMany(mappedBy = "kategoria")
	private List<Produkt> produkt = new ArrayList<Produkt>();

	public Kategoria() {
	}

	public Kategoria( String nazwaKategorii, String opisKategorii) {
		this.nazwaKategorii = nazwaKategorii;
		this.opisKategorii = opisKategorii;
	}

	public int getIdKategoria() {
		return idKategoria;
	}

	public void setIdKategoria(int idKategoria) {
		this.idKategoria = idKategoria;
	}

	public String getNazwaKategorii() {
		return nazwaKategorii;
	}

	public void setNazwaKategorii(String nazwaKategorii) {
		this.nazwaKategorii = nazwaKategorii;
	}

	public String getOpisKategorii() {
		return opisKategorii;
	}

	public void setOpisKategorii(String opisKategorii) {
		this.opisKategorii = opisKategorii;
	}

}
