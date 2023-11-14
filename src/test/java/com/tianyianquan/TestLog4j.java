package com.tianyianquan;

import com.tianyianquan.utils.ExecSysCommand;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
public class TestLog4j {
    private final Logger logger = Logger.getLogger(getClass());

    @Test
    public void test() throws Exception {
        logger.info("输出info");
        logger.debug("输出debug");
        logger.error("输出error");
    }
    @Test
    public void test1(){
        ExecSysCommand.execute("whoam");
    }
}
