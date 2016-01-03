# advse-project

Θα χρειαζεται
Java JdK
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

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

Στο source Tree καντε ingore τα αρχεια και φακελους με . μπροστα

Βαλτε το jdk
Επιλεξτε το project > δεξι κλικ > Properties > Java Build Path > Libraries

Επιλεξτε to JRE System > Edit > Alternate JRE > Installed JREs > Add > Standard VM > JRE HOME: C:\\Program Files\\Java\\jdk1.8.0_65 > Finish

Επιλεξτε to jdk1.8.0 και πατηστε ΟΚ , μετα πατηστε Finish . Τελος πατηστε ΟΚ

Run in Eclipse
Επιλεξτε το project > δεξι κλικ > Run as > Maven build...

Goals: spring-boot:run
Πατηστε Apply και μετα Run

Δοκιμη
Ανοιξτε browser localhost:8080/greeting
