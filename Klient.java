package projekt.firma;

import java.util.ArrayList;

/**
 * Podstawowa klasa ktora identyfikuje Klienta, podczas pierwszego kontaktu z firma. projekt.firma.Firma posiada tylko szczatkowe dane o kliencie, czyli dane kontaktowe. Klient zostawia je na stronie lub podczas kampanii marketingowej
 */
public class Klient extends ObiektyZapisywaneNaDysk {

    //Nazwa firmy - dotyczy tylko organizacji

    private static int LicznikIstancji = 0;

    public String nazwa;

    public String imie;

    public String nazwisko;

    public String email;

    public Long numerTelefonu;

    public Klient(Integer id, String nazwa, String imie, String nazwisko, String email, Long numerTelefonu) {
        super();
        this.id = id;
        this.nazwa = nazwa;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
        Terminal.system("Konstruktor pelny klasy Klient. Ilosc utworzonych obiektow [" + pobierzIloscWystapien() + "] typu " + this.getClass().getSimpleName());
    }

    public Klient(Klient klient) {
        this(klient.id, klient.nazwa, klient.imie, klient.nazwisko, klient.email, klient.numerTelefonu);
        Terminal.system("Konstruktor kopiujący klasy Klient. Ilosc utworzonych obiektow [" + pobierzIloscWystapien() + "] typu " + this.getClass().getSimpleName());
    }

    @Override
    protected void podbijLicznikWystapien() {
        LicznikIstancji++;
    }

    @Override
    protected int pobierzIloscWystapien() {
        return LicznikIstancji;
    }

    @Override
    public void zmniejszLicznikWystapien() {
        LicznikIstancji--;
    }



    @Override
    protected ArrayList<String> wartosciPol() {
        ArrayList<String> wartosci = super.wartosciPol();
        wartosci.add(nazwa);
        wartosci.add(imie);
        wartosci.add(nazwisko);
        wartosci.add(email);
        wartosci.add("" + numerTelefonu);
        return wartosci;
    }

    @Override
    public boolean czyJestPoprawny() {
        //Poprawny lead to jesli jest wypelniona nazwa, lub imie i nazwisko
        if (!Helper.isNullOrEmpty(nazwa) || !(Helper.isNullOrEmpty(imie) && Helper.isNullOrEmpty(nazwisko))) {
            return false;
        }
        //Jesli jest wypeniony email lub telefon
        if(!Helper.isNullOrEmpty(email) || numerTelefonu != null ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " nazwa: " + nazwa + " imie: " + imie + " nazwisko: " + nazwisko + " email: " + email + " nr. tel:" + numerTelefonu;
    }
}
