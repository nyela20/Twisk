package twisk.monde;

import java.util.Iterator;

public class SasEntree extends Activite {
    private final Monde monde;
    /**
     * Constructeur des SasEntree
     */
    public SasEntree(Monde monde) {
        super("Sas_Entree", 3, 1);
        this.monde = monde;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toC() {
        StringBuilder affichage = new StringBuilder();
        if (nbSuccesseurs() == 1) {
            Etape succ = iterator().next();
            if(monde.suitLoiUniforme()) {
                affichage.append("entrer(").append(getNom()).append(");\n").append("delai(").append(getTemps()).append(",").append(getEcartTemps()).append(");\n").append("transfert(").append(getNom()).append(",").append(succ.getNom()).append(");").append(succ.toC());
            }
            if(monde.suitLoiNormale()) {
                affichage.append("entrer(").append(getNom()).append(");\n").append("delaiGauss(").append(getTemps()).append(",").append(getEcartTemps()).append(");\n").append("transfert(").append(getNom()).append(",").append(succ.getNom()).append(");").append(succ.toC());
            }
            if(monde.suisLoiPoisson()){
                affichage.append("entrer(").append(getNom()).append(");\n").append("delaiExponentiel(").append(getTemps()).append(");\n").append("transfert(").append(getNom()).append(",").append(succ.getNom()).append(");").append(succ.toC());
            }
        }
        if (nbSuccesseurs() > 1) {
            Iterator<Etape> iterator = this.iterator();

            affichage.append("\nint sasE = (int)((rand()/(float) RAND_MAX) *").append(nbSuccesseurs()).append(");\n");
            affichage.append("switch(sasE){\n");
            for (int i = 0; i < nbSuccesseurs(); i++) {
                Etape succ = iterator.next();
                affichage.append("case ").append(i).append(":\n");
                if (monde.suitLoiUniforme()) {
                    affichage.append("entrer(").append(getNom()).append(");\n").append("delai(").append(getTemps()).append(",").append(getEcartTemps()).append(");\n").append("transfert(").append(getNom()).append(",").append(succ.getNom()).append(");").append(succ.toC()).append("break;\n");
                }
                if(monde.suitLoiNormale()) {
                    affichage.append("entrer(").append(getNom()).append(");\n").append("delaiGauss(").append(getTemps()).append(",").append(getEcartTemps()).append(");\n").append("transfert(").append(getNom()).append(",").append(succ.getNom()).append(");").append(succ.toC());
                }
                if(monde.suisLoiPoisson()){
                    affichage.append("entrer(").append(getNom()).append(");\n").append("delaiExponentiel(").append(getTemps()).append(");\n").append("transfert(").append(getNom()).append(",").append(succ.getNom()).append(");").append(succ.toC());
                }
            }
            affichage.append("}\n");
        }
        return affichage.toString();
    }

    @Override
    public String getNom(){
        return super.getNom();
    }


}
