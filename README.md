How to use:
Backend
1) Download repo.
2) Open remote100Test like maven project -> start spring app from Remote100TestApplication class.

Frontend
1) Open ui in separate window or IDE.
2) Open terminal and paste: yarn install
3) After project will be installed paste: yarn start

After that you can use localhost:8080/tickConsume endpoint to post orders (remember to authenticate before send a requests with:
username=admin
password=password123)

And UI will be using localhost:8080/getTicks to display the orders

What was implemented:
1) Spring service with two endpoints

/tickConsume - endpoint secured by JWT handles POST requests woth JSON body like:
{"userId": "134256", "currencyFrom": "EUR", "currencyTo": "GBP", "amountSell": 1000, "amountBuy": 747.10, "rate": 0.7471, "timePlaced" : "24-Jan-22 10:27:44", "originatingCountry" : "FR"}

/getTicks - endpoint handles GET requests which return list of Objects that include calculated data for ui

2) Basic UI with displaying currency pair and basic stats for it.

Future features:
1) Kafka integration to handle massive amount of requests
2) UI modification, to show data with animations and graphs (all ticks are passed by backend already)
3) Add sign-in/sign-up for UI. And add JWT to /getTicks
4) Add ability to sell and buy (add db, ui components, controller endpoints, service methods)

