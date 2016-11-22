package utilities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
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
import util.IabHelper;
import util.IabHelper.OnIabPurchaseFinishedListener;
import util.IabResult;
import util.Purchase;


public class Shop extends ActionBarActivity {

    static TextView txtCoins;
    static TextView txtSnowGunBullets;
    static IabHelper.OnConsumeFinishedListener listener = new IabHelper.OnConsumeFinishedListener() {

        @Override
        public void onConsumeFinished(Purchase purchase, IabResult result) {

            if (result.isSuccess()) {
            }
        }
    };
    private static String payload;
    public String currentSKU = "";
    private Boolean validateBoolean = false;
    OnIabPurchaseFinishedListener mPurchaseFinishedListener =
            new OnIabPurchaseFinishedListener() {

                @Override
                public void onIabPurchaseFinished(IabResult result, final Purchase purchase) {
                    final ProgressDialog dialogLoading = new ProgressDialog(Shop.this);
                    dialogLoading.setMessage(getResources().getString(R.string.check_purchase));

                    if (result.isFailure()) {
                        Log.d(G.TAG, "Error purchasing: " + result);
                        Toast.makeText(G.context, "پرداخت انجام نشد", Toast.LENGTH_LONG).show();
                        return;
                    } else if (purchase.getSku().equals(currentSKU)) {
                        new AsyncTask<Void, Void, Void>() {

                            @Override
                            public void onPreExecute() {
                                dialogLoading.show();

                            }


                            @Override
                            public void onPostExecute(Void result) {
                                dialogLoading.dismiss();
                                if (validateBoolean) {

                                    doShop(purchase);
                                    Toast.makeText(G.context, "با تشکر ، خرید شما با موفقیت صورت گرفت.",
                                            Toast.LENGTH_LONG).show();
                                    if (!G.gShop) {
                                        G.giftDialog(200, 2, R.string.gShop);
                                        G.gShop = true;
                                        Editor editor = G.preferences.edit();
                                        editor.putBoolean("gShop", G.gShop);
                                        editor.commit();
                                    }
                                    G.prepareBazaarInAppBilling(false, null);
                                }

                            }


                            @Override
                            protected Void doInBackground(Void... arg0) {

                                try {
                                    validateBoolean = Commands.checkPurchase(purchase.getSku(), purchase.getToken(), payload);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return null;

                            }
                        }.execute();

                    }
                }
            };

    private static void doShop(Purchase purchase) {
        String sku = purchase.getSku();

        //special game
        if (sku.equals(G.SKU_SPECIAL_GAME)) {
            G.bool_SPECIAL_GAME = true;
            Editor editor = G.preferences.edit();
            editor.putBoolean("bool_SPECIAL_GAME", G.bool_SPECIAL_GAME);
            editor.commit();
        }

        //time
        if (sku.equals(G.SKU_5S_TIME)) {

            G.bool_5S_TIME = true;
            Editor editor = G.preferences.edit();
            editor.putBoolean("bool_5S_TIME", G.bool_5S_TIME);
            editor.commit();
        }
        if (sku.equals(G.SKU_10S_TIME)) {
            G.bool_10S_TIME = true;
            Editor editor = G.preferences.edit();
            editor.putBoolean("bool_10S_TIME", G.bool_10S_TIME);
            editor.commit();
        }
        if (sku.equals(G.SKU_100S_TIME)) {
            G.bool_100S_TIME = true;
            Editor editor = G.preferences.edit();
            editor.putBoolean("bool_100S_TIME", G.bool_100S_TIME);
            editor.commit();
            G.RemoveAdd();

        }
        if (sku.equals(G.SKU_150S_TIME)) {
            G.bool_150S_TIME = true;
            Editor editor = G.preferences.edit();
            editor.putBoolean("bool_150S_TIME", G.bool_150S_TIME);
            editor.commit();
            G.RemoveAdd();

        }

        // coins
        if (sku.equals(G.SKU_1000_COIN)) {
            G.coins += 1000;
            G.saveCoins();
            G.mHelper.consumeAsync(purchase, listener);
        }
        if (sku.equals(G.SKU_3000_COIN)) {
            G.coins += 3000;
            G.saveCoins();
            G.mHelper.consumeAsync(purchase, listener);
            G.RemoveAdd();

        }
        if (sku.equals(G.SKU_10000_COIN)) {
            G.giftDialog(0, 20, R.string.gLotsOfCoins);
            G.coins += 10000;
            G.saveCoins();
            G.mHelper.consumeAsync(purchase, listener);
            G.RemoveAdd();

        }

        //bullets
        if (sku.equals(G.SKU_50_BULLETS)) {
            G.SnowGunBullets += 50;
            G.saveBullets();
            G.mHelper.consumeAsync(purchase, listener);
        }
        if (sku.equals(G.SKU_150_BULLETS)) {
            G.SnowGunBullets += 150;
            G.saveBullets();
            G.mHelper.consumeAsync(purchase, listener);
            G.RemoveAdd();

        }
        if (sku.equals(G.SKU_500_BULLETS)) {
            G.giftDialog(500, 0, R.string.gLotsOfBullets);
            G.SnowGunBullets += 500;
            G.saveBullets();
            G.mHelper.consumeAsync(purchase, listener);
            G.RemoveAdd();

        }
        if (sku.equals(G.SKU_ALL_ITEM)) {
            if (!G.gEveryThing) {
                G.giftDialog(1000, 50, R.string.gEveryThing);
                G.gEveryThing = true;
                Editor editor = G.preferences.edit();
                editor.putBoolean("gEveryThing", G.gEveryThing);
                editor.commit();
            }
            G.bool_ALL_ITEM = true;
            G.bool_150S_TIME = true;
            G.bool_10S_TIME = true;
            G.bool_SPECIAL_GAME = true;
            Editor editor = G.preferences.edit();
            editor.putBoolean("bool_ALL_ITEM", true);
            editor.putBoolean("bool_150S_TIME", true);
            editor.putBoolean("bool_10S_TIME", true);
            editor.putBoolean("bool_SPECIAL_GAMEd", G.bool_SPECIAL_GAME);
            editor.commit();
            G.coins += 10000;
            G.saveCoins();
            G.SnowGunBullets += 500;
            G.saveBullets();
            G.RemoveAdd();
            G.mHelper.consumeAsync(purchase, listener);

        }
        G.mHelper = null;
        txtCoins.setText("" + G.coins);
        txtSnowGunBullets.setText("" + G.SnowGunBullets);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        payload = telephonyManager.getDeviceId();
        G.prepareBazaarInAppBilling(false, null);
        View view = G.inflater.inflate(R.layout.action_bar_shop, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, new ActionBar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        final ImageView imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
        final TextView txtHeader = (TextView) view.findViewById(R.id.txtHeader);
        txtCoins = (TextView) view.findViewById(R.id.txtCoins);
        txtSnowGunBullets = (TextView) view.findViewById(R.id.txtSnowGunBullets);
        final Animation blink = AnimationUtils.loadAnimation(this, R.anim.blink);
        txtCoins.setText("" + G.coins);
        txtSnowGunBullets.setText("" + G.SnowGunBullets);
        HelperUi.persianizer((ViewGroup) view);

        txtHeader.setText(getResources().getString(R.string.shop));
        imgLogo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                imgLogo.startAnimation(blink);
            }
        });
        final Dialog infoDialog = new Dialog(this);
        infoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        G.currentPlace = this;
        Button btnBuy1000coins = (Button) findViewById(R.id.btnBuy1000coins);
        Button btnBuy3000coins = (Button) findViewById(R.id.btnBuy3000coins);
        Button btnBuy10000coins = (Button) findViewById(R.id.btnBuy10000coins);
        Button btnBuy50Bullets = (Button) findViewById(R.id.btnBuy50Bullets);
        Button btnBuy150Bullets = (Button) findViewById(R.id.btnBuy150Bullets);
        Button btnBuy500Bullets = (Button) findViewById(R.id.btnBuy500Bullets);
        Button btnBuy5s = (Button) findViewById(R.id.btnBuy5s);
        Button btnBuy10s = (Button) findViewById(R.id.btnBuy10s);
        Button btnBuy100s = (Button) findViewById(R.id.btnBuy100s);
        Button btnBuy150s = (Button) findViewById(R.id.btnBuy150s);
        Button btnBuySpecial = (Button) findViewById(R.id.btnBuySpecial);
        Button btnBuyAll = (Button) findViewById(R.id.btnBuyAll);

