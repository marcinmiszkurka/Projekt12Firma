package projekt.firma;

public class Adres {
    public String ulica;
    public String miasto;
    public String kodPocztowy;

    private static int LicznikInstancji = 0;

    public Adres() {
        LicznikInstancji++;
        Terminal.system("Konstruktor pusty klasy Adres. Ilosc utworzonych obiektow [" + LicznikInstancji + "] typu Adres");
    }

    public Adres(String ulica, String miasto, String kodPocztowy) {
        this();
        this.ulica = ulica;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        Terminal.system("Konstruktor pelny klasy Adres. Ilosc utworzonych obiektow [" + LicznikInstancji + "] typu Adres");
    }

    public Adres(Adres adres) {
        this(adres.ulica, adres.miasto, adres.kodPocztowy);
        Terminal.system("Konstruktor kopiujacy klasy Adres. Ilosc utworzonych obiektow [" + LicznikInstancji + "] typu Adres");
    }

    public static void zmniejszIloscWystapien() {
        LicznikInstancji--;
    }

    @Override
    public String toString() {
        return "ulica='" + ulica + '\'' +", miasto='" + miasto + '\'' + ", kodPocztowy='" + kodPocztowy + '\'';
    }
}
