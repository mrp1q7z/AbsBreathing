package com.yojiokisoft.absbreathing;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity implements AdListener {
    private AdView adViewBanner;
    private long mStartTime;
    private long mInhaleTime;
    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    private int mBreatheCount;
    private boolean mActiveFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        AdRequest adRequest = AdCatalogUtils.createAdRequest();
        adViewBanner = (AdView) findViewById(R.id.adViewBanner);
        adViewBanner.setAdListener(this);
        adViewBanner.loadAd(adRequest);

        mStartTime = 0;
        mInhaleTime = 0;
        mBreatheCount = 0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActiveFlag = true;
        if (mStartTime != 0) {
            reStart();
        }
    }

    private void reStart() {
        cancelTimer();
        mStartTime = 0;
        mInhaleTime = 0;
        mBreatheCount = 0;

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.container, new PlaceholderFragment())
                .commit();

    }

    @Override
    public void onPause() {
        super.onPause();
        mActiveFlag = false;
        cancelTimer();
    }

    @Override
    protected void onDestroy() {
        if (adViewBanner != null) {
            adViewBanner.destroy();
        }
        super.onDestroy();
    }

    private void cancelTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onReceiveAd(Ad ad) {
        Log.d("Banners_class", "I received an ad");
    }

    @Override
    public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode error) {
        Log.d("Banners_class", "I failed to receive an ad");
    }

    @Override
    public void onPresentScreen(Ad ad) {
        Log.d("Banners_class", "Presenting screen");
    }

    @Override
    public void onDismissScreen(Ad ad) {
        Log.d("Banners_class", "Dismissing screen");
    }

    @Override
    public void onLeaveApplication(Ad ad) {
        Log.d("Banners_class", "Leaving application");
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step1, container, false);
            return rootView;
        }
    }

    /**
     * Step2Fragment placeholder
     */
    public static class Step2Fragment extends Fragment {

        public Step2Fragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step2, container, false);
            return rootView;
        }
    }

    /**
     * Step3Fragment placeholder
     */
    public static class Step3Fragment extends Fragment {

        public Step3Fragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step3, container, false);
            TextView msg = (TextView) rootView.findViewById(R.id.message_text);
            float sec = getArguments().getFloat("sec");
            msg.setText(sec + " " + getString(R.string.step3_msg));
            return rootView;
        }
    }

    /**
     * Step4Fragment placeholder
     */
    public static class Step4Fragment extends Fragment {
        public Step4Fragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step4, container, false);
            TextView msg = (TextView) rootView.findViewById(R.id.message_text);
            float sec = getArguments().getFloat("sec");
            msg.setText(sec + " " + getString(R.string.step4_msg));
            return rootView;
        }
    }

    /**
     * Step5Fragment placeholder
     */
    public static class Step5Fragment extends Fragment {
        public Step5Fragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step5, container, false);
            TextView msg = (TextView) rootView.findViewById(R.id.message_text);
            float sec = getArguments().getFloat("sec");
            msg.setText(sec + " " + getString(R.string.step5_msg));
            return rootView;
        }
    }

    /**
     * Step6Fragment placeholder
     */
    public static class Step6Fragment extends Fragment {

        public Step6Fragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step6, container, false);
            return rootView;
        }
    }

    public void step1ButtonClicked(View view) {
        mStartTime = System.currentTimeMillis();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.container, new Step2Fragment())
                .commit();
    }

    public void step2ButtonClicked(View view) {
        if (mInhaleTime == 0) {
            mInhaleTime = System.currentTimeMillis() - mStartTime;
        }

        Step3Fragment fragment = new Step3Fragment();
        Bundle bundle = new Bundle();
        bundle.putFloat("sec", (float) mInhaleTime * 2.0f / 1000.0f);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.container, fragment)
                .commit();

        mTimer = new Timer(true);
        mTimer.schedule(new Step3TimerTask(), mInhaleTime * 2);
    }

    public void exitButtonClicked(View view) {
        finish();
    }

    class Step3TimerTask extends TimerTask {
        @Override
        public void run() {
            if (!mActiveFlag) {
                return;
            }
            mBreatheCount++;
            if (mBreatheCount >= 10) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                                .replace(R.id.container, new Step6Fragment())
                                .commit();

                        mTimer = new Timer(true);
                        mTimer.schedule(new ExitTimerTask(), 3000);
                    }
                });
            } else {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Step4Fragment fragment = new Step4Fragment();
                        Bundle bundle = new Bundle();
                        bundle.putFloat("sec", (float) mInhaleTime * 2.0f / 1000.0f);
                        fragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                                .replace(R.id.container, fragment)
                                .commit();

                        mTimer = new Timer(true);
                        mTimer.schedule(new Step4TimerTask(), mInhaleTime * 2);
                    }
                });
            }
        }
    }

    class Step4TimerTask extends TimerTask {
        @Override
        public void run() {
            if (!mActiveFlag) {
                return;
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Step5Fragment fragment = new Step5Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putFloat("sec", (float) mInhaleTime / 1000.0f);
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.container, fragment)
                            .commit();

                    mTimer = new Timer(true);
                    mTimer.schedule(new Step2TimerTask(), mInhaleTime);
                }
            });
        }
    }

    class Step2TimerTask extends TimerTask {
        @Override
        public void run() {
            if (!mActiveFlag) {
                return;
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    step2ButtonClicked(null);
                }
            });
        }
    }

    class ExitTimerTask extends TimerTask {
        @Override
        public void run() {
            if (!mActiveFlag) {
                return;
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
        }
    }
}
