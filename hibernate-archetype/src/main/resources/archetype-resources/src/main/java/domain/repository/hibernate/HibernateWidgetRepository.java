package ${package}.domain.repository.hibernate;

import ${package}.domain.entity.Widget;
import ${package}.domain.repository.WidgetRepository;
import org.domdrides.hibernate.repository.HibernateRepository;

public class HibernateWidgetRepository extends HibernateRepository<Widget,String> implements WidgetRepository
{
    public HibernateWidgetRepository()
    {
        super(Widget.class);
    }
}
