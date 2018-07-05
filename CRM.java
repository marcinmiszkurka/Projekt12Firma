package projekt.firma;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Klasa zawierajaca logike zarzadzania uzytkownikami - nie zlicza ona ilosci wystapien poniewaz jest tworzona tylko jedna.
 * Zawiera ona logike zarzadzania obiektem glownym (Klientem). Sama nie przechowuje danych.
 */
public class CRM {

    private ArrayList<ObiektyZapisywaneNaDysk> klienci;

    private String[] nazwyPlikow = {"klienci.txt","firmy.txt","osoby.txt","ofpd.txt"};


    public CRM() {
        klienci = new ArrayList<>();
    }

    public void zaladujDaneZDysku() throws IOException {
        zaladujKlienciZDysku();
        zaladujFirmyZDysku();
        zaladujOsobyZDysku();
        zaladujOsobyProwadzaceDzialalnoscZDysku();
        Terminal.operacjeIO("Zaladowano " + klienci.size() +  " klientow z plikow " + Arrays.toString(nazwyPlikow));
    }

    public ArrayList<ObiektyZapisywaneNaDysk> pobierzWszystkich() {
        return klienci;
    }


    public List<ObiektyZapisywaneNaDysk> pobierzKlientow() {
        return klienci.stream().filter(t -> t.getClass() == Klient.class).collect(Collectors.toList());
    }

    public List<ObiektyZapisywaneNaDysk> pobierzFirmy() {
        return klienci.stream().filter(t -> t.getClass() == Firma.class).collect(Collectors.toList());
    }

    public List<ObiektyZapisywaneNaDysk> pobierzOsoby() {
        return klienci.stream().filter(t -> t.getClass() == OsobaFizyczna.class).collect(Collectors.toList());
    }

    public List<ObiektyZapisywaneNaDysk> pobierzOsobaFizycznaProwadzacaDzialalnosc() {
        return klienci.stream().filter(t -> t.getClass() == OsobaFizycznaProwadzacaDzialalnosc.class).collect(Collectors.toList());
    }

    private void zaladujOsobyProwadzaceDzialalnoscZDysku() throws IOException {
        File file = new File(nazwyPlikow[3]);
        if(!file.exists()) {
            Terminal.operacjeIO("Brak pliku " + file.getName());
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            //Uzywam strumieni
            br.lines().forEach(linia -> {
                String[] elementyLini = linia.split(",");
                if(elementyLini.length != 12) {
                    throw new IllegalArgumentException("Linia ma zla ilosc elementow {" + elementyLini.length + "}: " + linia);
                }
                OsobaFizycznaProwadzacaDzialalnosc ofpd = new OsobaFizycznaProwadzacaDzialalnosc(
                        Integer.parseInt(elementyLini[0]),
                        elementyLini[1],
                        elementyLini[2],
                        elementyLini[3],
                        elementyLini[4],
                        Long.parseLong(elementyLini[5]),
                        new Adres(elementyLini[6],elementyLini[7],elementyLini[8]),
                        new DokumentTozsamosci(elementyLini[9],elementyLini[10]),
                        elementyLini[11]
                );
                klienci.add(ofpd);
            });
        }
    }

    private void zaladujOsobyZDysku() throws IOException {
        File file = new File(nazwyPlikow[2]);
        if(!file.exists()) {
            Terminal.operacjeIO("Brak pliku " + file.getName());
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            //Uzywam strumieni
            br.lines().forEach(linia -> {
                String[] elementyLini = linia.split(",");
                if(elementyLini.length != 11) {
                    throw new IllegalArgumentException("Linia ma zla ilosc elementow {" + elementyLini.length + "}: " + linia);
                }
                OsobaFizyczna osoba = new OsobaFizyczna(
                        Integer.parseInt(elementyLini[0]),
                        elementyLini[1],
                        elementyLini[2],
                        elementyLini[3],
                        elementyLini[4],
                        Long.parseLong(elementyLini[5]),
                        new Adres(elementyLini[6],elementyLini[7],elementyLini[8]),
                        new DokumentTozsamosci(elementyLini[9],elementyLini[10])
                );
                klienci.add(osoba);
            });
        }
    }

