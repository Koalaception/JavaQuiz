import java.util.*;
import java.io.*;
public class IO {
	/**
	readQuestions liest Fragen in eine ListenerThread

	Methode liest aus einer csv. datei die mit filename übergeben wird
	Fragen werden aus dieser datei gelesen und in eine Liste getan
	welche die Methode am ende ausgibt

	@param String filename name der auszulesenen Datei
	@return List Liste mit fragen und Antworten
	*/ 
	
	public static List<Question> readQuestions (String fileName) {
		List<Question> Fragenliste=new ArrayList<Question>();
		try  {
		Reader myReader = new FileReader( fileName );
		BufferedReader br=new BufferedReader(myReader);
		Scanner scanner = new Scanner ( myReader );
		String line=br.readLine();
		int i=0;
		while((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line, ",");
			if (st.countTokens() ==6) {
				String frage= st.nextToken();
				String antworteins = st.nextToken();
				String antwortzwei = st.nextToken();
				String antwortdrei = st.nextToken();
				String antwortvier = st.nextToken();
				String antwort = st.nextToken();
				int a= Integer.parseInt(antwort);
				Fragenliste.add(i,new Question(frage,antworteins,antwortzwei,antwortdrei,antwortvier,a));
				i++;
			}
		}
		scanner.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return Fragenliste;
	}
	
	/**
	saveResult speichert Ergebnisse in eine datei

	saveResult speichert die aus dem IStatisticController übergebenen 
	Daten und Ergebnisse in eine Datei namens "test.txt"
	Der spielername wird ebenfalls in der Datei notiert
	Speicherung der Form:
	Spielername:
	Richtige Antworten:
	Falsche Antworten:

	<Frage1><Antwort des Spielers1><falsch/richtig1>
	.
	.
	<Frage4><Antwort des Spielers4><falsch/richtig4>

	@param IStatisticController controller Daten zur Speicherung
	@param String playername Name des Spielers
	*/
	
	public static void saveResult(IStatisticController controller, String playername) {
		Writer fw = null;
		Map<Question,String> map=controller.getAnswers();
		String answers=map.get(null);
		try {
			fw=new FileWriter ("Statistiken.txt",true);
			fw.write("Spielername: " + playername+ "\r\n" +"Richtige Antworten: "+controller.getRightAnswers()+"\r\n"+"Falsche Antworten: "+(
			controller.getWrongAnswers()-1)+"\r\n"+answers);
			fw.close();
		}
		catch ( IOException e ) {
			System.err.println( "Konnte Datei nicht erstellen" );
		}
		finally {
		if ( fw != null )
			try { fw.close(); } catch ( IOException e ) { e.printStackTrace(); }
		}
	}

}