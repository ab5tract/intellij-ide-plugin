package edument.rakuidea.debugger.event;

import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.frame.XSuspendContext;
import edument.rakuidea.debugger.RakuDebugThread;
import edument.rakuidea.debugger.RakuSuspendContext;
import edument.rakuidea.debugger.RakuThreadDescriptor;

public class RakuDebugEventStop extends RakuDebugEventBase implements RakuDebugEvent {
    private final RakuThreadDescriptor[] threads;
    private final int activeThreadIndex;

    public RakuDebugEventStop(RakuThreadDescriptor[] threads, int activeThreadIndex, XDebugSession session, RakuDebugThread thread) {
        setDebugSession(session);
        setDebugThread(thread);
        this.threads = threads;
        this.activeThreadIndex = activeThreadIndex;
    }

    XSuspendContext getSuspendContext() {
        return new RakuSuspendContext(threads, activeThreadIndex, getDebugSession(), getDebugThread());
    }

    @Override
    public void run() {
        getDebugSession().positionReached(getSuspendContext());
    }
}
