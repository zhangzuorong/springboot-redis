# springboot-redis

#### 1.基于redis实现消息队列

```
  redis测试接口：
    1：添加list
      #str为key名称
      http://localhost:8666/my/addList?str=list   
    2: 获取list
      #str为key名称 one 下标开始（包括one） two 下标结束（包括two）
      http://localhost:8666/my/getList?str=list&one=0&two=9   
    3: 删除list的元素
      #str为key名称 one 下标结束 （从0到one） value 要删除的value
      http://localhost:8666/my/rmList?str=list&one=1&value=v0  
    4: 发送消息
      #key为通道名称（程序监控 key1 key2通道）  msg为消息内容
      http://localhost:8666/my/sendMsg?key=key1&msg=hahahahah
```

#### 2.集成aop,以及自定义注解
#### 3.集成 mybatis,mysql,Druid(数据库连接池), mybatis-generator(MBG自动生成)
#### 4.测试springboot 使用异步方法
#### 5.集成RedLock（基于redis实现分布式锁）
官方文档：https://github.com/redisson/redisson/wiki/2.-%E9%85%8D%E7%BD%AE%E6%96%B9%E6%B3%95#26-%E5%8D%95redis%E8%8A%82%E7%82%B9%E6%A8%A1%E5%BC%8F
```
1 添加依赖
      <dependency>
          <groupId>org.redisson</groupId>
          <artifactId>redisson</artifactId>
          <version>3.5.4</version>
      </dependency>
2 实现代码（本测试代码基于 单Redis节点模式）

    Config config = new Config();
    config.useSingleServer().setAddress("redis://47.95.117.206:6379").setClientName("clientName").setDatabase(1);
    RedissonClient redisson = Redisson.create(config);
    RLock redLock = redisson.getLock("REDLOCK_KEY");
    boolean isLock;
    try{
        isLock = redLock.tryLock();
        if(isLock){
            //实现自己的业务
        }else {
            System.out.println("锁被锁了");
        }
    }catch (Exception e){

    }finally {
        if(redLock.isHeldByCurrentThread()){
            redLock.unlock();
            System.out.println("关闭锁");
        }
    }
```


