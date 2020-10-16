package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-23 18:21:22
 */
@Data
@TableName("service_meeting")
public class ServiceMeetingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 */
	@TableId
	private Integer orderId;
	/**
	 * 使用单位（例:软件学院）
	 */
	private String department;
	/**
	 * 会议室预约人
	 */
	private String roomUser;
	/**
	 * 会议室名称
	 */
	private String roomName;
	/**
	 * 会议开始时间
	 */
	private Date startTime;
	/**
	 * 会议结束时间
	 */
	private Date endTime;
	/**
	 * 参数人数
	 */
	private Integer headCount;
	/**
	 * 参会领导
	 */
	private String leader;
	/**
	 * 会议主题
	 */
	private String meetingTheme;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 预约状态（0:审核中 1，通过，2：未通过，3:已取消）
	 */
	private Integer status;
	/**
	 * 电话
	 */
//	private String number;

}
