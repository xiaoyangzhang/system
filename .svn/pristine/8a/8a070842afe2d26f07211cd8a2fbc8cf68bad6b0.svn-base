package com.yhyt.health.model.enumE;


import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangzhan@cis.com.cn
 * @version v1.0
 * @project business-client
 * @Description
 * @encoding UTF-8
 * @date 2018/1/2
 * @time 下午18:32
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 *     v1.0 商品类型的枚举
 * --------------------------------------------------
 * </pre>
 */
public enum ItemTypeEnum {

    SERVERPACKAGE(1,"package","服务包");

    public static ConcurrentHashMap<Byte,ItemTypeEnum> map = new ConcurrentHashMap<>();

    static{
        map.put((byte)1,SERVERPACKAGE);
    }

    private final  int id;

    private final String code;

    private final String title;

    ItemTypeEnum(final  int id,
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
