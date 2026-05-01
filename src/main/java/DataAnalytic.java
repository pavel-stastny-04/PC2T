
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
    
    public DataAnalytic(String jmeno, String prijmeni, int narozeniny){
        super(jmeno, prijmeni, narozeniny);
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
            }
                
        }
        int numRelations = 0;
        Zamestnanec bestColeague = null;
        
        for (Zamestnanec z: pocetVazeb.keySet()){               //iteruje přes všechny kolegy a hledá nevíce vazeb
            if (pocetVazeb.get(z) > numRelations){
                numRelations = pocetVazeb.get(z);
                bestColeague = z;
            }
        }
        try{
            System.out.println("Nejvíce společných kolegů má s: " + bestColeague.toString() + ", a to celkem " + numRelations + ".");
            return true;
        }
        catch (NullPointerException e){
            System.out.println(e);
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
        int count = 0;
        for(Zamestnanec z:this.relations.keySet()){
            count++;
        }
        return count;
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
        if (z.getID() == this.getID()){
            return 0;
        }
        return this.getID() - z.getID();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.ID);
    }

    @Override
    public String toString() {
        return ("Datový analytik s ID: " + Integer.toString(this.getID()) + ": " + this.getName() + " " + this.getSurname() + " nrozen " + Integer.toString(this.getBirthDate()) + ".");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        return this.hashCode() - o.hashCode();
    }

}
