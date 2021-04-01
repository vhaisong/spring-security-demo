package cn.qingmg.demoboot;

import cn.qingmg.demoboot.dao.UserDao;
import cn.qingmg.demoboot.entity.SysRole;
import cn.qingmg.demoboot.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemobootApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        SysUser u1 = new SysUser();
        u1.setUsername("user1");
        u1.setPassword("123");
        u1.setNickName("测试账号1");
        u1.setLocked(true);
        u1.setStatus(1);
        List<SysRole> rs1 = new ArrayList<>();
        SysRole r1 = new SysRole();
        r1.setCode("test1");
        r1.setName("test1角色");
        rs1.add(r1);
        u1.setRoles(rs1);
        userDao.save(u1);

        SysUser u2 = new SysUser();
        u2.setUsername("user2");
        u2.setPassword("123");
        u2.setNickName("测试账号2");
        u2.setLocked(true);
        u2.setStatus(1);
        List<SysRole> rs2 = new ArrayList<>();
        SysRole r2 = new SysRole();
        r2.setCode("test2");
        r2.setName("test2角色");
        rs2.add(r2);
        u2.setRoles(rs2);
        userDao.save(u2);

        SysUser u3 = new SysUser();
        u3.setUsername("user3");
        u3.setPassword("123");
        u3.setNickName("测试账号3");
        u3.setLocked(true);
        u3.setStatus(1);
        List<SysRole> rs3 = new ArrayList<>();
        SysRole r3 = new SysRole();
        r3.setCode("test3");
        r3.setName("test3角色");
        rs3.add(r3);
        u3.setRoles(rs3);
        userDao.save(u3);

        SysUser u4 = new SysUser();
        u4.setUsername("user4");
        u4.setPassword("123");
        u4.setNickName("测试账号4");
        u4.setLocked(true);
        u4.setStatus(1);
        List<SysRole> rs4 = new ArrayList<>();
        SysRole r4 = new SysRole();
        r4.setCode("test4");
        r4.setName("test4角色");
        rs4.add(r4);
        u4.setRoles(rs4);
        userDao.save(u4);
    }

}
