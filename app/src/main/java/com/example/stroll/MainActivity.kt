package com.example.stroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceNavigationView
import com.luseen.spacenavigation.SpaceOnClickListener
import kotlinx.android.synthetic.main.activity_main.*

const val CENTRE_BUTTON_INDEX = -1
const val CURRENT_SELECTED_ITEM_BUNDLE_KEY = "currentItem"

class MainActivity : AppCompatActivity() {

    lateinit var navigationView: SpaceNavigationView

    private val firstFragment by lazy { FirstFragment.newInstance() }
    private val secondFragment by lazy { SecondFragment.newInstance() }
    private val profileFragment by lazy { ProfileFragment.newInstance() }

    private var currentSelectedItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationView = findViewById(R.id.bottomNavigationView)

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(CURRENT_SELECTED_ITEM_BUNDLE_KEY))
                currentSelectedItem = savedInstanceState.getInt(CURRENT_SELECTED_ITEM_BUNDLE_KEY, 0)
        }

        initBottomNavigationView(savedInstanceState)
        initFragment()
    }

    private fun initBottomNavigationView(savedInstanceState: Bundle?) {
        addItemToBottomNavigationView(savedInstanceState)
        setNavigationViewClickListener()
    }

    private fun addItemToBottomNavigationView(savedInstanceState: Bundle?) {
        navigationView.initWithSaveInstanceState(savedInstanceState)
        navigationView.addSpaceItem(SpaceItem("", R.drawable.ic_clipboards))
        navigationView.addSpaceItem(SpaceItem("", R.drawable.ic_heart))
    }

    private fun setNavigationViewClickListener() {
        navigationView.setSpaceOnClickListener(object : SpaceOnClickListener {
            override fun onCentreButtonClick() {
                navigationView.setCentreButtonSelectable(true)
                navigationView.setCentreButtonSelected()
                setCurrentSelectedItem(CENTRE_BUTTON_INDEX)
                setFragment(profileFragment)
            }

            override fun onItemReselected(itemIndex: Int, itemName: String?) {

            }

            override fun onItemClick(itemIndex: Int, itemName: String?) {
                when (itemIndex) {
                    0 -> {
                        setCurrentSelectedItem(itemIndex)
                        setFragment(firstFragment)
                    }
                    1 -> {
                        setCurrentSelectedItem(itemIndex)
                        setFragment(secondFragment)
                    }
                    else -> {
                        Toast.makeText(applicationContext, "$itemIndex - $itemName Not implemented", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
    }

    /**
     * Set a fragment when the activity is created.
     */
    private fun initFragment() {
        when (currentSelectedItem) {
            -1 -> {
                setFragment(profileFragment)
            }

            0 -> {
                setFragment(firstFragment)
            }

            1 -> {
                setFragment(secondFragment)
            }
        }
    }

    private fun setCurrentSelectedItem(itemIndex: Int) {
        currentSelectedItem = itemIndex
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction {
            replace(R.id.fragmentContainer, fragment)
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        bottomNavigationView.onSaveInstanceState(outState)
    }
}
