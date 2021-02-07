package twisk.monde;


import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {

    private final String nom;
    private final GestionnaireSuccesseurs gestionSucc;

    public Etape(String nom) {
        this.nom = nom;
        this.gestionSucc = new GestionnaireSuccesseurs();
    }

    public void ajouterSuccesseur(Etape... etapes) {
        for (Etape e : etapes) {
            if (this.estUnGuichet() && e.estUneActivite() && gestionSucc.nbSEtapes() < 1) {
                gestionSucc.ajouter(e);
            }
            if (this.estUneActivite()) {
                gestionSucc.ajouter(e);
            }
        }
    }

    public int nbSuccesseurs() {
        return gestionSucc.nbSEtapes();
    }

    public abstract boolean estUneActivite();

    public abstract boolean estUnGuichet();

    @Override
    public String toString() {
        return this.nom;
    }

    @Override
    public Iterator<Etape> iterator() {
        return gestionSucc.iterator();
    }
}
