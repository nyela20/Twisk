package twisk.outils;

import org.junit.platform.engine.support.descriptor.FileSystemSource;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class KitC {


    public KitC() {
    }

    public void creerFichier(String codeC) {
        FileWriter flot;
        BufferedWriter flotFiltre;

        //creation du dossier et recopier des fichers
        File dossier = new File("/tmp/twisk");
        dossier.mkdir();


      /*  try {
            if (dossier.exists()) {
                String dest1 = "/tmp/twisk/def.h";
                String src1 = getClass().getResource("/def.h").toExternalForm();
                String src2 = getClass().getResource("/programmeC.o").toExternalForm();
                Files.copy(Paths.get(src1), Paths.get(dest1));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        File chemin = new File("/tmp/twisk/client.c");
        try {
            flot = new FileWriter(chemin);
            flotFiltre = new BufferedWriter(flot);
            flotFiltre.write(codeC);
            flotFiltre.close();
        } catch (IOException e) {
            System.out.println("Impossible d'ecrire le code C");
        }
    }

    public void compiler() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process p = runtime.exec("gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o");

            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            // à reprendre éventuellement et à adapter à votre code
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne;
            while ((ligne = output.readLine()) != null) {
                System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void construireLaLibrairie() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process p = runtime.exec("gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/client.o -o /tmp/twisk/libTwisk.so");
            p.waitFor();

            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            // à reprendre éventuellement et à adapter à votre code
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne;
            while ((ligne = output.readLine()) != null) {
                System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
