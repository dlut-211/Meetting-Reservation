package io.renren.modules.generator.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.ServiceMeetingRoomDao;
import io.renren.modules.generator.entity.ServiceMeetingRoomEntity;
import io.renren.modules.generator.service.ServiceMeetingRoomService;


@Service("serviceMeetingRoomService")
public class ServiceMeetingRoomServiceImpl extends ServiceImpl<ServiceMeetingRoomDao, ServiceMeetingRoomEntity> implements ServiceMeetingRoomService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<ServiceMeetingRoomEntity> page = this.page(
                new Query<ServiceMeetingRoomEntity>().getPage(params),
                new QueryWrapper<ServiceMeetingRoomEntity>()
                        .like(StringUtils.isNotBlank(key),"room_name", key)
                        .isNotNull("room_name")
        );

        return new PageUtils(page);
    }
}