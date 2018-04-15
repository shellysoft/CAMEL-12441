# CAMEL-12441
An example for CAMEL-12441

This example contains a simple camel split route for xml.

There is a test for the route which exemplifies the blocking wait.

Notes:

com.ctc.wstx.stax.WstxInputFactory is used as  javax.xml.stream.XMLInputFactory

com.ctc.wstx.stax.WstxOutputFactory is used as javax.xml.stream.XMLOuputFactory

Dependencies:

    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-core</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-stax</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-spring</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-spring-javaconfig</artifactId>
    </dependency>
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
    </dependency>
    <dependency>
        <groupId>org.codehaus.woodstox</groupId>
        <artifactId>woodstox-core-asl</artifactId>
    </dependency>


    <!-- logging -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>jcl-over-slf4j</artifactId>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-core</artifactId>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
    </dependency>
    <dependency>
        <groupId>net.logstash.logback</groupId>
        <artifactId>logstash-logback-encoder</artifactId>
    </dependency>

    <!-- testing -->
    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-test-spring</artifactId>
        <scope>test</scope>
    </dependency>