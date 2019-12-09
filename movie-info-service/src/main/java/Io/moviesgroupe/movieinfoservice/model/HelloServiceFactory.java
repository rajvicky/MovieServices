package Io.moviesgroupe.movieinfoservice.model;
import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.FactoryBean;

public class HelloServiceFactory implements FactoryBean<HelloService>{

	@Override
	public HelloService getObject() throws Exception {
		
		PySystemState systemState = Py.getSystemState();
		systemState.path.getArray();
		for(PyObject py:systemState.path.getArray())
		{
			System.out.println(py.asString());
		}
		PythonInterpreter pythonInterPreter=new PythonInterpreter();
		 PyObject pyObject=null;
		 PythonInterpreter interpreter=new PythonInterpreter();
		 String clsName="HelloService";
		try {
		    interpreter.exec("from " + "Io.moviesgroupe.movieinfoservice.model" + " import "+ "HelloService");
		    pyObject=interpreter.get(clsName);
		  }
		 catch (  Exception e) {
			 e.printStackTrace();
		    //logger.error("The Python module '" + "Io.moviesgroupe.movieinfoservice.model"  + "' is not found: "+ compactWhitespace(e.toString()));
		  }
		System.out.println(pyObject+" first HelloPython");
		pythonInterPreter.execfile("G://SpringBoot//movie-info-service//src//main//java//Io//moviesgroupe//movieinfoservice//Python//HelloServicePython.py");
		System.out.println("HelloPython");
		PyObject foo=pythonInterPreter.eval("HelloServicesPython()");
		System.out.println(foo+" foo HelloPython");
		PyFunction getCountFunc = (PyFunction)pythonInterPreter.get("GetHello", PyFunction.class);
		System.out.println(getCountFunc+" "+"GetHello");
		PyObject buildingObject = pythonInterPreter.get("HelloServicesPython").__call__(); 
		System.out.println(buildingObject+" "+"HelloNullPointer");
		return (HelloService) buildingObject.__tojava__(HelloService.class);
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return HelloService.class;
	}
}
