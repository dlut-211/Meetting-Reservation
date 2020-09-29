package io.renren.modules.generator.service.impl;

import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.ServiceMeetingDao;
import io.renren.modules.generator.entity.ServiceMeetingEntity;
import io.renren.modules.generator.service.ServiceMeetingService;


@Service("serviceMeetingService")
public class ServiceMeetingServiceImpl extends ServiceImpl<ServiceMeetingDao, ServiceMeetingEntity> implements ServiceMeetingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        if(key.equals("userlist"))
        {
            SysUserEntity user_now=(SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            key=user_now.getEmail();
        }
        IPage<ServiceMeetingEntity> page = this.page(
                new Query<ServiceMeetingEntity>().getPage(params),
                new QueryWrapper<ServiceMeetingEntity>()
                        .like(StringUtils.isNotBlank(key),"room_user", key)
                        .isNotNull("room_name")
        );

        return new PageUtils(page);
    }

    @Override
    public List<ServiceMeetingEntity> chooselist(String params) {
        QueryWrapper<ServiceMeetingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("start_time",params);
        List<ServiceMeetingEntity>  page =baseMapper.selectList(queryWrapper);

        return page;
    }



}