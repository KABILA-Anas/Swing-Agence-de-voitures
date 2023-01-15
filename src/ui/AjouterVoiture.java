package ui;

import gestionLocations.Agence;
import gestionLocations.Voiture;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterVoiture extends JPanel {

    JLabel titre,marque,model,prix,annee;
    JTextField tmarque,tmodel,tprix,tannee;
    JButton submit,reset;
    JPanel Form = new JPanel();
    String vmarque,vmodel;
    int vannee,vprix;
    private LouerVoiture LV;
    private RendreVoiture RV;

    public AjouterVoiture(Agence A,JFrame myContent){
        //super("Ajouter voiture");
        Font f1 = new Font("Bold",Font.BOLD,18);
        Font f2 = new Font("Verdana",Font.PLAIN,16);
        //Initialisation
        titre = new JLabel("Ajouter nouvelle voiture");
        marque = new JLabel("Marque");
        model = new JLabel("Modèle");
        prix = new JLabel(" Prix");
        annee = new JLabel(" Annee");

        tmarque  = new JTextField();
        tmarque.setPreferredSize(new Dimension(210, 30));
        tmodel  = new JTextField();
        tmodel.setPreferredSize(new Dimension(210, 30));
        tprix  = new JTextField();
        tprix.setPreferredSize(new Dimension(210, 30));
        tannee  = new JTextField();
        tannee.setPreferredSize(new Dimension(210, 30));

        submit = new JButton("Enregistrer");
        submit.setBackground(Color.GREEN);
        submit.setPreferredSize(new Dimension(200, 30));
        reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(200, 30));
        reset.setBackground(Color.LIGHT_GRAY);
        //Customizing
        titre.setFont(f1);
        marque.setFont(f2);
        model.setFont(f2);
        prix.setFont(f2);
        annee.setFont(f2);
        //
        JPanel ptitre = new JPanel();
        ptitre.add(titre,BorderLayout.CENTER);
        //
        JPanel pmarque = new JPanel();
        pmarque.add(marque,BorderLayout.CENTER);
        pmarque.add(pmarque.add(Box.createHorizontalStrut(80)));
        pmarque.add(tmarque,BorderLayout.SOUTH);
        //
        JPanel pmodel = new JPanel();
        pmodel.add(model,BorderLayout.CENTER);
        pmodel.add(pmodel.add(Box.createHorizontalStrut(80)));
        pmodel.add(tmodel,BorderLayout.SOUTH);
        //
        JPanel pprix = new JPanel();
        pprix.add(prix,BorderLayout.CENTER);
        pprix.add(pprix.add(Box.createHorizontalStrut(100)));
        pprix.add(tprix,BorderLayout.SOUTH);
        //
        JPanel pannee = new JPanel();
        pannee.add(annee,BorderLayout.CENTER);
        pannee.add(pannee.add(Box.createHorizontalStrut(80)));
        pannee.add(tannee,BorderLayout.SOUTH);
        //
        JPanel pbuttons = new JPanel();
        pbuttons.add(reset,BorderLayout.CENTER);
        pbuttons.add(pbuttons.add(Box.createHorizontalStrut(20)));
        pbuttons.add(submit,BorderLayout.SOUTH);
        //
        Form = new JPanel();
        Form.setLayout(new BoxLayout(Form, BoxLayout.Y_AXIS));
        //Borders
        Form.setBorder(new BevelBorder(BevelBorder.RAISED) );
        submit.setBorder(BorderFactory.createLineBorder(Color.RED));
        reset.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        //
        Form.add(ptitre);
        Form.add(Form.add(Box.createVerticalStrut(40)));
        Form.add(pmarque);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pmodel);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pprix);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pannee);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pbuttons);
        //Form.add(Form.add(Box.createVerticalStrut(10)));
        //Actions
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                try {
                    vmarque = tmarque.getText();
                    if (vmarque.isEmpty())
                        throw new MarqueEmptyException();
                    vmodel = tmodel.getText();
                    if (vmodel.isEmpty())
                        throw new ModelEmptyException();
                    if (tprix.getText().isEmpty())
                        throw new PrixEmptyException();
                    vprix = Integer.parseInt(tprix.getText());
                    if (tannee.getText().isEmpty())
                        throw new AnneeEmptyException();
                    vannee = Integer.parseInt(tannee.getText());
                    A.ajouterVoiture(new Voiture(vmarque, vmodel, vannee, vprix));
                    LV.removeAll();
                    RV.removeAll();
                    LouerVoiture LVC = new LouerVoiture(myContent, A);
                    RendreVoiture RVC = new RendreVoiture(A,  myContent);
                    
                    LVC.setRV(RVC);
                    RVC.setLV(LVC);
                    
                    //LV.setRV(RVC);
					LV.add(LVC);
					//RV.setLV(LVC);
					RV.add(RVC);
					LV.revalidate();
					LV.repaint();
					RV.revalidate();
					RV.repaint();

					JOptionPane.showMessageDialog(myContent, "Voiture est ajouté");
                    tmarque.setText("");
                    tmodel.setText("");
                    tprix.setText("");
                    tannee.setText("");
                }catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(myContent, "Annee et prix doivent etre integers" ,"Fatal error",JOptionPane.ERROR_MESSAGE);
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(myContent, exception ,"Fatal error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               tmarque.setText("");
               tmodel.setText("");
               tprix.setText("");
               tannee.setText("");
            }
        });
        //
        JPanel FormContainer = new JPanel();
        FormContainer.setLayout(new BoxLayout(FormContainer, BoxLayout.Y_AXIS));
        FormContainer.add(FormContainer.add(Box.createVerticalStrut(20)));
        FormContainer.add(Form);
        this.add(FormContainer,BorderLayout.NORTH);
        setSize(600, 300);
        setVisible(true);

    }
    
    public void setLV(LouerVoiture LVC) {
		LV = LVC;
	}
    
    public void setRV(RendreVoiture RVC) {
		RV = RVC;
	}

    class MarqueEmptyException extends Exception{
        @Override
        public String toString() {
            return "Marque est un champ essentiel!!!ne peut etre vide";
        }
    }
    class ModelEmptyException extends Exception{
        @Override
        public String toString() {
            return "Model est un champ essentiel!!!ne peut etre vide";
        }
    }
    class PrixEmptyException extends Exception{
        @Override
        public String toString() {
            return "Prix est un champ essentiel!!!ne peut etre vide";
        }
    }
    class AnneeEmptyException extends Exception{
        @Override
        public String toString() {
            return "Annee est un champ essentiel!!!ne peut etre vide";
        }
    }
    
    /*public static void main(String[] args) {
        VoitureForm2 voitureForm = new VoitureForm2();
    }*/

}



