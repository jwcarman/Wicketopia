package ${package}.domain.repository.jpa;

import ${package}.domain.entity.Widget;
import ${package}.domain.repository.WidgetRepository;
import org.domdrides.jpa.repository.JpaRepository;

public class JpaWidgetRepository extends JpaRepository<Widget,String> implements WidgetRepository
{
    public JpaWidgetRepository()
    {
        super(Widget.class);
    }
}
