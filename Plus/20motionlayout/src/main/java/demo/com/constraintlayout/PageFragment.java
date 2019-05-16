package demo.com.constraintlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

public class PageFragment extends Fragment {
    int topLayoutRes;
    int bottomLayoutRes;
    int layoutTitleRes;
    private static final String TOP_LAYOUT_RES = "topLayoutRes";
    private static final String BOTTOM_LAYOUT_RES = "bottomLayoutRes";
    private static final String LAYOUT_TITLE_RES = "layoutTitleRes";

    public static PageFragment newInstance(int topLayoutRes, int bottomLayoutRes,int layoutTitleRes) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(TOP_LAYOUT_RES, topLayoutRes);
        args.putInt(BOTTOM_LAYOUT_RES, bottomLayoutRes);
        args.putInt(LAYOUT_TITLE_RES, layoutTitleRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_page, container, false);

        ViewStub sampleStub = (ViewStub) view.findViewById(R.id.topStub);
        sampleStub.setLayoutResource(topLayoutRes);
        sampleStub.inflate();

        ViewStub practiceStub = (ViewStub) view.findViewById(R.id.bottomStub);
        practiceStub.setLayoutResource(bottomLayoutRes);
        practiceStub.inflate();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            topLayoutRes = args.getInt(TOP_LAYOUT_RES);
            bottomLayoutRes = args.getInt(BOTTOM_LAYOUT_RES);
            layoutTitleRes = args.getInt(LAYOUT_TITLE_RES);
        }
    }
}
