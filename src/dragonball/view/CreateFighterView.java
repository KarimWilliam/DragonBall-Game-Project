package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateFighterView extends JFrame {
	
	private JPanel main;
	private JPanel FighterGrid;
	private JPanel ClassInfo;
	private HintTextField EnterName;
	private JPanel TextGrid;
	public CreateFighterView() throws IOException
	{
		setTitle("Creating Fighter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 1900, 1000);
		add(new JPanelWithBackground("src/resources/Goku.Png"));
		FighterGrid = new JPanel();
		FighterGrid.setLayout(new GridLayout(1, 5));
		add(FighterGrid, BorderLayout.NORTH);
		FighterGrid.setPreferredSize(new Dimension(getWidth(),500));
		ClassInfo = new JPanel();
		ClassInfo.setLayout(new GridLayout(1,5));
		add(ClassInfo,BorderLayout.CENTER);
		  EnterName = new HintTextField("Please Enter your Fighter's Name here");
		//EnterName = new JTextArea();
		//EnterName.setEditable(true);
		EnterName.setPreferredSize(new Dimension(getWidth(),50));
		//EnterName.setText("please Enter your Fighter's name here");
		TextGrid = new JPanel();
		TextGrid.setLayout(new GridLayout(1,0));
		TextGrid.add(EnterName);
		add(TextGrid, BorderLayout.SOUTH);
		
		
	}
	
	
	
	public static void main (String [] args) throws IOException
	{
		CreateFighterView cfv = new CreateFighterView();
		cfv.setVisible(true);
	}
	public void addFighter(JButton fighter) {
		FighterGrid.add(fighter);
	}
	public void addText (JTextArea text ) {
		ClassInfo.add(text);
		
	}

	public HintTextField getEnterName() {
		return EnterName;
	}

	public void setEnterName(HintTextField enterName) {
		EnterName = enterName;
	}

	public JPanel getTextGrid() {
		return TextGrid;
	}

	public void setTextGrid(JPanel textGrid) {
		TextGrid = textGrid;
	}

	
}
