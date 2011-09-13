package com.github.akofman.maven.plugins.gitreporting;

import com.github.akofman.maven.plugins.gitreporting.jaxb.XmlContent;
import com.google.common.io.Files;
import org.eclipse.jgit.api.CommitCommand;
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
        CommitCommand commit = git.commit();
        commit.setMessage("Init Project").call();

        GitReportGenerator gitReportGenerator = new GitReportGenerator(gitRepositoryDirectory.getPath());
        gitReportGenerator.buildXmlContent();

        File report = new File("/tmp/report.xml");
        XmlContent xmlContent = JAXB.unmarshal(report,XmlContent.class);

        Assert.assertTrue("Init Project".equals(xmlContent.getSections().iterator().next().getCommits
                ().iterator().next().getMessage()));

        report.delete();
    }

}
