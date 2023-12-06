package com.solsist.server.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

public class CodeGenerator {

    String url = "localhost";
    String username = "root";
    String password = "chen881587";
    static String[] tables = new String[]{"restaurant"};

    public void generateCode(String table) {
        FastAutoGenerator.create(new DataSourceConfig.Builder("jdbc:mysql://" + url + ":3306/javaweb?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false", username, password))
                .globalConfig(builder -> {
                    builder.author("solsist")
                            .disableOpenDir()
                            .commentDate("2024-01-01")
                            .outputDir("src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.solsist.server")
                            .entity("entity")
                            .mapper("mapper")
                            .service("service")
                            .controller("controller");
                })
                .templateConfig(builder -> {
                    builder.disable()
                        .entity("templates/entity.java")
                            .service("templates/service.java")
                            .serviceImpl("templates/serviceImpl.java")
                            .mapper("templates/mapper.java")
                            .controller("templates/controller.java")
                            .build();
                        }

                )
                .strategyConfig(builder -> {
                    builder.addInclude(table)
                            .entityBuilder()
                            .idType(IdType.AUTO)
                            .formatFileName("%sEntity")
                            .enableLombok() //使用lombok
                            .enableTableFieldAnnotation()//实体类字段注解
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .mapperBuilder()
                            .enableMapperAnnotation()//开启mapper注解
                            .enableBaseResultMap()//启用 BaseResultMap 生成
                            .enableBaseColumnList();//启用 BaseColumnList
                }).execute();
    }

    public static void main(String[] args) {
        CodeGenerator generator = new CodeGenerator();
        for (String table : tables){
            generator.generateCode(table);
        }
    }
}
