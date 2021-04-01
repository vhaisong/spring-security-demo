package cn.qingmg.demoboot.dao;

import cn.qingmg.demoboot.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<SysUser, Long> {

    SysUser findSysUserByUsername(String username);
}
