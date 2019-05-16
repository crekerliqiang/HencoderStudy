package demo.com.constraintlayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels;


    private void init(){
        pageModels = new ArrayList<>();
        //需要就继续添加
        pageModels.add(new PageModel(R.layout.sample, R.string.test1, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample, R.string.test2, R.layout.practice));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = pageModels.get(position);
                return PageFragment.newInstance(pageModel.topLayoutRes, pageModel.bottomLayoutRes,pageModel.titleRes);
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).titleRes);
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private class PageModel {
        int topLayoutRes;
        int titleRes;
        int bottomLayoutRes;

        PageModel( int topLayoutRes,  int titleRes,  int bottomLayoutRes) {
            this.topLayoutRes = topLayoutRes;
            this.titleRes = titleRes;
            this.bottomLayoutRes = bottomLayoutRes;
        }
    }

}
