# Portal Grails Starter App

A base on which to build your next [Portal Grails](https://github.com/bazaarvoice/portal-grails) application

# What it provides
**Portal Grails Starter App** is the `grails create-app` of [Portal Grails](https://github.com/bazaarvoice/portal-grails) applications.  It provides a starter app which is fully integrated with the [Portal Grails](https://github.com/bazaarvoice/portal-grails) plugin

# How to use it
Follow the [Portal Grails developer setup](https://github.com/bazaarvoice/portal-grails/blob/master/doc/dev_environment_setup.md) to get up and running.

Create a new github project for your application , then modify your clone of this project to push there

```bash
$ git remote rm origin # remove portal-grails-starter as your origin
$ git remote add origin <your new project git url> # register your new project as origin
$ git push -u origin master # push the starter app your project, marking it as "upstream"
```

* **Do not** Github fork this project.  You neither need nor want you new project linked back to this starter

Once you've built and launched the base app, you'll want to search your project for `PORTAL-GRAILS TODO` to see specific places where you should be modifying the provided code.

You'll probably also want to have a look at the Portal Grails [readme](https://github.com/bazaarvoice/portal-grails), and specifically the [installation instructions](https://github.com/bazaarvoice/portal-grails/blob/master/doc/installation.md) to see what you're getting (since the `portal-grails-starter` code you're developing your project on was created by running `grails create-app`, and then doing the Portal Grails `portal-app-plugin` install.

Having a look at the [commits which created this starter app](https://github.com/bazaarvoice/portal-grails-starter/commits/master) is also a good way to get a jumpstart on understanding the provided code.

# FAQ
**Isn't forking a starter repo bad practice?  How will I get updates to the base code?**
* the base code here is simply an integration with the [Portal Grails Plugin](https://github.com/bazaarvoice/portal-grails), which is where the shared code actually lives and can be versioned and updated like any dependency

**I'm getting class not found errors.  What's going on?**
* IDEA has a bit of trouble with transitive dependencies from inline plugins.  Try to build from the command line:

    ```bash
    $ grails clean
    $ grails compile
    $ grails run-app
    ```
    You can also try recreating your IDEA project

