package com.example.navigationdrawer

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle
import java.util.*


class MainActivity : AppCompatActivity(), DuoMenuView.OnMenuClickListener {

    lateinit var mDuoDrawerLayout: DuoDrawerLayout
    lateinit var mDuoMenuView: DuoMenuView
    lateinit var mToolbar: Toolbar
    private var mMenuAdapter: MenuAdapter? = null
    var mTitles = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDuoDrawerLayout = findViewById(R.id.drawer) as DuoDrawerLayout
        mDuoMenuView = mDuoDrawerLayout.menuView as DuoMenuView
        mToolbar = findViewById(R.id.toolbar) as Toolbar
        mToolbar.visibility= View.GONE


        mTitles = ArrayList(Arrays.asList(*resources.getStringArray(R.array.menuOptions)))

        handleMenu()
        handleDrawer()
        goToFragment(MainFragment(), false)
        mMenuAdapter?.setViewSelected(0, true)
        title = mTitles[0]
    }


    private fun handleDrawer() {
        val duoDrawerToggle = DuoDrawerToggle(
            this,
            mDuoDrawerLayout,
            mToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        mDuoDrawerLayout?.setDrawerListener(duoDrawerToggle)
        duoDrawerToggle.syncState()

    }

    private fun handleMenu() {
        mMenuAdapter = MenuAdapter(mTitles)
        mDuoMenuView.setOnMenuClickListener(this)
        mDuoMenuView.adapter = mMenuAdapter
    }

    override fun onFooterClicked() {
        Toast.makeText(this, "onFooterClicked", Toast.LENGTH_SHORT).show()
    }

    override fun onHeaderClicked() {
        Toast.makeText(this, "onHeaderClicked", Toast.LENGTH_SHORT).show()
    }

    private fun goToFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.add(R.id.container, fragment).commit()
    }

    override fun onOptionClicked(position: Int, objectClicked: Any) {
        title = mTitles[position]
        mMenuAdapter?.setViewSelected(position, true)
        when (position) {
            else -> goToFragment(MainFragment(), false)
        }
        mDuoDrawerLayout.closeDrawer()
    }

}
