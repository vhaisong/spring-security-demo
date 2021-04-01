package cn.qingmg.demoboot.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity(name = "t_user")
public class SysUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String nickName;
    /**
     * 锁定状态 0 锁定（ false ） 1 正常（ true ）
     */
    @Column(columnDefinition = "boolean default true")
    private boolean locked;
    /**
     * 账号状态 0 冻结 1 正常
     */
    @Column(columnDefinition = "tinyint default 1")
    private int status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<SysRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getCode())));
        return authorities;
    }

    /**
     * 账户是否过期，根据需要，这里直接返回 true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 密码是否过期，根据需要，这里直接返回 true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    /**
     * 账号是否可用
     */
    @Override
    public boolean isEnabled() {
        return status == 1;
    }
}