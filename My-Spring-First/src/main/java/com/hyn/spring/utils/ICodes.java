package com.hyn.spring.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 
 * @Title:：RespServiceCodes.java 
 * @Package ：com.summit.homs.util 
 * @Description： TODO
 * @author： hyn   
 * @date： 2018年9月13日 上午9:20:45 
 * @version ： 1.0
 */
public class ICodes {

	private static final Logger log = LoggerFactory.getLogger(ICodes.class);

	public static Map<String, String> respMsg = new HashMap<>(50);

	/**
	 *  ==============================系统通用报错================================
	 */
	@CodeDesc(code = "0000", description = "操作成功")
	public static final String CODE_0000 = "0000";

	@CodeDesc(code = "9999", description = "服务端未知异常")
	public static final String CODE_9999 = "9999";

	@CodeDesc(code = "9991", description = "查询数据为空")
	public static final String CODE_9991 = "9991";

	@CodeDesc(code = "9992", description = "保存数据异常")
	public static final String CODE_9992 = "9992";

	@CodeDesc(code = "9993", description = "更新数据异常")
	public static final String CODE_9993 = "9993";

	@CodeDesc(code = "9994", description = "删除数据异常")
	public static final String CODE_9994 = "9994";
	
	@CodeDesc(code = "9995", description = "请求方式不正确")
	public static final String CODE_9995 = "9995";
	
	@CodeDesc(code = "9996", description = "请求的数据格式不正确")
	public static final String CODE_9996 = "9996";

	@CodeDesc(code = "9997", description = "更新数据失败，所更新的数据不存在")
	public static final String CODE_9997 = "9997";
	
	@CodeDesc(code = "9998", description = "删除数据失败，所删除的数据不存在")
	public static final String CODE_9998 = "9998";
	
	@CodeDesc(code = "9989", description = "上传文件失败")
	public static final String CODE_9989 = "9989";
	
	@CodeDesc(code = "9988", description = "上传文件不能为空")
	public static final String CODE_9988 = "9988";
	
	@CodeDesc(code = "9987", description = "上传文件失败，文件最大不能超过10M")
	public static final String CODE_9987 = "9987";
	
	@CodeDesc(code = "9986", description = "输入字段有误！")
	public static final String CODE_9986 = "9986";

	@CodeDesc(code = "9985", description = "数据转换异常！")
	public static final String CODE_9985 = "9985";
	
	@CodeDesc(code = "9984", description = "账号或密码错误！")
	public static final String CODE_9984 = "9984";
	
	@CodeDesc(code = "9983", description = "用户未激活，请联系管理员或者部长进行激活！")
	public static final String CODE_9983 = "9983";
	
	@CodeDesc(code = "9982", description = "旧密码错误！")
	public static final String CODE_9982 = "9982";
	
	@CodeDesc(code = "9981", description = "旧密码和新密码不能相同！")
	public static final String CODE_9981 = "9981";

	@CodeDesc(code = "9980", description = "部门没有指定部长，不能申请加入！")
	public static final String CODE_9980 = "9980";
	
	@CodeDesc(code = "9979", description = "部门没有指定部长，不能申请退出！")
	public static final String CODE_9979 = "9979";
	static {
		List<CodeDesc> list = new ArrayList<>(50);
		Field[] fields = ICodes.class.getFields();
		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				CodeDesc an = fields[i].getAnnotation(CodeDesc.class);
				if (an != null) {
					log.debug("Annotation[" + i + "] : " + an.code() + ":" + an.description());
					list.add(an);
				}
			}
		}
		if (list != null || list.size() > 0) {
			for (CodeDesc desc : list) {
				respMsg.put(desc.code(), desc.description());
			}
		}
	}
}
