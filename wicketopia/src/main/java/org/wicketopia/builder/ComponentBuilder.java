package org.wicketopia.builder;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.IBehavior;

/**
 * @author James Carman
 */
public interface ComponentBuilder
{
    public void addBehavior(IBehavior behavior);
    public void setViewable(boolean viewable);
    public Component build();
}
