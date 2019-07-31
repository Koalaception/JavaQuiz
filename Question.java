
public class Question {
	String[] question;
	int solution;
		
	public Question(String frage,String antworteins,String antwortzwei,String antwortdrei, String antwortvier,int a)  {
		question=new String[5];
		question[0]=frage;
		question[1]=antworteins;
		question[2]=antwortzwei;
		question[3]=antwortdrei;
		question[4]=antwortvier;
		solution=a+1;
	}
	
	/**
	Methode erzeugt aus übergebenem Question Objekt einen String
	Gibt den String auf der Konsole in Form :
	<Frage>
	<Antwort1>	<Antwort2>
	<Antwort3>	<Antwort4>
	*/
	public void tString() {
		System.out.println(question[0]+"\n\n"+question[1]+"\t"+question[2]+"\n"+question[3]+"\t"+question[4]);
	}
	
	/**
	checkAnswer überprüft ein Question Objekt
	
	Übergebene Antwort als String wird überprüft und zurückgegeben 
	ob die Antwort stimmt (true) oder falsch ist (false)
	
	@param String answer eingebene antwort
	@return boolean true falls richtig/false statt falsch
	*/
	public boolean checkAnswer (String answer) {
		return question[solution].equals(answer);
	}
}