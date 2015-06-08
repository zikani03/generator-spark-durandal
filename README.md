Spark Durandal generator
=

Yeoman generator that creates an application scaffold for a web app using Java and the [Spark Framework](http://sparkjava.com) with a client-side browser app using DurandalJS 2.x and the latest KnockoutJS library

For creating web apps and RESTful APIs

## Installation

Install [Git](http://git-scm.com), [node.js](http://nodejs.org), [JDK 8](https://www.java.com), and [Maven 3](http://maven.apache.org/).

Install Yeoman:

    npm install -g yo

Install the Spark-Durandal generator:

    npm install -g generator-spark-durandal


## Creating a Spark service

In a new directory, generate the service:

    yo spark-durandal

Compile the service:

    mvn compile

Run the service:

    mvn exec:exec

Your service will run at [http://localhost:4567](http://localhost:4567).

## Creating a persistent entity

Generate the entity:

    yo spark-durandal:entity [myentity]

You will be asked to specify attributes for the entity, where each attribute has the following:

- a name
- a type (String, Integer, Long, Float, Double, Boolean, Date, Enum)
- for a String attribute, an optional minimum and maximum length
- for a numeric attribute, an optional minimum and maximum value
- for a Date attribute, an optional constraint to either past values or future values
- for an Enum attribute, a list of enumerated values
- whether the attribute is required

Compile and rerun the service:

    mvn compile exec:exec
    
A client-side AngularJS application will now be available by running

	grunt server
	
The Grunt server will run at [http://localhost:9000](http://localhost:9000).  It will proxy REST requests to the Spark service running at [http://localhost:4567](http://localhost:4567).

At this point you should be able to navigate to a page to manage your persistent entities.  

The Grunt server supports hot reloading of client-side HTML/CSS/Javascript file changes, while the Spark service supports hot reloading of Java class file changes.


## Features


### Entities from JSON Schema

* Generate api and front-end using JSON schema

`yo spark-durandal:entities --schema /path/to/schema.json`

```json
    // myapp/schema.json
    {
        "Note" : {
            "properties" : {
                "title" : "String",
                "content" : "String",
                "created_at":"Date",
                "created_by" : "User"
            }
        }
    }
```

Will result in the generator creating a resource available at http://localhost:4567/notes
and an entity with the specified properties as attributes.

## Resource subgenerator

Creates an API Resource that can be accessed via http://localhost:4567/resource name
Also creates the basic model

e.g.

`yo spark-durandal:resource Note`

will create a resource at http://localhost:4567/notes


### Controller subgenerator 

Works like ApiResource but is mostly for an MVC controller that renders an html view instead of responding with JSON/XML data

`yo spark-durandal:controller Posts`

## Contributing

Contributions welcome! Just **fork** this repo and send a Pull Request

## Inspiration / Credits

Shamelessly copied stuff from [Rayokota's Angular Spark generator](https://github.com/rayokota/generator-angular-spark)   
Thanks!


=

MIT 2015, zikani