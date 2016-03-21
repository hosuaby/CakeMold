# CakeMold

[![Build Status](https://travis-ci.org/hosuaby/CakeMold.svg?branch=master)](https://travis-ci.org/hosuaby/CakeMold)
[![Coverage Status](https://coveralls.io/repos/github/hosuaby/CakeMold/badge.svg?branch=master)](https://coveralls.io/github/hosuaby/CakeMold?branch=master)

There are already some `Java` libraries to make fluent builders for `POJO`s. But
they are mostly based on compile-time code generation and ask you to add
annotations on your domain classes or write interfaces. Sometimes you can not or
don't want to change your code (or generate it). Or you use third-party library
and don't have the source. You may want to start write compact and readable
code immediately, like in the case of tests.
This library is made specially to help you write readable tests without use of
`ObjectMother` pattern.

## Usecases
```java
/* Instantiate class respecting JavaBean convention */
Person person = CakeMold.of(Person.class)
    .set("firstName", "Bob")
    .set("lastName", "SquarePants")
    .set("email", "sponge.bob@bikinibottom.io")
    .set("age", 22)
    .cook();
```

## Maven
```xml
<repositories>
    <repository>
        <id>jcenter</id>
        <url>https://jcenter.bintray.com/</url>
    </repository>
</repositories>
...
<dependencies>
    ...
    <dependency>
        <groupId>io.hosuaby</groupId>
        <artifactId>cakemold</artifactId>
        <version>0.0.1</version>
        <scope>test</scope>
    </dependency>
    ...
</dependencies>
```

## License
Library distributed under MIT license.