package utilities;

public class StructPerson {

    public long   ID;
    public String username;
    public int    profileImage;
    public int    nickName;
    public int    normalMathClassicHighScore;
    public int    normalVocabClassicHighScore;
    public int    normalChallengeClassicHighScore;
    public int    normalMathTimerHighScore;
    public int    normalVocabTimerHighScore;
    public int    normalChallengeTimerHighScore;
    public int    reverseMathClassicHighScore;
    public int    reverseVocabClassicHighScore;
    public int    reverseChallengeClassicHighScore;
    public int    reverseMathTimerHighScore;
    public int    reverseVocabTimerHighScore;
    public int    reverseChallengeTimerHighScore;
    public int    specialGameHighScore;


    public StructPerson(long id, String Username, int ProfileImage, int NickName,
                        int NormalMathClassicHighScore, int NormalVocabClassicHighScore, int NormalChallengeClassicHighScore,
                        int NormalMathTimerHighScore, int NormalVocabTimerHighScore, int NormalChallengeTimerHighScore,
                        int ReverseMathClassicHighScore, int ReverseVocabClassicHighScore, int ReverseChallengeClassicHighScore,
                        int ReverseMathTimerHighScore, int ReverseVocabTimerHighScore, int ReverseChallengeTimerHighScore,
                        int SpecialGameHighScore)
    {
        ID = id;
        username = Username;

        profileImage = ProfileImage;
        nickName = NickName;
        normalMathClassicHighScore = NormalMathClassicHighScore;
        normalVocabClassicHighScore = NormalVocabClassicHighScore;
        normalChallengeClassicHighScore = NormalChallengeClassicHighScore;
        normalMathTimerHighScore = NormalMathTimerHighScore;
        normalVocabTimerHighScore = NormalVocabTimerHighScore;
        normalChallengeTimerHighScore = NormalChallengeTimerHighScore;
        reverseMathClassicHighScore = ReverseMathClassicHighScore;
        reverseVocabClassicHighScore = ReverseVocabClassicHighScore;
        reverseChallengeClassicHighScore = ReverseChallengeClassicHighScore;
        reverseMathTimerHighScore = ReverseMathTimerHighScore;
        reverseVocabTimerHighScore = ReverseVocabTimerHighScore;
        reverseChallengeTimerHighScore = ReverseChallengeTimerHighScore;
        specialGameHighScore = SpecialGameHighScore;

    }

}
