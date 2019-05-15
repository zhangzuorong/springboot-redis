# springboot-redis

#### 1.基于redis实现消息队列

```
  redis测试接口：
    1：添加list
      #str为key名称
      http://47.95.117.206:8666/my/addList?str=list   
    2: 获取list
      #str为key名称 one 下标开始（包括one） two 下标结束（包括two）
      http://47.95.117.206:8666/my/getList?str=list&one=0&two=9   
    3: 删除list的元素
      #str为key名称 one 下标结束 （从0到one） value 要删除的value
      http://47.95.117.206:8666/my/rmList?str=list&one=1&value=v0  
    4: 发送消息
      #key为通道名称（程序监控 key1 key2通道）  msg为消息内容
      http://47.95.117.206:8666/my/sendMsg?key=key1&msg=hahahahah
```

#### 2.集成aop,以及自定义注解
#### 3.集成 mybatis,mysql,Druid(数据库连接池), mybatis-generator(MBG自动生成)
#### 4.测试springboot 使用异步方法


