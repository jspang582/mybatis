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
package org.apache.ibatis.mapping;

import javax.sql.DataSource;

import org.apache.ibatis.transaction.TransactionFactory;

/**
 * MyBatis 可以配置成适应多种环境，这种机制有助于将 SQL 映射应用于多种数据库之中，
 * 现实情况下有多种理由需要这么做。例如，开发、测试和生产环境需要有不同的配置；
 * 或者想在具有相同 Schema 的多个生产数据库中使用相同的 SQL 映射。还有许多类似的使用场景。
 * 尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境，
 * 所以，如果你想连接两个数据库，就需要创建两个 SqlSessionFactory 实例，每个数据库对应一个。而如果是三个数据库，就需要三个实例
 * <environments default="development">
 *   <environment id="development">
 *     <transactionManager type="JDBC">
 *       <property name="..." value="..."/>
 *     </transactionManager>
 *     <dataSource type="POOLED">
 *       <property name="driver" value="${driver}"/>
 *       <property name="url" value="${url}"/>
 *       <property name="username" value="${username}"/>
 *       <property name="password" value="${password}"/>
 *     </dataSource>
 *   </environment>
 * </environments>
 * @author Clinton Begin
 */
public final class Environment {

  // 环境 ID（比如：id="development"）。 SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, environment, properties);通过environment匹配id
  private final String id;
  // 事物工厂
  private final TransactionFactory transactionFactory;
  // 数据源
  private final DataSource dataSource;

  public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
    if (id == null) {
      throw new IllegalArgumentException("Parameter 'id' must not be null");
    }
    if (transactionFactory == null) {
      throw new IllegalArgumentException("Parameter 'transactionFactory' must not be null");
    }
    this.id = id;
    if (dataSource == null) {
      throw new IllegalArgumentException("Parameter 'dataSource' must not be null");
    }
    this.transactionFactory = transactionFactory;
    this.dataSource = dataSource;
  }

  public static class Builder {
    private final String id;
    private TransactionFactory transactionFactory;
    private DataSource dataSource;

    public Builder(String id) {
      this.id = id;
    }

    public Builder transactionFactory(TransactionFactory transactionFactory) {
      this.transactionFactory = transactionFactory;
      return this;
    }

    public Builder dataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      return this;
    }

    public String id() {
      return this.id;
    }

    public Environment build() {
      return new Environment(this.id, this.transactionFactory, this.dataSource);
    }

  }

  public String getId() {
    return this.id;
  }

  public TransactionFactory getTransactionFactory() {
    return this.transactionFactory;
  }

  public DataSource getDataSource() {
    return this.dataSource;
  }

}
