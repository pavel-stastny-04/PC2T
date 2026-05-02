
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pavel
 */
public class DataAnalytic extends Zamestnanec{  
    
    public DataAnalytic(String jmeno, String prijmeni, int narozeniny, int ID){
        super(jmeno, prijmeni, narozeniny, ID);
    }

    @Override
    public boolean doYourJob() {
        Map<Zamestnanec, Integer> pocetVazeb = new HashMap();
        for (Zamestnanec z: this.relations.keySet()){           //iteruje přes všechny kolegy
            pocetVazeb.put(z, 0);
            for (Zamestnanec cz: z.relations.keySet()){         //iteruje vřes všechny spolupracovníky kolegy
                if (this.relations.containsKey(cz)){
                    pocetVazeb.put(z, pocetVazeb.get(z) + 1);
                }
                else{
                    pocetVazeb.put(z, 1);
                }
            }
                
        }
        int numRelations = 0;
        Zamestnanec bestColeague = null;
        
        for (Zamestnanec z: pocetVazeb.keySet()){               //iteruje přes všechny kolegy a hledá nevíce vazeb
            if (pocetVazeb.get(z) > numRelations){             //který kolega má s ním nejvíce vazeb, pokud více, vybere prvniho
                System.out.println(z);
                numRelations = pocetVazeb.get(z);               //vazby na ostatni, vcetne sebe sama
                bestColeague = z;
            }
        }
        try{
            System.out.println("Nejvice spolecnych kolegu ma s: " + bestColeague.toString() + ", a to celkem " + (numRelations - 1) + ".");
            return true;
        }
        catch (NullPointerException e){
            //System.out.println(e);
            System.out.println("" + toString() + "Nema zadne kolegy, nebo s nimi nema zadne vazby.");
            return false;
        }
    }

    @Override
    public boolean deleteRelations() {
        Set<Zamestnanec> coleagues = this.relations.keySet();
        for (Zamestnanec z:coleagues){
            z.relations.remove(this);
        }
        this.relations.clear();
        return true;
    }

    @Override
    public boolean addRelation(Zamestnanec coleague, float level) {
        if (coleague == null || (level < 0 || level > 1)){
            return false;
        }
        this.relations.put(coleague, level);
        coleague.relations.put(this, level);
        return true;
    }

    @Override
    public int getNumberOfRelations() {
    	return this.relations.size();
    }

    @Override
    public float getMostUsedRelation() {
        Map<Float, Integer> vyskyty = new HashMap();
        for (Zamestnanec z:this.relations.keySet()){
            Float relation = this.relations.get(z);
            if (vyskyty.containsKey(relation)){
                vyskyty.put(relation, vyskyty.get(relation) + 1);
            }
            else{
                vyskyty.put(relation, 1);
            }
        }
        int count = 0;
        float mostUsed = 0;
        for (Float relation:vyskyty.keySet()){
            if (vyskyty.get(relation) > count){
                count = vyskyty.get(relation);
                mostUsed = relation;
            }
        }
        return mostUsed;
    }

    @Override
    public boolean getGroup() {
        return true;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public int getBirthDate() {
        return this.birthYear;
    }

    @Override
    public int compareTo(Zamestnanec z) {
        return this.getSurname().compareToIgnoreCase(z.getSurname());
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.ID);
    }

    @Override
    public String toString() {
        return ("Datovy analytik s ID: " + Integer.toString(this.getID()) + ": " + this.getSurname() + this.getName() + " " + " nrozen " + Integer.toString(this.getBirthDate()) + ".");
    }

    @Override
    public boolean equals(Object obj) {
        // Pokud porovnávám objekt sám se sebou, je to shoda
        if (this == obj) return true;
        // Pokud je druhý objekt prázdný, není to shoda
        if (obj == null) return false;
        // Pokud druhý objekt není stejného typu, není to shoda
        if (getClass() != obj.getClass()) return false;
        
        // Převedeme neznámý objekt na Zaměstnance a porovnáme jejich ID
        Zamestnanec other = (Zamestnanec) obj;
        return this.getID() == other.getID();
    }

    @Override
    public int compareTo(Object o) {
        return this.hashCode() - o.hashCode();
    }

}
