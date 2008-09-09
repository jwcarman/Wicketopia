package org.wicketopia.service.impl;

import org.wicketopia.service.EditorTypeMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * @since 1.0
 */
public class EditorTypeMappingImpl implements EditorTypeMapping
{
   private Map<Class, String> editorTypeMap = new HashMap<Class, String>();

   private Queue<Class> createTypeQueue(final Class originalType)
   {
       Queue<Class> queue = new LinkedList<Class>();
       Class currentType = originalType;
       do
       {
           queue.add(currentType);
           currentType = currentType.getSuperclass();
       }
       while (currentType != null);
       queue.addAll(Arrays.asList(originalType.getInterfaces()));
       return queue;
   }

   public Map<Class, String> getEditorTypeMap()
   {
       return editorTypeMap;
   }

   public void setEditorTypeMap(Map<Class, String> editorTypeMap)
   {
       this.editorTypeMap = editorTypeMap;
   }

   public String getEditorType(Class propertyType)
   {
       final Queue<Class> typeQueue = createTypeQueue(propertyType);
       while (!typeQueue.isEmpty())
       {
           Class type = typeQueue.remove();
           final String editorType = editorTypeMap.get(type);
           if (editorType != null)
           {
               return editorType;
           }
       }
       return null;
   }
}