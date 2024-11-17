package se.yrgo.crm.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class PerformanceTimingAdvice {

    public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable {
        //before
        long startTime = System.nanoTime();

        try {
            //proceed to target
            Object value = method.proceed();
            return value;
        } finally {
            //after
            long endTime = System.nanoTime();
            long timeTaken = endTime - startTime;
            System.out.println((String.format("Time taken for the method %s from the class %s took %.4fms",
                    method.getSignature().getName(),
                    method.getSignature().getDeclaringTypeName(),
                    timeTaken / 1000000.0)));
        }

    }
}
