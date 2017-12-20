package mvc;

import java.util.Observable;
import java.awt.*;
import javax.swing.*; 
import java.util.Observer;

import com.sun.java.swing.plaf.windows.resources.windows;

import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import librInterpreteur.Case;
import librInterpreteur.Grille;
import librInterpreteur.ListSymboles;
import librInterpreteur.Rail;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


@SuppressWarnings("unused")

public class VueControleur extends Application {

	// modèle : ce qui réalise le calcul de l'expression
    Modele m;
    // affiche la saisie et le résultat
    Text affichage;
    
    // Taille de la grille
    int longueur = 8;
    int largeur = 8;
    
    @Override
    public void start(Stage primaryStage) {
		// initialisation du modele que l'on souhaite utiliser
	    m = new Modele(longueur,largeur);
	    // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
	    BorderPane border = new BorderPane();
	    //int currRail = m.getHeadRail();
	    //Rail r = m.getRr(currRail);
	    // permet de placer les differents boutons dans une grille
	    GridPane gPane = new GridPane();
	    Button endGame = new Button("RESET");  // a  implementer plus tard
        Label state = new Label(""); // pour savoir si on a fait des UPDATES
        JFrame frame = new JFrame("WIN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel textLabel = new JLabel("WIN !!",SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));
        frame.getContentPane().add(textLabel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();
        
        endGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
        	@Override
         	public void handle(MouseEvent event) {
        		Rail r = m.getRr(m.getHeadRail());
        		m.fullReset();
        		r.deleteRail();
        		state.setText("");
         	}
        });
        
        
        // ###################### Placement des boutons ######################  //

        border.setTop(endGame);
        border.setBottom(state);
	    
	    for (int i = 0; i<longueur; i++) {
            for(int k = 0; k<largeur; k++) {
                // CREER DES CASES (QUI SONT DES BOUTTONS QUI AFFICHENT LES TYPES DES NOEUDS)
            	Case newCase = m.getGrid().getCaseTab(i, k);
                Button newCaseButton = newCase.getNewCase();
                
                gPane.add(newCaseButton, i, k); // placement des cases dans la grille
                
                newCaseButton.setOnDragOver(new EventHandler<DragEvent>() {
                	@Override
					public void handle(DragEvent arg0) {
                		Rail r = m.getRr(m.getHeadRail());
                		// si ce n'est pas un symbole 
						if(!m.getGrid().getSymbs().isSymbole(newCaseButton.getText())) {
							// ET qu'il n'appartient pas au Rail en cours
							if (!r.isInRail(newCase) && !newCase.isHoovered()) {
								newCaseButton.setText("*");
								r.addCase(newCase);
								r.railHead().setHoovered(true);
							} else if (r.isInRail(newCase) && m.getGrid().estVoisine(r.railHead(), newCase)) {
								r.railHead().getNewCase().setText("O");
								r.railHead().setHoovered(false);
             					r.deleteCase(r.railHead());
							}
                 		} else {
                 			if (m.getGrid().estVoisine(r.railHead(), newCase)) {
                 				r.addCase(newCase);
                 				r.railHead().setHoovered(true);
                 			}
                 		}
					}
                });
                newCaseButton.setOnDragDetected(new EventHandler<MouseEvent>() {
                	@Override
					public void handle(MouseEvent arg0) {
                		Rail r = m.getRr(m.getHeadRail());
                		Dragboard db;
                		if(m.getGrid().getSymbs().isSymbole(newCaseButton.getText())) {
                			db = newCaseButton.startDragAndDrop(TransferMode.ANY);
                			ClipboardContent content = new ClipboardContent();
    						if(!m.getGrid().getSymbs().isSymbole(newCaseButton.getText()) && !r.isInRail(newCase)) {
                     			newCaseButton.setText("*");
                     			r.addCase(newCase);
                     			r.railHead().setHoovered(true);
                     		} else { 
                     			if (!r.isInRail(newCase)) {
                     				r.addCase(newCase);
                     				r.railHead().setHoovered(true);
                     			}
                     		}
    						content.putString(newCaseButton.getText());
    						db.setContent(content);
                		}
					}
                });
                newCaseButton.setOnDragDone(new EventHandler<DragEvent>() {
                	@Override
					public void handle(DragEvent arg0) {
                		Rail r = m.getRr(m.getHeadRail());
                		Dragboard db = arg0.getDragboard();
                		boolean success = false;
                        if (db.hasString()) {
                        	newCaseButton.setText(db.getString());
                        	success = true;
                        }
            	        if(r.railTailStr() == r.railHeadStr() && r.isInRail(r.railTail()) && m.getGrid().getSymbs().isSymbole(r.railHeadStr()) && !r.railTail().isLinked()) {
            	        	if(m.getSizeOfVectRails() == m.getGrid().getDifficulty())
            	        	{
            	        		System.out.println("WIN");
            	        		primaryStage.setTitle("WIN");
            	        		state.setText("WIN");
            	        		frame.setVisible(true);
            	        	}
            	        	else {
	            	        	r.railTail().setLinked(true);
	            	        	r.railHead().setLinked(true);
	                    		m.addNewRail();
            	        	}
                		} else {
                			m.deleteRail(m.getHeadRail());
                		}
                		arg0.consume();
					}
                });
             }
          }
	    
	    
	    gPane.setGridLinesVisible(false);
        border.setCenter(gPane);
        Scene scene = new Scene(border, Color.LIGHTGREEN); // LA FENETRE
        
        
        primaryStage.setTitle("Line"); // LE NOM DE L'APPLICATION(FENETRE)
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	public static void main(String[] args) {
		launch(args);
	}

}
