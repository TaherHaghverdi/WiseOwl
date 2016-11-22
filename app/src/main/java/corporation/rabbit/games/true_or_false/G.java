package corporation.rabbit.games.true_or_false;

import game.StructQuestion;
import ir.adad.Adad;
import java.io.File;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.IabHelper;
import util.IabHelper.QueryInventoryFinishedListener;
import util.IabResult;
import utilities.ConnectionDetector;
import utilities.StructPerson;
import utilities.StructTop;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseInstallation;


// be score ha emtiaze modele vizhe ezafe she
/*
 * classic normal math -> 1
 * classic normal vocab -> 2
 * classic normal challenge -> 3
 * ---
 * timer normal math -> 4
 * timer normal vocab -> 5
 * timer normal challenge -> 6
 * ---
 * classic reverse math -> 7
 * classic reverse vocab -> 8
 * classic reverse challenge -> 9
 * ---
 * timer reverse math -> 10
 * timer reverse vocab -> 11
 * timer reverse challenge -> 12
 * ---
 */

public class G extends Application {

    //utilities
    public static SharedPreferences      preferences;
    public static Activity               currentPlace;
    public static Context                context;
    public static LayoutInflater         inflater;
    public static Handler                handler                          = new Handler();
    public static Typeface               gameFont;

    //array
    public static Vector<StructQuestion> ArrayQuestions;
    public static Vector<StructTop>      ArrayHighScores;

    //Database
    public static SQLiteDatabase         database;
    public static final String           DIR_SDCARD
            = Environment.getExternalStorageDirectory().getAbsolutePath();

    //setting & control
    public static int                    fontSize                         = 20;
    public static boolean                isFirstTime                      = false;
    public static boolean                isRegistered                     = false;
    public static boolean                onExitDialogShow                 = true;
    public static boolean                reverseMode                      = false;
    public static boolean                musicPlay                        = true;
    public static int                    questionType                     = 0;
    public static int                    coins                            = 200;
    public static int                    dialogChooseAction               = 0;
    public static int                    SnowGunBullets                   = 3;
    public static MediaPlayer            click;
    public static StructPerson           rival;

    //gifts
    public static boolean                gChooseProfilePhoto              = false;
    public static boolean                gChooseNickName                  = false;
    public static boolean                gComment                         = false;
    public static boolean                gEnterHighScores                 = false;
    public static boolean                gRegister                        = false;
    public static boolean                gSendApp                         = false;
    public static boolean                gShop                            = false;
    public static boolean                gEveryThing                      = false;
    public static boolean                gAddSticker                      = false;
    public static boolean                gSendVideo                       = false;
    public static boolean                gNewVersion                      = false;

    //High scores & Profile
    public static int                    normalMathClassicHighScore       = 0;
    public static int                    normalVocabClassicHighScore      = 0;
    public static int                    normalChallengeClassicHighScore  = 0;
    public static int                    normalMathTimerHighScore         = 0;
    public static int                    normalVocabTimerHighScore        = 0;
    public static int                    normalChallengeTimerHighScore    = 0;
    public static int                    reverseMathClassicHighScore      = 0;
    public static int                    reverseVocabClassicHighScore     = 0;
    public static int                    reverseChallengeClassicHighScore = 0;
    public static int                    reverseMathTimerHighScore        = 0;
    public static int                    reverseVocabTimerHighScore       = 0;
    public static int                    reverseChallengeTimerHighScore   = 0;
    public static int                    specialGameHighScore             = 0;
    public static int                    totalScore                       = 0;
    public static String                 email                            = "";
    public static String                 username                         = "";
    public static int                    profileImage                     = 0;
    public static int                    nickName                         = 0;
    public static long                   myID                             = 0;
    public static boolean                isHighScoreSaved                 = true;

