package processing;

import data.SerialEvent;

/**
 *
 * @author valentinstarlinger
 */
public interface SerialListener {
    public abstract void messageReceived(SerialEvent e);
    public abstract void error(String msg);
}
