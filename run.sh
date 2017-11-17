cd project
mvn package
cd ../
java -cp build/obfousfous.jar fousfous.ServeurJeu 1234 1 &
sleep 2
java -cp build/obfousfous.jar fousfous.ClientJeu fousfous.JoueurAleatoire localhost 1234 &
java -cp project/target/fousfous-1.0-SNAPSHOT.jar fousfous.ClientJeu com.polytech.app5.fousfous.Controller localhost 1234
