package org.to9.key.sdk.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class BeanBindMachine {

    @JSONField(name = "version")
    private Integer lastVersion;

    @JSONField(name = "download")
    private String downloadUrl;

    @JSONField(name = "expire")
    private Date expireTime;

    @JSONField(name = "notice")
    private String notice;

    @Override
    @JSONField(serialize = false)
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Integer getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(Integer lastVersion) {
        this.lastVersion = lastVersion;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