    //Nick Names
    public static int                    playedGames                      = 0;
    public static int                    levelAchieve5                    = 0;
    public static int                    levelAchieve6                    = 0;
    public static int                    levelAchieve7                    = 0;
    public static int                    levelAchieve8                    = 0;
    public static int                    levelAchieve9                    = 0;
    public static int                    levelAchieve10                   = 0;
    public static boolean                specialLevelAchieve2             = false;
    public static boolean                specialLevelAchieve4             = false;
    public static boolean                specialLevelAchieve6             = false;
    public static boolean                specialLevelAchieve8             = false;
    public static boolean                specialLevelAchieve10            = false;
    public static boolean                isGamePlayed                     = false;

    //Shop && Purchase
    public static final String           TAG                              = "Purchase";
    public static final int              RC_REQUEST                       = 0;
    public static IabHelper              mHelper;
    public static String                 base64EncodedPublicKey           = "---/L//++/==";
    public static String                 SKU_50_BULLETS                   = "bullets_50";
    public static String                 SKU_150_BULLETS                  = "bullets_150";
    public static String                 SKU_500_BULLETS                  = "bullets_500";
    public static String                 SKU_1000_COIN                    = "coin_1000";
    public static String                 SKU_3000_COIN                    = "coins_3000";
    public static String                 SKU_10000_COIN                   = "coins_10000";
    public static String                 SKU_5S_TIME                      = "time_5s";
    public static String                 SKU_10S_TIME                     = "time_10s";
    public static String                 SKU_100S_TIME                    = "time_100s";
    public static String                 SKU_150S_TIME                    = "time_150s";
    public static String                 SKU_SPECIAL_GAME                 = "special_game";
    public static String                 SKU_ALL_ITEM                     = "all_items";

    public static boolean                bool_5S_TIME                     = false;
    public static boolean                bool_10S_TIME                    = false;
    public static boolean                bool_100S_TIME                   = false;
    public static boolean                bool_150S_TIME                   = false;
    public static boolean                bool_SPECIAL_GAME                = false;
    public static boolean                bool_ALL_ITEM                    = false;

    public static boolean                noAdv                            = false;


    @Override
    public void onCreate() {
        super.onCreate();

        preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        if (G.isFirstTime)
        {
            Editor editor = preferences.edit();
            editor.clear();
            editor.commit();

        }

        gameFont = Typeface.createFromAsset(getAssets(), "fonts/bold.ttf");
        Parse.initialize(this, "----", "----");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        context = getApplicationContext();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        String DIR_DATABASE = DIR_SDCARD + "/Android/data/" + getPackageName();

        new File(DIR_DATABASE).mkdirs();
        database = SQLiteDatabase.openOrCreateDatabase(DIR_DATABASE + "/true_or_false3.sqlite", null);
        database.execSQL("CREATE  TABLE  IF NOT EXISTS questions (" +
                "id INTEGER ," +
                " question TEXT," +
                " answer BOOL," +
                " level INTEGER," +
                " type INTEGER)");
        getStatics();
        click = MediaPlayer.create(G.context, R.raw.click);

        updateHighScores();
        ArrayQuestions = new Vector<StructQuestion>();
        ArrayHighScores = new Vector<StructTop>();

        if ( !isHighScoreSaved)
            G.saveHighscores();
        if (noAdv)
            Adad.setDisabled(true);

    }


    public static void saveHighscores()
    {
        ConnectionDetector cnd = new ConnectionDetector(context);
        if (cnd.isConnectingToInternet())
        {
            G.saveHighScore(1, G.normalMathClassicHighScore);
            G.saveHighScore(2, G.normalVocabClassicHighScore);
            G.saveHighScore(3, G.normalChallengeClassicHighScore);

            G.saveHighScore(4, G.normalMathTimerHighScore);
            G.saveHighScore(5, G.normalVocabTimerHighScore);
            G.saveHighScore(6, G.normalChallengeTimerHighScore);

            G.saveHighScore(7, G.reverseMathClassicHighScore);
            G.saveHighScore(8, G.reverseVocabClassicHighScore);
            G.saveHighScore(9, G.reverseChallengeClassicHighScore);

            G.saveHighScore(10, G.reverseMathTimerHighScore);
            G.saveHighScore(11, G.reverseVocabTimerHighScore);
            G.saveHighScore(12, G.reverseChallengeTimerHighScore);

            G.isHighScoreSaved = true;
            Editor editor = G.preferences.edit();
            editor.putBoolean("isHighScoreSaved", G.isHighScoreSaved);
            editor.commit();
            Toast.makeText(G.context, R.string.score_successfully_updated, Toast.LENGTH_SHORT).show();

        }
    }


