package twisk.outils;

/**/

public class FabriqueIdentifiant {
        private static final FabriqueIdentifiant instance = new FabriqueIdentifiant();
        private int noAct;
        private int noGui;
        private int noEtapePointDC;
        private int noArcs;

    /**
     * Constructeur d'une InstanceUnique qui fabrique des Identifiants
     */
    private FabriqueIdentifiant() {
        noAct = 0;
        noEtapePointDC = 0;
        noArcs = 0;
        noGui = 0;
    }

    /**
     * retourne l'instance du Singleton
     * @return instance
     */
    public static FabriqueIdentifiant getInstance(){
            return instance;
    }

    /**
     * retourne un identifiant unique
     * @return noEtape
     */
    public String getIdentifiantActivite() {
        noAct++;
        return "Activite" + (noAct - 1);
    }

    public String getIdentifiantGuichet() {
        noGui++;
        return "Guichet" + (noGui -1);
    }

    /**
     * retourne un identifiant de PointDeControle unique
     * @return noEtapePointDC
     */
    public String getPointDeControleIdentifiant(){
            noEtapePointDC++;
            return "PointDC" + (noEtapePointDC - 1);
    }

    /**
     * retourne un identifiant d'ArcIG unique
     * @return noArcs
     */
    public String getIdendifiantArcs(){
            noArcs++;
            return String.valueOf(noArcs -1);
    }
}
