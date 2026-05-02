
import java.util.HashMap;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author baartys
 */
public class SafetySpecialist extends Zamestnanec{  
	
	public SafetySpecialist(String jmeno, String prijmeni, int narozeniny, int ID) {
        super(jmeno, prijmeni, narozeniny, ID);
    }
    
    @Override
    public boolean doYourJob() {
        if (this.relations.isEmpty()) {
            System.out.println("Specialista " + this.getName() + " nema zadne vazby. Riziko: 0");
            return true; 
        }
        float sumLevel = 0;
        for (Float level : this.relations.values()) {
            sumLevel += level;
        }
        float avgLevel = sumLevel / this.relations.size(); 
        float riskScore = this.relations.size() * (1.1f - avgLevel);
        System.out.println("Specialista " + this.getName() + " vyhodnotil rizikove skore: " + riskScore);
        
        return true;
    }

    @Override
    public boolean deleteRelations() {
    	this.relations.clear();
        return true;
    }

    @Override
    public boolean addRelation(Zamestnanec coleague, float level) {
        this.relations.put(coleague, level);
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
    	return false;
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
        return ("Bezpecnostni specialista s ID: " + Integer.toString(this.getID()) + ": " + this.getSurname() + " " + this.getName() + " nrozen " + Integer.toString(this.getBirthDate()) + ".");
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