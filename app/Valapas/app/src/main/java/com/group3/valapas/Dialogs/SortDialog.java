package com.group3.valapas.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.group3.valapas.R;

public class SortDialog extends AppCompatDialogFragment
{
    private String selection;
    private int selectionIndex;
    ISortSelected sortSelected;

    public SortDialog (int index, ISortSelected sortSelected)
    {
        this.selectionIndex = index;
        Log.d("AAA", "in constructor: ");
        this.sortSelected = sortSelected;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Log.d("AAA", "Creating dialog: ");
        final String[] choices = getActivity().getResources().getStringArray(R.array.sortCategory);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Sort");
            builder.setSingleChoiceItems(R.array.sortCategory, selectionIndex, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selection = choices[which];
                    selectionIndex = which;
                    sortSelected.sortSelected(selection, selectionIndex);
                    dismiss();
                }
            });

        return builder.create();
    }
}
