package com.rogcomfox.moviecatalogue.presentation.ui.activity

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rogcomfox.moviecatalogue.R
import com.rogcomfox.core.source.local.database.Constant
import com.rogcomfox.core.source.local.database.LocalPrefManager
import com.rogcomfox.moviecatalogue.databinding.ActivityLauncherBinding
import com.rogcomfox.moviecatalogue.util.setAppLocale

class LauncherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLauncherBinding
    private lateinit var prefManager: LocalPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.actLauncher) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // get app version
        val info = packageManager.getPackageInfo(packageName, 0)
        binding.tvAppName.text = resources.getString(R.string.tv_app_version, info.versionName)

        makeFullScreen()
    }

    private fun makeFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (window.insetsController != null) {
                val insetsController = window.insetsController
                if (insetsController != null) {
                    insetsController.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                    insetsController.systemBarsBehavior =
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            }
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    override fun attachBaseContext(base: Context) {
        val lang = prefManager.getPrefsData(Constant.APP_LANG, Constant.INDONESIA_LANG)
        super.attachBaseContext(ContextWrapper(base.setAppLocale(lang)))
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onPause() {
        super.onPause()
        finishAffinity()
    }

}
