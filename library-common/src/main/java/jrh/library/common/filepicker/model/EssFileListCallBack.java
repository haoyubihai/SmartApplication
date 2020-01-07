package jrh.library.common.filepicker.model;

import java.util.List;


public interface EssFileListCallBack {
    void onFindFileList(String queryPath, List<EssFile> essFileList);
}
