package org.wit.placemark.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_placemark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.placemark.R
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

  var placemark = PlacemarkModel()
  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark)
    app = application as MainApp

    toolbar.title = title
    setSupportActionBar(toolbar)

    btnAdd.setOnClickListener() {
      placemark.title = placemarkTitle.text.toString()
      placemark.description = description.text.toString()
      if (placemark.title.isNotEmpty()) {
        app.placemarks.add(placemark.copy())
        info("add Button Pressed: ${placemark}")
        for (i in app.placemarks.indices) {
          info("Placemark[$i]:${app.placemarks[i]}")
        }
        setResult(AppCompatActivity.RESULT_OK)
        finish()
      } else {
        toast("Please Enter a title")
      }
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.placemark_activity_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }
}