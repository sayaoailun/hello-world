#spring+boot工程或普通spring工程使用paas配置中心统一管理配置

##一、sdk依赖项

如果是maven项目，添加如下两个依赖：

``` xml
    <dependency>
    	<groupId>com.alibaba</groupId>
    	<artifactId>fastjson</artifactId>
    	<version>1.2.6</version>
    </dependency>
    <dependency>
    	<groupId>org.apache.httpcomponents</groupId>
    	<artifactId>httpclient</artifactId>
    	<version>4.5.2</version>
    </dependency>
```
如果是普通项目，添加如下三个jar包：

- fastjson-1.1.33.jar
- httpclient-4.2.1.jar
- httpcore-4.2.2.jar

具体版本可以根据项目情况进行调整，因为只使用了最基础的功能，各个版本差别不大。

##二、引入到spring工程

将源码拷贝到项目中，或者建立一个maven模块，其他项目可以引入这个maven模块

在config_center_sdk.zip中有如下几个文件：

- com.digitalchina.custom.util.ConfigClient.java
- com.digitalchina.custom.util.ConfigUtil.java
- com.digitalchina.custom.util.Request.java
- com.digitalchina.custom.util.WebClient.java
- com.digitalchina.custom.PAASEnvironmentPostProcessor.java
- com.digitalchina.custom.PAASPropertyPlaceholderConfigurer.java

util包下的四个文件的作用是连接配置中心拉取配置，返回一个Properties。

拉取配置时，会首先读取环境变量中的Config_host属性，如果环境变量中不存在这个属性，就认为是在调试模式下运行，会尝试读取VM参数中的Config_host值。

在正式运行环境下，容器部署的时候会将Config_host, Config_user, Config_password, Config_app, Config_version这个几个属性写入容器的环境变量中，其中Config_password为MD5加密形式，如果是在调试环境下，请在启动程序是追加VM参数-DConfig_host=127.0.0.1 –DConfig_user=admin _DConfig_password=pwd –Dconfig_app=testApp –Dconfig_version=v999这几个参数，注意Config_password为明文。

PAASEnvironmentPostProcessor.java是为spring boot项目编写的，spring boot项目引入这个类之后还需要在项目的resource文件夹下新建一个META-INF文件夹，在META-INF文件夹下新建一个spring.factories文件，然后写入如下一行：

> org.springframework.boot.env.EnvironmentPostProcessor=com.digitalchina.custom.PAASEnvironmentPostProcessor

对应的就是上面的PAASEnvironmentPostProcessor.java类，然后项目在启动的时候，就会去配置中心读取配置。

PAASPropertyPlaceholderConfigurer.java是为普通spring项目准备的，一般普通项目都会有一个applicationContext.xml，在这个文件中会引入一下properties文件
如：

``` xml
<context:property-placeholder location="classpath*:jdbc.properties" />
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
	<property name="driverClassName"
		value="${jdbc.driverClassName}" />
	<property name="url" value="${jdbc.url}" />
	<property name="username" value="${jdbc.username}" />
	<property name="password" value="${jdbc.password}" />
</bean>
```

现在配置中心通过PAASPropertyPlaceholderConfigurer.java来支持这种形式的引用：
配置一个bean，可以使用注解，也可以使用xml

``` xml
<bean id="myPropertyPlaceholderConfigurer" class=" com.digitalchina.custom.PAASPropertyPlaceholderConfigurer "/>
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <property name="driverClassName" value="${jdbc.driverClassName}" />
    <property name="url" value="${jdbc.url}" />
    <property name="username" value="${jdbc.username}" />
    <property name="password" value="${jdbc.password}" />
</bean>
```

使用配置的方式不变。

##三、配置中心使用图解：

如yaml这样写：

``` yaml
    spring:
    datasource:
    url: jdbc:mysql://221.178.232.93:3307/springboot-demo?useSSL=false
    username: springboot
    password: 12345678
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.jdbc.Driver
    initial-size: 1
    maximum-pool-size: 10
    connection-timeout: 5000
```

在配置中心添加配置项：

