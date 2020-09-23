package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
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
        IPage<ServiceMeetingEntity> page = this.page(
                new Query<ServiceMeetingEntity>().getPage(params),
                new QueryWrapper<ServiceMeetingEntity>()
        );

        return new PageUtils(page);
    }

}