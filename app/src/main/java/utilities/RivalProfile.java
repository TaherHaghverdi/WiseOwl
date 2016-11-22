package utilities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import corporation.rabbit.games.true_or_false.G;
import corporation.rabbit.games.true_or_false.HelperUi;
import corporation.rabbit.games.true_or_false.R;


public class RivalProfile extends ActionBarActivity {

    private boolean classicReverseLayoutVisibility = false;
    private boolean classicLayoutVisibility        = false;
    private boolean timerLayoutVisibility          = false;
    private boolean timerReverseLayoutVisibility   = false;
    private boolean specialLayoutVisibility        = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rival_profile);
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());

        final TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
        final TextView txtNickname = (TextView) findViewById(R.id.txtNickname);

        final ViewGroup layoutClassicMath = (ViewGroup) findViewById(R.id.layoutClassicMath);
        final ViewGroup layoutClassicVocab = (ViewGroup) findViewById(R.id.layoutClassicVocab);
        final ViewGroup layoutClassicChallenge = (ViewGroup) findViewById(R.id.layoutClassicChallenge);
        final ImageView imgClassic = (ImageView) findViewById(R.id.imgClassic);

        final ViewGroup layoutClassicMathReverse = (ViewGroup) findViewById(R.id.layoutClassicMathReverse);
        final ViewGroup layoutClassicVocabReverse = (ViewGroup) findViewById(R.id.layoutClassicVocabReverse);
        final ViewGroup layoutClassicChallengeReverse = (ViewGroup) findViewById(R.id.layoutClassicChallengeReverse);
        final ImageView imgClassicReverse = (ImageView) findViewById(R.id.imgClassicReverse);

        final ViewGroup layoutTimerMath = (ViewGroup) findViewById(R.id.layoutTimerMath);
        final ViewGroup layoutTimerVocab = (ViewGroup) findViewById(R.id.layoutTimerVocab);
        final ViewGroup layoutTimerChallenge = (ViewGroup) findViewById(R.id.layoutTimerChallenge);
        final ImageView imgTimer = (ImageView) findViewById(R.id.imgTimer);

        final ViewGroup layoutTimerMathReverse = (ViewGroup) findViewById(R.id.layoutTimerMathReverse);
        final ViewGroup layoutTimerVocabReverse = (ViewGroup) findViewById(R.id.layoutTimerVocabReverse);
        final ViewGroup layoutTimerChallengeReverse = (ViewGroup) findViewById(R.id.layoutTimerChallengeReverse);
        final ImageView imgTimerReverse = (ImageView) findViewById(R.id.imgTimerReverse);

        final ViewGroup layoutSpecial = (ViewGroup) findViewById(R.id.layoutSpecial);
        final ImageView imgSpecial = (ImageView) findViewById(R.id.imgSpecial);

        final ViewGroup layoutClassic = (ViewGroup) findViewById(R.id.layoutClassic);
        final ViewGroup layoutTimer = (ViewGroup) findViewById(R.id.layoutTimer);
        final ViewGroup layoutClassicReverse = (ViewGroup) findViewById(R.id.layoutClassicReverse);
        final ViewGroup layoutTimerReverse = (ViewGroup) findViewById(R.id.layoutTimerReverse);
        final ViewGroup layoutMotherSpecial = (ViewGroup) findViewById(R.id.layoutMotherSpecial);

        //        final ImageView imgProfile = (ImageView) findViewById(R.id.imgProfile);

        txtUsername.setText("" + G.rival.username);
        txtNickname.setText(G.getNickname(G.rival.nickName));

        layoutClassic.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (classicLayoutVisibility == false)
                {
                    imgClassic.setImageResource(R.drawable.ic_minus);
                    layoutClassicMath.setVisibility(View.VISIBLE);
                    layoutClassicVocab.setVisibility(View.VISIBLE);
                    layoutClassicChallenge.setVisibility(View.VISIBLE);

                }
                else
                {
                    imgClassic.setImageResource(R.drawable.ic_plus);
                    layoutClassicMath.setVisibility(View.GONE);
                    layoutClassicVocab.setVisibility(View.GONE);
                    layoutClassicChallenge.setVisibility(View.GONE);
                }
                classicLayoutVisibility = !classicLayoutVisibility;

            }
        });

        layoutClassicReverse.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (classicReverseLayoutVisibility == false)
                {
                    imgClassicReverse.setImageResource(R.drawable.ic_minus);
                    layoutClassicMathReverse.setVisibility(View.VISIBLE);
                    layoutClassicVocabReverse.setVisibility(View.VISIBLE);
                    layoutClassicChallengeReverse.setVisibility(View.VISIBLE);

                }
                else
                {
                    imgClassicReverse.setImageResource(R.drawable.ic_plus);
                    layoutClassicMathReverse.setVisibility(View.GONE);
                    layoutClassicVocabReverse.setVisibility(View.GONE);
                    layoutClassicChallengeReverse.setVisibility(View.GONE);
                }
                classicReverseLayoutVisibility = !classicReverseLayoutVisibility;

            }
        });

        layoutTimer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (timerLayoutVisibility == false)
                {
                    imgTimer.setImageResource(R.drawable.ic_minus);
                    layoutTimerMath.setVisibility(View.VISIBLE);
                    layoutTimerVocab.setVisibility(View.VISIBLE);
                    layoutTimerChallenge.setVisibility(View.VISIBLE);

                }
                else
                {
                    imgTimer.setImageResource(R.drawable.ic_plus);
                    layoutTimerMath.setVisibility(View.GONE);
                    layoutTimerVocab.setVisibility(View.GONE);
                    layoutTimerChallenge.setVisibility(View.GONE);
                }
                timerLayoutVisibility = !timerLayoutVisibility;

            }
        });

        layoutTimerReverse.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (timerReverseLayoutVisibility == false)
                {
                    imgTimerReverse.setImageResource(R.drawable.ic_minus);
                    layoutTimerMathReverse.setVisibility(View.VISIBLE);
                    layoutTimerVocabReverse.setVisibility(View.VISIBLE);
                    layoutTimerChallengeReverse.setVisibility(View.VISIBLE);

                }
                else
                {
                    imgTimerReverse.setImageResource(R.drawable.ic_plus);
                    layoutTimerMathReverse.setVisibility(View.GONE);
                    layoutTimerVocabReverse.setVisibility(View.GONE);
                    layoutTimerChallengeReverse.setVisibility(View.GONE);
                }
                timerReverseLayoutVisibility = !timerReverseLayoutVisibility;

            }
        });

        layoutMotherSpecial.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (specialLayoutVisibility == false)
                {
                    imgSpecial.setImageResource(R.drawable.ic_minus);
                    layoutSpecial.setVisibility(View.VISIBLE);

                }
                else
                {
                    imgSpecial.setImageResource(R.drawable.ic_plus);
                    layoutSpecial.setVisibility(View.GONE);
                }
                specialLayoutVisibility = !specialLayoutVisibility;

            }
        });

        layoutClassicMath.setVisibility(View.GONE);
        layoutClassicVocab.setVisibility(View.GONE);
        layoutClassicChallenge.setVisibility(View.GONE);
        layoutTimerMath.setVisibility(View.GONE);
        layoutTimerVocab.setVisibility(View.GONE);
        layoutTimerChallenge.setVisibility(View.GONE);
        layoutClassicMathReverse.setVisibility(View.GONE);
        layoutClassicVocabReverse.setVisibility(View.GONE);
        layoutClassicChallengeReverse.setVisibility(View.GONE);
        layoutTimerMathReverse.setVisibility(View.GONE);
        layoutTimerVocabReverse.setVisibility(View.GONE);
        layoutTimerChallengeReverse.setVisibility(View.GONE);
        layoutSpecial.setVisibility(View.GONE);

    }


    @Override
    protected void onResume() {
        super.onResume();
        final TextView txtNickname = (TextView) findViewById(R.id.txtNickname);
        final ImageView imgProfile = (ImageView) findViewById(R.id.imgProfile);
        View view = G.inflater.inflate(R.layout.action_bar_simple, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, new ActionBar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        final ImageView imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
        final ImageView imgBack = (ImageView) view.findViewById(R.id.imgBack);
        HelperUi.persianizer((ViewGroup) view);
        imgBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
                overridePendingTransition(R.anim.come_in_right_to_left, R.anim.chap_be_rast);

            }
        });
        final TextView txtHeader = (TextView) view.findViewById(R.id.txtHeader);
        final Animation blink = AnimationUtils.loadAnimation(this, R.anim.blink);
        txtNickname.setText("" + G.getNickname(G.rival.nickName));
        txtHeader.setText(getResources().getString(R.string.profile) + " " + G.rival.username);
        imgLogo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                imgLogo.startAnimation(blink);
            }
        });

        TextView txtHighScoreClassicMath = (TextView) findViewById(R.id.txtHighScoreClassicMath);
        TextView txtHighScoreClassicVocab = (TextView) findViewById(R.id.txtHighScoreClassicVocab);
        TextView txtHighScoreClassicChallenge = (TextView) findViewById(R.id.txtHighScoreClassicChallenge);

        TextView txtHighScoreTimerMath = (TextView) findViewById(R.id.txtHighScoreTimerMath);
        TextView txtHighScoreTimerVocab = (TextView) findViewById(R.id.txtHighScoreTimerVocab);
        TextView txtHighScoreTimerChallenge = (TextView) findViewById(R.id.txtHighScoreTimerChallenge);

        TextView txtHighScoreClassicMathReverse = (TextView) findViewById(R.id.txtHighScoreClassicMathReverse);
        TextView txtHighScoreClassicVocabReverse = (TextView) findViewById(R.id.txtHighScoreClassicVocabReverse);
        TextView txtHighScoreClassicChallengeReverse = (TextView) findViewById(R.id.txtHighScoreClassicChallengeReverse);

        TextView txtHighScoreTimerMathReverse = (TextView) findViewById(R.id.txtHighScoreTimerMathReverse);
        TextView txtHighScoreTimerVocabReverse = (TextView) findViewById(R.id.txtHighScoreTimerVocabReverse);
        TextView txtHighScoreTimerChallengeReverse = (TextView) findViewById(R.id.txtHighScoreTimerChallengeReverse);

        TextView txtHighScoreSpecial = (TextView) findViewById(R.id.txtHighScoreSpecial);

        txtHighScoreClassicMath.setText("" + G.rival.normalMathClassicHighScore);
        txtHighScoreClassicVocab.setText("" + G.rival.normalVocabClassicHighScore);
        txtHighScoreClassicChallenge.setText("" + G.rival.normalChallengeClassicHighScore);

        txtHighScoreClassicMathReverse.setText("" + G.rival.reverseMathClassicHighScore);
        txtHighScoreClassicVocabReverse.setText("" + G.rival.reverseVocabClassicHighScore);
        txtHighScoreClassicChallengeReverse.setText("" + G.rival.reverseChallengeClassicHighScore);

        txtHighScoreTimerMath.setText("" + G.rival.normalMathTimerHighScore);
        txtHighScoreTimerVocab.setText("" + G.rival.normalVocabTimerHighScore);
        txtHighScoreTimerChallenge.setText("" + G.rival.normalChallengeTimerHighScore);

        txtHighScoreTimerMathReverse.setText("" + G.rival.reverseMathTimerHighScore);
        txtHighScoreTimerVocabReverse.setText("" + G.rival.reverseVocabTimerHighScore);
        txtHighScoreTimerChallengeReverse.setText("" + G.rival.reverseChallengeTimerHighScore);

        txtHighScoreSpecial.setText("" + G.rival.specialGameHighScore);
        if (G.rival.profileImage != 0)
        {
            int resourceId = G.getProfileImageID(G.rival.profileImage);
            imgProfile.setImageResource(resourceId);

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.come_in_right_to_left, R.anim.chap_be_rast);
    }

}
