Manager de Parole si Audit de Securitate
Proiect pentru Laboratorul de Ingineria Sistemelor Soft (ISS)

Acesta este un proiect de aplicatie practica conceput pentru a demonstra o arhitectura stratificata, operatii cu baza de date prin ORM si un domeniu puternic axat pe securitate cibernetica.

🏛 Arhitectura Sistemului

Aplicatia respecta o arhitectura pe 3 straturi (3-Tier Architecture), separand clar interfata de utilizator, logica de business si accesul la date.

Presentation Layer (Interfata UI): O aplicatie Desktop (ex: JavaFX, C# WPF) sau Web unde utilizatorul introduce parola principala (Master Password) si interactioneaza cu seiful de date. Acest strat nu contine deloc logica de business.

Business Logic / Service Layer: Aici este implementata logica de securitate si regulile aplicatiei. Acest strat se ocupa de:

Criptarea/decriptarea parolelor (ex: folosind algoritmul AES).

Calcularea scorului de complexitate al parolelor (entropy).

Validari incrucisate.

Data Access Layer (Repository): Interfata cu baza de date relationala (ex: SQL Server, MySQL) pentru stocarea informatiilor pe disc. Se utilizeaza obligatoriu o biblioteca ORM (Object-Relational Mapping), precum Entity Framework pentru C# sau Hibernate pentru Java, pentru maparea intre obiectele din cod si tabelele din baza de date.

⚙️ Operatii CRUD 

Sistemul gestioneaza ciclul de viata al conturilor prin urmatoarele operatii principale:

Create: Utilizatorul adauga un cont nou in UI. Datele (Website, Username, Parola in clar) sunt trimise catre Business Layer, care cripteaza parola. Data Layer-ul primeste obiectul si il salveaza prin ORM in baza de date.

Read: Preluarea conturilor din baza de date, decriptarea lor in Business Layer la cerere si trimiterea lor catre UI pentru a fi afisate.

Update: Modificarea unei parole existente pentru un cont (ex: dupa ce contul a expirat).

Delete: Stergerea definitiva a unui cont din baza de date.

🚀 Functionalitati Majore

Aplicatia ofera 7 functionalitati principale care acopera cerintele de complexitate medie:

Autentificare securizata: Login cu un singur Master Password.

Modul CRUD Conturi/Parole: Gestionarea completa a credentialelor salvate.

Modul CRUD Categorii: Gruparea conturilor (ex: Personal, Munca, Conturi Bancare).

Generator de parole sigure: Generare automata de parole cu parametri configurabili de utilizator.

Audit de securitate: Raport vizual ce foloseste logica de business pentru a marca parolele prea scurte, slabe sau refolosite.

Sistem de Auto-Lock: Aplicatia revine la ecranul de login dupa un numar definit (X) de minute de inactivitate.

Jurnal de activitate (Audit log): Jurnal salvat in baza de date ce inregistreaza data si ora fiecarei logari sau incercari esuate.

🛠 Tehnologii Utilizate


Interfata (UI): [ex: C# WPF / JavaFX / React]

Limbaj Backend: [ex: C# / Java]

Baza de date: [ex: Microsoft SQL Server / MySQL / PostgreSQL]

ORM: [ex: Entity Framework / Hibernate]
