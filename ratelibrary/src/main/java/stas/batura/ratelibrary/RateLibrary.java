package stas.batura.ratelibrary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

public class RateLibrary {

    private static final String TAG = RateLibrary.class.getSimpleName();

    private DialogClick dialogClick = new DialogClick() {
        @Override
        public void onYes() {
            mRated = 1; //если прошли по ссылке то болье диалог не вылезает
            saveSettings();
            openRateIntent();
        }

        @Override
        public void onNo() {
            Log.d(TAG, "onNo: no clicked");
        }

        @Override
        public void onNever() {

        }
    };

    //preferences keys
    private static final String PREF_NAME = "NotesPref";
    private static final String NUMBER_OF_OPENS = "Opens"; // количество запусков приложения
    private static final String IS_RATED = "Rated";       // прошли ли по ссылке в маркет
    private int GOAL_OPEN_NUM = 3;
    private int mNumberOfOpens;
    private int mRated;

    private Context context;

    private FragmentManager manager;

    private SharedPreferences mSettings;

    private String marketUrl;

    String title;

    String text;

    String positivButtonText;

    String negativeButtonText;

    private RateLibrary(Context context,
                        FragmentManager manager,
                        int num,
                        String marketUrl,
                        String title,
                        String text,
                        String positivButtonText,
                        String negativeButtonText
                        ) {
        this.context = context;
        this.manager = manager;
        GOAL_OPEN_NUM = num;
        this.marketUrl  = marketUrl;
        this.title = title;
        this.positivButtonText = positivButtonText;
        this.negativeButtonText = negativeButtonText;
        init();
    }

     public void showDialog() {
        MyDialogFragment fragment = new MyDialogFragment(dialogClick, this);
        fragment.show(manager, "RateLib");
    }

    public void checkRates() {
        if (checkOpens()) {
            mNumberOfOpens = 1;
            saveSettings();
            showDialog();
        } else {
            mNumberOfOpens ++;
            saveSettings();
        }
    }

    private void init() {
        mSettings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        loadSettings();
    }

    private void saveSettings() {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(NUMBER_OF_OPENS, mNumberOfOpens);
        editor.putInt(IS_RATED, mRated);
        editor.apply();
    }

    private void loadSettings() {
        mNumberOfOpens = mSettings.getInt(NUMBER_OF_OPENS, 1);
        mRated = mSettings.getInt(IS_RATED, 0);
    }

    private boolean checkOpens() {
        if (mNumberOfOpens % GOAL_OPEN_NUM == 0 && mRated != 1) {
            return true;
        } else {
            return false;
        }

    }

    private void openRateIntent() {
        if (marketUrl != null) {
            mRated = 1;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(marketUrl));
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Wrong app path", Toast.LENGTH_LONG).show();
        }
    }


    public static class Builder {

        private Context context;

        private FragmentManager manager;

        private int num = 10;

        private String url;

        private String title = "Thx, for rating us!";

        private String text = "If you like this app, please leave us a feedback";

        private String positivButtonText = "Yes, rate now";

        private String negativeButtonText = "Maybe later";

        public Builder() {

        }

        public Builder setContext(@NonNull Context context) {
            this.context = context;
            return this;
        }

        public Builder setFragManager(@NonNull FragmentManager manager) {
            this.manager = manager;
            return this;
        }

        public Builder setNumActions(int num) {
            this.num = num;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPositivButtonText(String positivButtonText) {
            this.positivButtonText = positivButtonText;
            return this;
        }

        public Builder setNegativeButtonText(String negativeButtonText) {
            this.negativeButtonText = negativeButtonText;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public RateLibrary build() {

            return new RateLibrary(this.context, this.manager, this.num, this.url,
                    this.title, this.text, this.positivButtonText, this.negativeButtonText);
        }

    }


}
