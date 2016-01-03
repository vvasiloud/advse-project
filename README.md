# advse-project

Θα χρειαζεται

Eclipse for java EE
https://eclipse.org/downloads/

Mongo DataBase
https://www.mongodb.org/downloads#production

SourceTree
https://www.sourcetreeapp.com/


Mongo Database Install
τρεξεται το αρχειο .msi
δημιουργιστε ενα φακελο C:/data/db
ανοιξτε ενα command line ("window key" + R)  cmd
cd C:\\Program Files\\MongoDB\\Server\\3.2\\bin
mongod

Για το project

Κανεται clone απο το git στο Source Tree

Import στο Eclipse
File > Import... > Maven > Existing Maven Projects

Run in Eclipse
Επιλεξτε το project > δεξι κλικ > Run as > Maven build...

Goals: spring-boot:run
Πατηστε Apply και μετα Run 

Δοκιμη
Ανοιξτε browser localhost:8080/greeting
