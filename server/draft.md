# FOOD

## DATABASE

 - ### Location
    - id
    - name
    - description
   
 - ### Restaurant
    - id
    - name
    - pic
    - user_id
    - description
    - location_id

 - ### comment
    - id
    - restaurant_id
    - user_id
    - content
    - score
    - gmt_create
    - gmt_modified
    
 - ### user
    - id
    - role
    - username
    - password
    - nickname
    - avatar
    - gmt_create


```json
{
   "page": {
      "currentPage": 1,
      "pageSize": 20 
   },
   "queryParams": {
      "name": "张三", 
      "age": 30
   }
}
```