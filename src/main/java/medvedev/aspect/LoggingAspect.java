package medvedev.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Условный вызов и логирование метода в пакете service
    @Around("execution(* medvedev.services.*.*(..))")
    public Object conditionallyInvokeMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();

        // Пример условия: не выполнять методы, начинающиеся с "skip"
        if (!methodName.startsWith("skip")) {
            logger.info("Executing method: {}", pjp.getSignature().toShortString());
            try {
                Object result = pjp.proceed(); // вызов метода
                logger.info("Method completed: {}", pjp.getSignature().toShortString());
                return result;
            } catch (Throwable ex) {
                logger.error("Exception during method execution: {}",
                        pjp.getSignature().toShortString(), ex);
                throw ex;
            }
        } else {
            logger.info("Method skipped: {}", pjp.getSignature().toShortString());
            return null; // или можно вернуть значение по умолчанию
        }
    }

    // Логирование исключений, выбрасываемых методами в пакете service
    @AfterThrowing(pointcut = "execution(* medvedev.services.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in method: {}", joinPoint.getSignature().toShortString(), ex);
    }
}
