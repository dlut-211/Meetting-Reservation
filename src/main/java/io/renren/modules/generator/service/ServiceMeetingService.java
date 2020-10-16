package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.ServiceMeetingEntity;
import io.renren.modules.generator.entity.ServiceMeetingWithPhone;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-23 18:21:22
 */
public interface ServiceMeetingService extends IService<ServiceMeetingEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ServiceMeetingEntity> chooselist(String params);

    List<ServiceMeetingWithPhone> listwithphone(String params,String phone);
}

