# 配置文件
## application.properties

```properties
server.port=8080
server.servlet.context-path=/api

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/javaweb?serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=

spring.redis.database=0
spring.redis.host=10.89.193.12
spring.redis.port=6379
spring.redis.password=

mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