    public static void getQuestions(int level)
    {

        Cursor cursor = database.rawQuery("SELECT * FROM questions WHERE level=" + level + " and type=" + questionType, null);
        ArrayQuestions.clear();

        while (cursor.moveToNext())
        {
            int ID = cursor.getInt(cursor.getColumnIndex("id"));
            String text = cursor.getString(cursor.getColumnIndex("question"));
            boolean answer = cursor.getInt(cursor.getColumnIndex("answer")) != 0;
            StructQuestion temp = new StructQuestion(ID, text, answer, level, questionType);
            ArrayQuestions.add(temp);
        }

        cursor.close();

    }


    public static void updateHighScores()
    {
        String TnormalMathClassicHighScore = preferences.getString("normalMathClassicHighScore", code(0));
        String TnormalVocabClassicHighScore = preferences.getString("normalVocabClassicHighScore", code(0));
        String TnormalChallengeClassicHighScore = preferences.getString("normalChallengeClassicHighScore", code(0));
        String TnormalMathTimerHighScore = preferences.getString("normalMathTimerHighScore", code(0));
        String TnormalVocabTimerHighScore = preferences.getString("normalVocabTimerHighScore", code(0));
        String TnormalChallengeTimerHighScore = preferences.getString("normalChallengeTimerHighScore", code(0));
        String TreverseMathClassicHighScore = preferences.getString("reverseMathClassicHighScore", code(0));
        String TreverseVocabClassicHighScore = preferences.getString("reverseVocabClassicHighScore", code(0));
        String TreverseChallengeClassicHighScore = preferences.getString("reverseChallengeClassicHighScore", code(0));
        String TreverseMathTimerHighScore = preferences.getString("reverseMathTimerHighScore", code(0));
        String TreverseVocabTimerHighScore = preferences.getString("reverseVocabTimerHighScore", code(0));
        String TreverseChallengeTimerHighScore = preferences.getString("reverseChallengeTimerHighScore", code(0));
        String TspecialGameHighScore = preferences.getString("specialGameHighScore", code(0));

        normalMathClassicHighScore = decode(TnormalMathClassicHighScore);
        normalVocabClassicHighScore = decode(TnormalVocabClassicHighScore);
        normalChallengeClassicHighScore = decode(TnormalChallengeClassicHighScore);
        normalMathTimerHighScore = decode(TnormalMathTimerHighScore);
        normalVocabTimerHighScore = decode(TnormalVocabTimerHighScore);
        normalChallengeTimerHighScore = decode(TnormalChallengeTimerHighScore);
        reverseMathClassicHighScore = decode(TreverseMathClassicHighScore);
        reverseVocabClassicHighScore = decode(TreverseVocabClassicHighScore);
        reverseChallengeClassicHighScore = decode(TreverseChallengeClassicHighScore);
        reverseMathTimerHighScore = decode(TreverseMathTimerHighScore);
        reverseVocabTimerHighScore = decode(TreverseVocabTimerHighScore);
        reverseChallengeTimerHighScore = decode(TreverseChallengeTimerHighScore);
        specialGameHighScore = decode(TspecialGameHighScore);

    }


