package utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import corporation.rabbit.games.true_or_false.Commands;
import corporation.rabbit.games.true_or_false.G;
import corporation.rabbit.games.true_or_false.HelperUi;
import corporation.rabbit.games.true_or_false.R;


public class HighScores extends ActionBarActivity {

    public static int myRank;
    public static int myScore;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_scores);
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());
        if ( !G.gEnterHighScores)
        {
            G.giftDialog(50, 0, R.string.gEnterHighScores);
            G.gEnterHighScores = true;
            Editor editor = G.preferences.edit();
            editor.putBoolean("gEnterHighScores", G.gEnterHighScores);
            editor.commit();
        }
        final Animation highScoreAnimation = AnimationUtils.loadAnimation(this, R.anim.high_score);

        ViewGroup LayoutScore1 = (ViewGroup) findViewById(R.id.LayoutScore1);
        ViewGroup LayoutScore2 = (ViewGroup) findViewById(R.id.LayoutScore2);
        ViewGroup LayoutScore3 = (ViewGroup) findViewById(R.id.LayoutScore3);
        ViewGroup LayoutScore4 = (ViewGroup) findViewById(R.id.LayoutScore4);
        ViewGroup LayoutScore5 = (ViewGroup) findViewById(R.id.LayoutScore5);
        ViewGroup LayoutScore6 = (ViewGroup) findViewById(R.id.LayoutScore6);
        ViewGroup LayoutScore7 = (ViewGroup) findViewById(R.id.LayoutScore7);
        ViewGroup LayoutScore8 = (ViewGroup) findViewById(R.id.LayoutScore8);
        ViewGroup LayoutScore9 = (ViewGroup) findViewById(R.id.LayoutScore9);
        ViewGroup LayoutScore10 = (ViewGroup) findViewById(R.id.LayoutScore10);
        ViewGroup LayoutScore11 = (ViewGroup) findViewById(R.id.LayoutScore11);

        ImageView imgNum1 = (ImageView) findViewById(R.id.imgNum1);
        ImageView imgNum2 = (ImageView) findViewById(R.id.imgNum2);
        ImageView imgNum3 = (ImageView) findViewById(R.id.imgNum3);
        ImageView imgNum4 = (ImageView) findViewById(R.id.imgNum4);
        ImageView imgNum5 = (ImageView) findViewById(R.id.imgNum5);
        ImageView imgNum6 = (ImageView) findViewById(R.id.imgNum6);
        ImageView imgNum7 = (ImageView) findViewById(R.id.imgNum7);
        ImageView imgNum8 = (ImageView) findViewById(R.id.imgNum8);
        ImageView imgNum9 = (ImageView) findViewById(R.id.imgNum9);
        ImageView imgNum10 = (ImageView) findViewById(R.id.imgNum10);
        ImageView imgNum11 = (ImageView) findViewById(R.id.imgNum11);

        TextView txtUser1 = (TextView) findViewById(R.id.txtUser1);
        TextView txtUser2 = (TextView) findViewById(R.id.txtUser2);
        TextView txtUser3 = (TextView) findViewById(R.id.txtUser3);
        TextView txtUser4 = (TextView) findViewById(R.id.txtUser4);
        TextView txtUser5 = (TextView) findViewById(R.id.txtUser5);
        TextView txtUser6 = (TextView) findViewById(R.id.txtUser6);
        TextView txtUser7 = (TextView) findViewById(R.id.txtUser7);
        TextView txtUser8 = (TextView) findViewById(R.id.txtUser8);
        TextView txtUser9 = (TextView) findViewById(R.id.txtUser9);
        TextView txtUser10 = (TextView) findViewById(R.id.txtUser10);
        TextView txtUser11 = (TextView) findViewById(R.id.txtUser11);

        TextView txtNickName1 = (TextView) findViewById(R.id.txtNickName1);
        TextView txtNickName2 = (TextView) findViewById(R.id.txtNickName2);
        TextView txtNickName3 = (TextView) findViewById(R.id.txtNickName3);
        TextView txtNickName4 = (TextView) findViewById(R.id.txtNickName4);
        TextView txtNickName5 = (TextView) findViewById(R.id.txtNickName5);
        TextView txtNickName6 = (TextView) findViewById(R.id.txtNickName6);
        TextView txtNickName7 = (TextView) findViewById(R.id.txtNickName7);
        TextView txtNickName8 = (TextView) findViewById(R.id.txtNickName8);
        TextView txtNickName9 = (TextView) findViewById(R.id.txtNickName9);
        TextView txtNickName10 = (TextView) findViewById(R.id.txtNickName10);
        TextView txtNickName11 = (TextView) findViewById(R.id.txtNickName11);

        TextView txtRank1 = (TextView) findViewById(R.id.txtRank1);
        TextView txtRank2 = (TextView) findViewById(R.id.txtRank2);
        TextView txtRank3 = (TextView) findViewById(R.id.txtRank3);
        TextView txtRank4 = (TextView) findViewById(R.id.txtRank4);
        TextView txtRank5 = (TextView) findViewById(R.id.txtRank5);
        TextView txtRank6 = (TextView) findViewById(R.id.txtRank6);
        TextView txtRank7 = (TextView) findViewById(R.id.txtRank7);
        TextView txtRank8 = (TextView) findViewById(R.id.txtRank8);
        TextView txtRank9 = (TextView) findViewById(R.id.txtRank9);
        TextView txtRank10 = (TextView) findViewById(R.id.txtRank10);
        TextView txtRank11 = (TextView) findViewById(R.id.txtRank11);

        TextView txtScore1 = (TextView) findViewById(R.id.txtScore1);
        TextView txtScore2 = (TextView) findViewById(R.id.txtScore2);
        TextView txtScore3 = (TextView) findViewById(R.id.txtScore3);
        TextView txtScore4 = (TextView) findViewById(R.id.txtScore4);
        TextView txtScore5 = (TextView) findViewById(R.id.txtScore5);
        TextView txtScore6 = (TextView) findViewById(R.id.txtScore6);
        TextView txtScore7 = (TextView) findViewById(R.id.txtScore7);
        TextView txtScore8 = (TextView) findViewById(R.id.txtScore8);
        TextView txtScore9 = (TextView) findViewById(R.id.txtScore9);
        TextView txtScore10 = (TextView) findViewById(R.id.txtScore10);
        TextView txtScore11 = (TextView) findViewById(R.id.txtScore11);

        txtRank1.setText(getResources().getString(R.string.rank) + "1");
        txtRank2.setText(getResources().getString(R.string.rank) + "2");
        txtRank3.setText(getResources().getString(R.string.rank) + "3");
        txtRank4.setText(getResources().getString(R.string.rank) + "4");
        txtRank5.setText(getResources().getString(R.string.rank) + "5");
        txtRank6.setText(getResources().getString(R.string.rank) + "6");
        txtRank7.setText(getResources().getString(R.string.rank) + "7");
        txtRank8.setText(getResources().getString(R.string.rank) + "8");
        txtRank9.setText(getResources().getString(R.string.rank) + "9");
        txtRank10.setText(getResources().getString(R.string.rank) + "10");

        if (G.ArrayHighScores.size() >= 10)
        {
            txtUser10.setText(G.ArrayHighScores.get(9).username);
            txtNickName10.setText("لقب:" + G.getNickname(G.ArrayHighScores.get(9).nickName));
            txtScore10.setText("" + G.ArrayHighScores.get(9).score);
            imgNum10.setImageResource(G.getProfileImageID(G.ArrayHighScores.get(9).pic));
            LayoutScore10.setTag(G.ArrayHighScores.get(9).ID);

        }
        if (G.ArrayHighScores.size() >= 9)
        {
            txtUser9.setText(G.ArrayHighScores.get(8).username);
            txtNickName9.setText("لقب:" + G.getNickname(G.ArrayHighScores.get(8).nickName));
            txtScore9.setText("" + G.ArrayHighScores.get(8).score);
            imgNum9.setImageResource(G.getProfileImageID(G.ArrayHighScores.get(8).pic));
            LayoutScore9.setTag(G.ArrayHighScores.get(8).ID);

        }
        if (G.ArrayHighScores.size() >= 8)
        {
            txtUser8.setText(G.ArrayHighScores.get(7).username);
            txtNickName8.setText("لقب:" + G.getNickname(G.ArrayHighScores.get(7).nickName));
            txtScore8.setText("" + G.ArrayHighScores.get(7).score);
            imgNum8.setImageResource(G.getProfileImageID(G.ArrayHighScores.get(7).pic));
            LayoutScore8.setTag(G.ArrayHighScores.get(7).ID);

        }
        if (G.ArrayHighScores.size() >= 7)
        {
            txtUser7.setText(G.ArrayHighScores.get(6).username);
            txtNickName7.setText("لقب:" + G.getNickname(G.ArrayHighScores.get(6).nickName));
            txtScore7.setText("" + G.ArrayHighScores.get(6).score);
            imgNum7.setImageResource(G.getProfileImageID(G.ArrayHighScores.get(6).pic));
            LayoutScore7.setTag(G.ArrayHighScores.get(6).ID);

        }
        if (G.ArrayHighScores.size() >= 6)
        {
            txtUser6.setText(G.ArrayHighScores.get(5).username);
            txtNickName6.setText("لقب:" + G.getNickname(G.ArrayHighScores.get(5).nickName));
            txtScore6.setText("" + G.ArrayHighScores.get(5).score);
            imgNum6.setImageResource(G.getProfileImageID(G.ArrayHighScores.get(5).pic));
            LayoutScore6.setTag(G.ArrayHighScores.get(5).ID);

        }
        if (G.ArrayHighScores.size() >= 5)
        {
            txtUser5.setText(G.ArrayHighScores.get(4).username);
            txtNickName5.setText("لقب:" + G.getNickname(G.ArrayHighScores.get(4).nickName));
            txtScore5.setText("" + G.ArrayHighScores.get(4).score);
            imgNum5.setImageResource(G.getProfileImageID(G.ArrayHighScores.get(4).pic));
            LayoutScore5.setTag(G.ArrayHighScores.get(4).ID);

        }
        if (G.ArrayHighScores.size() >= 4)
        {

            txtUser4.setText(G.ArrayHighScores.get(3).username);
            txtNickName4.setText("لقب:" + G.getNickname(G.ArrayHighScores.get(3).nickName));
            txtScore4.setText("" + G.ArrayHighScores.get(3).score);
            imgNum4.setImageResource(G.getProfileImageID(G.ArrayHighScores.get(3).pic));
            LayoutScore4.setTag(G.ArrayHighScores.get(3).ID);

        }
        if (G.ArrayHighScores.size() >= 3)
        {
            txtUser3.setText(G.ArrayHighScores.get(2).username);
            txtNickName3.setText("لقب:" + G.getNickname(G.ArrayHighScores.get(2).nickName));
            txtScore3.setText("" + G.ArrayHighScores.get(2).score);
            imgNum3.setImageResource(G.getProfileImageID(G.ArrayHighScores.get(2).pic));
            LayoutScore3.setTag(G.ArrayHighScores.get(2).ID);

        }
        if (G.ArrayHighScores.size() >= 2)
        {
            txtUser2.setText(G.ArrayHighScores.get(1).username);
            txtNickName2.setText("لقب:" + G.getNickname(G.ArrayHighScores.get(1).nickName));
            txtScore2.setText("" + G.ArrayHighScores.get(1).score);
            imgNum2.setImageResource(G.getProfileImageID(G.ArrayHighScores.get(1).pic));
            LayoutScore2.setTag(G.ArrayHighScores.get(1).ID);
        }
        if (G.ArrayHighScores.size() >= 1)
        {
            txtUser1.setText(G.ArrayHighScores.get(0).username);
            txtNickName1.setText("لقب:" + G.getNickname(G.ArrayHighScores.get(0).nickName));
            txtScore1.setText("" + G.ArrayHighScores.get(0).score);
            imgNum1.setImageResource(G.getProfileImageID(G.ArrayHighScores.get(0).pic));
            LayoutScore1.setTag(G.ArrayHighScores.get(0).ID);

        }

        txtRank11.setText("رتبه: " + myRank);
        if (myRank < 11)
        {
            if (myRank == 1)
            {
                LayoutScore1.setBackgroundColor(Color.parseColor("#98cfe4"));
                txtRank1.startAnimation(highScoreAnimation);
            }
            if (myRank == 2)
            {
                LayoutScore2.setBackgroundColor(Color.parseColor("#98cfe4"));
                txtRank2.startAnimation(highScoreAnimation);

            }
            if (myRank == 3)
            {
                LayoutScore3.setBackgroundColor(Color.parseColor("#98cfe4"));
                txtRank3.startAnimation(highScoreAnimation);

            }
            if (myRank == 4)
            {
                LayoutScore4.setBackgroundColor(Color.parseColor("#98cfe4"));
                txtRank4.startAnimation(highScoreAnimation);

            }
            if (myRank == 5)
            {
                LayoutScore5.setBackgroundColor(Color.parseColor("#98cfe4"));
                txtRank5.startAnimation(highScoreAnimation);

            }
            if (myRank == 6)
            {
                LayoutScore6.setBackgroundColor(Color.parseColor("#98cfe4"));
                txtRank6.startAnimation(highScoreAnimation);

            }
            if (myRank == 7)
            {
                LayoutScore7.setBackgroundColor(Color.parseColor("#98cfe4"));
                txtRank7.startAnimation(highScoreAnimation);

            }
            if (myRank == 8)
            {
                LayoutScore8.setBackgroundColor(Color.parseColor("#98cfe4"));
                txtRank8.startAnimation(highScoreAnimation);

            }
            if (myRank == 9)
            {
                LayoutScore9.setBackgroundColor(Color.parseColor("#98cfe4"));
                txtRank9.startAnimation(highScoreAnimation);

            }
            if (myRank == 10)
            {
                LayoutScore10.setBackgroundColor(Color.parseColor("#98cfe4"));
                txtRank10.startAnimation(highScoreAnimation);

            }
            LayoutScore11.setVisibility(View.GONE);

        } else {
            LayoutScore11.setBackgroundColor(Color.parseColor("#98cfe4"));
            txtUser11.setText(G.username);
            txtNickName11.setText("لقب:" + G.getNickname(G.nickName));
            txtScore11.setText("" + myScore);
            imgNum11.setImageResource(G.getProfileImageID(G.profileImage));
            txtRank11.setText(getResources().getString(R.string.rank) + myRank);
        }

        if (G.ArrayHighScores.size() < 11)
        {
            if (G.ArrayHighScores.size() < 10)
                LayoutScore11.setVisibility(View.GONE);

            if (G.ArrayHighScores.size() < 9)
                LayoutScore10.setVisibility(View.GONE);

            if (G.ArrayHighScores.size() < 8)
                LayoutScore9.setVisibility(View.GONE);

            if (G.ArrayHighScores.size() < 7)
                LayoutScore8.setVisibility(View.GONE);

            if (G.ArrayHighScores.size() < 6)
                LayoutScore7.setVisibility(View.GONE);

            if (G.ArrayHighScores.size() < 5)
                LayoutScore6.setVisibility(View.GONE);

            if (G.ArrayHighScores.size() < 4)
                LayoutScore5.setVisibility(View.GONE);

            if (G.ArrayHighScores.size() < 3)
                LayoutScore4.setVisibility(View.GONE);

            if (G.ArrayHighScores.size() < 2)
                LayoutScore3.setVisibility(View.GONE);

            if (G.ArrayHighScores.size() < 1)
                LayoutScore2.setVisibility(View.GONE);

            if (G.ArrayHighScores.size() < 0)
                LayoutScore1.setVisibility(View.GONE);
        }
        OnClickListener rivalProfile = new OnClickListener() {

            @Override
            public void onClick(View view) {
                final ProgressDialog dialogLoading = new ProgressDialog(HighScores.this);
                dialogLoading.setMessage(getResources().getString(R.string.downloading_data));

                ViewGroup layout = (ViewGroup) view;
                final long id = (Long) layout.getTag();
                if (id == G.myID)
                {
                    Intent intent = new Intent(HighScores.this, Profile.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
                }
                else
                {
                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        public void onPreExecute() {
                            dialogLoading.show();
                        }


                        @Override
                        public void onPostExecute(Void result) {
                            dialogLoading.dismiss();
                            Intent intent = new Intent(HighScores.this, RivalProfile.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
                        }


                        @Override
                        protected Void doInBackground(Void... arg0) {
                            G.rival = Commands.getProfile(id);

                            return null;

                        }
                    }.execute();
                }
            }
        };

        LayoutScore1.setOnClickListener(rivalProfile);
        LayoutScore2.setOnClickListener(rivalProfile);
        LayoutScore3.setOnClickListener(rivalProfile);
        LayoutScore4.setOnClickListener(rivalProfile);
        LayoutScore5.setOnClickListener(rivalProfile);
        LayoutScore6.setOnClickListener(rivalProfile);
        LayoutScore7.setOnClickListener(rivalProfile);
        LayoutScore8.setOnClickListener(rivalProfile);
        LayoutScore9.setOnClickListener(rivalProfile);
        LayoutScore10.setOnClickListener(rivalProfile);
        LayoutScore11.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(HighScores.this, Profile.class);
                startActivity(intent);
                overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        View view = G.inflater.inflate(R.layout.action_bar_share, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, new ActionBar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        final ImageView imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
        final ImageView imgShare = (ImageView) view.findViewById(R.id.imgShare);
        final TextView txtHeader = (TextView) view.findViewById(R.id.txtHeader);
        final Animation blink = AnimationUtils.loadAnimation(this, R.anim.blink);
        final Animation click = AnimationUtils.loadAnimation(this, R.anim.click);
        HelperUi.persianizer((ViewGroup) view);
        txtHeader.setText(getResources().getString(R.string.high_scores));
        Intent intent = getIntent();
        if (intent.getExtras() != null)
        {
            int headerInt = intent.getIntExtra("header", 0);
            String header = getResources().getString(R.string.best) + " " + getResources().getString(G.getHighscoreHeader(headerInt));
            txtHeader.setText(header);
        }
        imgShare.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                imgShare.startAnimation(click);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                share.putExtra(Intent.EXTRA_STREAM, storeImage(G.takeScreenShot(HighScores.this)));
                startActivity(Intent.createChooser(share, "Share Image"));

            }
        });
        imgLogo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                imgLogo.startAnimation(blink);
            }
        });

    }


    private Uri storeImage(Bitmap storeImage) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        FileOutputStream fileOutputStream = null;
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file = new File(path, "cwth_" + currentDateandTime + ".jpg");
        try {
            fileOutputStream = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
        storeImage.compress(CompressFormat.JPEG, 90, bos);
        try {
            bos.flush();
            bos.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.parse("file://" + file.getAbsolutePath());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.come_in_right_to_left, R.anim.chap_be_rast);

    }
}
