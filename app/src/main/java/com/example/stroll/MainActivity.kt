package com.example.stroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceNavigationView
import com.luseen.spacenavigation.SpaceOnClickListener

class MainActivity : AppCompatActivity() {

    lateinit var navigationView: SpaceNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView = findViewById(R.id.bottomNavigationView)

        initBottomNavigationView(savedInstanceState)
    }

    private fun initBottomNavigationView(savedInstanceState: Bundle?) {
        addItemToBottomNavigationView(savedInstanceState)
        setNavigationViewClickListener()
    }

    private fun addItemToBottomNavigationView(savedInstanceState: Bundle?) {
        navigationView.initWithSaveInstanceState(savedInstanceState)
        navigationView.addSpaceItem(SpaceItem("", R.drawable.ic_history_black_24dp))
        navigationView.addSpaceItem(SpaceItem("", R.drawable.ic_add_black_24dp))
        navigationView.addSpaceItem(SpaceItem("", R.drawable.ic_favorite_black_24dp))
        navigationView.addSpaceItem(SpaceItem("", R.drawable.ic_settings_black_24dp))
    }

    private fun setNavigationViewClickListener() {
        navigationView.setSpaceOnClickListener(object: SpaceOnClickListener {
            override fun onCentreButtonClick() {
                Toast.makeText(applicationContext, "onCentreButtonClicked", Toast.LENGTH_SHORT).show()
            }

            override fun onItemReselected(itemIndex: Int, itemName: String?) {
                Toast.makeText(applicationContext, "$itemIndex - $itemName", Toast.LENGTH_SHORT).show()
            }

            override fun onItemClick(itemIndex: Int, itemName: String?) {
                Toast.makeText(applicationContext, "$itemIndex - $itemName", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
