package com.yhyt.health;

import com.alibaba.fastjson.JSON;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    @org.junit.Test
    public void testString(){
        String str="(1,2,3),";
        String replace = str.replace("\\([1-9]+,", "\\(");
        System.out.println(replace);
    }

    @org.junit.Test
    public void testNull(){
        System.out.println("2".equals(2));
    }

    @org.junit.Test
    public void testJsonMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
        System.out.println(JSON.toJSONString(map));
    }
}
