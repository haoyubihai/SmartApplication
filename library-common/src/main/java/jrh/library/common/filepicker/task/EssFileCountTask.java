package jrh.library.common.filepicker.task;

import android.os.AsyncTask;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import jrh.library.common.filepicker.model.EssFile;
import jrh.library.common.filepicker.model.EssFileCountCallBack;
import jrh.library.common.filepicker.model.EssFileFilter;

/**
 * EssFileCountTask
 */

public class EssFileCountTask extends AsyncTask<Void, Void, Void> {

    private int position;
    private String queryPath;
    private String[] types;
    private EssFileCountCallBack countCallBack;
    private int childFileCount = 0;
    private int childFolderCount = 0;

    public EssFileCountTask(int position, String queryPath, String[] types, EssFileCountCallBack countCallBack) {
        this.position = position;
        this.queryPath = queryPath;
        this.types = types;
        this.countCallBack = countCallBack;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        File file = new File(queryPath);
        File[] files = file.listFiles(new EssFileFilter(types));
        if(files == null){
            return null;
        }
        List<EssFile> fileList = EssFile.getEssFileList(Arrays.asList(files));
        for (EssFile essFile :
                fileList) {
            if(essFile.isDirectory()){
                childFolderCount++;
            }else {
                childFileCount++;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(countCallBack!=null){
            countCallBack.onFindChildFileAndFolderCount(position, String.valueOf(childFileCount), String.valueOf(childFolderCount));
        }
    }
}
