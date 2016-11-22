package game;

import utilities.ConnectionDetector;
import utilities.HighScores;
import utilities.Shop;
import utilities.StructTop;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import corporation.rabbit.games.true_or_false.Commands;
import corporation.rabbit.games.true_or_false.G;
import corporation.rabbit.games.true_or_false.HelperUi;
import corporation.rabbit.games.true_or_false.R;


public class DialogTimerLose extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_timer_lose);
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());

        G.currentPlace = this;
        ViewGroup layoutShop = (ViewGroup) findViewById(R.id.layoutShop);
        Button btnExit = (Button) findViewById(R.id.btnExit);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        TextView txtCoins = (TextView) findViewById(R.id.txtCoins);
        TextView txtGameScore = (TextView) findViewById(R.id.txtGameScore);
        TextView txtHighScore = (TextView) findViewById(R.id.txtHighScore);
        ViewGroup layoutScore = (ViewGroup) findViewById(R.id.layoutScore);
        ViewGroup LayoutRanks = (ViewGroup) findViewById(R.id.LayoutRanks);

        final Animation highScoreAnimation = AnimationUtils.loadAnimation(this, R.anim.high_score);

        Intent intent = getIntent();
        final int Score = intent.getIntExtra("score", 0);

        final int highScore = G.getHighScore(G.questionType, "timer", G.reverseMode);

        if (Score > highScore)
        {
            if ( !G.reverseMode) {
                switch (G.questionType) {
                    case (1):
                        G.normalMathTimerHighScore = Score;
                        break;
                    case (2):
                        G.normalVocabTimerHighScore = Score;
                        break;
                    case (3):
                        G.normalChallengeTimerHighScore = Score;
                        break;
                }
            } else if (G.reverseMode) {
                switch (G.questionType) {
                    case (1):
                        G.reverseMathTimerHighScore = Score;
                        break;
                    case (2):
                        G.reverseVocabTimerHighScore = Score;
                        break;
                    case (3):
                        G.reverseChallengeTimerHighScore = Score;
                        break;
                }
            }
            layoutShop.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    MediaPlayer shop = MediaPlayer.create(G.context, R.raw.shop);
                    if (G.musicPlay)
                        shop.start();
                    Intent intent = new Intent(DialogTimerLose.this, Shop.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);

                }
            });

            //save high score to online database
            ConnectionDetector cnd = new ConnectionDetector(DialogTimerLose.this);
            if (cnd.isConnectingToInternet()) {
                //save high score to online database
                int code = G.getCode("timer");
                G.saveHighScore(code, Score);
            }
            else {
                G.isHighScoreSaved = false;
                Editor editor = G.preferences.edit();
                editor.putBoolean("isHighScoreSaved", G.isHighScoreSaved);
                editor.commit();
                Toast.makeText(G.context, R.string.update_highscores_in_profile, Toast.LENGTH_SHORT).show();

            }

            G.saveHighScores();
            //            G.giftDialog((Score - highScore) / 10, 0);

            if (Score > 0)
                G.coins += (Score - highScore) / 10;

            layoutScore.setVisibility(View.GONE);
            txtHighScore.startAnimation(highScoreAnimation);

        }
        if (Score > 0)
            G.coins += Score / 10;

        txtCoins.setText("" + G.coins);
        txtGameScore.setText("" + Score);
        txtHighScore.setText("" + G.getHighScore(G.questionType, "timer", G.reverseMode));

        final ProgressDialog dialogLoading = new ProgressDialog(DialogTimerLose.this);
        dialogLoading.setMessage(getResources().getString(R.string.downloading_data));

        LayoutRanks.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                new AsyncTask<Void, Void, Void>() {

                    @Override
                    public void onPreExecute() {
                        ConnectionDetector cnd = new ConnectionDetector(G.context);
                        if (cnd.isConnectingToInternet())
                        {
                            dialogLoading.show();
                        }
                    }


                    @Override
                    public void onPostExecute(Void result) {
                        ConnectionDetector cnd = new ConnectionDetector(G.context);
                        if (cnd.isConnectingToInternet())
                        {
                            dialogLoading.dismiss();

                            Intent intent = new Intent(DialogTimerLose.this, HighScores.class);
                            intent.putExtra("header", G.getType(G.questionType, "timer", G.reverseMode));
                            startActivity(intent);
                            overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
                        }
                        else
                            G.showOnUiThread(R.string.internet_required);

                    }


                    @Override
                    protected Void doInBackground(Void... arg0) {
                        ConnectionDetector cnd = new ConnectionDetector(G.context);
                        if (cnd.isConnectingToInternet())
                        {

                            int type = G.getType(G.questionType, "timer", G.reverseMode);
                            Commands.getHighScores(type);
                            int tempScore = 0;
                            if (Score > highScore)
                                tempScore = Score;
                            else
                                tempScore = highScore;
                            HighScores.myScore = tempScore;
                            HighScores.myRank = Commands.getRank(type);
                            if ( !G.CheckMyExistanceInHighScores())
                            {
                                StructTop myRecord = new StructTop(G.myID, G.username, type, G.nickName, G.profileImage, tempScore);
                                G.ArrayHighScores.add(myRecord);
                            }
                        }
                        return null;

                    }
                }.execute();

            }
        });

        btnExit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                G.dialogChooseAction = 1;
                finish();
                overridePendingTransition(R.anim.fade_in2, R.anim.yeho_gheib_she);
            }
        });
        btnReset.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                G.dialogChooseAction = 2;
                finish();
                overridePendingTransition(R.anim.fade_in2, R.anim.yeho_gheib_she);
                G.gamePlayed();

            }
        });

    }


    @Override
    public void onBackPressed() {
        G.dialogChooseAction = 1;
        finish();
        overridePendingTransition(R.anim.fade_in2, R.anim.yeho_gheib_she);
    }


    @Override
    protected void onDestroy() {
        G.isGamePlayed = true;
        super.onDestroy();
    }

}