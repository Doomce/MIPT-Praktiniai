package com.dom.mipt1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    /**
     <b>This test is checking constructor.</b><br>
     Input - null <br>
     Output - IllegalArgumentException
    **/
    @Test(expected = IllegalArgumentException.class)
    public void testTextUtils_Constructor0() {
        new TextUtils(null);
    }

    /**
     <b>This test is checking constructor.</b><br>
     Input - empty string <br>
     Output - IllegalArgumentException
    **/
    @Test(expected = IllegalArgumentException.class)
    public void testTextUtils_Constructor1() {
        new TextUtils("");
    }

    /**
     <b>This test is checking constructor.</b><br>
     Input - " " <br>
     Output - TextUtils -> Text = " " (1 symbol)
    **/
    @Test
    public void testTextUtils_Constructor2() {
        TextUtils result = new TextUtils(" ");
        assertEquals(1, result.countSymbols());
    }


    /**
     <b>This test is checking count words function.</b><br>
     Input - "Lorem Ipsum is simply dummy text of the printing and typesetting industry." <br>
     Output - 12
    **/
    @Test
    public void testTextUtils_CountWords0() {
        TextUtils result = new TextUtils("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        assertEquals(12, result.countWords());
    }

    /**
     <b>This test is checking count words function.</b><br>
     Input - "Lorem.Ipsum.is.simply,dummy, text of. the printing and typesetting industry. " <br>
     Output - 12
     **/
    @Test
    public void testTextUtils_CountWords1() {
        TextUtils result = new TextUtils("Lorem.Ipsum.is.simply,dummy, text of. the printing and typesetting industry. ");
        assertEquals(12, result.countWords());
    }

    /**
     <b>This test is checking count symbols function.</b><br>
     Input - "Lorem.Ipsum.is.simply,dummy, text of. the printing and typesetting industry." <br>
     Output - 12
     **/
    @Test
    public void testTextUtils_CountSymbols() {
        TextUtils result = new TextUtils("Lorem.Ipsum.is.simply,dummy, text of. the printing and typesetting industry.");
        assertEquals(76, result.countSymbols());
    }
}