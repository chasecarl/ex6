package test;

import line.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LineFactoryTest {

    private static LineFactory instance;

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

        String posMessEnd = "\nis a valid string";
        String negMessEnd = "\nis not a valid string";

        @Test
        public void normalDeclarationShouldPass_Int(){
            String declar = "int a;";
            Assert.assertTrue(declar + posMessEnd, instance.createLine(declar) instanceof IntegerVariableLine);
        }

        @Test
        public void manySpacesShouldPass_Int(){
            String messydec = "           int               b         ;             ";
            Assert.assertTrue(messydec + posMessEnd, instance.createLine(messydec) instanceof IntegerVariableLine);
        }

        @Test
        public void wrongTypeNameShouldNot_Int(){
            String notInt = " ind k;";
            Assert.assertFalse(notInt + negMessEnd, instance.createLine(notInt) instanceof IntegerVariableLine);
        }

        @Test
        public void notExistingModifierBeforeTypeShouldNotPass_Int(){
            String notADec = "q int s;";
            Assert.assertFalse(notADec + negMessEnd, instance.createLine(notADec) instanceof IntegerVariableLine);
        }

        @Test
        public void MoreThenOneUnderScoreInTheNameShouldPass_Int(){
            String dunderName = " int __;";
            Assert.assertTrue(dunderName + posMessEnd, instance.createLine(dunderName) instanceof IntegerVariableLine);
        }

        @Test
        public void OnlyOneUnderScoreInTheNameShouldNotPass_Int(){
            String onlyUnder = "int _;";
            Assert.assertFalse(onlyUnder + negMessEnd, instance.createLine(onlyUnder) instanceof IntegerVariableLine);
        }

        @Test
        public void NameStartsFromLetterAndEndsWithNumberShouldPass_Int(){
            String digitName = "int a1;";
            Assert.assertTrue(digitName + posMessEnd, instance.createLine(digitName) instanceof IntegerVariableLine);
        }

        @Test
        public void NameStartsFromLetterAndEndsWithUnderScoreShouldPass_Int(){
            String afterUnder = "int a_;";
            Assert.assertTrue(afterUnder + posMessEnd, instance.createLine(afterUnder) instanceof IntegerVariableLine);
        }

        @Test
        public void NameStartsFromNumberAndContainsLetterShouldNotPass_Int(){
            String digitFirst = "int 2a;";
            Assert.assertFalse(digitFirst + negMessEnd, instance.createLine(digitFirst) instanceof IntegerVariableLine);
        }

        @Test
        public void NameStartsFromNumberAndContainsUnderScoreAndNumberShouldNotPass_Int(){
            String digFirstAndUnder = "int 5_b;";
            Assert.assertFalse(digFirstAndUnder + negMessEnd, instance.createLine(digFirstAndUnder) instanceof IntegerVariableLine);
        }

        //Ilia Tests
        @Test
        public void typeWithValueWithoutSpaceShouldNotPass_Int() {
            String typeAndValWithoutSpace = "intnumber;";
            Assert.assertFalse(typeAndValWithoutSpace + negMessEnd, instance.createLine(typeAndValWithoutSpace) instanceof IntegerVariableLine);
        }

        @Test
        public void finalModifierDeclarationWithoutInitializationShouldPass_Int(){
            String finalInt = "final int a;";
            Assert.assertTrue(finalInt + negMessEnd, instance.createLine(finalInt) instanceof IntegerVariableLine);
        }

        @Test
        public void finalModifierDeclarationWithoutInitializationWithBigLetterShouldNotPass_Int(){
            String FinalInt = "Final int a;";
            Assert.assertFalse(FinalInt + negMessEnd, instance.createLine(FinalInt) instanceof IntegerVariableLine);
        }

        @Test
        public void normalInitialisationShouldPass_Int(){
            String normalIntialisation = "int a = 10;";
            Assert.assertTrue(normalIntialisation + negMessEnd, instance.createLine(normalIntialisation) instanceof IntegerVariableLine);
        }

        @Test
        public void normalInitialisationWithBigLettersInNameShouldPass_Int(){
            String normalIntialisation = "int A = 10;";
            Assert.assertTrue(normalIntialisation + negMessEnd, instance.createLine(normalIntialisation) instanceof IntegerVariableLine);
        }

        @Test
        public void normalInitialisationWithBigANdSmallfLettersInNameShouldPass_Int(){
            String normalIntialisation = "int Apple = 10;";
            Assert.assertTrue(normalIntialisation + negMessEnd, instance.createLine(normalIntialisation) instanceof IntegerVariableLine);
        }

        @Test
        public void initialisationWithTwoSimbolsOfEqualityShouldNotPass_Int(){
            String check = "int a == 10;";
            Assert.assertFalse(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void initialisationWithLetterInsteadOfNumberShouldNotPass_Int(){
            String check = "int a = k;";
            Assert.assertFalse(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void initialisationWithUnderScoreInsteadOfNumberShouldNotPass_Int(){
            String check = "int a = _;";
            Assert.assertFalse(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void initialisationWithoutSemicolonShouldNotPass_Int(){
            String check = "int a = 1";
            Assert.assertFalse(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void initialisationWithDoubleSemilconShouldNotPass_Int(){
            String check = "int a = 1;;";
            Assert.assertFalse(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void initialisationWithoutValueShouldNotPass_Int(){
            String check = "int a = ";
            Assert.assertFalse(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void initialisationWithValueNumberPlusLetterShouldNotPass_Int(){
            String check = "int a = 1b;";
            Assert.assertFalse(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void initialisationWithValueNumberPlusSpacePlusLetterShouldNotPass_Int(){
            String check = "int a = 1 b;";
            Assert.assertFalse(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void initialisationWithValueNumberPlusSpacePlusNumberShouldNotPass_Int(){
            String check = "int a = 1 2;";
            Assert.assertFalse(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void normalInitialisationOfTwoValuesShouldPass_Int(){
            String check = "int a = 1, b = 2;";
            Assert.assertTrue(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void normalInitialisationOfThreeValuesShouldPass_Int(){
            String check = "int a = 1, b = 2, c = 3;";
            Assert.assertTrue(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void normalInitialisationOfOneValueAndDeclarationOfAnotherShouldPass_Int(){
            String check = "int a = 1, b;";
            Assert.assertTrue(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void normalDeclarationOfOneValueAndInitialisationOfAnotherShouldPass_Int(){
            String check = "int a, b = 3;";
            Assert.assertTrue(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void normalDeclarationOfTwoValuesWithoutSpaceAfterCommaShouldPass_Int(){
            String check = "int a,b;";
            Assert.assertTrue(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void normalDeclarationOfTwoValuesWithSpaceAfterCommaShouldPass_Int(){
            String check = "int a,b;";
            Assert.assertTrue(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void normalDeclarationOfThreeValuesShouldPass_Int(){
            String check = "int a, b, c;";
            Assert.assertTrue(instance.createLine(check) instanceof IntegerVariableLine);
        }

        @Test
        public void normalDeclarationOfThreeValuesWithFinalModifierShouldPass_Int(){
            String check = "final int a, b, c;";
            Assert.assertTrue(instance.createLine(check) instanceof IntegerVariableLine);
        }
    }
}
