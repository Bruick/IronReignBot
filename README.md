# IronReignBot
Discord bot for our robotics team [Iron Reign Robotics](https://github.com/IronReign)'s Discord server using [Discord4J](https://github.com/discord4j/discord4j).

## Commands

### Blog Posts
- `!blog assign <@user> <blog post>`: Assign somebody a blogpost.
- `!blog list [<@user> | all]`: List all blog posts assigned to somebody, all users, or by default yourself.
- `!blog done [<@user>] <id>`: Mark a post as completed by its id.

### Miscelaneous
- `!ping`: Respond `no u` to test if bot is up.
- `!admin shutdown`: Shut down the bot.

## Configuration

Before this bot can be run, in the `src/main/resources` folder, you need to add an `auth.properties` file.

This file should have the following format:

```yaml
token: INSERT-DISCORD-BOT-TOKEN-HERE
admin: <@ADMIN-USER-MENTION-ID-HERE>
```

To run this bot, you can simply use Maven to build a jar file. Be aware that by using the jar provided in the releases, you will be using the Iron Reign Discord token.

## Extending this bot

This bot is split up into multiple handlers. Each handler is responsible for responding to all messages by filtering out the ones that aren't related to commands and responding to the others.
You create a handler by making a class that extends `AbstractHandler` and implementing the requisite method, adding a event handler to the Discord4J client.
It is probably easier to make a class extending `AbstractImperativeHandler`, which takes care of the boilerplate of defining the event handler and extracting the needed domain objects.
If you wish to add an Admin-locked handler, create a class that extends `AbstractAdminHandler`. This takes care of the boilerplate of `AbstractImperativeHandler`, but also ensures that the command is only called by the Admin user.