        ImageView imgInfo1000coins = (ImageView) findViewById(R.id.imgInfo1000coins);
        ImageView imgInfo3000coins = (ImageView) findViewById(R.id.imgInfo3000coins);
        ImageView imgInfo10000coins = (ImageView) findViewById(R.id.imgInfo10000coins);
        ImageView imgInfo50Bullets = (ImageView) findViewById(R.id.imgInfo50Bullets);
        ImageView imgInfo150Bullets = (ImageView) findViewById(R.id.imgInfo150Bullets);
        ImageView imgInfo500Bullets = (ImageView) findViewById(R.id.imgInfo500Bullets);
        ImageView imgInfo5s = (ImageView) findViewById(R.id.imgInfo5s);
        ImageView imgInfo10s = (ImageView) findViewById(R.id.imgInfo10s);
        ImageView imgInfo100s = (ImageView) findViewById(R.id.imgInfo100s);
        ImageView imgInfo150s = (ImageView) findViewById(R.id.imgInfo150s);
        ImageView imgInfoSpecial = (ImageView) findViewById(R.id.imgInfoSpecial);
        ImageView imgInfoAll = (ImageView) findViewById(R.id.imgInfoAll);

        OnClickListener buy = new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {

                    Button btn = (Button) arg0;
                    int code = (Integer) btn.getTag();
                    currentSKU = getCurrentSKU(code);

                    G.mHelper.launchPurchaseFlow(Shop.this, currentSKU, 0, mPurchaseFinishedListener, payload);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(G.context,
                            "برنامه قادر به انجام درخواست شما نمی باشد. لطفا مجددا تلاش نمایید.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
        OnClickListener info = new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ImageView btn = (ImageView) arg0;
                int code = (Integer) btn.getTag();
                String subject = "info_" + String.valueOf(code);
                int resID = getResources().getIdentifier(subject, "string", getPackageName());
                String info = getResources().getString(resID);

                infoDialog.setContentView(R.layout.dialog_buy_info);
                ViewGroup layoutOk = (ViewGroup) infoDialog.findViewById(R.id.layoutOk);
                TextView txtInfo = (TextView) infoDialog.findViewById(R.id.txtInfo);
                infoDialog.getWindow().getAttributes().windowAnimations = R.style.ExitDialogAnimation;
                txtInfo.setText(info);
                infoDialog.show();
                layoutOk.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        infoDialog.dismiss();
                    }
                });

            }
        };

        btnBuy1000coins.setTag(0);
        btnBuy3000coins.setTag(1);
        btnBuy10000coins.setTag(2);
        btnBuy5s.setTag(3);
        btnBuy10s.setTag(4);
        btnBuy100s.setTag(5);
        btnBuy150s.setTag(6);
        btnBuy50Bullets.setTag(7);
        btnBuy150Bullets.setTag(8);
        btnBuy500Bullets.setTag(9);
        btnBuySpecial.setTag(10);
        btnBuyAll.setTag(11);

        btnBuy1000coins.setOnClickListener(buy);
        btnBuy3000coins.setOnClickListener(buy);
        btnBuy10000coins.setOnClickListener(buy);
        btnBuy50Bullets.setOnClickListener(buy);
        btnBuy150Bullets.setOnClickListener(buy);
        btnBuy500Bullets.setOnClickListener(buy);
        btnBuy5s.setOnClickListener(buy);
        btnBuy10s.setOnClickListener(buy);
        btnBuy100s.setOnClickListener(buy);
        btnBuy150s.setOnClickListener(buy);
        btnBuySpecial.setOnClickListener(buy);
        btnBuyAll.setOnClickListener(buy);

        imgInfo1000coins.setTag(0);
        imgInfo3000coins.setTag(1);
        imgInfo10000coins.setTag(2);
        imgInfo5s.setTag(3);
        imgInfo10s.setTag(4);
        imgInfo100s.setTag(5);
        imgInfo150s.setTag(6);
        imgInfo50Bullets.setTag(7);
        imgInfo150Bullets.setTag(8);
        imgInfo500Bullets.setTag(9);
        imgInfoSpecial.setTag(10);
        imgInfoAll.setTag(11);

        imgInfo1000coins.setOnClickListener(info);
        imgInfo3000coins.setOnClickListener(info);
        imgInfo10000coins.setOnClickListener(info);
        imgInfo50Bullets.setOnClickListener(info);
        imgInfo150Bullets.setOnClickListener(info);
        imgInfo500Bullets.setOnClickListener(info);
        imgInfo5s.setOnClickListener(info);
        imgInfo10s.setOnClickListener(info);
        imgInfo100s.setOnClickListener(info);
        imgInfo150s.setOnClickListener(info);
        imgInfoSpecial.setOnClickListener(info);
        imgInfoAll.setOnClickListener(info);

        if (G.bool_SPECIAL_GAME) {
            btnBuySpecial.setVisibility(View.INVISIBLE);
        }
        if (G.bool_5S_TIME) {
            btnBuy5s.setVisibility(View.INVISIBLE);
        }
        if (G.bool_10S_TIME) {
            btnBuy5s.setVisibility(View.INVISIBLE);
            btnBuy10s.setVisibility(View.INVISIBLE);
        }
        if (G.bool_100S_TIME) {
            btnBuy100s.setVisibility(View.INVISIBLE);
        }
        if (G.bool_150S_TIME) {
            btnBuy100s.setVisibility(View.INVISIBLE);
            btnBuy150s.setVisibility(View.INVISIBLE);
        }
        if (G.bool_ALL_ITEM) {
            btnBuyAll.setVisibility(View.INVISIBLE);
            btnBuy100s.setVisibility(View.INVISIBLE);
            btnBuy150s.setVisibility(View.INVISIBLE);
            btnBuy5s.setVisibility(View.INVISIBLE);
            btnBuy10s.setVisibility(View.INVISIBLE);
            btnBuySpecial.setVisibility(View.INVISIBLE);

        }
    }

    public String getCurrentSKU(int code) {

        switch (code) {
            case 0:
                return G.SKU_1000_COIN;
            case 1:
                return G.SKU_3000_COIN;
            case 2:
                return G.SKU_10000_COIN;
            case 3:
                return G.SKU_5S_TIME;
            case 4:
                return G.SKU_10S_TIME;
            case 5:
                return G.SKU_100S_TIME;
            case 6:
                return G.SKU_150S_TIME;
            case 7:
                return G.SKU_50_BULLETS;
            case 8:
                return G.SKU_150_BULLETS;
            case 9:
                return G.SKU_500_BULLETS;
            case 10:
                return G.SKU_SPECIAL_GAME;
            case 11:
                return G.SKU_ALL_ITEM;

        }
        return "";
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!G.mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.come_in_right_to_left, R.anim.chap_be_rast);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (G.mHelper != null)
            G.mHelper.dispose();
        G.mHelper = null;
    }
}
