import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class TextUI {
	IStatisticController IST;
	int Rundenanzahl=5;
	String Spielername=new String();
	
	public TextUI () {
		IST=new SimpleController();
		}

	/**
	Methode playGame startet ein Quizspiel
	
	playGame() startet eine Quizrunde mit vordefinierter rundenanzahl
	*/
	
	public void playGame() {
		int maxRunden=Rundenanzahl;
		int runde=1;
		IST.initialize("Java 2014 - Fragen.csv");
		while (maxRunden >= runde) {
			System.out.println("Runde:"+runde+"\n");
			Question frage=IST.getQuestion();
			frage.tString();
			String eingabe = new java.util.Scanner( System.in ).nextLine();
				if(IST.addDataSet(frage,eingabe))System.out.println("\n"+"richtig"+"\n");
				else System.out.println("\n"+"falsch"+"\n");
			runde++;
		}
		IST.test.saveResult(IST,Spielername);}
	}
