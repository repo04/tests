/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * 
 * @author somesh.bansal
 */
public class MyTransformer implements IAnnotationTransformer{
    
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {
        
        if ("testTchrPostsOn_Wall_CrsWall".equals(testMethod.getName())) {
            System.out.println("Inside testTchrPostsOn_Wall_CrsWall");
            annotation.setDependsOnMethods(null);            
        }
       
    }
}
