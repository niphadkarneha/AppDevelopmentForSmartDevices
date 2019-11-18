package com.example.nehaniphadkar.goldenpalace.navigationdrawer;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.example.nehaniphadkar.goldenpalace.Dashboard;
import com.example.nehaniphadkar.goldenpalace.R;
import com.example.nehaniphadkar.goldenpalace.RecipiesActivity;
import com.example.nehaniphadkar.goldenpalace.Salads;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity { //changed from depricated ActionBarActivity
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    protected RelativeLayout _completeLayout, _activityLayout;
    // nav drawer title
    private CharSequence mDrawerTitle;
    private Menu menuObject;
    // used to store app title
    private CharSequence mTitle;
    public static int navCount;
    Toolbar toolbar;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private RelativeLayout relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
        // if (savedInstanceState == null) {
        // // on first time display view for first nav item
        // // displayView(0);
        // }
    }

    public void set(String[] navMenuTitles, TypedArray navMenuIcons) {
        mTitle = mDrawerTitle = getTitle();
        relative = (RelativeLayout) findViewById(R.id.drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items
        if (navMenuIcons == null) {
            for (int i = 0; i < navMenuTitles.length; i++) {
                navDrawerItems.add(new NavDrawerItem(navMenuTitles[i]));
            }
        } else {
            for (int i = 0; i < navMenuTitles.length; i++) {
                navDrawerItems.add(new NavDrawerItem(navMenuTitles[i],
                        navMenuIcons.getResourceId(i, -1)));
            }
        }

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setupDrawerToggle();

        // getSupportActionBar().setIcon(R.drawable.ic_drawer);
        //mDrawerToggle.syncState();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, // nav menu toolbar instead of icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                supportInvalidateOptionsMenu();
                //mDrawerToggle.syncState();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                supportInvalidateOptionsMenu();
                //mDrawerToggle.syncState();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(relative)) {
                mDrawerLayout.closeDrawer(relative);
            } else {
                mDrawerLayout.openDrawer(relative);
            }
        }


        return super.onOptionsItemSelected(item);
    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        // boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        // menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {

        switch (position) {
            case 6:
                navCount = 6;
                /*adapter.notifyDataSetChanged();
                if(this.getClass().getSimpleName().matches("Salads"));
               */ startActivity(new Intent(this, Salads.class));
                break;
            case 0:
                navCount=0;
              if (!this.getClass().getSimpleName().matches("MainPage")){
                  startActivity(new Intent(this, Dashboard.class));

              }
                break;
            case 1:
                navCount=1;
                adapter.notifyDataSetChanged();

                if (!this.getClass().getSimpleName().matches("RecipiesActivity")) {
                    adapter.notifyDataSetChanged();
                    startActivity(new Intent(this, RecipiesActivity.class));
                }
                break;
            case 2:
                navCount=2;
                adapter.notifyDataSetChanged();
                startActivity(new Intent(this, Salads.class));
                break;
            case 3:
                navCount=3;
                adapter.notifyDataSetChanged();

                startActivity(new Intent(this, Salads.class));
                break;
            case 4:
                navCount=4;
                adapter.notifyDataSetChanged();

                startActivity(new Intent(this, Salads.class));

                break;
            case 5:
                navCount=5;
                adapter.notifyDataSetChanged();

                startActivity(new Intent(this, Salads.class));
                break;

            case 7:
                navCount=7;
                adapter.notifyDataSetChanged();

                startActivity(new Intent(this, Salads.class));
                break;
            case 8:
                startActivity(new Intent(this, Salads.class));

                break;



                //case 2:
                //  Intent intent2 = new Intent(this, ThirdActivity.class);
                //  startActivity(intent2);
                //  finish();
                //  break;
                // case 3:
                // Intent intent3 = new Intent(this, fourth.class);
                // startActivity(intent3);
                // finish();
                // break;
                // case 4:
                // Intent intent4 = new Intent(this, fifth.class);
                // startActivity(intent4);
                // finish();
                // break;
                // case 5:
                // Intent intent5 = new Intent(this, sixth.class);
                // startActivity(intent5);
                // finish();
                // break;
            default:
                break;
        }

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        mDrawerLayout.closeDrawer(relative);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }
}
