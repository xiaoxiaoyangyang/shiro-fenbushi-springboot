package com.example.realm;

import com.example.entity.User;
import com.example.mapper.PermissionMapper;
import com.example.mapper.UserMapper;
import com.example.utils.JWTToken;
import com.example.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 集成了jwt的realm源
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 11:27
 * @Version 1.0
 */
public class AuthenAuthorRealm extends AuthorizingRealm {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 鉴权的
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        String primaryPrincipal =(String) principalCollection.getPrimaryPrincipal();
        String username = JWTUtil.getUsername(principalCollection.toString());
        List<String> list = permissionMapper.selectAllPermission(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(list);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证的
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String username = authenticationToken.getPrincipal().toString();
//        User users =  userMapper.selectOne(username);
//        if(users!=null){
//            return new SimpleAuthenticationInfo(users.getName(),users.getPassword(),ByteSource.Util.bytes("OMGG"),this.getName());
//        }
//        return null;

        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User userBean = userMapper.selectOne(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, username, userBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }
        return new SimpleAuthenticationInfo(token, token, this.getName());
    }
}