    public static void saveHighScores()
    {
        Editor editor = G.preferences.edit();
        editor.putString("normalMathClassicHighScore", code(normalMathClassicHighScore));
        editor.putString("normalVocabClassicHighScore", code(normalVocabClassicHighScore));
        editor.putString("normalChallengeClassicHighScore", code(normalChallengeClassicHighScore));
        editor.putString("normalMathTimerHighScore", code(normalMathTimerHighScore));
        editor.putString("normalVocabTimerHighScore", code(normalVocabTimerHighScore));
        editor.putString("normalChallengeTimerHighScore", code(normalChallengeTimerHighScore));

        editor.putString("reverseMathClassicHighScore", code(reverseMathClassicHighScore));
        editor.putString("reverseVocabClassicHighScore", code(reverseVocabClassicHighScore));
        editor.putString("reverseChallengeClassicHighScore", code(reverseChallengeClassicHighScore));
        editor.putString("reverseMathTimerHighScore", code(reverseMathTimerHighScore));
        editor.putString("reverseVocabTimerHighScore", code(reverseVocabTimerHighScore));
        editor.putString("reverseChallengeTimerHighScore", code(reverseChallengeTimerHighScore));

        editor.putString("specialGameHighScore", code(specialGameHighScore));

        editor.commit();

    }


    public static void resetScores()
    {

        normalMathClassicHighScore = 0;
        normalVocabClassicHighScore = 0;
        normalChallengeClassicHighScore = 0;
        normalMathTimerHighScore = 0;
        normalVocabTimerHighScore = 0;
        normalChallengeTimerHighScore = 0;

        reverseMathClassicHighScore = 0;
        reverseVocabClassicHighScore = 0;
        reverseChallengeClassicHighScore = 0;
        reverseMathTimerHighScore = 0;
        reverseVocabTimerHighScore = 0;
        reverseChallengeTimerHighScore = 0;

        saveHighScores();
    }


    public static int getHighScore(int type, String mode, boolean revMode)
    {
        if (mode.equals("classic") && !revMode)
        {
            if (type == 1)
                return normalMathClassicHighScore;
            if (type == 2)
                return normalVocabClassicHighScore;
            if (type == 3)
                return normalChallengeClassicHighScore;
        }
        if (mode.equals("timer") && !revMode)
        {
            if (type == 1)
                return normalMathTimerHighScore;
            if (type == 2)
                return normalVocabTimerHighScore;
            if (type == 3)
                return normalChallengeTimerHighScore;
        }

        if (mode.equals("classic") && revMode)
        {
            if (type == 1)
                return reverseMathClassicHighScore;
            if (type == 2)
                return reverseVocabClassicHighScore;
            if (type == 3)
                return reverseChallengeClassicHighScore;
        }
        if (mode.equals("timer") && revMode)
        {
            if (type == 1)
                return reverseMathTimerHighScore;
            if (type == 2)
                return reverseVocabTimerHighScore;
            if (type == 3)
                return reverseChallengeTimerHighScore;
        }
        if (mode.equals("special"))
        {
            return specialGameHighScore;
        }

        return 0;

    }


    public static int getType(int type, String mode, boolean revMode)
    {
        if (mode.equals("classic") && !revMode)
        {
            if (type == 1)
                return 1;
            if (type == 2)
                return 2;
            if (type == 3)
                return 3;
        }
        if (mode.equals("timer") && !revMode)
        {
            if (type == 1)
                return 4;
            if (type == 2)
                return 5;
            if (type == 3)
                return 6;
        }

        if (mode.equals("classic") && revMode)
        {
            if (type == 1)
                return 7;
            if (type == 2)
                return 8;
            if (type == 3)
                return 9;
        }
        if (mode.equals("timer") && revMode)
        {
            if (type == 1)
                return 10;
            if (type == 2)
                return 11;
            if (type == 3)
                return 12;
        }
        if (mode.equals("special"))
        {
            return 13;
        }

        return 0;

    }


    public static void saveCoins()
    {
        Editor editor = G.preferences.edit();
        editor.putString("coins", code(G.coins));
        editor.commit();
    }


