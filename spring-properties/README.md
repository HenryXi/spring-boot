# Read values from properties file in Spring
There are several ways read values from properties in Spring. You can choose one way in your application 
or change some code to fit your needs. In this tutorial I will show you different ways to read 
values from properties in Spring.

1. [using ``util`` tag in spring config file](#util-tag)
2. using ``PropertiesFactoryBean`` create ``Properties`` object
3. using ``PreferencesPlaceholderConfigurer`` and ``@Value`` inject value into bean
4. using ``@Configuration`` and ``@PropertySource`` inject ``Environment`` object into bean

using util tag in spring config file  {#util-tag}
=================================================

pom file


