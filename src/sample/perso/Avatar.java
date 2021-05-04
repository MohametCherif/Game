package sample.perso;

import java.util.ArrayList;

public class Avatar
{
	private final String prenom;
	private String image_perso;
	
	
	public Avatar(String prenom, String image_perso)
	{
		this.prenom = prenom;
		this.image_perso = image_perso;

		}
	
	public String get_prenom() {
		return prenom;
	}
	
	public String get_image() {
		return image_perso;
	}

	public void setImage(String image)
	{
		image_perso = image;
	}
	
		public String toString() {
			return "Avatar :  Prenom : " + prenom + "; Image : " + image_perso;
			
		}
	
	public boolean estPresent(ArrayList<Avatar> listAvatar)
	{
		for(Avatar avatar : listAvatar)
		{
			if (this.equals(avatar))
			{
				return true;
			}
		}

		return false;
	}

	public boolean equals(Avatar avatar)
	{
		return this.prenom.equalsIgnoreCase(avatar.prenom);
	}
}
