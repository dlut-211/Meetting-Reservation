package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.ServiceMeetingRoomEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-23 18:21:23
 */
public interface ServiceMeetingRoomService extends IService<ServiceMeetingRoomEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

