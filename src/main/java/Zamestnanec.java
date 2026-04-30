
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pavel
 */
public abstract class Zamestnanec implements Comparable<Zamestnanec>{
    static int noOfEmployees = 0;
    protected int ID = 0;
    protected String name = "";
    protected String surname = "";
    protected int birthYear = 0;
    
    protected Map<Zamestnanec, Float> relations = new HashMap<>();
    
    public Zamestnanec(String jmeno, String prijmeni, int narozeniny){
        ID = noOfEmployees;
        noOfEmployees++;
        name = jmeno;
        surname = prijmeni;
        birthYear = narozeniny;
    }
    
    public abstract boolean doYourJob();                                         //spustit dovednost daného zaměstnance
    public abstract boolean deleteRelations();                                   //odstranit všechny vyzby na ostatní zaměstnance
    public abstract boolean addRelation(Zamestnanec coleague, float level);      //přidání spolupráce, ID druhého zaměstnance, úroveň spolupáce (náleží do <0, 1>)
    public abstract int getNumberOfRelations();                                  //vrací počet spoluprací
    public abstract float getMostUsedRelation();                                 //vrací nejčastěji používanou spolupráci
    public abstract boolean getGroup();                                          //vrací hodnotu podle skupiny pracovníka: True: Datový analytik, False: bezpečnostní specialista
    public abstract String getName();                                            //vlací jméno zaměstnance
    public abstract String getSurname();                                         //vrací příjmení zaměstnance
    public abstract int getID();                                                 //vrací ID zaměstnance
    public abstract int getBirthDate();                                          //vrací datum narození zaměstnance
    public abstract int compareTo(Zamestnanec z);                                //porovnávací metoda
    public abstract boolean equals(Object obj);
    public abstract int hashCode();                                              //generování hashe, vrací hash ID: return Integer.hashCode(cisloID)
    public abstract String toString();                                           //převede objekt na string
    
}

/* Zaměstnanec skladuje své spolupracovníky ve struktuře typu HashMap (nebo něčem podobném slovníku v pythonu). Jako klíč bude pointer na zaměstnance (kolegu) a jako hodnota bude úroveň spolupráce (implement Comparable a metodu public int compareTo(Zamestnanec o), která vrací 0 pro stejné, - pro menší a + pro větší) */ 