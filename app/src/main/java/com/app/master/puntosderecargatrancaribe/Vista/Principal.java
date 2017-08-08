package com.app.master.puntosderecargatrancaribe.Vista;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.master.puntosderecargatrancaribe.Presentador.iPresentadorPrincipal;
import com.app.master.puntosderecargatrancaribe.R;
import com.app.master.puntosderecargatrancaribe.Vista.Adaptadores.AdaptadorViewPagerPrincipal;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements iPrincipal{

    private ViewPager viewPager;
    private iPresentadorPrincipal presentador;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutmenuprincipal);
        //setContentView(R.layout.activity_principal);
        //viewPager=(ViewPager)findViewById(R.id.viewpager);
        //presentador=new PresentadorPrincipal(this,this);
        //presentador.iniciarViewPager();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                fragment = new FragmentMapaPuntoRecarga();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_2:
                                fragment=new FragmentoRutas();
                                fragmentTransaction = true;
                                break;
                        }

                        if(fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });
        Toolbar appbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.punto_recarga);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            //...
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public ArrayList<Fragment> getFragmentos(){
        ArrayList<Fragment> fragments=new ArrayList();
        fragments.add(new FragmentMapaPuntoRecarga());
        return fragments;
    }

    @Override
    public AdaptadorViewPagerPrincipal crearAdaptador(ArrayList<Fragment> fragments){
        return new AdaptadorViewPagerPrincipal(getSupportFragmentManager(),fragments);
    }

    @Override
    public void establecerPagerPrincipal(AdaptadorViewPagerPrincipal adaptador){
        viewPager.setAdapter(adaptador);
    }

}