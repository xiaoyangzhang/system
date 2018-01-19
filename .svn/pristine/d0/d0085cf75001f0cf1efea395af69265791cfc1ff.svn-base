package com.yhyt.health.model.enumE;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangzhan@cis.com.cn
 * @version v1.0
 * @project business-client
 * @Description
 * @encoding UTF-8
 * @date 2018/1/2
 * @time 下午2:20
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 *     v1.0 商品分类的枚举
 * --------------------------------------------------
 * </pre>
 */
public enum ItemClassificationEnum {

    CUSTOMSERVICEPACKAGE(1,"package","定制服务包"),
    PRIVATEDOCTORSERVICE(2,"private","私人医生服务"),
    MEDICLAINSURANCE(3,"insurance","医保");


    public static ConcurrentHashMap<Byte,ItemClassificationEnum> map = new ConcurrentHashMap<>();


    static{
        map.put((byte)1,CUSTOMSERVICEPACKAGE);
        map.put((byte)2, PRIVATEDOCTORSERVICE);
        map.put((byte)3, MEDICLAINSURANCE);
    }

    private final  int id;

    private final String code;

    private final String title;

     ItemClassificationEnum(final  int id,
                            final String code,
                            final String title){
         this.id = id;
         this.code = code;
         this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
