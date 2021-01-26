package letsun.learn;

/**
 * 单例
 */
public class Single {

}
class Singleton{
    private static Singleton istance;
    private Singleton() {}

    public static Singleton getIstance() {
        if(istance==null) {
            istance=new Singleton();
        }
        return istance;
    }
}
class SingletonB{
    private static SingletonB instance=new SingletonB();
    private SingletonB() {}
    public static SingletonB getIstance() {
        return instance;
    }

}
class SingletonC{
    private volatile static SingletonC singleton;
    private SingletonC() {}
    public static SingletonC getSingleton() {
        if(singleton==null) {
            synchronized (Singleton.class){
                if(singleton==null) {
                    singleton=new SingletonC();
                }
            }
        }
        return singleton;
    }
}
