package com.fish.utils;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;


public class Codegen {

    public static void main(String[] args) {
        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/kexie_open_judge?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("qx310320");
        GlobalConfig globalConfig = createGlobalConfigUseStyle();
        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);
        //生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();
        //设置根包
        globalConfig.getPackageConfig()
                .setBasePackage("com.fish");
        //设置表前缀和只生成哪些表，setGenerateTable 未配置时，生成所有表
        globalConfig.getStrategyConfig()
                .setTablePrefix("oj_");
        //设置生成 entity 并启用 Lombok
        globalConfig.enableEntity()
                .setWithLombok(true);
        //设置生成 mapper
        globalConfig.enableMapper();
        return globalConfig;
    }
}