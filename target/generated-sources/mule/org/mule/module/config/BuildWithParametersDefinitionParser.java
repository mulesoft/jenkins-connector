
package org.mule.module.config;

import javax.annotation.Generated;
import org.mule.module.config.AbstractDefinitionParser.ParseDelegate;
import org.mule.module.processors.BuildWithParametersMessageProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public class BuildWithParametersDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(BuildWithParametersMessageProcessor.class.getName());
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        parseProperty(builder, element, "jobName", "jobName");
        parseMapAndSetProperty(element, builder, "params", "params", "param", new ParseDelegate<String>() {


            public String parse(Element element) {
                return element.getTextContent();
            }

        }
        );
        parseProperty(builder, element, "connectionName", "connectionName");
        parseProperty(builder, element, "jenkinsUrl", "jenkinsUrl");
        parseProperty(builder, element, "username", "username");
        parseProperty(builder, element, "password", "password");
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        attachProcessorDefinition(parserContext, definition);
        return definition;
    }

}
