# springboot-redis

#### 1.基于redis实现消息队列
#### 2.集成aop,以及自定义注解
#### 3.集成 mybatis,mysql,Druid(数据库连接池), mybatis-generator(MBG自动生成)
#### 4.测试springboot 使用异步方法

```
redis测试接口：
  1：添加list
  http://47.95.117.206:8666/my/addList?str=list   #str为key名称
  2: 获取list
  http://47.95.117.206:8666/my/getList?str=list&one=0&two=9   #str为key名称 one 下标开始（包括one） two 下标结束（包括two）
  3: 删除list的元素
  http://47.95.117.206:8666/my/rmList?str=list&one=1&value=v0  #str为key名称 one 下标结束 （从0到one） value 要删除的value
```
