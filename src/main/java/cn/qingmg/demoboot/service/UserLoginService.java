package cn.qingmg.demoboot.service;

import cn.qingmg.demoboot.dao.UserDao;
import cn.qingmg.demoboot.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userDao.findSysUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
        return user;
    }
}
