package twisk.outils;

import java.io.*;

public class KitC {


    public KitC() {
    }

    public void creerFichier(String codeC) {
        FileWriter flot;
        BufferedWriter flotFiltre;

        File dossier = new File("/tmp/twisk");
        dossier.mkdir();
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
}
