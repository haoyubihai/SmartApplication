package jrh.library.common.filepicker.adapter;


import java.util.List;

import jrh.library.common.filepicker.model.EssFile;

/**
 * OnFileSelectListener
 * Created by 李波 on 2018/2/26.
 */

public interface OnFileSelectListener {
    void onSelected(List<EssFile> essFileList);
}
