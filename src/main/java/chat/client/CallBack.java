package chat.client;
@FunctionalInterface
public interface CallBack {

    void  onReceive( String message);
}
