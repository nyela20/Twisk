package twisk.monde;


public class Guichet extends Etape{

    private int nbJetons;

    public Guichet(String nom) {
        super(nom);
        this.nbJetons = 3;
    }

    public Guichet(String nom, int nbJetons){
        super(nom);
        this.nbJetons = nbJetons;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    @Override
    public boolean estUneActivite() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
