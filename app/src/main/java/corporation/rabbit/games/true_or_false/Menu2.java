package corporation.rabbit.games.true_or_false;

import game.ActivityClassicGame;
import game.ActivityTimerGame;
import java.io.File;
import specialMode.ActivityGame;
import utilities.ConnectionDetector;
import utilities.Donation;
import utilities.Guid;
import utilities.HighScores;
import utilities.Profile;
import utilities.Setting;
import utilities.Shop;
import utilities.StructTop;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class Menu2 extends ActionBarActivity {

    ViewGroup layoutMath1;
    ViewGroup layoutMath2;
    ImageView imgMathNormal;
    ImageView imgMathReverse;
    ViewGroup layoutMath3;
    ImageView imgMathClassic;
    ImageView imgMathTimer;

    ViewGroup layoutEnglish1;
    ViewGroup layoutEnglish2;
    ImageView imgEnglishNormal;
    ImageView imgEnglishReverse;
    ViewGroup layoutEnglish3;
    ImageView imgEnglishClassic;
    ImageView imgEnglishTimer;

    ViewGroup layoutChallenge1;
    ViewGroup layoutChallenge2;
    ImageView imgChallengeNormal;
    ImageView imgChallengeReverse;
    ViewGroup layoutChallenge3;
    ImageView imgChallengeClassic;
    ImageView imgChallengeTimer;

    int       rank     = 0;

    boolean   isUpdate = false;
    TextView  txtTotalRank;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu2_root);
        G.currentPlace = this;
        if ( !G.isRegistered)
        {
            RegisterDialog();
        }
        if ( !G.gNewVersion)
        {

            G.giftDialog(500, 3, R.string.ginstall_new_version);
            G.gNewVersion = true;
            Editor editor = G.preferences.edit();
            editor.putBoolean("gNewVersion", G.gNewVersion);
            editor.commit();

        }
        //        if (G.email.equals("t.haghverdi@yahoo.com")) {
        //            G.bool_SPECIAL_GAME = true;
        //        }
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());

        layoutMath1 = (ViewGroup) findViewById(R.id.layoutMath1);
        layoutMath2 = (ViewGroup) findViewById(R.id.layoutMath2);
        imgMathNormal = (ImageView) findViewById(R.id.imgMathNormal);
        imgMathReverse = (ImageView) findViewById(R.id.imgMathReverse);
        layoutMath3 = (ViewGroup) findViewById(R.id.layoutMath3);
        imgMathClassic = (ImageView) findViewById(R.id.imgMathClassic);
        imgMathTimer = (ImageView) findViewById(R.id.imgMathTimer);
        layoutMath1.setVisibility(View.VISIBLE);
        layoutMath2.setVisibility(View.GONE);
        layoutMath3.setVisibility(View.GONE);

        layoutEnglish1 = (ViewGroup) findViewById(R.id.layoutEnglish1);
        layoutEnglish2 = (ViewGroup) findViewById(R.id.layoutEnglish2);
        imgEnglishNormal = (ImageView) findViewById(R.id.imgEnglishNormal);
        imgEnglishReverse = (ImageView) findViewById(R.id.imgEnglishReverse);
        layoutEnglish3 = (ViewGroup) findViewById(R.id.layoutEnglish3);
        imgEnglishClassic = (ImageView) findViewById(R.id.imgEnglishClassic);
        imgEnglishTimer = (ImageView) findViewById(R.id.imgEnglishTimer);
        layoutEnglish1.setVisibility(View.VISIBLE);
        layoutEnglish2.setVisibility(View.GONE);
        layoutEnglish3.setVisibility(View.GONE);

        layoutChallenge1 = (ViewGroup) findViewById(R.id.layoutChallenge1);
        layoutChallenge2 = (ViewGroup) findViewById(R.id.layoutChallenge2);
        imgChallengeNormal = (ImageView) findViewById(R.id.imgChallengeNormal);
        imgChallengeReverse = (ImageView) findViewById(R.id.imgChallengeReverse);
        layoutChallenge3 = (ViewGroup) findViewById(R.id.layoutChallenge3);
        imgChallengeClassic = (ImageView) findViewById(R.id.imgChallengeClassic);
        imgChallengeTimer = (ImageView) findViewById(R.id.imgChallengeTimer);
        layoutChallenge1.setVisibility(View.VISIBLE);
        layoutChallenge2.setVisibility(View.GONE);
        layoutChallenge3.setVisibility(View.GONE);

        layoutMath1.setOnClickListener(click);
        imgMathNormal.setOnClickListener(click);
        imgMathReverse.setOnClickListener(click);
        imgMathClassic.setOnClickListener(click);
        imgMathTimer.setOnClickListener(click);

        layoutEnglish1.setOnClickListener(click);
        imgEnglishNormal.setOnClickListener(click);
        imgEnglishReverse.setOnClickListener(click);
        imgEnglishClassic.setOnClickListener(click);
        imgEnglishTimer.setOnClickListener(click);

        layoutChallenge1.setOnClickListener(click);
        imgChallengeNormal.setOnClickListener(click);
        imgChallengeReverse.setOnClickListener(click);
        imgChallengeClassic.setOnClickListener(click);
        imgChallengeTimer.setOnClickListener(click);

        final ViewGroup layoutSpecial = (ViewGroup) findViewById(R.id.layoutSpecial);
        if (G.bool_SPECIAL_GAME)
        {
            layoutSpecial.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_special));
        } else
        {
            layoutSpecial.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_special_locked));

        }
        layoutSpecial.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (G.bool_SPECIAL_GAME)
                {
                    G.questionType = 4;
                    Intent intent = new Intent(Menu2.this, ActivityGame.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.down_to_up, R.anim.fade_out_animation);

                } else
                {
                    MediaPlayer shop = MediaPlayer.create(G.context, R.raw.shop);
                    if (G.musicPlay)
                        shop.start();
                    Intent intent = new Intent(Menu2.this, Shop.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);

                }
            }
        });

        new AsyncTask<Void, Void, Void>() {

            @Override
            public void onPreExecute() {}


            @Override
            public void onPostExecute(Void result) {
                if (isUpdate)
                    Toast.makeText(G.context, R.string.questionsUpdated, Toast.LENGTH_LONG).show();

            }


            @Override
            protected Void doInBackground(Void... arg0) {

                try {
                    final int res1 = Commands.getNewsCount(G.getLastID(1), 1);
                    final int res2 = Commands.getNewsCount(G.getLastID(2), 2);
                    final int res3 = Commands.getNewsCount(G.getLastID(3), 3);
                    final int res4 = Commands.getNewsCount(G.getLastID(4), 4);
                    if (res1 > 0)
                    {
                        Commands.addNews(G.getLastID(1), 1);
                        isUpdate = true;
                    }
                    if (res2 > 0)
                    {
                        Commands.addNews(G.getLastID(2), 2);
                        isUpdate = true;

                    }
                    if (res3 > 0)
                    {
                        Commands.addNews(G.getLastID(3), 3);
                        isUpdate = true;

                    }
                    if (res4 > 0)
                    {
                        Commands.addNews(G.getLastID(4), 4);
                        isUpdate = true;

                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return null;

            }
        }.execute();

    }


    @Override
    protected void onResume() {

        super.onResume();
        G.currentPlace = this;
        if ( !G.isHighScoreSaved)
            G.saveHighscores();

        final ConnectionDetector cnd = new ConnectionDetector(this);
        final ViewGroup layoutCaution = (ViewGroup) findViewById(R.id.layoutCaution);
        final Animation fadeOutAnim = AnimationUtils.loadAnimation(this,
                R.anim.fade_out_fast);
        layoutCaution.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                CalculateTotalRank();
                layoutCaution.startAnimation(fadeOutAnim);
                fadeOutAnim.setAnimationListener(new AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation arg0) {
                        // TODO Auto-generated method stub

                    }


                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                        // TODO Auto-generated method stub

                    }


                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        layoutCaution.setVisibility(View.GONE);

                    }
                });
            }
        });

        final ImageView imgComentToCoin = (ImageView) findViewById(R.id.imgComentToCoin);
        final ConnectionDetector cd = new ConnectionDetector(G.context);

        if (G.gComment || !cd.isConnectingToInternet())
        {
            imgComentToCoin.setVisibility(View.GONE);
        }
        imgComentToCoin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (cd.isConnectingToInternet())
                {
                    String url = "bazaar://details?id=" + getPackageName();
                    Intent next = new Intent(Intent.ACTION_EDIT);
                    try {
                        next.setData(Uri.parse(url));
                        startActivity(next);
                        Toast.makeText(getBaseContext(), "با 5 ستاره از ما حمایت کنید.", Toast.LENGTH_LONG).show();

                    }
                    catch (Exception e) {
                        next.setData(Uri.parse("http://cafebazaar.ir/"));
                        startActivity(next);

                    }

                    if ( !G.gComment)
                    {
                        G.giftDialog(500, 5, R.string.gComment);
                        G.gComment = true;
                        Editor editor = G.preferences.edit();
                        editor.putBoolean("gComment", G.gComment);
                        editor.commit();
                        imgComentToCoin.setVisibility(View.GONE);

                    }

                }
                else {
                    Toast.makeText(getBaseContext(), "برای ثبت نظر و امتیاز باید به اینترنت متصل شوید", Toast.LENGTH_LONG).show();

                }
            }
        });

        // action bar
        View view = G.inflater.inflate(R.layout.action_bar_menu, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, new ActionBar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        txtTotalRank = (TextView) view.findViewById(R.id.txtTotalRank);
        final ImageView imgSlidingMenu = (ImageView) view.findViewById(R.id.imgSlidingMenu);
        final ImageView imgTotalHighScores = (ImageView) view.findViewById(R.id.imgTotalHighScores);
        final Button txtRegister = (Button) view.findViewById(R.id.btnRegister);
        txtRegister.setVisibility(View.GONE);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        HelperUi.persianizer((ViewGroup) view);

        txtTotalRank.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                doCaution();
                CalculateTotalRank();
            }
        });
        CalculateTotalRank();
        doCaution();
        imgSlidingMenu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                setSlidingMenuProfile();
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawers();
                    imgSlidingMenu.setImageResource(R.drawable.ic_sliding_menu_open);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);

                    imgSlidingMenu.setImageResource(R.drawable.ic_sliding_menu_closed);
                }
            }
        });
        if ( !G.isRegistered)
        {
            txtRegister.setVisibility(View.VISIBLE);
        }
        txtRegister.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ConnectionDetector connectionDetector = new ConnectionDetector(Menu2.this);
                if (connectionDetector.isConnectingToInternet())
                {
                    RegisterDialog();
                    txtRegister.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(G.context, R.string.register_no_internet, Toast.LENGTH_SHORT).show();
                }

            }
        });
        OnClickListener totalHighScore = new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final ProgressDialog dialogLoading = new ProgressDialog(Menu2.this);
                dialogLoading.setMessage(getResources().getString(R.string.downloading_data));

                ConnectionDetector cnd = new ConnectionDetector(G.context);
                if (cnd.isConnectingToInternet())
                {
                    if (G.isRegistered)
                    {
                        new AsyncTask<Void, Void, Void>() {

                            @Override
                            public void onPreExecute() {
                                dialogLoading.show();
                            }


                            @Override
                            public void onPostExecute(Void result) {
                                dialogLoading.dismiss();
                                Intent intent = new Intent(Menu2.this, HighScores.class);
                                intent.putExtra("header", 0);
                                startActivity(intent);
                                overridePendingTransition(R.anim.come_in_right_to_left, R.anim.chap_be_rast);
                            }


                            @Override
                            protected Void doInBackground(Void... arg0) {
                                Commands.getHighScores(14);
                                HighScores.myScore = G.getMyTotalScore();
                                HighScores.myRank = Commands.getRank(14);
                                if ( !G.CheckMyExistanceInHighScores())
                                {
                                    StructTop myRecord = new StructTop(G.myID, G.username, 14, G.nickName, G.profileImage, G.getMyTotalScore());
                                    G.ArrayHighScores.add(myRecord);
                                }
                                return null;

                            }
                        }.execute();
                    }
                    else
                        Toast.makeText(G.context, R.string.register_to_see_ranks, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(G.context, R.string.internet_required, Toast.LENGTH_LONG).show();

            }
        };
        imgTotalHighScores.setOnClickListener(totalHighScore);
        txtTotalRank.setOnClickListener(totalHighScore);

        // end action bar
        ////// SLIDING MENU
        /*
         *   SLIDING MENU - NAVIGATION DRAWER
         */
        drawerLayout.setDrawerListener(new DrawerListener() {

            @Override
            public void onDrawerStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }


            @Override
            public void onDrawerSlide(View arg0, float arg1) {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawers();
                    imgSlidingMenu.setImageResource(R.drawable.ic_sliding_menu_open);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);

                    imgSlidingMenu.setImageResource(R.drawable.ic_sliding_menu_closed);
                }

            }


            @Override
            public void onDrawerOpened(View arg0) {

            }


            @Override
            public void onDrawerClosed(View arg0) {

            }
        });
        ViewGroup layoutProfile1 = (ViewGroup) drawerLayout.findViewById(R.id.layoutProfile1);
        ViewGroup layoutProfile2 = (ViewGroup) drawerLayout.findViewById(R.id.layoutProfile2);
        ViewGroup layoutShop = (ViewGroup) drawerLayout.findViewById(R.id.layoutShop);
        ViewGroup layoutComent = (ViewGroup) drawerLayout.findViewById(R.id.layoutComent);
        ViewGroup layoutSetting = (ViewGroup) drawerLayout.findViewById(R.id.layoutSetting);
        ViewGroup layoutProducts = (ViewGroup) drawerLayout.findViewById(R.id.layoutProducts);
        ViewGroup layoutGuid = (ViewGroup) drawerLayout.findViewById(R.id.layoutGuid);
        ViewGroup layoutDonate = (ViewGroup) drawerLayout.findViewById(R.id.layoutDonate);
        ViewGroup layoutSendApp = (ViewGroup) drawerLayout.findViewById(R.id.layoutSendApp);
        ViewGroup layoutLogout = (ViewGroup) drawerLayout.findViewById(R.id.layoutLogout);
        ViewGroup layoutExit = (ViewGroup) drawerLayout.findViewById(R.id.layoutExit);
        TextView txtLogout = (TextView) drawerLayout.findViewById(R.id.txtLogout);
        final MediaPlayer shop = MediaPlayer.create(G.context, R.raw.shop);
        setSlidingMenuProfile();
        if (G.isRegistered)
        {
            txtLogout.setText(R.string.logout);
        } else {
            txtLogout.setText(R.string.login);

        }
        layoutShop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (G.musicPlay)
                    shop.start();
                Intent intent = new Intent(Menu2.this, Shop.class);
                startActivity(intent);
                overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
                drawerLayout.closeDrawers();
            }
        });
        layoutSetting.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Menu2.this, Setting.class);
                startActivity(intent);
                overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
                drawerLayout.closeDrawers();

            }
        });
        layoutGuid.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Menu2.this, Guid.class);
                startActivity(intent);
                overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
                drawerLayout.closeDrawers();

            }
        });
        layoutDonate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Menu2.this, Donation.class);
                intent.putExtra("activity", "menu");
                startActivity(intent);
                overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
                drawerLayout.closeDrawers();

            }
        });
        layoutLogout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                drawerLayout.closeDrawers();

                if (G.isRegistered) {
                    G.isRegistered = false;
                    Editor editor = G.preferences.edit();
                    editor.putBoolean("isRegistered", false);
                    editor.putString("email", "");
                    editor.putString("username", "نام کاربری");
                    editor.putInt("nickName", 0);
                    editor.putInt("profileImage", 0);
                    G.profileImage = 0;
                    editor.putLong("myID", 0);
                    G.email = "";
                    G.resetScores();
                    editor.commit();
                    if (Build.VERSION.SDK_INT >= 11) {
                        recreate();
                    } else {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }
                else
                {
                    LoginDialog();
                }
            }
        });

        OnClickListener layoutProfileListener = new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (cnd.isConnectingToInternet())
                {
                    drawerLayout.closeDrawers();

                    if ( !G.isRegistered)
                    {
                        RegisterDialog();
                        txtRegister.setVisibility(View.GONE);
                    }
                    else
                    {
                        Intent intent = new Intent(Menu2.this, Profile.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
                    }

                }
                else
                {
                    Toast.makeText(G.context, R.string.internet_required, Toast.LENGTH_SHORT).show();
                }

            }
        };
        layoutProfile1.setOnClickListener(layoutProfileListener);
        layoutProfile2.setOnClickListener(layoutProfileListener);
        layoutSendApp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {
                    drawerLayout.closeDrawers();

                    PackageManager pm = G.context.getPackageManager();
                    ApplicationInfo ai = pm.getApplicationInfo(G.context.getPackageName(), 0);
                    File srcFile = new File(ai.publicSourceDir);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setType("application/*");
                    share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(srcFile));
                    startActivity(Intent.createChooser(share, "ارسال برنامه به دوستان"));
                    if ( !G.gSendApp)
                    {
                        G.giftDialog(100, 5, R.string.gSendApp);
                        G.gSendApp = true;
                        Editor editor = G.preferences.edit();
                        editor.putBoolean("gSendApp", G.gSendApp);
                        editor.commit();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        layoutProducts.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("bazaar://collection?slug=by_author&aid=rabbit"));
                startActivity(browserIntent);
                overridePendingTransition(R.anim.fade_in_animation, R.anim.up_to_down);
                drawerLayout.closeDrawers();

            }
        });
        G.prepareBazaarInAppBilling(false, null);
        layoutComent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                drawerLayout.closeDrawers();

                String url = "bazaar://details?id=" + getPackageName();
                Intent next = new Intent(Intent.ACTION_EDIT);
                try {
                    next.setData(Uri.parse(url));
                    startActivity(next);
                    Toast.makeText(getBaseContext(), "با 5 ستاره از ما حمایت کنید.", Toast.LENGTH_LONG).show();

                }
                catch (Exception e) {
                    next.setData(Uri.parse("http://cafebazaar.ir/"));
                    startActivity(next);

                }
                if ( !G.gComment)
                {
                    G.giftDialog(200, 2, R.string.gComment);
                    G.gComment = true;
                    Editor editor = G.preferences.edit();
                    editor.putBoolean("gComment", G.gComment);
                    editor.commit();
                }
            }
        });
        layoutExit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                doExit();

            }
        });
        /*
         *   END SLIDING MENU - NAVIGATION DRAWER
         *   
         */
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        G.saveCoins();
        G.saveHighScores();

    }


    @Override
    public void onBackPressed() {
        doExit();

    }

    private static long back_pressed = 0L;


    private void doExit()
    {
        if (G.onExitDialogShow)
        {
            final Dialog onExit = new Dialog(Menu2.this);

            onExit.requestWindowFeature(Window.FEATURE_NO_TITLE);
            onExit.setContentView(R.layout.dialog_exit);
            ViewGroup layoutExit = (ViewGroup) onExit.findViewById(R.id.layoutExit);
            ViewGroup layoutComent = (ViewGroup) onExit.findViewById(R.id.layoutComent);
            ViewGroup layoutCancel = (ViewGroup) onExit.findViewById(R.id.layoutCancel);
            final CheckBox checkBoxNoDialog = (CheckBox) onExit.findViewById(R.id.checkBoxNoDialog);
            onExit.getWindow().getAttributes().windowAnimations = R.style.ExitDialogAnimation;
            onExit.show();
            HelperUi.persianizer((ViewGroup) onExit.getWindow().getDecorView());

            checkBoxNoDialog.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                    if (checkBoxNoDialog.isChecked())
                    {
                        G.onExitDialogShow = false;
                    }
                    else
                    {
                        G.onExitDialogShow = true;
                    }
                    SharedPreferences.Editor editor = G.preferences.edit();
                    editor.putBoolean("onExitDialogShow", G.onExitDialogShow);
                    editor.commit();

                }
            });
            layoutComent.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    String url = "bazaar://details?id=" + getPackageName();
                    Intent next = new Intent(Intent.ACTION_EDIT);
                    try {
                        next.setData(Uri.parse(url));
                        startActivity(next);
                        Toast.makeText(getBaseContext(), "با 5 ستاره از ما حمایت کنید.", Toast.LENGTH_LONG).show();

                    }
                    catch (Exception e) {
                        next.setData(Uri.parse("http://cafebazaar.ir/"));
                        startActivity(next);
                        overridePendingTransition(R.anim.fade_in_animation, R.anim.up_to_down);

                    }
                    if ( !G.gComment)
                    {
                        G.giftDialog(200, 2, R.string.gComment);
                        G.gComment = true;
                        Editor editor = G.preferences.edit();
                        editor.putBoolean("gComment", G.gComment);
                        editor.commit();
                    }
                }
            });
            layoutCancel.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    onExit.dismiss();
                }
            });
            layoutExit.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    finish();
                    overridePendingTransition(R.anim.fade_in_animation, R.anim.up_to_down);
                }
            });

        }
        else
        {

            if (back_pressed + 5000 > System.currentTimeMillis())
            {
                super.onBackPressed();
                overridePendingTransition(R.anim.fade_in_animation, R.anim.up_to_down);
            }
            else
                Toast.makeText(getBaseContext(), R.string.exit_dialog, Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }


    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        View view = G.inflater.inflate(R.layout.action_bar_menu, null);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ImageView imgSlidingMenu = (ImageView) view.findViewById(R.id.imgSlidingMenu);

        switch (keycode) {
            case KeyEvent.KEYCODE_MENU:

                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawers();
                    imgSlidingMenu.setImageResource(R.drawable.ic_sliding_menu_open);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                    imgSlidingMenu.setImageResource(R.drawable.ic_sliding_menu_closed);
                }

        }

        return super.onKeyDown(keycode, e);
    }


    private void animat(final View hide, final View show) {
        //        hide.setVisibility(View.GONE);
        Animation fade_right_to_left = AnimationUtils.loadAnimation(this,
                R.anim.gheib_right_to_left);
        final Animation fade_in = AnimationUtils.loadAnimation(this,
                R.anim.zaher_right_to_left);

        hide.startAnimation(fade_right_to_left);

        hide.startAnimation(fade_right_to_left);
        hide.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                show.startAnimation(fade_in);
                show.setVisibility(View.VISIBLE);

            }
        }, 300);

    }


    private void animat2(final View hide, final View show) {
        hide.setVisibility(View.GONE);
        Animation hide_animation = AnimationUtils.loadAnimation(this,
                R.anim.fade_out_fast);
        final Animation show_animation = AnimationUtils.loadAnimation(this,
                R.anim.fade_in_fast);
        hide.startAnimation(hide_animation);
        hide.setVisibility(View.GONE);

        hide_animation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {

            }


            @Override
            public void onAnimationRepeat(Animation arg0) {

            }


            @Override
            public void onAnimationEnd(Animation arg0) {
                show.startAnimation(show_animation);
                show.setVisibility(View.VISIBLE);

            }
        });

    }


    private void dissAnimate(final View stand) {

        if (stand.getId() == layoutMath1.getId())
        {
            if (layoutChallenge2.getVisibility() == View.VISIBLE)
            {
                animat2(layoutChallenge2, layoutChallenge1);
            }
            if (layoutChallenge3.getVisibility() == View.VISIBLE)
            {
                animat2(layoutChallenge3, layoutChallenge1);
            }

            if (layoutEnglish2.getVisibility() == View.VISIBLE)
            {
                animat2(layoutEnglish2, layoutEnglish1);
            }
            if (layoutEnglish3.getVisibility() == View.VISIBLE)
            {
                animat2(layoutEnglish3, layoutEnglish1);
            }

        }
        if (stand.getId() == layoutEnglish1.getId())
        {
            if (layoutMath2.getVisibility() == View.VISIBLE)
            {
                animat2(layoutMath2, layoutMath1);
            }
            if (layoutMath3.getVisibility() == View.VISIBLE)
            {
                animat2(layoutMath3, layoutMath1);
            }
            if (layoutChallenge2.getVisibility() == View.VISIBLE)
            {
                animat2(layoutChallenge2, layoutChallenge1);
            }
            if (layoutChallenge3.getVisibility() == View.VISIBLE)
            {
                animat2(layoutChallenge3, layoutChallenge1);
            }

        }
        if (stand.getId() == layoutChallenge1.getId())
        {
            if (layoutMath2.getVisibility() == View.VISIBLE)
            {
                animat2(layoutMath2, layoutMath1);
            }
            if (layoutMath3.getVisibility() == View.VISIBLE)
            {
                animat2(layoutMath3, layoutMath1);
            }

            if (layoutEnglish2.getVisibility() == View.VISIBLE)
            {
                animat2(layoutEnglish2, layoutEnglish1);
            }
            if (layoutEnglish3.getVisibility() == View.VISIBLE)
            {
                animat2(layoutEnglish3, layoutEnglish1);
            }

        }

    }

    OnClickListener click = new OnClickListener() {

                              @Override
                              public void onClick(View v) {
                                  if (v.getId() == R.id.layoutMath1) {
                                      G.questionType = 1;
                                      animat(layoutMath1, layoutMath2);
                                      dissAnimate(layoutMath1);

                                  } else if (v.getId() == R.id.imgMathNormal) {
                                      G.reverseMode = false;
                                      animat(layoutMath2, layoutMath3);
                                      dissAnimate(layoutMath1);

                                  } else if (v.getId() == R.id.imgMathReverse)
                                  {
                                      G.reverseMode = true;
                                      animat(layoutMath2, layoutMath3);
                                      dissAnimate(layoutMath1);
                                  }

                                  else if (v.getId() == R.id.imgMathClassic) {
                                      dissAnimate(layoutMath1);
                                      Intent intent = new Intent(Menu2.this, ActivityClassicGame.class);
                                      startActivity(intent);
                                      overridePendingTransition(R.anim.down_to_up, R.anim.fade_out_animation);
                                      animat(layoutMath3, layoutMath1);

                                  } else if (v.getId() == R.id.imgMathTimer) {
                                      dissAnimate(layoutMath1);
                                      Intent intent = new Intent(Menu2.this, ActivityTimerGame.class);
                                      startActivity(intent);
                                      overridePendingTransition(R.anim.down_to_up, R.anim.fade_out_animation);
                                      animat(layoutMath3, layoutMath1);
                                  }

                                  if (v.getId() == R.id.layoutEnglish1) {
                                      G.questionType = 2;
                                      animat(layoutEnglish1, layoutEnglish2);
                                      dissAnimate(layoutEnglish1);

                                  } else if (v.getId() == R.id.imgEnglishNormal) {
                                      G.reverseMode = false;
                                      animat(layoutEnglish2, layoutEnglish3);
                                      dissAnimate(layoutEnglish1);

                                  } else if (v.getId() == R.id.imgEnglishReverse)
                                  {
                                      G.reverseMode = true;
                                      animat(layoutEnglish2, layoutEnglish3);
                                      dissAnimate(layoutEnglish1);
                                  }

                                  else if (v.getId() == R.id.imgEnglishClassic) {
                                      dissAnimate(layoutEnglish1);
                                      Intent intent = new Intent(Menu2.this, ActivityClassicGame.class);
                                      startActivity(intent);
                                      overridePendingTransition(R.anim.down_to_up, R.anim.fade_out_animation);
                                      animat(layoutEnglish3, layoutEnglish1);

                                  } else if (v.getId() == R.id.imgEnglishTimer) {
                                      dissAnimate(layoutEnglish1);
                                      Intent intent = new Intent(Menu2.this, ActivityTimerGame.class);
                                      startActivity(intent);
                                      overridePendingTransition(R.anim.down_to_up, R.anim.fade_out_animation);
                                      animat(layoutEnglish3, layoutEnglish1);
                                  }

                                  if (v.getId() == R.id.layoutChallenge1) {
                                      G.questionType = 3;
                                      animat(layoutChallenge1, layoutChallenge2);
                                      dissAnimate(layoutChallenge1);

                                  } else if (v.getId() == R.id.imgChallengeNormal) {
                                      G.reverseMode = false;
                                      animat(layoutChallenge2, layoutChallenge3);
                                      dissAnimate(layoutChallenge1);

                                  } else if (v.getId() == R.id.imgChallengeReverse)
                                  {
                                      G.reverseMode = true;
                                      animat(layoutChallenge2, layoutChallenge3);
                                      dissAnimate(layoutChallenge1);
                                  }

                                  else if (v.getId() == R.id.imgChallengeClassic) {
                                      dissAnimate(layoutChallenge1);
                                      Intent intent = new Intent(Menu2.this, ActivityClassicGame.class);
                                      startActivity(intent);
                                      overridePendingTransition(R.anim.down_to_up, R.anim.fade_out_animation);
                                      animat(layoutChallenge3, layoutChallenge1);

                                  } else if (v.getId() == R.id.imgChallengeTimer) {
                                      dissAnimate(layoutChallenge1);
                                      Intent intent = new Intent(Menu2.this, ActivityTimerGame.class);
                                      startActivity(intent);
                                      overridePendingTransition(R.anim.down_to_up, R.anim.fade_out_animation);
                                      animat(layoutChallenge3, layoutChallenge1);
                                  }

                              }
                          };


    private void doCaution()
    {
        final Animation fade_in_animation = AnimationUtils.loadAnimation(this,
                R.anim.fade_in_animation);

        final ConnectionDetector cnd = new ConnectionDetector(this);
        final TextView txtCaution = (TextView) findViewById(R.id.txtCaution);
        final ViewGroup layoutCaution = (ViewGroup) findViewById(R.id.layoutCaution);

        if (cnd.isConnectingToInternet())
        {
            if (G.isRegistered)
            {
                layoutCaution.setVisibility(View.GONE);

            }
            else
            {
                layoutCaution.setVisibility(View.VISIBLE);
                layoutCaution.startAnimation(fade_in_animation);
                txtCaution.setText(getResources().getText(R.string.register_later));

            }
        }
        else
        {
            layoutCaution.setVisibility(View.VISIBLE);
            layoutCaution.startAnimation(fade_in_animation);
            txtCaution.setText(getResources().getText(R.string.no_access_to_total_rank));
        }
    }


    private void CalculateTotalRank()
    {
        final ConnectionDetector cnd = new ConnectionDetector(this);
        final ProgressBar prgRank = (ProgressBar) findViewById(R.id.prgRank);
        prgRank.setVisibility(View.GONE);

        if (cnd.isConnectingToInternet())
        {
            if (G.isRegistered)
            {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    public void onPreExecute() {
                        prgRank.setVisibility(View.VISIBLE);
                        txtTotalRank.setVisibility(View.GONE);

                    }


                    @Override
                    public void onPostExecute(Void result) {
                        txtTotalRank.setVisibility(View.VISIBLE);
                        prgRank.setVisibility(View.GONE);

                        if (rank > 0)
                            txtTotalRank.setText(getResources().getString(R.string.total_rank) + " " + rank);

                        else
                        {
                            txtTotalRank.setVisibility(View.GONE);
                        }

                    }


                    @Override
                    protected Void doInBackground(Void... arg0) {
                        rank = Commands.getRank(14);
                        return null;

                    }
                }.execute();
            }
            else
            {
                txtTotalRank.setVisibility(View.GONE);
            }
        }
        else
        {
            txtTotalRank.setVisibility(View.GONE);
        }
    }


    private void setSlidingMenuProfile()
    {
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ImageView imgProfile = (ImageView) drawerLayout.findViewById(R.id.imgProfile);
        final TextView txtUsername = (TextView) drawerLayout.findViewById(R.id.txtUsername);

        if (G.profileImage != 0)
        {
            int resourceId = G.getProfileImageID(G.profileImage);
            imgProfile.setImageResource(resourceId);

        }
        else
        {
            imgProfile.setImageResource(R.drawable.ic_no_profile);
        }
        if (G.isRegistered)
        {
            txtUsername.setText("" + G.username);
        }

    }


    private void RegisterDialog()
    {

        final Dialog register = new Dialog(this);
        register.requestWindowFeature(Window.FEATURE_NO_TITLE);
        register.setContentView(R.layout.dialog_register);
        Button btnRegister = (Button) register.findViewById(R.id.btnRegister);
        Button btnLater = (Button) register.findViewById(R.id.btnLater);
        final EditText edtMail = (EditText) register.findViewById(R.id.edtMail);
        final EditText edtUsername = (EditText) register.findViewById(R.id.edtUsername);
        final EditText edtPass = (EditText) register.findViewById(R.id.edtPass);
        final ProgressBar prgRegister = (ProgressBar) register.findViewById(R.id.prgRegister);
        TextView txtAlreadyMember = (TextView) register.findViewById(R.id.txtAlreadyMember);
        prgRegister.setVisibility(View.GONE);
        register.getWindow().getAttributes().windowAnimations = R.style.ExitDialogAnimation;
        register.show();
        register.setCancelable(false);
        HelperUi.persianizer((ViewGroup) register.getWindow().getDecorView());

        btnRegister.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final String localMail = edtMail.getText().toString();
                final String localUsername = edtUsername.getText().toString();
                final String password = edtPass.getText().toString();
                if (localUsername.equals("") || localMail.equals("") || password.equals(""))
                {
                    Toast.makeText(G.context, R.string.empty_field, Toast.LENGTH_SHORT).show();

                } else {
                    if (G.isEmailValid(localMail))
                    {

                        new AsyncTask<Void, Void, Void>() {

                            @Override
                            public void onPreExecute() {
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        prgRegister.setVisibility(View.VISIBLE);
                                    }
                                });

                            }


                            @Override
                            public void onPostExecute(Void result) {
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        prgRegister.setVisibility(View.GONE);
                                        if (G.isRegistered)
                                        {
                                            if ( !G.gRegister)
                                            {
                                                G.giftDialog(200, 2, R.string.gRegister);
                                                G.gRegister = true;
                                                Editor editor = G.preferences.edit();
                                                editor.putBoolean("gRegister", G.gRegister);
                                                editor.commit();

                                            }
                                            register.dismiss();
                                            Editor editor = G.preferences.edit();
                                            editor.putBoolean("isRegistered", G.isRegistered);
                                            editor.putString("email", G.email);
                                            editor.putString("username", G.username);
                                            editor.putInt("nickName", G.nickName);
                                            editor.putLong("myID", G.myID);
                                            editor.commit();
                                            if (Build.VERSION.SDK_INT >= 11) {
                                                recreate();
                                            } else {
                                                Intent intent = getIntent();
                                                finish();
                                                startActivity(intent);
                                            }
                                        }
                                    }
                                });

                            }


                            @Override
                            protected Void doInBackground(Void... arg0) {
                                ConnectionDetector cnd = new ConnectionDetector(G.context);

                                if (cnd.isConnectingToInternet())
                                {
                                    if (Commands.Register(localMail, localUsername, password) == 0)
                                    {
                                        G.showOnUiThread(R.string.register_repetitive_mail);

                                    } else {

                                        G.isRegistered = true;
                                        G.username = localUsername;
                                        G.email = localMail;
                                        G.nickName = 0;
                                        G.myID = Commands.getID(G.email);
                                        G.resetScores();
                                        G.showOnUiThread(R.string.registered_successfully);

                                    }

                                }

                                else
                                {
                                    G.showOnUiThread(R.string.register_no_internet);
                                }
                                return null;

                            }
                        }.execute();

                    }
                    else
                    {
                        Toast.makeText(G.context, R.string.invalid_email, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnLater.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(G.context, R.string.register_later, Toast.LENGTH_LONG).show();
                G.isRegistered = false;
                register.dismiss();
            }
        });
        txtAlreadyMember.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                register.dismiss();
                LoginDialog();
            }
        });

    }


    private void LoginDialog()
    {

        final Dialog login = new Dialog(this);
        login.requestWindowFeature(Window.FEATURE_NO_TITLE);
        login.setContentView(R.layout.dialog_login);
        Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
        Button btnExit = (Button) login.findViewById(R.id.btnExit);
        final EditText edtMail = (EditText) login.findViewById(R.id.edtMail);
        final EditText edtPass = (EditText) login.findViewById(R.id.edtPass);
        final ProgressBar prgLogin = (ProgressBar) login.findViewById(R.id.prgLogin);
        TextView txtForgetPass = (TextView) login.findViewById(R.id.txtForgetPass);
        prgLogin.setVisibility(View.GONE);
        login.getWindow().getAttributes().windowAnimations = R.style.ExitDialogAnimation;
        login.show();
        login.setCancelable(false);
        HelperUi.persianizer((ViewGroup) login.getWindow().getDecorView());

        txtForgetPass.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                login.dismiss();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{ "corporation.rabbit@gmail.com" });
                i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.forgot_pass));
                i.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.forgot_pass_text));
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(G.context, R.string.no_email_client_installed, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final String localMail = edtMail.getText().toString();
                final String password = edtPass.getText().toString();
                if (localMail.equals("") || password.equals(""))
                {
                    Toast.makeText(G.context, R.string.empty_field, Toast.LENGTH_SHORT).show();

                } else {
                    if (G.isEmailValid(localMail))
                    {

                        new AsyncTask<Void, Void, Void>() {

                            @Override
                            public void onPreExecute() {
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        prgLogin.setVisibility(View.VISIBLE);
                                    }
                                });

                            }


                            @Override
                            public void onPostExecute(Void result) {
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        prgLogin.setVisibility(View.GONE);
                                        if (G.isRegistered)
                                        {
                                            login.dismiss();
                                            Editor editor = G.preferences.edit();
                                            editor.putBoolean("isRegistered", G.isRegistered);
                                            editor.putString("email", G.email);
                                            editor.putString("username", G.username);
                                            editor.putInt("nickName", G.nickName);
                                            editor.putInt("profileImage", G.profileImage);
                                            editor.putLong("myID", G.myID);
                                            editor.commit();
                                            if (Build.VERSION.SDK_INT >= 11) {
                                                recreate();
                                            } else {
                                                Intent intent = getIntent();
                                                finish();
                                                startActivity(intent);
                                            }
                                        }
                                    }
                                });

                            }


                            @Override
                            protected Void doInBackground(Void... arg0) {
                                ConnectionDetector cnd = new ConnectionDetector(G.context);

                                if (cnd.isConnectingToInternet())
                                {
                                    int loginResult = Commands.login(localMail, password);
                                    if (loginResult == 0)
                                    {
                                        G.showOnUiThread(R.string.login_invalid_pass);

                                    } else if (loginResult == 2) {

                                        G.showOnUiThread(R.string.login_invalid_mail);
                                    }
                                    else if (loginResult == 1) {
                                        G.email = localMail;
                                        G.isRegistered = true;
                                        G.myID = Commands.getID(G.email);
                                        G.showOnUiThread(R.string.logged_in_successfully);

                                    }
                                    else if (loginResult == 3)
                                    {
                                        G.showOnUiThread(R.string.login_problem);

                                    }

                                }

                                else
                                {
                                    G.showOnUiThread(R.string.register_no_internet);
                                }
                                return null;

                            }
                        }.execute();

                    }
                    else
                    {
                        Toast.makeText(G.context, R.string.invalid_email, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnExit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                login.dismiss();
            }
        });

    }

}