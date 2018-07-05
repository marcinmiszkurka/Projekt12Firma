package projekt.firma;

import java.util.ArrayList;

public class OsobaFizycznaProwadzacaDzialalnosc extends OsobaFizyczna {

    public String nip;

    private static int LicznikWystapien = 0;

    public OsobaFizycznaProwadzacaDzialalnosc(Integer id, String nazwa, String imie, String nazwisko, String email, Long numerTelefonu, Adres adres,DokumentTozsamosci dokumentTozsamosci,String nip) {
        super(id,nazwa,imie,nazwisko,email,numerTelefonu,adres,dokumentTozsamosci);
        this.nip = nip;
        Terminal.system("Konstruktor pelny klasy OsobaFizycznaProwadzacaDzialalnosc. Ilosc utworzonych obiektow [" + pobierzIloscWystapien() + "] typu " + this.getClass().getSimpleName());
    }

    public OsobaFizycznaProwadzacaDzialalnosc(OsobaFizycznaProwadzacaDzialalnosc osFizyczna) {
        this(osFizyczna.id,osFizyczna.nazwa,osFizyczna.imie,osFizyczna.nazwisko,osFizyczna.email,osFizyczna.numerTelefonu,osFizyczna.adresZamieszkania,osFizyczna.dokumentTozsamosci,osFizyczna.nip);
        Terminal.system("Konstruktor kopiujacy klasy OsobaFizycznaProwadzacaDzialalnosc. Ilosc utworzonych obiektow [" + pobierzIloscWystapien() + "] typu " + this.getClass().getSimpleName());
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
        pola.add(this.nip);
        return pola;
    }

    @Override
    public boolean czyJestPoprawny() {
        if(!super.czyJestPoprawny()) {
            return false;
        }
        if(Helper.isNullOrEmpty(nip)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " nip: " + nip;
    }
}
