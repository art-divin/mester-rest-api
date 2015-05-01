# mester-rest-api #

[![Join the chat at https://gitter.im/art-divin/mester-rest-api](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/art-divin/mester-rest-api?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Mester is a suite for Quality Assurance testing (QA).
Mester consists of 3 parts:

1. REST API
2. Front End
3. Mobile Clients

### Basic Capabilities of the REST API ###

* Projects are in a public domain (no registration required)
* Project can be added via POST request
* Project has test cases, which have test steps
* Test case can be added via POST request by project identifier

### Build & Run ###

In order to build & run project, you'll need:

* Eclipse (the fastest IDE to compile Grails, preferably basic distribution like Luna)
* Eclipse GGTS **plugin**
* MySQL DB (community edition is fine)*[1]
* JDK (I am using JDK 1.8u25 at the moment, but 1.7 would work as well)
* Grails 2.4.4 or later*[2]
* Groovy 2.3.9 or later*[2]

[*1] : 

* You need to create a user with a password (this can be changed in DataSource configuration)
* You need to create a DB and specify its name
* You need to create a configuration file and put it on your local drive outside of the project with following content as a template:
```groovy
environments {
	development {
		dataSource {
			url="jdbc:mysql://localhost:3306/PLACE_DB_NAME_HERE"
			username="PLACE_CREATED_USERNAME_HERE"
			password="PLACE_USER_PASSWORD_HERE"
		}
	}
}
```
* You need to replace line [#62](https://github.com/art-divin/mester-rest-api/blob/develop/grails-app/conf/Config.groovy#L62) in file [Config.groovy](/grails-app/conf/Config.groovy) with to your configuration file's location

[*2] : it is possible that later versions of language / platform will have some methods used in the project deprecated. For easiest setup, use specified versions

### Contribution guidelines ###

* Currently, project is in read-only mode

### Whom do I talk to? ###

* Check out Wiki pages for more info about the project:
 * [JSON Structure](https://github.com/art-divin/mester-rest-api/wiki/JSON-Structure) page describes JSON structure for all object entities
* If you have any further questions, open an issue and I'll answer as soon as possible

### License ###

* [MIT License](http://opensource.org/licenses/MIT) is used

> Copyright (c) 2015 Ruslan Alikhamov

> Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

> The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
