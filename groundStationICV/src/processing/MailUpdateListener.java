package processing;

import data.MailEvent;

/**
 *
 * @author valentinstarlinger
 */
public interface MailUpdateListener {
    public abstract void mailUpdated(MailEvent e);
    public abstract void error(String msg);
}
