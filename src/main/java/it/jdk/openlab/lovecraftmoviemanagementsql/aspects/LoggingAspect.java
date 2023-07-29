package it.jdk.openlab.lovecraftmoviemanagementsql.aspects;

import ch.qos.logback.classic.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static Logger logger = (Logger) LoggerFactory.getLogger("app.web.eldritch-vault");
    public static final String LOGGER_LEVEL = "INFO";

    @Around("@annotation(toLog)")
    public Object log(ProceedingJoinPoint joinPoint, ToLog toLog) throws Throwable {
        String level = toLog.level().toUpperCase();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = methodSignature.getMethod().getName();
        String[] parameterNames = methodSignature.getParameterNames();
        Class[] parameterTypes = methodSignature.getParameterTypes();
        Object[] parameterValues = joinPoint.getArgs();
        Class[] error = methodSignature.getExceptionTypes();

        int count = 0;
        StringBuilder builder = new StringBuilder();
        builder.append("Class: " + className + ", Method: " + methodName);
        while(count < parameterNames.length) {
            builder.append("\n [Parameter name: " + parameterNames[count] +
                    " - Parameter type: " + parameterTypes[count] +
                    " - Parameter value: " + parameterValues[count].toString() +
                    " - Exception type: " + error[count].toString());
            count++;
        }
        if(level.equals("ERROR"))
            logger.error(builder.toString());
        else if(level.equals("WARN"))
            logger.warn(builder.toString());
        else if(level.equals("INFO"))
            logger.info(builder.toString());
        else if(level.equals("DEBUG"))
            logger.debug(builder.toString());
        else if(level.equals("TRACE"))
            logger.trace(builder.toString());

        return joinPoint.proceed();
    }
}
