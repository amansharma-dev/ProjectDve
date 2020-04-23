package com.example.projectdve.AlertDialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;

import com.example.projectdve.R;

public class AddDialog extends AppCompatDialogFragment implements View.OnClickListener {


    public static final String TAG = "AddDialog";
    private AlertDialog.Builder builder;

    private EditText bookName;
    private EditText authorName;
    private EditText price;
    private Button saveBtn;
    private CardView cardView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_popup,null);
        bookName = view.findViewById(R.id.bookNameDialog_editText);
        authorName = view.findViewById(R.id.authorNameDialog_editText);
        price = view.findViewById(R.id.priceDialog_editText);
        saveBtn = view.findViewById(R.id.saveBtnDialog_button);
        cardView = view.findViewById(R.id.dialogPopup_cardView);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.colorCard));

        saveBtn.setOnClickListener(this);

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: save button clicked");
    }
}
