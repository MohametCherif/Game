package sample.texteManagement;

import sample.ControllerInterfaceJeu;
import sample.perso.Personnages;

import java.io.*;
import java.util.ArrayList;

public class TraitemenTexte
{
	String filePath;
	BufferedReader reader;
	ArrayList<String> choix = new ArrayList<>();
	ArrayList<Personnages> personnage = new ArrayList<>();
	Personnages personneQuiParle;
	String ceQuilDit;
	ControllerInterfaceJeu controllerInterfaceJeu;
	private static final ArrayList<Personnages> classe = Personnages.classeEI2I();
	boolean endGame=false;

	public TraitemenTexte(String monFichier) throws FileNotFoundException, UnsupportedEncodingException
	{
		filePath = monFichier;
		setReader(filePath);
	}

	public void findChoice(String choix) throws IOException
	{
		System.out.println("\nfindChoice()");
		File choiceFile = new File("src/sample/texteManagement/Texte/Choix.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(choiceFile), "UTF-8"));

		String line = br.readLine();
		String[] tLine;
		while (line!=null)
		{
			tLine = line.split(" - ",2);
			if (choix.equals(tLine[0]))
			{
				System.out.println("Choice found in choix.txt");
				setFilePath(tLine[1]);
				lectureHistoire(controllerInterfaceJeu);
				br.close();
				return;
			}
			line = br.readLine();
		}
		br.close();
		System.out.println("\nNO CHOICE FOUND IN CHOIX.TXT !!!!!!!!");
	}

	public void setFilePath(String filePath) throws FileNotFoundException, UnsupportedEncodingException
	{
		this.filePath = "src/sample/texteManagement/Texte/"+filePath;
		setReader(this.filePath);
	}

	public void setReader(String filePath) throws FileNotFoundException, UnsupportedEncodingException
	{
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
	}

	public void lectureHistoire(ControllerInterfaceJeu controllerInterfaceJeu) throws IOException
	{
		System.out.println("Fichier = "+filePath);
		this.controllerInterfaceJeu = controllerInterfaceJeu;
		String line = reader.readLine();
		//while (line != null)
		if (line!=null)
		{
			System.out.println("\nLine = "+line);

			if (line.contains("(Choix"))
			{
				listerChoix(reader, controllerInterfaceJeu);
				return;
			}

			else if (line.contains(":"))
			{
				String[] splited = line.split(":",2);

				String speaker = splited[0].trim();
				personneQuiParle = findPersonne(speaker);
				affichePersonne(personneQuiParle, controllerInterfaceJeu);
				checkListPersonne(personneQuiParle, personnage, controllerInterfaceJeu);
				ceQuilDit = splited[1].trim();
				afficheDialog(ceQuilDit, controllerInterfaceJeu);
				return;
			}

			else if (line.contains("Background"))
			{
				line = reader.readLine();
				File background = new File(line);
				controllerInterfaceJeu.changeBackground(background.getAbsolutePath());
				personnage.clear();
				controllerInterfaceJeu.supprimePersonnage();
				System.out.println("\nLine background = "+line);
				return;
			}

			else
			{
				afficheDialog(line, controllerInterfaceJeu);
				controllerInterfaceJeu.setCharacterName("");
			}

		}

		else
		{
			fin(controllerInterfaceJeu);
			if(endGame)
			{
				reader.close();
			}
		}
	}

	public void fin(ControllerInterfaceJeu controllerInterfaceJeu)
	{
		System.out.println("Fin du jeu");
		controllerInterfaceJeu.setTexte("FIN DU JEU");
		endGame = true;
	}

	public void listerChoix(BufferedReader reader, ControllerInterfaceJeu controllerInterfaceJeu) throws IOException
	{
		if(endGame)
		{
			controllerInterfaceJeu.quitGame();
		}
		String line = reader.readLine();
		while (line != null)
		{
			choix.add(line);
			line = reader.readLine();
		}

		afficherChoix(choix, controllerInterfaceJeu);
		choix.clear();
	}

	public void afficherChoix(ArrayList<String> choix, ControllerInterfaceJeu controllerInterfaceJeu) throws IOException
	{
		System.out.println("\n------Choix a afficher-------");
		for(String choice : choix)
		{
			System.out.println(choice);
		}

		controllerInterfaceJeu.showChoices(choix);

	}

	public Personnages findPersonne(String personneQuiParle)
	{
		String personneQuiParle1 = personneQuiParle.substring(0,personneQuiParle.length()-1);
		for (Personnages personne : classe)
		{
			if (personneQuiParle.equalsIgnoreCase(personne.get_prenom()))
			{
				System.out.println("found");
				//personneQuiParle.setImage(personne.get_image());

				return personne;
			}

			System.out.println("Classe = "+personne.get_prenom()+" / Personnage = "+personneQuiParle);
		}

		for (Personnages personne : classe)
		{
			if (personneQuiParle1.equalsIgnoreCase(personne.get_prenom()))
			{
				System.out.println("found");
				//personneQuiParle.setImage(personne.get_image());

				return personne;
			}

			System.out.println("Classe = "+personne.get_prenom()+" / Personnage = "+personneQuiParle);
		}

		System.out.println(personneQuiParle+"Not found");
		return new Personnages(personneQuiParle, "url");
	}

	public void affichePersonne(Personnages personneQuiParle, ControllerInterfaceJeu controllerInterfaceJeu)
	{
		System.out.println("Personne qui parle = "+personneQuiParle.get_prenom());
		controllerInterfaceJeu.setCharacterName(personneQuiParle.get_prenom());
	}

	public void afficheDialog(String dialog, ControllerInterfaceJeu controllerInterfaceJeu)
	{
		System.out.println("Dialog = "+dialog);
		controllerInterfaceJeu.setTexte(dialog);
	}

	public void checkListPersonne(Personnages personne, ArrayList<Personnages> listPersonne, ControllerInterfaceJeu controllerInterfaceJeu)
	{
		if (personne.get_image().equals("url"))
		{
			System.out.println("Avatar joueur");
			return;
		}
		for (Personnages s : listPersonne)
		{
			if (personne.equals(s))
			{
				System.out.println(personne.get_prenom()+" Deja présent");
				return;
			}
		}

		listPersonne.add(personne);
		controllerInterfaceJeu.afficherPersonnage(personne.get_image());
		System.out.println(personne.get_prenom()+"ajouté");
	}

	public void interactionJeu(ControllerInterfaceJeu controllerInterfaceJeu) throws IOException
	{
		lectureHistoire(controllerInterfaceJeu);
	}

	public void choixClicked(String choix) throws IOException
	{
		System.out.println("\nchoixClicked()");
		findChoice(choix);
	}

}