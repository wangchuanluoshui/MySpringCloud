package com.hyn.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hyn.spring.entity.UserInfo;
import com.hyn.spring.repository.UserRepository;
import com.hyn.spring.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    UserRepository userInfoDao;

    @Override
    @CachePut(value = "userinfocache",key="#userInfo.id") //@CachePut缓存新增的或更新的数据到缓存，其中缓存名称为people，数据的key是person的id。
    public UserInfo save(UserInfo userInfo) {
        UserInfo u = userInfoDao.save(userInfo);
        log.info("为userinfo的id（也是cache的key）:" + u.getId() + "数据做了缓存");
        return u;
    }

    @Override
    @CacheEvict(value = "userinfocache") //@CacheEvict从缓存people中删除key为id的数据。
    public void remove(Long id) {
        log.info("删除了userinfo的id（也是cache的key）:" + id + "数据缓存");
        userInfoDao.deleteById(id);
    }

    @Override
    @Cacheable(value = "userinfocache",key="#userInfo.id") //@Cacheable缓存key为person的id数据到缓存people中。
    public UserInfo findOne(UserInfo userInfo) {
        Optional<UserInfo> u = userInfoDao.findById(userInfo.getId());
        log.info("为userinfo的id（也是cache的key）:" + u.get().getId() + "数据做了缓存");
        return u.get();
    }
    
    @Override
    @Cacheable(value = "alluserinfo") //@Cacheable缓存key为person的id数据到缓存people中。
    public List<UserInfo> findAll() {
    	return userInfoDao.findAll();
    }
    
}
