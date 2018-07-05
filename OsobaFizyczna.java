package projekt.firma;

import java.util.ArrayList;

/**
 * Klasa reprezentujaca osobe fizyczna (
 */
public class OsobaFizyczna extends Klient {
    public Adres adresZamieszkania;
    public DokumentTozsamosci dokumentTozsamosci;

    private static int LicznikWystapien = 0;

    public OsobaFizyczna(Integer id, String nazwa, String imie, String nazwisko, String email, Long numerTelefonu, Adres adres,DokumentTozsamosci dokumentTozsamosci) {
        super(id,nazwa,imie,nazwisko,email,numerTelefonu);
        if(adres == null) {
            this.adresZamieszkania = new Adres();
        } else {
            this.adresZamieszkania = adres;
        }
        this.dokumentTozsamosci = dokumentTozsamosci;
        Terminal.system("Konstruktor pelny klasy OsobaFizyczna. Ilosc utworzonych obiektow [" + pobierzIloscWystapien() + "] typu " + this.getClass().getSimpleName());
    }

    public OsobaFizyczna(OsobaFizyczna osFizyczna) {
        this(osFizyczna.id,osFizyczna.nazwa,osFizyczna.imie,osFizyczna.nazwisko,osFizyczna.email,osFizyczna.numerTelefonu,osFizyczna.adresZamieszkania,osFizyczna.dokumentTozsamosci);
        Terminal.system("Konstruktor kopiujacy klasy OsobaFizyczna. Ilosc utworzonych obiektow [" + pobierzIloscWystapien() + "] typu " + this.getClass().getSimpleName());
    }

    @Override
    protected void podbijLicznikWystapien() {
        LicznikWystapien++;
    }

    @Override
    protected int pobierzIloscWystapien() {
        return LicznikWystapien;
    }

    @Override
    public void zmniejszLicznikWystapien() {
        Adres.zmniejszIloscWystapien();
        DokumentTozsamosci.zmniejszIloscWystapien();
        LicznikWystapien--;
    }



    @Override
    protected ArrayList<String> wartosciPol() {
        ArrayList<String> pola = super.wartosciPol();
        pola.add(adresZamieszkania.ulica);
        pola.add(adresZamieszkania.miasto);
        pola.add(adresZamieszkania.kodPocztowy);
        pola.add(dokumentTozsamosci.typ);
        pola.add(dokumentTozsamosci.numer);
        return pola;
    }

    @Override
    public boolean czyJestPoprawny() {
        if(Helper.isNullOrEmpty(imie) && Helper.isNullOrEmpty(nazwisko)) {
            return false;
        }
        if(dokumentTozsamosci == null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " adres: " + adresZamieszkania + "dok: " + dokumentTozsamosci;
    }
}
