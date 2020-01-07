package jrh.library.common.update;

import android.Manifest;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import aly.library.common.base.R;
import jrh.library.common.update.download.DownLoadUtil;
import jrh.library.common.utils.AppUtil;
import jrh.library.common.utils.ToastUtil;
import io.reactivex.functions.Consumer;

/**
 * desc: 版本更新
 * Created by jiarh on 2018/8/8 18:03.
 */

public class UpdateModel {

    private FragmentActivity mAct;
    private DownVersion downVersion;
    private RxPermissions rxPermissions;
    private String downSavePath;
    private String fileAuthority;
    private boolean hasNewVersion;


    public UpdateModel(FragmentActivity mAct, DownVersion downVersion, String downSavePath, String fileAuthority) {
        init(mAct, downVersion, downSavePath, fileAuthority);
    }

    private void init(FragmentActivity mAct, DownVersion downVersion, String downSavePath, String fileAuthority) {
        this.mAct = mAct;
        this.downVersion = downVersion;
        this.downSavePath = downSavePath;
        this.fileAuthority = fileAuthority;
        rxPermissions = new RxPermissions(mAct);
    }

    public boolean checkUpDate() {
        rxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            hasNewVersion = isUpdate();
                        } else {
                            ToastUtil.showMessage("app 功能受限");
                        }
                    }
                });
        return hasNewVersion;
    }

    private boolean isUpdate() {

        if (downVersion == null) return false;

        //版本名称 1.02
        double currentVersion = Double.valueOf(AppUtil.getVersionName());
        double serverVersion = Double.valueOf(downVersion.getMainVer() + "." + downVersion.getMinVer());

        if (currentVersion >= serverVersion) {
            return false;
        } else {
            showUpdateDialog();
        }

        return false;
    }


    private void showUpdateDialog() {


//        MaterialDialog.Builder builder = new MaterialDialog.Builder(mAct)
//                .title(R.string.common_update_version)
//                .content(TextUtils.isEmpty(downVersion.getFix()) ? mAct.getString(R.string.common_version_update_info) : downVersion.getFix());
//        if (downVersion.isNeeded()) {
//            builder.positiveText("更新").negativeText("不更新").canceledOnTouchOutside(false);
//        } else {
//            builder.negativeText("忽略").positiveText("更新");
//        }
//
//        builder.negativeColor(ContextCompat.getColor(mAct, R.color.common_theme_blue))
//                .positiveColor(ContextCompat.getColor(mAct, R.color.common_theme_blue))
//                .onNegative(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        dialog.hide();
//                        if (downVersion.isNeeded()) {
//                            mAct.finish();
//                        }
//
//                    }
//                })
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        try {
//                            downSavePath = downSavePath + downVersion.getUrl().substring(downVersion.getUrl().lastIndexOf("/"));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        DownLoadUtil downLoadUtil = new DownLoadUtil.Builder(mAct, downSavePath, fileAuthority, downVersion.getUrl()).force(downVersion.isNeeded()).build();
//                        downLoadUtil.download();
//                        dialog.dismiss();
//                    }
//                }).show();

    }
}
