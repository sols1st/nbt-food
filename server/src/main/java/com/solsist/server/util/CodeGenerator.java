package com.solsist.server.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import org.springframework.beans.factory.annotation.Value;

public class CodeGenerator {

    String url = "jdbc:mysql://localhost:3306/javaweb?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    String username = "root";
    String password = "chen881587";

    public void generateCode() {
        System.out.println(url);
        FastAutoGenerator.create(new DataSourceConfig.Builder(url, username, password))
                .globalConfig(builder -> {
                    builder.author("solsist")
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
                .strategyConfig(builder -> {
                    builder.addInclude("container").build();
                })
                .execute();
    }

    public static void main(String[] args) {
        CodeGenerator generator = new CodeGenerator();
        generator.generateCode();
    }
}
