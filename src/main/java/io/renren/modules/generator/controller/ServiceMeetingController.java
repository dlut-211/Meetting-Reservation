package io.renren.modules.generator.controller;

import java.util.*;

import io.renren.modules.generator.entity.ServiceMeetingRoomEntity;
import io.renren.modules.generator.service.ServiceMeetingRoomService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
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
    @Autowired
    private ServiceMeetingRoomService serviceMeetingRoomService;

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
     * 表单填充
     */
    @RequestMapping("/formuser")
    @RequiresPermissions("generator:servicemeeting:list")
    public R formuser(){
        SysUserEntity user_now=(SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        List<ServiceMeetingRoomEntity> room= serviceMeetingRoomService.list();
        List<Map<String, String>> list = new ArrayList<>();
        List<Map<String, Integer>> choosetable = new ArrayList<>();
//        int [][] choosetable=new int[14][room.size()];
        for (int i=7;i<21;i++)
        {
            Map map = new HashMap();
            for(int j=0;j<room.size();j++)
            {
//                choosetable[i][j]=0;
                if (j != 0) {
                    map.put("column" + j, "column" + j);
                } else {
                    map.put("date", i + ":00-" + (i + 1) + ":00");
                }
            }
            list.add(map);
        }

        List<ServiceMeetingEntity> table= serviceMeetingService.chooselist("2020-09-24");

        for (int i=0;i<table.size();i++)
        {
//            System.out.println("time");
//            System.out.println(table.get(i).getStartTime().toString());
//            System.out.println("row");
//            System.out.println(Integer.parseInt( table.get(i).getStartTime().toString().split(" ")[3].substring(0,2)));
            Map map = new HashMap();
            int a=Integer.parseInt( table.get(i).getStartTime().toString().split(" ")[3].substring(0,2));
           // map.put(table.get(i).getRoomName(),a );
            map.put("chose",table.get(i).getRoomName()+"_"+a );
            choosetable.add(map);
            System.out.println(map);
        }


        System.out.println(user_now);
        return R.ok().put("now_user", user_now).put("room",room).put("list",list).put("choosetable",choosetable);
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
