# <center> KexieOnlineJudger 😆</center>

## 📓 简介

一个简单(😇)的OJ后端，由kotlin语言开发，构建工具选用Gradle 8.4(下次打死也不用😅)。

技术栈：

* 基本后端框架：SpringBoot 3.1.6
  * web
  * data-redis
  * security
  * validation
  * json
* API文档生成： springdoc
* JWT：jjwt-0.12.3
* ORM：Mybatis-flex
* OpenFeign：基于RestTemplate的Http请求库

预期开发于取代原先社团使用的oj系统，这里是业务后端，代码运行的沙箱环境见：[sb-judger](https://github.com/msqtt/sb-judger?tab=readme-ov-file) 

## 🚀 开始

### 🔧 安装

1. 下载代码

~~~bash
git clone https://github.com/nefelibetas/KexieOnlineJudge
~~~

2. 导入idea

![image-20240306174011936](README.assets/image-20240306174011936.png)

3. 修改配置文件

`修改关于数据库、日志文件导出的配置`

## 🛠 API

* 用户相关功能
  * [x] 登录
  * [x] 注册
  * [x] 修改个人信息（不包括密码）
  * [x] 切换用户状态（是否启用）
  * 获取用户
    * [x] 管理员
    * [x] 普通用户
  * [x] 修改密码
* 栏目相关
  * [x] 创建栏目
    * 支持在创建时添加题目
  * [x] 添加题目到栏目中
  * [x] 移除栏目中的题目
  * [x] 获取栏目信息（包括其中的题目）
  * [x] 获取栏目可添加的的题目
  * [x] 切换栏目状态（是否启用）
  * [x] 删除栏目
  * 获取栏目
    * [x] 全部（分页）
    * [x] 特定
  * [x] 模糊查询
* 题目相关
  * [x] 添加题目
    * 支持在添加时添加标签、样例
  * 删除题目 (不建议做，涉及表太多)
  * [x] 禁用、启用题目
  * [x] 获取题目可选的标签 
  * [x] 修改题目信息
  * 获取题目信息
    * [x] 特定
    * [x] 全部（分页）
  * [x] 模糊查询
* 标签相关
  * 新增
    * [x] 批量
    * [x] 单个
  * [x] 删除
  * [x] 修改
  * 获取
    * [x] 批量
    * [x] 特定
  * [x] 模糊查询
* 样例相关
  * [x] 批量添加样例
  * [x] 更新样例信息
  * [x] 样例不测评
    * **暂时不做删除，会涉及到外键问题，应当选择某样例不参与测评**
* 题解相关
  * [x] 新增题解
  * [x] 获取题解详情（带一级评论）
  * [x] 获取全部题解
  * [x] 修改置顶状态（单置顶）
  * [x] 修改题解状态（禁用或启用）
* 题解评论相关
  * [x] 发布评论
  * [x] 删除评论
  * [x] 获取一级评论
  * [x] 获取二级评论
* 题解点赞相关
  * [x] 点赞和取消题解
  * [x] 点赞和取消评论
* 测评相关
  * [x] 试运行代码
  * [x] 判题
* 考试相关
  * [x] 查询全部试卷
  * [x] 查询特定试卷
  * [x] 添加试卷
  * [x] 修改试卷
  * [x] 修改试卷状态
* 消息相关
  * 添加消息
    * [x] 公告
    * [x] 通知
  * 修改通知阅读状态
    * [x] 单个
    * [ ] 批量
  * [x] 获取未读通知数量


## 后续优化方案

首先，目前的架构是单数据库(MySQL)，并未使用读写分离方案，所以，将来遇到大量读导致性能降低可以使用读写分离（先使用下方Redis方案，无法满足需求再上读写分离），使用中间件同步修改即可。

其次，Redis的应用较少，没有把某些只需要读的数据取出放入Redis中保存加快读（题目信息、题目样例以及测评的结果），所以遇到读导致性能降低先上这个。

然后就是压力问题，如果遇到某个时间使用率非常高，但某个时间段使用率几乎为零（夜猫子选手），可以考虑使用消息队列削峰填谷（正常程序中只做必要的操作（好像没有不必要操作），不必要操作可以转移到低使用率时间段）。

最后，我估计是不会到这步了，实在是并发量高到不行了，你就上微服务吧，估计那时候科协也已经非常有规模了（也可能是被攻击XD）。

