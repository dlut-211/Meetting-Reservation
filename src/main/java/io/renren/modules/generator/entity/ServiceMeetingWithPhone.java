package io.renren.modules.generator.entity;

import io.renren.modules.generator.entity.ServiceMeetingEntity;

public class ServiceMeetingWithPhone {
    private ServiceMeetingEntity item;

    private String mobile;

    public ServiceMeetingWithPhone() {
    }

    public ServiceMeetingWithPhone(ServiceMeetingEntity item, String mobile) {
        this.item = item;
        this.mobile = mobile;
    }

    public ServiceMeetingEntity getItem() {
        return item;
    }

    public void setItem(ServiceMeetingEntity item) {
        this.item = item;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
