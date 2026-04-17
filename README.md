<div align="center">

# NapCat4j

_✨ 基于 [Shiro](https://github.com/MisakaTAT/Shiro) 的 NapCat QQ 机器人快速开发框架 ✨_

</div>


# Migration Guide

> 仅支持 `JDK 25+` 与 `SpringBoot 4.0.0+`，如需低版本请自行打包

# QuickStart

## 简介

NapCat4j 是在 Shiro 基础上针对 NapCat 进行二次开发形成的框架。NapCat4j 新增了适配 NapCat 的事件、api 调用封装，并提供了一套更简单的注解体系，便于更快、更简单地开发新 bot 以及从 Mirai 等重构迁移。

NapCat4j 从 2.5.3 版本的 Shiro 形成独立的 fork 分支，因此本身兼容 Shiro 该版本的全部功能。然而，在 bot 开发中，建议使用包 `fun.imiku.napcat4j` 下的注解，以使用 NapCat4j 的逻辑进行开发，仅仅使用 `com.mikuac.shiro` 下的 DTO、工具类等。这是因为 NapCat4j 依赖 Shiro 的事件分发逻辑工作，但并未禁用或修改 Shiro 本身的注解消费逻辑（仅删除了 Shiro 的插件功能），如果 Shiro 和 NapCat4j 的注解同时工作可能会产生意料之外的结果；同时，NapCat4j 开发的新功能不会兼容 Shiro 的分发体系。

## 依赖引入

### Maven
TODO
```xml
```

### Gradle Kotlin DSL
TODO
```kotlin
```

### Gradle Groovy DSL
TODO
```groovy
```

## 开发示例

建议创建一个空的 `JDK 25+` 与 `SpringBoot 4.0.5+` Springboot应用。

### 快速开始

> Napcat4j 使用 Shiro 作为底层，兼容 Shiro 的 websocket 配置
> 编写 `application.yaml` 配置文件
> 或参考 [进阶配置文件](https://misakatat.github.io/shiro-docs/advanced.html#进阶配置文件)

```yaml
shiro:
  ws:
    access-token: "some_token"
    client:
      # 启用正向连接
      enable: true
      url: "ws://127.0.0.1:3001"
```

TODO
```java
```

# Credits

* [Shiro](https://github.com/MisakaTAT/Shiro)

# License

```text
NapCat4j, a Shiro based NapCat java SDK
Copyright (C) <2026>  <V.A.>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
```
