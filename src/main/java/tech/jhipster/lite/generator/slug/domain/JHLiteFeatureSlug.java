package tech.jhipster.lite.generator.slug.domain;

import tech.jhipster.lite.module.domain.resource.JHipsterFeatureSlugFactory;

public enum JHLiteFeatureSlug implements JHipsterFeatureSlugFactory {
  ANGULAR_AUTHENTICATION("angular-authentication"),
  AUTHENTICATION("authentication"),
  AUTHENTICATION_SPRINGDOC("authentication-springdoc"),
  BANNER("banner"),
  JCACHE("jcache"),
  CLIENT_CORE("client-core"),
  CUCUMBER_AUTHENTICATION("cucumber-authentication"),
  DATABASE_MIGRATION("database-migration"),
  DUMMY_PERSISTENCE("dummy-persistence"),
  DUMMY_SCHEMA("dummy-schema"),
  FRONT_BROWSER_TEST("front-browser-test"),
  JAVA_BUILD_TOOL("java-build-tool"),
  JAVA_BUILD_TOOL_WRAPPER("java-build-tool-wrapper"),
  JPA_PERSISTENCE("jpa-persistence"),
  LICENSE("license"),
  OAUTH_PROVIDER("oauth-provider"),
  OAUTH_PROVIDER_SPRINGDOC("oauth-provider-springdoc"),
  SERVICE_DISCOVERY("service-discovery"),
  SPRING_BOOT_CUCUMBER("spring-boot-cucumber"),
  SPRING_SERVER("spring-server"),
  SPRING_MVC_SERVER("spring-mvc-server"),
  SPRINGDOC("springdoc");

  private final String slug;

  JHLiteFeatureSlug(String slug) {
    this.slug = slug;
  }

  @Override
  public String get() {
    return slug;
  }
}
