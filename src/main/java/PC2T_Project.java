
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author pavel
 */
public class PC2T_Project {
    public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
    public static float pouzeFloat01(Scanner sc) 
	{
		float cislo = 0;
		try
		{
			cislo = sc.nextFloat();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cislo mezi 0 a 1 ");
			sc.nextLine();
                        cislo = pouzeFloat01(sc);
		}
                if (cislo > 1 || cislo < 0){
			System.out.println("zadejte prosim cislo mezi 0 a 1 ");
			sc.nextLine();
                        cislo = pouzeFloat01(sc);
                }
		return cislo;
	}
    
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
		Map <Integer, Zamestnanec> mojeDatabaze = new HashMap();
		int volba;
		boolean run=true;
		while(run)
		{
			System.out.println("Vyberte pozadovanou operaci:");
			System.out.println("1  .. Pridani noveho zamestnance");
			System.out.println("2  .. Pridani spoluprace");
			System.out.println("3  .. Odstraneni zamestnance");
			System.out.println("4  .. Najit zamestnance");
			System.out.println("5  .. Spustit dovednost zamestnance");
			System.out.println("6  .. Vypsat skupiny zamestnancu");              //poradi dle prijmeni abecedne
			System.out.println("7  .. Vypsat statistiky");
			System.out.println("8  .. Vypsat pocty zamestnancu");                //celkem i ve skupinach
			System.out.println("9  .. Ulozit zamestnance do souboru");
			System.out.println("10 .. Nacist zamestnance ze souboru");
			System.out.println("11 .. Ulozit data do SQL");
			System.out.println("12 .. Obnovit data z SQL");
			System.out.println("13 .. Ukoncit aplikaci");
                        
			volba=pouzeCelaCisla(sc);
			switch(volba)
			{
				case 1:
					System.out.println("Zadej skupinu noveho zamestnance (1: Datovy analytik, 2: Bezpecnostni specialista): ");
                                        int skupina = pouzeCelaCisla(sc);
					System.out.println("Zadej jmeno noveho zamestnance: ");
                                        String jmeno = sc.next();
					System.out.println("Zadej prijmeni noveho zamestnance: ");
                                        String prijmeni = sc.next();
					System.out.println("Zadej rok narozeni noveho zamestnance: ");
                                        int narozen = pouzeCelaCisla(sc);
                                        int id;
                                        if (mojeDatabaze.isEmpty()){
                                            id = 1;
                                        }
                                        else{
                                            id = Collections.max(mojeDatabaze.keySet()) + 1;
                                        }
                                        
                                        if (skupina == 1){
                                            mojeDatabaze.put(id, new DataAnalytic(jmeno, prijmeni, narozen, id));
                                        }
                                        else if (skupina == 2){
                                            mojeDatabaze.put(id, new SafetySpecialist(jmeno, prijmeni, narozen, id));
                                        }
                                        else{
                                            System.out.println("Neznámá skupina \"" + skupina + "\". Zadejte 1 nebo 2.");
                                        }
                                        
					break;
				case 2:
					System.out.println("Zadej ID prvniho zamestnance: ");
                                        int id1 = pouzeCelaCisla(sc);
                                        if (!mojeDatabaze.containsKey(id1)){
                                            System.out.println("Neznámé ID \"" + id1 + "\".");
                                            break;
                                        }
					System.out.println("Zadej ID druheho zamestnance: ");
                                        int id2 = pouzeCelaCisla(sc);
                                        if (!mojeDatabaze.containsKey(id2)){
                                            System.out.println("Neznámé ID \"" + id2 + "\".");
                                            break;
                                        }
					System.out.println("Zadej uroven spoluprace (v rozmezi 0 - 1): ");
                                        float spoluprace = pouzeFloat01(sc);
                                        
                                        mojeDatabaze.get(id1).addRelation(mojeDatabaze.get(id2), spoluprace);
					
					break;
				case 3:
					System.out.println("Zadej ID zamestnance k odstraneni: ");
                                        int idDel = pouzeCelaCisla(sc);
                                        if (!mojeDatabaze.containsKey(idDel)){
                                            System.out.println("Neznámé ID \"" + idDel + "\".");
                                            break;
                                        }
                                        System.out.println("Opravdu chcete odstranit tohoto zamestnance:\n" + mojeDatabaze.get(idDel).toString() + "\n (Y/N): ");
                                        String odpoved = sc.next();
                                        if (odpoved.equalsIgnoreCase("Y") || odpoved.equalsIgnoreCase("y")){
                                            mojeDatabaze.get(idDel).deleteRelations();
                                            mojeDatabaze.remove(idDel);
                                            System.out.println("Operace provedena uspesne");
                                            break;
                                        }
                                        
                                        System.out.println("Ruseni...");
                                        
					break;
				case 4:
					System.out.println("Zadej ID hledaneho zamestnance: ");
                                        int idFind = pouzeCelaCisla(sc);
                                        if (!mojeDatabaze.containsKey(idFind)){
                                            System.out.println("Zamestnanec s ID \"" + idFind + "\" se v databazi nenachazi.");
                                            break;
                                        }
                                        System.out.println("Hledate tohoto zamestnance:\n" + mojeDatabaze.get(idFind).toString() + "\n\n");
                                        
                                        break;
				case 5:System.out.println("Zadej ID zamestnance, ktery ma vykonat svou praci: ");
                                        int idWork = pouzeCelaCisla(sc);
                                        if (!mojeDatabaze.containsKey(idWork)){
                                            System.out.println("Zamestnanec s ID \"" + idWork + "\" se v databazi nenachazi.");
                                            break;
                                        }
                                        mojeDatabaze.get(idWork).doYourJob();
                                        
                                        System.out.println("\n\n");
					break;
				case 6:
                                        List<Zamestnanec> Analytici = new ArrayList();
                                        List<Zamestnanec> Specialiste = new ArrayList();
                                        
                                        for (int z: mojeDatabaze.keySet()){
                                            if (mojeDatabaze.get(z).getGroup()){
                                                Analytici.add(mojeDatabaze.get(z));
                                            }
                                            else{
                                                Specialiste.add(mojeDatabaze.get(z));
                                            }
                                        }
                                        
                                        Analytici.sort((a, b) -> { return a.compareTo(b); });
                                        Specialiste.sort((a, b) -> { return a.compareTo(b); });
                                        
                                        System.out.println("Skupina datovych analytiku:\n-----------------------------------------------------------------------------------------------------------------------------------------");
                                        for (Zamestnanec z: Analytici){
                                            System.out.println(z);
                                        }
                                        System.out.println("\nSkupina bezpecnostnich speialistu:\n-----------------------------------------------------------------------------------------------------------------------------------------");
                                        for (Zamestnanec z: Specialiste){
                                            System.out.println(z);
                                        }
                                        
                                        System.out.println("\n\n");
                                    
                                    
					break;
				case 7:
                                        Map<Float, Integer> kvalitySpoluprace = new HashMap();
                                        int nejPocetKolegu = 0;
                                        Zamestnanec nejZamestnanec = null;
                                        for (int z: mojeDatabaze.keySet()){
                                            int pocetKolegu = mojeDatabaze.get(z).getNumberOfRelations();
                                            float spolupraceLevel = 0;
                                            if (pocetKolegu != 0){
                                                spolupraceLevel = mojeDatabaze.get(z).getNumberOfRelations();
                                            }
                                            if (pocetKolegu >= nejPocetKolegu){                                 //pokud jich bude více stejných, vybrat posledního
                                                nejPocetKolegu = pocetKolegu;
                                                nejZamestnanec = mojeDatabaze.get(z);
                                            }
                                            if (kvalitySpoluprace.containsKey(spolupraceLevel)){
                                                kvalitySpoluprace.put(spolupraceLevel, kvalitySpoluprace.get(spolupraceLevel) + 1);
                                            }
                                            else{
                                                kvalitySpoluprace.put(spolupraceLevel, 1);
                                            }
                                        }
                                        System.out.println("Nejvíce kolegů má " + nejZamestnanec + ", a to " + nejPocetKolegu);
                                        
                                        int nejPocetKvality = 0;
                                        float nejKvalita = 0;
                                        for (float k : kvalitySpoluprace.keySet()){
                                            if (kvalitySpoluprace.get(k) > nejPocetKvality){
                                                nejPocetKvality = kvalitySpoluprace.get(k);
                                                nejKvalita = k;
                                            }
                                        }
                                        
                                        System.out.println("Nejčastější kvalita spolupráce je " + nejKvalita + " s četností " + nejPocetKvality + ".\n\n");
                                    
                                    
					break;
				case 8:
					int analytici = 0;
                                        int specialiste = 0;
                                        for (int i : mojeDatabaze.keySet()){
                                            if (mojeDatabaze.get(i).getGroup()){
                                                analytici += 1;
                                            }
                                            else{
                                                specialiste += 1;
                                            }
                                        }
                                        
                                        System.out.println("Datovi analytici: " + analytici + "\nBezpecnostni specialiste: " + specialiste + "\n Celkem: " + (analytici + specialiste) + "\n\n");
                                        
                                        
					break;
				case 9:
					System.out.println("Zadejte ID zamestnance, ktereho chcete ulozit: ");
                                        int idSave = pouzeCelaCisla(sc);
                                        if (!mojeDatabaze.containsKey(idSave)){
                                            System.out.println("Neznámé ID \"" + idSave + "\".");
                                            break;
                                        }
                                        try {
                                            serializuj(mojeDatabaze.get(idSave), idSave);
                                            System.out.println("Zamestnanec uspesne ulozen!");
                                        } catch (IOException ex) {
                                            //System.getLogger(PC2T_Project.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                            System.out.println("Zamestnance s ID " + idSave + " se nepodarilo ulozit! Vracena chyba je \n" + ex);
                                            break;
                                        }
                                        
					break;

				case 10:
					System.out.println("Zadejte ID zamestnance, ktereho chcete nacist: ");
                                        int idLoad = pouzeCelaCisla(sc);
                                        Zamestnanec nacteny = null;
                                        try {
                                            nacteny = deserializuj(idLoad);
                                        } catch (IOException ex) {
                                            //System.getLogger(PC2T_Project.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                            System.out.println("Zamestnance s ID " + idLoad + " se nepodarilo nacist! Vracena chyba je \n" + ex);
                                            break;
                                        } catch (ClassNotFoundException ex) {
                                            //System.getLogger(PC2T_Project.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                            System.out.println("Zamestnance s ID " + idLoad + " se nepodarilo nacist! Vracena chyba je \n" + ex);
                                            break;
                                        }
                                        
                                        if (nacteny == null){
                                            System.out.println("Dany soubor neobsahuje zamestnance!");
                                            break;
                                        }
                                        
                                        if (mojeDatabaze.containsKey(nacteny.getID())){
                                            System.out.println("Zametnanec s id " + nacteny.getID() + " se jiz v databazi nachazi:\n" + mojeDatabaze.get(nacteny.getID()) + "\n\n");
                                            break;
                                        }
                                        mojeDatabaze.put(nacteny.getID(), nacteny);
                                        System.out.println("" + nacteny + " uspesne pridan do databaze!");
                                        
					break;

				case 11:
					questionMark(); //bude obsahovat ulozeni do SQL
					break;
				case 12:
					questionMark(); //bude obsahovat nacteni z SQL
					break;
				case 13:
					run=false;
					break;
                                default:
                                    System.out.println("Neznama operace \"" + volba + "\". Vyberte prosim polozku z nasledujici nabidky.");
                                    questionMark();
                                    break;
			}
			
		}
	
    }
    public static void serializuj(Zamestnanec p, int ID) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("Zamestnanec_" + ID + ".dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(p);
        }
    }
    public static Zamestnanec deserializuj(int ID) throws IOException, ClassNotFoundException {
        Zamestnanec p;
        try (FileInputStream fis = new FileInputStream("Zamestnanec_" + ID + ".dat")) {
            ObjectInputStream ois =new ObjectInputStream(fis);
            p = (Zamestnanec)ois.readObject();
        }
    return p;
    }
    public static void questionMark(){
        System.out.println(".\n.\n.\n.\n\n\n");
        System.out.println("What you were looking for there good man? \n"+
        "\n"+
        "\n"+
        "        ????????\n"+
        "     ??????????????\n"+
        "   ?????        ?????\n"+
        "  ????            ????\n"+
        "  ???              ???\n"+
        "                   ???\n"+
        "                  ????\n"+
        "                 ????\n"+
        "                ????\n"+
        "               ????\n"+
        "             ????\n"+
        "           ????\n"+
        "         ????\n"+
        "        ????\n"+
        "        ???\n"+
        "        ???\n"+
        "\n"+
        "        ???\n"+
        "        ???\n"+
        "        ???\n"+
        "\n"+
        "\n"+
        "There\'s stil nothing to do and your life is to short for wasting your time..."
        );
    }
}
