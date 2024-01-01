# 理工今天吃什么

## 介绍
以iOS风格样式设计的
用来解决**吃什么**问题的小web应用

以PWA形式使用体验更佳
(ios端为例:safari访问该网页并选择添加到主屏幕)

## 技术栈
### 前端
Vue3 + Vite + Typescript + Tailwind + [Ionic](https://ionicframework.com/docs/)

### 后端
Spring Boot + Mybatis-Plus

application.properties
```properties
server.port=8080
server.servlet.context-path=/api

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/javaweb?serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=
spring.security.oauth2.resourceserver.jwt.issuer-uri=https://auth.app.nbtca.space/oidc
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=https://auth.app.nbtca.space/oidc/jwks

#使用了logto
logto.audience=https://food.app.nbtca.space

upload.path=C:\\Users\\Mephisto\\Projects\\img\\
upload.accessPath=/pic/
upload.staticAccessPath=/pic/**

spring.servlet.multipart.max-file-size=200MB
spring.servlet.multipart.max-request-size=200MB

mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl

```
