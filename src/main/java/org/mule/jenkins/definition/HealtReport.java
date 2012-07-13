package org.mule.jenkins.definition;

import java.io.Serializable;

public class HealtReport implements Serializable {
    private String description;
    private String iconUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
