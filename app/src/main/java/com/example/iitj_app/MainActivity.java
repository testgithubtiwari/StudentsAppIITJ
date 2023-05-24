package com.example.iitj_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.iitj_app.ebook.EbookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth authProfile;
    private NavController navController;

    private DrawerLayout  drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        navController= Navigation.findNavController(this,R.id.frame_layout);


        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigation_view);

        authProfile=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser= authProfile.getCurrentUser();

        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        switch (item.getItemId())
        {
            case R.id.option_logout:
                authProfile.signOut();
                Toast.makeText(MainActivity.this,"You are logged out!",Toast.LENGTH_LONG).show();
                intent=new Intent(MainActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.option_share:
                try {
                    Intent i=new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT,"IITJ APP");
                    i.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/detailed?id="+getApplicationContext().getPackageName());
                    startActivity(Intent.createChooser(i,"Share with"));
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to share this app!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.navigation_developer:
                String url2="https://testgithubtiwari.github.io/myportfolio2/";
                Intent websiteIntent2 = new Intent(Intent.ACTION_VIEW);
                websiteIntent2.setData(Uri.parse(url2));
                startActivity(websiteIntent2);
                break;
            case R.id.navigation_rate:
                Uri uri=Uri.parse("https://play.google.com/store/apps/detailed?id="+getApplicationContext().getPackageName());
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to open\n"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.navigation_theme:
                Toast.makeText(this,"Themes",Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_share:
                try {
                    Intent j=new Intent(Intent.ACTION_SEND);
                    j.setType("text/plain");
                    j.putExtra(Intent.EXTRA_SUBJECT,"IITJ APP");
                    j.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/detailed?id="+getApplicationContext().getPackageName());
                    startActivity(Intent.createChooser(j,"Share with"));
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to share this app!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.navigation_video:
                String url1 = "https://www.youtube.com/@IITJodhpurOfficial"; // replace with your website URL
                Intent websiteIntent1 = new Intent(Intent.ACTION_VIEW);
                websiteIntent1.setData(Uri.parse(url1));
                startActivity(websiteIntent1);
                break;
            case R.id.navigation_website:
                String url = "https://www.iitj.ac.in/"; // replace with your website URL
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
                websiteIntent.setData(Uri.parse(url));
                startActivity(websiteIntent);
                break;
            case R.id.navigation_ebook:
                startActivity(new Intent(this, EbookActivity.class));
                break;
            case R.id.navigation_logout:
                authProfile.signOut();
                Toast.makeText(MainActivity.this,"You are logged out!",Toast.LENGTH_LONG).show();
                intent=new Intent(MainActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
        super.onBackPressed();
    }}
}