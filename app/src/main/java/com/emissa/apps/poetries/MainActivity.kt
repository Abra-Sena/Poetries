package com.emissa.apps.poetries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * this app allows the user to search for a poetry
 * user type in author's name, first name only is enough
 * a list of author's name with the same firstname will be displayed
 * user select the desired author from the list to show all poetry of that author
 * use select the poetry they want from the list -> a new fragment opens to show the selected poetry
 *
 * optional:
 *  allow user to search with voice recording
 *  activate accessibility
 *  unit testing
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

/**
 *
 */