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
 * @date 2020-09-23 18:21:23
 */
@Data
@TableName("service_meeting_room")
public class ServiceMeetingRoomEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 */
	@TableId
	private Integer roomId;
	/**
	 * 会议室
	 */
	private String roomName;
	/**
	 * 会议室地点
	 */
	private String location;
	/**
	 * 状态（0:无设备，1:麦克风，2:投影仪，3:都有）
	 */
	private Integer equipment;
	/**
	 * 容纳人数
	 */
	private Integer capacity;
	/**
	 * 所属校区
	 */
	private String roomArea;

}
