package SistemGestiune;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n~~~~~ Sistem de gestiune inscrieri ~~~~~\n");

        Info info = new Info();
        Scanner sc = new Scanner(System.in);
        boolean cond = true, onOff = true;
        int one = 1, zero = 0, option, initSeats = 0;


        /*
        - Algoritmul de mai jos initializeaza numarul de locuri "seat" pe care le va avea sistemul de gestiune si
        ii ofera utilizatorului optiunea de a realege numarul de locuri disponibile in cazul in care a fost introdusa
        o valoare gresita.
        - Este folosit un StringBuilder pentru a se evita erorile de compilare in cazul in care:
            - este introdus un numar mai mare decat valoarea maxima a unui int,
            - un numar negativ,
            - un string,
            - un char.
         */

        info.intro();

        StringBuilder seats;
        seats = new StringBuilder(sc.next());

        while (cond) {
            if (getSeats(seats)) {
                initSeats = Integer.parseInt(String.valueOf(seats));
            }
            else {
                info.introErr(String.valueOf(seats));
                seats = new StringBuilder(sc.next());
                continue;
            }

            info.updateSeats(initSeats);
            int n = sc.nextInt();

            updateSeats:
            while (cond) {
                switch (n) {
                    case 0:
                        cond = false;
                        break;

                    case 1:
                        info.newSeatsNr();
                        seats = new StringBuilder(sc.next());
                        break updateSeats;

                    default:
                        info.invalidNumber(n, initSeats);
                        n = sc.nextInt();
                        break;
                }
            }
        }

        info.intro2(initSeats);
        GuestsList g = new GuestsList(initSeats);

        System.out.println("\nMai jos aveti un set de comenzi disponibile din care puteti alege:");
        info.printCommands();

        /*
        - Structura 'while' si 'switch' pentru functionalitate si structurarea codului.
         */
        cond = true;
        while (onOff) {

            System.out.println("\nTe rog alege urmatoarea comanda:");
            String command = sc.next();

            switch (command.toLowerCase()) {
                case "0", "help":
                    System.out.println("Comenzile disponibile sunt:");
                    info.printCommands();
                    break;

                case "1", "add":
                    sc.nextLine();
                    String[] arr = new String[4];
                    System.out.println("\nPentru a inscrie o persoana va rugam sa introduceti:");

                    cond = true;
                    while (cond) {
                        System.out.print("- numele de familie: ");
                        arr[0] = sc.nextLine();

                        cond = checkLastName(arr[0]);
                        while (cond) {
                            info.lastNameErr();
                            arr[0] = sc.nextLine();
                            cond = checkLastName(arr[0]);
                        }


                        System.out.print("- prenumele: ");
                        arr[1] = sc.nextLine();

                        cond = checkFirstName(arr[1]);
                        while (cond) {
                            info.firstNameErr();
                            arr[1] = sc.nextLine();
                            cond = checkFirstName(arr[1]);
                        }

                        if (checkName(arr[0], arr[1], g)) {
                            info.nameErr(arr[0], arr[1]);
                            cond = true;
                        }
                    }


                    System.out.print("- email: ");
                    arr[2] = sc.next();

                    cond = checkEmail(arr[2], g);
                    while (cond) {
                        arr[2] = sc.next();
                        cond = checkEmail(arr[2], g);
                    }


                    System.out.print("- numarul de telefon: ");
                    arr[3] = sc.next();

                    cond = checkPhoneNr(arr[3], g);
                    while (cond) {
                        arr[3] = sc.next();
                        cond = checkPhoneNr(arr[3], g);
                    }


                    g.registerNewGuest(arr[0], arr[1], arr[2], arr[3]);
                    sc.nextLine();
                    break;

                case "2", "check":
                    info.searchForGuest();

                    option = sc.nextInt();
                    while (cond) {
                        if (option < 1 || option > 3) {
                            option = sc.nextInt();
                        } else {
                            cond = false;
                        }
                    }

                    String[] searchCase;
                    searchCase = chooseSearchingMethod(sc, option);

                    if (searchForGuest(info, g, one, zero, option, searchCase)) {
                        info.regInParticipantsList();
                    } else {
                        info.unreg();
                    }
                    break;

                case "3", "remove":
                    info.deleteMethodForGuest();

                    option = sc.nextInt();
                    while (cond) {
                        if (option < 1 || option > 3) {
                            option = sc.nextInt();
                        } else {
                            cond = false;
                        }
                    }
                    String[] deleteCase;
                    deleteCase = chooseSearchingMethod(sc, option);

                    if (deleteCase != null) {
                        switch (option) {
                            case 1:
                                if (g.elimGuest(deleteCase[0], deleteCase[1])) {
                                    info.guestDeleted();
                                } else {
                                    info.unreg();
                                }
                                break;
                            case 2:
                                if (g.elimGuest(deleteCase[0], one)) {
                                    info.guestDeleted();
                                } else {
                                    info.unreg();
                                }
                                break;
                            case 3:
                                if (g.elimGuest(deleteCase[0], zero)) {
                                    info.guestDeleted();
                                } else {
                                    info.unreg();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    else {
                        info.unreg();
                    }
                    break;

                case "4", "update":
                    info.searchForGuest();

                    option = sc.nextInt();
                    while (cond) {
                        if (option < 1 || option > 3) {
                            option = sc.nextInt();
                        } else {
                            cond = false;
                        }
                    }
                    String[] updateCase;
                    updateCase = g.requiredGuest(chooseSearchingMethod(sc, option));

                    if (updateCase != null) {
                        if (searchForGuest(info, g, one, zero, option, updateCase)) {
                            info.fieldToUpdate();

                            cond = true;
                            option = sc.nextInt();

                            while (cond) {
                                if (option < 1 || option > 4) {
                                    option = sc.nextInt();
                                } else {
                                    cond = false;
                                }
                            }

                            switch (option) {
                                case 1:
                                    sc.nextLine();
                                    System.out.println("Introduceti noul nume:");
                                    String newLastName = sc.nextLine();

                                    cond = true;
                                    while (cond) {
                                        if (checkLastName(updateCase[0])) {
                                            info.lastNameErr();
                                            newLastName = sc.nextLine();
                                        }
                                        else if (checkName(newLastName, updateCase[1], g)) {
                                            info.existingLastName(newLastName);
                                            newLastName = sc.nextLine();
                                        }
                                        else {
                                            cond = false;
                                        }
                                    }
                                    g.updateInfo(updateCase[0], updateCase[1], newLastName, updateCase[1], option);

                                    break;
                                case 2:
                                    sc.nextLine();
                                    System.out.println("Introduceti noul prenume:");
                                    String newFirstName = sc.nextLine();

                                    cond = true;
                                    while (cond) {
                                        if (checkFirstName(updateCase[1])) {
                                            info.firstNameErr();
                                            newFirstName = sc.nextLine();
                                        }
                                        else if (checkName(updateCase[0], newFirstName, g)) {
                                            info.existingFirstName(updateCase[0], newFirstName);
                                            newFirstName = sc.nextLine();
                                        }
                                        else {
                                            cond = false;
                                        }
                                    }
                                    g.updateInfo(updateCase[0], updateCase[1], updateCase[0], newFirstName, option);

                                    break;
                                case 3:
                                    System.out.println("Introduceti noul email:");
                                    String newEmail = sc.next();

                                    cond = true;
                                    while (cond) {
                                        if (checkEmail(newEmail, g)) {
                                            newEmail = sc.next();
                                        }
                                        else {
                                            cond = false;
                                        }
                                    }
                                    g.updateInfo(updateCase[2], newEmail, one);

                                    break;
                                case 4:
                                    System.out.println("Introduceti noul numar de telefon:");
                                    String newPhoneNr = sc.next();

                                    cond = true;
                                    while (cond) {
                                        if (checkPhoneNr(newPhoneNr, g)) {
                                            newPhoneNr = sc.next();
                                        }
                                        else {
                                            cond = false;
                                        }
                                    }
                                    g.updateInfo(updateCase[3], newPhoneNr, zero);

                                    break;

                                default:
                                    info.unreg();
                                    break;
                            }
                        }
                        else {
                            info.unreg();
                            break;
                        }
                    }
                    else {
                        info.unreg();
                    }
                    break;

                case "5", "guests":
                    info.listOfParticipants();
                    g.joiningPpl();
                    break;

                case "6", "waitlist":
                    info.waitingList();
                    g.waitingListPpl();
                    break;

                case "7", "available":
                    info.seatsAvailable(g.availableSeats());
                    break;

                case "8", "guests_no":
                    info.guestsNr(g.participatingPpl());
                    break;

                case "9", "waitlist_no":
                    info.waitingGuestsNr(g.waitingListNrOfPpl());
                    break;

                case "10", "subscribe_no":
                    info.sumOfAllGuests(g.sumOfRegAndWaitingPpl());
                    break;

                case "11", "search":
                    if (g.sumOfRegAndWaitingPpl() == 0) {
                        System.out.println("Nu exista persoane inscrise!");
                    }
                    else {
                        info.insertKey();

                        sc.nextLine();
                        String key = sc.nextLine();

                        info.displayKeyGuests(key);
                        ArrayList<Guest> gArr = new ArrayList<>(g.searchAfterKey(key));

                        int i = 0;
                        for (Guest guest : gArr) {
                            System.out.println((i + 1) + ". \"" + guest + "\" are " + guest.guestCommonKey(key));
                            i++;
                        }
                    }
                    break;

                case "12", "quit":
                    info.exit();
                    onOff = false;
                    break;

                default:
                    info.defaultCommand();
            }
        }
    }

    /*
    - Metoda este folosita pentru a se verifica daca StringBuilderul "seats" citit de la tastatura este sau nu numar.
     */
    public static boolean getSeats(StringBuilder seats) {
        for (int i = 0; i < seats.length(); i++) {
            if (!Character.isDigit(seats.charAt(i))) {
                return false;

            }
        }
        return true;
    }

    /*
    - Metoda verifica daca numele este valid:
        - nu este 'null'
        - nu incepe si nu se termina cu un spatiu
        - nu incepe si nu se termina cu un caracter care nu este litera sau cifra
    - Verificarile se fac din cauza ca Java nu va face distinctia dintre
        - "Popescu" si
        - "Popescu " sau " Popescu" sau "_Popescu" sau "Popescu_"
    - Asa 2 persoane cu nume identice vor fi considerate a fi diferite de Java din cauza unui spatiu
    sau alt caracter care nu este litera sau cifra.
    - Da litera sau cifra. Ma uit la tine Elon Musk!
     */
    public static boolean checkLastName(String lastName) {

        return lastName.length() == 0 ||
                Character.isWhitespace(lastName.charAt(0)) ||
                Character.isWhitespace(lastName.charAt(lastName.length() - 1)) ||
                !Character.isAlphabetic(lastName.charAt(0)) ||
                !Character.isAlphabetic(lastName.charAt(lastName.length() - 1));
    }

    /*
    - Metoda face acelasi lucru ca metoda de mai sus doar ca face verificarea pentru prenume.
     */
    public static boolean checkFirstName(String firstName) {
        return firstName.length() == 0 ||
                Character.isWhitespace(firstName.charAt(0)) ||
                Character.isWhitespace(firstName.charAt(firstName.length() - 1)) ||
                !Character.isAlphabetic(firstName.charAt(0)) ||
                !Character.isAlphabetic(firstName.charAt(firstName.length() - 1));
    }

    /*
    - Metoda apeleaza metoda "nameChecker" din "GuestList" pentru a verifica daca numele valid introdus,
    nu se regaseste deja la un alt "Guest"
     */
    public static boolean checkName(String lastName, String firstName, GuestsList g) {
        return g.nameChecker(lastName, firstName);
    }

    /*
    - Metoda verifica daca emailul este valid:
        - Verifica daca exista nu mai mult de 1 '@' in corpul email-ului
        - Verifica daca exista minim 1 '.' (punct) / maxim 5 puncte in corpul email-ului
        - verifica daca lungimea minima a email-ului este de 14 caractere
    - Astfel un email ge genul aaa@gmail.com este compus din:
        - 4 caractere minim pentru nume
        - 1 '@'
        - 1 '.' (punct) minim
        - "gmail", "yahoo", etc (5 caractere) si "com" (3 caractere)
        - 14 caractere minim
     */
    public static boolean checkEmail(String email, GuestsList g) {
        int count = 0, count2 = 0;
        char key = '@', key2 = '.';

        if (g.emailChecker(email)) {
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == key) {
                    count++;
                }
                if (email.charAt(i) == key2) {
                    count2++;
                }
            }

            if (count == 1 && (count2 > 0 && count2 < 5) && email.length() >= 14) {
                return false;
            }
            else {
                System.out.println("Introduceti o adresa valida de email!");
                return true;
            }
        }
        System.out.println("Acest email este deja folosit!"
                + "\nIntroduceti o alta adresa de email!");
        return true;
    }

    /*
    - Metoda verifica daca:
        - fiecare caracter al numarului de telefon este cifra
        - numarul de telefon are exact 10 cifre
     */
    public static boolean checkPhoneNr(String phoneNr, GuestsList g) {
        if (g.phoneChecker(phoneNr)) {
            if (phoneNr.length() != 10) {
                System.out.println("Numarul de telefon trebuie sa contina 10 cifre!");
                return true;
            }

            for (int i = 0; i < phoneNr.length(); i++) {
                if (!Character.isDigit(phoneNr.charAt(i))) {
                    System.out.println("Numarul de telefon trebuie sa fie format doar din cifre!");
                    return true;
                }
            }
        }
        else {
            System.out.println("Acest numar de telefon este deja folosit!"
                    + "\nIntroduceti un alt numar de telefon!");
            return true;
        }
        return false;
    }

    /*
    - Metoda este apelata de fiecare data cand este solicitata cautarea dupa:
        - nume si prenume
        - email
        - telefon
     */
    public static String[] chooseSearchingMethod(Scanner sc, int option) {
        String[] str = new String[2];

        System.out.print("\nIntrodu ");
        switch (option) {
            case 1:
                sc.nextLine();
                System.out.print("numele: ");
                str[0] = sc.nextLine();
                System.out.print("Introdu prenumele: ");
                str[1] = sc.nextLine();
                System.out.println();
                return str;
            case 2:
                System.out.println("adresa de email:");
                str[0] = sc.next();
                System.out.println();
                return str;
            case 3:
                System.out.println("numarul de telefon:");
                str[0] = sc.next();
                System.out.println();
                return str;
            default:
                return null;
        }
    }

    /*
    - Metoda este apelata de fiecare daca cand se doreste cautarea unui "Guest", cand se doreste sa se afle daca
    un anumit fielt exista deja intr-unul dintre obiectele de tip "Guest" care se afla deja in arrayList-ul
    "participantsList" sau "waitingList"
     */
    public static boolean searchForGuest(Info info, GuestsList g, int one, int zero, int option, String[] searchCase) {
        switch (option) {
            case 1:
                if (g.searchGuestList(searchCase[0], searchCase[1]) >= 0) {
                    return true;
                } else if (g.searchWaitingList(searchCase[0], searchCase[1]) >= 0) {
                    info.regInWaitingList();
                    return true;
                } else {
                    return false;
                }
            case 2:
                if (g.searchGuestList(searchCase[0], one) >= 0) {
                    return true;
                } else if (g.searchWaitingList(searchCase[0], one) >= 0) {
                    info.regInWaitingList();
                    return true;
                } else {
                    return false;
                }
            case 3:
                if (g.searchGuestList(searchCase[0], zero) >= 0) {
                    return true;
                } else if (g.searchWaitingList(searchCase[0], zero) >= 0) {
                    info.regInWaitingList();
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}

