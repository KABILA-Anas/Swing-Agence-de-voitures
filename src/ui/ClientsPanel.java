package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import gestionLocations.Client;
import gestionLocations.Voiture;

public class ClientsPanel extends JPanel {

public ClientsPanel(Iterator<Client> I) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setBorder(new EtchedBorder (Color.BLACK, Color.BLACK));
		
		JPanel columns = new JPanel();
		columns.setMaximumSize(new Dimension(600, 30));
		columns.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
		columns.setLayout(new GridLayout(1, 4));
		JLabel Nom, Prenom, Cin, Civilite;
		Nom = new JLabel("Nom");
		Nom.setForeground(Color.WHITE);
		Prenom = new JLabel("Prenom");
		Prenom.setForeground(Color.WHITE);
		Cin = new JLabel("CIN");
		Cin.setForeground(Color.WHITE);
		Civilite = new JLabel("Civilite");
		Civilite.setForeground(Color.WHITE);
		columns.add(Nom);
		columns.add(Prenom);
		columns.add(Cin);
		columns.add(Civilite);
		columns.setBackground(Color.DARK_GRAY);
		
		this.add(Box.createVerticalStrut(15));
		this.add(columns);
		
		while(I.hasNext()) {
			Client C = (Client)I.next();
			//GridBagConstraints gbc = new GridBagConstraints();
			JPanel tmp = new JPanel();
			tmp.setMaximumSize(new Dimension(600, 30));
			tmp.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
			tmp.setLayout(new GridLayout(1, 4));
			tmp.add(new JLabel(C.getNom()));
			tmp.add(new JLabel(C.getPrenom()));
			tmp.add(new JLabel(C.getCin()));
			tmp.add(new JLabel(C.getCivilite()));
			this.add(tmp);
		}
	}
	
}
