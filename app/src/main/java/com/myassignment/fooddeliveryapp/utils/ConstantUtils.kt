package com.myassignment.fooddeliveryapp.utils

import android.content.Context
import android.widget.Toast

class ConstantUtils {
    companion object {
        fun configToast(mContext: Context?, toastMsg: String?) {
            Toast.makeText(
                mContext, toastMsg,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}