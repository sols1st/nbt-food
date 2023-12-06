# api 文档

## 1. 通用返回格式

```json
{
  "code": 0,
  "msg": "success",
  "data": {}
}
```

## 2. 通用分页参数

```json
{
  "currentPage": 1,
  "pageSize": 10,
  "queryParams": {}
}
```

## 3.用户

### 3.1 用户分页查询

#### 3.1.1 请求

```json
{
  "currentPage": 1,
  "pageSize": 10,
  "queryParams": {
    "username": "a"
  }
}
```

#### 3.1.2 响应
```json
{
"code": 200,
"data": {
"records": [
{
"id": 2,
"username": "a",
"password": "a"
},
{
"id": 1,
"username": "admin",
"password": "admin"
}
],
"totalRecords": 2,
"totalPages": 1
},
"msg": "success"
}
```
