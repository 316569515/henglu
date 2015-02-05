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
	        //Greet�ӿڵČ��F��GreetImpl
	        this.obj = obj;
	    }

	    //Method m���{�õķ���
	    //Object[] args������Ҫ����ą���
	    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
	    {
	        Object result;
	        try
	        {
	            //�Զ��x��̎��
	            System.out.println("--before method " + m.getName());
	            //�{��GreetImpl�з���
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
	        //���ɵ�greet��tmp����ͬ��hashCode
	        
	        greet.sayHello("walter");
	        greet.goodBye();
	    }

}
