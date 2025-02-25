package tech.jhipster.lite.module.infrastructure.secondary;

import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.module;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.upgrade;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.jhipster.lite.Logs;
import tech.jhipster.lite.LogsSpy;
import tech.jhipster.lite.LogsSpyExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;

@UnitTest
@ExtendWith(LogsSpyExtension.class)
class FileSystemJHipsterModulesRepositoryTest {

  @Logs
  private LogsSpy logs;

  @Test
  void shouldApplyMavenModule() {
    JHipsterModule module = module();

    // @formatter:off
    assertThatModuleWithFiles(
      module,
      file("src/test/resources/projects/maven/pom.xml", "pom.xml"),
      packageJsonFile(),
      file("src/test/resources/projects/files/dummy.txt", "dummy.txt")
    )
      .hasFiles(
        "src/main/java/com/company/myapp/MyApp.java",
        "src/main/java/com/company/myapp/errors/Assert.java",
        "src/main/java/com/company/myapp/errors/AssertionException.java",
        "documentation/cucumber-integration.md",
        ".gitignore"
      )
      .hasExecutableFiles(".husky/pre-commit")
      .hasFile("src/main/java/com/company/myapp/MyApp.java")
      .containing("com.test.myapp")
      .and()
      .hasFile("pom.xml")
      .containing("<dummy-dependency.version>4.5.8</dummy-dependency.version>")
      .notContaining(
        """
              <dependency>
                <groupId>net.logstash.logback</groupId>
                <artifactId>logstash-logback-encoder</artifactId>
              </dependency>
          """
      )
      .notContaining(
        """
                <dependency>
                  <groupId>org.springdoc</groupId>
                  <artifactId>springdoc-openapi-ui</artifactId>
                  <version>${springdoc-openapi.version}</version>
                </dependency>
          """
      )
      .containing(
        """
                <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-dependencies</artifactId>
                  <version>${spring-boot.version}</version>
                  <scope>import</scope>
                </dependency>
          """
      )
      .containing(
        """
              <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <exclusions>
                  <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                  </exclusion>
                </exclusions>
              </dependency>
          """
      )
      .containing(
        """
                <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-dependencies</artifactId>
                  <version>${spring-boot.version}</version>
                  <type>pom</type>
                  <scope>import</scope>
                </dependency>
          """
      )
      .containing(
        """
              <dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter-engine</artifactId>
                <version>${spring-boot.version}</version>
                <classifier>test</classifier>
                <scope>test</scope>
                <optional>true</optional>
              </dependency>
          """
      )
      .containing("<spring-boot.version>")
      .containing("</spring-boot.version>")
      .containing("<artifactId>spring-boot-starter</artifactId>")
      .notContaining(
        """
              <dependency>
                <groupId>org.assertj</groupId>
                <artifactId>assertj-core</artifactId>
                <version>${assertj.version}</version>
                <scope>test</scope>
              </dependency>
          """
      )
      .containing("<maven-enforcer-plugin.version>")
      .containing("<json-web-token.version>")
      .containing(
        """
              <build>
                <pluginManagement>
                  <plugins>
                    <plugin>
                      <groupId>org.springframework.boot</groupId>
                      <artifactId>spring-boot-maven-plugin</artifactId>
                      <version>${spring-boot.version}</version>
                      <executions>
                        <execution>
                          <goals>
                            <goal>repackage</goal>
                          </goals>
                        </execution>
                      </executions>
                      <configuration>
                        <mainClass>${start-class}</mainClass>
                      </configuration>
                    </plugin>
                    <plugin>
                      <artifactId>maven-enforcer-plugin</artifactId>
                      <version>${maven-enforcer-plugin.version}</version>
                      <executions>
                        <execution>
                          <id>enforce-versions</id>
                          <goals>
                            <goal>enforce</goal>
                          </goals>
                        </execution>
                        <execution>
                          <id>enforce-dependencyConvergence</id>
                          <goals>
                            <goal>enforce</goal>
                          </goals>
                          <configuration>
                            <rules>
                              <DependencyConvergence />
                            </rules>
                            <fail>false</fail>
                          </configuration>
                        </execution>
                      </executions>
                      <dependencies>
                        <dependency>
                          <groupId>io.jsonwebtoken</groupId>
                          <artifactId>jjwt-jackson</artifactId>
                          <version>${json-web-token.version}</version>
                          <exclusions>
                            <exclusion>
                              <groupId>com.fasterxml.jackson.core</groupId>
                              <artifactId>jackson-databind</artifactId>
                            </exclusion>
                          </exclusions>
                        </dependency>
                      </dependencies>
                      <configuration>
                        <rules>
                          <requireMavenVersion>
                            <message>You are running an older version of Maven. JHipster requires at least Maven ${maven.version}</message>
                            <version>[${maven.version},)</version>
                          </requireMavenVersion>
                          <requireJavaVersion>
                            <message>You are running an incompatible version of Java. JHipster engine supports JDK 21+.</message>
                            <version>[21,22)</version>
                          </requireJavaVersion>
                        </rules>
                      </configuration>
                    </plugin>
                  </plugins>
                </pluginManagement>
                <plugins>
                  <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                  </plugin>
                  <plugin>
                    <artifactId>maven-enforcer-plugin</artifactId>
                  </plugin>
                </plugins>
              </build>
            """
      )
      .containing(
        """
              <profiles>
                <profile>
                  <id>local</id>
                  <activation />
                  <build>
                    <pluginManagement>
                      <plugins>
                        <plugin>
                          <artifactId>maven-enforcer-plugin</artifactId>
                          <version>${maven-enforcer-plugin.version}</version>
                          <executions>
                            <execution>
                              <id>enforce-versions</id>
                              <goals>
                                <goal>enforce</goal>
                              </goals>
                            </execution>
                            <execution>
                              <id>enforce-dependencyConvergence</id>
                              <goals>
                                <goal>enforce</goal>
                              </goals>
                              <configuration>
                                <rules>
                                  <DependencyConvergence />
                                </rules>
                                <fail>false</fail>
                              </configuration>
                            </execution>
                          </executions>
                          <dependencies>
                            <dependency>
                              <groupId>io.jsonwebtoken</groupId>
                              <artifactId>jjwt-jackson</artifactId>
                              <version>${json-web-token.version}</version>
                              <exclusions>
                                <exclusion>
                                  <groupId>com.fasterxml.jackson.core</groupId>
                                  <artifactId>jackson-databind</artifactId>
                                </exclusion>
                              </exclusions>
                            </dependency>
                          </dependencies>
                          <configuration>
                            <rules>
                              <requireMavenVersion>
                                <message>You are running an older version of Maven. JHipster requires at least Maven ${maven.version}</message>
                                <version>[${maven.version},)</version>
                              </requireMavenVersion>
                              <requireJavaVersion>
                                <message>You are running an incompatible version of Java. JHipster engine supports JDK 21+.</message>
                                <version>[21,22)</version>
                              </requireJavaVersion>
                            </rules>
                          </configuration>
                        </plugin>
                      </plugins>
                    </pluginManagement>
                    <plugins>
                      <plugin>
                        <artifactId>maven-enforcer-plugin</artifactId>
                      </plugin>
                    </plugins>
                  </build>
                  <properties>
                    <spring.profiles.active>local</spring.profiles.active>
                  </properties>
                </profile>
              </profiles>
            """
      )
      .and()
      .hasFile("package.json")
      .containing("\"scripts\": {\n    \"serve\": \"tikui-core serve\"")
      .containing("\"dependencies\": {\n    \"@angular/animations\": \"")
      .containing("\"devDependencies\": {\n    \"@playwright/test\": \"")
      .and()
      .hasFile("src/main/java/com/company/myapp/errors/Assert.java")
      .containing("Dummy replacement")
      .containing("Another dummy replacement")
      .containing("Dummy collection replacement")
      .containing("Another dummy collection replacement")
      .containing("// Dummy comment\n  public static class IntegerAsserter {")
      .notContaining("""
            import java.math.BigDecimal;
            import java.math.BigDecimal;
            """)
      .notContaining("""
            import java.util.Collection;
            import java.util.Collection;
            """)
      .and()
      .hasFile("src/main/resources/config/application.yml")
      .containing("""
        springdoc:
          swagger-ui:
            # This is a comment
            operationsSorter: alpha
        """)
      .and()
      .hasFile("src/main/resources/config/application-local.yml")
      .containing("""
        springdoc:
          swagger-ui:
            # This is a comment
            tryItOutEnabled: 'false'
        """)
      .and()
      .hasFile("src/test/resources/config/application-test.yml")
      .containing("""
        springdoc:
          swagger-ui:
            # This is a comment
            operationsSorter: test
        """)
      .and()
      .hasFile("src/test/resources/config/application-local.yml")
      .containing("""
        springdoc:
          # Swagger properties
          swagger-ui:
            operationsSorter: test
            tagsSorter: test
            # This is a comment
            tryItOutEnabled: test
        """)
      .and()
      .hasFile("src/test/resources/META-INF/spring.factories")
      .containing("o.s.c.ApplicationListener=c.m.m.MyListener1,c.m.m.MyListener2")
      .and()
      .hasFile("README.md")
      .containing(
        """
           - [Cucumber integration](documentation/cucumber-integration.md)
           - [Another cucumber integration](documentation/another-cucumber-integration.md)

           <!-- jhipster-needle-documentation -->
           """
      )
      .containing("This is a startup section")
      .and()
      .hasPrefixedFiles(".git", "config", "HEAD")
      .doNotHaveFiles("dummy.txt")
      .hasFiles("dummy.json");
    // @formatter:on

    assertPreActions();
    assertPostActions();
  }

  private void assertPreActions() {
    logs.shouldHave(Level.DEBUG, "Applying fixture module");
    logs.shouldHave(Level.DEBUG, "You shouldn't add this by default in your modules :D");
  }

  private void assertPostActions() {
    logs.shouldHave(Level.DEBUG, "Fixture module applied");
    logs.shouldHave(Level.DEBUG, "Applied on");
    logs.shouldHave(Level.DEBUG, System.getProperty("java.io.tmpdir"));
  }

  @Test
  void shouldApplyUpgrade() {
    assertThatModuleUpgrade(
      module(),
      upgrade(),
      file("src/test/resources/projects/maven/pom.xml", "pom.xml"),
      packageJsonFile(),
      file("src/test/resources/projects/files/dummy.txt", "dummy.txt")
    )
      .doNotUpdate(".gitignore")
      .update("src/main/java/com/company/myapp/MyApp.java")
      .doNotHaveFiles("documentation/cucumber-integration.md")
      .hasFile("src/main/java/com/company/myapp/errors/Assert.java")
      .containing("// This is an updated file");
  }
}
