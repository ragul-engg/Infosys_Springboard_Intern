package com.infosys.licensecreation.model;


import java.util.List;
import java.util.Map;

public class SoftwareModule {
    private String moduleName;
    private String moduleVersion;
    private List<Constraint> constraints;
    private Map<String,String> module;


    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleVersion() {
        return moduleVersion;
    }

    public void setModuleVersion(String moduleVersion) {
        this.moduleVersion = moduleVersion;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public String toString() {
        return "SoftwareModule{" +
                "moduleName='" + moduleName + '\'' +
                ", moduleVersion='" + moduleVersion + '\'' +
                ", constraints=" + constraints +
                ", module=" + module +
                '}';
    }
}