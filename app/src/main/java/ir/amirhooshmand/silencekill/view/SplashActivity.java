package ir.amirhooshmand.silencekill.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;

import ir.amirhooshmand.silencekill.R;
import ir.amirhooshmand.silencekill.contracts.SplashActivityContract;
import ir.amirhooshmand.silencekill.presenter.SplashActivityPresenter;
import ir.amirhooshmand.silencekill.repository.SplashActivityRepository;

public class SplashActivity extends AppCompatActivity implements SplashActivityContract.View {

    SplashActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(SplashActivity.this);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_splash);

        NotificationManager n = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        SplashActivityRepository repository = new SplashActivityRepository(n);

        presenter = new SplashActivityPresenter(this, repository);
    }

    public void initView() {

    }

    public void openPermissionActivity() {
        Intent intent = new Intent(this, RequestPermissionActivity.class);
        startActivity(intent);
        finish();
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
