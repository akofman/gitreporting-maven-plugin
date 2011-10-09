package com.github.akofman.maven.plugins.gitreporting.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Alexis Kofman
 * Date: 12/09/11
 * Time: 23:19
 */
@XmlType
public class XmlSection {
    @XmlElement(name = "commit")
    private List<XmlCommit> commits =  new ArrayList<XmlCommit>();

    @XmlAttribute
    private String title;

    public void setTitle(String title){
        this.title = title;
    }

    public void addCommit(XmlCommit xmlCommit){
        commits.add(xmlCommit);
    }

    public List<XmlCommit> getCommits() {
        return commits;
    }
}
