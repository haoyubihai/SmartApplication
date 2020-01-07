package jrh.library.common.update;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * desc:
 * Created by jiarh on 2018/8/8 17:27.
 */

public class DownVersion implements Parcelable {


    /** 主版本 * */
    private int mainVer;
    /** 次版本 * */
    private int minVer;
    /** 版本修改日志 * */
    private String fix;
    /** 是否必须更新 * */
    private boolean needed;
    /** 下载地址 * */
    private String url;

    public int getMainVer() {
        return mainVer;
    }

    public void setMainVer(int mainVer) {
        this.mainVer = mainVer;
    }

    public int getMinVer() {
        return minVer;
    }

    public void setMinVer(int minVer) {
        this.minVer = minVer;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    public boolean isNeeded() {
        return needed;
    }

    public void setNeeded(boolean needed) {
        this.needed = needed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mainVer);
        dest.writeInt(this.minVer);
        dest.writeString(this.fix);
        dest.writeByte(this.needed ? (byte) 1 : (byte) 0);
        dest.writeString(this.url);
    }

    public DownVersion() {
    }

    protected DownVersion(Parcel in) {
        this.mainVer = in.readInt();
        this.minVer = in.readInt();
        this.fix = in.readString();
        this.needed = in.readByte() != 0;
        this.url = in.readString();
    }

    public static final Parcelable.Creator<DownVersion> CREATOR = new Parcelable.Creator<DownVersion>() {
        @Override
        public DownVersion createFromParcel(Parcel source) {
            return new DownVersion(source);
        }

        @Override
        public DownVersion[] newArray(int size) {
            return new DownVersion[size];
        }
    };
}
