package game;

public class StructQuestion {

    public int     id;
    public String  text;
    public boolean answer;
    public int     level;
    public int     type;


    public StructQuestion(int ID, String Text, boolean Answer, int Level, int Type)
    {
        id = ID;
        text = Text;
        answer = Answer;
        level = Level;
        type = Type;
    }
}