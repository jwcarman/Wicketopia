package org.wicketopia.example.domain.repository.hibernate;

import org.domdrides.hibernate.repository.HibernateRepository;
import org.wicketopia.example.domain.entity.Widget;
import org.wicketopia.example.domain.repository.WidgetRepository;

public class HibernateWidgetRepository extends HibernateRepository<Widget, String> implements WidgetRepository
{
    public HibernateWidgetRepository()
    {
        super(Widget.class);
    }
}
