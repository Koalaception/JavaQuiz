import java.util.*;
 public class SimpleController implements IStatisticController {

	public IO test=new IO();
	public HashMap<Question,String> map=new HashMap<Question,String>();
	public List<Question> fragen=new ArrayList<Question>();
	public List<Question> gestelltefragen=new ArrayList<Question>();

	/**
	methode addDataSet speichert Antworten und gestellte fragen

	Übergebenes Question Objekt und die gegebene Antwort
	wird als map gespeichert

	@param Question question die gestellte frage die gespeichert wird
	@param String givenAnswer die gegebene Antwort auf die gestellte frage
	@return boolean boolean wert ob Antwort richtig oder falsch
	*/
	public boolean addDataSet(Question question, String givenAnswer) {
		boolean check=question.checkAnswer(givenAnswer);
		map.put(question,givenAnswer);
		return check;
	}
	
	/**
	Methode getQuestion sucht eine frage aus
	
	Ein zufälliges Question Objekt wird aus einer Liste ausgesucht 
	und übergeben

	@return Question zufällig ausgesuchtes Question Objekt
	*/
	public Question getQuestion() {
	int n=fragen.size();
	int random= (int) (Math.random()*n);
	map.put(fragen.get(random), null);
	gestelltefragen.add((fragen.get(random)));
	Question ausgabe=fragen.get(random);
	fragen.remove(random);
	return ausgabe;
	}
	
	public String getString(Question frage, int a) {
	return frage.question[a];}

	/**
	Erzeugt Liste und map

	Eine Liste und eine Map wird erzeugt , zur Speicherung
	aller Question Objekte  und zur Speicherung der gestellten Fragen sowie
	Antworten

	@param List<Question> questions Liste in die Alle Question Objekte kommen
	*/
	
	public void initialize(String fragenData) {
		fragen=test.readQuestions(fragenData);
		map=new HashMap<Question,String>();
	}
	
	/**
	Methode gibt ANzahl an Fragen in der Liste aus
	
	Gibt die größe der Liste der Fragen als integer Wert aus
	
	@return int Anzahl der Question Objekte in der ListenerThread
	*/

	public int getNumberOfQuestions(){
		return fragen.size();
	}
	/**
	Methode gibt Anzahl richtig beantworteter Fragen
  
	Alle Fragen die vom Spieler richtig beantwortet wurden werden als 
	integer Wert zurückgegeben
 
	@retrun int Anzahl richtig Antworten
	*/
	public int getRightAnswers(){
		int a=0;
	int n=gestelltefragen.size();
	for (int i=0;i<n;i++) {
		Question c=gestelltefragen.get(i);
		if (map.containsKey(c)==true){
			String check=(String)map.get(c);
			if (c.checkAnswer(check))	a++;
		}
	}
	return a;
	}
  
	/**
	Methode gibt Anzahl falsch beantworteter Fragen
  
	Alle Fragen die vom Spieler falsch beantwortet wurden werden als 
	integer Wert zurückgegeben
 
	@retrun int Anzahl falscher Antworten
	*/
	public int getWrongAnswers(){
		return map.size()-getRightAnswers()-1;
	}
	
	/**
	Methode erzeugt Map Objekt mit allen Antworten des Spielers
	als String value
	
	Methode erzeugt einen String der Form
	<Frage1><Antwort des Spielers1><falsch/richtig1>
	.
	.
	<Frage4><Antwort des Spielers4><falsch/richtig4>
	der String wird als Key "null" und dem Strin als value in 
	einer map gespeichert
	
	@return map mit String value
	*/
	
	public Map<Question,String> getAnswers(){
		String ausgabe=new String();
		for (int i=0;i<gestelltefragen.size();i++){
			if (map.containsKey(gestelltefragen.get(i))) {
				Question help=gestelltefragen.get(i);
				ausgabe=ausgabe+"\r\n"+help.question[0]+"\t"+map.get(gestelltefragen.get(i))+"\t"+help.checkAnswer(map.get(gestelltefragen.get(i)))+"\r\n";
			}
		}
		Question empty=null;
		map.put(empty,ausgabe);
		return map;
		}
	}