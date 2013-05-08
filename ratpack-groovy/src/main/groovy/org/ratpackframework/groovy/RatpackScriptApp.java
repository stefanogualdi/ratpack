package org.ratpackframework.groovy;

import org.ratpackframework.bootstrap.RatpackServer;
import org.ratpackframework.bootstrap.RatpackServerBuilder;
import org.ratpackframework.groovy.bootstrap.GroovyKitAppFactory;
import org.ratpackframework.groovy.internal.ScriptBackedApp;
import org.ratpackframework.routing.Handler;

import java.io.File;

public abstract class RatpackScriptApp {

  public static RatpackServer ratpack(File script) {
    String portString = System.getProperty("ratpack.port", new Integer(RatpackServerBuilder.DEFAULT_PORT).toString());
    int port = Integer.valueOf(portString);

    String host = System.getProperty("ratpack.host", null);
    boolean reloadable = Boolean.parseBoolean(System.getProperty("ratpack.reloadable", "false"));
    boolean compileStatic = Boolean.parseBoolean(System.getProperty("ratpack.compileStatic", "false"));

    return ratpack(script, script.getAbsoluteFile().getParentFile(), port, host, compileStatic, reloadable);
  }

  public static RatpackServer ratpack(File script, File baseDir, int port, String host, boolean compileStatic, boolean reloadable) {
    Handler scriptBackedApp = new ScriptBackedApp(script, new GroovyKitAppFactory(baseDir), compileStatic, reloadable);

    RatpackServerBuilder builder = new RatpackServerBuilder(scriptBackedApp);
    builder.setPort(port);
    builder.setHost(host);

    return builder.build();
  }

}