package com.androidpourtous.admobbannerinterstitialexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);

        loadBanner();

        loadInterstitialAd();

        Button button = (Button) findViewById(R.id.btn_show_ad_interstitial);

        // On fait en sorte que dès qu'on clique sur notre buouton
        // on affiche la grosse pub qui couvre tout l'écran
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 *  Avant d'essayer de l'afficher, il faut vérifier qu'il est chargé
                 */
                if (mInterstitialAd != null && mInterstitialAd.isLoaded())
                    mInterstitialAd.show();

            }
        });
    }

    /**
     * Pour charger la petite bannière
     */
    private void loadBanner() {
        AdRequest adRequest = new AdRequest.Builder()
                // à utiliser simplement quand vous faîtes des tests
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Ici on commence à afficher notre publicité
        mAdView.loadAd(adRequest);
    }

    /**
     *  Pour charger notre interstitial, mais on ne l'affiche pas
     */
    private void loadInterstitialAd() {

        mInterstitialAd = new InterstitialAd(this);

        AdRequest adRequest = new AdRequest.Builder()
                // Même chose ici, il ne faut surtout pas l'oublier en test
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // On récupère depuis le ressource l'id de l'interstitiel
        mInterstitialAd.setAdUnitId(getString(R.string.id_interstitiel));

        mInterstitialAd.loadAd(adRequest);
    }

    // Quand on quitte notre activity
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    // Quand on retourne sur notre activity
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    // Avant que notre activity ne soit détruite
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }





}
