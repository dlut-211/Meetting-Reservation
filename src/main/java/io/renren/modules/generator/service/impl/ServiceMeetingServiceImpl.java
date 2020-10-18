package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.renren.modules.generator.entity.ServiceMeetingWithPhone;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private SysUserService sysUserService;

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

//        for(int i=0;i<page.getRecords().size();i++) {
//            SysUserEntity user_now=(SysUserEntity) SecurityUtils.getSubject().getPrincipal();
////            String mobile=user_now.getMobile();
////            page.getRecords().get(i).setNumber(mobile);
////        }

        return new PageUtils(page);
    }


    @Override
    public  ServiceMeetingEntity findmeeting(Date start, Date end, String room){
        ServiceMeetingEntity target=new ServiceMeetingEntity();
        QueryWrapper<ServiceMeetingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_name",room)
        .le("start_time",start)
        .ge("end_time",end);
        target=baseMapper.selectOne(queryWrapper);
        return target;
    }

    @Override
    public List<ServiceMeetingEntity> chooselist(String params) {
        QueryWrapper<ServiceMeetingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("start_time",params);
        List<ServiceMeetingEntity>  page =baseMapper.selectList(queryWrapper);

        return page;
    }


    @Override
    public List<ServiceMeetingWithPhone> listwithphone(String params,String phone){

        if(params.equals("all"))
            params="";
        QueryWrapper<ServiceMeetingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("room_user",params);

        List<ServiceMeetingEntity>  alist =baseMapper.selectList(queryWrapper);

        for(int i=0;i<alist.size();i++)
             System.out.println(alist.get(i));


        List<ServiceMeetingWithPhone> phoneList=new ArrayList<>();

        for(int i=0;i<alist.size();i++) {

            ServiceMeetingWithPhone phoneitem=new ServiceMeetingWithPhone();
            phoneitem.setItem(alist.get(i));
            if(phone.equals("all"))
            {
                QueryWrapper<SysUserEntity> qw=new QueryWrapper<>();
                qw.like("email",alist.get(i).getRoomUser());
                String number = sysUserService.getBaseMapper().selectList(qw).get(0).getMobile();
                phoneitem.setMobile(number);
            }
            else {
                phoneitem.setMobile(phone);
            }

            phoneList.add(phoneitem);
        }
        return phoneList;
    }



}