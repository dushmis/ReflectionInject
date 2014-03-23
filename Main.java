import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main{
  @Inject
  String name=null;

  @Inject
  K k=null;

  @Inject
  Gen<String> x=null;

  @Inject
  Object o=null;

  public static void main(String args[]) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

    Main a = new Main();

    a.getAllAndPrint("Before");

    Class<?> clazz = a.getClass();
    Field[] f = clazz.getDeclaredFields();
    for(Field field : f){
      if(field.isAnnotationPresent(Inject.class)){
        Class<?> gen = field.getType();
        Constructor<?> ctor = gen.getConstructor();
        if(ctor.getModifiers() != Modifier.PRIVATE){
          field.set(a, ctor.newInstance());
        }
      }
    }
    a.getAllAndPrint("After");
  }

  public void getAllAndPrint(String when) throws IllegalAccessException{
    System.out.printf("--\n");
    Field[] fields = this.getClass().getDeclaredFields();
    for(Field field : fields){
      System.out.printf("%s\t%s == %s\n",when,field.toGenericString(),field.get(this));
    }
  }
}
