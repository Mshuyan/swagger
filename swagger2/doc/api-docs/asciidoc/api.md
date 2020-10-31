# 测试swagger


<a name="overview"></a>
## Overview
测试swagger使用


### Version information
*Version* : 1.0


### Contact information
*Contact* : Will  
*Contact Email* : 434224591@qq.com


### URI scheme
*Host* : localhost:8080  
*BasePath* : /


### Tags

* 测试swagger相关的api : Test Controller




<a name="paths"></a>
## Resources

<a name="12f80e692e8b6f0c0c26e76282ec2d3b"></a>
### 测试swagger相关的api
Test Controller


<a name="testusingget"></a>
#### 测试
```
GET /test
```


##### Description
note


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**str**  <br>*required*|要说的话|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `\*/*`


##### Example HTTP request

###### Request path
```
/test?str=string
```


##### Example HTTP response

###### Response 200
```json
"string"
```


<a name="testlongusingget"></a>
#### 测试Long类型必须指定example问题
```
GET /test/{id}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**id**  <br>*required*|Long 类型|integer (int64)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `\*/*`


##### Example HTTP request

###### Request path
```
/test/{id}?id=1
```


##### Example HTTP response

###### Response 200
```json
"string"
```


<a name="testapimodelpropertyusingget"></a>
#### 测试ApiModelProperty用法
```
GET /test1/{id}
```


##### Parameters

|Type|Name|Schema|
|---|---|---|
|**Query**|**id**  <br>*optional*|integer (int64)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `\*/*`


##### Example HTTP request

###### Request path
```
/test1/{id}
```


##### Example HTTP response

###### Response 200
```json
"string"
```


<a name="testreturnusingget"></a>
#### 测试返回数据说明
```
GET /test2
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ReturnDto](#returndto)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `\*/*`


##### Example HTTP request

###### Request path
```
/test2
```


##### Example HTTP response

###### Response 200
```json
{
  "id" : 1
}
```




<a name="definitions"></a>
## Definitions

<a name="returndto"></a>
### ReturnDto
订单查询请求数据


|Name|Description|Schema|
|---|---|---|
|**id**  <br>*optional*|订单ID  <br>**Example** : `1`|integer (int64)|





