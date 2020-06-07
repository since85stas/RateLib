package stas.batura.ratelibrary;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getString(R.string.dialogTitle);
        String message = getString(R.string.dialogMessage);
        String button1String = getString(R.string.dialogYes);
        String button2String = getString(R.string.dialogNo);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);  // заголовок
        builder.setMessage(message); // сообщение
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), R.string.dialogYesthx,
                        Toast.LENGTH_SHORT).show();
//                ((MainActivity) getActivity()).okClicked();
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), R.string.dialogNoThx, Toast.LENGTH_SHORT)
                        .show();
//                ((MainActivity) getActivity()).cancelClicked();
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }
}
