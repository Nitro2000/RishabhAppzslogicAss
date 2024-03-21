package com.bitspan.rishabhappzslogicass

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import com.bitspan.rishabhappzslogicass.databinding.DialogFilterBinding

object DialogHelper {

    fun showFilterDialog(context: Context, callback: (filter: Int) -> Unit) {
        val pDialog = Dialog(context)
        val binding: DialogFilterBinding = DialogFilterBinding.inflate(LayoutInflater.from(context), null, false)
        pDialog.setContentView(binding.root)
        pDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pDialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        binding.applyFilter.setOnClickListener {
            when (binding.filterRadGr.checkedRadioButtonId) {
                R.id.titleRadB -> callback(1)
                R.id.dateRadB -> callback(2)
            }
            pDialog.dismiss()
        }
        pDialog.show()
    }
}