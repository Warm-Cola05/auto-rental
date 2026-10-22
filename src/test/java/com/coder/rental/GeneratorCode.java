package com.coder.rental;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class GeneratorCode {
    private static final String AUTHOR="pat";
    private static final String JDBC_URL="jdbc:mysql://127.0.0.1:3306/auto_rental";
    private static final String JDBC_USERNAME="root";
    private static final String JDBC_PASSWORD="20050910hgj";
    private static final String OUT_DIR=".\\src\\main\\java";
    private static final String PACKAGE_NAME="com.coder";
    private static final String MODULE_NAME="rental";
    private static final String[] TABLES={
            "auto_maker","auto_brand","auto_info",
            "sys_dept","sys_permission","sys_role","sys_user","sys_role_permission","sys_user_role",
            "busi_customer","busi_maintain","busi_violation","busi_order","busi_rental_type"
    };
    private static final String[] PREFIX={"busi_","sys_"};
    /**
     * 生成代码的测试方法。
     * 该方法使用了FastAutoGenerator来自动化生成代码，配置了全局参数、包信息、策略以及模板引擎等。
     * 无需参数，执行后会根据配置生成相应的代码。
     */
    @Test
    void generatorCode(){
        // 创建代码生成器实例，并配置数据库连接信息
        FastAutoGenerator.create(JDBC_URL,JDBC_USERNAME,JDBC_PASSWORD)
                .globalConfig(builder->{
                    builder.author(AUTHOR)
                            // 开启swagger支持
                            .enableSwagger()
                            .outputDir(OUT_DIR);
                })
                // 配置包信息
                .packageConfig(builder->{
                    builder.parent(PACKAGE_NAME)
                            .moduleName(MODULE_NAME)
                            // 配置xml文件输出路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml,".\\src\\main\\resources\\mapper"));
                })
                // 配置生成策略，包括要包含的表、表名前缀等
                .strategyConfig(builder->{
                    builder.addInclude(TABLES)
                            .addTablePrefix(PREFIX)
                            // 配置实体类、控制器等的生成选项
                            .entityBuilder()
                            .enableLombok()
                            .enableChainModel()
                            .controllerBuilder()
                            .enableRestStyle();
                })
//        核心修改：使用自定义的实体类模板替换官方默认的
                .templateEngine(new FreemarkerTemplateEngine())
                // 显式指定实体模板路径
                .templateConfig(builder -> {
                    // 关键：让生成器去读你刚刚创建的这个自定义 freemarker 文件
                    builder.entity("templates/entity.java");
                })
                // 执行代码生成
                .execute();
    }
}
