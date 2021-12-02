package com.boolokam.boolokamtv.Utils;

import android.content.Context;
import android.widget.Button;


import androidx.core.content.ContextCompat;


import com.boolokam.boolokamtv.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DialogAlert {
    SweetAlertDialog alertDialog;
    Context mContext;
    SweetAlertDialog pDialog;
    public DialogAlert(Context context)
    {
        mContext = context;
    }

    public void showDialog(String alertType,String title, String description)
    {
        switch(alertType)
        {
            case "error":
                alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
                break;

            case "warning":
                alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
                break;
            case"normal":
                alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE);
                break;
            case"success":
                alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE);
                break;
                default:
                    alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE);
                    break;
        }
        alertDialog.setTitleText(title);
        alertDialog.setContentText(description);
        alertDialog.show();
        Button btn = (Button) alertDialog.findViewById(R.id.confirm_button);
        btn.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorPrimaryDark));
    }

    public SweetAlertDialog progressDialogInit(String message, boolean isCancelable)
    {
        pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(mContext.getResources().getColor(R.color.colorPrimary));
        pDialog.setTitleText(message);
        if(isCancelable)
            pDialog.setCancelable(true);
        else
            pDialog.setCancelable(false);
        return pDialog;
    }

    public void dismissDialog(SweetAlertDialog dialog)
    {
        if(dialog!=null)
            if(dialog.isShowing())
                dialog.dismiss();
    }

    public void showDialog(SweetAlertDialog dialog)
    {
        if(dialog!=null)
            if(!dialog.isShowing())
                dialog.show();
    }
}
