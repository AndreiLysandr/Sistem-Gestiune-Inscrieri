package SistemGestiune;
public class Info {

    /**
     * --- Clasa "Info" contine marea parte a textelor, pentru a putea fi introduse mai frumos in cod
     * si pentru a se evita incarcarea codului cu text.
     */

    public void regComplete() {
        System.out.println("Inscrierea a fost efectuata cu succes! Te asteptam!");
    }

    public void waitingListReg(int n) {
        System.out.println("\nLocurile disponibile au fost deja ocupate."
                + "\nInscrierea a fost efectuata cu succes in lista de asteptare pe pozitia " + (n + 1) + "."
                + "\nTe vom anunta daca un loc devine disponibil!");
    }

    public void unsuccessfulReg(Guest g) {
        System.out.println("Dl/Dna " + g.getLastName() + " " + g.getFirstName()
                + " a fost deja inscris(a) la eveniment!");
    }

    public void regInWaitingList() {
        System.out.println("Persoana se afla pe lista de asteptare!");
    }

    public void regInParticipantsList() {
        System.out.println("Pesroana este inregistrata la eveniment!");
    }

    public void guestDeleted() {
        System.out.println("Persoana a fost stearsa din lista persoanelor inregistrate la eveniment!");
    }

    public void unreg() {
        System.out.println("Persoana nu este inscrisa la eveniment!");
    }

    public void notifyFirstFromWaiting(Guest g) {
        System.out.println("\n" + g.getLastName().toUpperCase() + " " + g.getFirstName().toUpperCase()
                + " a fost mutat din lista de asteptare, in lista de participare a evenimentului!");
    }
    public void updateInfo(Guest g) {
        System.out.println("\nDatele au fost actualizate:"
                + "\n- nume de familie: " + g.getLastName().toUpperCase()
                + "\n- prenume: " + g.getFirstName().toUpperCase()
                + "\n- email: " + g.getEmail()
                + "\n- nr. telefon: " + g.getPhoneNr());
        System.out.println();
    }

    public void joiningPplErr() {
        System.out.println("Nu exista persoane inscrise la eveniment!");
    }

    public void waitingListPplErr() {
        System.out.println("Nu exista persoane in lista de asteptare!");
    }

    public void printCommands() {
        System.out.println("0. -------- (help) Afiseaza aceasta lista de comenzi"
                + "\n1. -------- (add) Adauga o noua persoana (inscriere)"
                + "\n2. -------- (check) Verifica daca o persoana este inscrisa la eveniment"
                + "\n3. -------- (remove) Sterge o persoana existenta din lista"
                + "\n4. -------- (update) Actualizeaza detaliile unei persoane"
                + "\n5. -------- (guests) Lista de persoane care participa la eveniment"
                + "\n6. -------- (waitlist) Persoanele din lista de asteptare"
                + "\n7. -------- (available) Numarul de locuri libere"
                + "\n8. -------- (guests_no) Numarul de persoane care participa la eveniment"
                + "\n9. -------- (waitlist_no) Numarul de persoane din lista de asteptare"
                + "\n10. ------- (subscribe_no) Numarul total de persoane inscrise"
                + "\n11. ------- (search) Cauta toti invitatii conform sirului de caractere introdus"
                + "\n12. ------- (quit) Inchide aplicatia");
    }

    public void searchForGuest() {
        System.out.println("\nTe rog sa alegi felul in care doresti sa se realizeze cautarea invitatului:"
                + "\n1. nume si prenume"
                + "\n2. email"
                + "\n3. numar de telefon\n");
    }

    public void deleteMethodForGuest() {
        System.out.println("\nTe rog sa alegi dupa ce criteriu de cautare doresti sa se realizeze stergerea:"
                + "\n1. nume si prenume"
                + "\n2. email"
                + "\n3. numar de telefon\n");
    }

    public void fieldToUpdate() {
        System.out.println("\nTe rog sa alegi ce anume doresti sa actualizezi:"
                + "\n1. nume de familie"
                + "\n2. prenume"
                + "\n3. email"
                + "\n4. numar de telefon");
    }

    public void listOfParticipants() {
        System.out.println("Lista persoanelor care au loc si sunt inscrise la eveniment:");
    }

    public void waitingList() {
        System.out.println("Lista persoanelor inscrise la eveniment, dar care se afla pe lista de asteptare:");
    }

    public void seatsAvailable(int seats) {
        if (seats > 0) {
            System.out.print("\nAu mai ramas " + seats + " locuri disponibile la eveniment!\n");
        } else {
            System.out.println("\nDin pacate nu mai sunt locuri disponibile la eveniment!"
                    + "\nPersoanele care continua sa se inscrie vor fi mutate pe lista de asteptare.");
        }
    }

