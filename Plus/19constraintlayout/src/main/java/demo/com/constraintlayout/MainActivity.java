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
        pageModels.add(new PageModel(R.layout.sample_11, R.string.base_constraint_layout, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample_12, R.string.base_constraint_layout_2, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample_13_horizontal_weight, R.string.base_constraint_layout_bias, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample_14_textbaseline, R.string.text_base_line, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample_15_circle, R.string.circle, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample_21_constraint_width, R.string.constraint_width, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample_22_bias, R.string.constraint_bias, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample_23_chain_style, R.string.constraint_chain_style, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample_24_width_height_ratio, R.string.constraint_width_height_ratio, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample_25_percent, R.string.constraint_percent, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample_26_guide_line, R.string.constraint_guide_line, R.layout.practice));
        pageModels.add(new PageModel(R.layout.sample_31_group, R.string.constraint_guide, R.layout.practice));

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
                return PageFragment.newInstance(pageModel.sampleLayoutRes, pageModel.practiceLayoutRes,MainActivity.this);
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
        int sampleLayoutRes;
        int titleRes;
        int practiceLayoutRes;

        PageModel( int sampleLayoutRes,  int titleRes,  int practiceLayoutRes) {
            this.sampleLayoutRes = sampleLayoutRes;
            this.titleRes = titleRes;
            this.practiceLayoutRes = practiceLayoutRes;
        }
    }

}
