package twisk;

import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.simulation.Simulation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClientTwisk{

    private static void start(Monde monde,int nbclients) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
        ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
        Class<?>loadClass = classLoaderPerso.loadClass("twisk.simulation.Simulation");
        Constructor<?> co = loadClass.getConstructor();
        Object simulation = co.newInstance();
        assert(simulation.getClass().equals(Simulation.class)) : "erreur newInstance() Simulation";
        Method m1 = simulation.getClass().getMethod("setNbClients",int.class);
        m1.invoke(simulation,nbclients);
        Method m2 = simulation.getClass().getMethod("simuler",Monde.class);
        m2.invoke(simulation, monde);
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {




        /*---monde0-(biffurcation)--*/

        Monde monde = new Monde();
        Etape lolo = new Activite("lolo", 4, 2);
        Etape dodo = new Activite("dodo", 6,2 );
        Etape popo = new Activite("popo", 6, 2);
        Etape koko = new Activite("koko", 6, 2);
        monde.ajouter(lolo,dodo,popo,koko);
        dodo.ajouterSuccesseur(koko);
        monde.aCommeSortie(lolo);
        monde.aCommeSortie(koko);
        popo.ajouterSuccesseur(lolo);
        popo.ajouterSuccesseur(dodo);
        monde.aCommeEntree(dodo);
        monde.aCommeEntree(popo);
        start(monde,15);



        /*----monde1--(biffurcation)-*/
        Monde monde1 = new Monde();

        Etape guichet_lion = new Guichet("Guichet_lion", 4);
        Etape guichet_girafe = new Guichet("Guichet_girafe", 4);
        Etape plaine_girafe = new ActiviteRestreinte("plaine_girafe", 2, 1);
        Etape guichet_zebre = new Guichet("Guichet_zebre", 4);
        Etape fast_food = new Activite("fast_food", 2, 1);
        Etape plaine_zebre = new ActiviteRestreinte("plaine_zebre", 2, 1);
        Etape magasin_souvenir = new Activite("magasin_souvenir", 2, 1);
        Etape cage_lion = new ActiviteRestreinte("Cage_lion", 5, 2);

        monde1.ajouter(guichet_girafe,guichet_lion,guichet_zebre,plaine_girafe,fast_food,plaine_zebre,magasin_souvenir,cage_lion);
        monde1.aCommeEntree(fast_food);
        fast_food.ajouterSuccesseur(guichet_girafe);
        guichet_girafe.ajouterSuccesseur(plaine_girafe);
        fast_food.ajouterSuccesseur(guichet_lion);
        guichet_lion.ajouterSuccesseur(cage_lion);
        fast_food.ajouterSuccesseur(guichet_zebre);
        guichet_zebre.ajouterSuccesseur(plaine_zebre);
        fast_food.ajouterSuccesseur(magasin_souvenir);
        monde1.aCommeSortie(magasin_souvenir);
        monde1.aCommeSortie(plaine_girafe);
        monde1.aCommeSortie(plaine_zebre);
        monde1.aCommeSortie(cage_lion);
        start(monde1,10);

        /*----monde2---*/
        Monde monde2 = new Monde();

        Activite zoo = new Activite("zoo", 2, 1);
        Etape Guichettob = new Guichet("guichet_tob", 4);
        Activite toboggan = new ActiviteRestreinte("toboggan", 2, 1);
        Etape GuichetPiscine = new Guichet("guichet_piscine", 3);
        Activite piscineActivite = new ActiviteRestreinte("piscine", 5,2 );

        monde2.ajouter(toboggan, zoo, Guichettob, GuichetPiscine, piscineActivite);
        zoo.ajouterSuccesseur(Guichettob);
        Guichettob.ajouterSuccesseur(toboggan);
        toboggan.ajouterSuccesseur(GuichetPiscine);
        GuichetPiscine.ajouterSuccesseur(piscineActivite);
        monde2.aCommeEntree(zoo);
        monde2.aCommeSortie(piscineActivite);
        start(monde2,15);

        /*----monde3---*/
        Monde monde3 = new Monde();

        Etape guichet_lion2 = new Guichet("Guichet_lion2", 4);
        Etape guichet_girafe2 = new Guichet("Guichet_girafe2", 4);
        Etape plaine_girafe2 = new ActiviteRestreinte("plaine_girafe2", 2, 1);
        Etape guichet_zebre2 = new Guichet("Guichet_zebre2", 4);
        Etape fast_food2 = new Activite("fast_food2", 2, 1);
        Etape plaine_zebre2 = new ActiviteRestreinte("plaine_zebre2", 2, 1);
        Etape magasin_souvenir2 = new Activite("magasin_souvenir2", 2, 1);
        Etape cage_lion2 = new ActiviteRestreinte("Cage_lion2", 5, 2);

        monde3.ajouter(fast_food2, guichet_lion2, plaine_girafe2,cage_lion2, guichet_girafe2, guichet_zebre2, plaine_zebre2, magasin_souvenir2);
        monde3.aCommeEntree(fast_food2);
        fast_food2.ajouterSuccesseur(guichet_lion2);
        guichet_lion2.ajouterSuccesseur(cage_lion2);
        cage_lion2.ajouterSuccesseur(guichet_girafe2);
        guichet_girafe2.ajouterSuccesseur(plaine_girafe2);
        plaine_girafe2.ajouterSuccesseur(guichet_zebre2);
        guichet_zebre2.ajouterSuccesseur(plaine_zebre2);
        plaine_zebre2.ajouterSuccesseur(magasin_souvenir2);
        monde3.aCommeSortie(magasin_souvenir2);
        start(monde3,25);
    }
}

