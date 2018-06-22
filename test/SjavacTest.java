package test;

import main.Sjavac;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SjavacTest {

    private Sjavac program;
    private Class sjavacClass;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void initialize() {
        program = new Sjavac();
        sjavacClass = program.getClass();
    }

    @Test
    public void throwsIllegalNumberOfArgumentsExceptionIfThereIsNotOneArgument() throws Throwable {

        Class exClass = Class.forName("main.IllegalNumberOfArgumentsException");
        exception.expect(exClass);

        Field exMessage = exClass.getDeclaredField("message");
        exMessage.setAccessible(true);
        Constructor constructor = exClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        exception.expectMessage(exMessage.get(constructor.newInstance()).toString());

        Method checkArgs = sjavacClass.getDeclaredMethod("checkArgs", String[].class);
        checkArgs.setAccessible(true);

        String[] oneArg = { "firstArg"};
        checkArgs.invoke(program, new Object[]{oneArg}); // should be fine

        String[] twoArg = { "firstArg", "secArg"};
        try {
            checkArgs.invoke(program, new Object[]{twoArg});
        }
        // TODO: REFLECTION MECHANISM WRAPS ALL EXCEPTIONS THROWN WITH InvocationTargetException (there is nothing to do)
        catch (InvocationTargetException ite) {
            throw ite.getCause();
        }
    }
}