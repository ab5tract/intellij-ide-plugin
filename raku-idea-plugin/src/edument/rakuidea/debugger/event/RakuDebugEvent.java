package edument.rakuidea.debugger.event;

import com.intellij.xdebugger.XDebugSession;
import edument.rakuidea.debugger.RakuDebugThread;

public interface RakuDebugEvent extends Runnable {
    @Override
    void run();

    XDebugSession getDebugSession();

    void setDebugSession(XDebugSession debugSession);

    RakuDebugThread getDebugThread();

    void setDebugThread(RakuDebugThread thread);
}
