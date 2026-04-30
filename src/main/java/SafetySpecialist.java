
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author baartys
 */
public class SafetySpecialist extends Zamestnanec{  
	
	public SafetySpecialist(String jmeno, String prijmeni, int narozeniny) {
        super(jmeno, prijmeni, narozeniny);
    }
	
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

    public boolean deleteRelations() {
    	this.relations.clear();
        return true;
    }

    public boolean addRelation(Zamestnanec coleague, float level) {
        this.relations.put(coleague, level);
        return true;
    }

    public int getNumberOfRelations() {
    	return this.relations.size(); 
    }

    public float getMostUsedRelation() {
    	if (this.relations.isEmpty()) {
            return 0f;
        }
        return java.util.Collections.max(this.relations.values());
    }

    public boolean getGroup() {
    	return false;
    }

    public String getName() {
    	return this.name;
    }

    public String getSurname() {
    	return this.surname;
    }

    public int getID() {
    	return this.ID;
    }

    public int getBirthDate() {
    	return this.birthYear;  
    }

    public int compareTo(Zamestnanec z) {
        return this.getSurname().compareToIgnoreCase(z.getSurname());
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.ID);
    }

    @Override
    public String toString() {
        return "Specialista: [" + this.getID() + "] " + this.getName() + " " + this.getSurname();
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
    
}
