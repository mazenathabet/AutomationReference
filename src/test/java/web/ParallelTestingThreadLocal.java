package web;

import Common.web.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParallelTestingThreadLocal extends Base {

    @Test
    public void test1() {
        getDriver().get("https://www.google.com/");
        System.out.println("Test1 thread id is : " + Thread.currentThread().getId());
        Assert.assertEquals("Google", getDriver().getTitle());
        Assert.assertEquals("https://www.google.com/", getDriver().getCurrentUrl());
        System.out.println("Test1 Completed ...");
    }

    @Test
    public void test2() {
        getDriver().get("https://www.google.com/");
        System.out.println("Test2 thread id is : " + Thread.currentThread().getId());
        Assert.assertEquals("Google", getDriver().getTitle());
        Assert.assertEquals("https://www.google.com/", getDriver().getCurrentUrl());
        System.out.println("Test2 Completed ...");
    }

    @Test
    public void test3() {
        getDriver().get("https://www.google.com/");
        System.out.println("Test3 thread id is : " + Thread.currentThread().getId());
        Assert.assertEquals("Google", getDriver().getTitle());
        Assert.assertEquals("https://www.google.com/", getDriver().getCurrentUrl());
        System.out.println("Test3 Completed ...");
    }

    @Test
    public void test4() {
        getDriver().get("https://www.google.com/");
        System.out.println("Test4 thread id is : " + Thread.currentThread().getId());
        Assert.assertEquals("Google", getDriver().getTitle());
        Assert.assertEquals("https://www.google.com/", getDriver().getCurrentUrl());
        System.out.println("Test4 Completed ...");
    }

    @Test
    public void test5() {
        getDriver().get("https://www.google.com/");
        System.out.println("Test5 thread id is : " + Thread.currentThread().getId());
        Assert.assertEquals("Google", getDriver().getTitle());
        Assert.assertEquals("https://www.google.com/", getDriver().getCurrentUrl());
        System.out.println("Test5 Completed ...");
    }
    @Test
    public void test6() {
        getDriver().get("https://www.google.com/");
        System.out.println("Test6 thread id is : " + Thread.currentThread().getId());
        Assert.assertEquals("Google", getDriver().getTitle());
        Assert.assertEquals("https://www.google.com/", getDriver().getCurrentUrl());
        System.out.println("Test6 Completed ...");
    }
}

