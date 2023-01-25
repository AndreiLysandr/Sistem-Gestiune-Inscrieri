package SistemGestiune;

import java.util.ArrayList;

public class GuestsList {

    // Starea obiectului GuestsList
    private final int seats;
    private final ArrayList<Guest> participantsList;
    private final ArrayList<Guest> waitingList;
    private final Info info; // <- Clasa Info cuprinde marea majoritate a textelor pentru o mai buna aranjare a codului

    // Constructorii obiectului GuestsList
    public GuestsList(int seats) {
        this.seats = seats;
        participantsList = new ArrayList<>(seats);
        waitingList = new ArrayList<>();
        this.info = new Info();
    }

    // Comportamentul obiectului GuestsList
    /**
     * 1.
     * --- Prima data se verifica daca 'participantsList' este gol. Daca este gol, se va introduce primul obiect
     * de tipul "Guest" in "participansList:.
     * --- La urmatoarea adaugate a unui "Guest", se va verifica DACA "participantsList" nu a depasit dimensiunea
     * data in constructor la creeare pentru a adauga un "Guest" nou.
     * --- In cazul in care "participantsList" a depasit dimensiunea maxima, noul "Guest" va fi adaugat in
     * "waitingList" si va fi notificat.
     */
    public int registerNewGuest(String lastName, String firstName, String email, String phoneNr) {
        Guest checkGuest = new Guest(lastName, firstName, email, phoneNr);
        int num = -1;
        int emailOrPhone = 1;

        if (participantsList.size() == 0) {
            participantsList.add(new Guest(checkGuest));
            info.regComplete();
            return 0;
        }

        if (searchGuestList(lastName, firstName) == num && searchWaitingList(lastName, firstName) == num) {
            return addGuest(checkGuest);
        }
        else if (searchGuestList(email, emailOrPhone) == num && searchWaitingList(email, emailOrPhone) == num) {
            return addGuest(checkGuest);
        }
        else if (searchGuestList(phoneNr, ++emailOrPhone) == num && searchWaitingList(phoneNr, ++emailOrPhone) == num) {
            return addGuest(checkGuest);
        }
        return -1;
    }

    private int addGuest(Guest checkGuest) {
        if (participantsList.size() < seats) {
            participantsList.add(new Guest(checkGuest));
            info.regComplete();

            return 0;
        }
        else if (participantsList.size() == seats) {
            waitingList.add(new Guest(checkGuest));
            info.waitingListReg(waitingList.size() - 1);

            return waitingList.size() - 1;
        }
        else {
            info.unsuccessfulReg(checkGuest);
            return -1;
        }
    }


    /**
     * 2.
     * --- Am supraincarcat metodele "searchGuestList" si "searchWaitingList" (polimorfism static)
     * pentru a-i oferi utilizatorului optiunea de a cauta un anumit "Guest" dupa
     * nume SI prenume SAU email SAU numar de telefon.
     * --- "int num" este folosit doar in cazul cautarii prin email SAU telefon. Acesta ajuta la metoda "searchGuest"
     * din "Guest", pentru se putea returna tipul cerut dupa car (email SAU phoneNr)
     */
    public int searchGuestList(String lastName, String firstName) {
        for (Guest g : participantsList) {
            if (g.searchGuest(lastName, firstName)) {
                return participantsList.indexOf(g);
            }
        }
        return -1;
    }

    public int searchWaitingList(String lastName, String firstName) {
        for (Guest g : waitingList) {
            if (g.searchGuest(lastName, firstName)) {
                return waitingList.indexOf(g);
            }
        }
        return -1;
    }

    public int searchGuestList(String emailOrPhone, int num) {
        for (Guest g : participantsList) {
            if (g.searchGuest(emailOrPhone, num)) {
                return participantsList.indexOf(g);
            }
        }
        return -1;
    }

    public int searchWaitingList(String emailOrPhone, int num) {
        for (Guest g : waitingList) {
            if (g.searchGuest(emailOrPhone, num)) {
                return waitingList.indexOf(g);
            }
        }
        return -1;
    }


    /**
     * 3.
     * --- Metoda "elimGuest" este supraincarcata, astfel, in functie de apelul din "Main" va cauta sa stearga o
     * persoana dupa nume SI prenume SAU email SAU telefon.
     * --- In cazul in care exista persoane in "waitingList", prima persoana din "waitingList" va fi adaugata
     * pe ultima pozitie din "participantsList" cu ajutorul metodei "addFromWaitingList" si va fi notificata.
     */
    public boolean elimGuest(String lastName, String firstName) {
        for (Guest g : participantsList) {
            if (g.searchGuest(lastName, firstName)) {
                participantsList.remove(g);

                addFromWaitingList();
                return true;
            }
        }

        for (Guest g : waitingList) {
            if (g.searchGuest(lastName, firstName)) {
                waitingList.remove(g);

                addFromWaitingList();
                return true;
            }
        }
        return false;
    }

