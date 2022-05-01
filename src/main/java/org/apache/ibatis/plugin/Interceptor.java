/*
 *    Copyright 2009-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.plugin;

import java.util.Properties;

/**
 * 定义代理对象和代理逻辑的拦截器
 * @author Clinton Begin
 */
public interface Interceptor {

  /**
   * 执行代理逻辑
   */
  Object intercept(Invocation invocation) throws Throwable;

  /**
   * 通过Plugin.wrap方法生成代理对象
   */
  default Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  /**
   * 设置拦截器的属性
   */
  default void setProperties(Properties properties) {
    // NOP
  }

}
