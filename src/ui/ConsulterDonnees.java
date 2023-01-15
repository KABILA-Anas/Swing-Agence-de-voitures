package ui;




import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import criteres.*;
import gestionLocations.Agence;

public class ConsulterDonnees extends JPanel {
	
	private JPanel mainPanel;
	private VoituresPanel VP;
	private ClientsPanel CP;
	private LocationsPanel LP;
	private ConsultTypes CT;
	private JLabel inputs;
	private JTextField marque;
	private JTextField annee;
	private JTextField prix;
	private JButton search;
	private InterCritere IC = new InterCritere();
	
	public ConsulterDonnees(Agence A,Container myContent) {
		this.setLayout(new BorderLayout());
		CT = new ConsultTypes();
		VP = new VoituresPanel(A.selectionne(IC));
		
		inputs = new JLabel("Choix des criteres :");
		marque = new JTextField("Enter une marque");
		annee = new JTextField("Enter l'annee de production");
		prix = new JTextField("Enter un prix maximale");
		search = new JButton("Search");
		
		search.setBackground(Color.DARK_GRAY);
		search.setForeground(Color.WHITE);
		
		//inputs.setSize(new Dimension(300, 100));
		marque.setMaximumSize(new Dimension(310, 30));
		annee.setMaximumSize(new Dimension(310, 30));
		prix.setMaximumSize(new Dimension(310, 30));
		
		/*marque.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e){
				if(marque.getText().startsWith("Enter une marque"))
					marque.setText("");
            }
		});*/
		
		marque.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(marque.getText().startsWith("Enter une marque"))
					marque.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(marque.getText().isEmpty())
					marque.setText("Enter une marque");
			}
			
		});
		
		annee.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(annee.getText().startsWith("Enter l'annee de production"))
					annee.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(annee.getText().isEmpty())
					annee.setText("Enter l'annee de production");
			}
			
		});
		
		prix.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(prix.getText().startsWith("Enter un prix maximale"))
					prix.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(prix.getText().isEmpty())
					prix.setText("Enter un prix maximale");
			}
			
		});


		
		
		JPanel P = new JPanel();
		P.setLayout(new BoxLayout(P, BoxLayout.Y_AXIS));
		P.setBackground(Color.LIGHT_GRAY);
		//P.setAlignmentY(LEFT_ALIGNMENT);
		
		P.add(P.add(Box.createVerticalStrut(15)));
		P.add(inputs);
		P.add(Box.createVerticalStrut(10));
		P.add(marque);
		P.add(P.add(Box.createVerticalStrut(5)));
		P.add(annee);
		P.add(P.add(Box.createVerticalStrut(5)));
		P.add(prix);
		P.add(P.add(Box.createVerticalStrut(10)));
		P.add(search);
		add("West", P);
		add("North", CT);
		add("Center", VP);

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					IC = new InterCritere();
					String m, a, p;
					m = marque.getText();
					a = annee.getText();
					p = prix.getText();
					if(!m.startsWith("Enter une marque"))
						IC.addCritere(new CritereMarque(m));
					if(!a.startsWith("Enter l'annee de production"))
						IC.addCritere(new CritereAnneeProd(Integer.parseInt(a)));
					if(!p.startsWith("Enter un prix maximale"))
						IC.addCritere(new CriterePrix(Integer.parseInt(p)));
					
					removeAll();
					VP = new VoituresPanel(A.selectionne(IC));
					add("West", P);
					add("North", CT);
					add("Center", VP);
				}catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(myContent,"Annee et prix doivent etre integers" ,"Fatal error",JOptionPane.ERROR_MESSAGE);;
				}    
				

			}

		});
		
		CT.getVoitures().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*remove(VP);
				remove(P);
				VP = new VoituresPanel(A.lesVoituresLouees());
				add("Center", VP);*/
				removeAll();
				VP = new VoituresPanel(A.selectionne(IC));
				add("West", P);
				add("North", CT);
				add("Center", VP);
			}
			
		});
		
		CT.getVoituresLoue().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*remove(VP);
				remove(P);
				VP = new VoituresPanel(A.lesVoituresLouees());
				add("Center", VP);*/
				removeAll();
				VP = new VoituresPanel(A.lesVoituresLouees());
				add("North", CT);
				add("Center", VP);
			}
			
		});
		
		CT.getClients().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*remove(VP);
				remove(P);
				VP = new VoituresPanel(A.lesVoituresLouees());
				add("Center", VP);*/
				removeAll();
				CP = new ClientsPanel(A.lesClients());
				add("North", CT);
				add("Center", CP);
			}
			
		});
		
		CT.getLocations().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*remove(VP);
				remove(P);
				VP = new VoituresPanel(A.lesVoituresLouees());
				add("Center", VP);*/
				removeAll();
				LP = new LocationsPanel(A.lesLocations());
				add("North", CT);
				add("Center", LP);
			}
			
		});

	}
	
	
	
}
	

