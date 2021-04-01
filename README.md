## 使用

tag: jpa-mysql
1. 更新 yml 中的数据连接信息，创建数据库 db_test
2. 运行 test 包中的测试类
3. 启动

### 测试内容

1. 依次登陆 user1 - user4，分别请求 `/test/111` ，`/test/222` ，`/test/333` ，`/test/444` ，`/test/555`
2. 进入数据库表 t_user_roles， 增加 t_user_id = 1， role_id = 4，再测试一次

### 预期效果

1. `/test/555` 任一用户都能请求，而 user1 只能请求 `/test/111` 而不能请求 `/test/222` ，`/test/333` ，`/test/444` 三个接口， user2 只能请求 `/test/222` ...
2. 更新数据库后，user1 可以请求 `/test/444` ， 其他没有变化。