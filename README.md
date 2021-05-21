Application to match clients needs and candidates (recruitment business management) <br />
Microservices architecture

# Installation software

Download docker (latest stable version in https://www.docker.com/)

Download postgresql 12.2 

Download node.js 12.16.1 https://nodejs.org/en/
Add path to node in PATH
Install java
Update environment variables (PATH and JAVA_HOME)
JAVA_HOME : add path to jdk-11.0.6

PATH : 
Path_to\jdk-11.0.6\bin <br />
Path_to\jdk-11.0.6 <br />
Path_to\spring-2.2.4.RELEASE\bin <br />

Install eclipse or any java editor <br />
Check jre : be careful to have jdk-11.0.6 as jre (and not the pre-installed former jre-8.xxx <br />
Install Tools4 from marketplace <br />
Install wild web developer from marketplace <br />
Install git <br />


# Client installation

In git : 
cd project_path
Install ui : 
./mvnw generate-resources

Then :

give execution rights to npm

then :

./npm install @angular/cli

Then :

give execution rights to ng

npm install

then :


ng serve

if everything ok : 

ctrl c

then :

./npm install bootstrap jquery –save


Add nebular :

ng add @nebular/theme <br />
ng add @nebular/auth <br />

ng add @nebular/security <br />
ng add @asymmetrik/ngx-leaflet <br />
ng add @angular/google-maps <br />
npm install --save ngx-echarts <br />
npm install --save leaflet <br />
npm install --save @swimlane/ngx-charts<br />
npm install --save angular2-chartjs <br />
npm install --save echarts <br />

npm install @angular/cdk <br />

npm install --save angular2-chartjs  <br />
npm install --save ng2-ckeditor <br />
npm install --save ng2-smart-table <br />
npm install --save @akveo/ng2-completer <br />

npm install --save @angular/cdk @angular/animations <br />
npm install --save nebular-icons <br />
npm install crypto-random-string <br />

start ng serve : <br />
ng serve --proxy-config proxy.conf.json<br />

Then : 

Run project as spring boot app to check if server ok

