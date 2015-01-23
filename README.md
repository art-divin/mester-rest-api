# mester-rest-api #

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
* Java JDK (I am using JDK 1.8u25 at the moment)
* Grails 2.4.4 or later*[2]
* Groovy 2.3.9 or later*[2]

[*1] : 

1. You need to create a user "admin" with password "password" (this can be changed in DataSource configuration)
2. You need to create a DB called "mester"

[*2] : it is possible that later versions of language / platform will have some methods used in the project deprecated. For easiest setup, use specified versions

### Contribution guidelines ###

* Currently, project is in read-only mode

### Who do I talk to? ###

* If you have any questions, send a Pull Request and I'll answer as soon as possible

### License ###

* [MIT License](http://opensource.org/licenses/MIT) is used

> Copyright (c) 2015 Ruslan Alikhamov

> Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

> The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
