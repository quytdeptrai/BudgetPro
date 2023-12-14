package com.learncode.budgetpro;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.learncode.budgetpro.databinding.ActivityMainBinding;
import com.learncode.budgetpro.dialog.ChiDialog;
import com.learncode.budgetpro.dialog.LoaiChiDialog;
import com.learncode.budgetpro.dialog.LoaiThuDialog;
import com.learncode.budgetpro.dialog.ThuDialog;
import com.learncode.budgetpro.ui.chi.KhoanChiFragment;
import com.learncode.budgetpro.ui.chi.LoaiChiFragment;
import com.learncode.budgetpro.ui.thu.KhoanThuFragment;
import com.learncode.budgetpro.ui.thu.LoaiThuFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
            final MainActivity currentContext = this;
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {

                                                      @Override
                                                      public void onClick(View view) {
                                                          List<Fragment> fragments = getSupportFragmentManager().getFragments();
                                                          Fragment fragment = fragments.get(fragments.size() - 1);
                                                          if (fragment instanceof LoaiThuFragment) {
                                                              LoaiThuDialog dialog = new LoaiThuDialog(currentContext,
                                                                      (LoaiThuFragment) fragment);
                                                              dialog.show();
                                                          } else if (fragment instanceof KhoanThuFragment) {
                                                              ThuDialog dialog = new ThuDialog(currentContext, (KhoanThuFragment) fragment);
                                                              dialog.show();
                                                          }
                                                          //loai chi
                                                          List<Fragment> fragments1 = getSupportFragmentManager().getFragments();
                                                          Fragment fragment1 = fragments1.get(fragments.size() - 1);
                                                          if (fragment instanceof LoaiChiFragment) {
                                                              LoaiChiDialog dialog = new LoaiChiDialog(currentContext, (LoaiChiFragment) fragment1);
                                                              dialog.show();

                                                          }else if(fragment instanceof KhoanChiFragment){
                                                              ChiDialog dialog = new ChiDialog (currentContext, (KhoanChiFragment) fragment);
                                                              dialog.show();
                                                          }
                                                      }
        });




        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //thoát chương trình nav_thoat
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() ==R.id.nav_thoat){
                    finish();
                }

            }
        });

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}