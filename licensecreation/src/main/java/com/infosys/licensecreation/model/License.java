package com.infosys.licensecreation.model;

import java.util.List;
import java.util.Map;

public class License {
    private String softwareName;
    private String softwareVersion;
    private String softwareDescription;
    private String vendorName;
    private String vendorUrl;
    private String grantedTo;
    private String licensedTo;
    private String purpose;
    private Map<String,String> additionalInformation;
    private List<SoftwareModule> softwareModules;

    public List<SoftwareModule> getSoftwareModules() {
        return softwareModules;
    }

    public void setSoftwareModules(List<SoftwareModule> softwareModules) {
        this.softwareModules = softwareModules;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getSoftwareDescription() {
        return softwareDescription;
    }

    public void setSoftwareDescription(String softwareDescription) {
        this.softwareDescription = softwareDescription;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorUrl() {
        return vendorUrl;
    }

    public void setVendorUrl(String vendorUrl) {
        this.vendorUrl = vendorUrl;
    }

    public String getGrantedTo() {
        return grantedTo;
    }

    public void setGrantedTo(String grantedTo) {
        this.grantedTo = grantedTo;
    }

    public String getLicensedTo() {
        return licensedTo;
    }

    public void setLicensedTo(String licensedTo) {
        this.licensedTo = licensedTo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Map<String, String> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, String> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String toString() {
        return "License{" +
                "softwareName='" + softwareName + '\'' +
                ", softwareVersion='" + softwareVersion + '\'' +
                ", softwareDescription='" + softwareDescription + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", vendorUrl='" + vendorUrl + '\'' +
                ", grantedTo='" + grantedTo + '\'' +
                ", licensedTo='" + licensedTo + '\'' +
                ", purpose='" + purpose + '\'' +
                ", additionalInformation=" + additionalInformation +
                ", softwareModules=" + softwareModules +
                '}';
    }
}
