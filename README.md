# war-game
war game


To run the war game server,
1. Pull the maven project war-game-server into your workspace
2. Navigate to the application class: com.hive.wargame.server.Application
3. Run the class with the follwing VM arguments: -Dserver.port=4747
4. You should see the server start on port 4747 in the logs.
5. Right now I am injecting a user into the database when the application starts.

To run the war game java fx client
1. Pull the maven project war-game-javafx into your workspace
2. Navigate to the application class: com.hive.jfx.wargame.JFXApplication
3. Since this is the client, it needs to know where the server is so it can communicate with it over REST
	a. Run the application with the vm arguments: -Drest.base.url=http://localhost:4747/wargameServer -Dserver.port=0
		1. The rest base url tells the application where the server is, since you are running it locally on 4747, we use that address. If it was on a different machine you would put the IP and port application is running on.
		2. The server port tells the application which port to run the server on. We want to run the server on a random port so we use port 0 and the operating system will randomly assign a port.
4. The login page should pop up. Use the default credentials:
	a. username: 7
	b. password: 7
