package twisk.outils;

/**/

public class FabriqueIdentifiant {
        private static final FabriqueIdentifiant instance = new FabriqueIdentifiant();
        private int noEtape;
        private int noEtapePointDC;
        private int noArcs;

    /**
     * Constructeur d'une InstanceUnique qui fabrique des Identifiants
     */
    private FabriqueIdentifiant(){
            this.noEtape = 0;this.noEtapePointDC = 0;
            this.noArcs = 0;
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
    public String getIdentifiant(){
            this.noEtape++ ;
            return "Activite" + (this.noEtape - 1);
        }

    /**
     * retourne un identifiant de PointDeControle unique
     * @return noEtapePointDC
     */
    public String getPointDeControleIdentifiant(){
            this.noEtapePointDC++;
            return "PointDC" + (this.noEtapePointDC - 1);
        }

    /**
     * retourne un identifiant d'ArcIG unique
     * @return noArcs
     */
    public String getIdendifiantArcs(){
            this.noArcs++;
            return String.valueOf(this.noArcs -1);
        }
}
