package com.nusantarian.moviecatalogue.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.PagerAdapter
import com.nusantarian.moviecatalogue.R
import com.nusantarian.moviecatalogue.adapter.SectionsPagerAdapter
import com.nusantarian.moviecatalogue.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var back = false
    private lateinit var fm: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val adapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = adapter
        binding.tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0F
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_language){
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (back) {
            super.onBackPressed()
            return
        }
        this.back = true
        Toast.makeText(this, "Tekan BACK sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            back = false
            finish()
        }, 2000)
    }

}
