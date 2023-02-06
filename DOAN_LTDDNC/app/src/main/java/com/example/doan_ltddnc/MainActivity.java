package com.example.doan_ltddnc;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.doan_ltddnc.Adapter.ThongBaoAdapter;
import com.example.doan_ltddnc.Model.NhanVienModel;
import com.example.doan_ltddnc.Model.ThongBaoModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "";
    private static final String CHANNEL_NAME ="" ;
    NavigationView navigationView;
    DrawerLayout drawer;
    Toolbar toolbar;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView Tvmail,Tvname,cart_badge;
    FirebaseDatabase fDatabase;
    Menu mMenu;
    TextView textCartItemCount;

    int mCartItemCount = 0;
    int number=0;
    int a;


    FirebaseAuth fAuth;
    ThongBaoAdapter thongBaoAdapter;
    ArrayList<ThongBaoModel> thongBaoModels;
    DatabaseReference dtb;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        drawer = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        drawer.addDrawerListener(actionBarDrawerToggle);
        setSupportActionBar(toolbar);
        mMenu = toolbar.getMenu();
        thongBaoModels=new ArrayList<>();
        cart_badge=findViewById(R.id.cart_badge);



        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                drawer,
                toolbar,
                R.string.open,
                R.string.close);



        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.logOutFragment, R.id.proFileFragment,R.id.logOutFragment)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        View view = navigationView.getHeaderView(0);
        Tvname=view.findViewById(R.id.Tvname);
        Tvmail=view.findViewById(R.id.Tvmail);

        fDatabase = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();

        fDatabase.getReference("QuanLyNv").child("1").child(fAuth.getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        NhanVienModel user = dataSnapshot.getValue(NhanVienModel.class);
//                        user.setUserID(userID);
                        FirebaseUser user2=FirebaseAuth.getInstance().getCurrentUser();

                        Tvname.setText(user.getnv());
                        Tvmail.setText(user2.getEmail());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });





    }

    public static void createNotificationChannel(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
           int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
           channel.setDescription("Reminders");
           channel.setShowBadge(true);
              NotificationManager mNotificationManager = (NotificationManager)
                      context.getSystemService(Context.NOTIFICATION_SERVICE) ;
            mNotificationManager.createNotificationChannel(channel);}
        }

    @Override
    protected void onResume() {
        super.onResume();

//        Log.d("ABC", number + "");
        mCartItemCount=1;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.notification, menu);
        mMenu = menu;
        View actionView = mMenu.findItem(R.id.mnuCart).getActionView();
        if (actionView == null){
            Log.d("isnull","Null");
        }else{
            textCartItemCount = actionView.findViewById(R.id.cart_badge);

            actionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOptionsItemSelected(mMenu.findItem(R.id.mnuCart));
                }
            });
        }

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuCart) {
            Intent intent = new Intent(this, ThongBaoActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void ShowChage(){
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if (user==null)
        {
            return;
        }
        String gmailnew=user.getEmail();

        Tvmail.setText(gmailnew);

    }






}