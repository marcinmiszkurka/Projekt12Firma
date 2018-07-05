package projekt.firma;

import java.util.ArrayList;


/**
 * Klasa reprezentuje juz klienta ktory podal nam swoje dane w przypadku firmy jest to NIP i Regon.
 * Imie i nazwisko to wlasciciel firmy/przedstawiciel.
 */
public class Firma extends Klient {
    public String nip;
    public String regon;
    public Adres adresSiedziby;

    private static int IloscWystapien = 0;

    public Firma(Integer id,
                 String nazwa,
                 String imie,
                 String nazwisko,
                 String email,
                 Long numerTelefonu,
                 String nip,
                 String regon,
                 Adres adres) {
        super(id,nazwa,imie,nazwisko,email,numerTelefonu);
        this.nip = nip;
        this.regon = regon;
        if(adres == null) {
            //Jesli nie ma adresu tworzymy pusty...
            this.adresSiedziby = new Adres();

        } else {
            this.adresSiedziby = adres;
        }
        Terminal.system("Konstruktor pelny klasy Firma. Ilosc utworzonych obiektow [" + pobierzIloscWystapien() + "] typu " + this.getClass().getSimpleName());
    }

    public Firma(Firma firma) {
        this(
                firma.id,
                firma.nazwa,
                firma.imie,
                firma.nazwisko,
                firma.email,
                firma.numerTelefonu,
                firma.nip,
                firma.regon,
                firma.adresSiedziby
        );
        Terminal.system("Konstruktor kopiujący klasy Firma. Ilosc utworzonych obiektow [" + pobierzIloscWystapien() + "] typu " + this.getClass().getSimpleName());
    }

    @Override
    protected void podbijLicznikWystapien() {
        IloscWystapien++;
    }

    @Override
    protected int pobierzIloscWystapien() {
        return IloscWystapien;
    }

    @Override
    public void zmniejszLicznikWystapien() {
        Adres.zmniejszIloscWystapien();
        IloscWystapien--;
    }


    @Override
    protected ArrayList<String> wartosciPol() {
        ArrayList<String> wartosciPol =  super.wartosciPol();
        wartosciPol.add(nip);
        wartosciPol.add(regon);
        wartosciPol.add(adresSiedziby.ulica);
        wartosciPol.add(adresSiedziby.miasto);
        wartosciPol.add(adresSiedziby.kodPocztowy);
        return wartosciPol;

    }

    @Override
    public boolean czyJestPoprawny() {
        return super.czyJestPoprawny();
    }

    @Override
    public String toString() {
        return super.toString() + " nip: " + nip + " regon: " + regon + " adres: " + adresSiedziby;
    }
}
