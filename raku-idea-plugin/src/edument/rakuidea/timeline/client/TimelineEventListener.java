package edument.rakuidea.timeline.client;

public interface TimelineEventListener {
    void onEvent(ClientEvent e);
    void onError(Throwable e);
}
