package projekt.firma;

/**
 * Klasa statyczna pomocnicza ktora wyswietla komunikaty na ekranie uzytkownika w roznych kolorach.
 * (Uwaga nie dziala pod windows - dziala w intellij).
 *
 */
public class Terminal {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    //Zmienna ktora definiuje czy wyswietlac informacje systemowe
    public static boolean czyPokazywacSystem = true;


    public static void bledy(String wiadomosc) {
        System.out.println(ANSI_RED + wiadomosc);
    }

    public static void aplikacja(String wiadomosc) {
        System.out.println(ANSI_GREEN + wiadomosc);
    }

    public static void operacjeIO(String wiadomosc) {
        System.out.println(ANSI_YELLOW + wiadomosc);
    }

    public static void wynik(String wiadomosc) {
        System.out.println(ANSI_CYAN + wiadomosc);
    }

    public static void system(String wiadomosc) {
        if(czyPokazywacSystem) {
            System.out.println(ANSI_BLUE + wiadomosc);
        }
    }

    public static void powitanie(String wiadomosc) {
        System.out.println(ANSI_PURPLE + wiadomosc);
    }

    /*public static void wypiszNaBialo(String wiadomosc) {
        System.out.println(ANSI_WHITE + wiadomosc);
    }*/

}
