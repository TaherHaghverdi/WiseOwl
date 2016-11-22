package specialMode;

import utilities.ConnectionDetector;
import utilities.HighScores;
import utilities.Shop;
import utilities.StructTop;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import corporation.rabbit.games.true_or_false.Commands;
import corporation.rabbit.games.true_or_false.G;
import corporation.rabbit.games.true_or_false.HelperUi;
import corporation.rabbit.games.true_or_false.R;


public class DialogLose extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_lose);

        G.currentPlace = this;
        ImageView imgShop = (ImageView) findViewById(R.id.imgShop);
        TextView txtShop = (TextView) findViewById(R.id.txtShop);

        Button btnExit = (Button) findViewById(R.id.btnExit);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        Button btnContinue = (Button) findViewById(R.id.btnContinue);
        TextView txtContinue = (TextView) findViewById(R.id.txtContinue);
        ImageView imgReason = (ImageView) findViewById(R.id.imgReason);
        TextView txtCoins = (TextView) findViewById(R.id.txtCoins);

        TextView txtGameScore = (TextView) findViewById(R.id.txtGameScore);
        TextView txtHighScore = (TextView) findViewById(R.id.txtHighScore);

        ViewGroup layoutScore = (ViewGroup) findViewById(R.id.layoutScore);
        ViewGroup LayoutRanks = (ViewGroup) findViewById(R.id.LayoutRanks);
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());

        final Animation highScoreAnimation = AnimationUtils.loadAnimation(this, R.anim.high_score);
        OnClickListener goShopping = new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MediaPlayer shop = MediaPlayer.create(G.context, R.raw.shop);
                if (G.musicPlay)
                    shop.start();
                Intent intent = new Intent(DialogLose.this, Shop.class);
                startActivity(intent);
                overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);

            }
        };
        imgShop.setOnClickListener(goShopping);
        txtShop.setOnClickListener(goShopping);
        Intent intent = getIntent();
        int reason = intent.getIntExtra("reason", 0);
        final int level = intent.getIntExtra("level", 3);
        final int score = intent.getIntExtra("score", 0);
        final int highScore = G.getHighScore(G.questionType, "special", G.reverseMode);

        if (score > highScore) {
            G.specialGameHighScore = score;
            ConnectionDetector cnd = new ConnectionDetector(DialogLose.this);
            if (cnd.isConnectingToInternet()) {
                //save high score to online database
                int code = G.getCode("special");
                G.saveHighScore(code, score);
            }
            else {
                G.isHighScoreSaved = false;
                Editor editor = G.preferences.edit();
                editor.putBoolean("isHighScoreSaved", G.isHighScoreSaved);
                editor.commit();
                Toast.makeText(G.context, R.string.update_highscores_in_profile, Toast.LENGTH_SHORT).show();

            }

            G.saveHighScores();
            //            Toast.makeText(G.context, (score - highScore) / 10 + " سکه دریافت کردید", Toast.LENGTH_SHORT).show();
            G.coins += (score - highScore) / 10;
            layoutScore.setVisibility(View.GONE);
            txtHighScore.startAnimation(highScoreAnimation);

        }

        final ProgressDialog dialogLoading = new ProgressDialog(DialogLose.this);
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
                        dialogLoading.dismiss();
                        ConnectionDetector cnd = new ConnectionDetector(G.context);
                        if (cnd.isConnectingToInternet())
                        {
                            Intent intent = new Intent(DialogLose.this, HighScores.class);
                            intent.putExtra("header", G.getType(G.questionType, "special", G.reverseMode));

                            startActivity(intent);
                            overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
                        }
                        else
                            Toast.makeText(G.context, R.string.internet_required, Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    protected Void doInBackground(Void... arg0) {
                        ConnectionDetector cnd = new ConnectionDetector(G.context);
                        if (cnd.isConnectingToInternet())
                        {
                            int type = G.getType(G.questionType, "special", G.reverseMode);
                            Commands.getHighScores(type);
                            int tempScore = 0;
                            if (score > highScore)
                                tempScore = score;
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

        G.coins += score / 10;

        txtGameScore.setText("" + score);
        txtHighScore.setText("" + G.getHighScore(G.questionType, "special", G.reverseMode));

        txtCoins.setText("" + G.coins);
        txtContinue.setText(level * 100 + "سکه");
        if (reason == 1)
        {
            imgReason.setImageResource(R.drawable.ic_wrong_answer);

        } else if (reason == 2) {
            imgReason.setImageResource(R.drawable.ic_time_out);
        }
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
        btnContinue.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (G.coins > level * 100)
                {
                    G.coins -= level * 100;
                    G.dialogChooseAction = 3;
                    G.saveCoins();
                    finish();
                    overridePendingTransition(R.anim.fade_in2, R.anim.yeho_gheib_she);
                }
                else {
                    Toast.makeText(G.context, "" + getResources().getString(R.string.not_enough_coins), Toast.LENGTH_LONG).show();
                }

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