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

import io.renren.modules.generator.entity.ServiceMeetingRoomEntity;
import io.renren.modules.generator.service.ServiceMeetingRoomService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-23 18:21:23
 */
@RestController
@RequestMapping("generator/servicemeetingroom")
public class ServiceMeetingRoomController {
    @Autowired
    private ServiceMeetingRoomService serviceMeetingRoomService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:servicemeetingroom:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serviceMeetingRoomService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{roomId}")
    @RequiresPermissions("generator:servicemeetingroom:info")
    public R info(@PathVariable("roomId") Integer roomId){
		ServiceMeetingRoomEntity serviceMeetingRoom = serviceMeetingRoomService.getById(roomId);

        return R.ok().put("serviceMeetingRoom", serviceMeetingRoom);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:servicemeetingroom:save")
    public R save(@RequestBody ServiceMeetingRoomEntity serviceMeetingRoom){
		serviceMeetingRoomService.save(serviceMeetingRoom);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:servicemeetingroom:update")
    public R update(@RequestBody ServiceMeetingRoomEntity serviceMeetingRoom){
		serviceMeetingRoomService.updateById(serviceMeetingRoom);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:servicemeetingroom:delete")
    public R delete(@RequestBody Integer[] roomIds){
		serviceMeetingRoomService.removeByIds(Arrays.asList(roomIds));

        return R.ok();
    }

}
