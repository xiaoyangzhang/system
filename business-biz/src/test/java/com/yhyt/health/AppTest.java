package com.yhyt.health;

import com.alibaba.fastjson.JSON;
import com.yhyt.health.model.SysBlacklist;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
        String str="<p>123333</p><p>3455666</p>";
        System.out.println(str.replaceAll("\\<p>","").replaceAll("\\</p>","</br>"));
        SysBlacklist sysBlacklist = new SysBlacklist();
        sysBlacklist.setId(1L);
        sysBlacklist.setOperator("zhangsan");
        sysBlacklist.setReason("fldfjdslfjsd");
        String content="";
        System.out.println(JSON.toJSONString(sysBlacklist));
    }
}
