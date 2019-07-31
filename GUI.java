import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.awt.*;
import javax.swing.*;
import java.awt.Font;
 

public class GUI extends JFrame implements ActionListener
{
	private TextUI Quiz =new TextUI();
	
	private JButton newGame,buttonFinish,buttonWeiter,buttonFragenLaden,buttonStatistik, buttonClose, buttonRundenHoch, buttonRundenRunter,buttonantwortzwei
	 ,buttonantwortdrei,buttonantworteins,buttonantwortvier,buttonHaupt;
	
	private JLabel labelSpielername,labelEinstellung,labelFiller6,labelFiller8,labelFiller7,
	labelFiller,labelFiller2,labelFiller3,labelFiller4,labelFiller5,labelVerwaltungzwei,
	labelName,label,labelNewGame,labelQuiz, labelRunden, labelVerwaltung;
	
	private JTextField  textSpielername, textFragen;
    
	private JPanel panel,panelWest,panelCenter,panelEast;
	
	private int n=0;
	private int Runden= 4;
	
	private JPanel panelGame= new JPanel(new GridLayout(6,5));
	private JPanel	endScreen = new JPanel (new GridLayout(8,8));
	private boolean anfang=false;
    private Question saver;
	private  Question helper;
	private	String gegebeneAntwort=new String();
	
	/**
	Konstruktur der GUI
	
	Erzeugt eine GUI-Klasse und startet sofort das Hauptmenü der Klasse
	*/
    public  GUI(){
        this.setTitle("ActionListener Beispiel");
        this.setSize(500, 500);
		homeScreen();}

	/**
	Erzeugt das Hauptmenü des Quizes
	
	Klasse erzeugt das Hauptmenü in dem die Fragen geladen werden können
	,das Quiz gestartet werden kann, die Rundenanzahl angegeben werden kann sowie
	ein Spielername eingegeben werden kann.
	*/
	
