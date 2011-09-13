package com.github.akofman.maven.plugins.gitreporting.jaxb;

import javax.xml.bind.annotation.XmlElement;

/**
 * User: Alexis Kofman
 * Date: 12/09/11
 * Time: 23:06
 */
public class Commit {
    private String message;
    private String type;
    private String level;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public String getLevel() {
        return level;
    }
}
