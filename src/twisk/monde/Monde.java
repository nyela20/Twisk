package twisk.monde;

import twisk.exceptionstwisk.ExceptionObjetNonTrouve;
import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public class Monde implements Iterable<Etape> {

    private final Etape sasSortie;
    private final Etape sasEntree;
    private final GestionnaireEtapes gestioEtapes;


    /**
     * Constructeur d'un Monde
     */
    public Monde(){
        FabriqueNumero.getInstance().reset();
        this.sasEntree = new SasEntree();
        this.gestioEtapes = new GestionnaireEtapes();
        this.sasSortie = new SasSortie();
    }

    /**
     * Défini les entrées du monde
     *
     * @param etapes les entrées du monde
     */
    public void aCommeEntree(Etape... etapes) {
        sasEntree.ajouterSuccesseur(etapes);
    }

    /**
     * Défini les sorties du monde
     *
     * @param etapes les sortie
     */
    public void aCommeSortie(Etape... etapes) {
        for (Etape e : etapes) {
            assert (e.estUneActivite() || e.estUneActiviteRestreinte()) : "un guichet comme sortie est impossible.";
            e.ajouterSuccesseur(sasSortie);
        }
    }

    /**
     * retourne le nombre de jetons de l'Etape
     * dont le nom est donnée en paramètres
     * @param nom le nom de l'Etape
     * @return lenombre de jetons
     * @throws ExceptionObjetNonTrouve lève un exception si l'Etape non trouvé
     */
    public int getnbJetonsNiemeEtape(String nom) throws ExceptionObjetNonTrouve {
       for(Etape etape : this){
           if(etape.getNom().equals(nom)){
               assert(etape.estUnGuichet()) : "erreur l'étape n'est pas un guichet";
               return ((Guichet) etape).getNombreDeJetons();
           }
       }
       throw new ExceptionObjetNonTrouve("guichet non trouvé");
    }

    public String getNomNiemeEtape(int ieme) {
        if (ieme == 0) { return sasEntree.getNom(); }
        if (ieme == 1) { return sasSortie.getNom(); }
        for (int i = 2; i < nbEtapes() ; i++) {
            if (gestioEtapes.getIemeEtape(i-2).getNumeroEtape() == ieme) {
                return gestioEtapes.getIemeEtape(i-2).getNom();
            }
        }
        return null;
    }

    /**
     * retourne l'Etape ayant le
     * même nom que celui donné en paramètre
     * @param nom le nom de l'Etape
     * @return l'Etape
     */
    public Etape getEtape(String nom) {
        if(nom.equals(sasEntree.getNom())){
            return sasEntree;
        }
        if(nom.equals(sasSortie.getNom())){
            return  sasSortie;
        }
        for(Etape etape : this){
            if(etape.getNom().equals(nom)){
                return etape;
            }
        }
        return null;
      //  throw new ExceptionObjetNonTrouve("Aucun etape trouvé portant ce nom");
    }

    /**
     * Ajoute successivement des Etapes au Monde
     *
     * @param etapes les Etape  à ajoutés
     */
    public void ajouter(Etape... etapes) {
        gestioEtapes.ajouter(etapes);
    }

    /**
     * retourne le nombre d'Etape dans le monde
     *
     * @return le nb d'Etape dans le monde
     */
    public int nbEtapes(){
        return gestioEtapes.nbEtapes() + 2;
    }

    /**
     * retourne le nombre de Guichet dans le monde
     *
     * @return le nb de Guichet dans le monde
     */
    public int nbGuichet() {
        return gestioEtapes.nbGuichet();
    }

    /**
     * le monde sous code C
     *
     * @return code C
     */
    public String toC() {
        StringBuilder affichage = new StringBuilder();

        //----------------------------------EN TETE CLIENT.C

        //Ecriture des includes
        affichage.append("#include<stdio.h>\n");
        affichage.append("#include<stdlib.h>\n");
        affichage.append("#include\"def.h\"\n\n");

        //ecriture des defines des etapes
        affichage.append("#define ").append(sasEntree.getNom()).append(" ").append(sasEntree.getNumeroEtape()).append("\n\n");
        for (Etape e : gestioEtapes) {
            affichage.append("#define ").append(e.getNom()).append(" ").append(e.getNumeroEtape()).append("\n");
        }
        affichage.append("\n#define ").append(sasSortie.getNom()).append(" ").append(sasSortie.getNumeroEtape()).append("\n\n");

        //Ecritures des defines des numero des semamphores
        System.out.println();
        for (Etape e : gestioEtapes) {
            if (e.estUnGuichet()) {
                affichage.append("#define ");
                affichage.append("num_sem");
                affichage.append((e.getNom())).append(" ");
                affichage.append(((Guichet) e).getNoSemaphore()).append("\n");
            }
        }



        //------------------------------------------------------FONCTION SIMULER

        //Ecriture de la fonction Simuler
        affichage.append("void simulation(int ids){\n");

        affichage.append(sasEntree.toC());
        //sasEntree.toC();
        affichage.append("}");
        //Fin
        return affichage.toString();
    }

    /**
     * Affichage du monde
     *
     * @return affichge du monde en string
     */
    @Override
    public String toString() {
        return sasEntree + "\n\n" +
                gestioEtapes + "\n" +
                sasSortie;
    }

    /**
     * retourne l'itérateur de GestionEtapes
     * @return Iterator<Etape>
     */
    @Override
    public Iterator<Etape> iterator() {
        return gestioEtapes.iterator();
    }

    /**
     * la fonction retourne le numéroD'Etape du sasDeSortie
     * utile pour atteindre la condition de fin
     * de la boucle while dans la classe Simulation
     */
    public int getSasSortieNumeroEtape() {
        return this.sasSortie.getNumeroEtape();
    }
}
