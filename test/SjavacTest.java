package test;

import main.Sjavac;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SjavacTest {

    private Sjavac program;
    private Class sjavacClass;

    @Before
    public void initialize() {
        program = new Sjavac();
        sjavacClass = program.getClass();
    }

    @Test
    public void programShouldRunWithExactlyOneArgument() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {

        Method checkArgs = sjavacClass.getDeclaredMethod("checkArgs", String[].class);
        checkArgs.setAccessible(true);

        String trueMessage = "The program accepts exactly with one argument!";
        String falseMessage = "The program should run with exactly one argument, but it accepted ";

        String[] oneArg = { "firstArg" };
        Assert.assertTrue(trueMessage, (boolean)checkArgs.invoke(program, new Object[]{oneArg}));

        String[] twoArgs = { "firstArg", "secArg" };
        Assert.assertFalse(falseMessage + "2 arguments", (boolean)checkArgs.invoke(program, new Object[]{twoArgs}));
    }
}
