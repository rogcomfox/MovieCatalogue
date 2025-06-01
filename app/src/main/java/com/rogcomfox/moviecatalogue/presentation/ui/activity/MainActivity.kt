package com.rogcomfox.moviecatalogue.presentation.ui.activity

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rogcomfox.core.source.local.database.Constant
import com.rogcomfox.core.source.local.database.LocalPrefManager
import com.rogcomfox.moviecatalogue.R
import com.rogcomfox.moviecatalogue.databinding.ActivityMainBinding
import com.rogcomfox.moviecatalogue.util.setAppLocale
import org.koin.android.ext.android.inject
import kotlin.getValue

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val prefManager: LocalPrefManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.actMain) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // setup bottom navigation
        val topDestination = setOf(
            R.id.moviesFragment,
            R.id.tvSeriesFragment,
            R.id.settingsFragment
        )

        // setup bottom nav
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val navController = navHostFragment.findNavController()

        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNav.isVisible =
                destination.id == topDestination.elementAt(0)
                        || destination.id == topDestination.elementAt(1)
                        || destination.id == topDestination.elementAt(2)
        }
    }

    override fun attachBaseContext(base: Context) {
        val lang = prefManager.getPrefsData(Constant.APP_LANG, Constant.INDONESIA_LANG)
        super.attachBaseContext(ContextWrapper(base.setAppLocale(lang)))
    }
}