    private void zaladujFirmyZDysku() throws IOException  {
        File file = new File(nazwyPlikow[1]);
        if(!file.exists()) {
            Terminal.operacjeIO("Brak pliku " + file.getName());
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            //Uzywam strumieni
            br.lines().forEach(linia -> {
                String[] elementyLini = linia.split(",");
                if(elementyLini.length != 11) {
                    throw new IllegalArgumentException("Linia ma zla ilosc elementow {" + elementyLini.length + "}: " + linia);
                }
                Firma firma = new Firma(
                        Integer.parseInt(elementyLini[0]),
                        elementyLini[1],
                        elementyLini[2],
                        elementyLini[3],
                        elementyLini[4],
                        Long.parseLong(elementyLini[5]),
                        elementyLini[6],
                        elementyLini[7],
                        new Adres(elementyLini[8],
                        elementyLini[9],
                        elementyLini[10])
                );
                klienci.add(firma);
            });
        }
    }

    private void zaladujKlienciZDysku() throws IOException {
        File file = new File(nazwyPlikow[0]);
        if(!file.exists()) {
            Terminal.operacjeIO("Brak pliku " + file.getName());
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            //Uzywam strumieni
                br.lines().forEach(linia -> {
                    String[] elementyLini = linia.split(",");
                    if(elementyLini.length != 6) {
                        throw new IllegalArgumentException("Linia ma zla ilosc elementow {" + elementyLini.length + "}: " + linia);
                    }
                    Klient klient = new Klient(
                            Integer.parseInt(elementyLini[0]),
                            elementyLini[1],
                            elementyLini[2],
                            elementyLini[3],
                            elementyLini[4],
                            Long.parseLong(elementyLini[5])
                    );
                    klienci.add(klient);
                });
        }
    }

    public void dodajKlienta(ObiektyZapisywaneNaDysk klient) {
        if(klient.id == null || klient.id == 0) {
            klient.id = pobierzKolejnyNumer();
        }
        klienci.add(klient);
    }

    public boolean usunKlienta(Integer id) {
        ObiektyZapisywaneNaDysk k  = klienci.stream().filter(t -> t.id == id).findAny().orElse(null);
        //nie znalezlismy klienta
        if(k ==null) return false;
        k.zmniejszLicznikWystapien();
        klienci.remove(k);
        return true;
    }

    public void zapiszKlientowNaDysku() throws IOException {
        //zapisujemy klientow
        //Nadpisujemy poprzednie plik

        Files.write(FileSystems.getDefault().getPath(nazwyPlikow[0]),
                klienci.stream().filter(t -> t.getClass() == Klient.class).map(ObiektyZapisywaneNaDysk::pobierzLinieZapisuDoPliku).collect(Collectors.toList()),
                Charset.defaultCharset(),
                StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.CREATE);

        Files.write(FileSystems.getDefault().getPath(nazwyPlikow[1]),
                klienci.stream().filter(t -> t.getClass() == Firma.class).map(ObiektyZapisywaneNaDysk::pobierzLinieZapisuDoPliku).collect(Collectors.toList()),
                Charset.defaultCharset(),
                StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.CREATE);
        Files.write(FileSystems.getDefault().getPath(nazwyPlikow[2]),
                klienci.stream().filter(t -> t.getClass() == OsobaFizyczna.class).map(ObiektyZapisywaneNaDysk::pobierzLinieZapisuDoPliku).collect(Collectors.toList()),
                Charset.defaultCharset(),
                StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.CREATE);
        Files.write(FileSystems.getDefault().getPath(nazwyPlikow[3]),
                klienci.stream().filter(t -> t.getClass() == OsobaFizycznaProwadzacaDzialalnosc.class).map(ObiektyZapisywaneNaDysk::pobierzLinieZapisuDoPliku).collect(Collectors.toList()),
                Charset.defaultCharset(),
                StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.CREATE);
    }
    protected int pobierzKolejnyNumer() {
        //Uzywam strumieni
        return klienci.stream().mapToInt(t -> t.id).max().orElseGet(() -> 0) +1;
    }


}