    public static void saveCoins(int coin)
    {
        coins = coin;
        Editor editor = G.preferences.edit();
        editor.putString("coins", code(G.coins));
        editor.commit();
    }


    public static int getLastID(int type)
    {
        Cursor cursor = database.rawQuery("SELECT * FROM questions WHERE type=" + type, null);
        int res = 0;
        while (cursor.moveToNext())
        {
            res = cursor.getInt(cursor.getColumnIndex("id"));
        }

        cursor.close();

        return res;
    }


    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


    public static void showOnUiThread(final int MessageID)
    {
        currentPlace.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(context, MessageID, Toast.LENGTH_LONG).show();

            }
        });
    }


    public static int getCode(String gameMode)
    {
        if (gameMode.equals("classic"))
        {
            if (questionType == 1 && reverseMode == false)
            {
                return 1;
            }
            if (questionType == 2 && reverseMode == false)
            {
                return 2;
            }
            if (questionType == 3 && reverseMode == false)
            {
                return 3;
            }
            if (questionType == 1 && reverseMode == true)
            {
                return 7;
            }
            if (questionType == 2 && reverseMode == true)
            {
                return 8;
            }
            if (questionType == 3 && reverseMode == true)
            {
                return 9;
            }
        }
        if (gameMode.equals("timer"))
        {
            if (questionType == 1 && reverseMode == false)
            {
                return 4;
            }
            if (questionType == 2 && reverseMode == false)
            {
                return 5;
            }
            if (questionType == 3 && reverseMode == false)
            {
                return 6;
            }
            if (questionType == 1 && reverseMode == true)
            {
                return 10;
            }
            if (questionType == 2 && reverseMode == true)
            {
                return 11;
            }
            if (questionType == 3 && reverseMode == true)
            {
                return 12;
            }
        }
        if (gameMode.equals("special"))
        {
            return 13;
        }

        return 0;

    }


    public static int getHighscoreHeader(int gameMode)
    {
        switch (gameMode) {
            case 0:
                return R.string.country;

            case 1:
                return R.string.classic_math_normal;
            case 2:
                return R.string.classic_vocab_normal;
            case 3:
                return R.string.classic_challenge_normal;
            case 4:
                return R.string.timer_math_normal;
            case 5:
                return R.string.timer_vocab_normal;
            case 6:
                return R.string.timer_challenge_normal;
            case 7:
                return R.string.classic_math_reverse;
            case 8:
                return R.string.classic_vocab_reverse;
            case 9:
                return R.string.classic_challenge_reverse;
            case 10:
                return R.string.timer_math_reverse;
            case 11:
                return R.string.timer_vocab_reverse;
            case 12:
                return R.string.timer_challenge_reverse;
            case 13:
                return R.string.special;
        }
        return 0;

    }


    public static void saveHighScore(final int code, final int Score)
    {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Commands.saveHighScore(email, code, Score);
            }
        }).start();

    }


    public static int getProfileImageID(int imgNum)
    {
        String name = "img_" + imgNum;
        Resources resources = G.context.getResources();
        final int resourceId = resources.getIdentifier(name, "drawable",
                G.context.getPackageName());
        return resourceId;
    }


    public static boolean CheckMyExistanceInHighScores()
    {
        for (int i = 0; i < ArrayHighScores.size(); ++i)
        {
            if (ArrayHighScores.get(i).ID == myID)
                return true;
        }
        return false;
    }


    private void getStatics()
    {
        G.isFirstTime = preferences.getBoolean("isFirstTime", true);
        G.isRegistered = preferences.getBoolean("isRegistered", false);
        G.onExitDialogShow = preferences.getBoolean("onExitDialogShow", true);
        G.musicPlay = preferences.getBoolean("musicPlay", true);
        G.coins = decode(preferences.getString("coins", code(200)));
        G.profileImage = preferences.getInt("profileImage", 0);
        G.email = preferences.getString("email", "");
        G.username = preferences.getString("username", "");
        G.nickName = preferences.getInt("nickName", 0);
        G.myID = preferences.getLong("myID", 0);
        G.SnowGunBullets = decode(preferences.getString("SnowGunBullets", code(3)));
        G.isHighScoreSaved = preferences.getBoolean("isHighScoreSaved", true);
        G.playedGames = preferences.getInt("playedGames", 0);
        G.fontSize = preferences.getInt("fontSize", 20);

        G.levelAchieve5 = preferences.getInt("levelAchieve5", 0);
        G.levelAchieve6 = preferences.getInt("levelAchieve6", 0);
        G.levelAchieve7 = preferences.getInt("levelAchieve7", 0);
        G.levelAchieve8 = preferences.getInt("levelAchieve8", 0);
        G.levelAchieve9 = preferences.getInt("levelAchieve9", 0);
        G.levelAchieve10 = preferences.getInt("levelAchieve10", 0);

        G.specialLevelAchieve2 = preferences.getBoolean("specialLevelAchieve2", false);
        G.specialLevelAchieve4 = preferences.getBoolean("specialLevelAchieve4", false);
        G.specialLevelAchieve6 = preferences.getBoolean("specialLevelAchieve6", false);
        G.specialLevelAchieve8 = preferences.getBoolean("specialLevelAchieve8", false);
        G.specialLevelAchieve10 = preferences.getBoolean("specialLevelAchieve10", false);

        G.bool_5S_TIME = preferences.getBoolean("bool_5S_TIME", false);
        G.bool_10S_TIME = preferences.getBoolean("bool_10S_TIME", false);
        G.bool_100S_TIME = preferences.getBoolean("bool_100S_TIME", false);
        G.bool_150S_TIME = preferences.getBoolean("bool_150S_TIME", false);
        G.bool_SPECIAL_GAME = preferences.getBoolean("bool_SPECIAL_GAME", false);
        G.bool_ALL_ITEM = preferences.getBoolean("bool_ALL_ITEM", false);

        G.gChooseProfilePhoto = preferences.getBoolean("gChooseProfilePhoto", false);
        G.gChooseNickName = preferences.getBoolean("gChooseNickName", false);
        G.gComment = preferences.getBoolean("gComment", false);
        G.gEnterHighScores = preferences.getBoolean("gEnterHighScores", false);
        G.gRegister = preferences.getBoolean("gRegister", false);
        G.gSendApp = preferences.getBoolean("gSendApp", false);
        G.gShop = preferences.getBoolean("gShop", false);
        G.gEveryThing = preferences.getBoolean("gEveryThing", false);
        G.gAddSticker = preferences.getBoolean("gAddSticker", false);
        G.gSendVideo = preferences.getBoolean("gSendVideo", false);
        G.gNewVersion = preferences.getBoolean("gNewVersion", false);

        G.noAdv = preferences.getBoolean("noAdv", false);
    }


    public static void saveLevelAchieved()
    {
        Editor editor = G.preferences.edit();
        editor.putInt("levelAchieve5", levelAchieve5);
        editor.putInt("levelAchieve6", levelAchieve6);
        editor.putInt("levelAchieve7", levelAchieve7);
        editor.putInt("levelAchieve8", levelAchieve8);
        editor.putInt("levelAchieve9", levelAchieve9);
        editor.putInt("levelAchieve10", levelAchieve10);

        editor.commit();

    }


    public static void specialSaveLevelAchieved()
    {
        Editor editor = G.preferences.edit();

        editor.putBoolean("specialLevelAchieve2", specialLevelAchieve2);
        editor.putBoolean("specialLevelAchieve4", specialLevelAchieve4);
        editor.putBoolean("specialLevelAchieve6", specialLevelAchieve6);
        editor.putBoolean("specialLevelAchieve8", specialLevelAchieve8);
        editor.putBoolean("specialLevelAchieve10", specialLevelAchieve10);

        editor.commit();

    }


    public static void gamePlayed()
    {
        G.playedGames++;
        Editor editor = G.preferences.edit();
        editor.putInt("playedGames", playedGames);
        editor.commit();
    }


    public static void levelBonus(int level)
    {
        switch (level) {
            case 5:
                levelAchieve5++;
                break;
            case 6:
                levelAchieve6++;
                break;

            case 7:
                levelAchieve7++;
                break;

            case 8:
                levelAchieve8++;
                break;

            case 9:
                levelAchieve9++;
                break;

            case 10:
                levelAchieve10++;
                break;

        }
    }


    public static void specialLevelBonus(int level)
    {
        switch (level) {
            case 2:
                specialLevelAchieve2 = true;
                break;
            case 4:
                specialLevelAchieve4 = true;
                break;
            case 6:
                specialLevelAchieve6 = true;
                break;
            case 8:
                specialLevelAchieve8 = true;
                break;

            case 10:
                specialLevelAchieve10 = true;
                break;

        }
    }


    public static String getNickname(int nickNameNumber)
    {
        switch (nickNameNumber) {

            case 0:
                return "مبتدی";
            case 1:
                return "بدبخت بی پول";
            case 2:
                return "غرب زده";
            case 3:
                return "معتاد";
            case 4:
                return "عمه ننه";
            case 5:
                return "کارمند دولت";
            case 6:
                return "مریض";
            case 7:
                return "خسته";
            case 8:
                return "پروفسور";
            case 9:
                return "مهندس";
            case 10:
                return "میلیاردر";
            case 11:
                return "کاربلد";
            case 12:
                return "قهرمان";
            case 13:
                return "ابر قهرمان";
            case 14:
                return "تاریخ ساز";
            case 15:
                return "اسطوره";
            case 16:
                return "امپراطور";
            case 17:
                return "سرباز";
            case 18:
                return "نیزه دار";
            case 19:
                return "تیرانداز";
            case 20:
                return "شمشیرزن";
            case 21:
                return "سردار";
            case 22:
                return "سپهسالار";
            case 23:
                return "شوالیه";
            case 24:
                return "پادشاه";
            case 25:
                return "جغد مبارز";
            case 26:
                return "جغد جنگجو";
            case 27:
                return "جغد پیر";
            case 28:
                return "جغد کبیر";
            case 29:
                return "جغد دانا";

            default:
                return "";

        }

    }


    public static boolean prepareBazaarInAppBilling(final boolean requireUpdatePrefrences,
                                                    final QueryInventoryFinishedListener mGotInventoryListener) {
        try {
            mHelper = new IabHelper(G.context, base64EncodedPublicKey);
            Log.d(TAG, "Starting setup.");
            IabHelper.OnIabSetupFinishedListener listener =
                    new IabHelper.OnIabSetupFinishedListener() {

                        @Override
                        public void onIabSetupFinished(IabResult result) {
                            Log.d(TAG, "Setup finished.");
                            if ( !result.isSuccess()) {
                                Log.d(TAG, "Problem setting up In-app Billing: " + result);
                            }
                            if (requireUpdatePrefrences && mGotInventoryListener != null) {
                                mHelper.queryInventoryAsync(mGotInventoryListener);
                            }
                        }
                    };
            mHelper.startSetup(listener);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean initalizeBazaarInAppBilling(final boolean requireUpdatePrefrences,
                                                      final QueryInventoryFinishedListener mGotInventoryListener) {
        try {
            mHelper = new IabHelper(context, base64EncodedPublicKey);
            Log.d(TAG, "Starting setup.");
            IabHelper.OnIabSetupFinishedListener listener =
                    new IabHelper.OnIabSetupFinishedListener() {

                        @Override
                        public void onIabSetupFinished(IabResult result) {
                            Log.d(TAG, "Setup finished.");
                            if ( !result.isSuccess()) {
                                Log.d(TAG, "Problem setting up In-app Billing: " + result);
                            }
                            if (requireUpdatePrefrences && mGotInventoryListener != null) {
                                mHelper.queryInventoryAsync(mGotInventoryListener);
                            }
                        }
                    };
            mHelper.startSetup(listener);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static Bitmap takeScreenShot(Activity activity)
    {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = activity.getWindow().getDecorView().getWidth();
        int height = activity.getWindow().getDecorView().getHeight();

        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);

        view.destroyDrawingCache();
        return b;
    }


    public static void saveBullets()
    {
        Editor editor = G.preferences.edit();
        editor.putString("SnowGunBullets", code(SnowGunBullets));
        editor.commit();
    }


    public static void giftDialog(int coin, int bullet, int resId)
    {
        String reason = G.currentPlace.getResources().getString(resId);
        final Dialog gift = new Dialog(G.currentPlace);
        gift.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gift.setContentView(R.layout.dialog_gift);
        ViewGroup layoutOk = (ViewGroup) gift.findViewById(R.id.layoutOk);
        TextView txtGift = (TextView) gift.findViewById(R.id.txtGift);
        TextView txtReason = (TextView) gift.findViewById(R.id.txtReason);

        txtReason.setText(reason);
        if (bullet == 0)
            txtGift.setText(coin + " سکه جایزه گرفتی");
        else if (bullet == 0)
            txtGift.setText(bullet + " گلوله برفی جایزه گرفتی");
        else
            txtGift.setText(coin + " سکه و " + bullet + " گلوله برفی جایزه گرفتی");

        G.coins += coin;
        G.SnowGunBullets += bullet;
        Editor editor = G.preferences.edit();
        editor.putString("SnowGunBullets", code(G.SnowGunBullets));
        editor.putString("coins", code(G.coins));
        editor.commit();

        gift.getWindow().getAttributes().windowAnimations = R.style.ExitDialogAnimation;
        gift.show();
        HelperUi.persianizer((ViewGroup) gift.getWindow().getDecorView());

        layoutOk.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                gift.dismiss();
            }
        });
    }


    public static int getMyTotalScore()
    {
        totalScore = normalMathClassicHighScore +
                normalVocabClassicHighScore +
                normalChallengeClassicHighScore +
                normalMathTimerHighScore +
                normalVocabTimerHighScore +
                normalChallengeTimerHighScore +
                reverseMathClassicHighScore +
                reverseVocabClassicHighScore +
                reverseChallengeClassicHighScore +
                reverseMathTimerHighScore +
                reverseVocabTimerHighScore +
                reverseChallengeTimerHighScore +
                specialGameHighScore;
        return totalScore;
    }


    public static void RemoveAdd()
    {
        noAdv = true;
        Editor editor = G.preferences.edit();
        editor.putBoolean("noAdv", G.noAdv);
        editor.commit();

    }


    public static String code(int Score)
    {

        String code = Integer.toString(Score);
        char[] ascii = code.toCharArray();

        int[] asciiCode = new int[ascii.length];

        for (int i = 0; i < ascii.length; ++i) {
            asciiCode[i] = (int) ascii[i];
            asciiCode[i] += 10;

        }

        char[] asciiReverse = new char[asciiCode.length];
        for (int i = 0; i < asciiCode.length; ++i) {

            asciiReverse[i] = (char) asciiCode[i];

        }
        String finalString = String.valueOf(asciiReverse);

        return finalString;
    }


    public static int decode(String Score)
    {

        char[] scoreArray = Score.toCharArray();
        int[] asciiIntArray = new int[scoreArray.length];
        for (int i = 0; i < scoreArray.length; ++i) {

            asciiIntArray[i] = (int) scoreArray[i];
            asciiIntArray[i] -= 10;

        }
        char[] asciiReverse = new char[asciiIntArray.length];

        for (int i = 0; i < asciiIntArray.length; ++i) {

            asciiReverse[i] = (char) asciiIntArray[i];

        }
        String finalString = String.valueOf(asciiReverse);
        int decode = Integer.valueOf(finalString);

        return decode;
    }
}