	private void homeScreen() {
		if (n != 0 )  {
			n=0;
			panel.removeAll();
			panelGame.removeAll();
			endScreen.removeAll();
		}
        panel = new JPanel( new BorderLayout());
		panelWest = new JPanel (new GridLayout(10,10));
		panelCenter = new JPanel (new GridLayout(10,10));
        labelQuiz = new JLabel("Uniquiz");
		labelRunden = new JLabel ("Anzahl der Runden: "+Runden);
		buttonRundenHoch = new JButton ("Mehr Runden");
		buttonRundenRunter = new JButton ("Weniger Runden");
		panelEast=new JPanel(new GridLayout(10,10));
		labelVerwaltung = new JLabel ("Datei lesen:");
		labelVerwaltungzwei= new JLabel ("Verwaltung");
		labelName = new JLabel ("Spielernamen eingeben: ");
		labelFiller = new JLabel ();labelFiller2 = new JLabel ();labelFiller3 = new JLabel ();
		labelFiller4 = new JLabel ();labelFiller5 = new JLabel ();labelFiller6 = new JLabel ();
		labelFiller7 = new JLabel ();labelFiller8 = new JLabel ();
		textSpielername = new JTextField (Quiz.Spielername,15);
		labelEinstellung= new JLabel ( "Einstellungen :");
		textFragen = new JTextField ("Java 2014 - Fragen.csv");	
		buttonFragenLaden= new JButton ("Lade Fragen");
		newGame = new JButton("Neues Spiel");
		buttonClose = new JButton ("Beenden");
        newGame.addActionListener(this);
        buttonFragenLaden.addActionListener(this);
		buttonRundenHoch.addActionListener(this);
		buttonRundenRunter.addActionListener(this);
		buttonClose.addActionListener(this);
		panelWest.add(labelFiller7);
		panelWest.add(labelVerwaltungzwei);
		panelWest.add(labelVerwaltung);
		panelWest.add(textFragen);
		panelWest.add(buttonFragenLaden);
		panelCenter.add(labelFiller);
		panelCenter.add(labelFiller2);
		panelCenter.add(labelFiller3);
		panelCenter.add(newGame);
		panelCenter.add(labelFiller4);
		panelCenter.add(labelFiller5);
		panelWest.add(labelName);
		panelWest.add(textSpielername);
		panelEast.add(labelFiller8);
		panelEast.add(labelEinstellung);
		panelEast.add(labelRunden);
		panelEast.add(buttonRundenHoch);
		panelEast.add(buttonRundenRunter);
		labelFiller4.setForeground (Color.RED);
		panel.add(labelQuiz, BorderLayout.NORTH);
		panel.add(panelWest, BorderLayout.WEST);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelEast, BorderLayout.EAST);
		panel.add(buttonClose, BorderLayout.SOUTH);
        this.add(panel);
		newGame.setVisible(false);
		System.out.println("home");
		Quiz.Spielername= textSpielername.getText();
		anfang=false;
	}
    
	/**
	Methode actionPerformed bearbeitet alle Button eingaben
	
	actionPerformed implementiert alle Butoneingaben und führt
	die gewünschte Aktion aus
	*/

	public void actionPerformed (ActionEvent ae){
		if(ae.getSource() == this.buttonFragenLaden){
			start();
			if (Quiz.IST.getNumberOfQuestions() != 0){
				labelFiller4.setText("");
				newGame.setVisible(true);
				newGame.setBackground(Color.GREEN);
			}
			else{		newGame.setVisible(false);
				labelFiller4.setText("Falsche Datei!!!");}
		}
        if(ae.getSource() == this.newGame){
			Quiz.Spielername=textSpielername.getText();
			if (Quiz.Spielername.equals("")){
				labelFiller4.setText("Kein Spielername!!!");
			}
			else {labelFiller4.setText("Falsche Datei!!!");
				panel.setVisible(false);
				panel.removeAll();
				this.repaint();
				start();
				newGame();
			}
        }
        else if (ae.getSource() == this.buttonStatistik){
            Quiz.IST.test.saveResult(Quiz.IST,Quiz.Spielername);
        }
		else if (ae.getSource() == this.buttonClose){
            System.exit(0);
        }
		else if (ae.getSource() == this.buttonRundenRunter){
			if ( Runden>1) {
				Runden--;
				String rundenanzahl= "Anzahl der Runden "+Runden;
				labelRunden.setText(rundenanzahl);
			}
		}
		 else if (ae.getSource() == this.buttonRundenHoch){
			Runden++;
			String rundenanzahl= "Anzahl der Runden "+Runden;
			labelRunden.setText(rundenanzahl);
		}
		else if (ae.getSource() ==  this.buttonantworteins){
			gegebeneAntwort=buttonantworteins.getText();
			Quiz.IST.addDataSet(helper, gegebeneAntwort);
			System.out.println("check");
			buttonantworteins.setBackground(Color.RED);
			background();
			buttonWeiter.setVisible(true);
		}
		else if (ae.getSource() ==  this.buttonantwortzwei){
			gegebeneAntwort=buttonantwortzwei.getText();
			Quiz.IST.addDataSet(helper, gegebeneAntwort);
			System.out.println("check");
			buttonantwortzwei.setBackground(Color.RED);
			background();
			buttonWeiter.setVisible(true);
		}
		else if (ae.getSource() ==  this.buttonantwortdrei){
			gegebeneAntwort=buttonantwortdrei.getText();
			Quiz.IST.addDataSet(helper, gegebeneAntwort);
			System.out.println("check");
			buttonantwortdrei.setBackground(Color.RED);
			background();
			buttonWeiter.setVisible(true);
		}
		else if (ae.getSource() ==  this.buttonantwortvier){
			gegebeneAntwort=buttonantwortvier.getText();
			Quiz.IST.addDataSet(helper, gegebeneAntwort);
			buttonantwortvier.setBackground(Color.RED);
			System.out.println("check");
			background();
			buttonWeiter.setVisible(true);
		}
		else if (ae.getSource() ==  this.buttonWeiter){
			panelGame.removeAll();
			newGame();}
			else if (ae.getSource() ==  this.buttonHaupt){
				anfang=true;
				endScreen();
			}
		}

	/**
	Methode newGame() startet das Quiz
	
	newGame() sucht eine Frage aus der Fragenliste und gibt diese auf
	der GUI aus.
	*/
    
	private void newGame () {
		n++;
		panelGame.setVisible(true);
		System.out.println("newGame");
		if (n<=Quiz.Rundenanzahl) {
			helper = Quiz.IST.getQuestion();
			saver=helper;
			JButton Game = new JButton("Neues Spiel");
			String frage=Quiz.IST.getString(helper,0);
			String antworteins=Quiz.IST.getString(helper,1);
			String antwortzwei=Quiz.IST.getString(helper,2);
			String antwortdrei=Quiz.IST.getString(helper,3);
			String antwortvier=Quiz.IST.getString(helper,4);
			JLabel fragelabel = new JLabel(frage); 
			buttonFinish = new JButton ( "Quiz auswerten");
			buttonWeiter = new JButton ("Weiter");
			buttonantworteins=new JButton(antworteins);
			buttonantwortzwei=new JButton(antwortzwei);
			buttonantwortdrei=new JButton(antwortdrei);
			buttonantwortvier=new JButton(antwortvier);
			buttonWeiter.addActionListener(this);
			buttonantworteins.addActionListener(this);
			buttonantwortzwei.addActionListener(this);
			buttonantwortdrei.addActionListener(this);
			buttonantwortvier.addActionListener(this);
			buttonFinish.addActionListener(this);
			panelGame.add(fragelabel);
			panelGame.add(buttonantworteins);
			panelGame.add(buttonantwortzwei);
			panelGame.add(buttonantwortdrei);
			panelGame.add(buttonantwortvier);
			panelGame.add(buttonWeiter);
			buttonWeiter.setVisible(false);
			this.add(panelGame);
			System.out.println(Quiz.IST.getRightAnswers());
			System.out.println(Quiz.IST.getWrongAnswers());
		}
		if (Quiz.Rundenanzahl == n) {
			panelGame.setVisible(false);
			panelGame.removeAll();
			panelGame.repaint();
			endScreen();
		}
	}
	
	/**
	Methode start initialisiert die Fragenliste
	*/
	private void start () {
		Quiz.Rundenanzahl=Runden+1;
		Quiz.IST.initialize(textFragen.getText());
	}
	 
	 /**
	 Methode background() ändert die HIntergrundfarbe der Buttons
	 entsprechend einer richtige oder falschen Antwort
	 */
	private void background () {
		if (saver.checkAnswer(buttonantworteins.getText())) {
			buttonantworteins.setBackground(Color.GREEN);
		}
		if (saver.checkAnswer(buttonantwortzwei.getText())) {
	 		buttonantwortzwei.setBackground(Color.GREEN);
		}
		if (saver.checkAnswer(buttonantwortdrei.getText())) {
	 		buttonantwortdrei.setBackground(Color.GREEN);
		}
		if (saver.checkAnswer(buttonantwortvier.getText())) {
	 		buttonantwortvier.setBackground(Color.GREEN);
		}
	}
	/**
	Methode endScreen() erzeugt das zusammenfassende GUI-Fenster
	
	In diesem kann man zurück zum Hauptmenü gehen oder die Statistik speichern
	*/
	private void endScreen() {
		endScreen.setVisible(true);
		if (anfang ==false)  {
			JLabel ende = new JLabel("Ende");
			JLabel spielstand = new JLabel("Spielstand:");
			JLabel richtigeant = new JLabel("Richtige Antworten:");
			JLabel falscheant = new JLabel("Falsche Antworten:");
			String falsch = String.valueOf(Quiz.IST.getWrongAnswers());
			String richtig = String.valueOf(Quiz.IST.getRightAnswers());
			JLabel richtige = new JLabel(richtig);
			buttonHaupt= new JButton("Hauptmenü");
			JLabel falsche = new JLabel(falsch);
			System.out.println("endScreen");
			buttonStatistik = new JButton ("Statistic speichern?");
			buttonStatistik.addActionListener(this);
			buttonHaupt.addActionListener(this);
			endScreen.add(ende);
			endScreen.add(spielstand);
			endScreen.add(richtigeant);
			endScreen.add(richtige);
			endScreen.add(falscheant);
			endScreen.add(falsche);
			endScreen.add(buttonStatistik);
			endScreen.add(buttonHaupt);
			this.add(endScreen);
		}
		if (anfang) {
			endScreen.setVisible(false);
			homeScreen();
		}
	}

	
}
