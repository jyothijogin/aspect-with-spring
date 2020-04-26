package com.example.aspect.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TracingAspect {
    private Logger logger = LoggerFactory.getLogger(TracingAspect.class);

    //to log before entering the method example()
    @Before("execution(String example())")
    public void trackingEntry(JoinPoint joinPoint){
        logger.info("Entering method named "+ joinPoint.getSignature());
    }

    //to log after exiting the method example()
    @After("execution(* example())")
    public void trackingExit(){
        logger.info("Exiting method..");
    }

    //to log after exiting example() method of any return type and with any number of arguements
    @Before("execution(* example(..))")
    public void trackAllmethods(JoinPoint joinPoint){
        logger.info("Executing..");
    }

    //to log before entering methods which are annotated with bean named TrackEntry
    @Before("@annotation(TrackEntry)")
    public void trackingBean(){
        logger.info("Entering method (annotation example)");
    }

    //executed if original method throws an exception
    @AfterThrowing(pointcut = "execution(* example())")
    public void trackException(){
        logger.error("Exception thrown");
    }

    //executed if the method returned successfully and return value is String
    @AfterReturning(pointcut = "execution(String example())",returning = "result")
    public void trackResult(String result){
        logger.info("Result: " + result);
    }

    //used when we need to catch an exception and the original method is executed only when proceed is returned from here
    @Around("execution(String example())")
    public Object trace(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        logger.info("Entering the method ",proceedingJoinPoint.getSignature().toString());
        try {
            return proceedingJoinPoint.proceed();
        }catch(Throwable ex){
            logger.error("Caught exception ",ex );
            throw ex;
        }finally {
            logger.info("Exiting the method ",proceedingJoinPoint.getSignature().toString());
        }

    }

}
