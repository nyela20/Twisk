package twisk;

import twisk.Exceptions.ExceptionObjetNonTrouve;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.simulation.Simulation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClientTwisk {

    public static void start(Monde monde,int nbclients) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
        Class<?>loadClass = classLoaderPerso.loadClass("twisk.simulation.Simulation");
        Constructor co = loadClass.getConstructor();
        Object simulation = co.newInstance();
        assert(simulation.getClass().equals(Simulation.class)) : "erreur newInstance() Simulation";
        Method m1 = simulation.getClass().getMethod("setNbClients",int.class);
        m1.invoke(simulation,nbclients);
        Method m2 = simulation.getClass().getMethod("simuler",Monde.class);
        m2.invoke(simulation,monde);
        classLoaderPerso.finalize();
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {


        /*----monde1---*/
        Monde monde = new Monde();

        Etape guichet_lion = new Guichet("Guichet_lion", 4);
        Etape guichet_girafe = new Guichet("Guichet_girafe", 4);
        Etape plaine_girafe = new ActiviteRestreinte("plaine_girafe", 2, 1);
        Etape guichet_zebre = new Guichet("Guichet_zebre", 4);
        Etape fast_food = new Activite("fast_food", 2, 1);
        Etape plaine_zebre = new ActiviteRestreinte("plaine_zebre", 2, 1);
        Etape magasin_souvenir = new Activite("magasin_souvenir", 2, 1);
        Etape cage_lion = new ActiviteRestreinte("Cage_lion", 5, 2);

        monde.ajouter(fast_food, guichet_lion, plaine_girafe,cage_lion, guichet_girafe, guichet_zebre, plaine_zebre, magasin_souvenir);
        monde.aCommeEntree(fast_food);
        fast_food.ajouterSuccesseur(guichet_lion);
        guichet_lion.ajouterSuccesseur(cage_lion);
        cage_lion.ajouterSuccesseur(guichet_girafe);
        guichet_girafe.ajouterSuccesseur(plaine_girafe);
        plaine_girafe.ajouterSuccesseur(guichet_zebre);
        guichet_zebre.ajouterSuccesseur(plaine_zebre);
        plaine_zebre.ajouterSuccesseur(magasin_souvenir);
        monde.aCommeSortie(magasin_souvenir);
        start(monde,5);


        /*----monde2---*/
        Monde monde2 = new Monde();

        Activite zoo = new Activite("zoo", 2, 1);
        Etape Guichettob = new Guichet("guichet_tob", 4);
        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);
        Etape GuichetPiscine = new Guichet("guichet_piscine", 3);
        Activite piscine = new ActiviteRestreinte("piscine", 5,2 );

        monde2.ajouter(tob, zoo, Guichettob, GuichetPiscine, piscine);
        zoo.ajouterSuccesseur(Guichettob);
        Guichettob.ajouterSuccesseur(tob);
        tob.ajouterSuccesseur(GuichetPiscine);
        GuichetPiscine.ajouterSuccesseur(piscine);
        monde2.aCommeEntree(zoo);
        monde2.aCommeSortie(piscine);
        start(monde2,15);

    }
}

