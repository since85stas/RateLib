package stas.batura.ratelibrary;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {

    private DialogClick inter;

    private RateLibrary library;

    public MyDialogFragment(DialogClick inter, RateLibrary library) {
        this.inter = inter;
        this.library = library;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = library.title;
        String message = library.text;
        String button1String = library.positivButtonText;
        String button2String = library.negativeButtonText;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);  // заголовок
        builder.setMessage(message); // сообщение
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), R.string.dialogYesthx,
                        Toast.LENGTH_SHORT).show();
                inter.onYes();
//                ((MainActivity) getActivity()).okClicked();
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), R.string.dialogNoThx, Toast.LENGTH_SHORT)
                        .show();
                inter.onNo();
//                ((MainActivity) getActivity()).cancelClicked();
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }
}
