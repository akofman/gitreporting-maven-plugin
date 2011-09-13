package com.github.akofman.maven.plugins.gitreporting.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Alexis Kofman
 * Date: 12/09/11
 * Time: 23:00
 */
@XmlRootElement
public class XmlContent {
    @XmlElement
    private Set<Section> sections = new HashSet<Section>();

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public Set<Section> getSections() {
        return sections;
    }
}
