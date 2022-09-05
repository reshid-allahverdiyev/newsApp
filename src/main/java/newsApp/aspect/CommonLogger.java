package newsApp.aspect;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArguments;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class CommonLogger {
    @Pointcut("within(newsApp.service..*)")
    public void services(){
    }

    @Pointcut("within(newsApp.aspect.GlobalExceptionHandler)")
    public void exceptions(){
    }

    @Before("services()")
    public void beforeExecuting(JoinPoint joinPoint){
        log.info("*****************************************");
        log.info("Before Executing",
                StructuredArguments.kv("methodName",joinPoint.getSignature().getName()),
                StructuredArguments.kv("parameters", Arrays.toString(joinPoint.getArgs()))
        );
    }

    @AfterThrowing(value = "services()",throwing="ex")
    public void afterThrowingError(JoinPoint joinPoint,Exception ex){
        log.info("ERROR IN SERVICE");
        ex.printStackTrace();
    }



//    @AfterReturning(value = "exceptions()", returning = "response")
//    public void afterReturningError(JoinPoint joinPoint, Object response){
//        log.info("Response",kv("response",response));
//        log.info(response.toString());
//    }

    @After("services()")
    public void afterExecuting(JoinPoint joinPoint){
        log.info("After Executing", StructuredArguments.kv("methodName",joinPoint.getSignature().getName()));
        log.info("*****************************************");
    }
}
