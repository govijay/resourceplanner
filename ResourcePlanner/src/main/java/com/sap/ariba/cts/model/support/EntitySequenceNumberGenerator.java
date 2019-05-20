package com.sap.ariba.cts.model.support;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.ariba.cts.model.base.BaseEntity;

import java.io.Serializable;
import java.util.Properties;

/**
 * Override Hibernate Sequence Style Generator.
 *
 * @author Vijay.G
 * @version $Id : $
 */
public class EntitySequenceNumberGenerator extends SequenceStyleGenerator {

  // Logging
  private static Logger logger = LoggerFactory.getLogger(EntitySequenceNumberGenerator.class);

  private String valuePrefix;

  /**
   * {@inheritDoc}
   * <p/>
   * Configuration of the Sequence Mapping.
   */
  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
    super.configure(LongType.INSTANCE, params, serviceRegistry);
  }

  /**
   * {@inheritDoc}
   * <p/>
   * Configuration of the generate Identifier.
   */
  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
    valuePrefix = getObjectClassCode(obj);
    logger.info(valuePrefix);
    return valuePrefix + "_" + super.generate(session, obj);
  }

  /**
   * Helper method to get the object's class code.
   *
   * @param obj a {@link Object} the Base Entity Object.
   * @return String a {@link String} the object code.
   */

  private String getObjectClassCode(Object obj) {

    String objCode;

    if (!(obj instanceof BaseEntity)) {
      logger.error("Illegal Object Class:[" + obj.getClass().getName()
              + "] using Element Sequence Generator!");
      throw new IllegalArgumentException(
              "The Specified Object is not derived from RootElement!");
    }
    objCode = ((BaseEntity) obj).getClassCode();

    logger.info("Class Code", objCode);
    return objCode;
  }


}
