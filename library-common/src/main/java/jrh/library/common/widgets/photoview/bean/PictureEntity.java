package jrh.library.common.widgets.photoview.bean;

/**
 * Demo class
 *
 * @author ranxh
 * @e-mail : xianghui.ran@onesmart.org
 * @date 2019/5/914:07
 * @desc
 */
public class PictureEntity {
    //是否是本地模块
    private boolean isLocal;

    // isLocal =true 本地图片路径，isLocal=false 网络图片
    private String path;

    public PictureEntity( String path ,boolean isLocal) {
        this.isLocal = isLocal;
        this.path = path;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
