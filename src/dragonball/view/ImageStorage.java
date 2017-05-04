package dragonball.view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class ImageStorage  {

	Image Goku;
	Image Earthling;
	Image Frieza;
	 Image Majin;
	 Image Namekian;
	 Image boss;
	 Image forest;
	 Image Ghost;
	 Image Gokuf;
	 Image Namekianf;
	 Image Majinf;
	 Image Dragon;
	 Image Health;
	 Image Damage;
	 Image Stamina;
	 Image KI;
	 Image Blast;
	 Image UP;
	 Image Down;
	 Image Victory ;
	public ImageStorage ()
	{
		 Goku = getImage("src/resources/Goku.Png");
		  Earthling = getImage("src/resources/Earthling.Png");
		   Frieza = getImage("src/resources/Frieza2.Png");
		    Majin = getImage("src/resources/Majin.Png");
		     Namekian = getImage("src/resources/Namekian.Png");
		      boss = getImage("src/resources/boss.Png");
		       forest = getImage("src/resources/forest.Png");
		       Ghost = getImage("src/resources/Cloak.Png");;
		       Gokuf =  getImage("src/resources/Gokuf.Png");
		       Majinf =  getImage("src/resources/Majinf.Png");
		       Namekianf =  getImage("src/resources/Namekianf.Png");
		        Dragon =  getImage ("src/resources/Dragony.Png");
		         Health = getImage("src/resources/Health.Png");
		         Damage = getImage("src/resources/Damage.Png");
		         Stamina = getImage("src/resources/Stamina.Png");
		         KI = getImage("src/resources/KI.Png");
		       Blast = getImage("src/resources/Blast.Png");
		      UP = getImage("src/resources/blue.Png");
		       Down = getImage("src/resources/red.Png");
		        Victory = getImage("src/resources/Victory.Png");
	}
	
	
	public Image getImage (String path)
	{
		Image img;
		try {
			img = ImageIO.read(new File(path));
			return img;
			
		} catch (IOException e) {
			System.out.println("picture not found");
			return null;
		}
	}


	public Image getGoku() {
		return Goku;
	}


	public Image getEarthling() {
		return Earthling;
	}


	public Image getFrieza() {
		return Frieza;
	}


	public Image getMajin() {
		return Majin;
	}


	public Image getNamekian() {
		return Namekian;
	}


	public Image getBoss() {
		return boss;
	}


	public Image getForest() {
		return forest;
	}


	public Image getGhost() {
		return Ghost;
	}


	public Image getGokuf() {
		return Gokuf;
	}


	public Image getNamekianf() {
		return Namekianf;
	}


	public Image getMajinf() {
		return Majinf;
	}


	public Image getDragon() {
		return Dragon;
	}


	public Image getHealth() {
		return Health;
	}


	public Image getDamage() {
		return Damage;
	}


	public Image getStamina() {
		return Stamina;
	}


	public Image getKI() {
		return KI;
	}


	public Image getBlast() {
		return Blast;
	}


	public Image getUP() {
		return UP;
	}


	public Image getDown() {
		return Down;
	}


	public Image getVictory() {
		return Victory;
	}
	
	
	
	
	

}
