# smart-demo-test-doc
Version |  Update Time  | Status | Author |  Description
---|---|---|---|---
1.0|2023-02-21 17:30|update|ly|init api
1.1|2023-02-21 17:40|update|ly|add test api



## 
### 
**URL:** http://127.0.0.1/testSwagger/all.do

**Type:** GET


**Content-Type:** application/x-www-form-urlencoded;charset=UTF-8

**Description:** 

**Request-example:**
```
curl -X GET -i http://127.0.0.1/testSwagger/all.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
timestamp|int64|No comments found.|-
code|string|No comments found.|-
message|string|No comments found.|-
data|array|No comments found.|-
└─id|int32|No comments found.|-
└─create_date|object|No comments found.|-
└─update_date|object|No comments found.|-
└─name|string|No comments found.|-

**Response-example:**
```
{
  "timestamp": 1676973066190,
  "code": "65878",
  "message": "success",
  "data": [
    {
      "id": "1wm5rn",
      "create_date": "2023-02-21",
      "update_date": "2023-02-21",
      "name": "randy.kuphal"
    }
  ]
}
```

### 
**URL:** http://127.0.0.1/testSwagger/find.do

**Type:** GET


**Content-Type:** application/x-www-form-urlencoded;charset=UTF-8

**Description:** 

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int32|No comments found.|false|-

**Request-example:**
```
curl -X GET -i http://127.0.0.1/testSwagger/find.do?id=228
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
timestamp|int64|No comments found.|-
code|string|No comments found.|-
message|string|No comments found.|-
data|object|No comments found.|-
└─id|int32|No comments found.|-
└─create_date|object|No comments found.|-
└─update_date|object|No comments found.|-
└─name|string|No comments found.|-

**Response-example:**
```
{
  "timestamp": 1676973066190,
  "code": "65878",
  "message": "success",
  "data": {
    "id": "77jvmh",
    "create_date": "2023-02-21",
    "update_date": "2023-02-21",
    "name": "randy.kuphal"
  }
}
```

## 
### 
**URL:** http://127.0.0.1/test/all.do

**Type:** GET


**Content-Type:** application/x-www-form-urlencoded;charset=UTF-8

**Description:** 

**Request-example:**
```
curl -X GET -i http://127.0.0.1/test/all.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
timestamp|int64|No comments found.|-
code|string|No comments found.|-
message|string|No comments found.|-
data|object|No comments found.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "timestamp": 1676973066190,
  "code": "65878",
  "message": "success",
  "data": {}
}
```

### 
**URL:** http://127.0.0.1/test/find.do

**Type:** GET


**Content-Type:** application/x-www-form-urlencoded;charset=UTF-8

**Description:** 

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int32|No comments found.|false|-

**Request-example:**
```
curl -X GET -i http://127.0.0.1/test/find.do?id=31
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
timestamp|int64|No comments found.|-
code|string|No comments found.|-
message|string|No comments found.|-
data|object|No comments found.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "timestamp": 1676973066190,
  "code": "65878",
  "message": "success",
  "data": {}
}
```

### 
**URL:** http://127.0.0.1/test/.do

**Type:** GET


**Content-Type:** application/x-www-form-urlencoded;charset=UTF-8

**Description:** 

**Request-example:**
```
curl -X GET -i http://127.0.0.1/test/.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
timestamp|int64|No comments found.|-
code|string|No comments found.|-
message|string|No comments found.|-
data|object|No comments found.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "timestamp": 1676973066190,
  "code": "65878",
  "message": "success",
  "data": {}
}
```

### 
**URL:** http://127.0.0.1/test/error.do

**Type:** GET


**Content-Type:** application/x-www-form-urlencoded;charset=UTF-8

**Description:** 

**Request-example:**
```
curl -X GET -i http://127.0.0.1/test/error.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
timestamp|int64|No comments found.|-
code|string|No comments found.|-
message|string|No comments found.|-
data|object|No comments found.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "timestamp": 1676973066190,
  "code": "65878",
  "message": "success",
  "data": {}
}
```

## 错误码列表
Error code |Description
---|---
200|success
500|failed
200|ok
400|request error
401|no authentication
403|no authorities
500|server error

## 数据字典


### http状态码字典

Code |Type|Description
---|---|---
200|string|success
500|string|failed
200|string|ok
400|string|request error
401|string|no authentication
403|string|no authorities
500|string|server error
