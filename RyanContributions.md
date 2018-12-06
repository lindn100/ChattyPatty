(10.29) Added usernames to the ChatClient.java file. This will help user's separate their own messages from others, as well as let ChattyPatty directly address a user by using .split on their messages. Passes check style. 

(11.5) Modified the checkForGoodbye array in the ChatClient.java file. I added a substring check to the goodbye, so the exit statement will not have to just be "goodbye", but it could be something instead like "goodbye patty" and it will still exit.

(11.7) Added the Chatty Patty bot, ChatServer, and ClientHandler files. Added functionality within the ClientHandler to parse the message that the server recieves from any user and sends the bot's reponse to all connected users.

(11.27) Added a welcome message from Patty sent to every user whenever a new user connects to the server. Fixed a bug where Patty would send messages to only some users. Added more responses to Patty's pool. Passes checkstyle. Attempted to make a dockerfile for client and server, but have run into some issues and need help from Prof. Fahy. Created presentation poster.

(12.5) Modified the chatbot's perception of incoming messages by checking if the incoming message has punctuation at the end, and if so, removes it for the purpose of parsing the string and getting a correct response. This was implemented so that Hello! and Hello get the same response from the bot. However, the message is modified after its sent to all other users, so the user's original message is kept in-tact. It is only modified for parsing purposes for the bot.
