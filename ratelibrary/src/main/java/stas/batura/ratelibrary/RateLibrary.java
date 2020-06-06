package stas.batura.ratelibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class RateLibrary extends LinearLayout {


    public RateLibrary(Context context) {
        super(context);
        initialize(context);
    }

    public RateLibrary(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context){ inflate(context, R.layout.test_layout, this); }
}
