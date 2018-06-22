package test;

import line.CommentLine;
import line.EmptyLine;
import line.LineFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LineFactoryTest {

    private LineFactory instance;

    @Before
    public void initialize() {
        instance = LineFactory.getInstance();
    }

    @Test
    public void checkEmptyLinePattern() {

        String reallyEmpty = "";
        Assert.assertTrue("A string without characters", instance.createLine(reallyEmpty) instanceof EmptyLine);

        String space = " ";
        Assert.assertTrue("A single whitespace string", instance.createLine(space) instanceof EmptyLine);

        String twoSpaces = "  ";
        Assert.assertTrue("A double space string", instance.createLine(twoSpaces) instanceof EmptyLine);

        String spaceAndLetter = " a";
        Assert.assertFalse("A string with a letter", instance.createLine(spaceAndLetter) instanceof EmptyLine);
    }

    @Test
    public void checkCommentLinePattern() {

        String justAComment = "\\\\";
        Assert.assertTrue("Two slashes", instance.createLine(justAComment) instanceof CommentLine);

        String aRealComment = "\\\\comment";
        Assert.assertTrue("A word right after slashes", instance.createLine(aRealComment) instanceof CommentLine);

        String anotherCom = "\\\\ TODO: |";
        Assert.assertTrue("Comment and slashes separated by spaces",
                instance.createLine(anotherCom) instanceof CommentLine);

        String confusing = "\\\\\\";
        Assert.assertTrue("3 slashes", instance.createLine(confusing) instanceof CommentLine);

        String doubleCom = "\\\\ bla \\\\ bla again";
        Assert.assertTrue("Comment-in-comment", instance.createLine(doubleCom) instanceof CommentLine);

        String notACom = "\\ heyyyyy \\";
        Assert.assertFalse("Not a comment", instance.createLine(notACom) instanceof CommentLine);
    }
}
