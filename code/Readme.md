配置VUE开发环境
1.安装Node.js
2.安装Webpack， npm install -g webpack
3.切换到Portal\vue目录，执行npm install
4.执行npm run dev, 然后访问http://localhost:8080

配置JAVA开发环境
1.设置环境变量MYSQL_HOST, RABBITMQ_HOST到DOCKER SERVER IP
2.Portal 项目端口 8081
3.Sever 项目端口 8082

Portal发布说明
1. 在MAVEN中将Profiles选中prd，这样会把vue生成的Js打包到JAR里面，然后直接访问http://localhost:8081，无需node环境。
2. 在MAVEN中Profiles选中war，会将项目打包成war格式。


使用Lombok来生成实体的getter/setter/toString/hashcode方法
1.IntelliJ idea需要安装lombok插件(https://github.com/mplushnikov/lombok-intellij-plugin),请参考链接中的步骤进行配置
