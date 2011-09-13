package com.github.akofman.maven.plugins.gitreporting;

import com.github.akofman.maven.plugins.gitreporting.jaxb.Commit;
import com.github.akofman.maven.plugins.gitreporting.jaxb.Section;
import com.github.akofman.maven.plugins.gitreporting.jaxb.XmlContent;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * User: Alexis Kofman
 * Date: 11/09/11
 * Time: 20:53
 *
 * Convenient report generator from git repository.
 */
public class GitReportGenerator {

    private Git git;

    public GitReportGenerator(String gitRepositoryPath) throws IOException {
        this.git = Git.open(new File(gitRepositoryPath));
    }

    /**
     * Builds temporary XML file in order to check or edit it before exporting.
     *  
     * @throws IOException
     * @throws NoHeadException
     */
    public void buildXmlContent() throws IOException, NoHeadException {
        File report = new File("/tmp/report.xml");
        report.createNewFile();
        XmlContent xmlContent = new XmlContent();
        Section section = new Section();
        xmlContent.addSection(section);

        Iterator<RevCommit> iterator = git.log().call().iterator();
        while(iterator.hasNext()){
            RevCommit revCommit = iterator.next();
            Commit commit = new Commit();
            commit.setMessage(revCommit.getFullMessage());
            commit.setLevel("level");
            commit.setType("type");
            section.addCommit(commit);
        }

        JAXB.marshal(xmlContent,report);
    }
}
