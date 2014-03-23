public class Gen<S>{
  public Gen(){
    super();
  }

  S x=null;
  public S getX(){
    return x;
  }
  public void setX(S x){
    this.x=x;
  }

  // @Override
  public String toString_(){
    return "["+x+"]";
  }
}
