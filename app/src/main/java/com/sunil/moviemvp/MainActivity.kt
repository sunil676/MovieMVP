package com.sunil.moviemvp

import android.app.Fragment
import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sunil.moviemvp.ui.PopularMovieFragment

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private val MAIN_FRAGMENT_TAG = "MFTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            return
        } else {
            // Add the Main Fragment to the 'main_grid_container' FrameLayout
            addFragmentToActivity(fragmentManager, PopularMovieFragment.newInstance(), R.id.main_grid_container, MAIN_FRAGMENT_TAG)
        }

    }


    fun addFragmentToActivity(fragmentManager: FragmentManager,
                                     fragment: Fragment, frameId: Int, fragmentTag: String) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment, fragmentTag).addToBackStack(null)
        transaction.commit()
    }
}

