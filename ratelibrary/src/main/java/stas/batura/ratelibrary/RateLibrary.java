package stas.batura.ratelibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

public class RateLibrary {

    //preferences keys
    private static final String PREF_NAME = "NotesPref";
    public static final String NUMBER_OF_OPENS = "Opens"; // количество запусков приложения
    public static final String IS_RATED = "Rated";       // прошли ли по ссылке в маркет
    public static final int GOAL_OPEN_NUM = 40;
    public int mNumberOfOpens;
    public int mRated;

    Context context;

    FragmentManager manager;

    private SharedPreferences mSettings;

    public RateLibrary(Context context, FragmentManager manager) {
        this.context = context;
        this.manager = manager;
        init();
    }


    public void showDialog() {
        MyDialogFragment fragment = new MyDialogFragment();

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


}
