package com.sba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

import javax.persistence.EntityManager;

/**
 * parameters management
 * 
 *
 */
@Component
public class UtilService {

	@Autowired
    private EntityManager entityManager;

    /**
     * convert parameters
     * 
     * @param params the parameters from the request
     * @param targetClass the target class
     * @param targetIdClass the class of the primary key (null if not an entity)
     * @param requestKey the key to search
     * @param targetKey the attribute name of the entity 
     * @param errorKey the error code to return if the entity is not found
     * @return a map containing all parameters found in the incoming list, with converted values to the requested target class
     * @throws Exception
     */
    public <T, I> Map<String, Object> convertStringParameter(final Map<String, Object> params, Class<T> targetClass, Class<I> targetIdClass,
            String requestKey, String targetKey, String errorKey) throws Exception {
        Map<String, Object> result = new HashMap<>();

        // Parse all parameters received by the API
        for (Entry<String, Object> param : params.entrySet()) {
            String key = param.getKey();
            Object value = param.getValue();
            String paramName = "_sortColumn";
            // sortField management
            if (params.get(paramName) != null && params.get(paramName).equals(requestKey)) {
                // Add the converted value in the result map with the incoming type
                result.put(paramName, targetKey);
            }

            // Search our key starting with the requested param: code, code_gt,           
            if (key.equals(requestKey) || key.startsWith(requestKey + "_")) {

                // Target value in the right class
                Object convertedValue = null;

                // Suffix added to the parameter: gt, lt, nl, ...
                String suffix = key.indexOf('_') >= 0 ? key.split("_")[1] : null;

                convertedValue = convertValue(targetIdClass, value, errorKey, targetClass);

                // Add the converted value in the result map with the incoming type
                result.put(targetKey + (suffix != null ? "_" + suffix : ""), convertedValue);
            }
        }

        return result;
    }

    private<I, T> Object convertValue(Class<I> targetIdClass, Object value, String errorKey, Class<T> targetClass) throws Exception {
        // Case the target class is an entity (in this case the id of the primary key has been given)
        Object convertedValue;
        if (targetIdClass != null) {

            Object primaryKey = convertIdToPrimaryKey(targetIdClass, value);
            // Convert the id to the primary key (Long, Integer, String, ...)


            // Retrieve the entity in database
            try {
            	 convertedValue = entityManager.find(targetClass, primaryKey);
            } catch (IllegalArgumentException e) {
            	throw new Exception("Entity not found "+primaryKey);
            }
            if (convertedValue == null) {
                throw new Exception(errorKey+" "+primaryKey);
            }
        }

        // The parameter is not an entity.
        // Convert the value to the target class (Long, Integer, String, ...)
        else {
            convertedValue = convertValueToTargetClass(targetClass, value);
        }
        return convertedValue;
    }

    private<T> Object convertValueToTargetClass(Class<T> targetClass, Object value) throws Exception {
        if (targetClass.equals(String.class)) {
            return value.toString();
        } else if (targetClass.equals(Long.class)) {
            return Long.parseLong(value.toString());
        } else if (targetClass.equals(Integer.class)) {
            return Integer.parseInt(value.toString());
        } else if (targetClass.equals(BigDecimal.class)) {
            return new BigDecimal(value.toString());
        } else if (targetClass.equals(Date.class)) {
            Long millis = Long.parseLong((String) value);
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(millis);
            return cal.getTime();
        } else if (targetClass.equals(Boolean.class)) {
            return Boolean.parseBoolean(value.toString());
        } else if (targetClass.equals(Double.class)) {
            return Double.parseDouble(value.toString());
        }
        else {
            throw new Exception("error.utils.unknownClass");
        }
    }

    private <I> Object convertIdToPrimaryKey(Class<I> targetIdClass, Object value) throws Exception {
        Object primaryKey;
        if (targetIdClass.equals(String.class)) {
            primaryKey = value.toString();
        } else if (targetIdClass.equals(Long.class)) {
            primaryKey = Long.parseLong(value.toString());
        } else if (targetIdClass.equals(Integer.class)) {
            primaryKey = Integer.parseInt(value.toString());
        } else if (targetIdClass.equals(BigDecimal.class)) {
            primaryKey = new BigDecimal(value.toString());
        } else if (targetIdClass.equals(Boolean.class)) {
            primaryKey = Boolean.parseBoolean(value.toString());
        } else if (targetIdClass.equals(Date.class)) {
            Long millis = Long.parseLong((String) value);
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(millis);
            primaryKey = cal.getTime();
        } else if (targetIdClass.equals(Double.class)) {
            primaryKey = Double.parseDouble(value.toString());
        } else {
            throw new Exception("error.utils.unknownPrimaryKeyClass"+ targetIdClass);
        }
        return primaryKey;
    }

    /**
     * Extract all pagination and filter parameters
     * @param params
     * @return
     */
    public static Map<String, Object> getFilterAndPaginationParams(final Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();

        String sortOrderParam = "_sortOrder";
        String getAllParam = "_getAll";
        String perPageParam = "_perPage";
        String pageParam = "_page";

        // Sort direction
        if (params.get(sortOrderParam) != null) {
            result.put(sortOrderParam, params.get(sortOrderParam));
        }

        // No filter
        if (params.get(getAllParam) != null) {
            result.put(getAllParam, params.get(getAllParam));
        }

        // Elements per page
        if (params.get(perPageParam) != null) {
            result.put(perPageParam, params.get(perPageParam));
        }

        // Page number
        if (params.get(pageParam) != null) {
            result.put(pageParam, params.get(pageParam));
        }

        return result;
    }

}