    public boolean elimGuest(String emailOrPhone, int num) {
        for (Guest g : participantsList) {
            if (g.searchGuest(emailOrPhone, num)) {
                participantsList.remove(g);

                addFromWaitingList();
                return true;
            }
        }

        for (Guest g : waitingList) {
            if (g.searchGuest(emailOrPhone, num)) {
                waitingList.remove(g);

                addFromWaitingList();
                return true;
            }
        }
        return false;
    }

    private void addFromWaitingList() {
        int first = 0;
        if (waitingList.size() != first) {
            participantsList.add(waitingList.get(first));
            info.notifyFirstFromWaiting(waitingList.get(first));
            waitingList.remove(first);
        }
    }


    /**
     * 4.
     * --- Metoda "updateInfo" a fost suprascrisa pentru a trata cazurile in care se cere cautarea dupa
     * nume SAU prenume SAU email SAU telefon.
     */
    public boolean updateInfo(String oldLastName, String oldFirstName,
                              String newLastName, String newFirstName, int lastOrFirst) {
        int cnt = 0;
        int updateGuest = searchGuestList(oldLastName, oldFirstName);
        int updateWaitingGuest = searchWaitingList(oldLastName, oldFirstName);

        if (lastOrFirst == 1) {
            if (updateGuest >= cnt) {
                participantsList.get(updateGuest).setLastName(newLastName);
                info.updateInfo(participantsList.get(updateGuest));

                return true;
            } else if (updateWaitingGuest >= cnt) {
                waitingList.get(updateWaitingGuest).setLastName(newLastName);
                info.updateInfo(waitingList.get(updateWaitingGuest));

                return true;
            }
        }
        else if (lastOrFirst == 2) {
            if (updateGuest >= cnt) {
                participantsList.get(updateGuest).setFirstName(newFirstName);
                info.updateInfo(participantsList.get(updateGuest));
            }
            else if (updateWaitingGuest >= cnt) {
                waitingList.get(updateWaitingGuest).setFirstName(newFirstName);
                info.updateInfo(waitingList.get(updateWaitingGuest));
            }
        }
        return false;
    }

    public boolean updateInfo(String oldEmailOrPhone ,String newEmailOrPhone, int n) {
        int cnt = 0;

        if (n == 1) {
            int updateGuest = searchGuestList(oldEmailOrPhone, n);
            int updateWaitingGuest = searchWaitingList(oldEmailOrPhone, n);

            if (updateGuest >= cnt) {
                participantsList.get(updateGuest).setEmail(newEmailOrPhone);
                info.updateInfo(participantsList.get(updateGuest));

                return true;
            }
            else if (updateWaitingGuest >= cnt) {
                waitingList.get(updateWaitingGuest).setEmail(newEmailOrPhone);
                info.updateInfo(waitingList.get(updateWaitingGuest));

                return true;
            }
        }
        else if (n == 0) {
            int updateGuest = searchGuestList(oldEmailOrPhone, n);
            int updateWaitingGuest = searchWaitingList(oldEmailOrPhone, n);

            if (updateGuest >= cnt) {
                participantsList.get(updateGuest).setPhoneNr(newEmailOrPhone);
                info.updateInfo(participantsList.get(updateGuest));

                return true;
            }
            else if (updateWaitingGuest >= cnt) {
                waitingList.get(updateWaitingGuest).setPhoneNr(newEmailOrPhone);
                info.updateInfo(waitingList.get(updateWaitingGuest));

                return true;
            }
        }
        return false;
    }

