# 汽车租赁管理系统（auto_rental）

一个汽车租赁业务的后台管理系统后端，覆盖 **车辆档案、客户、订单、维保、违章、财务统计** 等完整租赁业务链路，并提供基于 RBAC 的用户权限体系。

## 技术栈

| 分类 | 选型 |
| --- | --- |
| 框架 | Spring Boot 3.1、MyBatis-Plus 3.5 |
| 安全 | Spring Security + JWT |
| 存储 | MySQL 8、Redis |
| 文件 / 消息 | 阿里云 OSS、Spring Mail、EasyExcel |
| 其他 | Hutool、FastJson2、SpringDoc OpenAPI |

## 功能模块

- **认证授权**：Spring Security 登录、JWT 令牌签发与校验过滤器、方法级权限控制、RBAC 用户-角色-权限模型、动态路由菜单树
- **基础档案**：车辆品牌、厂商、租赁类型、车辆信息
- **客户管理**：客户档案维护、客户账号登录
- **租赁业务**：车辆租赁订单（下单 / 还车 / 状态流转）
- **维保与违章**：保养维护记录（按里程提醒）、违章记录处理
- **财务统计**：按日 / 按月营收与成本统计报表
- **组织架构**：部门树形结构管理
- **公共能力**：阿里云 OSS 图片上传、邮件通知、统一返回体、全局异常处理、Redis 缓存工具

## 目录结构

```
src/main/java/com/coder/rental
├── config      # MyBatis-Plus、CORS、OSS、全局异常配置
├── controller  # 接口层
├── entity      # 数据库实体
├── mapper      # Mapper 接口
├── security    # Spring Security 配置、JWT 过滤器、登录处理器
├── service     # 业务接口与实现
├── utils       # JWT、Redis、结果封装、部门/路由树工具
└── vo          # 视图对象
src/main/resources
├── mapper                  # 自定义 SQL 映射
├── templates/entity.java.ftl  # 代码生成模板
└── application.yaml        # 数据源、Redis、OSS、邮件配置
```

## 本地运行

1. 创建 MySQL 数据库 `auto_rental`
2. 启动本地 Redis
3. 修改 `src/main/resources/application.yaml` 中的数据库、Redis、OSS、邮件配置（默认占位为 `your-xxx`，需替换为自己的）
4. 运行 `AppServer`，默认端口 `8888`

> 配置中的 OSS / 邮箱等敏感信息已替换为占位符，运行前需自行填写。
