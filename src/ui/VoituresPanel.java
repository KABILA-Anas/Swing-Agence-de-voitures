package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import gestionLocations.Voiture;

public class VoituresPanel extends JPanel {
	
	private JTable j;
	
	public VoituresPanel(Iterator<Voiture> I) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setBorder(new EtchedBorder (Color.BLACK, Color.BLACK));
		
		JPanel columns = new JPanel();
		columns.setMaximumSize(new Dimension(600, 30));
		columns.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
		columns.setLayout(new GridLayout(1, 4));
		JLabel Marque, Model, Annee, Prix;
		Marque = new JLabel("Marque");
		Marque.setForeground(Color.WHITE);
		Model = new JLabel("Model");
		Model.setForeground(Color.WHITE);
		Annee = new JLabel("Annee de production");
		Annee.setForeground(Color.WHITE);
		Prix = new JLabel("Prix");
		Prix.setForeground(Color.WHITE);
		columns.add(Marque);
		columns.add(Model);
		columns.add(Annee);
		columns.add(Prix);
		columns.setBackground(Color.DARK_GRAY);
		
		this.add(Box.createVerticalStrut(15));
		this.add(columns);
		
		while(I.hasNext()) {
			Voiture V = (Voiture)I.next();
			//GridBagConstraints gbc = new GridBagConstraints();
			JPanel tmp = new JPanel();
			tmp.setMaximumSize(new Dimension(600, 30));
			tmp.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
			tmp.setLayout(new GridLayout(1, 4));
			tmp.add(new JLabel(V.getMarque()));
			tmp.add(new JLabel(V.getModele()));
			tmp.add(new JLabel(Integer.toString(V.getAnnee_production())));
			tmp.add(new JLabel(Double.toString(V.getPrix())));
			this.add(tmp);
		}
	}
	
	
	
}
