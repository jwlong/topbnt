package com.topbnt.menu.mdl;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
public class ImageMessage extends BaseMessage{
    private String PicUrl;
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
