# EtherasTickets

Θα χρειάζεται
Java JdK
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Maven ή IDE με maven oπως το Εclipse
https://maven.apache.org/

Mongo DataBase
https://www.mongodb.org/downloads#production

SourceTree
https://www.sourcetreeapp.com/


Mongo Database Install
τρέξετε το αρχείο .msi > πατήστε το Complete install
δημιουργήστε ενα φάκελο C:/data/db
ανοίξτε ένα command line ("window key" + R)  cmd
cd C:\\Program Files\\MongoDB\\Server\\3.2\\bin
mongod

Για το project

Κάνετε clone από το git στο Source Tree

Import στο ΙDE
File > Import... > Maven > Existing Maven Projects

Στο source Tree κάντε ingore τα αρχεία και φακέλους με . μπροστά

Εκτέλεση στο Εclipse
Επιλέξτε το project > δεξί κλικ > Run as > Maven Build...
Στο goals spring-boot:run

Εκτέλεση σε Maven Command Line
mvn spring-boot:run
