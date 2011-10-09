package com.github.akofman.maven.plugins.gitreporting;

import com.github.akofman.maven.plugins.gitreporting.jaxb.XmlCommit;
import com.github.akofman.maven.plugins.gitreporting.jaxb.XmlContent;
import com.google.common.io.Files;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * User: Alexis Kofman
 * Date: 11/09/11
 * Time: 20:42
 */
public class GitReportGeneratorTest {

    @Test
    public void testBuildXmlContent() throws IOException, NoHeadException, NoMessageException, ConcurrentRefUpdateException,
            WrongRepositoryStateException {

        File gitRepositoryDirectory = Files.createTempDir();

        InitCommand init = Git.init();
        init.setDirectory(gitRepositoryDirectory);

        Git git = new Git(init.call().getRepository());
        git.commit().setMessage("Init Project").call();
        git.commit().setMessage("IMPROVEMENT 156 : Refactors Section.java").call();
        git.commit().setMessage("FEATURE 24 : Adds pdf export service").call();
        git.commit().setMessage("IMPROV 157 : Refactors Section.java").call();
        git.commit().setMessage("ISSUE 68 : Fixes pdf export service").call();

        GitReportGenerator gitReportGenerator = new GitReportGenerator(gitRepositoryDirectory.getPath());
        gitReportGenerator.addSection("Améliorations", "IMPROV.*").withOutputPattern("TRAC - $1");

        gitReportGenerator.buildXmlContent("/tmp/");

        File report = new File("/tmp/report.xml");
        XmlContent xmlContent = JAXB.unmarshal(report, XmlContent.class);

        Assert.assertTrue(xmlContent.getSections().size() == 1);

        List<XmlCommit> xmlCommits = xmlContent.getSections().get(0).getCommits();

        Assert.assertTrue(xmlCommits.size() == 2);
        Assert.assertTrue("IMPROV 157 : Refactors Section.java".equals(xmlCommits.get(0).getMessage()));
        Assert.assertTrue("IMPROVEMENT 156 : Refactors Section.java".equals(xmlCommits.get(1).getMessage()));

        report.delete();
    }

}
