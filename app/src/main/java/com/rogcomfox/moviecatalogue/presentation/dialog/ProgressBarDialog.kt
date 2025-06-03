package com.rogcomfox.moviecatalogue.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import androidx.core.graphics.drawable.toDrawable
import com.rogcomfox.moviecatalogue.databinding.DialogProgressBarBinding

class ProgressBarDialog(context: Context) : Dialog(context) {
    private lateinit var binding: DialogProgressBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogProgressBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCancelable(false)
        this.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
    }
}