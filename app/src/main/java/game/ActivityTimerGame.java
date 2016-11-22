package game;

// reverse dashte bashe game
import java.util.ArrayList;
import java.util.Random;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import corporation.rabbit.games.true_or_false.G;
import corporation.rabbit.games.true_or_false.HelperUi;
import corporation.rabbit.games.true_or_false.R;


public class ActivityTimerGame extends ActionBarActivity {

    private int                      currentLevel    = 1;
    private StructQuestion           question;
    private int                      correctQuestion = -1;
    public static ArrayList<Integer> checkNoRepeat   = new ArrayList<Integer>();
    private TextView                 txtLevel;
    private TextView                 txtSnowGunBullets;
    private CountDownTimer           counter;
    private MediaPlayer              tic_toc;
    private MediaPlayer              correct;
    private MediaPlayer              wrong;
    private MediaPlayer              freezeSound;
    private int                      Score           = 0;
    private boolean                  isFrozen        = false;
    private int                      gameTime        = 60000;
    private int                      gameTimeCheck   = 0;
    private TextView                 txtTime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_timer);
        G.currentPlace = this;
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());

        if (G.bool_100S_TIME)
            gameTime = 160000;
        if (G.bool_150S_TIME)
            gameTime = 210000;
        final Button btnTrue = (Button) findViewById(R.id.btnTrue);
        final Button btnFalse = (Button) findViewById(R.id.btnFalse);

        final ImageView imgClock = (ImageView) findViewById(R.id.imgClock);
        final ViewGroup LayoutTimerGame = (ViewGroup) findViewById(R.id.LayoutTimerGame);

        final TextView txtQuestion = (TextView) findViewById(R.id.txtText);
        final TextView txtScore = (TextView) findViewById(R.id.txtScore);
        txtTime = (TextView) findViewById(R.id.txtTime);
        TextView txtMode = (TextView) findViewById(R.id.txtMode);
        txtQuestion.setTextSize(G.fontSize);
        txtQuestion.setMovementMethod(new ScrollingMovementMethod());

        tic_toc = MediaPlayer.create(ActivityTimerGame.this, R.raw.clock_tic);
        correct = MediaPlayer.create(ActivityTimerGame.this, R.raw.correct_answer);
        wrong = MediaPlayer.create(ActivityTimerGame.this, R.raw.wrong_answer);
        freezeSound = MediaPlayer.create(ActivityTimerGame.this, R.raw.freeze_sound);
        MediaPlayer begin = MediaPlayer.create(ActivityTimerGame.this, R.raw.begin);
        if (G.musicPlay)
            begin.start();
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        ImageView imgSnowGun = (ImageView) findViewById(R.id.imgSnowGun);
        if (G.SnowGunBullets > 0 && (screenSize != Configuration.SCREENLAYOUT_SIZE_SMALL))
            imgSnowGun.setVisibility(View.VISIBLE);
        else
            imgSnowGun.setVisibility(View.GONE);

        imgSnowGun.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if ( !isFrozen)
                {
                    freeze(btnTrue, btnFalse, imgClock, LayoutTimerGame);
                    G.SnowGunBullets--;
                    txtSnowGunBullets.setText("" + G.SnowGunBullets);
                    Editor editor = G.preferences.edit();
                    editor.putString("SnowGunBullets", G.code(G.SnowGunBullets));
                    editor.commit();
                }

            }
        });

        txtLevel = (TextView) findViewById(R.id.txtLevel);
        txtLevel.setText("" + currentLevel);

        if ( !G.reverseMode)
            txtMode.setVisibility(View.INVISIBLE);
        startCounter();

        G.getQuestions(currentLevel);
        txtScore.setText("" + Score);
        doNextQuestion(txtQuestion, false);

        btnTrue.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (isFrozen)
                {
                    unFreeze(btnTrue, btnFalse, imgClock, LayoutTimerGame);
                }

                if (checkAnswer(true))
                {
                    if (G.musicPlay)
                        correct.start();
                    doNextQuestion(txtQuestion, true);
                    gotScore(txtScore, currentLevel * 10);

                }
                else
                {
                    if (G.musicPlay)
                        wrong.start();
                    doNextQuestion(txtQuestion, false);
                    gotScore(txtScore, -(currentLevel * 20));
                }

            }
        });
        btnFalse.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (isFrozen)
                {
                    unFreeze(btnTrue, btnFalse, imgClock, LayoutTimerGame);
                }

                if (checkAnswer(false))
                {
                    if (G.musicPlay)
                        correct.start();
                    doNextQuestion(txtQuestion, true);
                    gotScore(txtScore, currentLevel * 10);

                }
                else
                {
                    if (G.musicPlay)
                        wrong.start();
                    doNextQuestion(txtQuestion, false);
                    gotScore(txtScore, -(currentLevel * 20));
                }
            }
        });
    }


    private void startCounter()
    {
        counter = new CountDownTimer(gameTime - gameTimeCheck, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                gameTimeCheck += 1000;
                int i = (int) (millisUntilFinished / 1000);
                txtTime.setText("" + (i - 1));
                if (i < 11)
                    if (G.musicPlay)
                        tic_toc.start();

            }


            @Override
            public void onFinish() {
                youLoseDialog();

            }
        };
    }


    private boolean checkAnswer(boolean ans)
    {
        if (ans == question.answer)
            return !G.reverseMode;
        else
            return G.reverseMode;

    }


    private void gotScore(TextView txt, int score)
    {
        if (Score < 0 && score < 0)
            Score += score / 2;
        else
            Score += score;

        txt.setText("" + Score);
        if (Score < 0)
            txt.setTextColor(Color.parseColor("#e21f56"));
        else
            txt.setTextColor(Color.parseColor("#000000"));

    }


    private void doNextQuestion(TextView txt, boolean ans)
    {
        txtLevel.setText("" + currentLevel);
        if (ans)
            ++correctQuestion;
        if (correctQuestion == 10)
        {
            checkNoRepeat.clear();
            correctQuestion = 0;
            if (currentLevel <= 10) // movaghatie
            {
                ++currentLevel;
                G.levelBonus(currentLevel);
                txtLevel.setText("" + currentLevel);
            }

            G.getQuestions(currentLevel);

        }
        if (currentLevel > 10)
        {

            Random r = new Random();
            int Low = 7;
            int High = 10;
            int R = r.nextInt(High - Low) + Low;
            G.getQuestions(R);
            int i;
            Random ran = new Random();
            int size = G.ArrayQuestions.size();
            i = ran.nextInt(size);
            while (checkNoRepeat.contains(i) && checkNoRepeat.size() < G.ArrayQuestions.size())
            {
                i = ran.nextInt(size);
            }
            checkNoRepeat.add(i);
            question = G.ArrayQuestions.get(i);
            txt.setText(question.text);

        }
        else
        {
            int i;
            Random r = new Random();
            int size = G.ArrayQuestions.size();
            i = r.nextInt(size);
            while (checkNoRepeat.contains(i) && checkNoRepeat.size() < G.ArrayQuestions.size())
            {
                i = r.nextInt(size);
            }
            checkNoRepeat.add(i);
            question = G.ArrayQuestions.get(i);
            txt.setText(question.text);
        }

    }


    private void youLoseDialog()
    {

        Intent intent = new Intent(ActivityTimerGame.this, DialogTimerLose.class);
        intent.putExtra("score", Score);
        startActivity(intent);
        overridePendingTransition(R.anim.yeho_zaher_she, R.anim.fade_out_animation);

    }


    @Override
    protected void onResume() {
        super.onResume();
        startCounter();
        counter.start();
        final Button btnTrue = (Button) findViewById(R.id.btnTrue);
        final Button btnFalse = (Button) findViewById(R.id.btnFalse);
        final ImageView imgClock = (ImageView) findViewById(R.id.imgClock);
        final ViewGroup LayoutTimerGame = (ViewGroup) findViewById(R.id.LayoutTimerGame);
        /*
         * 
         *          ACTION BAR
         * 
         */

        View view = G.inflater.inflate(R.layout.action_bar_game, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, new ActionBar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        TextView txtCoins = (TextView) view.findViewById(R.id.txtCoins);
        txtSnowGunBullets = (TextView) view.findViewById(R.id.txtSnowGunBullets);
        ImageView imgSnowGunActionBar = (ImageView) view.findViewById(R.id.imgSnowGun);
        HelperUi.persianizer((ViewGroup) view);

        txtSnowGunBullets.setText("" + G.SnowGunBullets);
        txtCoins.setText("" + G.coins);
        imgSnowGunActionBar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if ( !isFrozen)
                {
                    if (G.SnowGunBullets > 0)
                    {
                        freeze(btnTrue, btnFalse, imgClock, LayoutTimerGame);
                        G.SnowGunBullets--;
                        txtSnowGunBullets.setText("" + G.SnowGunBullets);
                        Editor editor = G.preferences.edit();
                        editor.putString("SnowGunBullets", G.code(G.SnowGunBullets));
                        editor.commit();
                    }
                    else
                    {
                        Toast.makeText(G.context, R.string.no_froze_bullets, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        /*
         * 
         *         END ACTION BAR
         * 
         */

        if (G.dialogChooseAction == 0)
        {
            // do nothing 
        } else if (G.dialogChooseAction == 1) // exit
        {
            finish();
        } else if (G.dialogChooseAction == 2) // reset
        {
            //            if (Build.VERSION.SDK_INT >= 11) {
            //                recreate();
            //            } else {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            //            }
        }
        G.dialogChooseAction = 0;
        G.saveHighScores();

    }


    private void freeze(Button btnTrue, Button btnFalse, ImageView imgClock, ViewGroup BackGround)
    {
        if (G.musicPlay)
            freezeSound.start();
        isFrozen = true;
        counter.cancel();
        btnTrue.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_ice_answer));
        btnFalse.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_ice_answer));
        imgClock.setImageResource(R.drawable.ic_clock_frozen);
        BackGround.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_ice));
    }


    private void unFreeze(Button btnTrue, Button btnFalse, ImageView imgClock, ViewGroup BackGround)
    {
        isFrozen = false;
        startCounter();
        counter.start();
        btnTrue.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_true));
        btnFalse.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_false));
        imgClock.setImageResource(R.drawable.ic_clock);
        BackGround.setBackgroundColor(Color.parseColor("#d3ebee"));
    }


    @Override
    protected void onPause() {
        super.onPause();
        counter.cancel();
        G.saveCoins();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_animation, R.anim.up_to_down);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        counter.cancel();
        G.saveCoins();
        G.saveLevelAchieved();
        if (G.isGamePlayed)
        {
            G.gamePlayed();
            G.isGamePlayed = false;
        }

    }

}