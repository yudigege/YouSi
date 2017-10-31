package com.example.qsys.yousi.common.widget.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.activity.IdeaActivity;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.ActivityUtils;

/**
 * @author hanshaokai
 * @date 2017/10/27 15:27
 */


public class IdeaSelectDialog extends DialogFragment {

    public IdeaSelectDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View diagView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_select_idea,
                null, false);
        LinearLayout daily = (LinearLayout) diagView.findViewById(R.id.daily_layout);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.IDEA_STYPE, Constant.DAYLIE);
                ActivityUtils.startActivity(bundle, getActivity(), IdeaActivity.class);
                dismiss();
            }
        });
        LinearLayout read = (LinearLayout) diagView.findViewById(R.id.read_pression_layout);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.IDEA_STYPE, Constant.READPRESSION);
                ActivityUtils.startActivity(bundle, getActivity(), IdeaActivity.class);
                dismiss();

            }
        });
        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(getActivity()).setView
                (diagView)
                .create();
        // alertDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);//不加 则shape不作用
        return alertDialog;
    }

    public static DialogFragment newInstance() {

        return new IdeaSelectDialog();

    }
}
