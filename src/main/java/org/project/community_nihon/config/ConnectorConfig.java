package org.project.community_nihon.config;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Valve;
import org.apache.catalina.valves.RemoteIpValve;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectorConfig {


}
