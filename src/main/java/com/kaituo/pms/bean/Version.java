package com.kaituo.pms.bean;

public class Version {
    private Integer versionId;

    private String versionCurrent;

    private String versionHistory;

    private String versionOverview;

    private String versionCompany;

    private String versionDeveloper;

    public Version(Integer versionId, String versionCurrent, String versionHistory, String versionOverview, String versionCompany, String versionDeveloper) {
        this.versionId = versionId;
        this.versionCurrent = versionCurrent;
        this.versionHistory = versionHistory;
        this.versionOverview = versionOverview;
        this.versionCompany = versionCompany;
        this.versionDeveloper = versionDeveloper;
    }

    public Version() {
        super();
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public String getVersionCurrent() {
        return versionCurrent;
    }

    public void setVersionCurrent(String versionCurrent) {
        this.versionCurrent = versionCurrent == null ? null : versionCurrent.trim();
    }

    public String getVersionHistory() {
        return versionHistory;
    }

    public void setVersionHistory(String versionHistory) {
        this.versionHistory = versionHistory == null ? null : versionHistory.trim();
    }

    public String getVersionOverview() {
        return versionOverview;
    }

    public void setVersionOverview(String versionOverview) {
        this.versionOverview = versionOverview == null ? null : versionOverview.trim();
    }

    public String getVersionCompany() {
        return versionCompany;
    }

    public void setVersionCompany(String versionCompany) {
        this.versionCompany = versionCompany == null ? null : versionCompany.trim();
    }

    public String getVersionDeveloper() {
        return versionDeveloper;
    }

    public void setVersionDeveloper(String versionDeveloper) {
        this.versionDeveloper = versionDeveloper == null ? null : versionDeveloper.trim();
    }
}