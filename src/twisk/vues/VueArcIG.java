package twisk.vues;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import twisk.ecouteurs.EcouteurArcs;
import twisk.monde.ArcIG;
import twisk.monde.MondeIG;

public class VueArcIG extends Pane implements Observateur {
    private final int identifiantStyle;

    /**
     * Constructeur d'une VueArcIG
     * @param mondeIG le monde
     * @param a l'ArcIG
     * @param style l'identifiant du style
     */
    public VueArcIG(MondeIG mondeIG, ArcIG a, int style) {
        identifiantStyle = style;
        Line ligne = dessinerUneLigne(a);
        Polygon tete = dessinerUnTriangle(ligne,a);
        this.getChildren().addAll(ligne,tete);
        this.setPickOnBounds(false);
        this.setOnMouseClicked(new EcouteurArcs(mondeIG,a));
    }

    /**
     * La fonction retourne une instance de Line qui est un composant
     * de l'arc
     * @param arc l'arc
     * @return Line
     */
    public Line dessinerUneLigne(ArcIG arc) {
        Line ligne = new Line();
        ligne.setStartX(arc.getpointDeDepart().getx());
        ligne.setStartY(arc.getpointDeDepart().gety());
        ligne.setEndX(arc.getPointDarrive().getx());
        ligne.setEndY(arc.getPointDarrive().gety());
        ligne.setStrokeWidth(5);
        if(arc.estSelectionne()) {
            ligne.setId("ligne_selected");
        }else{
            ligne.setId("ligne"+ identifiantStyle +"_not_selected");
        }
        return ligne;
    }

    /**
     * La fonction retourne un Polygon qui est composant de l'arc
     * @param ligne le ligne composant de l'arcIG
     * @param arc l'arc
     * @return Polygon
     */
    public Polygon dessinerUnTriangle(Line ligne,ArcIG arc) {
        Polygon tete = new Polygon();
        Point2D pS = new Point2D(ligne.getStartX(), ligne.getStartY());
        Point2D pE = new Point2D(ligne.getEndX(), ligne.getEndY());
        tete.getPoints().setAll(new Double[]{
                pE.getX(), pE.getY(),
                pE.getX() + 10, pE.getY() + 20,
                pE.getX() - 10, pE.getY() + 20,
                pE.getX(), pE.getY(),
        });
        double coeff = pE.distance(new Point2D(pS.getX(), pE.getY())) / pS.distance(pE);
        if (ligne.getStartX() < ligne.getEndX()) {
            if (ligne.getStartY() > ligne.getEndY()) {
                tete.setRotate(Math.toDegrees(Math.asin(coeff)));
            } else {
                tete.setRotate(180 - Math.toDegrees(Math.asin(coeff)));
            }
        } else {
            if (ligne.getStartY() > ligne.getEndY()) {
                tete.setRotate(-Math.toDegrees(Math.asin(coeff)));
            } else {
                tete.setRotate(180 + Math.toDegrees(Math.asin(coeff)));
            }
        }
        tete.relocate(pE.getX() - 10, pE.getY() - 10);
        if (arc.estSelectionne()) {
            tete.setId("polygonfleche_selected");
        }else{
            tete.setId("polygonfleche"+ identifiantStyle +"_not_selected");
        }
        return tete;
    }


    @Override
    public void reagir() { }

}
