package com.github.akofman.maven.plugins.gitreporting;

import com.github.akofman.maven.plugins.gitreporting.jaxb.XmlCommit;
import com.github.akofman.maven.plugins.gitreporting.jaxb.XmlContent;
import com.github.akofman.maven.plugins.gitreporting.jaxb.XmlSection;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: Alexis Kofman
 * Date: 11/09/11
 * Time: 20:53
 * <p/>
 * Convenient report generator from git repository.
 */
public class GitReportGenerator {

    private Git git;
    private List<Section> sections = new ArrayList<Section>();

    public GitReportGenerator(String gitRepositoryPath) throws IOException {
        this.git = Git.open(new File(gitRepositoryPath));
    }

    /**
     * Builds temporary XML file in order to check or edit it before exporting.
     *
     * @throws IOException
     * @throws NoHeadException
     */
    public void buildXmlContent(String pathToBuild) throws IOException, NoHeadException {
        File report = new File(pathToBuild + "report.xml");
        report.createNewFile();

        XmlContent xmlContent = new XmlContent();

        for (Section section : sections) {
            XmlSection xmlSection = new XmlSection();
            xmlSection.setTitle(section.getTitle());

            Iterator<RevCommit> iterator = git.log().call().iterator();
            while (iterator.hasNext()) {
                RevCommit revCommit = iterator.next();

                XmlCommit xmlCommit = new XmlCommit();

                if (section.getCommitPattern().matcher(revCommit.getFullMessage()).matches()) {
                    xmlCommit.setMessage(revCommit.getFullMessage());

                    xmlCommit.setLevel("level");
                    xmlCommit.setType("type");

                    xmlSection.addCommit(xmlCommit);
                }
            }

            xmlContent.addSection(xmlSection);
        }
        JAXB.marshal(xmlContent, report);
    }

    public Section addSection(String sectionTitle, String commitPattern) {
        Section section = new Section(sectionTitle, commitPattern);
        sections.add(section);
        return section;
    }
}
