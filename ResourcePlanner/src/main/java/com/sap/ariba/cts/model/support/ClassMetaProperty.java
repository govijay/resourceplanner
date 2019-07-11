package com.sap.ariba.cts.model.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassMetaProperty to notate the entity object class code.
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClassMetaProperty {

    /**
     * Class code to identify or categorize the primary root id of entity objects.
     *
     * @return String Shorthand notation of the entity object.
     */
    String code() default "UNDEFINED";
}
