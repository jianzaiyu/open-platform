package cn.ce.framework.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import cn.ce.framework.mybatis.scanner.MapperScanner;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * @author: ggs
 * @date: 2018-08-31 14:16
 **/
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter({DruidDataSourceAutoConfigure.class, MybatisAutoConfiguration.class})
@MapperScanner(basePackages = "${mybatis.scanPackages}", sqlSessionFactoryRef = "sqlSessionFactory")
public class PrimaryDSConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* cn.ce..service..*.*(..))";

    @Bean
    public TransactionInterceptor txAdvice(PlatformTransactionManager platformTransactionManager) {
        //正常事务
        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //只读事务
        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED_READONLY.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("batch*", txAttr_REQUIRED);
        source.addTransactionalMethod("insert*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("exec*", txAttr_REQUIRED);
        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("select*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("build*", txAttr_REQUIRED_READONLY);

        return new TransactionInterceptor(platformTransactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
}
