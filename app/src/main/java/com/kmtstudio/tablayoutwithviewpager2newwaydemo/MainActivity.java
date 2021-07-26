package com.kmtstudio.tablayoutwithviewpager2newwaydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    FragmentManager fm;
    SliderAdapter sa;
    MaterialToolbar toolbar;

    HomeFragment homeFragment;
    ProfileFragment profileFragment;
    ShopFragment shopFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBarID);
        setSupportActionBar(toolbar);

        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();
        shopFragment = new ShopFragment();


        fm = getSupportFragmentManager();
        sa = new SliderAdapter(fm,getLifecycle());


        ViewPager2 viewPager2 = findViewById(R.id.pagerID);

        TabLayout tabLayout = findViewById(R.id.tabLayoutId);

        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Shop"));

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_person_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_add_shopping_cart_24);

        BadgeDrawable badgeDrawable = tabLayout.getTabAt(0).getOrCreateBadge();
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(17);


        sa.addFragment(homeFragment);
        sa.addFragment(profileFragment);
        sa.addFragment(shopFragment);

        viewPager2.setAdapter(sa);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}