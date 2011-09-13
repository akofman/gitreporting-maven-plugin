package com.github.akofman.maven.plugins.gitreporting.jaxb;

import javax.xml.bind.annotation.XmlElement;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Alexis Kofman
 * Date: 12/09/11
 * Time: 23:19
 */
public class Section {
    @XmlElement
    private Set<Commit> commits =  new HashSet<Commit>();

    public void addCommit(Commit commit){
        commits.add(commit);
    }

    public Set<Commit> getCommits() {
        return commits;
    }
}
