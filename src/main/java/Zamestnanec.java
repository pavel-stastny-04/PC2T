/**
 *
 * @author pavel
 */
public interface Zamestnanec {
    public boolean doYourJob();                                         //spustit dovednost daného zaměstnance
    public boolean deleteRelations();                                   //odstranit všechny vyzby na ostatní zaměstnance
    public boolean addRelation(Zamestnanec coleague, float level);      //přidání spolupráce, ID druhého zaměstnance, úroveň spolupáce (náleží do <0, 1>)
    public int getNumberOfRelations();                                  //vrací počet spoluprací
    public float getMostUsedRelation();                                 //vrací nejčastěji používanou spolupráci
    public boolean getGroup();                                          //vrací hodnotu podle skupiny pracovníka: True: Datový analytik, False: bezpečnostní specialista
    public String getName();                                            //vlací jméno zaměstnance
    public String getSurname();                                         //vrací příjmení zaměstnance
    public int getID();                                                 //vrací ID zaměstnance
    public int getBirthDate();                                          //vrací datum narození zaměstnance
    
}

/* Zaměstnanec skladu je své spolupracovníky ve struktuře typu HashMap (nebo něčem podobném slovníku v pythonu). Jako klíč bude pointer na zaměstnance (kolegu) a jako hodnota bude úroveň spolupráce