    /**
     * --- Metoda "requiredGuest" primeste un arrayList care are 1 element (pentru email SAU telefon) sau 2 elemente
     * (pentru nume SI prenume). Aceasta verifica daca fieldul dat ca parametru exista deja, si va returna fieldul
     * din structura (if / else-if) in functie de elementele care se regasesc in arrayList
     * (nume SI prenume SAU email SAU telefon)
     */
    public String[] requiredGuest(String[] field) {
        for (Guest g : participantsList) {
            for (int i = 0; i < 1; i++) {
                if (g.getLastName().equalsIgnoreCase(field[i].toLowerCase()) &&
                        g.getFirstName().equalsIgnoreCase(field[i+1].toLowerCase())) {
                    return g.getGuestFields();
                }
                else if (g.getEmail().equalsIgnoreCase(field[i].toLowerCase())) {
                    return g.getGuestFields();
                }
                else if (g.getPhoneNr().equalsIgnoreCase(field[i])) {
                    return g.getGuestFields();
                }
            }
        }

        for (Guest g : waitingList) {
            for (int i = 0; i < 1; i++) {
                if (g.getLastName().equalsIgnoreCase(field[i].toLowerCase()) &&
                        g.getFirstName().equalsIgnoreCase(field[i+1].toLowerCase())) {
                    return g.getGuestFields();
                }
                else if (g.getEmail().equalsIgnoreCase(field[i].toLowerCase())) {
                    return g.getGuestFields();
                }
                else if (g.getPhoneNr().equalsIgnoreCase(field[i])) {
                    return g.getGuestFields();
                }
            }
        }
        return null;
    }

    /**
     * 5.
     * --- Am suprascris metoda "toString()" din clasa "Guest" (polimorfism dinamic) pentru a putea afisa mai usor
     * lista persoanelor care participa la eveniment dar si lista persoanelor aflate in asteptare.
     */
    public void joiningPpl() {
        int i = 0;

        if (participantsList.size() == 0) {
            info.joiningPplErr();
        }
        else {
            for (Guest g : participantsList) {
                System.out.println((i+1) + ". " + g);
            }
        }
    }

    /**
     * 6.
     * --- La fel ca in metoda anterioara, "joiningPpl"
     */
    public void waitingListPpl() {
        int i = 0;

        if (waitingList.size() == 0) {
            info.waitingListPplErr();
        }
        else {
            for (Guest g : waitingList) {
                System.out.println((i + 1) + ". " + g);
            }
        }
    }

    /**
     * --- Metode intuitive
     *         |
     *         |
     *         |
     *         V
     */
    // 7.
    public int availableSeats() {
        return seats - participantsList.size();
    }

    // 8.
    public int participatingPpl() {
        return seats - availableSeats();
    }

    // 9.
    public int waitingListNrOfPpl() {
        return waitingList.size();
    }

    // 10.
    public int sumOfRegAndWaitingPpl() {
        return waitingListNrOfPpl() + participatingPpl();
    }

    /**
     * 11.
     * --- Metoda "searchAfterKey" va cauta daca exista "cheia" data ca parametru oriune in fieldurile fiecarui "Guest"
     * care se afla atat in "participantsList" cat si in "waitingList"
     */
    public ArrayList<Guest> searchAfterKey(String key) {
        ArrayList<Guest> keyGuests = new ArrayList<>();

        for (Guest g : participantsList) {
            if (g.keyGuest(key.toLowerCase())) {
                keyGuests.add(new Guest(g));
            }
        }

        for (Guest g : waitingList) {
            if (g.keyGuest(key.toLowerCase())) {
                keyGuests.add(new Guest(g));
            }
        }
        return keyGuests;
    }

    /**
     * --- Metodele de mai jos sunt auxiliare si ne ajuta sa facem mai multe verificari de control pentru a ne asigura
     * de faptul ca nu vor exista persoane care sa aiba fielduri identice.
     * --- In sine metodele se asigura de faptul ca fiecare persoana creeata va fi unica si nu vor exista 2 persoane
     * care sa aiba aceeasi adresa de email, acelasi numar de telefon sau acelasi nume.
     * --- Este posibil ca 2 persoane sa aiba acelasi nume de familie dar NU si aceleasi prenume, la fel cum este
     * posibil ca 2 persoane sa aiba acelasi prenume dar NU si acelasi nume de familie
     */
    public boolean nameChecker(String lastName, String firstName) {
        for (Guest g : participantsList) {
            if (g.getLastName().equalsIgnoreCase(lastName) && g.getFirstName().equalsIgnoreCase(firstName)) {
                return true;
            }
        }

        for (Guest g : waitingList) {
            if (g.getLastName().equalsIgnoreCase(lastName) && g.getFirstName().equalsIgnoreCase(firstName)) {
                return true;
            }
        }
        return false;
    }

    public boolean emailChecker(String email) {
        for (Guest g : participantsList) {
            if (g.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }

        for (Guest g : waitingList) {
            if (g.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    public boolean phoneChecker(String phoneNr) {
        for (Guest g : participantsList) {
            if (g.getPhoneNr().equalsIgnoreCase(phoneNr)) {
                return false;
            }
        }

        for (Guest g : waitingList) {
            if (g.getPhoneNr().equalsIgnoreCase(phoneNr)) {
                return false;
            }
        }
        return true;
    }
}