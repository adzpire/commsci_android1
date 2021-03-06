package com.example.commsci_android1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.commsci_android1.ui.generalinfo.GeneralinfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.MainActivity.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean openF2 = false;
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_generalinfo)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Bundle extras = getIntent().getExtras();
        if(extras!=null && extras.containsKey("openF2"))
            openF2 = (boolean) extras.getBoolean("openF2");
        if(openF2){
//            openfragment();
            Fragment generalinfoFragment = new GeneralinfoFragment();
//            Fragment fragment = new GeneralinfoFragment();
//            FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.nav_host_fragment, generalinfoFragment);
//            ft.addToBackStack(null);
            ft.commit();
//            Intent intent = new Intent(this, GeneralinfoFragment.class);
////            Fragment generalinfoFragment = new GeneralinfoFragment();
//            ((MainActivity) getActivity()).startActivity(intent);
//            startActivity(intent);
            //add or replace fragment F2 in container
        }
    }

//    public void openfragment(View) {
//        getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new GeneralinfoFragment)).commit();
//    }

//    public void sendMessage(View view) {
        // Do something in response to button
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.t_0:
                Toast.makeText(this, "t0 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.t_1:
                Toast.makeText(this, "t1 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
