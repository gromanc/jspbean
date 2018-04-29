package jspbean.websocket;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Map;

/**
 * Created by Roma on 22.09.2014.
 */
public class ServletAwareConfig extends ServerEndpointConfig.Configurator implements ApplicationContextAware {
  private static final Logger LOG = LoggerFactory.getLogger(ServletAwareConfig.class);

  private ApplicationContext appContext;
  protected AutowireCapableBeanFactory autoWiringFactory;
  protected int autowireStrategy = AutowireCapableBeanFactory.AUTOWIRE_BY_NAME;
  private boolean alwaysRespectAutowireStrategy = false;


  @Override
  public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
    HttpSession httpSession = (HttpSession) request.getHttpSession();
    setApplicationContext((ApplicationContext)httpSession.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
    config.getUserProperties().put("httpSession", httpSession);
  }

  public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
    Object bean;
    bean =  autoWiringFactory.createBean(endpointClass, autowireStrategy, false);
    return (T) bean;
  }

  @Override
  public void setApplicationContext(ApplicationContext appContext) throws BeansException {
    this.appContext = appContext;
    autoWiringFactory = findAutoWiringBeanFactory(this.appContext);
  }

  protected AutowireCapableBeanFactory findAutoWiringBeanFactory(ApplicationContext context) {
    if (context instanceof AutowireCapableBeanFactory) {
      // Check the context
      return (AutowireCapableBeanFactory) context;
    } else if (context instanceof ConfigurableApplicationContext) {
      // Try and grab the beanFactory
      return ((ConfigurableApplicationContext) context).getBeanFactory();
    } else if (context.getParent() != null) {
      // And if all else fails, try again with the parent context
      return findAutoWiringBeanFactory(context.getParent());
    }
    return null;
  }

  public Object buildBean(Class clazz, Map<String, Object> extraContext) throws Exception {
    Object bean;

    try {
      // Decide to follow autowire strategy or use the legacy approach which mixes injection strategies
      if (alwaysRespectAutowireStrategy) {
        // Leave the creation up to Spring
        bean = autoWiringFactory.createBean(clazz, autowireStrategy, false);
        injectApplicationContext(bean);
        return bean;
      } else {
        bean = autoWiringFactory.autowire(clazz, AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR, false);
        bean = autoWiringFactory.applyBeanPostProcessorsBeforeInitialization(bean, bean.getClass().getName());
        // We don't need to call the init-method since one won't be registered.
        bean = autoWiringFactory.applyBeanPostProcessorsAfterInitialization(bean, bean.getClass().getName());
        return autoWireBean(bean, autoWiringFactory);
      }
    } catch (UnsatisfiedDependencyException e) {
      if (LOG.isErrorEnabled())
        LOG.error("Error building bean", e);
      // Fall back
      return autoWireBean(clazz.newInstance(), autoWiringFactory);
    }
  }

  private void injectApplicationContext(Object bean) {
    if (bean instanceof ApplicationContextAware) {
      ((ApplicationContextAware) bean).setApplicationContext(appContext);
    }
  }

  public Object autoWireBean(Object bean, AutowireCapableBeanFactory autoWiringFactory) {
    if (autoWiringFactory != null) {
      autoWiringFactory.autowireBeanProperties(bean, autowireStrategy, false);
    }
    return bean;
  }

}
