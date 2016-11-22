package specialMode;

import game.StructQuestion;
import java.util.ArrayList;
import java.util.Random;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Build;
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


public class ActivityGame extends ActionBarActivity {

    private int                      currentLevel        = 1;
    private StructQuestion           question;
    private int                      correctQuestion     = -1;
    private int                      Score               = 0;
    public static ArrayList<Integer> checkNoRepeat       = new ArrayList<Integer>();
    private TextView                 txtMode;
    private TextView                 txtLevel;
    private TextView                 txtSnowGunBullets;
    private TextView                 txtTime;
    private CountDownTimer           counter;
    private MediaPlayer              tic_toc;
    private MediaPlayer              correct;
    private MediaPlayer              wrong;
    private MediaPlayer              freezeSound;
    private boolean                  isFrozen            = false;
    private ViewGroup                layoutQuestion;

    private static int               EVERY_QUESTION_TIME = 11000;
    private boolean                  reverseMode         = false;

    private boolean                  checkContinue       = false;
    private int                      gameTimeCheck       = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_classic);
        G.currentPlace = this;
        G.questionType = 4;
        G.getQuestions(currentLevel);
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());

        reverseMode = false;
        final ViewGroup LayoutNext = (ViewGroup) findViewById(R.id.LayoutNext);
        final Button btnTrue = (Button) findViewById(R.id.btnTrue);
        final Button btnFalse = (Button) findViewById(R.id.btnFalse);
        final ImageView imgClock = (ImageView) findViewById(R.id.imgClock);
        final ViewGroup LayoutClassicGame = (ViewGroup) findViewById(R.id.LayoutClassicGame);
        layoutQuestion = (ViewGroup) findViewById(R.id.layoutQuestion);

        final TextView txtQuestion = (TextView) findViewById(R.id.txtText);
        final TextView txtScore = (TextView) findViewById(R.id.txtScore);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtMode = (TextView) findViewById(R.id.txtMode);

        txtQuestion.setTextSize(G.fontSize);
        txtQuestion.setMovementMethod(new ScrollingMovementMethod());

        LayoutClassicGame.setBackgroundResource(R.drawable.bg_special);
        LayoutNext.setVisibility(View.GONE);
        tic_toc = MediaPlayer.create(ActivityGame.this, R.raw.clock_tic);
        correct = MediaPlayer.create(ActivityGame.this, R.raw.correct_answer);
        wrong = MediaPlayer.create(ActivityGame.this, R.raw.wrong_answer);
        freezeSound = MediaPlayer.create(ActivityGame.this, R.raw.freeze_sound);
        MediaPlayer begin = MediaPlayer.create(ActivityGame.this, R.raw.begin);
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
                    freeze(btnTrue, btnFalse, imgClock, LayoutClassicGame);
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

        startCounter();

        txtScore.setText("" + Score);
        doNextQuestion(txtQuestion);
        btnTrue.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (isFrozen)
                {
                    unFreeze(btnTrue, btnFalse, imgClock, LayoutClassicGame);
                }
                counter.cancel();
                if (checkAnswer(true))
                {
                    if (G.musicPlay)
                        correct.start();
                    gameTimeCheck = 0;
                    if (gameTimeCheck < 5001)
                        saveScore(txtScore, currentLevel * 10);
                    if (gameTimeCheck < 2001)
                        saveScore(txtScore, currentLevel * 20);
                    startCounter();
                    counter.start();
                    doNextQuestion(txtQuestion);
                    saveScore(txtScore, currentLevel * 10);

                }
                else
                {
                    if (G.musicPlay)
                        wrong.start();
                    youLoseDialog(1);
                }

            }
        });
        btnFalse.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (isFrozen)
                {
                    unFreeze(btnTrue, btnFalse, imgClock, LayoutClassicGame);
                }
                counter.cancel();
                if (checkAnswer(false))
                {
                    if (G.musicPlay)
                        correct.start();
                    gameTimeCheck = 0;
                    if (gameTimeCheck < 5001)
                        saveScore(txtScore, currentLevel * 10);
                    if (gameTimeCheck < 2001)
                        saveScore(txtScore, currentLevel * 20);

                    startCounter();
                    counter.start();
                    doNextQuestion(txtQuestion);
                    saveScore(txtScore, currentLevel * 10);

                }
                else
                {
                    if (G.musicPlay)
                        wrong.start();
                    youLoseDialog(1);
                }
            }
        });
    }


    //
    //    private StructQuestion nextQuestion()
    //    {
    //       
    //    }

    private boolean checkAnswer(boolean ans)
    {
        if (ans == question.answer)
            return !reverseMode;
        else
            return reverseMode;

    }


    private void saveScore(TextView txt, int score)
    {
        Score += score;
        txt.setText("" + Score);
        // in karo felan inja anjam bede badan bayad akhare har marhale anjam bedi

    }


    private void doNextQuestion(TextView txt)
    {

        reverseMode = getRandomBoolean();

        txtLevel.setText("" + currentLevel);
        ++correctQuestion;
        if (correctQuestion == 10)
        {
            checkNoRepeat.clear();
            correctQuestion = 0;
            if (currentLevel <= 10) // movaghatie
            {
                ++currentLevel;
                G.specialLevelBonus(currentLevel);

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
            int size = G.ArrayQuestions.size();
            Random ran = new Random();
            i = ran.nextInt(size);
            while (checkNoRepeat.contains(i) && checkNoRepeat.size() < G.ArrayQuestions.size())
            {
                i = ran.nextInt(size);
            }
            checkNoRepeat.add(i);
            question = G.ArrayQuestions.get(i);
            txt.setText(question.text);

        } else {
            int i;
            int size = G.ArrayQuestions.size();
            Random ran = new Random();
            i = ran.nextInt(size);
            while (checkNoRepeat.contains(i) && checkNoRepeat.size() < G.ArrayQuestions.size())
            {
                i = ran.nextInt(size);
            }
            checkNoRepeat.add(i);
            question = G.ArrayQuestions.get(i);
            txt.setText(question.text);
        }
        if ( !reverseMode)
        {
            txtMode.setVisibility(View.INVISIBLE);
            layoutQuestion.setBackgroundResource(R.drawable.button_question_special);
        }
        else
        {
            txtMode.setVisibility(View.VISIBLE);
            layoutQuestion.setBackgroundResource(R.drawable.button_reverse_question_special);

        }
    }


    private void youLoseDialog(int Reason)
    {

        Intent intent = new Intent(ActivityGame.this, DialogLose.class);

        intent.putExtra("reason", Reason);
        intent.putExtra("level", currentLevel);
        intent.putExtra("score", Score);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();

        final Button btnTrue = (Button) findViewById(R.id.btnTrue);
        final Button btnFalse = (Button) findViewById(R.id.btnFalse);

        final ImageView imgClock = (ImageView) findViewById(R.id.imgClock);
        final ViewGroup LayoutClassicGame = (ViewGroup) findViewById(R.id.LayoutClassicGame);

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
                        freeze(btnTrue, btnFalse, imgClock, LayoutClassicGame);
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
            overridePendingTransition(R.anim.fade_in_animation, R.anim.up_to_down);

        } else if (G.dialogChooseAction == 2) // reset
        {
            if (Build.VERSION.SDK_INT >= 11) {
                recreate();
            } else {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        } else if (G.dialogChooseAction == 3) // continue
        {
            checkContinue = true;

        }
        G.dialogChooseAction = 0;
        G.saveHighScores();

        if ( !checkContinue)
        {
            startCounter();
            counter.start();
        }
        else
        {
            checkContinue = false;
        }
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
        gameTimeCheck = 0;
        startCounter();
        counter.start();
        btnTrue.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_true));
        btnFalse.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_false));
        imgClock.setImageResource(R.drawable.ic_clock);
        BackGround.setBackgroundResource(R.drawable.bg_special);
    }


    private void startCounter()
    {
        counter = new CountDownTimer(EVERY_QUESTION_TIME - gameTimeCheck, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                gameTimeCheck += 1000;
                int i = (int) (millisUntilFinished / 1000);
                txtTime.setText("" + (i - 1));
                if (i < 6)
                    if (G.musicPlay)
                        tic_toc.start();
            }


            @Override
            public void onFinish() {
                youLoseDialog(2);

            }
        };
    }


    @Override
    protected void onPause() {
        super.onPause();
        counter.cancel();
        G.saveCoins();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        counter.cancel();
        G.saveCoins();
        G.specialSaveLevelAchieved();

        if (G.isGamePlayed)
        {
            G.gamePlayed();
            G.isGamePlayed = false;
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_animation, R.anim.up_to_down);
    }


    private boolean getRandomBoolean() {
        return Math.random() < 0.5;
        //I tried another approaches here, still the same result
    }

}