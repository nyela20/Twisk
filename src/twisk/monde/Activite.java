package twisk.monde;

public class Activite extends Etape{

    private final int temps;
    private final int ecartTemps;

   public Activite(String nom) {
        super(nom);
        this.temps = 15;
        this.ecartTemps = 10;
    }

   public Activite(String nom,int temps,int ecartTemps) {
        super(nom);
        this.ecartTemps = ecartTemps;
        this.temps = temps;
    }
    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public boolean estUnGuichet() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
