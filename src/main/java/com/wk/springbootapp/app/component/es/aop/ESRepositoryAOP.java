package com.wk.springbootapp.app.component.es.aop;

import com.wk.springbootapp.app.component.es.annotation.ESId;
import com.wk.springbootapp.app.component.es.bo.ESBO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Created by kavin on 2017/8/23.
 */
@Component
@Aspect
public class ESRepositoryAOP {

    private static final Logger logger = LoggerFactory.getLogger(ESRepositoryAOP.class);

    @Pointcut("@annotation(com.wk.springbootapp.app.component.es.annotation.ESRepoCommonCheck)")
    private void executeService() {
    }

    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        Object[] objs = jp.getArgs();
        ESRepository esRepository = (ESRepository) jp.getThis();
        Class clazz = null;
        if (objs.length > 0) {
            for (int i = 0; i < objs.length; i++) {
                Object obj = objs[i];
                if (obj.getClass().getName() == "java.lang.Class") {
                    clazz = (Class) obj;
                    break;
                }
            }
            if (!checkIsAssignableFromESBOAndIsOwnIdFieldAndIsContainESIdAnnotaion(clazz)) {
                logger.error("ES Common Repo Check Failed");
                throw new Exception("ES Common Repo Check Failed");
            } else {
                return jp.proceed();
            }
        }
        return null;
    }


    /**
     * 通用检查
     */
    private <T> Boolean checkIsAssignableFromESBOAndIsOwnIdFieldAndIsContainESIdAnnotaion(
        Class<T> clazz) {
        return isAssinableFromESBO(clazz) && isOwnIdFieldAndContainESIdAnnotation(clazz);
    }


    private <T> boolean isAssinableFromESBO(Class<T> clazz) {
        if (!(ESBO.class.isAssignableFrom(clazz))) {
            logger.error("document not extend from ESBO {}", clazz.toString());
            return false;
        }
        return true;
    }

    private <T> boolean isOwnIdFieldAndContainESIdAnnotation(Class<T> clazz) {
        Field idField = null;
        try {
            idField = clazz.getDeclaredField("id");
            if (!isContainESIdAnnotation(clazz, idField)) {
                return false;
            }
        } catch (NoSuchFieldException e) {
            logger.error("document not contain id field {}", clazz.toString());
            return false;
        }
        return true;
    }

    private <T> boolean isContainESIdAnnotation(Class<T> clazz, Field idField) {
        if (!hasFieldContainsIdAnnotaion(idField)) {
            logger.error("document not has a ESId annotation {}", clazz.toString());
            return false;
        }
        return true;
    }


    /**
     * 判断Field是否有ID注解
     */
    private Boolean hasFieldContainsIdAnnotaion(Field field) {
        return field.getAnnotation(ESId.class) != null;
    }
}
