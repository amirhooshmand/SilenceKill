package ir.amirhooshmand.silencekill.contracts;

public interface RequestPermissionActivityContract {

    interface View {
        void initView();

        void openMainActivity();
        void openDoNotDisturbActivityForResult();
    }

    interface Presenter {
        void onAllowClicked();
        void onPermissionResult();
    }
}
