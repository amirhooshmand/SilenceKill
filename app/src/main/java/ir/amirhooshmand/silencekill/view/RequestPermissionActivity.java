package ir.amirhooshmand.silencekill.view;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;

import ir.amirhooshmand.silencekill.R;
import ir.amirhooshmand.silencekill.contracts.RequestPermissionActivityContract;
import ir.amirhooshmand.silencekill.presenter.RequestPermissionActivityPresenter;
import ir.amirhooshmand.silencekill.repository.RequestPermissionActivityRepository;
import ir.amirhooshmand.silencekill.repository.SplashActivityRepository;

public class RequestPermissionActivity extends AppCompatActivity implements RequestPermissionActivityContract.View, View.OnClickListener {
    Button allowBtn;
    RequestPermissionActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(RequestPermissionActivity.this);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_request_permission);

        NotificationManager n = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        RequestPermissionActivityRepository repository = new RequestPermissionActivityRepository(n);

        presenter = new RequestPermissionActivityPresenter(this, repository);
    }

    public void initView() {
        allowBtn = findViewById(R.id.permission_allow_button);
        allowBtn.setOnClickListener(this);
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void openDoNotDisturbActivityForResult() {
        Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Log.i("Here", "resultCode: " + resultCode);

        if (requestCode == 1) {
                presenter.onPermissionResult();
        }
    }//onActivityResult

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.permission_allow_button)
            presenter.onAllowClicked();

    }
}
