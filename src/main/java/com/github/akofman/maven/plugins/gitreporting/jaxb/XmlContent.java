package com.github.akofman.maven.plugins.gitreporting.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Alexis Kofman
 * Date: 12/09/11
 * Time: 23:00
 */
@XmlRootElement
public class XmlContent {
    @XmlElement(name = "section")
    private List<XmlSection> sections = new ArrayList<XmlSection>();

    public void addSection(XmlSection section) {
        this.sections.add(section);
    }

    public List<XmlSection> getSections() {
        return sections;
    }
}
