### Generating the jar

- Install the 'apache.maven.plugins.jar.plugin'
- Inside pom.xml, add : 

    ```
    <build>
        <plugins>
            <plugin>
            <!-- Build an executable JAR -->
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>3.3.0</version>
            <configuration>
                <archive>
                <manifest>
                    <addClasspath>true</addClasspath>
                    <classpathPrefix>lib/</classpathPrefix>
                    <mainClass>org.example.Main</mainClass>
                </manifest>
            </archive>
            </configuration>
            </plugin>
        </plugins>
    </build>
    ```

  ```<mainClass>``` attribue is the most imporant one.
- Generate jar using the Maven menu -> package
- Using a latest java.exe executable, run the jar -> ./java.exe -jar <jar_file.jar>