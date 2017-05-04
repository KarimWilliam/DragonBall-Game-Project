package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import dragonball.controller.ListListener;


public class WorldView extends GameView{
	
	private JPanel panelCells;
	private JTextArea txtEvent;
	private JTextArea playerInfo;
	private JPanel panelInfo;
	private JPanel panelMenu;
	private  DefaultListModel charList;
	private ListListener LL;
	public WorldView ()
	
	{

		
		
		setTitle("World");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setBounds(100, 100, 1200, 900);
		setBounds(0, 0, 1920, 1000);
		panelCells = new JPanel();
		panelCells.setLayout(new GridLayout(10, 10));
		add(panelCells, BorderLayout.CENTER);
		txtEvent = new JTextArea();
		txtEvent.setEditable(false);
		txtEvent.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		//add(txtEvent, BorderLayout.SOUTH);
		//txtEvent.setPreferredSize(new Dimension(getWidth(), 100));
		txtEvent.setText("what will you do now?");
		
		panelInfo = new JPanel();
		panelInfo.setLayout(new GridLayout(1, 0));
		add(panelInfo, BorderLayout.SOUTH);
		panelInfo.setPreferredSize(new Dimension(getWidth(),200));
		
		JScrollPane scroll = new JScrollPane (txtEvent, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			panelInfo.add(scroll);
			
			
			playerInfo = new JTextArea();
			playerInfo.setEditable(false);
			panelInfo.add(playerInfo);
			
			panelMenu = new JPanel();
			panelMenu.setLayout(new GridLayout(0,1));
			panelInfo.add(panelMenu);
			
		
		
	}
	
	public void addMenuButton(JButton btn)
	{
		panelMenu.add(btn);
	}
	public  void addcell(JButton cell) {
		panelCells.add(cell);
	}
	
	public static void main (String [] args)
	{
		//WorldView r = new WorldView();
		//r.setVisible(true);
	}


	public JPanel getPanelCells() {
		return panelCells;
	}


	public void setPanelCells(JPanel panelCells) {
		this.panelCells = panelCells;
	}


	public String getTxt() {
		return txtEvent.getText();
	}


	public void updateTxtEvent(String txtEvent) {
		this.txtEvent.setText(txtEvent);
	}


	public JTextArea getPlayerInfo() {
		return playerInfo;
	}


	public void setPlayerInfo(JTextArea playerInfo) {
		this.playerInfo = playerInfo;
	}

	public JPanel getPanelInfo() {
		return panelInfo;
	}

	public void setPanelInfo(JPanel panelInfo) {
		this.panelInfo = panelInfo;
	}

	public JPanel getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(JPanel panelMenu) {
		this.panelMenu = panelMenu;
	}






}
