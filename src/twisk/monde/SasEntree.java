package twisk.monde;

public class SasEntree extends Activite {

    /**
     * Constructeur des SasEntree
     */
    public SasEntree() {
        super("Sas_Entree", 3, 1);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toC() {

        Etape succ = iterator().next();
        return "entrer(" + getNom() + ");\n" +
                "delai(" + getTemps() + "," + getEcartTemps() + ");\n" +
                "transfert(" + getNom() + "," + succ.getNom() + ");" + succ.toC();
    }

    @Override
    public String getNom(){
        return super.getNom();
    }


}
