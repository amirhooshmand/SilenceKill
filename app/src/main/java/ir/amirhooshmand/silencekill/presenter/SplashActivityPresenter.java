package ir.amirhooshmand.silencekill.presenter;

import android.os.Build;

import ir.amirhooshmand.silencekill.contracts.SplashActivityContract;
import ir.amirhooshmand.silencekill.repository.SplashActivityRepository;

public class SplashActivityPresenter implements SplashActivityContract.Presenter {

    SplashActivityContract.View view;
    SplashActivityRepository repository;

    public SplashActivityPresenter(SplashActivityContract.View view, SplashActivityRepository repository) {
        this.view = view;
        this.repository = repository;

        initPresenter();
    }

    void initPresenter() {
        view.initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (repository.getNotificationManager().isNotificationPolicyAccessGranted()) {

            } else {
                view.openPermissionActivity();
            }
        }
    }


}
