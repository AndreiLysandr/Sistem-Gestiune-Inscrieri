# Sistem-Gestiune-Inscrieri

  Aplicatia este la baza un sistem de gestiune al inscrierilor. Acesta este compus din 4 clase (Guest, GuestList, Info, Main)
#
Clasa Guest: 

   - Clasa Guest contine campurile necesare definirii unei persoane de tipul guest. Fiecare persoana introdusa, va avea un profil de tipul Guest care va contine un set de metode si campuri necesare initializarii, dar si pentru a se putea verifica daca persoana deja exista, pentru a se putea evita introducerea unei persoane de mai multe ori.
	 
#
Clasa GuestList:
  
   - Clasa GuestList contine campurile necesare definirii unei liste de persoane. Clasa contine si 2 Liste de Guest (participantsList si waitingList) ce vor tine toti participantii inscrisi la eveniment, fie ei in lista participantilor sau cei aflati pe lista de asteptare. 
   - Clasa mai contine si metodele necesare:
        - adaugarii
        - eliminarii
        - verificarii statusului de inscris al unei persoane
        - actualizarii datelor unei persoane

#
Clasa Info:
  
    - Clasa Info contine marea majoritate a textelor ce vor fi folosite in cod. 
    - Clasa a fost creata pentru a putea creste lizibilitatea codului.

#
Clasa Main:

    - Clasa Main reprezinta clasa principala a aplicatiei. Este responsabila de interactiunea cu utilizatorul. 
    - Clasa Main implementeaza functionalitatea aplicatiei:
         - ofera utilizatorului posibilitatea de a schimba numarul initial de locuri al evenimentului.
         - se fac verificari pentru a se stabili daca persoana care urmeaza sa se inscrie, nu a fost deja inscrisa.
         - contine un while care are in interior un switch ce va rula intreg programul.
