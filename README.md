# advse-project

Θα χρειάζεται
Java JdK
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Eclipse for java EE
https://eclipse.org/downloads/

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

Import στο Eclipse
File > Import... > Maven > Existing Maven Projects

Στο source Tree κάντε ingore τα αρχεία και φακέλους με . μπροστά

Βάλτε το jdk
Επιλέξτε το project > δεξί κλικ > Properties > Java Build Path > Libraries

Επιλέξτε to JRE System > Edit > Alternate JRE > Installed JREs > Add > Standard VM > JRE HOME: C:\\Program Files\\Java\\jdk1.8.0_65 > Finish

Επιλέξτε to jdk1.8.0 και πατήστε ΟΚ , μεάα πατήστε Finish . Τέλος πατήστε ΟΚ

Run in Eclipse
Επιλέξτε το project > δεξί κλικ > Run as > Maven build...

Goals: spring-boot:run
Πατήστε Apply και μετά Run

Δοκιμή
Ανοίξτε browser localhost:8080/greeting
