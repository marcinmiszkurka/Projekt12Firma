package projekt.firma;


import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Klasa reprezentuje obiekty zapisywane na dysk w naszym przypadku sa to klienci. Obiekt zapisywany na dysk musi miec identyfikator.
 */
public abstract class ObiektyZapisywaneNaDysk {

    /**
     * Identyfikator obiektu
     */
    protected Integer id;


    ObiektyZapisywaneNaDysk() {
        Terminal.system("Konstruktor klasy abstrakcyjnej ObiektyZapisywaneNaDysk. Ilosc utworzonych obiektow [" + pobierzIloscWystapien() + "] typu " + this.getClass().getSimpleName());
        podbijLicznikWystapien();
    }




    /**
     *
     *   Kazdy obiekt dziedziczacy dodaje liste wartosci dla swoich pol. Kolejnosc tej listy powinna byc zgodna z metoda nazwyPol
     * @return Lista wartosci obiektow zmapowanych do stringa.
     */
    protected ArrayList<String> wartosciPol() {
        ArrayList<String> wartosciPol = new ArrayList();
        wartosciPol.add(""+id);
        return wartosciPol;
    }


    public String pobierzLinieZapisuDoPliku() {
        //Uzywam strumieni
        return wartosciPol().stream().map(str -> str == null ? "" : str).collect(Collectors.joining(","));
    }

    /**
     * Każdy obiekt przed odczytem i zapisem na dysk musi zostac zweryfikowany czy jest poprawny.
     * @return prawda jesli obiekt jest poprawny i falsz jesli nie.
     */
    public abstract boolean czyJestPoprawny();

    protected abstract void podbijLicznikWystapien();

    protected abstract int pobierzIloscWystapien();

    public abstract void zmniejszLicznikWystapien();

    @Override
    public String toString() {
        return "id: " + id;
    }
}
