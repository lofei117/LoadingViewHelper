package info.lofei.loadingviewhelper;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import info.lofei.viewloadinghelper.ViewHelper;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Handler mHandler = new Handler();

    private View mLoadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        setListeners();
    }

    private void findViews() {
        mLoadBtn = findViewById(R.id.btn_load);
    }

    private void setListeners() {
        mLoadBtn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
                doLoad();
                break;
        }
    }

    private void doLoad() {
        ViewHelper.setViewLoadingState(this, mLoadBtn, true);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewHelper.setViewLoadingState(MainActivity.this, mLoadBtn, false);
            }
        }, 2000);
    }
}
