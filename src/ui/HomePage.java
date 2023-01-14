package ui;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.*;

import gestionLocations.Agence;
import gestionLocations.Civilite;
import gestionLocations.Client;
import gestionLocations.Voiture;
import gestionLocations.Agence.ClientEstLoueurException;
import gestionLocations.Agence.VoitureEstLoueException;
import gestionLocations.Agence.VoitureNotFoundException;

public class HomePage extends JFrame {
	
	private Container myContent;
	private  JTabbedPane header;
	private ConsulterDonnees CD;
	private AjouterVoiture AV;
	private LouerVoiture LV;
	private RendreVoiture RV;
	
	public HomePage(Agence A) {
		super("Agence de voitures");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myContent = this.getContentPane();
		
		CD  = new ConsulterDonnees(A);
		AV = new AjouterVoiture(A,this);
		RV = new RendreVoiture(A, this);
		LV = new LouerVoiture(this, A);
		
		RV.setLV(LV);
		LV.setRV(RV);
		//RV.setLV(LV);
		AV.setLV(LV);
		AV.setRV(RV);
		

		//myContent.setLayout(getLayout());
		header = new JTabbedPane();
		header.addTab("Consulter les donnees", CD);
		header.addTab("Ajouter une voiture", AV);
		header.addTab("Louer une voiture", LV);
		header.addTab("Rendre une voiture", RV);
		myContent.add(header);
		//this.pack();
		this.setSize(900, 500);
		this.setLocation(200, 50);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		

		Voiture v1, v2, v3, v4, v5, v6;
		v1 = new Voiture("BMW", "M7", 2014, 95);
		v2 = new Voiture("Mercedes", "Benz 1.6", 2016, 400);
		v3 = new Voiture("Dacia", "DK", 2012, 90);
		v4 = new Voiture("Maruti", "800 AC", 2007, 90);
		v5 = new Voiture("Renault", "RXT", 2016, 90);
		v6 = new Voiture("Mercedes", "Benz 1.8", 2017, 400);
		ArrayList<Voiture> V = new ArrayList<Voiture>();
		V.add(v1);
		V.add(v2);
		V.add(v3);
		V.add(v4);
		V.add(v5);
		V.add(v6);
		Agence agence = new Agence(V);
		Client c1, c2, c3;
		c1 = new Client("KABILA", "Anas", "cin1", Civilite.M);
		c2 = new Client("JRAIFY", "Yassine", "cin2", Civilite.M);
		c3 = new Client("ELON", "Musk", "cin3", Civilite.M);
		
		try {
			agence.loueVoiture(c1, v1);
			agence.loueVoiture(c2, v2);
			agence.loueVoiture(c3, v3);
			//System.out.println("hello");
		} catch (VoitureNotFoundException | VoitureEstLoueException | ClientEstLoueurException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//ConsulterDonnees CD = new ConsulterDonnees(CT, agence);
		HomePage myHomePage = new HomePage(agence);
	}
	
}
