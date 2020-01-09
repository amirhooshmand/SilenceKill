package ir.amirhooshmand.silencekill.presenter;

import android.app.Activity;
import android.util.Log;

import ir.amirhooshmand.silencekill.contracts.RequestPermissionActivityContract;
import ir.amirhooshmand.silencekill.repository.RequestPermissionActivityRepository;
import ir.amirhooshmand.silencekill.repository.SplashActivityRepository;

public class RequestPermissionActivityPresenter implements RequestPermissionActivityContract.Presenter {

    RequestPermissionActivityContract.View view;
    RequestPermissionActivityRepository repository;

    public RequestPermissionActivityPresenter(RequestPermissionActivityContract.View view,  RequestPermissionActivityRepository repository) {
        this.view = view;
        this.repository = repository;

        initPresenter();
    }

    void initPresenter() {
        view.initView();

    }

    public void onAllowClicked() {
        view.openDoNotDisturbActivityForResult();
    }


    public void onPermissionResult() {
        int resultCode = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
            resultCode = (repository.getNotificationManager().isNotificationPolicyAccessGranted()) ? Activity.RESULT_OK : Activity.RESULT_CANCELED;

        if (resultCode == Activity.RESULT_OK) {
            view.openMainActivity();
        }
    }

}
