package twisk.monde;

public class SasEntree extends Activite {

    /**
     * Constructeur des SasEntree
     */
    public SasEntree() {
        super("SasEntrée", 0, 0);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toC() {
        return "entrer(" + getNom() + ")";
    }
}
