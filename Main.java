package projekt.firma;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

       public static void main(String[] args) {



           if(args.length >=1) {
               //Sprawdzamy czy przekazano jako parametr "tak". Ustawiamy zmienna statyczna w klasie pomocniczej terminal. Decyduje ona czy nalezy pokazywac tworzenie obiektow czy nie.
               Terminal.czyPokazywacSystem = "tak".equalsIgnoreCase(args[0].trim());
           }
           if(Terminal.czyPokazywacSystem) {
               Terminal.powitanie("Pokazujemy tworzenie i usuwanie obiektow");

           } else {
               Terminal.powitanie("Nie pokazujemy tworzenie i usuwanie obiektow. Jesli chcesz to zmienic uruchom aplikacje poleceniem \"run.bat tak\"");
           }
        Terminal.aplikacja("Aplikacja do zarzadzania klientami typu CRM");
           CRM crm = new CRM();
           try {
               crm.zaladujDaneZDysku();
           } catch (IOException e) {
               Terminal.bledy("Nie udalo sie zaladowac danych z dysku " + e.getMessage());
               return;
           }

           Terminal.aplikacja("Klientow w bazie: " + crm.pobierzWszystkich().size() + " w tym Klientow: " + crm.pobierzKlientow().size() + " Firm: " + crm.pobierzFirmy().size() + " Osob: " + crm.pobierzOsoby().size() + " Osob prowadzacych dzialalnosc: " + crm.pobierzOsobaFizycznaProwadzacaDzialalnosc().size());

            wyswietlMenuGlowne(crm);



       }


       public static void wyswietlMenuGlowne(CRM crm) {
           //Jesli nie ma juz ludzikow to dodajemy od razu
           if(crm.pobierzWszystkich().isEmpty()) {
               Terminal.aplikacja("Baza klientow jest pusta, jedyna mozliwa operacja to dodanie klienta");
               dodajKlienta(crm);
           }

           Terminal.aplikacja("Co chcesz zrobic ?");
           Terminal.aplikacja("1. Wyswietl klientow");
           Terminal.aplikacja("2. Dodaj klienta");
           Terminal.aplikacja("3. Usun klienta");
           Terminal.aplikacja("\n0. Koniec");

           int wynik  = wczytajLiczbe(0,3);
           switch (wynik){
               case 0: wyjdz(crm);break;
               case 1: wyswietlKlientow(crm);break;
               case 2: dodajKlienta(crm);break;
               case 3: usunKlienta(crm);break;
               default: wyjdz(crm);break;
           }

       }


    private static void wyjdz(CRM crm) {
        Terminal.operacjeIO("Zapisuje dane na dysku");
        try {
            crm.zapiszKlientowNaDysku();
        } catch (IOException ex) {
            Terminal.bledy("Nastapil blad podczas zapisu" + ex.getMessage());
        }
        System.exit(0);
    }

    private static void wyswietlKlientow(CRM crm) {
        List<ObiektyZapisywaneNaDysk> obiekty = crm.pobierzKlientow();
        if(!obiekty.isEmpty()) {
            Terminal.aplikacja("Lista klientow:");
            obiekty.forEach(o -> Terminal.wynik(o.toString()));
        }
        obiekty = crm.pobierzFirmy();
        if(!obiekty.isEmpty()) {
            Terminal.aplikacja("Lista firm:");
            obiekty.forEach(o -> Terminal.wynik(o.toString()));
        }
        obiekty = crm.pobierzOsoby();
        if(!obiekty.isEmpty()) {
            Terminal.aplikacja("Lista Osob Fizycznych:");
            obiekty.forEach(o -> Terminal.wynik(o.toString()));
        }
        obiekty = crm.pobierzOsobaFizycznaProwadzacaDzialalnosc();
        if(!obiekty.isEmpty()) {
            Terminal.aplikacja("Lista Osob Fizycznych Prowadzacych dzialanosc:");
            obiekty.forEach(o -> Terminal.wynik(o.toString()));
        }
        wyswietlMenuGlowne(crm);

    }








    private static void usunKlienta(CRM crm) {
          Terminal.aplikacja("Podaj numer klienta do usuniecia");
          int max = crm.pobierzKolejnyNumer()-1;
          int idKlientaDoUsuniecia = wczytajLiczbe(1,max);
          boolean wynik= crm.usunKlienta(idKlientaDoUsuniecia);
          if(wynik) {
              Terminal.wynik("Usunelismy klienta: " + idKlientaDoUsuniecia);
          } else {
              Terminal.bledy("Nie udalo sie usunac klienta o id " + idKlientaDoUsuniecia + " sprawdz czy wpisales poprawne id");
          }
           wyswietlMenuGlowne(crm);
       }

    private static void dodajKlienta(CRM crm) {

        Terminal.aplikacja("Podaj typ klienta:");
        Terminal.aplikacja("1. klient - lead marketingowy");
        Terminal.aplikacja("2. Osoba fizyczna");
        Terminal.aplikacja("3. Firma");
        Terminal.aplikacja("4. Osoba fizyczna prowadzaca dzialalnosc");
        Terminal.aplikacja("\n0. Powrot do menu");

        int wynik  = wczytajLiczbe(0,4);
        switch (wynik){
            case 0: wyswietlMenuGlowne(crm);break;
            case 1: dodajKlienta2(crm);break;
            case 2: dodajOsoba(crm);break;
            case 3: dodajFirma(crm);break;
            case 4: dodajOsobaDzialalnosc(crm);break;
            default: wyswietlMenuGlowne(crm);break;
        }
        wyswietlMenuGlowne(crm);
    }

    private static void dodajKlienta2(CRM crm) {
           Klient k =  new Klient(null,wczytajStringa("nazwa"),wczytajStringa("imie"),wczytajStringa("nazwisko"),wczytajStringa("email"),wczytajTelefon(true));
           crm.dodajKlienta(k);
        Terminal.wynik("Dodano klienta: " + k);
    }

    private static void dodajOsoba(CRM crm) {
        OsobaFizyczna k =  new OsobaFizyczna(null,
                wczytajStringa("nazwa"),
                wczytajStringa("imie"),
                wczytajStringa("nazwisko"),
                wczytajStringa("email"),
                wczytajTelefon(true),
                wczytajAdres(),
                wczytajDowod());
        crm.dodajKlienta(k);
        Terminal.wynik("Dodano osobe fizyczna : " + k);

    }

    private static void dodajOsobaDzialalnosc(CRM crm) {

        OsobaFizycznaProwadzacaDzialalnosc k =  new OsobaFizycznaProwadzacaDzialalnosc(null,
                wczytajStringa("nazwa"),
                wczytajStringa("imie"),
                wczytajStringa("nazwisko"),
                wczytajStringa("email"),
                wczytajTelefon(true),
                wczytajAdres(),
                wczytajDowod(),
                wczytajStringa("nip"));
        crm.dodajKlienta(k);

        Terminal.wynik("Dodano osobe fizyczna prowadzaca dzialalnosc: " + k);
    }


    private static void dodajFirma(CRM crm) {
        Firma firma =  new Firma(null,
                wczytajStringa("nazwa"),
                wczytajStringa("imie"),
                wczytajStringa("nazwisko"),
                wczytajStringa("email"),
                wczytajTelefon(true),
                wczytajStringa("nip"),
                wczytajStringa("regon"),
                wczytajAdres());
        crm.dodajKlienta(firma);
        Terminal.wynik("Dodano firme: " + firma);
    }

    private static DokumentTozsamosci wczytajDowod() {
           return new DokumentTozsamosci(wczytajTyp(),wczytajStringa("numer"));
    }

    private static String wczytajTyp() {
        Terminal.aplikacja("Podaj typ dowodu:");
        Terminal.aplikacja("1. Dowod Osobisty");
        Terminal.aplikacja("2. Paszport");

        int wynik  = wczytajLiczbe(1,2);
        switch (wynik){
            case 1: return "Dowod osobisty";
            case 2: return "Paszport";
            default: return "";
        }

    }

    private static Adres wczytajAdres() {
           return new Adres(wczytajStringa("ulica"),wczytajStringa("miasto"),wczytajStringa("kod pocztowy"));
    }




    private static String wczytajStringa(String nazwaPola) {
        Terminal.aplikacja("Podaj " + nazwaPola + " :");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

    }


    private static int wczytajLiczbe(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        int a = scanner.nextInt();
        if(a >= min && a <= max) {
            return a;
        } else {
            return wczytajLiczbe(min,max);
        }

    }

    private static long wczytajTelefon(boolean pokazywac) {
        if(pokazywac) Terminal.aplikacja("Podaj numer telefonu  w formacie same cyfry np: \'666777999\'");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextLong()) {
            scanner.next();
        }
        long a = scanner.nextLong();
        if(a >= 100000000l && a <= 900000000) {
            return a;
        } else {
            return wczytajTelefon(false);
        }

    }

}

