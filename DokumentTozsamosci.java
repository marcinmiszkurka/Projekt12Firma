package projekt.firma;

public class DokumentTozsamosci {

    public static String DOWOD_OSOBISTY = "Dowod osobisty";
    public static String PASZPORT = "Paszport";

    public String typ;
    public String numer;

    private static int LicznikInstancji = 0;


    public DokumentTozsamosci(String typ,String numer) {
        this.typ = typ;
        if(!(DOWOD_OSOBISTY.equals(typ) || PASZPORT.equals(typ))) {
            throw new IllegalArgumentException("Typ dokumentu tozsamosci musi byc \"" + DOWOD_OSOBISTY + "\" lub \"" + PASZPORT + "\"  a nie " + typ);
        }
        this.numer = numer;
        LicznikInstancji++;
        Terminal.system("Konstruktor pelny klasy DokumentTozsamosci. Ilosc utworzonych obiektow [" + LicznikInstancji + "] typu DokumentTozsamosci");
    }

    public DokumentTozsamosci(DokumentTozsamosci dokumentTozsamosci) {
        this(dokumentTozsamosci.typ,dokumentTozsamosci.numer);
        Terminal.system("Konstruktor kopiujacy klasy DokumentTozsamosci. Ilosc utworzonych obiektow [" + LicznikInstancji + "] typu DokumentTozsamosci");
    }


    public static void zmniejszIloscWystapien() {
        LicznikInstancji--;
    }

    @Override
    public String toString() {
        return "typ='" + typ + '\'' + ", numer='" + numer + '\'';
    }
}
