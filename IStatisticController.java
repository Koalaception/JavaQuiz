import java.util.*;

public interface IStatisticController {
	
	public IO test=new IO();
	
	public HashMap<Question,String> map=new HashMap<Question,String>();
	
	public List<Question> fragen=test.readQuestions("Java 2014 - Fragen.csv");
	
	public void initialize(String fragen);
	
	public boolean addDataSet(Question question, String givenAnswer);
	
	public Question getQuestion();
	
	public int getNumberOfQuestions();
	
	public int getRightAnswers();
	
	public int getWrongAnswers();
	public String getString(Question frage, int a);
	
	public Map<Question,String> getAnswers();
}