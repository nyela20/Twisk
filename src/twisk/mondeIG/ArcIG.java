package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;

import java.util.Objects;

public class ArcIG {
    private final PointDeControleIG pointDeDepart;
    private final PointDeControleIG pointDarrive;
    private final EtapeIG etapeArrive;
    private final EtapeIG etapeDebut;
    private boolean estSelectionne;
    private final String identifiant =  FabriqueIdentifiant.getInstance().getIdendifiantArcs();

    /**
     * Constructeur d'un ArcIG à partie de deux
     * PointsDeControle
     * @param pdcDepart le pointDeControle de départ
     * @param pdcArrive le pointDeControle d'arrivée
     */
    public ArcIG(PointDeControleIG pdcDepart, PointDeControleIG pdcArrive) {
        pointDeDepart = pdcDepart;
        pointDarrive = pdcArrive;
        etapeArrive = pdcArrive.getEtapeIG();
        etapeDebut = pdcDepart.getEtapeIG();
        estSelectionne = false;
    }

    /**
     * la fonction verifie si un arc et une Etape son reliée
     * @param etape l'Etape de test
     * @return un booléen
     */
    public boolean estRelie(EtapeIG etape) {
        return aCommeArrive(etape) || aCommeDebut(etape);
    }

    /**
     * la fonction retourne l'Etape d'arrive d'un ArcIG
     * @param etapeIG l'Etape de test
     * @return un booléen
     */
    public boolean aCommeArrive(EtapeIG etapeIG) {
        return etapeIG.getIdentifiant().equals(etapeArrive.getIdentifiant());
    }

    /**
     * la fonction retournr l'Etape de départ d'un ArcIG
     * @param etapeIG l'Etape de test
     * @return un booléen
     */
    public boolean aCommeDebut(EtapeIG etapeIG) {
        return etapeIG.getIdentifiant().equals(etapeDebut.getIdentifiant());
    }

    /**
     * la fonction retourne le pointDeControle de départ du e l'Arc
     * @return  le pointDeControle de départ de l'Arc
     */
    public PointDeControleIG getpointDeDepart() {
        return pointDeDepart;
    }

    /**
     * la fonction retourne le pointDeControle d'arrivée de l'Arc
     * @return  le pointDeControle d'arrivée de l'Arc
     */
    public PointDeControleIG getPointDarrive() {
        return pointDarrive;
    }

    /**
     * la fonction retourne l'Etape d'arrivée de l'Arc
     * @return l'Etape d'arrivée de l'Arc
     */
    public EtapeIG getEtapeArrive() {
        return etapeArrive;
    }

    /**
     * la fonction retourne l'Etape de départ de l'Arc
     * @return l'Etape de départ de l'Arc
     */
    public EtapeIG getEtapeDebut() {
        return etapeDebut;
    }

    /**
     * la fonction modifie la valeur booléenne
     * du champs estSelectionne en fonction
     * de la valeur du paramètre de la fonction
     * @param set true pour estSelectionne, sinon non-selectionne
     */
    public void setEstSelectionne(boolean set) {
        estSelectionne = set;
    }

    /**
     * La fonction retourne vrai si el'ArcIG est séléctionné sinon faux
     * @return vrai si el'ArcIG est séléctionné sinon faux
     */
    public boolean estSelectionne() {
        return estSelectionne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArcIG)) return false;
        ArcIG arcIG = (ArcIG) o;
        return estSelectionne == arcIG.estSelectionne && Objects.equals(pointDeDepart, arcIG.pointDeDepart) && Objects.equals(getPointDarrive(), arcIG.getPointDarrive()) && Objects.equals(getEtapeArrive(), arcIG.getEtapeArrive()) && Objects.equals(getEtapeDebut(), arcIG.getEtapeDebut()) && Objects.equals(identifiant, arcIG.identifiant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointDeDepart, getPointDarrive(), getEtapeArrive(), getEtapeDebut(), estSelectionne, identifiant);
    }
}
