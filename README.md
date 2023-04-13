# 项目介绍
项目包含springboot2.x框架，适用于开发Web项目，其中集成swagger在线文档、smart接口文档，抽象的MVC，Javers审计日志、接口花费时间aop。

### 自定义banner
```text
修改./src/main/resources/banner.txt即可
```

### Swagger
```text
运行后访问http://localhost:9999/swagger-ui/index.html
```

### Smart
```text
smart配置文档在：./src/main/resources/smart-doc.json

//生成html
mvn -Dfile.encoding=UTF-8 smart-doc:html

也可以直接在maven插件中
选择对应模块->Plugins->smart-doc进行对应的接口文件类型生成
生成后会在项目中生成smart文件夹
```

### 待优化以及补充