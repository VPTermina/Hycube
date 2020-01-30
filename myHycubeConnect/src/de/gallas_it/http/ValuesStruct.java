package de.gallas_it.http;

import java.util.ArrayList;
import java.util.ListIterator;

public class ValuesStruct {
	ArrayList<ArrayList> valueList = new ArrayList<ArrayList>();
	ArrayList[] valueLines = new ArrayList[3];
	
	
public void init() {
	
	
	valueLines[0] = new ArrayList();
	valueLines[1] = new ArrayList();
	valueLines[2] = new ArrayList();
	
	valueLines[0].add("Battery_C");
	valueLines[0].add(55);
	
	
	valueLines[1].add("Battery_I");
	valueLines[1].add(-3.03);
	
	
	valueLines[2].add("Battery_P");
	valueLines[2].add(-149);
	
	
	valueList.add(valueLines[0]);
	valueList.add(valueLines[1]);
	valueList.add(valueLines[2]);
	
	for (ListIterator<ArrayList> li = valueList.listIterator(0); li.hasNext();){
		for(ListIterator li2 = li.next().listIterator(0); li2.hasNext(); ){
			System.out.println(li2.next());	
		}
	}
	
}


	
/*
 * 
 * Bespiel zur Erzeugung einer zweidimensionalen Liste
 * http://www.codeadventurer.de/?p=1751
 * 	
 */
	public void initExample(){
	ArrayList<String> einkaufsListe = new ArrayList<>();
	einkaufsListe.add("Ei");
	einkaufsListe.add("Schokolade");
	einkaufsListe.add("Zahnpasta");
	einkaufsListe.add("Banane");
	einkaufsListe.add("Tiefkühlpizza");
	
	ListIterator<String> artikelIterator = einkaufsListe.listIterator(0);
	do{
		System.out.println(artikelIterator.next());
		}
	while(artikelIterator.hasNext());
	
	for (ListIterator li = einkaufsListe.listIterator(0); li.hasNext();){
		System.out.println(li.next());
	}
	
	//ab hier zweidimensional
	ArrayList<ArrayList> einkaufsListe1 = new ArrayList<ArrayList>();
	ArrayList[] artikelZeilen = new ArrayList[2];
	artikelZeilen[0] = new ArrayList();
	artikelZeilen[1] = new ArrayList();
	
	artikelZeilen[0].add("Straussenei");
	artikelZeilen[0].add(2.3);
	artikelZeilen[1].add("Hühnerei");
	artikelZeilen[1].add("Milch von brauner Kuh");
	
	einkaufsListe1.add(artikelZeilen[0]);
	einkaufsListe1.add(artikelZeilen[1]);
	
	for (ListIterator<ArrayList> li = einkaufsListe1.listIterator(0); li.hasNext();){
		for(ListIterator li2 = li.next().listIterator(0); li2.hasNext(); ){
			System.out.println(li2.next());	
		}
	}
	
	
	}
	
}


/**" Battery_C ": 55,
" Battery_I ": -3.03,
" Battery_P ": -149,	
" Battery_V ": 48.93,
" Grid_f ": 49.99,
" Grid_P ": 64,
" Grid_V ": 228.4,
" Home_P ": 144,
" Inv 1_I": 0.8,
" Inv 1_P": 80,
" Inv 1_V": 228.4,
" Solar 1_P": 0,
" Solar 1_I": 0,
" Solar 1_V": 0,
" solar 2_P": 0,
" solar 2_I": 0,
" solar 2_V": 0,
" solar_total_P ": 0

*/