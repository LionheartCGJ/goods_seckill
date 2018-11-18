package org.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void testGetById() throws Exception {
        long id = 1000L;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
        /**
         * exposer=Exposer [ exposed=true, md5=91fd0305a91195f04d3e02d7b73f129d,
         * seckillId=1000, now=0, start=0, end=0]
         */
    }

    @Test
    public void testExecuteSeckill() throws Exception {
        long id = 1000L;
        long phone = 17600119185L;
        String md5 = "91fd0305a91195f04d3e02d7b73f129d";
        SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
        logger.info("seckillExecution={}", seckillExecution);
        /**
         * seckillExecution=SeckillExecution [seckillId=1000, state=1,
         * stateInfo=秒杀成功, successKilled=SuccessKilled [seckillId=1000,
         * userPhone=17600119184, state=0, createTime=Sat Apr 08 01:06:37 CST
         * 2017, seckill=Seckill [seckillId=1000, name=1000元秒杀iphone6,
         * number=99, startTime=Sat Apr 08 01:06:37 CST 2017, endTime=Mon May 01
         * 00:00:00 CST 2017, createTime=Tue Apr 04 14:45:04 CST 2017]]]
         */
    }

    //测试代码完整逻辑，注意重复执行
    @Test
    public void testSeckillLogic() throws Exception {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
        if (exposer.isExposed()) {
            long phone = 17600119185L;
            String md5 = exposer.getMd5();
            SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
            logger.info("seckillExecution={}", seckillExecution);
        }
    }
    
    // 测试存储过程实现秒杀逻辑
    @Test
    public void testSeckillProcedure() throws Exception {
        long seckillId = 1000L;
        long userPhone = 17600118284L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        logger.info("exposer={}", exposer);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId, userPhone, md5);
            logger.info("seckillExecution={}", seckillExecution);
        }
    }
}