<table class="table table-bordered table-striped">
    <tr>
      <th>key</th>
      <th>value</th>
   </tr>
   <tr>
     <td>spring.datasource.url</td>
     <td>jdbc:mysql://221.178.232.93:3307/springboot-demo?useSSL=false</td>
   </tr>
   <tr>
     <td>spring.datasource.username</td>
     <td>springboot</td>
   </tr>
   <tr>
     <td>spring.datasource.password</td>
     <td>12345678</td>
   </tr>
   <tr>
     <td>spring.datasource.type</td>
     <td>com.zaxxer.hikari.HikariDataSource</td>
   </tr>
   <tr>
     <td>spring.datasource.driver-class-name</td>
     <td>com.mysql.jdbc.Driver</td>
   </tr>
   <tr>
     <td>spring.datasource.initial-size</td>
     <td>1</td>
   </tr>
   <tr>
     <td>spring.datasource.maximum-pool-size</td>
     <td>10</td>
   </tr>
   <tr>
     <td>spring.datasource.connection-timeout</td>
     <td>5000</td>
   </tr>
</table>

下面是普通properties

``` properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://10.1.155.217:3306/test?useUnicode=true&characterEncoding=UTF8
username=root
password=mysql
```

**定义初始连接数**
> initialSize=20

**定义最大连接数**
> maxActive=100

**定义最大空闲**
> maxIdle=20

**定义最小空闲**
> minIdle=1

**定义最长等待时间**
> maxWait=60000

在配置中心中添加配置项

<table class="table table-bordered table-striped">
   <tr>
      <th>key</th>
      <th>value</th>
   </tr>
   <tr>
      <td>driver</td>
      <td>com.mysql.jdbc.Driver</td>
   </tr>
   <tr>
      <td>url</td>
      <td>jdbc:mysql://10.1.155.217:3306/test?useUnicode=true&characterEncoding=UTF8</td>
   </tr>
   <tr>
      <td>username</td>
      <td>root</td>
   </tr>
   <tr>
      <td>password</td>
      <td>mysql</td>
   </tr>
   <tr>
      <td>initialSize</td>
      <td>20</td>
   </tr>
   <tr>
      <td>maxActive</td>
      <td>100</td>
   </tr>
   <tr>
      <td>maxIdle</td>
      <td>20</td>
   </tr>
   <tr>
      <td>minIdle</td>
      <td>1</td>
   </tr>
   <tr>
      <td>maxWait</td>
      <td>60000</td>
   </tr>
</table>
​	
上面两种方式
（PAASEnvironmentPostProcessor、PAASPropertyPlaceholderConfigurer）不可以同时使用，否则可能会发生重复读取，请根据项目情况选择一种方式使用。

引入配置后使用的方式有多种：

1、 使用@Value直接注入到类属性中

``` java
@Configuration
public class FileAProps {
    private String name;
    @Value("${dataDir}")
    private String info;
    public FileAProps(){

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInfo() {
        return info;
    }
}
```

2、 使用@ConfigurationProperties类注解直接注入到类属性中

``` java
@Configuration
@ConfigurationProperties(prefix = “jdbc”)
public class FileBProps {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this. url = url;
    }
}
```

如果属性是有前缀的，如jdbc.url，那么@ConfigurationProperties有prefix可以限定前缀，成员变量名与去掉前缀的属性名一致即可。

##四、配置动态引用

1、配置引用配置

某个应用的配置可以引用其他应用的配置。

如应用A有一项配置testKey，那么应用B的配置中就可以写${A#v333#testKey}来引用这项配置。

注意：333是A应用的应用版本，需要注意的是，这个版本其实相当于一个占位符，运行时会查找A应用的当前版本来替换这个值

2、配置引用环境变量

某个应用可以引用其他应用的环境变量，包括内置环境变量（HOST_IP、CONTAINER_IP、ACCESSPORT）和自定义环境变量。

现在有一个mysql应用，有一个web应用，web应用需要使用mysql应用进行数据存储，那么web应用中有一项配置

> jdbc.url=jdbc:mysql://${mysql#v123#CONTAINER_IP}:${mysql#v123#PORT}/paas?useUnicode=true&characterEncoding=UTF-8

那么在部署的时候，就能引用到真实的mysql容器的CONTAINER_IP和PORT。

上面示例中的PORT就是mysql应用的自定义环境变量，CONTAINER_IP就是内置环境变量

**注意：** 如果某个应用的配置里和环境变量里有同名项，那么优先使用配置当中的，即遇到动态引用的，首先在配置当中寻找，如果找不到，就到环境变量中寻找。<br/>
并且，动态引用只支持一级引用，如果说某个应用引用另一个应用的环境变量或者配置，而被引用的环境变量或配置也是一个动态引用，目前是不支持这种变量的解析，使用的时候需要注意一下。
