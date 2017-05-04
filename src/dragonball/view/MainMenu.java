package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dragonball.controller.CreateFirstFighterController;
import dragonball.controller.GameController;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotSamePlayerException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;

public class MainMenu extends GameView implements ActionListener  {
	private JLayeredPane main;
	private JPanel panelOptions;
	private JButton btnNew;
	private JButton btnLoad;
	private JButton btnClose;
	private JButton AI;
	private Game game;
	private boolean AION;
	JLabel greenLed;
	JLabel whiteLed;
	public MainMenu () throws MissingFieldException, UnknownAttackTypeException
	{
		
		setTitle("Main Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1000);
		//panelOptions = new JPanel();
	
		main = new JLayeredPane();
		main.setBounds(0, 0, 1920, 1000);
		add(main);
		AI = new JButton();
		btnNew = new JButton();
		btnLoad = new JButton();
		btnClose = new JButton();
		AI.setText("AI ON/OFF");
		btnNew.setText("Start new Game");
		AI.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		btnNew.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		btnLoad.setText("Load saved Game");
		btnLoad.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		btnClose.setText("Exit Game");
		btnClose.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		AI.setBounds(380, 50, 200, 100);
		btnNew.setBounds(610, 50, 700, 100);
		btnLoad.setBounds(610, 180, 700, 100);
		btnClose.setBounds(610, 310, 700, 100);
		AI.setBackground(Color.ORANGE);
		btnNew.setBackground(Color.ORANGE);
		btnLoad.setBackground(Color.ORANGE);
		btnClose.setBackground(Color.ORANGE);
		main.add(AI, new Integer (2));
		main.add(btnNew,new Integer (2));
		main.add(btnLoad,new Integer (2));
		main.add(btnClose,new Integer (2));
		AI.addActionListener(this);
		btnNew.addActionListener(this);
		btnLoad.addActionListener(this);
		btnClose.addActionListener(this);
		Image Main = getImage("src/resources/MainMenu.Png");
		Image newMain = Main.getScaledInstance( 1920,1000,  java.awt.Image.SCALE_SMOOTH ) ;
		JLabel bg = new JLabel();
		bg.setIcon(new ImageIcon(newMain));
		bg.setBounds(0, 0, 1920, 1000);
		main.add(bg,btnClose,new Integer (0));
		
		
		
		// making AI Light
		Image green = getImage("src/resources/GreenLED.Png");
		Image newgreen = green.getScaledInstance( 100,100,  java.awt.Image.SCALE_SMOOTH ) ;
		 greenLed = new JLabel();
		greenLed.setIcon(new ImageIcon(newgreen));
		greenLed.setBounds(1400, 50, 100, 100);
		main.add(greenLed, new Integer (2));
		greenLed.setVisible(false);
		
		Image white = getImage("src/resources/WhiteLED.Png");
		Image newwhite = white.getScaledInstance( 100,100,  java.awt.Image.SCALE_SMOOTH ) ;
		whiteLed = new JLabel();
		whiteLed.setIcon(new ImageIcon(newwhite));
		whiteLed.setBounds(1400, 50, 100, 100);
		main.add(whiteLed, new Integer (2));
		
		
	}
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		switch(btn.getText())
		{
		case "AI ON/OFF":
			if (AION == true) 
				{
				AION = false;
				whiteLed.setVisible(true);
				greenLed.setVisible(false);
				
				}
			else if (AION == false)
				{
				AION = true;
				whiteLed.setVisible(false);
				greenLed.setVisible(true);
				}
			
			break;
		case "Start new Game":
			try {
				
				
				CreateFirstFighterController CFFC = new CreateFirstFighterController(AION);
				CFFC.getCreatefighterview().setVisible(true);
				this.setVisible(false);
				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case "Load saved Game":
			try {
				game = new Game("player",AION);
				game.load("src/resources/SaveFolder/s.bin");
				((GameController)game.getListener()).getWorldview().setVisible(true);
			} catch (ClassNotFoundException e1) {
				JPanel JP = new JPanel();
				JP.setBounds(100, 100, 1200, 900);
				JTextArea ops = new JTextArea();
				ops.setText("There is no save data!");
				JP.add(ops);
				JP.setVisible(true);
				this.setVisible(false);
			} catch (NotSamePlayerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		case "Exit Game":
			System.exit(0);
			break;
		}
		
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
	
	public static void main (String [] args) throws MissingFieldException, UnknownAttackTypeException
	{
		MainMenu MM = new MainMenu();
		MM.setVisible(true);
	}
}
