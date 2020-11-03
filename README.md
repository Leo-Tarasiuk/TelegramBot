#Tourism telegram bot

Web application for managing your own travel telegram bot.

##The Problem

1) Telegram bot provides the user with sights in the entered city.

2) City data must be stored in a database.

3) It is necessary to manage data about cities (add new cities and information about them, change and delete any information) 
through the REST WebService.

##Input Example:

Minsk

##The output will be:

I advise you to visit the following places:

* National library

* Museum of WOW

* Bolshoi Theater of Belarus

* Victory Square

##Required to run

* Bot username: tg_tourism_bot
* Bot token: 1420505776:AAEEVE8ZvawSqc2o4fK48CXQYgnRDJ8pc0I

For the telegram bot to work: 
* launch the application 
* open Ngrok and enter the command "ngrok http 8088" and get the WebHookPath 
* insert the received WebHookPath into the url "https://api.telegram.org/bot1420505776:AAEEVE8ZvawSqc2o4fK48CXQYgnRDJ8pc0I/setWebhook?url=WebHookPath" 
* bot is ready to work
