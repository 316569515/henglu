package test.hentglu.erp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DebugProxy implements InvocationHandler {

	 private Object obj;

	    public static Object newInstance(Object obj)
	    {
	        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
	                obj.getClass().getInterfaces(), new DebugProxy(obj));
	    }

	    private DebugProxy(Object obj)
	    {
	        //Greet接口的實現：GreetImpl
	        this.obj = obj;
	    }

	    //Method m：調用的方法
	    //Object[] args：方法要傳入的參數
	    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
	    {
	        Object result;
	        try
	        {
	            //自定義的處理
	            System.out.println("--before method " + m.getName());
	            //調用GreetImpl中方法
	            result = m.invoke(obj, args);
	        }
	        catch(InvocationTargetException e)
	        {
	            throw e.getTargetException();
	        }
	        catch(Exception e)
	        {
	            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
	        }
	        finally
	        {
	            System.out.println("--after method " + m.getName());
	        }
	        return result;
	    }

	    /**
	     * @param args
	     */
	    public static void main(String[] args)
	    {
	        Greet tmp = new GreetImpl();
	        
	        Greet greet = (Greet) DebugProxy.newInstance(tmp);
	        //生成的greet和tmp有相同的hashCode
	        
	        greet.sayHello("walter");
	        greet.goodBye();
	    }

}
