package com.bima.maknews.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.bima.maknews.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.card.MaterialCardView;

import java.util.Calendar;
import java.util.Date;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AdView mAdView;
    MaterialCardView cvHead, cvSports, cvTechno, cvBusiness, cvHealth, cvEntertaiment;
    TextView tvToday;
    String hariIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        cvHead = findViewById(R.id.cardHeadLine);
        cvSports = findViewById(R.id.cardSports);
        cvTechno = findViewById(R.id.cardTechno);
        cvBusiness = findViewById(R.id.cardBusiness);
        cvHealth = findViewById(R.id.cardHealth);
        cvEntertaiment = findViewById(R.id.cardEnter);

        cvHead.setOnClickListener(this);
        cvSports.setOnClickListener(this);
        cvTechno.setOnClickListener(this);
        cvBusiness.setOnClickListener(this);
        cvHealth.setOnClickListener(this);
        cvEntertaiment.setOnClickListener(this);

        tvToday = findViewById(R.id.tvDate);
        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        if (hariIni.equalsIgnoreCase("sunday")) {
            hariIni = "Minggu";
        } else if (hariIni.equalsIgnoreCase("monday")) {
            hariIni = "Senin";
        } else if (hariIni.equalsIgnoreCase("tuesday")) {
            hariIni = "Selasa";
        } else if (hariIni.equalsIgnoreCase("wednesday")) {
            hariIni = "Rabu";
        } else if (hariIni.equalsIgnoreCase("thursday")) {
            hariIni = "Kamis";
        } else if (hariIni.equalsIgnoreCase("friday")) {
            hariIni = "Jumat";
        } else if (hariIni.equalsIgnoreCase("saturday")) {
            hariIni = "Sabtu";
        }

        getToday();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d", date);
        String monthNumber = (String) DateFormat.format("M", date);
        String year = (String) DateFormat.format("yyyy", date);

        int month = Integer.parseInt(monthNumber);
        String bulan = null;
        if (month == 1) {
            bulan = "Januari";
        } else if (month == 2) {
            bulan = "Februari";
        } else if (month == 3) {
            bulan = "Maret";
        } else if (month == 4) {
            bulan = "April";
        } else if (month == 5) {
            bulan = "Mei";
        } else if (month == 6) {
            bulan = "Juni";
        } else if (month == 7) {
            bulan = "Juli";
        } else if (month == 8) {
            bulan = "Agustus";
        } else if (month == 9) {
            bulan = "September";
        } else if (month == 10) {
            bulan = "Oktober";
        } else if (month == 11) {
            bulan = "November";
        } else if (month == 12) {
            bulan = "Desember";
        }
        String formatFix = hariIni + ", " + tanggal + " " + bulan + " " + year;
        tvToday.setText(formatFix);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardHeadLine) {
            Intent intent = new Intent(MainActivity.this, HeadLineActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cardSports) {
            Intent intent = new Intent(MainActivity.this, SportsNewsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cardTechno) {
            Intent intent = new Intent(MainActivity.this, TechnologyActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cardBusiness) {
            Intent intent = new Intent(MainActivity.this, BusinessActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cardHealth) {
            Intent intent = new Intent(MainActivity.this, HealthActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cardEnter) {
            Intent intent = new Intent(MainActivity.this, EntertaimentActivity.class);
            startActivity(intent);
        }
    }

}