package com.github.akofman.maven.plugins.gitreporting;

import java.util.regex.Pattern;

/**
 * User: kushi
 * Date: 13/09/11
 * Time: 02:55
 */
public class Section {
    private String title;
    private Pattern commitPattern;
    private String outputPattern;

    public Section(String title, String commitPattern) {
        this.title = title;
        this.commitPattern = Pattern.compile(commitPattern);
    }

    public void withOutputPattern(String outputPattern) {
        this.outputPattern = outputPattern;
    }

    public String getTitle() {
        return title;
    }

    public Pattern getCommitPattern() {
        return commitPattern;
    }

    public String getOutputPattern() {
        return outputPattern;
    }
}
