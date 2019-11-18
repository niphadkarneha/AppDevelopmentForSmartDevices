package com.example.nehaniphadkar.contacts;

import android.content.pm.PackageManager;
import android.os.Bundle;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends Activity implements
        MyListFragment.OnItemSelectedListener,MyAdapter.ChangeFilterMainNumber,DetailsFragment.MainListRefresh {

    SelectionStateFragment stateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(checkPermission()){

            //Toast.makeText(MainActivity.this, "All Permissions Granted Successfully", Toast.LENGTH_LONG).show();

        }
        else {

            requestPermission();
        }
        stateFragment =
                (SelectionStateFragment) getFragmentManager()
                        .findFragmentByTag("headless");

        if(stateFragment == null) {
            stateFragment = new SelectionStateFragment();
            getFragmentManager().beginTransaction()
                    .add(stateFragment, "headless").commit();
        }

        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            // restore state
            if (stateFragment.lastSelection.length()>0) {
                onRssItemSelected(stateFragment.lastSelection, stateFragment.number);
            }
            // otherwise all is good, we use the fragments defined in the layout
            return;
        }
        // if savedInstanceState is null we do some cleanup
        if (savedInstanceState != null) {
            // cleanup any existing fragments in case we are in detailed mode
            getFragmentManager().executePendingTransactions();
            Fragment fragmentById = getFragmentManager().
                    findFragmentById(R.id.fragment_container);
            if (fragmentById!=null) {
                getFragmentManager().beginTransaction()
                        .remove(fragmentById).commit();
            }
        }

        MyListFragment listFragment = new MyListFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, listFragment).commit();
    }

    @Override
    public void onRssItemSelected(String text, String s) {
        stateFragment.lastSelection = text;
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
          /*  DetailsFragment fragment = (DetailsFragment) getFragmentManager()
                    .findFragmentById(R.id.detailFragment);
            fragment.setText(text);*/
        } else {
            // replace the fragment
            // Create fragment and give it an argument for the selected article
            DetailsFragment newFragment = new DetailsFragment();
            Bundle args = new Bundle();
            args.putString(DetailsFragment.EXTRA_TEXT, text);

            newFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back

            transaction.replace(R.id.fragment_container, newFragment);
           // transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }

    @Override
    public void onRefresh() {
        MyListFragment test = (MyListFragment) getFragmentManager().findFragmentById(R.id.listFragment);
        if (test != null && test.isVisible()) {
            //DO STUFF
            DetailsFragment fragment = (DetailsFragment) getFragmentManager()
                    .findFragmentById(R.id.detailFragment);
            fragment.show();
        }


    }

    public void onResume(){
        super.onResume();
       /* getFragmentManager().beginTransaction().detach(new MyListFragment()).attach(new MyListFragment()).commit();
        getFragmentManager().beginTransaction().detach(new DetailsFragment()).attach(new DetailsFragment()).commit();
*/

    }

    @Override
    public void OnChangeFilterMainNumberListener(String name, String number, String json, byte[] bytes) {
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            DescriptionFragment newFragment = new DescriptionFragment();
findViewById(R.id.detailFragment).setVisibility(View.GONE);
            /*Bundle args = new Bundle();
            args.putString(DetailsFragment.EXTRA_TEXT, text);
            newFragment.setArguments(args);*/
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            Bundle args = new Bundle();

            args.putString("name", name);
            args.putString("number", number);
            args.putString("json", json);
            args.putByteArray("image",bytes);
            newFragment.setArguments(args);

            transaction.replace(R.id.rel, newFragment);
            transaction.addToBackStack(null);


            // Commit the transaction
            transaction.commit();

        } else {
            // replace the fragment
            // Create fragment and give it an argument for the selected article
            DescriptionFragment newFragment = new DescriptionFragment();
            /*Bundle args = new Bundle();
            args.putString(DetailsFragment.EXTRA_TEXT, text);
            newFragment.setArguments(args);*/
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            Bundle args = new Bundle();

            args.putString("name", name);
            args.putString("number", number);
            args.putString("json", json);
            args.putByteArray("image",bytes);
            newFragment.setArguments(args);

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }

    }
    private void requestPermission() {

        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        CAMERA
                }, 1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1:

                if (grantResults.length > 0) {

                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;


                    if (CameraPermission ) {


                    }
                    else {
                        Toast.makeText(this, "You need to give permission for Camera", Toast.LENGTH_SHORT).show();

                    }
                }

                break;
        }
    }
    public boolean checkPermission() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED ;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            DescriptionFragment test = new DescriptionFragment();
            if ( test.isVisible()) {

                DetailsFragment newFragment = new DetailsFragment();
                findViewById(R.id.detailFragment).setVisibility(View.VISIBLE);
            /*Bundle args = new Bundle();
            args.putString(DetailsFragment.EXTRA_TEXT, text);
            newFragment.setArguments(args);*/
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back

                transaction.add(R.id.rel, newFragment);
                transaction.addToBackStack(null);


                // Commit the transaction
                transaction.commit();

                findViewById(R.id.detailFragment).setVisibility(View.VISIBLE);

            }

        }

        }

    @Override
    public void onUpdate() {
        MyListFragment test = (MyListFragment) getFragmentManager().findFragmentById(R.id.listFragment);
        if (test != null && test.isVisible()) {
            //DO STUFF
            MyListFragment fragment = (MyListFragment) getFragmentManager()
                    .findFragmentById(R.id.listFragment);
            fragment.displyOnresume();
        }

    }
}