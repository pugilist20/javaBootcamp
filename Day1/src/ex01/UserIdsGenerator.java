package ex01;

public class UserIdsGenerator {
    private static UserIdsGenerator INSTANCE;
    private static Integer lastIdentifier=1;
    private UserIdsGenerator(){
    }
    public static UserIdsGenerator getInstance(){
        if(INSTANCE==null){
            INSTANCE=new UserIdsGenerator();
        }
        return INSTANCE;
    }
    public static Integer generateID(){
        return lastIdentifier++;
    }
}
