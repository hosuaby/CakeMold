# CakeMold

[![Build Status](https://travis-ci.org/hosuaby/CakeMold.svg?branch=master)](https://travis-ci.org/hosuaby/CakeMold)
[![Coverage Status](https://coveralls.io/repos/github/hosuaby/CakeMold/badge.svg?branch=master)](https://coveralls.io/github/hosuaby/CakeMold?branch=master)

Easy Java fluent POJO builder.

## Overview
Builders makes creation of data objects more fluent and pleasant. Use of build
pattern in tests help to improve readability of test cases.

## Usecases
```java
/* Build POJO respecting JavaBean convention */
Person person = CakeMold.of(Person.class)
    .set("firstName", "Bob")
    .set("lastName", "SquarePants")
    .set("email", "sponge.bob@bikinibottom.io")
    .set("age", 22)
    .set("address", CakeMold.of(Address.class)
        .set("address1", "ananas house")
        .set("city", "Bikini Bottom")
        .set("zipcode", 10101)
        .cook())
    .cook();
```

## Why it's awesome
* Written in Java 7
* No need to add annotations in your code
* No need ennoying compile-time code generation

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
        <version>0.0.2</version>
        <scope>test</scope>
    </dependency>
    ...
</dependencies>
```

## License
Library distributed under MIT license.
