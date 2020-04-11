package com.nusantarian.moviecatalogue.adapter


import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nusantarian.moviecatalogue.R
import com.nusantarian.moviecatalogue.fragment.MovieFragment
import com.nusantarian.moviecatalogue.fragment.TvShowFragment


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val titles = intArrayOf(
        R.string.tab_title_movies,
        R.string.tab_title_tv
    )

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowFragment()
        }
        return fragment as Fragment
    }

    
    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(titles[position])


    override fun getCount(): Int = 2
}