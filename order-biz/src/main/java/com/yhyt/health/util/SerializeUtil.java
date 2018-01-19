package com.yhyt.health.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 
* localadmin 作者: 
* @version 创建时间：2017年8月16日 下午8:04:29 
* 类说明 
*/
public class SerializeUtil {
	public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
         ByteArrayOutputStream baos = null;
         try {
              // 序列化
             baos = new ByteArrayOutputStream();
             oos = new ObjectOutputStream(baos);
             oos.writeObject(object);
              byte[] bytes = baos.toByteArray();
              return bytes;
        } catch (Exception e) {

        }
         return null;
  }

   public static Object unserialize( byte[] bytes) {
        ByteArrayInputStream bais = null;
         try {
              // 反序列化
             bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais);
              return ois.readObject();
        } catch (Exception e) {

        }
         return null;
  }
}
