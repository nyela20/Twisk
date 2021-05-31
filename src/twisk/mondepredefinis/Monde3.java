package twisk.mondepredefinis;

import twisk.exceptionstwiskIG.ExceptionArcIG;
import twisk.monde.Activite;
import twisk.monde.Guichet;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;

public class Monde3 {

    public Monde3(MondeIG mondeIG){

        /*creation etape*/
        ActiviteIG football = (ActiviteIG) mondeIG.ajouter("Activite");
        GuichetIG guichetVolley = (GuichetIG) mondeIG.ajouter("Guichet");
        GuichetIG guichetRugby = (GuichetIG) mondeIG.ajouter("Guichet");
        ActiviteIG volley = (ActiviteIG) mondeIG.ajouter("Activite");
        ActiviteIG rugby = (ActiviteIG) mondeIG.ajouter("Activite");
        ActiviteIG course = (ActiviteIG) mondeIG.ajouter("Activite");
        ActiviteIG vip = (ActiviteIG) mondeIG.ajouter("Activite");
        GuichetIG guichetVolleyVIP = (GuichetIG) mondeIG.ajouter("Guichet");
        GuichetIG guichetRugbyVIP = (GuichetIG) mondeIG.ajouter("Guichet");

        /*renommer etape*/
        football.renommer("football");
        guichetVolley.renommer("guichetVolley");
        guichetRugby.renommer("guichetRugby");
        volley.renommer("volley");
        rugby.renommer("rugby");
        course.renommer("course");
        vip.renommer("vip");
        guichetVolleyVIP.renommer("guichetVolleyVIP");
        guichetRugbyVIP.renommer("guichetRugbyVIP");

        /*deplacer etape*/
        football.deplacerEtape(400,25);
        guichetVolley.deplacerEtape(200,175);
        guichetRugby.deplacerEtape(600,175);
        volley.deplacerEtape(25,285);
        course.deplacerEtape(400,285);
        rugby.deplacerEtape(800,285);
        guichetRugbyVIP.deplacerEtape(200,435);
        guichetVolleyVIP.deplacerEtape(600,435);
        vip.deplacerEtape(400,545);

        /*ajouter arcs*/
        try {
            mondeIG.ajouter(football.getPointDeControle("bas"),guichetVolley.getPointDeControle("droite"));
            mondeIG.ajouter(football.getPointDeControle("bas"),guichetRugby.getPointDeControle("gauche"));
            mondeIG.ajouter(guichetVolley.getPointDeControle("gauche"), volley.getPointDeControle("haut"));
            mondeIG.ajouter(guichetRugby.getPointDeControle("droite"),rugby.getPointDeControle("haut"));
            mondeIG.ajouter(volley.getPointDeControle("droite"), course.getPointDeControle("gauche"));
            mondeIG.ajouter(rugby.getPointDeControle("gauche"), course.getPointDeControle("droite"));
            mondeIG.ajouter(vip.getPointDeControle("haut"),guichetRugbyVIP.getPointDeControle("droite"));
            mondeIG.ajouter(vip.getPointDeControle("haut"), guichetVolleyVIP.getPointDeControle("gauche"));
            mondeIG.ajouter(guichetRugbyVIP.getPointDeControle("gauche"), volley.getPointDeControle("bas"));
            mondeIG.ajouter(guichetVolleyVIP.getPointDeControle("droite"), rugby.getPointDeControle("bas"));
        } catch (ExceptionArcIG exceptionArcIG) {
            exceptionArcIG.printStackTrace();
        }

        /*ajouter entrees/sorties*/
        mondeIG.setEstSelectionne(football,true);
        mondeIG.setEstSelectionne(vip,true);
        mondeIG.assignerEntree();
        mondeIG.setEstSelectionne(course,true);
        mondeIG.assignerSortie();
    }
}
