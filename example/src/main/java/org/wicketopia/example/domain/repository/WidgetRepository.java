package org.wicketopia.example.domain.repository;

import org.domdrides.repository.PageableRepository;
import org.wicketopia.example.domain.entity.Widget;

public interface WidgetRepository extends PageableRepository<Widget, String>
{
}
