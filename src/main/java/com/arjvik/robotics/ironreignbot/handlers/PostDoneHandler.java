package com.arjvik.robotics.ironreignbot.handlers;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

public class PostDoneHandler extends AbstractHandler {

	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
		  .on(MessageCreateEvent.class)
		  .subscribe(e -> {
				Message msg = e.getMessage();
				if (!msg.getContent().get().startsWith("!blog done"))
					return;
				String[] cmd = msg.getContent().map(s -> s.substring("!blog done".length()).trim()).map((s -> s.split("\\s+", 2))).get();
				if (cmd.length == 1 && cmd[0].matches("\\d+")) {
						String user = msg.getAuthor().get().getMention();
						int id = Integer.parseInt(cmd[0]);
						if (store.removeBlogPost(user, id)) {
							replyTo(msg, "Sucessfully removed %s's blog post (send `!blog list` again for new IDs)", user);
						} else {
							replyTo(msg, "Unable to remove post #%d from %s", id, user);
						}
				} else if (cmd.length == 2 && cmd[0].matches("<@\\d+>") && cmd[1].matches("\\d+")) {
					String user = cmd[0];
					int id = Integer.parseInt(cmd[1]);
					if (store.removeBlogPost(user, id)) {
						replyTo(msg, "Sucessfully removed %s's blog post (send `!blog list` again for new IDs)", user);
					} else {
						replyTo(msg, "Unable to remove post #%d from %s", id, user);
					}
				} else {
					replyTo(msg, "Invalid usage of `!blog done [<@user>] <post id>");
				}
		  });
	}

}
