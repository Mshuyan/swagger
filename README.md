# swagger
## 介绍

> Swagger具备如下功能：
>
> + 在线生成restful风格接口文档
> + 对接口功能进行测试

## 使用

+ pom

  ```xml
  <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>2.9.2</version>
  </dependency>
  <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>2.9.2</version>
  </dependency>
  ```

+ `Swagger2配置类`

  ```java
  @Configuration
  @EnableSwagger2
  public class Swagger2 {
      @Bean
      public Docket createRestApi() {
          return new Docket(DocumentationType.SWAGGER_2)
                  .apiInfo(apiInfo())
                  .select()
                  //为当前包路径
                  .apis(RequestHandlerSelectors.basePackage("com.shuyan.swagger"))
                  .paths(PathSelectors.any())
                  .build();
      }
  
      private ApiInfo apiInfo() {
          return new ApiInfoBuilder()
                  //页面标题
                  .title("测试swagger")
                  //创建人、个人门户网站、邮箱
                  .contact(new Contact("Will", "https://www.github.com/Mshuyan", "434224591@qq.com"))
                  //版本号
                  .version("1.0")
                  //描述
                  .description("测试swagger使用")
                  .build();
      }
  }
  ```

+ `Controller`

  ```java
  @RestController
  @Api(tags = "测试swagger相关的api",description = "TestController")
  public class TestController {
  
      @ApiOperation(value = "根据关键字查询产品列表", notes = "根据关键字查询产品列表 note")
      @ApiImplicitParams({
              @ApiImplicitParam(name = "str", value = "要说的话", paramType = "query",required = true, dataType = "String")
      })
      // 当只有1个参数时，上面注解可替换为：
      // @ApiImplicitParam(name = "keywords", value = "关键字，以空格分割", required = true, dataType = "String")
  
      @GetMapping("/test")
      public String test(String str){
          return "you said " + str;
      }
  }
  ```

+ 启动项目并访问

  > 项目根路径  +  /swagger-ui.html
  >
  > 比如本次测试访问路径为`http://127.0.0.1:8080/api/swagger-ui.html`
  >
  > 结果如下图：

  ![861542278200_.pic](assets/861542278200_.pic.jpg) 

  > 代码中的配置在页面上都能找到对应的内容

## 说明

+ `@Api`的`value`属性没作用，需要使用`tags`和`description`属性

+ `paramType`

  > 有效值有如下几种：

  + path，路径参数，如`/api/test/{id}`

  + query，url请求参数，如`/api/test?id=1`

    > query是`paramType`属性的默认值

  + body，

  + header，请求头参数

  + form，form表单参数