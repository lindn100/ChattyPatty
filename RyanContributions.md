(10.29) Added usernames to the ChatClient.java file. This will help user's separate their own messages from others, as well as let ChattyPatty directly address a user by using .split on their messages. Passes check style. 

(11.5) Modified the checkForGoodbye array in the ChatClient.java file. I added a substring check to the goodbye, so the exit statement will not have to just be "goodbye", but it could be something instead like "goodbye patty" and it will still exit.

(11.7) Added the Chatty Patty bot, ChatServer, and ClientHandler files. Added functionality within the ClientHandler to parse the message that the server recieves from any user and sends the bot's reponse to all connected users.
