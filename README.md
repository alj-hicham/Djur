Djur

this application is running by a pure java code and sql as database , jdbc and maven for
dependencies

things done :
Login or create account
or exit
if administrator :
1 - starting the question to list animal based on choice
2 - create account
3 -list users
4 - delete account
5 – add an animal
6 -list all animal
7 – delete choice
8 – exit the application

if user :
1 – starting the question to list animal based on choice
2 – exit the application

question that the user will be asked :

1 gillar du fluffiga djur?
2 föredrar du smarta eller mysiga djur
3 är det en nackel om det finns en överhängande chans att djuret tar livet av dig

things not done yet ( to do )

- let the user choose the animal and assign it to his account
- Delete method by inserting the id ,username and password(code java available , but sql request
doesn&#39;t work yet )
- if the account doesn&#39;t exist in the application , user need to be asked for the right account
-delete animal by inserting the name

how to ?
1 install mysql server
2 import the database djurförvaltning.sql as zip file
3start mysql server
4 import the project djur
5 start the java code
ps :
i use java version 9 ,it can happen som compatibility issues if you use a older version
if you find some issues after starting the project try this sql script in our database
SET GLOBAL time_zone = &#39;+2:00&#39;;
to login with admin account :
usernae :azzus
password :22hlceek
to login with user account
username: djur
password : 22hlceek
