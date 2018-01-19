package com.yhyt.health.model.enumE;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangzhan@cis.com.cn
 * @version v1.0
 * @project business-client
 * @Description
 * @encoding UTF-8
 * @date 2018/1/5
 * @time 下午3:01
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 *     v1.0 商品状态的枚举
 * --------------------------------------------------
 * </pre>
 */
public enum ItemStateEnum {

    PENDING(1,"pending","待处理"),
    SHELVES(2,"shelves","上架"),
    PROCESSING(3,"processing","处理中"),
    OFFSHELF(4,"offshelf","下架 ");


    public static ConcurrentHashMap<Byte,ItemStateEnum> map = new ConcurrentHashMap<>();


    static{
        map.put((byte)1,PENDING);
        map.put((byte)2, SHELVES);
        map.put((byte)3, PROCESSING);
        map.put((byte)4, OFFSHELF);

    }

    private final  int id;

    private final String code;

    private final String title;

    ItemStateEnum(final  int id,
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