    public void guestsNr(int guests) {
        if (guests == 0) {
            System.out.println("Nu este inscrisa nicio persoana care participa la eveniment!");
        }
        else if (guests == 1) {
            System.out.println("Este inscrisa o singura persoana care participa la eveniment!");
        }
        else {
            System.out.println("Sunt inscrise " + guests + " persoane care participa la eveniment!");
        }
    }

    public void waitingGuestsNr(int waitingGuests) {
        System.out.println("La eveniment sunt inscrise " + waitingGuests + " persoane pe lista de asteptare.");
    }

    public void sumOfAllGuests(int allGuests) {
        if (allGuests == 0) {
            System.out.println("Pana in acest moment nu a fost inscrisa nicio persoana la eveniment");
        }
        else if (allGuests == 1) {
            System.out.println("Pana in acest moment a fost inscrisa o persoana care se afla in lista de asteptare!");
        }
        else {
            System.out.println("Pana in acest moment au fost inscrise " + allGuests + " persoane, care se afla"
                    + " atat in lista de participare cat si in lista de asteptare!");
        }
    }

    public void insertKey() {
        System.out.println("\nIntroduceti un sir de caractere, entru a putea afisa o lista cu toti invitatii "
                + "a caror date coincid cu sirul de caractere introdus, :");
    }

    public void displayKeyGuests(String key) {
        System.out.println("\nInvitatii care contin sirul de caractere \"" + key + "\" sunt:");
    }

    public void exit() {
        System.out.println("\nVa multimim pentru ca ati folosit sistemul nostru de gestiune."
                + "\nLa revedere!");
    }

    public void defaultCommand() {
        System.out.println("\nAceasta nu este o comanda disponibila!"
                + "\nPentru a afisa lista de comenzi, apasa pe 0 sau tasteaza 'help'");
    }

    public void lastNameErr() {
        System.out.print("\nNumele de familie nu poate sa contina spatii libere"
                + " la inceputul ori sfarsitul acestuia, alte caractere non-aplhanumerice sau sa fie vid!"
                + "\n- va rugam sa reintroduceti numele de familie: ");
    }

    public void firstNameErr() {
        System.out.print("\nPrenumele nu poate sa contina spatii libere"
                + " la inceputul ori sfarsitul acestuia, alte caractere non-aplhanumerice sau sa fie vid!"
                + "\n- va rugam sa reintroduceti prenumele: ");
    }

    public void nameErr(String lastName, String firstName) {
        System.out.println("Persoana cu numele " + lastName.toUpperCase() + " " + firstName.toUpperCase()
                + " a fost deja inscrisa la eveniment!"
                + "\n\nTe rog sa reintroduci numele de familie si prenumele pentru un utilizator nou:");
    }

    public void existingLastName(String lastName) {
        System.out.println("Persoana cu numele de familie " + lastName.toUpperCase()
                + " a fost deja inscrisa la eveniment! \nTe rog sa introduci un alt nume de familie valid:");
    }

    public void existingFirstName(String lastName, String firstName) {
        System.out.println("Persoana cu numele de familie " + lastName.toUpperCase()
                + " si prenumele " + firstName.toUpperCase() + " a fost deja inscrisa la eveniment! "
                + "\nTe rog sa introduci un alt prenume valid:");
    }

    public void intro() {
        System.out.println("Bun venit la sistemul de gestiune pentru evenimente!"
                + "\nPentru inceput va rog sa introduceti mai jos numarul de locuri disponibile pentru eveniment:");
    }

    public void intro2(int initSeats) {
        System.out.println("\nBun venit! \n"
                + (initSeats == 1
                ? "A fost alocat 1 loc "
                : "Au fost alocate " + initSeats + " locuri ")
                + "pentru eveniment!");
    }

    public void introErr(String seats) {
        System.out.println("\nSe pare ca \"" + seats + "\" nu este un numar valid. "
                + "\nTe rog sa reintroduci numarul de locuri pe care doresti sa il ai!:");
    }

    public void updateSeats(int initSeats) {
        System.out.println("\nNumarul de locuri alocate este " + initSeats + "."
                + "\nDaca doriti sa actualizati numarul de locuri apasati -> \"1\""
                + "\nDaca doriti sa continuati apasati -> \"0\"");
    }

    public void newSeatsNr() {
        System.out.println("\nIntroduceti noua capacitate pe care doriti sa o aiba evenimentul:");
    }

    public void invalidNumber(int n, int initSeats) {
        System.out.println("\n" + n + " nu este o optiune valida! \nVa rog sa apasati pe \"1\" saun \"0\""
                + "\nNumarul de locuri alocate evenimentului este momentan de: " + initSeats);
    }
}