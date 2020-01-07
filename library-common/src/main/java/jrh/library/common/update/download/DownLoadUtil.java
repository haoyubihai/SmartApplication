package jrh.library.common.update.download;

import android.app.Activity;
import android.os.Handler;

import com.afollestad.materialdialogs.MaterialDialog;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;

import aly.library.common.base.R;
import jrh.library.common.update.InstallUtil;
import jrh.library.common.utils.StringUtil;

/**
 * desc:
 * Created by jiarh on 2018/2/12 14:18.
 */

public class DownLoadUtil {
    private Activity mAct;
    private MaterialDialog showDownDialog;
    private boolean isDownLoading;
    private String downLoadPath;
    private boolean isForceUpdate;
    private boolean isShowProgressDialog = true;
    private String fileAuthority;
    private String url;
    private DownLoadUtil downLoadUtil;

//    private DownLoadUtil(Builder builder) {
//        downLoadUtil = new DownLoadUtil();
//        this.mAct = builder.mAct;
//        this.downLoadPath = builder.downLoadPath;
//        this.isForceUpdate = builder.isForceUpdate;
//        this.fileAuthority = builder.fileAuthority;
//        this.url = builder.url;
//
//    }

    private DownLoadUtil() {

        init();
    }

    private void init() {
        FileDownloader.setup(mAct);
    }


    public void download() {
        download(null);
    }

    public void download(FileDownloadListener listener) {

//        if (StringUtil.isBlank(url) || StringUtil.isBlank(downLoadPath) || StringUtil.isBlank(fileAuthority)) {
//            return;
//        }
//        if (isShowProgressDialog) {
//            if (listener == null) {
//                listener = downloadListener;
//            }
//        }
//
//        FileDownloader.getImpl().create(url).setPath(downLoadPath).setListener(listener).start();
//
//        showDownDialog = new MaterialDialog.Builder(mAct)
//                .title("正在下载")
//                .content("请稍等")
//                .canceledOnTouchOutside(!isForceUpdate)
//                .progress(false, 100, true)
//                .show();

    }

//
//    FileDownloadListener downloadListener = new FileDownloadListener() {
//        @Override
//        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//
//
//        }
//
//        @Override
//        protected void progress(BaseDownloadTask task, final int soFarBytes, final int totalBytes) {
//            isDownLoading = true;
//            int progress = (int) (((float) soFarBytes / totalBytes) * 100);
//            showDownDialog.setProgress(progress);
//        }
//
//        @Override
//        protected void completed(BaseDownloadTask task) {
//
//            showDownDialog.setContent(R.string.common_download_complete);
//            showDownDialog.dismiss();
//            InstallUtil.openAPKFile(mAct, downLoadPath, "");
//            mAct.finish();
//            isDownLoading = false;
//        }
//
//        @Override
//        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//            downFailed();
//        }
//
//        @Override
//        protected void error(BaseDownloadTask task, Throwable e) {
//            downFailed();
//        }
//
//        @Override
//        protected void warn(BaseDownloadTask task) {
//
//        }
//    };
//
//    private void downFailed() {
//        isDownLoading = false;
//        showDownDialog.setContent(R.string.common_download_failed);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                showDownDialog.dismiss();
//            }
//        }, 1000);
//
//        new File(downLoadPath).delete();
//    }
//
//    public static class Builder {
//        private Activity mAct;
//        private String downLoadPath;
//        private boolean isForceUpdate;
//        private String fileAuthority;
//        private String url;
//        private boolean isShowProgressDialog = true;
//
//        public Builder(Activity mAct, String downLoadPath, String fileAuthority, String url) {
//            this.mAct = mAct;
//            this.downLoadPath = downLoadPath;
//            this.fileAuthority = fileAuthority;
//            this.url = url;
//        }
//
//        public Builder force(boolean isForceUpdate) {
//            this.isForceUpdate = isForceUpdate;
//            return this;
//        }
//
//        public Builder showProgressDialog(boolean show) {
//            this.isShowProgressDialog = show;
//            return this;
//        }
//
//        public DownLoadUtil build() {
//            return new DownLoadUtil(this);
//        }
//    }

}
