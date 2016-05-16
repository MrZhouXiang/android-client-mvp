package puyuntech.com.beihai.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import puyuntech.com.beihai.R;
import puyuntech.com.beihai.app.ActivityBuilder.Impl.ActivityDirector;
import puyuntech.com.beihai.app.BaseAct;
import puyuntech.com.beihai.http.httpContor.base.HttpAfterExpand;

public class WelcomeActivity extends ActivityDirector {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public Object getValue(Enum type) {
        return null;
    }

    @Override
    public void updateUI(Object params, Enum type) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void setViewClickListener() {

    }

    @Override
    public void getDataLoc() {

    }

    @Override
    public void showTitle() {

    }

    @Override
    public void showView() {

    }

    @Override
    public void getDataNet(HttpAfterExpand afterExpand) {

    }

    @Override
    public void homeBack() {

    }

    @Override
    public void refresh() {

    }
}
