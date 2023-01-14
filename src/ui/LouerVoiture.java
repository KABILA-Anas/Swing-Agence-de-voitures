package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import gestionLocations.*;
import ui.AjouterVoiture.AnneeEmptyException;
import ui.AjouterVoiture.MarqueEmptyException;
import ui.AjouterVoiture.ModelEmptyException;
import ui.AjouterVoiture.PrixEmptyException;

public class LouerVoiture extends JPanel {

	public LouerVoiture(JFrame myframe , Agence A, RendreVoiture RV) {
	
		LouerVoiture copy = this;
		
		Iterator<Voiture> I = (A.lesVoituresNonLouees()).iterator();
	
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
		columns.add(new JPanel());
		columns.setBackground(Color.DARK_GRAY);
		
		this.add(Box.createVerticalStrut(15));
		this.add(columns);
		
		int i=1;
		while(I.hasNext()) {
			Voiture V = (Voiture)I.next();
			JButton Louer = new JButton("  Louer  ");
			Louer.setName(Integer.toString(i++));
			JPanel tmp = new JPanel();
			tmp.setMaximumSize(new Dimension(600, 35));
			tmp.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
			tmp.setLayout(new GridLayout(1, 4));
			tmp.add(new JLabel(V.getMarque()));
			tmp.add(new JLabel(V.getModele()));
			tmp.add(new JLabel(Integer.toString(V.getAnnee_production())));
			tmp.add(new JLabel(Integer.toString(V.getPrix())));
			tmp.add(Louer);
			this.add(tmp);
			
			Louer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					
					//Voiture à louer
					int Vnumber = Integer.parseInt(((JButton)e.getSource()).getName());
					List<Voiture> vnl = A.lesVoituresNonLouees();
					Louer(myframe,A,vnl.get(Vnumber-1));
					removeAll();
					RV.removeAll();
					RV.add(new RendreVoiture(A, copy, myframe));
					RV.revalidate();
					RV.repaint();
					add(new LouerVoiture(myframe, A, RV));
					revalidate();
					repaint();
					
					/*removeAll();
					add(new LouerVoiture(A));
					revalidate();
					repaint();*/
				}
				
			});
		}
		
	}
	
	
	public static void Louer(JFrame myframe,Agence A,Voiture V) {
		
		final JDialog modelDialog = new JDialog(myframe, "Ajouter Client",true);
		modelDialog.setDefaultCloseOperation(
			    JDialog.HIDE_ON_CLOSE);
		Container myContent = modelDialog.getContentPane();
		
		//Declaration;
		JLabel titre,nom,prenom,cin,civilte;
	    JTextField tnom,tprenom,tcin;
	    JComboBox tcivilte;
	    JButton submit,reset;
	    JPanel Form = new JPanel();
	    
	    
	    Font f1 = new Font("Bold",Font.BOLD,18);
        Font f2 = new Font("Verdana",Font.PLAIN,16);
        //Initialisation
        titre = new JLabel("Ajouter Client");
        nom = new JLabel("nom     ");
        prenom = new JLabel("prenom");
        cin = new JLabel("cin  ");
        civilte = new JLabel("civilte  ");

        tnom  = new JTextField();
        tnom.setPreferredSize(new Dimension(210, 30));
        tprenom  = new JTextField();
        tprenom.setPreferredSize(new Dimension(210, 30));
        tcin  = new JTextField();
        tcin.setPreferredSize(new Dimension(210, 30));
        String[] civilities = {"M","Mme","Mlle"};
        tcivilte  = new JComboBox(civilities);
        tcivilte.setPreferredSize(new Dimension(210, 30));

        submit = new JButton("Enregistrer");
        submit.setBackground(Color.GREEN);
        submit.setPreferredSize(new Dimension(100, 30));
        reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(100, 30));
        reset.setBackground(Color.LIGHT_GRAY);
        //Customizing
        titre.setFont(f1);
        nom.setFont(f2);
        prenom.setFont(f2);
        cin.setFont(f2);
        civilte.setFont(f2);
        //
        JPanel ptitre = new JPanel();
        ptitre.add(titre,BorderLayout.CENTER);
        //
        JPanel pnom = new JPanel();
        pnom.add(nom,BorderLayout.CENTER);
        pnom.add(pnom.add(Box.createHorizontalStrut(80)));
        pnom.add(tnom,BorderLayout.SOUTH);
        //
        JPanel pprenom = new JPanel();
        pprenom.add(prenom,BorderLayout.CENTER);
        pprenom.add(pprenom.add(Box.createHorizontalStrut(80)));
        pprenom.add(tprenom,BorderLayout.SOUTH);
        //
        JPanel pcin = new JPanel();
        pcin.add(cin,BorderLayout.CENTER);
        pcin.add(pcin.add(Box.createHorizontalStrut(110)));
        pcin.add(tcin,BorderLayout.SOUTH);
        //
        JPanel pcivilte = new JPanel();
        pcivilte.add(civilte,BorderLayout.CENTER);
        pcivilte.add(pcivilte.add(Box.createHorizontalStrut(90)));
        pcivilte.add(tcivilte,BorderLayout.SOUTH);
        //
        JPanel pbuttons = new JPanel();
        pbuttons.add(reset,BorderLayout.CENTER);
        pbuttons.add(pbuttons.add(Box.createHorizontalStrut(20)));
        pbuttons.add(submit,BorderLayout.SOUTH);
        //
        Form = new JPanel();
        Form.setLayout(new BoxLayout(Form, BoxLayout.Y_AXIS));
        Form.add(Form.add(Box.createVerticalStrut(30)));
        Form.add(ptitre);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pnom);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pprenom);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pcin);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pcivilte);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pbuttons);
		//
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String vnom,vprenom,vcin;
        	    Civilite vcivilte;
                try {
                    vnom = tnom.getText();
                    if (vnom.isEmpty())
                        throw new NomEmptyException();
                    vprenom = tprenom.getText();
                    if (vprenom.isEmpty())
                        throw new PrenomEmptyException();
                   
                    vcin = tcin.getText();
                    if (vcin.isEmpty())
                        throw new CinEmptyException();
                    
                    vcivilte =Civilite.valueOf(tcivilte.getSelectedItem().toString());
                    
                    Client cli = new Client(vnom,vprenom,vcin,vcivilte);
                    A.loueVoiture(cli, V);
                    
                    JOptionPane.showMessageDialog(myContent, vnom+" à bien loué "+V.getMarque());
                    modelDialog.dispose();
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(myContent, exception);
                }
            }
        });
        
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               tnom.setText("");
               tprenom.setText("");
               tcin.setText("");
               tcivilte.setSelectedIndex(0);
            }
        });
	    
        //
        modelDialog.add(Form,BorderLayout.NORTH);
		modelDialog.setSize(500,400);
		modelDialog.setLocation(300, 200);
		modelDialog.setResizable(false);
		modelDialog.setVisible(true);
	}
	
}


class NomEmptyException extends Exception{
    @Override
    public String toString() {
        return "Nom est un champ essentiel!!!ne peut etre vide";
    }
}
class PrenomEmptyException extends Exception{
    @Override
    public String toString() {
        return "Prenom est un champ essentiel!!!ne peut etre vide";
    }
}
class CinEmptyException extends Exception{
    @Override
    public String toString() {
        return "Cin est un champ essentiel!!!ne peut etre vide";
    }
}