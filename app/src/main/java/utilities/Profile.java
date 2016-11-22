package utilities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import corporation.rabbit.games.true_or_false.Commands;
import corporation.rabbit.games.true_or_false.G;
import corporation.rabbit.games.true_or_false.HelperUi;
import corporation.rabbit.games.true_or_false.R;


public class Profile extends ActionBarActivity {

    private boolean classicReverseLayoutVisibility = false;
    private boolean classicLayoutVisibility        = false;
    private boolean timerLayoutVisibility          = false;
    private boolean timerReverseLayoutVisibility   = false;
    private boolean specialLayoutVisibility        = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());

        final ConnectionDetector cnd = new ConnectionDetector(Profile.this);

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

        final ImageView imgProfile = (ImageView) findViewById(R.id.imgProfile);

        imgProfile.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ConnectionDetector cnd = new ConnectionDetector(Profile.this);
                if (cnd.isConnectingToInternet())
                {

                    Intent intent = new Intent(Profile.this, DialogCharacter.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.down_to_up, R.anim.fade_out_animation);
                    if ( !G.gChooseProfilePhoto)
                    {
                        G.giftDialog(50, 0, R.string.gChooseProfilePhoto);
                        G.gChooseProfilePhoto = true;
                        Editor editor = G.preferences.edit();
                        editor.putBoolean("gChooseProfilePhoto", G.gChooseProfilePhoto);
                        editor.commit();
                    }
                }
                else
                {
                    Toast.makeText(G.context, R.string.internet_required, Toast.LENGTH_SHORT).show();

                }

            }
        });

        txtUsername.setText("" + G.username);
        txtNickname.setText(G.getNickname(G.nickName));

        txtNickname.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (cnd.isConnectingToInternet())
                {
                    Intent intent = new Intent(Profile.this, NickNameChoose.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
                    if ( !G.gChooseNickName)
                    {
                        G.giftDialog(0, 3, R.string.gChooseNickName);
                        G.gChooseNickName = true;
                        Editor editor = G.preferences.edit();
                        editor.putBoolean("gChooseNickName", G.gChooseNickName);
                        editor.commit();
                    }
                }
                else
                {
                    Toast.makeText(G.context, R.string.internet_required, Toast.LENGTH_SHORT).show();

                }
            }
        });

        txtUsername.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (cnd.isConnectingToInternet())
                {

                    final Dialog dialogChangeUsername = new Dialog(Profile.this, R.style.ExitDialog);
                    dialogChangeUsername.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogChangeUsername.setContentView(R.layout.dialog_edit);
                    Button btnOk = (Button) dialogChangeUsername.findViewById(R.id.btnOk);
                    Button btnCancel = (Button) dialogChangeUsername.findViewById(R.id.btnCancel);
                    final EditText edtEdit = (EditText) dialogChangeUsername.findViewById(R.id.edtEdit);
                    final ProgressBar prgEdit = (ProgressBar) dialogChangeUsername.findViewById(R.id.prgEdit);
                    dialogChangeUsername.show();
                    prgEdit.setVisibility(View.GONE);
                    btnOk.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            final String newUsername = edtEdit.getText().toString();
                            if (newUsername.equals(""))
                            {
                                Toast.makeText(G.context, R.string.empty_field, Toast.LENGTH_SHORT).show();
                            } else
                            {

                                new AsyncTask<Void, Void, Void>() {

                                    @Override
                                    public void onPreExecute() {
                                        prgEdit.setVisibility(View.VISIBLE);

                                    }


                                    @Override
                                    public void onPostExecute(Void result) {
                                        G.showOnUiThread(R.string.successfully_changed_username);
                                        prgEdit.setVisibility(View.GONE);
                                        txtUsername.setText("" + G.username);
                                        dialogChangeUsername.dismiss();

                                    }


                                    @Override
                                    protected Void doInBackground(Void... arg0) {
                                        ConnectionDetector cnd = new ConnectionDetector(G.context);

                                        if (cnd.isConnectingToInternet())
                                        {
                                            Commands.UpdateUsername(G.email, newUsername);
                                            G.username = newUsername;
                                            Editor editor = G.preferences.edit();
                                            editor.putString("username", newUsername);
                                            editor.commit();

                                        }

                                        else
                                        {
                                            G.showOnUiThread(R.string.register_no_internet);
                                        }
                                        return null;

                                    }
                                }.execute();

                            }

                        }
                    });

                    btnCancel.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            dialogChangeUsername.dismiss();
                        }
                    });

                }
                else
                    Toast.makeText(G.context, R.string.internet_required, Toast.LENGTH_SHORT).show();
            }
        });

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
                if (G.bool_SPECIAL_GAME)
                {
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
                } else {

                    Toast.makeText(G.context, R.string.purchase_special_mode, Toast.LENGTH_SHORT).show();

                }

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

        layoutClassicMath.setTag(1);
        layoutClassicVocab.setTag(2);
        layoutClassicChallenge.setTag(3);
        layoutTimerMath.setTag(4);
        layoutTimerVocab.setTag(5);
        layoutTimerChallenge.setTag(6);

        layoutClassicMathReverse.setTag(7);
        layoutClassicVocabReverse.setTag(8);
        layoutClassicChallengeReverse.setTag(9);
        layoutTimerMathReverse.setTag(10);
        layoutTimerVocabReverse.setTag(11);
        layoutTimerChallengeReverse.setTag(12);
        layoutSpecial.setTag(13);

        OnClickListener showHighScores = new OnClickListener() {

            @Override
            public void onClick(View view) {
                ViewGroup btn = (ViewGroup) view;
                int tag = (Integer) btn.getTag();
                //                goToHighscores(Score, questionType, mode, reverseMode)  ;              

                switch (tag)
                {
                    case 1:
                        goToHighscores(G.normalMathClassicHighScore, 1, "classic", false);
                        break;
                    case 2:
                        goToHighscores(G.normalMathClassicHighScore, 2, "classic", false);
                        break;
                    case 3:
                        goToHighscores(G.normalMathClassicHighScore, 3, "classic", false);
                        break;
                    case 4:
                        goToHighscores(G.normalMathClassicHighScore, 1, "timer", false);
                        break;
                    case 5:
                        goToHighscores(G.normalMathClassicHighScore, 2, "timer", false);
                        break;
                    case 6:
                        goToHighscores(G.normalMathClassicHighScore, 3, "timer", false);
                        break;
                    case 7:
                        goToHighscores(G.normalMathClassicHighScore, 1, "classic", true);
                        break;
                    case 8:
                        goToHighscores(G.normalMathClassicHighScore, 2, "classic", true);
                        break;
                    case 9:
                        goToHighscores(G.normalMathClassicHighScore, 3, "classic", true);
                        break;
                    case 10:
                        goToHighscores(G.normalMathClassicHighScore, 1, "timer", true);
                        break;
                    case 11:
                        goToHighscores(G.normalMathClassicHighScore, 2, "timer", true);
                        break;
                    case 12:
                        goToHighscores(G.normalMathClassicHighScore, 3, "timer", true);
                        break;
                    case 13:
                        goToHighscores(G.normalMathClassicHighScore, 3, "special", true);
                        break;

                }
            }
        };
        layoutClassicMath.setOnClickListener(showHighScores);
        layoutClassicVocab.setOnClickListener(showHighScores);
        layoutClassicChallenge.setOnClickListener(showHighScores);
        layoutTimerMath.setOnClickListener(showHighScores);
        layoutTimerVocab.setOnClickListener(showHighScores);
        layoutTimerChallenge.setOnClickListener(showHighScores);
        layoutClassicMathReverse.setOnClickListener(showHighScores);
        layoutClassicVocabReverse.setOnClickListener(showHighScores);
        layoutClassicChallengeReverse.setOnClickListener(showHighScores);
        layoutTimerMathReverse.setOnClickListener(showHighScores);
        layoutTimerVocabReverse.setOnClickListener(showHighScores);
        layoutTimerChallengeReverse.setOnClickListener(showHighScores);
        layoutSpecial.setOnClickListener(showHighScores);

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
        txtNickname.setText("" + G.getNickname(G.nickName));
        txtHeader.setText(getResources().getString(R.string.my_profile));
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

        txtHighScoreClassicMath.setText("" + G.normalMathClassicHighScore);
        txtHighScoreClassicVocab.setText("" + G.normalVocabClassicHighScore);
        txtHighScoreClassicChallenge.setText("" + G.normalChallengeClassicHighScore);

        txtHighScoreClassicMathReverse.setText("" + G.reverseMathClassicHighScore);
        txtHighScoreClassicVocabReverse.setText("" + G.reverseVocabClassicHighScore);
        txtHighScoreClassicChallengeReverse.setText("" + G.reverseChallengeClassicHighScore);

        txtHighScoreTimerMath.setText("" + G.normalMathTimerHighScore);
        txtHighScoreTimerVocab.setText("" + G.normalVocabTimerHighScore);
        txtHighScoreTimerChallenge.setText("" + G.normalChallengeTimerHighScore);

        txtHighScoreTimerMathReverse.setText("" + G.reverseMathTimerHighScore);
        txtHighScoreTimerVocabReverse.setText("" + G.reverseVocabTimerHighScore);
        txtHighScoreTimerChallengeReverse.setText("" + G.reverseChallengeTimerHighScore);

        txtHighScoreSpecial.setText("" + G.specialGameHighScore);
        if (G.profileImage != 0)
        {
            int resourceId = G.getProfileImageID(G.profileImage);
            imgProfile.setImageResource(resourceId);

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.come_in_right_to_left, R.anim.chap_be_rast);
    }


    // too noskhe badi ino dashte bashe ke az safe profile betoone bere high socre haye har category ro bebine
    private void goToHighscores(final int Score, final int questionType, final String mode, final boolean reverseMode)
    {

        final ProgressDialog dialogLoading = new ProgressDialog(Profile.this);
        dialogLoading.setMessage(getResources().getString(R.string.downloading_data));
        new AsyncTask<Void, Void, Void>() {

            @Override
            public void onPreExecute() {
                dialogLoading.show();

            }


            @Override
            public void onPostExecute(Void result) {
                dialogLoading.dismiss();
                Intent intent = new Intent(Profile.this, HighScores.class);
                intent.putExtra("header", G.getType(questionType, mode, reverseMode));

                startActivity(intent);
                overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
            }


            @Override
            protected Void doInBackground(Void... arg0) {
                int type = G.getType(questionType, mode, reverseMode);
                Commands.getHighScores(type);
                int tempScore = 0;
                HighScores.myScore = Score;
                HighScores.myRank = Commands.getRank(type);
                if ( !G.CheckMyExistanceInHighScores())
                {
                    StructTop myRecord = new StructTop(G.myID, G.username, type, G.nickName, G.profileImage, tempScore);
                    G.ArrayHighScores.add(myRecord);
                }
                return null;

            }
        }.execute();

    }
}
