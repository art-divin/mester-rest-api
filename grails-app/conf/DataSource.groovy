dataSource {
  pooled = true
  driverClassName = "com.mysql.jdbc.Driver"
  dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
  cache.use_second_level_cache = true
  cache.use_query_cache = false
  //    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
  cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
  singleSession = true // configure OSIV singleSession mode
  flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
  development {
    dataSource {
      dbCreate = "create" // one of 'create', 'create-drop', 'update', 'validate', ''
      // Print Hibernate SQL to the console
      logSql = true
    }
  }
  test {
    dataSource {
      dbCreate = "update"
    }
  }
  production {
    dataSource {
      pooled = true
      dbCreate = "update"
      properties {
        validationQuery = "SELECT 1"
        testOnBorrow = true
        testOnReturn = true
        testWhileIdle = true
        timeBetweenEvictionRunsMillis = 1000 * 60 * 30
        numTestsPerEvictionRun = 3
        minEvictableIdleTimeMillis = 1000 * 60 * 30
      }
    }
  }
}
