package corporation.rabbit.games.true_or_false;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utilities.StructPerson;
import utilities.StructTop;
import android.database.Cursor;
import android.util.Log;


public class Commands {

    private static String localhost = "http://178.32.139.233/";


    public static void read() {
        String result = null;
        try {

            InputStream is = G.currentPlace.getAssets().open("questions.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            result = new String(buffer, "UTF-8");
            Log.i("Mine", "result is: " + result);

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        if (result != null) {
            try {
                JSONArray tasks = new JSONArray(result);
                for (int i = 0; i < tasks.length(); i++) {
                    JSONObject object = tasks.getJSONObject(i);

                    long id = object.getInt("id");
                    String text = object.getString("text");
                    int answer = object.getInt("answer");
                    int level = object.getInt("level");
                    int type = object.getInt("type");
                    Cursor cursor = G.database.rawQuery("SELECT * FROM questions WHERE id=" + id, null);
                    if (cursor.moveToNext())
                    {
                        Log.i("Mine", "id:" + id + " tekrarie");

                    }
                    else
                    {
                        G.database.execSQL("INSERT INTO questions (id,question,answer,level,type)" +
                                " VALUES (+" + id + ",'" + text + "'," + answer + "," + level + "," + type + ")");
                    }
                    cursor.close();

                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public static int getNewsCount(long lastID, int Type)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "new_counts"));
        params.add(new BasicNameValuePair("last_id", "" + lastID));
        params.add(new BasicNameValuePair("type", "" + Type));
        String res = Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);
        if (res == null)
            res = "" + 0;

        return Integer.parseInt(res);

    }


    //    public static void test()
    //    {
    //        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    //        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
    //        params.add(new BasicNameValuePair("code", "rhL6qFrZycL74IuOTH7Y7e1kI0YT57"));
    //        params.add(new BasicNameValuePair("client_id", "uM5BT4N4zqRW7PmRzFK5M1AhjrwqEyxaO48QYw5G"));
    //        params.add(new BasicNameValuePair("client_secret", "eS3DLSToVN7Ydi2bo4AJYIMkD1xPDAtUPsgk1El48zILZQeHTpGl0Ri9irIp"));
    //        params.add(new BasicNameValuePair("redirect_uri", "http://178.32.139.233/"));
    //        Log.i("Mine", "before sending demand");
    //
    //        String res = Webservice.readUrl("https://pardakht.cafebazaar.ir/auth/token/", params);
    //        Log.i("Mine", "res is: " + res);
    //
    //    }

    public static boolean checkPurchase(String SKU, String purchaseToken, String payload)
    {

        String res = Webservice.readUrl("https://pardakht.cafebazaar.ir/api/validate/" + G.currentPlace.getPackageName() +
                "/inapp/" + SKU +
                "/purchases/" + purchaseToken +
                "/?access_token=" + getAccessCode()
                , null);
        if (res != null) {
            try {
                JSONObject object = new JSONObject(res);
                String developerPayload = object.getString("developerPayload");
                int purchaseState = object.getInt("purchaseState");

                if (purchaseState == 0 && developerPayload.equals(payload))
                {

                    return true;
                } else
                {
                    return false;
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return false;
    }


    public static String getAccessCode()
    {
        String res = Webservice.readUrl(localhost + "/acc_code.php?", null);

        return res;

    }


    public static void addNews(final long lastID, final int type)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "read"));
        params.add(new BasicNameValuePair("last_id", "" + lastID));
        params.add(new BasicNameValuePair("type", "" + type));

        String res = Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);
        Log.i("Mine", res);
        if (res != null) {
            try {
                JSONArray tasks = new JSONArray(res);
                for (int i = 0; i < tasks.length(); i++) {
                    JSONObject object = tasks.getJSONObject(i);
                    long id = object.getInt("id");
                    String text = object.getString("text");
                    boolean answer = object.getInt("answer") != 0;
                    int ans = (answer) ? 1 : 0;
                    int level = object.getInt("level");

                    Cursor cursor = G.database.rawQuery("SELECT * FROM questions WHERE id=" + id, null);
                    if (cursor.moveToNext())
                    {
// repetitive
                    }
                    else
                    {
                        G.database.execSQL("INSERT INTO questions (id,question,answer,level,type)" +
                                " VALUES (+" + id + ",'" + text + "'," + ans + "," + level + "," + type + ")");
                    }

                }

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

    }


    public static int Register(String email, String username, String pass)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "register"));
        params.add(new BasicNameValuePair("email", "" + email));
        params.add(new BasicNameValuePair("username", "" + username));
        params.add(new BasicNameValuePair("pass", "" + pass));

        String res = Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);
        if (res == null)
            res = "" + 0;
        return Integer.parseInt(res);

    }


    public static StructPerson getProfile(long id)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", "" + id));
        params.add(new BasicNameValuePair("action", "getProfile"));

        String res = Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);
        if (res == null)
            res = "" + 2;
        if ( !res.equals("2"))
        {
            try {
                JSONArray tasks = new JSONArray(res);
                for (int i = 0; i < tasks.length(); i++) {
                    JSONObject object = tasks.getJSONObject(i);

                    String Username = object.getString("username");
                    int ProfileImage = object.getInt("pic");
                    int NickName = object.getInt("nick_name");
                    int NormalMathClassicHighScore = object.getInt("classic_normal_math");
                    int NormalVocabClassicHighScore = object.getInt("classic_normal_vocab");
                    int NormalChallengeClassicHighScore = object.getInt("classic_normal_challenge");
                    int NormalMathTimerHighScore = object.getInt("timer_normal_math");
                    int NormalVocabTimerHighScore = object.getInt("timer_normal_vocab");
                    int NormalChallengeTimerHighScore = object.getInt("timer_normal_challenge");
                    int ReverseMathClassicHighScore = object.getInt("classic_reverse_math");
                    int ReverseVocabClassicHighScore = object.getInt("classic_reverse_vocab");
                    int ReverseChallengeClassicHighScore = object.getInt("classic_reverse_challenge");
                    int ReverseMathTimerHighScore = object.getInt("timer_reverse_math");
                    int ReverseVocabTimerHighScore = object.getInt("timer_reverse_vocab");
                    int ReverseChallengeTimerHighScore = object.getInt("timer_reverse_challenge");
                    int SpecialGameHighScore = object.getInt("special_game");

                    StructPerson person = new StructPerson(id, Username, ProfileImage, NickName, NormalMathClassicHighScore, NormalVocabClassicHighScore, NormalChallengeClassicHighScore, NormalMathTimerHighScore, NormalVocabTimerHighScore, NormalChallengeTimerHighScore, ReverseMathClassicHighScore, ReverseVocabClassicHighScore, ReverseChallengeClassicHighScore, ReverseMathTimerHighScore, ReverseVocabTimerHighScore, ReverseChallengeTimerHighScore, SpecialGameHighScore);

                    return person; //logged in successfully

                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;

    }


    public static int login(String email, String pass)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "login"));
        params.add(new BasicNameValuePair("email", "" + email));
        params.add(new BasicNameValuePair("pass", "" + pass));
        String res = Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);
        Log.i("Mine", "res is: " + res);
        if (res == null)
            res = "" + 0;
        if (res.equals("0") || res.equals("2"))
            return Integer.parseInt(res);// 2:invalid email   0:invalid pass
        else
        {
            try {
                JSONArray tasks = new JSONArray(res);
                for (int i = 0; i < tasks.length(); i++) {
                    JSONObject object = tasks.getJSONObject(i);

                    G.username = object.getString("username");
                    G.profileImage = object.getInt("pic");
                    G.nickName = object.getInt("nick_name");
                    G.normalMathClassicHighScore = object.getInt("classic_normal_math");
                    G.normalVocabClassicHighScore = object.getInt("classic_normal_vocab");
                    G.normalChallengeClassicHighScore = object.getInt("classic_normal_challenge");
                    G.normalMathTimerHighScore = object.getInt("timer_normal_math");
                    G.normalVocabTimerHighScore = object.getInt("timer_normal_vocab");
                    G.normalChallengeTimerHighScore = object.getInt("timer_normal_challenge");
                    G.reverseMathClassicHighScore = object.getInt("classic_reverse_math");
                    G.reverseVocabClassicHighScore = object.getInt("classic_reverse_vocab");
                    G.reverseChallengeClassicHighScore = object.getInt("classic_reverse_challenge");
                    G.reverseMathTimerHighScore = object.getInt("timer_reverse_math");
                    G.reverseVocabTimerHighScore = object.getInt("timer_reverse_vocab");
                    G.reverseChallengeTimerHighScore = object.getInt("timer_reverse_challenge");
                    G.specialGameHighScore = object.getInt("special_game");

                    return 1; //logged in successfully

                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return 3;// there is a problem

    }


    public static void UpdateUsername(String email, String username)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "update_username"));

        params.add(new BasicNameValuePair("email", "" + email));
        params.add(new BasicNameValuePair("username", "" + username));

        Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);

    }


    public static void UpdateProfilePicture(String email, int pic_id)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "update_profile_pic"));
        params.add(new BasicNameValuePair("email", "" + email));
        params.add(new BasicNameValuePair("pic_id", "" + pic_id));

        Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);

    }


    public static void saveHighScore(String email, int code, int score)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "save_highscore"));

        params.add(new BasicNameValuePair("email", "" + email));
        params.add(new BasicNameValuePair("code", "" + code));
        params.add(new BasicNameValuePair("score", "" + score));

        Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);

    }


    public static void getHighScores(int code)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "get_records"));

        params.add(new BasicNameValuePair("type", "" + code));

        String res = Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);
        G.ArrayHighScores.clear();
        if (res != null) {
            try {

                JSONArray tasks = new JSONArray(res);
                Log.i("Mine", "size is: " + tasks.length());
                for (int i = 0; i < tasks.length(); i++) {
                    JSONObject object = tasks.getJSONObject(i);

                    long id = object.getInt("id");
                    String username = object.getString("username");
                    int pic = object.getInt("pic");
                    int nick_name = object.getInt("nick_name");
                    int Score = object.getInt("score");
                    Log.i("Mine", "adding: " + username);

                    StructTop top = new StructTop(id, username, code, nick_name, pic, Score);
                    if (username != null)
                        G.ArrayHighScores.add(top);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    public static long getID(String mail)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "getID"));

        params.add(new BasicNameValuePair("email", "" + mail));

        String res = Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);
        if (res == null)
            res = "" + 0;
        return Long.parseLong(res);
    }


    public static int getRank(int type)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "getRank"));

        params.add(new BasicNameValuePair("email", "" + G.email));
        params.add(new BasicNameValuePair("type", "" + type));

        String res = Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);
        if (res == null)
            res = "" + 0;
        return Integer.parseInt(res);
    }


    public static void changeNickname(int number)
    {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "changeNickname"));

        params.add(new BasicNameValuePair("email", "" + G.email));
        params.add(new BasicNameValuePair("number", "" + number));

        Webservice.readUrl(localhost + "/true_or_false/tof.php?", params);
    }

}
