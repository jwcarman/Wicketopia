package org.wicketopia.editor.facet.validator;

import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.wicketopia.editor.EditorContext;
import org.wicketopia.editor.facet.AbstractValidatorFacet;

public class PatternFacet extends AbstractValidatorFacet
{
    private final String pattern;

    public PatternFacet(String pattern)
    {
        this.pattern = pattern;
    }

    @Override
    protected IValidator createValidator(EditorContext context)
    {
        return new PatternValidator(pattern);
    }
}
