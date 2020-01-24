package com.example.demo.util;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class CustomInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (types != null) {
			for (int i = 0; i < types.length; i++) {
				if (types[i].isCollectionType()) {
					String propertyName = propertyNames[i];
					propertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
					try {
						Method method = entity.getClass().getMethod("get" + propertyName);
						@SuppressWarnings("unchecked")
						List<Object> objectList = (List<Object>) method.invoke(entity);

						if (objectList != null) {
							for (Object object : objectList) {
								String entityName = entity.getClass().getSimpleName();
								Method eachMethod = object.getClass().getMethod("set" + entityName, entity.getClass());
								eachMethod.invoke(object, entity);
							}
						}

					} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		return true;
	}
}