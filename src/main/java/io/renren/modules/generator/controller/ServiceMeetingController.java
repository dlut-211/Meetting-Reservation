package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.ServiceMeetingEntity;
import io.renren.modules.generator.service.ServiceMeetingService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-23 18:21:22
 */
@RestController
@RequestMapping("generator/servicemeeting")
public class ServiceMeetingController {
    @Autowired
    private ServiceMeetingService serviceMeetingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:servicemeeting:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serviceMeetingService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
    @RequiresPermissions("generator:servicemeeting:info")
    public R info(@PathVariable("orderId") Integer orderId){
		ServiceMeetingEntity serviceMeeting = serviceMeetingService.getById(orderId);

        return R.ok().put("serviceMeeting", serviceMeeting);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:servicemeeting:save")
    public R save(@RequestBody ServiceMeetingEntity serviceMeeting){
		serviceMeetingService.save(serviceMeeting);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:servicemeeting:update")
    public R update(@RequestBody ServiceMeetingEntity serviceMeeting){
		serviceMeetingService.updateById(serviceMeeting);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:servicemeeting:delete")
    public R delete(@RequestBody Integer[] orderIds){
		serviceMeetingService.removeByIds(Arrays.asList(orderIds));

        return R.ok();
    }

}
