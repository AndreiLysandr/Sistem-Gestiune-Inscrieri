package SistemGestiune;

public class Guest {

    // Starea obiectului Guest
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNr;

    // Constructorii obiectului Guest
    public Guest(String lastName, String firstName, String email, String phoneNr) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNr = phoneNr;
    }

    public Guest(Guest g) {
        this(g.lastName, g.firstName, g.email, g.phoneNr);
    }

    // Comportamentul obiectului Guest
    /**
     * --- Cele 2 metode sunt cele la care se ajunge pentru a se face verificarea campurilor, pentru a se evita
     * obiecte de tipul "Guest" duplicate sau care au campuri asemanatoare.
     * --- In a 2-a metoda se poate observa mai bine functionalitatea variabilei "int n"
     */
    public boolean searchGuest(String lastName, String firstName) {
        return (this.lastName.equalsIgnoreCase(lastName) && this.firstName.equalsIgnoreCase(firstName));
    }

    public boolean searchGuest(String emailOrPhoneNr, int n) {
        if (n == 1) {
            return this.email.equalsIgnoreCase(emailOrPhoneNr);
        }
        return this.phoneNr.equalsIgnoreCase(emailOrPhoneNr);
    }

    /**
     * --- Metoda returneaza campul in care s-a gasit "cheia" din metoda "searchAfterKey" din "GuestList"
     */
    public boolean keyGuest(String key) {
        return this.lastName.toLowerCase().contains(key) ||
                this.firstName.toLowerCase().contains(key) ||
                this.email.toLowerCase().contains(key) ||
                this.phoneNr.toLowerCase().contains(key);
    }

    /**
     * --- Metoda returneaza un string ce contine si campul in care a fost gasita "cheia" data ca parametru si
     * gasita intr-un obiect de tipul "Guest" cu ajutorul metodei "searchAfterKey" din "GuestList"
     */
    public String guestCommonKey(String key) {
        if (this.lastName.toLowerCase().contains(key)) {
            return "campul \"lastName\" care contine \"" + key + "\" -> "
                    + "\"lastName = " + this.lastName.toLowerCase() + "\"";
        }
        else if (this.firstName.toLowerCase().contains(key)) {
            return "campul \"firstName\" care contine \"" + key + "\" -> "
                    + "\"firstName = " + this.firstName.toLowerCase() + "\"";
        }
        else if (this.email.toLowerCase().contains(key)) {
            return "campul \"email\" care contine \"" + key + "\" -> " + "\"email = " + this.email + "\"";
        }
        else if (this.phoneNr.toLowerCase().contains(key)) {
            return "campul \"phoneNr\" care contine \"" + key + "\" -> " + "\"phoneNr = " + this.phoneNr + "\"";
        }
        return "\n";
    }

    @Override
    public String toString() {
        return this.lastName + " " + this.firstName;
    }

    /**
     * --- Gettere si Settere
     */
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    /**
     * --- Metoda returneaza toate fieldurile unui "Guest" pentru metoda "requiredGuest" din "GuestList". Aceasta ajuta
     * la cautarea unui camp nume SI prenume, email SAU telefon pentru a se putea compara si verifica daca
     * respectivul camp exista deja.
     * --- Metoda ajuta la o verificare preliminara inainte de a se face update la unul din campurile unui guest,
     * pentru a se evita duplicarea datelor.
     */
    public String[] getGuestFields() {
        String[] str = new String[4];

        str[0] = getLastName();
        str[1] = getFirstName();
        str[2] = getEmail();
        str[3] = getPhoneNr();

        return str;
    }
}
