/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Borrowed from the spring framework.
 *
 * @since 1.0
 */
public abstract class Pluralizer
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final Logger LOGGER = LoggerFactory.getLogger(Pluralizer.class);

    private static List<PluralizationRule> pluralizationRules = Collections.synchronizedList(new ArrayList<PluralizationRule>());

    private static Map<String, String> pluralizationCache = Collections.synchronizedMap(new HashMap<String, String>());

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    static
    {
        // register default rules
        // y -> ies
        registerPluralizationRule(new RegexPluralizationRule("([^aeiouy])y$", "ies", 1));

        // sxz -> [sxz]es
        registerPluralizationRule(new RegexPluralizationRule("([sxz])$", "es", 1));

        // hard h -> hes
        registerPluralizationRule(new RegexPluralizationRule("([^aeioudgkprt]h$)", "es", 1));
    }

    /**
     * Applies default English pluralization rules adding &quot;s&quot; to the end of the term.
     */
    private static String applyDefaultRule(String term)
    {
        return term + "s";
    }

    /**
     * Attempts to locate and return a {@link PluralizationRule} for the specified term using the
     * set of configured {@link PluralizationRule PluralizationRules}. Returns <code>null</code>
     * if no rule can be found.
     */
    private static PluralizationRule lookupPluralizationRule(String term)
    {
        for (PluralizationRule rule : pluralizationRules)
        {
            if (rule.appliesTo(term))
            {
                return rule;
            }
        }

        return null;
    }

    public static String pluralize(String term)
    {
        String pluralForm = pluralizationCache.get(term);

        if (pluralForm == null)
        {
            PluralizationRule rule = lookupPluralizationRule(term);

            if (rule != null)
            {
                pluralForm = rule.apply(term);
            }
            else
            {
                pluralForm = applyDefaultRule(term);

                if (LOGGER.isDebugEnabled())
                {
                    LOGGER.debug("Located pluralization [" + pluralForm + "] for term [" + term + "] using default rules.");
                }
            }
        }
        else
        {
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug("Located pluralization [" + pluralForm + "] for term [" + term + "] in the cache.");
            }
        }

        return pluralForm;
    }

    public static void registerPluralizationRule(PluralizationRule rule)
    {
        pluralizationRules.add(rule);
    }

    public static String splitIntoWords(String name)
    {
        String[] words = StringUtils.splitByCharacterTypeCamelCase(name);
        words[0] = StringUtils.capitalize(words[0]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++)
        {
            String word = words[i];
            sb.append(word);
            if (i != words.length - 1)
            {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    interface PluralizationRule
    {
        boolean appliesTo(String term);

        String apply(String term);
    }

    private static final class RegexPluralizationRule implements PluralizationRule
    {
        private Pattern pattern;

        private String replacement;

        private int includeGroup = -1;

        public RegexPluralizationRule(String pattern, String replacement)
        {
            this(pattern, replacement, -1);
        }

        public RegexPluralizationRule(String pattern, String replacement, int includeGroup)
        {
            this.pattern = Pattern.compile(pattern);
            this.replacement = replacement;
            this.includeGroup = includeGroup;
        }

        public boolean appliesTo(String term)
        {
            return pattern.matcher(term).find();
        }

        public String apply(String term)
        {
            Matcher m = pattern.matcher(term);
            if (m.find())
            {
                String replace = (this.includeGroup > -1) ? m.group(this.includeGroup) : "";
                replace += this.replacement;
                return m.replaceFirst(replace);
            }
            else
            {
                return term;
            }
        }
    }
}
