package sample.perso;

import java.util.ArrayList;

public class Personnages extends Avatar
{
	int points_perso=0;

	public Personnages(String prenom, String image_perso) {
		super(prenom, image_perso);
	}
	
	public int get_points() {
		return points_perso;
	}
	
	
	public String toString(){
		return "Les aspects du personnage : " + super.toString() + ", Le nombre de points accumul� " + points_perso;
	}

	public void addPoint(int point)
	{
		points_perso+=point;
	}

	public static ArrayList<Personnages> classeEI2I()
	{

		ArrayList<Personnages> classe = new ArrayList<>();

		classe.add(new Personnages("Antoine","Images/Antoine.PNG "));
		classe.add(new Personnages("Aymerick","Images/Aymerick.PNG "));
		classe.add(new Personnages("Adrien","Images/Adrien.PNG "));
		classe.add(new Personnages("Bibou","Images/William.PNG "));

		classe.add(new Personnages("Ariel","Images/Ariel.PNG "));
		classe.add(new Personnages("Victor","Images/Victor.PNG "));
		classe.add(new Personnages("Bolor","Images/Bolor.PNG "));

		classe.add(new Personnages("Jad","Images/Jad.PNG "));
		classe.add(new Personnages("Yassine","Images/Yassine.PNG "));
		classe.add(new Personnages("Piotr","Images/Piotr.PNG "));

		classe.add(new Personnages("Joao","Images/Joao.PNG "));
		classe.add(new Personnages("Xavier","Images/Xavier.PNG "));
		classe.add(new Personnages("Mariama","Images.PNG "));
		classe.add(new Personnages("Magatte","Images/Magatte.PNG "));
		classe.add(new Personnages("Mama","Images/Mama.PNG "));
		classe.add(new Personnages("Cherif","Images/Cherif.PNG "));
		classe.add(new Personnages("Simran","Images/Simran.PNG "));
		classe.add(new Personnages("Khadija","Images/Khadija.PNG "));
		classe.add(new Personnages("Julien","Images/Julien.PNG "));
		classe.add(new Personnages("Houda","Images/Houda0.PNG "));
		classe.add(new Personnages("Sarah","Images/Sarah.PNG "));
		classe.add(new Personnages("Marc A","Images/MarcA.PNG "));
		classe.add(new Personnages("Marc","Images/MarcB.PNG "));
		classe.add(new Personnages("Florence","Images/Florence.PNG "));
		classe.add(new Personnages("Manu","Images/Manu.PNG "));

		return classe;
	}

}

