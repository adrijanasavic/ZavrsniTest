package com.example.zavrsnitest.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.zavrsnitest.R;
import com.example.zavrsnitest.adapters.FilmoviAdapter;
import com.example.zavrsnitest.db.DatabaseHelper;
import com.example.zavrsnitest.db.model.Filmovi;
import com.example.zavrsnitest.dialog.AboutDialog;
import com.example.zavrsnitest.settings.SettingsActivity;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.zavrsnitest.tools.Tools.NOTIF_CHANNEL_ID;

public class MojiFilmoviActivity extends AppCompatActivity implements FilmoviAdapter.OnItemClickListener, FilmoviAdapter.OnItemLongClickListener {

    private Toolbar toolbar;
    private ArrayList<String> drawerItems;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private RelativeLayout drawerPane;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_moji_filmovi );

        setupToolbar();
        fillDataDrawer();
        setupDrawer();
    }

    private void refresh() {

        RecyclerView recyclerView = findViewById( R.id.rvRepertoarLista );
        if (recyclerView != null) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );
            recyclerView.setLayoutManager( layoutManager );
            List<Filmovi> film = null;
            try {

                film = getDataBaseHelper().getFilmoviDao().queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            FilmoviAdapter adapter = new FilmoviAdapter( this, film, this, this );
            recyclerView.setAdapter( adapter );
        }
    }
    private void fillDataDrawer() {
        drawerItems = new ArrayList<>();
        drawerItems.add( "Moji filmovi" );
        drawerItems.add( "Pretraga filmova" );
        drawerItems.add( "Settings" );
        drawerItems.add( "Brisanje liste filmova" );
        drawerItems.add( "O aplikaciji" );


    }

    private void setupDrawer() {
        drawerList = findViewById( R.id.left_drawer );
        drawerLayout = findViewById( R.id.drawer_layout );
        drawerPane = findViewById( R.id.drawerPane );
        drawerList.setAdapter( new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, drawerItems ) );

        drawerList.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = "Unknown";
                switch (i) {
                    case 0:
                        title = "Moji filmova";
                        startActivity( new Intent( MojiFilmoviActivity.this, MojiFilmoviActivity.class ) );
                        break;

                    case 1:
                        title = "Pretraga filmova";
                        startActivity( new Intent( MojiFilmoviActivity.this, MainActivity.class ) );
                        break;
                    case 2:
                        title = "Settings";
                        Toast.makeText( getBaseContext(), "Prikaz podesavanja", Toast.LENGTH_SHORT );
                        startActivity( new Intent( MojiFilmoviActivity.this, SettingsActivity.class ) );
                        break;
                    case 3:
                        Toast.makeText( getBaseContext(), "Obisi sve filmove", Toast.LENGTH_SHORT );
                        title = "Brisanje filmova";
                        //TODO : brisanje cele liste filmova
                        break;
                    case 4:
                        AboutDialog dialog = new AboutDialog( MojiFilmoviActivity.this );
                        dialog.show();
                        title = "O aplikaciji";
                        break;

                    default:
                        break;
                }
                setTitle( title );
                drawerLayout.closeDrawer( Gravity.LEFT );
            }
        } );

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.app_name,
                R.string.app_name
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle( "" );
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle( "" );
                invalidateOptionsMenu();
            }
        };
    }

    public void setupToolbar() {
        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        toolbar.setTitleTextColor( Color.WHITE );
        toolbar.setSubtitle( "Moji filmova" );
        toolbar.setLogo( R.drawable.heart );

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled( true );
            actionBar.setHomeAsUpIndicator( R.drawable.drawer );
            actionBar.setHomeButtonEnabled( true );
            actionBar.show();
        }
    }


    public DatabaseHelper getDataBaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper( this, DatabaseHelper.class );
        }
        return databaseHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel";
            String description = "Description of My Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel( NOTIF_CHANNEL_ID, name, importance );
            channel.setDescription( description );

            NotificationManager notificationManager = getSystemService( NotificationManager.class );
            notificationManager.createNotificationChannel( channel );
        }
    }
    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(int position) {

    }
}

