package test;

import line.*;
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

        String notStartCom = "some shit \\\\";
        Assert.assertFalse("Slashes must appear at the start of the line",
                instance.createLine(notStartCom) instanceof CommentLine);
    }

    @Test
    public void checkIntegerVariableLinePattern() {

        String posMessEnd = "\nis a valid string";
        String negMessEnd = "\nis not a valid string";


        String declar = "int a;";
        Assert.assertTrue(declar + posMessEnd, instance.createLine(declar) instanceof IntegerVariableLine);

        String messydec = "           int               b         ;             ";
        Assert.assertTrue(messydec + posMessEnd, instance.createLine(messydec) instanceof IntegerVariableLine);

        String notInt = " ind k;";
        Assert.assertFalse(notInt + negMessEnd, instance.createLine(notInt) instanceof IntegerVariableLine);

        String notADec = "q int s;";
        Assert.assertFalse(notADec + negMessEnd, instance.createLine(notADec) instanceof IntegerVariableLine);

        String dunderName = " int __;";
        Assert.assertTrue(dunderName + posMessEnd, instance.createLine(dunderName) instanceof IntegerVariableLine);

        String digitName = "int a1;";
        Assert.assertTrue(digitName + posMessEnd, instance.createLine(digitName) instanceof IntegerVariableLine);

        String afterUnder = "int a_;";
        Assert.assertTrue(afterUnder + posMessEnd, instance.createLine(afterUnder) instanceof IntegerVariableLine);

        String digitFirst = "int 2a;";
        Assert.assertFalse(digitFirst + negMessEnd, instance.createLine(digitFirst) instanceof IntegerVariableLine);

        String onlyUnder = "int _;";
        Assert.assertFalse(onlyUnder + negMessEnd, instance.createLine(onlyUnder) instanceof IntegerVariableLine);

        String digFirstAndUnder = "int 5_b;";
        Assert.assertFalse(digFirstAndUnder + negMessEnd, instance.createLine(digFirstAndUnder)
                instanceof IntegerVariableLine);
    }

    /*
    It's actually not the way we should right it
    It's better to write a separate method for every test (because of modularity)
    or for every very specialized set of tests (the first option is preferable though)
    The method name should have a word "should" in it and needs to be self-explanatory
    (like method names in SjavacTest - don't be afraid of lengthy names)
    TODO: IF SOMETHING GOES WRONG IN THE PATTERN FOR IntegerVariableLine AND THE METHOD ABOVE FAILS -
    TODO: WE PROBABLY NEED TO DIVIDE IT INTO SEPARATE METHODS. BUT FOR NOW IT WILL STAY AS IT IS
    Here is a signature for the first one (I also created a new static class for it and added it to AllTests,
    because the method with the same name can be for all data types):
     */

    public static class IntVar {
        @Test
        public void finalModifierDeclarationWithoutInitializationShouldPass() {

//        String testString = "blabla";
            Assert.assertTrue(true);
        }
    }
}
